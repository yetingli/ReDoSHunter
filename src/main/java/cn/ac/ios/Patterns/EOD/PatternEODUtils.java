package cn.ac.ios.Patterns.EOD;

import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Bean.*;
import cn.ac.ios.Utils.Constant;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

import static cn.ac.ios.TreeNode.Utils.*;
import static cn.ac.ios.TreeNode.Utils.setInitialChainIndex;
import static cn.ac.ios.Utils.BracketUtils.isSpecialCharacterBracket;
import static cn.ac.ios.Utils.DkBricsAutomatonUtils.*;
import static cn.ac.ios.Utils.FlagsUtils.*;
import static cn.ac.ios.Utils.GenMatchStringUtils.*;
import static cn.ac.ios.Utils.NegateUtils.*;
import static cn.ac.ios.Utils.RegexUtils.*;
import static cn.ac.ios.Utils.SpecialStringUtils.*;
import static cn.ac.ios.Utils.SplitRegexUtils.getR0;
import static cn.ac.ios.Utils.SplitRegexUtils.getR4;

public class PatternEODUtils {
    private static TreeNode getReDoSTree(String regex, String language) throws InterruptedException {
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

    private static TreeNode getStandardizedReDoSTree(TreeNode sourceReDoSTree, String language) throws InterruptedException {
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

        // 计算所有结点的first last followLast nullable flexible
        ReDoSTree.calculateFiveAttributesNullableAndFirstAndLastAndFlexibleAndFollowLast();

        return ReDoSTree;
    }

//    public static TreeNode getReDoSTree(String regex, String language) throws InterruptedException {
//        language = language.toLowerCase();
//
//        // 最开头的预处理
//        regex = rewriteRegex(regex);
//        regex = reduceLocalFlags(regex);
//        regex = removeAnnotationByFlagX(regex);
//        regex = processLocalFlag(regex);
//        regex = replaceLocalFlagGM(regex);
//
//        // 建树
//        TreeNode ReDoSTree = createReDoSTree(regex, language);
//
//        // 转换[\w-.] -> [\w\-.] 而 [a-z]保留 为了regexlib
//        ReDoSTree.rewriteIllegalBarSymbol();
//
//        // 处理java中奇奇怪怪的character_class 及 交集问题
//        if (language.equals("java")) {
//            ReDoSTree.dealWithCharacterClassInJava();
//        }
//
//        // 去group name
//        ReDoSTree.deleteGroupName();
//
//        // 针对snort数据集中出现的{?写法 需要在{前加\ 暂不知是否还有其他需要加斜杠的
//        ReDoSTree.addBackslashBeforeSomeCharacters();
//
//        // 将方括号中的\0~\777重写为\u0000~\u0777
//        ReDoSTree.rewriteUnicodeNumberInBracketNode();
//
//        // 将方括号中的\b删除 因为方括号中的\b表示退格符
//        ReDoSTree.reWriteBackspace();
//
//        // 优化方括号结点, 将内部重复的字符删掉
//        // 这里假设结点内部不会嵌套方括号结点/补结点
//        ReDoSTree.optimizeBracketNode();
//
////        ReDoSTree = refactorAssertPattern(ReDoSTree);
//
//        // 处理\x{....} \xff
//        ReDoSTree.escapeHexadecimal();
//
//        // 处理特殊斜杠字符 根据不同的语言
//        ReDoSTree.rewriteSpecialBackslashCharacterForDifferentLanguage(language);
//
//        // 删除Flags
//        ReDoSTree = getNodeByRemoveRegExpFlag(ReDoSTree);
//
//        ReDoSTree = getNodeByRemoveLocalFlag(ReDoSTree);
//
//        // 使用重写后的去首尾^$
////        ReDoSTree.deleteCaretAndDollarSymbols();
//
//        // 重写反向引用
//        ReDoSTree.rewriteBackreferences();
//
//        // 获取后缀
////        String suffix = getSuffixByNegateNode(ReDoSTree);
//        // 去补
////        removeNegateSymbol(ReDoSTree, Constant.SimplyLevel.HIGH);
//
//        // 新版重写空串
//        ReDoSTree = removeBlankStr(ReDoSTree);
//
//        // 重写反向引用后 删除NonCapturingGroupFlag ?:
//        ReDoSTree.deleteNonCapturingGroupFlag();
//
////        ReDoSTree = refactorToDot(ReDoSTree);
//
////        ReDoSTree = removeGroup(ReDoSTree);
//
////        return new Pair<>(ReDoSTree, suffix);
//
//        // 计算所有结点的first last followLast nullable flexible
//        ReDoSTree.calculateFiveAttributesNullableAndFirstAndLastAndFlexibleAndFollowLast();
//
//        return ReDoSTree;
//    }


    private static String getRegexWithoutZeroWidthAssertion(String regex) throws InterruptedException {
        if (!regex.contains("(?<=") &&
                !regex.contains("(?<!") &&
                !regex.contains("(?=") &&
                !regex.contains("(?!") &&
                !regex.contains("\\b") &&
                !regex.contains("\\B") &&
                !regex.contains("^") &&
                !regex.contains("$") &&
                !regex.contains("\\A") &&
                !regex.contains("\\Z") &&
                !regex.contains("\\z")) {
            return regex;
        }
        TreeNode regexTree = createReDoSTree(regex);
        // 删除零宽断言
//        deleteZeroWidthAssertion(regexTree);
        refactorAssertPattern(regexTree);
        // 使用重写后的去首尾^$
        regexTree.deleteCaretAndDollarSymbols();
        // 新版重写空串
        regexTree = removeBlankStr(regexTree);
        // 重写反向引用后 删除NonCapturingGroupFlag ?:
        regexTree.deleteNonCapturingGroupFlag();

        return regexTree.getData();
    }

    // 生成中缀串forEOD
    private static String generateInfixStringForEOD(String regex1, String regex2, int model, String firstCharacter, boolean considerZeroWidthAssertions) throws InterruptedException {
        if (considerZeroWidthAssertions) {
            if (model == 1) {
                TreeNode treeNode1 = createReDoSTree("(" + regex1 + ")+(" + regex2 + ")+");
                TreeNode treeNode2 = createReDoSTree("(" + regex1 + ")+");

                // 使用重写后的去首尾^$
                treeNode1.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode1 = removeBlankStr(treeNode1);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode1.deleteNonCapturingGroupFlag();

                // 使用重写后的去首尾^$
                treeNode2.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode2 = removeBlankStr(treeNode2);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode2.deleteNonCapturingGroupFlag();

                List<String> treeNode1Trans = getTranslateRegexForAssertionsList(treeNode1);
                List<String> treeNode2Trans = getTranslateRegexForAssertionsList(treeNode2);

                List<String> regexList = new ArrayList<>();
                regexList.addAll(treeNode1Trans);
                regexList.addAll(treeNode2Trans);
                regexList.add(firstCharacter + ".*");

                return getExampleByDkBricsAutomaton2(regexList, 0);
            } else if (model == 2) {
                TreeNode treeNode1 = createReDoSTree("(" + regex1 + ")+(" + regex2 + ")+");
                TreeNode treeNode2 = createReDoSTree("(" + regex2 + ")+");

                // 使用重写后的去首尾^$
                treeNode1.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode1 = removeBlankStr(treeNode1);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode1.deleteNonCapturingGroupFlag();

                // 使用重写后的去首尾^$
                treeNode2.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode2 = removeBlankStr(treeNode2);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode2.deleteNonCapturingGroupFlag();

                List<String> treeNode1Trans = getTranslateRegexForAssertionsList(treeNode1);
                List<String> treeNode2Trans = getTranslateRegexForAssertionsList(treeNode2);

                List<String> regexList = new ArrayList<>();
                regexList.addAll(treeNode1Trans);
                regexList.addAll(treeNode2Trans);
                regexList.add(firstCharacter + ".*");

                return getExampleByDkBricsAutomaton2(regexList, 0);
            } else if (model == 3) {
                TreeNode treeNode1 = createReDoSTree("(" + regex2 + ")+(" + regex1 + ")+");
                TreeNode treeNode2 = createReDoSTree("(" + regex1 + ")+");

                // 使用重写后的去首尾^$
                treeNode1.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode1 = removeBlankStr(treeNode1);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode1.deleteNonCapturingGroupFlag();

                // 使用重写后的去首尾^$
                treeNode2.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode2 = removeBlankStr(treeNode2);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode2.deleteNonCapturingGroupFlag();

                List<String> treeNode1Trans = getTranslateRegexForAssertionsList(treeNode1);
                List<String> treeNode2Trans = getTranslateRegexForAssertionsList(treeNode2);

                List<String> regexList = new ArrayList<>();
                regexList.addAll(treeNode1Trans);
                regexList.addAll(treeNode2Trans);
                regexList.add(firstCharacter + ".*");

                return getExampleByDkBricsAutomaton2(regexList, 0);
            } else if (model == 4) {
                TreeNode treeNode1 = createReDoSTree("(" + regex2 + ")+(" + regex1 + ")+");
                TreeNode treeNode2 = createReDoSTree("(" + regex2 + ")+");

                // 使用重写后的去首尾^$
                treeNode1.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode1 = removeBlankStr(treeNode1);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode1.deleteNonCapturingGroupFlag();

                // 使用重写后的去首尾^$
                treeNode2.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode2 = removeBlankStr(treeNode2);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode2.deleteNonCapturingGroupFlag();

                List<String> treeNode1Trans = getTranslateRegexForAssertionsList(treeNode1);
                List<String> treeNode2Trans = getTranslateRegexForAssertionsList(treeNode2);

                List<String> regexList = new ArrayList<>();
                regexList.addAll(treeNode1Trans);
                regexList.addAll(treeNode2Trans);
                regexList.add(firstCharacter + ".*");

                return getExampleByDkBricsAutomaton2(regexList, 0);
            } else if (model == 5) {
                TreeNode treeNode1 = createReDoSTree(regex2 + regex1);
                TreeNode treeNode2 = createReDoSTree(regex2 + ".*");

                // 使用重写后的去首尾^$
                treeNode1.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode1 = removeBlankStr(treeNode1);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode1.deleteNonCapturingGroupFlag();

                // 使用重写后的去首尾^$
                treeNode2.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode2 = removeBlankStr(treeNode2);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode2.deleteNonCapturingGroupFlag();

                List<String> treeNode1Trans = getTranslateRegexForAssertionsList(treeNode1);
                List<String> treeNode2Trans = getTranslateRegexForAssertionsList(treeNode2);

                List<String> regexList = new ArrayList<>();
                regexList.addAll(treeNode1Trans);
                regexList.addAll(treeNode2Trans);

                return getExampleByDkBricsAutomaton2(regexList, 0);
            }
        } else {
            regex1 = getRegexWithoutZeroWidthAssertion(regex1);
            regex2 = getRegexWithoutZeroWidthAssertion(regex2);

            if (model == 1) {
                List<String> regexList1 = new ArrayList<>();
                regexList1.add("(" + regex1 + ")+(" + regex2 + ")+");
                regexList1.add("(" + regex1 + ")+");
                List<String> regexList2 = new ArrayList<>();
                regexList2.add(firstCharacter + ".*");
                return getExampleByDkBricsAutomaton(regexList1, regexList2, 0);
            } else if (model == 2) {
                List<String> regexList1 = new ArrayList<>();
                regexList1.add("(" + regex1 + ")+(" + regex2 + ")+");
                regexList1.add("(" + regex2 + ")+");
                List<String> regexList2 = new ArrayList<>();
                regexList2.add(firstCharacter + ".*");
                return getExampleByDkBricsAutomaton(regexList1, regexList2, 0);
            } else if (model == 3) {
                List<String> regexList1 = new ArrayList<>();
                regexList1.add("(" + regex2 + ")+(" + regex1 + ")+");
                regexList1.add("(" + regex1 + ")+");
                List<String> regexList2 = new ArrayList<>();
                regexList2.add(firstCharacter + ".*");
                return getExampleByDkBricsAutomaton(regexList1, regexList2, 0);
            } else if (model == 4) {
                List<String> regexList1 = new ArrayList<>();
                regexList1.add("(" + regex2 + ")+(" + regex1 + ")+");
                regexList1.add("(" + regex2 + ")+");
                List<String> regexList2 = new ArrayList<>();
                regexList2.add(firstCharacter + ".*");
                return getExampleByDkBricsAutomaton(regexList1, regexList2, 0);
            } else if (model == 5) {
                List<String> regexList1 = new ArrayList<>();
                regexList1.add(regex2 + regex1);
                List<String> regexList2 = new ArrayList<>();
                regexList2.add(regex2 + ".*");
                return getExampleByDkBricsAutomaton(regexList1, regexList2, 0);
            }
        }
        return null;
    }


    // 找需要排除的r前后的|
    private static TreeNode getOrSymbolTreeNode(TreeNode treeNode) {
        if (treeNode.isFirstChild()) {
            if (treeNode.getNextNode().getData().equals("|")) return treeNode.getNextNode();
            else return null;
        } else if (treeNode.isLastChild()) {
            if (treeNode.getPreviousNode().getData().equals("|")) return treeNode.getPreviousNode();
            else return null;
        } else {
            if (treeNode.getPreviousNode().getData().equals("|")) return treeNode.getPreviousNode();
            else if (treeNode.getNextNode().getData().equals("|")) return treeNode.getNextNode();
        }
        return null;
    }

    // 删除orNode中orNode.getChild(i)和orNode.getChild(j)
    private static String deleteTwoChildFromOrNode(TreeNode orNode, TreeNode child1, TreeNode child2) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < orNode.getChildCount(); i += 2) {
            if (orNode.getChild(i) != child1 && orNode.getChild(i) != child2) {
                list.add(orNode.getChild(i).getData());
            }
        }
        return String.join("|", list);
    }


    private static ReDoSBean getEODReDoSBeanHelper(TreeNode sourceReDoSTree, TreeNode standardizedReDoSTree, String regex) {
        ReDoSBean bean = new ReDoSBean();
        ArrayList<AttackBean> attackBeanList = new ArrayList<>();
        try {
//            Set<String> rootAlphabet = root.getLetterSet(true, true);
            // 这里临时改一下
            TreeNode treeNodeCopy = SerializationUtils.clone(standardizedReDoSTree);    // 深拷贝
            // 去补
            removeNegateSymbol(treeNodeCopy, Constant.SimplyLevel.HIGH);

            // 获取特殊后缀 正则中所有的counting结点取反
            String specialSuffixStringWithAllCountingNodes = getSpecialSuffixStringWithAllCountingNodes(standardizedReDoSTree);
            // 获取特殊后缀 通过(?<! (?!不接受
            List<String> specialSuffixStringWithNegativeAssertNode = getSpecialSuffixStringWithNegativeAssertNode(standardizedReDoSTree);

            Stack<TreeNode> stack = new Stack<>();
            stack.push(standardizedReDoSTree);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (isQuantifierNode(node)) continue;
                if (isGeneralizedCountingNodeWithMaxNumGreaterThanOne(node)) {
                    node = getGroupSubNode(node);
                    TreeNode countingNode = node;   // node + counting的备份指针

                    String countingNodeR4 = getR4(standardizedReDoSTree, countingNode);    // 特殊后缀构造
                    TreeNode specialParentR4Tree = createReDoSTree(countingNodeR4);
                    if (isCanEmptyNode(specialParentR4Tree)) {
                        countingNodeR4 = "(" + node.getData() + ")(" + countingNodeR4 + ")";
                        specialParentR4Tree = createReDoSTree(countingNodeR4);
                    }
                    String specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree);

                    node = node.getChild(0);
                    node = getGroupSubNode(node);

                    ArrayList<TreeNode> allOrNodeList = node.getAllOrNode();
                    for (TreeNode orNode : allOrNodeList) {
                        Set<String> infix1Set = new HashSet<>();
                        Set<String> infix3Set = new HashSet<>();

                        String r0 = getR0(node, orNode);
                        String r4 = getR4(node, orNode);

                        TreeNode r0Tree = createReDoSTree(r0);
                        TreeNode r4Tree = createReDoSTree(r4);

                        // 使用重写后的去首尾^$
                        r0Tree.deleteCaretAndDollarSymbols();
                        // 新版重写空串
                        r0Tree = removeBlankStr(r0Tree);
                        // 重写反向引用后 删除NonCapturingGroupFlag ?:
                        r0Tree.deleteNonCapturingGroupFlag();

                        // 使用重写后的去首尾^$
                        r4Tree.deleteCaretAndDollarSymbols();
                        // 新版重写空串
                        r4Tree = removeBlankStr(r4Tree);
                        // 重写反向引用后 删除NonCapturingGroupFlag ?:
                        r4Tree.deleteNonCapturingGroupFlag();

                        List<String> r0Trans = getTranslateRegexForAssertionsList(r0Tree);
                        List<String> r4Trans = getTranslateRegexForAssertionsList(r4Tree);

                        String infix1 = getExampleByDkBricsAutomaton2(r0Trans, 0);
                        if (infix1 != null) infix1Set.add(infix1);
                        String infix3 = getExampleByDkBricsAutomaton2(r4Trans, 0);
                        if (infix3 != null) infix3Set.add(infix3);

                        r0 = getRegexWithoutZeroWidthAssertion(r0);
                        r4 = getRegexWithoutZeroWidthAssertion(r4);

                        infix1 = getExampleByDkBricsAutomaton(Collections.singletonList(r0), null, 0);
                        if (infix1 != null) infix1Set.add(infix1);
                        infix3 = getExampleByDkBricsAutomaton(Collections.singletonList(r4), null, 0);
                        if (infix3 != null) infix3Set.add(infix3);

                        if (infix1Set.isEmpty() || infix3Set.isEmpty()) continue;

                        for (int i = 0; i < orNode.getChildCount() - 2; i += 2) {
                            for (int j = i + 2; j < orNode.getChildCount(); j++) {
                                if (Collections.disjoint(orNode.getChild(i).getFirst(), orNode.getChild(j).getFirst()) &&
                                        Collections.disjoint(orNode.getChild(i).getFirst(), orNode.getChild(j).getFollowLast()) &&
                                        Collections.disjoint(orNode.getChild(j).getFirst(), orNode.getChild(i).getFollowLast())
                                ) {
                                    continue;
                                }

                                String prefix1 = getPrefixString(standardizedReDoSTree, node, true); // 获取的是前缀正则+中缀正则的生成串
                                String prefix2 = getPrefixString(standardizedReDoSTree, node, false);
                                String prefix3 = treeNodeCopy.getMatchStr(treeNodeCopy.getChildByChainIndexString(node.getChainIndex()));

                                Set<String> prefixSet = new HashSet<>();
                                if (prefix1 != null) prefixSet.add(prefix1);
                                if (prefix2 != null) prefixSet.add(prefix2);
                                if (prefix3 != null) prefixSet.add(prefix3);
                                if (prefixSet.isEmpty()) continue;


                                if (!Collections.disjoint(orNode.getChild(i).getFirst(), orNode.getChild(j).getFirst())) {
                                    // 对于r = (r1|r2|...|rn)+ 如果r1 r2有歧义
                                    // 如果是r1.first ∩ r2.first ≠ ∅ 中缀w = (r1|r3|...|rn)+ (r2|...|rn)+ & (r1|r3|...|rn)+ & firstCharacter .*
                                    //                                  w = (r1|r3|...|rn)+ (r2|...|rn)+ & (r2|r3|...|rn)+ & firstCharacter .*
                                    //                                  w = (r2|r3|...|rn)+ (r1|r3|...|rn)+ & (r2|r3|...|rn)+ & firstCharacter .*
                                    //                                  w = (r2|r3|...|rn)+ (r1|r3|...|rn)+ & (r2|r3|...|rn)+ & firstCharacter .*


                                    Set<String> intersection = new HashSet<>();    // 交集
                                    intersection.addAll(orNode.getChild(i).getFirst());
                                    intersection.retainAll(orNode.getChild(j).getFirst());
                                    String firstCharacter = intersection.iterator().next();  // 获取第一个元素
                                    // 需要对\f \n \r \v \r
                                    if (firstCharacter.equals("\\t")) {
                                        firstCharacter = "\t";
                                    } else if (firstCharacter.equals("\\f")) {
                                        firstCharacter = "\f";
                                    } else if (firstCharacter.equals("\\n")) {
                                        firstCharacter = "\n";
                                    } else if (firstCharacter.equals("\\v") || firstCharacter.equals("\\u000b")) {
                                        firstCharacter = "\u000b";
                                    } else if (firstCharacter.equals("\\r")) {
                                        firstCharacter = "\r";
                                    } else if (isSpecialCharacterBracket(firstCharacter)) {
                                        firstCharacter = "\\" + firstCharacter;
                                    }


                                    // 删除orNode中orNode.getChild(i)和orNode.getChild(j)
                                    String tempRegex = deleteTwoChildFromOrNode(orNode, orNode.getChild(i), orNode.getChild(j));
                                    String regex1n = tempRegex.equals("") ? orNode.getChild(i).getData() : tempRegex + "|" + orNode.getChild(i).getData();    // 除掉r2
                                    String regex2n = tempRegex.equals("") ? orNode.getChild(j).getData() : tempRegex + "|" + orNode.getChild(j).getData();    // 除掉r1

                                    // 需要对\f \n \r \v \r
//                                    regex1n = repairLineFeedsCarriageReturnsAndTabCharacters(regex1n);
//                                    regex2n = repairLineFeedsCarriageReturnsAndTabCharacters(regex2n);

                                    Set<String> infixSet = new HashSet<>();
                                    String infix2 = null;
                                    infix2 = generateInfixStringForEOD(regex1n, regex2n, 1, firstCharacter, false);
                                    if (infix2 != null) infixSet.add(infix2);
                                    infix2 = generateInfixStringForEOD(regex1n, regex2n, 1, firstCharacter, true);
                                    if (infix2 != null) infixSet.add(infix2);
                                    infix2 = generateInfixStringForEOD(regex1n, regex2n, 2, firstCharacter, false);
                                    if (infix2 != null) infixSet.add(infix2);
                                    infix2 = generateInfixStringForEOD(regex1n, regex2n, 2, firstCharacter, true);
                                    if (infix2 != null) infixSet.add(infix2);
                                    infix2 = generateInfixStringForEOD(regex1n, regex2n, 3, firstCharacter, false);
                                    if (infix2 != null) infixSet.add(infix2);
                                    infix2 = generateInfixStringForEOD(regex1n, regex2n, 3, firstCharacter, true);
                                    if (infix2 != null) infixSet.add(infix2);
                                    infix2 = generateInfixStringForEOD(regex1n, regex2n, 4, firstCharacter, false);
                                    if (infix2 != null) infixSet.add(infix2);
                                    infix2 = generateInfixStringForEOD(regex1n, regex2n, 4, firstCharacter, true);
                                    if (infix2 != null) infixSet.add(infix2);

                                    Set<String> suffixSet = new HashSet<>();
                                    suffixSet.add(treeNodeCopy.getNonMatchStr());
                                    suffixSet.add(specialSuffixStringWithAllCountingNodes);
                                    suffixSet.add(specialSuffixStringWithCountingAndEndLine);
                                    suffixSet.addAll(specialSuffixStringWithNegativeAssertNode);
//                                    specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, true);
//                                    if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);
//                                    specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, false);
//                                    if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);

                                    Set<String> fullInfixSet = new HashSet<>();
                                    for (String infix1s : infix1Set) {
                                        for (String infix : infixSet) {
                                            for (String infix3s: infix3Set) {
                                                fullInfixSet.add(infix1s + infix + infix3s);
                                            }
                                        }
                                    }

                                    for (String fullInfix : fullInfixSet) {
                                        suffixSet.addAll(getSuffixString(standardizedReDoSTree, countingNode, fullInfix));
                                    }
                                    if (suffixSet.isEmpty()) continue;

                                    for (String suffix : suffixSet) {
                                        for (String prefix : prefixSet) {
                                            for (String fullInfix : fullInfixSet) {
                                                AttackBean attackBean = new AttackBean();
                                                attackBean.setPrefix(prefix);
                                                attackBean.setInfix(fullInfix);
                                                attackBean.setSuffix(suffix);
                                                attackBean.initType(AttackType.EXPONENT);
                                                attackBean.setPatternType(PatternType.EOD_i);
                                                AttackBean.conflictPointHelper conflictPointHelper = new AttackBean.conflictPointHelper();
                                                conflictPointHelper.setReDoSTree(sourceReDoSTree);
                                                conflictPointHelper.setOuterConflictPoint(countingNode.getInitialChainIndex());
                                                List<String> list = new LinkedList<>();
                                                list.add(orNode.getChild(i).getInitialChainIndex());
                                                list.add(orNode.getChild(j).getInitialChainIndex());
                                                conflictPointHelper.setInnerConflictPointList(list);
                                                attackBean.setConflictPoint(conflictPointHelper);
                                                attackBeanList.add(attackBean);
                                            }
                                        }
                                    }
                                }

                                // 如果是r1.first ∩ r2.followLast ≠ ∅ 中缀w = r2r1&r2
                                if (!Collections.disjoint(orNode.getChild(i).getFirst(), orNode.getChild(j).getFollowLast())) {
                                    // 通过拼接生成攻击串 node.getChild(j).getData() + node.getChild(i).getData()
                                    Set<String> infixSet = new HashSet<>();
                                    String infix2 = null;
                                    infix2 = generateInfixStringForEOD(orNode.getChild(i).getData(), orNode.getChild(j).getData(), 5, "", false);
                                    if (infix2 != null) infixSet.add(infix2);
                                    infix2 = generateInfixStringForEOD(orNode.getChild(i).getData(), orNode.getChild(j).getData(), 5, "", true);
                                    if (infix2 != null) infixSet.add(infix2);

                                    Set<String> suffixSet = new HashSet<>();
                                    suffixSet.add(treeNodeCopy.getNonMatchStr());
                                    suffixSet.add(specialSuffixStringWithAllCountingNodes);
                                    suffixSet.add(specialSuffixStringWithCountingAndEndLine);
                                    suffixSet.addAll(specialSuffixStringWithNegativeAssertNode);
//                                    specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, true);
//                                    if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);
//                                    specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, false);
//                                    if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);

                                    Set<String> fullInfixSet = new HashSet<>();
                                    for (String infix1s : infix1Set) {
                                        for (String infix : infixSet) {
                                            for (String infix3s: infix3Set) {
                                                fullInfixSet.add(infix1s + infix + infix3s);
                                            }
                                        }
                                    }

                                    for (String fullInfix : fullInfixSet) {
                                        suffixSet.addAll(getSuffixString(standardizedReDoSTree, countingNode, fullInfix));
                                    }
                                    if (suffixSet.isEmpty()) continue;

                                    for (String suffix : suffixSet) {
                                        for (String prefix : prefixSet) {
                                            for (String fullInfix : fullInfixSet) {
                                                AttackBean attackBean = new AttackBean();
                                                attackBean.setPrefix(prefix);
                                                attackBean.setInfix(fullInfix);
                                                attackBean.setSuffix(suffix);
                                                attackBean.initType(AttackType.EXPONENT);
                                                attackBean.setPatternType(PatternType.EOD_ii1);
                                                AttackBean.conflictPointHelper conflictPointHelper = new AttackBean.conflictPointHelper();
                                                conflictPointHelper.setReDoSTree(sourceReDoSTree);
                                                conflictPointHelper.setOuterConflictPoint(countingNode.getInitialChainIndex());
                                                List<String> list = new LinkedList<>();
                                                list.add(orNode.getChild(i).getInitialChainIndex());
                                                list.add(orNode.getChild(j).getInitialChainIndex());
                                                conflictPointHelper.setInnerConflictPointList(list);
                                                attackBean.setConflictPoint(conflictPointHelper);
                                                attackBeanList.add(attackBean);
                                            }
                                        }
                                    }
                                }

                                if (!Collections.disjoint(orNode.getChild(j).getFirst(), orNode.getChild(i).getFollowLast())) {
                                    // 通过拼接生成攻击串 node.getChild(i).getData() + node.getChild(j).getData()
                                    Set<String> infixSet = new HashSet<>();
                                    String infix2 = null;
                                    infix2 = generateInfixStringForEOD(orNode.getChild(j).getData(), orNode.getChild(i).getData(), 5, "", false);
                                    if (infix2 != null) infixSet.add(infix2);
                                    infix2 = generateInfixStringForEOD(orNode.getChild(j).getData(), orNode.getChild(i).getData(), 5, "", true);
                                    if (infix2 != null) infixSet.add(infix2);

                                    Set<String> suffixSet = new HashSet<>();
                                    suffixSet.add(treeNodeCopy.getNonMatchStr());
                                    suffixSet.add(specialSuffixStringWithAllCountingNodes);
                                    suffixSet.add(specialSuffixStringWithCountingAndEndLine);
                                    suffixSet.addAll(specialSuffixStringWithNegativeAssertNode);
//                                    specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, true);
//                                    if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);
//                                    specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, false);
//                                    if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);

                                    Set<String> fullInfixSet = new HashSet<>();
                                    for (String infix1s : infix1Set) {
                                        for (String infix : infixSet) {
                                            for (String infix3s: infix3Set) {
                                                fullInfixSet.add(infix1s + infix + infix3s);
                                            }
                                        }
                                    }

                                    for (String fullInfix : fullInfixSet) {
                                        suffixSet.addAll(getSuffixString(standardizedReDoSTree, countingNode, fullInfix));
                                    }
                                    if (suffixSet.isEmpty()) continue;

                                    for (String suffix : suffixSet) {
                                        for (String prefix : prefixSet) {
                                            for (String fullInfix : fullInfixSet) {
                                                AttackBean attackBean = new AttackBean();
                                                attackBean.setPrefix(prefix);
                                                attackBean.setInfix(fullInfix);
                                                attackBean.setSuffix(suffix);
                                                attackBean.initType(AttackType.EXPONENT);
                                                attackBean.setPatternType(PatternType.EOD_ii2);
                                                AttackBean.conflictPointHelper conflictPointHelper = new AttackBean.conflictPointHelper();
                                                conflictPointHelper.setReDoSTree(sourceReDoSTree);
                                                conflictPointHelper.setOuterConflictPoint(countingNode.getInitialChainIndex());
                                                List<String> list = new LinkedList<>();
                                                list.add(orNode.getChild(i).getInitialChainIndex());
                                                list.add(orNode.getChild(j).getInitialChainIndex());
                                                conflictPointHelper.setInnerConflictPointList(list);
                                                attackBean.setConflictPoint(conflictPointHelper);
                                                attackBeanList.add(attackBean);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
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

    public static ReDoSBean getEODReDoSBean(String regex, String language) {
        ReDoSBean bean = new ReDoSBean();
        try {
//            TreeNode tree = getReDoSTree(regex, language);
//            bean = getEODReDoSBeanHelper(tree, regex);
            TreeNode sourceReDoSTree = getReDoSTree(regex, language);
            TreeNode standardizedReDoSTree = getStandardizedReDoSTree(sourceReDoSTree, language);
            bean = getEODReDoSBeanHelper(sourceReDoSTree, standardizedReDoSTree, regex);
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
        String regex = "xyz(bb|ab{3,5})+xyz(\\d\\.|\\d|\\.)+xyz(abcd|a|b|c|d)+";
        regex = "(.|\\n|\\s)+";
        regex = "(\\s*<script.*>)((\\s|.)*)(<\\/script\\s*?>)";
//        regex = "^\\[\\s*(((-?([_a-z]|(?![\\u0000-\\u0239]).*|((\\\\[0-9a-f]{1,6}(\\r\\n|[ \\t\\r\\n\\f])?)|\\\\[^\\r\\n\\f0-9a-f]))([_a-z0-9-]|(?![\\u0000-\\u0239]).*|((\\\\[0-9a-f]{1,6}(\\r\\n|[ \\t\\r\\n\\f])?)|\\\\[^\\r\\n\\f0-9a-f]))*)|\\\\*)?|)?(-?([_a-z]|(?![\\u0000-\\u0239]).*|((\\\\[0-9a-f]{1,6}(\\r\\n|[ \\t\\r\\n\\f])?)|\\\\[^\\r\\n\\f0-9a-f]))([_a-z0-9-]|(?![\\u0000-\\u0239]).*|((\\\\[0-9a-f]{1,6}(\\r\\n|[ \\t\\r\\n\\f])?)|\\\\[^\\r\\n\\f0-9a-f]))*)\\s*((\\^=|\\$=|\\*=|=|~=|\\|=)\\s*((-?([_a-z]|(?![\\u0000-\\u0239]).*|((\\\\[0-9a-f]{1,6}(\\r\\n|[ \\t\\r\\n\\f])?)|\\\\[^\\r\\n\\f0-9a-f]))([_a-z0-9-]|(?![\\u0000-\\u0239]).*|((\\\\[0-9a-f]{1,6}(\\r\\n|[ \\t\\r\\n\\f])?)|\\\\[^\\r\\n\\f0-9a-f]))*)|((\\\"([^\\n\\r\\f\\\"]|\\\\n|\\r\\n|\\r|\\f|(?![\\u0000-\\u0239]).*|((\\\\[0-9a-f]{1,6}(\\r\\n|[ \\t\\r\\n\\f])?)|\\\\[^\\r\\n\\f0-9a-f]))*\\\")|(\\'([^\\n\\r\\f\\']|\\\\n|\\r\\n|\\r|\\f|(?![\\u0000-\\u0239]).*|((\\\\[0-9a-f]{1,6}(\\r\\n|[ \\t\\r\\n\\f])?)|\\\\[^\\r\\n\\f0-9a-f]))*\\')))\\s*)?\\]$";
        regex = "(\\/\\*([^*]|[\\r\\n]|(\\*+([^*\\/]|[\\r\\n])))*\\*+\\/)|(\\/\\/.*)";
        regex = "(?<lat0>\\d+)[-|\\s](?<lat1>\\d+)[.|,|\\s](?<lat2>\\d+)['|\\s]?(?<latDir>[N|n|S|s])[,|\\s|\\-|–]+(?<lon0>\\d+)[-|\\s](?<lon1>\\d+)[.|,|\\s](?<lon2>\\d+)['|\\s]?(?<lonDir>[E|e|W|w])";
        regex = "(\\d+)[-|\\s](\\d+)[.|,|\\s](\\d+)['|\\s]?([N|n|S|s])[,|\\s|\\-|–]+(\\d+)[-|\\s](\\d+)[.|,|\\s](\\d+)['|\\s]?([E|e|W|w])";
        regex = "(^[xX][= \\0]{0,2})(a\\d{3,7}|a\\d{2,6})+";
//        regex = "^(\\-)?(?:(\\d*)[. ])?(\\d+)\\:(\\d+)(?:\\:(\\d+)\\.?(\\d{3})?\\d*)?$";
        regex = "(abc|a|b|c)*";
//        regex = "(([ab]|[bc])e)*";

        regex = "^(x(y?(a+|a+)c)d)+$";

//        regex = "(d?ab+b+c?)+";
//        regex = "((\\s)*[a-zA-Z](\\s)*)*";
//        regex = "^(ab*b{0,1})+";
//        regex = "(d?(ab+)b+c?)+";
//        regex = "(c?(a+b+a+)c?)+";
        regex = "(ab?|b)+";
        regex = "^(/w|/W|[^<>+?$%{}&])+$";
        regex = "(\\s(\\\"[^\\\"]*\\\"*|[^>])*)*>";
        regex = "(?:W/)?\"((?:\\\\.|[^\"])*)\"";
        regex = "(\\S+)\\s+([^\\s\\(\\)\\{}]+(\\{[^\\{}]*})?)\\s*\\((((\\{[^\\{}]*})+|(\\([^\\(\\)]*\\))+|[^\\(\\)]+)*)\\)";
        regex = "(\"(\\\\[\"\\\\]|[^\"])*(\"|$)|\\S)+"; // 这个失败了 关键在于后缀要是空格a空格"空格 前缀空 中缀"\""
        regex = "^\\+(((i(.*?))|(m(\\d+))|(r)|(s(\\d+))|(\\/)|(up(\\d{3}))),)*\\t(.*?)$";
        regex = "[^#]*\\s+\\bdo\\b(\\s*|(\\s+\\|.+\\|\\s*))|\\s*(\\bif\\b\\s+.*|\\belsif\\b.*|\\belse\\b.*|\\bdo\\b(\\s*|\\s+.*)|\\bcase\\b\\s+.*|\\bwhen\\b\\s+.*|\\bwhile\\b\\s+.*|\\bfor\\b\\s+.*|\\buntil\\b\\s+.*|\\bloop\\b\\s+.*|\\bdef\\b\\s+.*|\\bclass\\b\\s+.*|\\bmodule\\b\\s+.*|\\bbegin\\b.*|\\bunless\\b\\s+.*|\\brescue\\b.*|\\bensure\\b.*)+";
        regex = "(?:\\r\\n|\\r|\\n){2,}";
        regex = "/\\[DRIVE\\s+LIST\\]\\d(\\x00[a-zA-Z]\\x3A(\\s+\\[.*\\])?)+/smi";
//        regex = "^((gdb|mdb|sde|mdf))*(?<!..\\.shp|(?<!..))$";
        ReDoSBean reDosBean = getEODReDoSBean(regex, "java");
        ArrayList<AttackBean> attackBeanList = reDosBean.getAttackBeanList();
        for (int i = 0; i < attackBeanList.size(); i++) {
            System.out.println(attackBeanList.get(i).getAttackStringFormat());
        }
    }
}
