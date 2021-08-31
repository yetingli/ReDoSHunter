package cn.ac.ios.Patterns.NQ;

import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Bean.*;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

import static cn.ac.ios.TreeNode.Utils.*;
import static cn.ac.ios.Utils.BracketUtils.isInLookAroundNode;
import static cn.ac.ios.Utils.DkBricsAutomatonUtils.*;
import static cn.ac.ios.Utils.FlagsUtils.*;
import static cn.ac.ios.Utils.GenMatchStringUtils.getExampleByDkBricsAutomaton2;
import static cn.ac.ios.Utils.GenMatchStringUtils.getTranslateRegexForAssertionsList;
import static cn.ac.ios.Utils.NegateUtils.*;
import static cn.ac.ios.Utils.RegexUtils.*;
import static cn.ac.ios.Utils.SpecialStringUtils.*;
import static cn.ac.ios.Utils.SplitRegexUtils.getR4;

public class PatternNQUtils {
    public static TreeNode getReDoSTree(String regex, String language) throws InterruptedException {
        language = language.toLowerCase();

        // 最开头的预处理
        regex = rewriteRegex(regex);

        if (language.equals("pcre")) {
            // 处理POSIX 字符组
            regex = rewritePOSIXCharacterClass(regex);
            // 处理POSIX 中特殊的单词分界符号[[:<:]] -> \b(?=\w)    [[:>:]] -> \b(?<=\w)
            regex = rewritePOSIXWordBoundary(regex);
        }

        regex = reduceLocalFlags(regex);
        regex = removeAnnotationByFlagX(regex);
        regex = processLocalFlag(regex);
        regex = replaceLocalFlagGM(regex);

        // 建树
        TreeNode ReDoSTree = createReDoSTree(regex, language);
        setInitialChainIndex(ReDoSTree);

        return ReDoSTree;
    }

    public static TreeNode getStandardizedReDoSTree(TreeNode sourceReDoSTree, String language) throws InterruptedException {
        language = language.toLowerCase();

        TreeNode ReDoSTree = SerializationUtils.clone(sourceReDoSTree); // 深拷贝

        // 删除注释
        ReDoSTree.deleteAnnotation();

        // 转换[\w-.] -> [\w\-.] 而 [a-z]保留 为了regexlib
        ReDoSTree.rewriteIllegalBarSymbol();

        if (language.equals("java")) {
            // 处理java中奇奇怪怪的character_class 及 交集问题
            ReDoSTree.dealWithCharacterClassInJava();
        } else if (language.equals("python")) {
            // 处理python中{,4}的问题
            ReDoSTree.dealWithUnusualQuantifierInPython();
        }

        // 将possessive和lazy匹配转换为对应的greedy匹配
        ReDoSTree.transNonGreedyQuantifier();

        // 去group name
        ReDoSTree.deleteGroupName();

        // 针对snort数据集中出现的{?写法 需要在{前加\ 暂不知是否还有其他需要加斜杠的
        ReDoSTree.addBackslashBeforeSomeCharacters();

        // 将方括号中的\0~\777重写为\u0000~\u0777
        ReDoSTree.rewriteUnicodeNumberInBracketNode();

        // 将方括号中的\b删除 因为方括号中的\b表示退格符
        ReDoSTree.reWriteBackspace();

        // 优化方括号结点, 将内部重复的字符删掉
        // 这里假设结点内部不会嵌套方括号结点/补结点
        ReDoSTree.optimizeBracketNode();

//        refactorAssertPattern(ReDoSTree);

        // 处理\x{....} \xff
        ReDoSTree.escapeHexadecimal();

        // 处理特殊斜杠字符 根据不同的语言
        ReDoSTree.rewriteSpecialBackslashCharacterForDifferentLanguage(language);

        // 删除Flags
//        ReDoSTree = getNodeByRemoveRegExpFlag(ReDoSTree);
        ReDoSTree.getNodeByRemoveRegExpFlag();

//        ReDoSTree = getNodeByRemoveLocalFlag(ReDoSTree);
        ReDoSTree.getNodeByRemoveLocalFlag();

        // 使用重写后的去首尾^$
//        ReDoSTree.deleteCaretAndDollarSymbols();

        // 重写反向引用
        ReDoSTree.rewriteBackReferences();

        // 获取后缀
//        String suffix = getSuffixByNegateNode(ReDoSTree);
        // 去补
//        removeNegateSymbol(ReDoSTree, Constant.SimplyLevel.HIGH);

        // 新版重写空串
//        ReDoSTree = removeBlankStr(ReDoSTree);
        ReDoSTree.removeBlankStr();

        // 重写反向引用后 删除NonCapturingGroupFlag ?:
        ReDoSTree.deleteNonCapturingGroupFlag();

        return ReDoSTree;
    }

//    private static boolean isNQNode(TreeNode node) {
//        if (isGeneralizedCountingNode(node)) {
//            if (! node.isLeaf()) {
//                node = node.getChild(0);
//            }
//            while (isGroupNode(node)) {
//                node = node.getChild(1);
//            }
//            if (isGeneralizedCountingNode(node)) {
//                return true;
//            }
//            for (int i = 0; i < node.getChildCount(); i++) {
//                if (isNQNodeHelper(node.getChild(i))) return true;
//            }
//            return false;
//        } else {
//            return false;
//        }
//    }

//    private static boolean isNQNodeHelper(TreeNode node) {
//        if (isGeneralizedCountingNode(node)) {
//            return true;
//        } else {
//            while (isGroupNode(node)) {
//                node = node.getChild(1);
//            }
//            for (int i = 0; i < node.getChildCount(); i++) {
//                if (isNQNodeHelper(node.getChild(i))) return true;
//            }
//        }
//        return false;
//    }


//    private static String getInfixForNQ(String outerRegex, String innerRegex) throws InterruptedException {
//        List<String> regexList1 = new ArrayList<>();
//        regexList1.add(outerRegex);
//        regexList1.add(innerRegex);
//        return getExampleByDkBricsAutomaton(regexList1);
//    }

//    private static String getInfixForNQwithLookAround(TreeNode parent, TreeNode child) throws InterruptedException {
//        String trans = translateRegexForLookAround(parent);
//        return getInfixForNQ(trans, child.getData());
//    }


    private static String getInfixForNQ(TreeNode root, TreeNode parent, TreeNode child, boolean considerZeroWidthAssertions) throws InterruptedException {
        if (considerZeroWidthAssertions) {  // 考虑零宽断言
            List<String> innerTrans = getTranslateRegexForAssertionsList(parent);
            List<String> regexList1 = new ArrayList<>(innerTrans);
            TreeNode childCopy = SerializationUtils.clone(child);
            // 删除零宽断言
//            deleteZeroWidthAssertion(childCopy);
            refactorAssertPattern(childCopy);
            // 使用重写后的去首尾^$
            childCopy.deleteCaretAndDollarSymbols();
            // 新版重写空串
            childCopy = removeBlankStr(childCopy);
            // 重写反向引用后 删除NonCapturingGroupFlag ?:
            childCopy.deleteNonCapturingGroupFlag();
            regexList1.add(childCopy.getData());
            return getExampleByDkBricsAutomaton2(regexList1, 1);
        } else {
            TreeNode parentCopy = SerializationUtils.clone(parent);
            // 删除零宽断言
            deleteZeroWidthAssertion(parentCopy);
            // 使用重写后的去首尾^$
            parentCopy.deleteCaretAndDollarSymbols();
            // 新版重写空串
            parentCopy = removeBlankStr(parentCopy);
            // 重写反向引用后 删除NonCapturingGroupFlag ?:
            parentCopy.deleteNonCapturingGroupFlag();
            List<String> regexList1 = new ArrayList<>();
            regexList1.add(parentCopy.getData());
            TreeNode childCopy = SerializationUtils.clone(child);
            // 删除零宽断言
            deleteZeroWidthAssertion(childCopy);
            // 使用重写后的去首尾^$
            childCopy.deleteCaretAndDollarSymbols();
            // 新版重写空串
            childCopy = removeBlankStr(childCopy);
            // 重写反向引用后 删除NonCapturingGroupFlag ?:
            childCopy.deleteNonCapturingGroupFlag();
            regexList1.add(childCopy.getData());
            return getExampleByDkBricsAutomaton2(regexList1, 1);
        }
    }

    private static ReDoSBean getNQReDoSBeanHelper(TreeNode sourceReDoSTree, TreeNode standardizedReDoSTree, String regex) {
        ReDoSBean bean = new ReDoSBean();
        ArrayList<AttackBean> attackBeanList = new ArrayList<>();
        try {
            String specialSuffixStringWithAllCountingNodes = getSpecialSuffixStringWithAllCountingNodes(standardizedReDoSTree);
            List<String> specialSuffixStringWithNegativeAssertNode = getSpecialSuffixStringWithNegativeAssertNode(standardizedReDoSTree);

            Stack<TreeNode> stack = new Stack<>();
            stack.push(standardizedReDoSTree);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (isQuantifierNode(node)) continue;
//            if (isNQNode(node)) {
                if (isGeneralizedCountingNodeWithMaxNumGreaterThanOne(node)) {
                    node = getGroupSubNode(node);
//                TreeNode child = node.getChild(0);
//                while (isGroupNode(child)) {
//                    child = child.getChild(1);
//                }
//                System.out.println("child.getData() = " + child.getData());

                    ArrayList<TreeNode> allGeneralizedCountingNode = node.getAllGeneralizedCountingWithMaxNumLeqOneNode();
                    for (TreeNode generalizedCountingNode : allGeneralizedCountingNode) {
                        if (node == generalizedCountingNode) continue;

                        // 要求counting {m,n}中 m ≠ n
                        if (isEqualCountingNode(generalizedCountingNode)) continue;

                        if (isInLookAroundNode(generalizedCountingNode)) continue;

                        if (judgeInNodeOneAllNodesExceptNodeTwoCanEmpty(node, generalizedCountingNode)) {
//                        AttackBean attackBean = new AttackBean();
//                        attackBean.setPrefix(root.getMatchStr(generalizedCountingNode));
//                        attackBean.setRepeat(generalizedCountingNode.getMatchStrWithCounting());
//                        attackBean.setSuffix(root.getNonMatchStr() + "_NQ");
//                        attackBean.initType(AttackType.EXPONENT);
//                        attackBeanList.add(attackBean);

//                        // ^\\(\\[\w-]+){1,}(\\[\w-()]+(\s[\w-()]+)*)+(\\(([\w-()]+(\s[\w-()]+)*)+\.[\w]+)?)?$
//                        // "\\\\1\\1\\"+"1"*32+"◎@! _1_NQ"
//                        // "\\\\-{1,}\\(\\"+"("*2048+"◎@! _1_NQ"
//
//                        String infix = getInfixForNQ(generalizedCountingNode.getData());
//                        String infix = getInfixForNQwithLookAround(node, generalizedCountingNode);
                            String infix1 = getInfixForNQ(standardizedReDoSTree, node, generalizedCountingNode, true);
                            String infix2 = getInfixForNQ(standardizedReDoSTree, node, generalizedCountingNode, false);
                            String infix3 = node.getMatchStrWithZeroWidthAssertions();

                            Set<String> infixSet = new HashSet<>();
                            if (infix1 != null) infixSet.add(infix1);
                            if (infix2 != null) infixSet.add(infix2);
                            if (infix3 != null) infixSet.add(infix3);
                            if (infixSet.isEmpty()) continue;

                            String prefix1 = getPrefixString(standardizedReDoSTree, generalizedCountingNode, true); // 获取的是前缀正则+中缀正则的生成串
                            String prefix2 = getPrefixString(standardizedReDoSTree, generalizedCountingNode, false);

                            Set<String> prefixSet = new HashSet<>();
                            if (prefix1 != null) prefixSet.add(prefix1);
                            if (prefix2 != null) prefixSet.add(prefix2);
                            if (prefixSet.isEmpty()) continue;

                            String r4 = getR4(standardizedReDoSTree, generalizedCountingNode);    // 特殊后缀构造
                            TreeNode r4Tree = createReDoSTree(r4);
                            if (isCanEmptyNode(r4Tree)) {
                                r4 = "(" + node.getData() + ")(" + r4 + ")";
                                r4Tree = createReDoSTree(r4);
                            }
                            String specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(r4Tree);


                            Set<String> suffixSet = new HashSet<>();
                            suffixSet.add(specialSuffixStringWithCountingAndEndLine);
                            suffixSet.add(specialSuffixStringWithAllCountingNodes);
                            suffixSet.addAll(specialSuffixStringWithNegativeAssertNode);
//                            specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(r4Tree, true);
//                            if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);
//                            specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(r4Tree, false);
//                            if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);

                            for (String infix : infixSet) {
                                suffixSet.addAll(getSuffixString(standardizedReDoSTree, generalizedCountingNode, infix));
                                suffixSet.addAll(getSuffixString(standardizedReDoSTree, node, infix));
                            }
                            if (suffixSet.isEmpty()) continue;

                            for (String prefix : prefixSet) {
                                for (String infix : infixSet) {
                                    for (String suffix : suffixSet) {
                                        AttackBean attackBean = new AttackBean();
                                        attackBean.setPrefix(prefix);
                                        attackBean.setInfix(infix);
                                        attackBean.setSuffix(suffix);
                                        attackBean.initType(AttackType.EXPONENT);
                                        attackBean.setPatternType(PatternType.NQ);
                                        AttackBean.conflictPointHelper conflictPointHelper = new AttackBean.conflictPointHelper();
                                        conflictPointHelper.setReDoSTree(sourceReDoSTree);
//                                        printTree(sourceReDoSTree);
//                                        printTree(standardizedReDoSTree);
//                                        System.out.println(node.getChainIndex());
                                        conflictPointHelper.setOuterConflictPoint(node.getInitialChainIndex());
                                        List<String> list = new LinkedList<>();
                                        list.add(generalizedCountingNode.getInitialChainIndex());
                                        conflictPointHelper.setInnerConflictPointList(list);
                                        attackBean.setConflictPoint(conflictPointHelper);
//                                        attackBean.setConflictPoint(new Pair<>(outerConflictPoint, list));
                                        attackBeanList.add(attackBean);
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (int i = node.getChildCount() - 1; i >= 0; i--) {
                        stack.push(node.getChild(i));
                    }
                }
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        } finally {
            if (attackBeanList.size() > 0) {
                bean.setAttackBeanList(attackBeanList);
                bean.setReDoS(true);
            } else {
                bean.setReDoS(false);
            }
        }
        return bean;
    }

    // 判断在结点1中，除了结点2，是否都可以为空
    // 例如 ((d?a+|c)b?)+ 中 a+ 的两边都可以为空
    private static boolean judgeInNodeOneAllNodesExceptNodeTwoCanEmpty(TreeNode node1, TreeNode node2) {
        TreeNode nodeOne = node1;
        TreeNode nodeTwo = node2;
        while (nodeTwo != nodeOne) {
            TreeNode parent = nodeTwo.getParent();
            if (parent != null) {
                if (isOrNode(parent)) {

                } else {
                    for (int i = 0; i < parent.getChildCount(); i++) {
//                        if (isZeroWidthAssertion(parent.getChild(i))) continue;
                        if (parent.getChild(i) != nodeTwo) {
                            if (! isCanEmptyNode(parent.getChild(i))) { // 不为空
//                            System.out.println("parent.getChild(i).getData() = " + parent.getChild(i).getData());
//                            System.out.println("parent.getChild(i).getLetterSetMustHas() = " + parent.getChild(i).getLetterSetMustHas());
                                return false;
                            }
                        }
                    }
                }
                nodeTwo = parent;
            } else {
                break;
            }
        }
        return true;
    }

    public static ReDoSBean getNQReDoSBean(String regex, String language) {
        ReDoSBean bean = new ReDoSBean();
        try {
            TreeNode sourceReDoSTree = getReDoSTree(regex, language);
            TreeNode standardizedReDoSTree = getStandardizedReDoSTree(sourceReDoSTree, language);
            bean = getNQReDoSBeanHelper(sourceReDoSTree, standardizedReDoSTree, regex);
        } catch (InterruptedException e) {
            bean.setReDoS(false);
        } catch (Exception e) {
//            System.out.println(regex);
//            e.printStackTrace();
            bean.setMessage("PARSE ERROR");
            bean.setReDoS(false);
        }
        return bean;
    }


    public static void main(String[] args) throws InterruptedException {
        String regex = "c((a*)*|((b)*)*)*(a+|b)\\s+\\s+(a+|b)(\\d+)+(\\s|\\t)(a*b+a*)+d[a-z]";
//        regex = "[(a*)*]";
        regex = "c[a-z]+(\\d{2,3}){2,}a+";
        regex = "((((([abc]))+)))+";
        regex = "((a|[bc]+)+)";
        regex = "(a+b+|c)+";
        regex = "(-?)\\d+(\\.?(\\d+?)?(\\w+))?";
        regex = "b(a?(\\d+)?(\\w+)?)?";
//        regex = "(a?(\\d+)?(\\w+)?)?";
//        regex = "((a?|c)b?)+";
//        regex = "(a?b+|c)+";
//        regex = "((c{0,})+a+b?)+";
        regex = "((a+b|c+b)+)?";
        regex = "((([A-z0-9]*(\\s))*[a-z.])+)*(?!([A-z0-9]*(\\s)))";
        regex = "\\s*(?:\"([^\"]*)\"|([^,\"\"<>]*))?\\s*(?:(?:,|<|\\s+|^)([^<@\\s,]+@[^>@\\s,]+)>?)\\s*";
        regex = "(\\s+[^a-z0-9]+|[^a-z0-9']+\\s+|[\\s&\\/]|^)+(\\w)";
        regex = "/^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})).?)(?::\\d{2,5})?(?:[/?#]\\S*)?$/i";
        regex = "(b|a+)+$";
        regex = "(bb*b)+";
        regex = "^(?:(?:((?![0-9_])[a-zA-Z0-9_]+)\\.?)+)(?<!\\.)$";
        regex = "(?=^.{1,254}$)(^(?:(?!\\d+\\.|-)[a-zA-Z0-9_\\-]{1,63}(?<!-)\\.?)+(?:[a-zA-Z]{2,})$)";
        regex = "^((((?![0-9_])[a-zA-Z0-9_]+)\\.?)+)(?<!\\.)$";
        regex = "^((?:(?!0)\\d*)*(?:\\D*)?)(\\d+)$";
//        regex = "^((?:(?=00123456)\\d*)*)cccc$";
//        regex = "^((?:\\d*(?<=00123456))*)cccc$";
//        regex = "^((?:\\d*(?<!00123456))*)cccc$";
        regex = "(?<=(?:\\n|:|^)\\s*?)(if|end\\sif|elseif|else|for\\seach|for|next|call|class|exit|do|loop|const|dim|erase|option\\s(?:explicit|implicit)|(?:public|private|end)\\ssub|(?:public|private|end)\\sfunction|private|public|redim|select\\scase|end\\sselect|case\\selse|case|set|while|wend|with|end\\swith|on\\serror\\sgoto\\s0|on\\serror\\sresume\\snext|exit|end\\sclass|property\\slet|property\\sget|property\\sset)(?=\\s|$)";
        regex = "^(?:(?:[\\+]?(?<CountryCode>[\\d]{1,3}(?:[ ]+|[\\-.])))?[(]?(?<AreaCode>[\\d]{3})[\\-/)]?(?:[ ]+)?)?(?<Number>[a-zA-Z2-9][a-zA-Z0-9 \\-.]{6,})(?:(?:[ ]+|[xX]|(i:ext[\\.]?)){1,2}(?<Ext>[\\d]{1,5}))?$";
        regex = "^\\w+(([-+']|[-+.]|\\w+))*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        regex = "(?=a)(([-+']|[-+.]|\\w+))*";
        regex = "^((?:(?!0)\\d*)*(?:\\D*)?)(\\d+)$";
        regex = "(?P<foo>(y)x?|z)(?<foo2>(y)x)\\k'foo'\\k<foo>(?P=foo)\\1(?'foo1'x)";
        regex = "(?<b>a)\\1\\g1\\g{1}\\g{-1}\\k<b>\\k'b'\\k{b}\\g{b}(?P=b)(?:c)(?'a'c)(?P<c>a)";
        regex = "^[-]?P(?!$)(?:(?<year>\\d+)+Y)?(?:(?<month>\\d+)+M)?(?:(?<days>\\d+)+D)?(?:T(?!$)(?:(?<hours>\\d+)+H)?(?:(?<minutes>\\d+)+M)? (?:(?<seconds>\\d+(?:\\.\\d+)?)+S)?)?$";
        regex = "(b+)+(?!A)";
//        regex = "(b+)+\\s*.*";
        regex = "(?:\"\"(?:(?:(?:\\\\.)|[^\"\"\\\\\\r\\n])*)\"\"|'(?:(?:(?:\\\\.)|[^'\\\\\\r\\n])*)'|`(?:(?:(?:\\\\.)|[^`\\\\\\r\\n])*)`|(?:\\s?(?:\\#|--\\ ).*(?=[\\r\\n]))|(?:/\\*(?:(?:[^*]|\\*(?!/))*)\\*/)|(?:[^;`'\"\"](?!(?:--\\ |\\#|/\\*)))*(?:[^;`'\"\"](?=(?:--\\ |\\#|/\\*)))?)*";
//        regex = "(([\\n,  ])*((<+)([^<>]+)(>*))+([\\n,  ])*)+";
//        regex = "((a+[\u0000-`c-\uffff]+b*)+)+[\u0000-bd-\uffff]*";
//        regex = "[^abc[d]]";
//        TreeNode ReDoSTree = getRedosTree(regex);
        regex = "(?!a{2,})[ac]((?!b)[a-z]+)+";
        regex = "xxx(?!a{2,})[ac]((?!b)[a-z]*)*yyy";
        regex = "^v=spf1[ \\t]+[+?~-]?(?:(?:all)|(?:ip4(?:[:][0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})?(?:/[0-9]{1,2})?)|(?:ip6(?:[:]([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})?(?:/[0-9]{1,2})?)|(?:a(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+)?(?:/[0-9]{1,2})?)|(?:mx(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+)?(?:/[0-9]{1,2})?)|(?:ptr(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+))|(?:exists(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+))|(?:include(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+))|(?:redirect(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+))|(?:exp(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+))|)(?:(?:[ \\t]+[+?~-]?(?:(?:all)|(?:ip4(?:[:][0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})?(?:/[0-9]{1,2})?)|(?:ip6(?:[:]([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})?(?:/[0-9]{1,2})?)|(?:a(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+)?(?:/[0-9]{1,2})?)|(?:mx(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+)?(?:/[0-9]{1,2})?)|(?:ptr(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+))|(?:exists(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+))|(?:include(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+))|(?:redirect(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+))|(?:exp(?:[:][A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?)+))|))*)?$";
        regex = "&lt;(span|font).*?(?:(?:(\\s?style=&quot;?).*?((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*(&quot;?)|(\\s?size=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(\\s?color=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(?=&gt;)).*?){3}&gt";
        regex = "(?:(?:(\\s?style=&quot;?).*?((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*(&quot;?)).*?){3}";
        regex = "(?:(?:(\\s?style=&quot;?).*?((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*(&quot;?)).*?){3}(a*)*";
        regex = "(?:(?:(\\s?color=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(?=&gt;)).*?){3}";
        regex = "&lt;(span|font).*?(?:(?:(\\s?style=&quot;?).*?((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*(&quot;?)|(\\s?size=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(\\s?color=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(?=&gt;)).*?){3}&gt;";
//        "((?=&quot;).*|(?=&quot;).*|(?=&quot;).*|(?=\\s).*|(?=&gt;).*|(?=\\s).*|(?=&gt;).*|(?=&gt;).*)"
//        regex = "(\\w)[^abc]\\1(a+)+$";
//        regex = "(?=a)([a-z]*)*(?<!b)";
//        regex = "(?ix-ms:[\\wöäüß]{2,}[\\.]?)*";

        regex = "xxx(?!a{2,})[ac]((?!b)[a-z]*)*yyy";
        regex = "^([a-z0-9]+[.+-])*([a-z0-9]+)+@(([a-z0-9]+[.-])+([a-z]{2,})$|(([0-9]|[1-9][0-9]|1[0-9]{1,2}|2[0-4][0-9]|25[0-5])(\\.|$)){4})";
        regex = "\\c0\\c1\\c2\\c3\\c4\\c5\\c6\\c7\\c8\\c9\\ca\\cb\\cc\\cd\\ce\\cf\\cg\\ch\\ci\\cj\\ck\\cl\\cm\\cn\\co\\cp\\cq\\cr\\cs\\ct\\cu\\cv\\cw\\cx\\cy\\cz\\c`\\c~\\c!\\c@\\c#\\c$\\c%\\c^\\c&\\c*\\c(\\c)\\c-\\c_\\c+\\c=\\c[\\c]\\c{\\c}\\c|\\c\\\\c:\\c;\\c\"\\c'\\c<\\c,\\c>\\c.\\c?\\c/";
        regex = "\\bCOPY\\b\\s*\\b(\\w*(-)?\\w*)*\\b";
        regex = "(\\\"([^\\\"]+|\\\"\\\")*\\\"|([^,]*))";
        regex = "^(([a-zA-Z0-9]+([\\-])?[a-zA-Z0-9]+)+(\\.)?)+[a-zA-Z]{2,6}$";
        regex = "<\\s*[\\/]?(?<tag>[a-z:_][-a-z0-9._:]*)(\\s+(?<attributes>[a-z:_]*[-a-z0-9._:]*[^\\s=><]*)\\s*=?\\s*(\"[^\"]*\"|'[^']*'|\"|')*[^\\s><]*)*\\s*[\\/]?>?";
        regex = "&lt;(span|font).*?(?:(?:(\\s?style=&quot;?).*?((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*(&quot;?)|(\\s?size=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(\\s?color=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(?=&gt;)).*?){3}&gt;";
        regex = "((((?:(?=\\s)|(?=&gt;)))|(?=&gt;)).*?){3}&gt;";
        regex = "(([\\n,  ])*((<+)([^<>]+)(>*))+([\\n,  ])*)+";
        regex = "(?:\"\"(?:(?:(?:\\\\.)|[^\"\"\\\\\\r\\n])*)\"\"|'(?:(?:(?:\\\\.)|[^'\\\\\\r\\n])*)'|`(?:(?:(?:\\\\.)|[^`\\\\\\r\\n])*)`|(?:\\s?(?:\\#|--\\ ).*(?=[\\r\\n]))|(?:/\\*(?:(?:[^*]|\\*(?!/))*)\\*/)|(?:[^;`'\"\"](?!(?:--\\ |\\#|/\\*)))*(?:[^;`'\"\"](?=(?:--\\ |\\#|/\\*)))?)*";
        regex = "^(ftp|https?):\\/\\/([^:]+:[^@]*@)?([a-zA-Z0-9][-_a-zA-Z0-9]*\\.)*([a-zA-Z0-9][-_a-zA-Z0-9]*){1}(:[0-9]+)?\\/?(((\\/|\\[|\\]|-|~|_|\\.|:|[a-zA-Z0-9]|%[0-9a-fA-F]{2})*)\\?((\\/|\\[|\\]|-|~|_|\\.|,|:|=||\\{|\\}|[a-zA-Z0-9]|%[0-9a-fA-F]{2})*\\&?)*)?(#([-_.a-zA-Z0-9]|%[a-fA-F0-9]{2})*)?$";
        regex = "^[-]?P(?!$)(?:(?<year>\\d+)+Y)?(?:(?<month>\\d+)+M)?(?:(?<days>\\d+)+D)?(?:T(?!$)(?:(?<hours>\\d+)+H)?(?:(?<minutes>\\d+)+M)? (?:(?<seconds>\\d+(?:\\.\\d+)?)+S)?)?$";
        regex = "&lt;(span|font).*?(?:(?:(\\s?style=&quot;?).*?((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*(&quot;?)|(\\s?size=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(\\s?color=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(?=&gt;)).*?){3}&gt;";
        regex = "(?im:[\\wöäüß]{2,}[\\.]?)*";
        regex = "^(,*([,;]|$)+)*$";
        regex = "([A-Za-z0-9.]+\\s*)+,";
        regex = "(?:(?:http|https):\\/\\/(?:(?:[^\\/&=()\\/§, ]*?)*\\.)+(?:\\w{2,3})+?)(?:\\/+[^ ?,'§$&()={\\[\\]}]*)*(?:\\?+.*)?$";
        regex = "[abc[:alnum:]abc]+";
        regex = "^(\\s|\\/\\*.*?\\*\\/)*[\\[\\(\\w]";
        regex = "(?:(?:http|https):\\/\\/(?:(?:[^\\/&=()\\/§, ]*?)*\\.)+(?:\\w{2,3})+?)(?:\\/+[^ ?,'§$&()={\\[\\]}]*)*(?:\\?+.*)?$";
        regex = "^(?:[^\\\\\\/#\\s]|\\\\[\\s\\S]|\\/(?!\\/\\/)|\\#(?!\\{)|\\s+(?:#(?!\\{).*)?)*";   // 中缀空格 后缀#{
//        TreeNode ReDoSTree = createReDoSTree(regex, "java");
//        System.out.println(isZeroNode(ReDoSTree.getChild(1)));
//        System.out.println(ReDoSTree.getChild(0).getChild(1).getChild(0).getChild(0).getLetterSetMustHas());
//        System.out.println(ReDoSTree.getChild(0).getChild(1).getChild(1).getChild(0).getChild(1).getData());

//        System.out.println(judgeInNodeOneAllNodesExceptNodeTwoCanEmpty(ReDoSTree, ReDoSTree.getChild(0).getChild(1).getChild(1).getChild(0).getChild(1)));

//        printTree(ReDoSTree);
//        System.out.println(ReDoSTree.getMatchStr());
        ReDoSBean reDosBean = getNQReDoSBean(regex, "java");
        ArrayList<AttackBean> attackBeanList = reDosBean.getAttackBeanList();
        for (int i = 0; i < attackBeanList.size(); i++) {
            System.out.println(attackBeanList.get(i).getAttackStringFormat());
        }
    }
}
