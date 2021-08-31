package cn.ac.ios.Patterns.EOA;

import cn.ac.ios.Bean.*;
import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Utils.Constant;
import dk.brics.automaton.Automaton;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

import static cn.ac.ios.TreeNode.Utils.*;
import static cn.ac.ios.Utils.BracketUtils.*;
import static cn.ac.ios.Utils.DkBricsAutomatonUtils.*;
import static cn.ac.ios.Utils.FlagsUtils.*;
import static cn.ac.ios.Utils.GenMatchStringUtils.*;
import static cn.ac.ios.Utils.NegateUtils.*;
import static cn.ac.ios.Utils.RegexUtils.*;
import static cn.ac.ios.Utils.SpecialStringUtils.*;
import static cn.ac.ios.Utils.SplitRegexUtils.*;

public class PatternEOAUtils {
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

//    private static TreeNode getReDoSTree(String regex, String language) throws InterruptedException {
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

    private static int getCounting(String regex1, String regex2, String firstCharacter) throws InterruptedException {
        regex1 = getRegexWithoutZeroWidthAssertion(regex1);
        regex2 = getRegexWithoutZeroWidthAssertion(regex2);

//        regex1 = repairLineFeedsCarriageReturnsAndTabCharacters(regex1);
//        regex2 = repairLineFeedsCarriageReturnsAndTabCharacters(regex2);

        // .*要转义的
        List<String> regexList1 = new ArrayList<>();
        regexList1.add("(" + regex1 + ")(" + regex2 + ")");

        // 先判断是否接受空串 b*b{0,1} 的counting应该是0 然后外面+1 b就可以攻击成功 如果再交上b.*反而会counting = 1 外面+1后生成bb,错误
        Automaton automaton = getIntersectionAutomaton(regexList1, null, 0);
        if (isAcceptEmptyString(automaton)) return 0;

        // .*不转义的
        List<String> regexList2 = new ArrayList<>();
        regexList2.add(firstCharacter + ".*");
        String str = getExampleByDkBricsAutomaton(regexList1, regexList2, 0);
        return (str == null) ? 0 : str.length();
    }

    private static void getTranslateRegexForAssertionsListHelper(TreeNode node) throws InterruptedException {
        // 使用重写后的去首尾^$
        node.deleteCaretAndDollarSymbols();
        // 新版重写空串
        node = removeBlankStr(node);
        // 重写反向引用后 删除NonCapturingGroupFlag ?:
        node.deleteNonCapturingGroupFlag();
    }


    private static Set<String> getInfixSetForEOA(String r0, String r1, String r2, String r3, String r4, List<Integer> candidateCountingList, boolean considerZeroWidthAssertions) throws InterruptedException {
        if (considerZeroWidthAssertions) {  // 考虑零宽断言
            Set<String> infixSet = new HashSet<>();

            TreeNode r0Tree = createReDoSTree(r0);
            TreeNode r1Tree = createReDoSTree(r1);
            TreeNode r2Tree = createReDoSTree(r2);
            TreeNode r3Tree = createReDoSTree(r3);
            TreeNode r4Tree = createReDoSTree(r4);



            getTranslateRegexForAssertionsListHelper(r0Tree);
            getTranslateRegexForAssertionsListHelper(r1Tree);
            getTranslateRegexForAssertionsListHelper(r2Tree);
            getTranslateRegexForAssertionsListHelper(r3Tree);
            getTranslateRegexForAssertionsListHelper(r4Tree);

            List<String> r0Trans = getTranslateRegexForAssertionsList(r0Tree);
            List<String> r1Trans = getTranslateRegexForAssertionsList(r1Tree);
            List<String> r2Trans = getTranslateRegexForAssertionsList(r2Tree);
            List<String> r3Trans = getTranslateRegexForAssertionsList(r3Tree);
            List<String> r4Trans = getTranslateRegexForAssertionsList(r4Tree);



            // (\s*([,;]|$)+\s*)*
            // r0 =
            // r1 = \s*
            // r2 = (([,;])?)+
            // r3 = \s*
            // r4 =
            TreeNode r1AndR3Tree = createReDoSTree("(" + r1 + ")(" + r3 + ")");
            String infix1 = r0Tree.getMatchStr();
            String infix2 = getMatchStringWithEndLineInOrNode(r1AndR3Tree);
            String infix3 = r2Tree.getMatchStr();
            String infix4 = r4Tree.getMatchStr();
            if (!"".equals(infix1 + infix2 + infix3 + infix4)) {
                infixSet.add(infix1 + infix2 + infix3 + infix4);
            }


            infix1 = getExampleByDkBricsAutomaton2(r0Trans, 0);
            if (infix1 == null) return null;

            List<String> r1AndR3Trans = new ArrayList<>();
            r1AndR3Trans.addAll(r1Trans);
            r1AndR3Trans.addAll(r3Trans);

            Set<String> infix2Set = new HashSet<>();
            for (int counting: candidateCountingList) {
                infix2 = getExampleByDkBricsAutomaton2(r1AndR3Trans, counting);
                if (infix2 != null) infix2Set.add(infix2);
            }
            if (infix2Set.isEmpty()) return null;

            infix3 = getExampleByDkBricsAutomaton2(r2Trans, 0);
            if (infix3 == null) return null;

            infix4 = getExampleByDkBricsAutomaton2(r4Trans, 0);

            for (String infix2s: infix2Set) {
                if (!"".equals(infix1 + infix2s + infix3 + infix4)) {
                    infixSet.add(infix1 + infix2s + infix3 + infix4);
                }
            }

            return infixSet;
        } else {
            r0 = getRegexWithoutZeroWidthAssertion(r0);
            r1 = getRegexWithoutZeroWidthAssertion(r1);
            r2 = getRegexWithoutZeroWidthAssertion(r2);
            r3 = getRegexWithoutZeroWidthAssertion(r3);
            r4 = getRegexWithoutZeroWidthAssertion(r4);

            String infix1 = getExampleByDkBricsAutomaton(new ArrayList<>(Collections.singleton(r0)), null, 0);
            if (infix1 == null) return null;


            Set<String> infix2Set = new HashSet<>();
            for (int counting: candidateCountingList) {
                String infix2 = getExampleByDkBricsAutomaton(new ArrayList<>(Arrays.asList(r1, r3)), null, counting);
                if (infix2 != null) infix2Set.add(infix2);
            }
            if (infix2Set.isEmpty()) return null;

            String infix3 = getExampleByDkBricsAutomaton(new ArrayList<>(Collections.singleton(r2)), null, 0);
            if (infix3 == null) return null;

            String infix4 = getExampleByDkBricsAutomaton(new ArrayList<>(Collections.singleton(r4)), null, 0);

            Set<String> infixSet = new HashSet<>();
            for (String infix2: infix2Set) {
                if (!"".equals(infix1 + infix2 + infix3 + infix4)) {
                    infixSet.add(infix1 + infix2 + infix3 + infix4);
                }
            }
            return infixSet;
        }
    }

    private static String getInfixForEOA(String r0, String r1, String r2, String r3, String r4, int counting, boolean considerZeroWidthAssertions) throws InterruptedException {
        if (considerZeroWidthAssertions) {  // 考虑零宽断言
            TreeNode r0Tree = createReDoSTree(r0);
            TreeNode r1Tree = createReDoSTree(r1);
            TreeNode r2Tree = createReDoSTree(r2);
            TreeNode r3Tree = createReDoSTree(r3);
            TreeNode r4Tree = createReDoSTree(r4);

            getTranslateRegexForAssertionsListHelper(r0Tree);
            getTranslateRegexForAssertionsListHelper(r1Tree);
            getTranslateRegexForAssertionsListHelper(r2Tree);
            getTranslateRegexForAssertionsListHelper(r3Tree);
            getTranslateRegexForAssertionsListHelper(r4Tree);

            List<String> r0Trans = getTranslateRegexForAssertionsList(r0Tree);
            List<String> r1Trans = getTranslateRegexForAssertionsList(r1Tree);
            List<String> r2Trans = getTranslateRegexForAssertionsList(r2Tree);
            List<String> r3Trans = getTranslateRegexForAssertionsList(r3Tree);
            List<String> r4Trans = getTranslateRegexForAssertionsList(r4Tree);
            String infix1 = getExampleByDkBricsAutomaton2(r0Trans, 0);
            if (infix1 == null) return null;

            List<String> r1AndR3Trans = new ArrayList<>();
            r1AndR3Trans.addAll(r1Trans);
            r1AndR3Trans.addAll(r3Trans);
            String infix2 = getExampleByDkBricsAutomaton2(r1AndR3Trans, counting);
            if (infix2 == null) return null;

            String infix3 = getExampleByDkBricsAutomaton2(r2Trans, 0);
            if (infix3 == null) return null;

            String infix4 = getExampleByDkBricsAutomaton2(r4Trans, 0);

//        System.out.println("r0 = " + r0 + " r1 = " + r1 + " r2 = " + r2 + " r3 = " + r3 + " r4 = " + r4 + " counting = " + counting + " infix = " + infix1 + infix2 + infix3 + infix4);
            return infix1 + infix2 + infix3 + infix4;
        } else {
            r0 = getRegexWithoutZeroWidthAssertion(r0);
            r1 = getRegexWithoutZeroWidthAssertion(r1);
            r2 = getRegexWithoutZeroWidthAssertion(r2);
            r3 = getRegexWithoutZeroWidthAssertion(r3);
            r4 = getRegexWithoutZeroWidthAssertion(r4);

            String infix1 = getExampleByDkBricsAutomaton(new ArrayList<>(Collections.singleton(r0)), null, 0);
            if (infix1 == null) return null;

            String infix2 = getExampleByDkBricsAutomaton(new ArrayList<>(Arrays.asList(r1, r3)), null, counting);
            if (infix2 == null) return null;

            String infix3 = getExampleByDkBricsAutomaton(new ArrayList<>(Collections.singleton(r2)), null, 0);
            if (infix3 == null) return null;

            String infix4 = getExampleByDkBricsAutomaton(new ArrayList<>(Collections.singleton(r4)), null, 0);

//        System.out.println("r0 = " + r0 + " r1 = " + r1 + " r2 = " + r2 + " r3 = " + r3 + " r4 = " + r4 + " counting = " + counting + " infix = " + infix1 + infix2 + infix3 + infix4);
            return infix1 + infix2 + infix3 + infix4;
        }
    }



    private static String generateInfixStringForEOA(String r0, String r1, String r2, String r3, String r4, int counting) throws InterruptedException {
        String infix1 = getExampleByDkBricsAutomaton(new ArrayList<>(Collections.singleton(r0)), null, 0);
        if (infix1 == null) return null;

        String infix2 = getExampleByDkBricsAutomaton(new ArrayList<>(Arrays.asList(r1, r3)), null, counting);
        if (infix2 == null) return null;

        String infix3 = getExampleByDkBricsAutomaton(new ArrayList<>(Collections.singleton(r2)), null, 0);
        if (infix3 == null) return null;

        String infix4 = getExampleByDkBricsAutomaton(new ArrayList<>(Collections.singleton(r4)), null, 0);

//        System.out.println("r0 = " + r0 + " r1 = " + r1 + " r2 = " + r2 + " r3 = " + r3 + " r4 = " + r4 + " counting = " + counting + " infix = " + infix1 + infix2 + infix3 + infix4);
        return infix1 + infix2 + infix3 + infix4;
    }


    private static String judgeThirdCondition(String beta1, String beta2, String nodeCounting, int beta1Nullable, int beta2Nullable, boolean considerZeroWidthAssertions) throws InterruptedException {
//        if (beta1Nullable == 1 && beta2Nullable == 1) return "";
        if (!(beta1Nullable == 1 || beta2Nullable == 1)) return "";

        if (considerZeroWidthAssertions) {
            TreeNode beta1Tree = createReDoSTree("(" + beta1 + ")" + nodeCounting);
            TreeNode beta2Tree = createReDoSTree("(" + beta2 + ")" + nodeCounting);

            getTranslateRegexForAssertionsListHelper(beta1Tree);
            getTranslateRegexForAssertionsListHelper(beta2Tree);

            List<String> beta1Trans = getTranslateRegexForAssertionsList(beta1Tree);
            List<String> beta2Trans = getTranslateRegexForAssertionsList(beta2Tree);
            List<String> regexList = new ArrayList<>();
            regexList.addAll(beta1Trans);
            regexList.addAll(beta2Trans);
            String infix = getExampleByDkBricsAutomaton2(regexList, 0);
            return infix != null ? infix : "";
        } else {
            beta1 = getRegexWithoutZeroWidthAssertion(beta1);
            beta2 = getRegexWithoutZeroWidthAssertion(beta2);

            List<String> regexList1 = new ArrayList<>();
            regexList1.add("(" + beta1 + ")" + nodeCounting);
            regexList1.add("(" + beta2 + ")" + nodeCounting);
            String infix = getExampleByDkBricsAutomaton(regexList1);
            return infix != null ? infix : "";
        }

////        System.out.println(beta1);
////        System.out.println(beta2);
//        String regex = reWriteMetaEscape("((" + beta1 + ")" + nodeCounting + ")＆((" + beta2 + ")" + nodeCounting + ")");
//        regex = regex + "&(.+)";
//        regex = reductSpecailStringForDkBricsAutomaton(regex);
////        System.out.println(regex);
//        RegExp regExp = new RegExp(regex);
//        Automaton automaton = regExp.toAutomaton(false);
//        String infix = automaton.getShortestExample(true);
//        return infix != null ? infix : "";
    }

    private static String judgeFourthCondition(String beta1, String beta2, int beta1Nullable, int beta2Nullable, boolean considerZeroWidthAssertions) throws InterruptedException {
//        if (beta1Nullable == 1 || beta2Nullable == 1) return "";
        if (!(beta1Nullable != 1 && beta2Nullable != 1)) return "";

        if (considerZeroWidthAssertions) {
            TreeNode beta1Tree = createReDoSTree(beta1);
            TreeNode beta2Tree = createReDoSTree(beta2);

            getTranslateRegexForAssertionsListHelper(beta1Tree);
            getTranslateRegexForAssertionsListHelper(beta2Tree);

            List<String> beta1Trans = getTranslateRegexForAssertionsList(beta1Tree);
            List<String> beta2Trans = getTranslateRegexForAssertionsList(beta2Tree);
            List<String> regexList = new ArrayList<>();
            regexList.addAll(beta1Trans);
            regexList.addAll(beta2Trans);
            String infix = getExampleByDkBricsAutomaton2(regexList, 1);
            if (infix != null) return infix;

            TreeNode betaTree = createReDoSTree(beta1 + beta2);
            List<TreeNode> allPossibleChildren = betaTree.getAllPossibleChildren();
            for (int i = 0; i < allPossibleChildren.size(); i++) {
                TreeNode candidateNode = allPossibleChildren.get(i);
                if (isGeneralizedCountingNode(candidateNode)) {
                    String r0 = getR0(betaTree, candidateNode);            // 第一个counting前面的正则
                    String infix1 = "";
                    if (!r0.equals("")) {
                        TreeNode r0Tree = createReDoSTree(r0);
                        getTranslateRegexForAssertionsListHelper(r0Tree);
                        List<String> r0Trans = getTranslateRegexForAssertionsList(r0Tree);
                        infix1 = getExampleByDkBricsAutomaton2(r0Trans, 1);
                        if (infix1 == null) continue;
                    }
                    String r4 = getR4(betaTree, candidateNode);            // 第二个counting后面的正则
                    String infix2 = "";
                    if (!r4.equals("")) {
                        TreeNode r4Tree = createReDoSTree(r4);
                        getTranslateRegexForAssertionsListHelper(r4Tree);
                        List<String> r4Trans = getTranslateRegexForAssertionsList(r4Tree);
                        infix2 = getExampleByDkBricsAutomaton2(r4Trans, 1);
                        if (infix2 == null) continue;
                    }
                    return infix1 + infix2;
                }
            }

        } else {
            beta1 = getRegexWithoutZeroWidthAssertion(beta1);
            beta2 = getRegexWithoutZeroWidthAssertion(beta2);

            List<String> regexList1 = new ArrayList<>();
            regexList1.add(beta1);
            regexList1.add(beta2);
            String infix = getExampleByDkBricsAutomaton(regexList1);
//        System.out.println("infix = " + infix);
            if (infix != null) return infix;

            // 考虑a[ab]+b这种情况，β1 = a, β2 = [ab]+b, 即存在某一个counting 是可以吸收掉左右两边的
            TreeNode betaTree = createReDoSTree(beta1 + beta2);
            List<TreeNode> allPossibleChildren = betaTree.getAllPossibleChildren();
            for (int i = 0; i < allPossibleChildren.size(); i++) {
                TreeNode candidateNode = allPossibleChildren.get(i);
                if (isGeneralizedCountingNode(candidateNode)) {
                    String r0 = getR0(betaTree, candidateNode);            // 第一个counting前面的正则
                    String infix1 = "";
                    if (!r0.equals("")) {
                        infix1 = getExampleByDkBricsAutomaton(new ArrayList<>(Arrays.asList("(" + r0 + ")+", candidateNode.getData())));
                        if (infix1 == null) continue;
                    }
                    String r4 = getR4(betaTree, candidateNode);            // 第二个counting后面的正则
                    String infix2 = "";
                    if (!r4.equals("")) {
                        infix2 = getExampleByDkBricsAutomaton(new ArrayList<>(Arrays.asList("(" + r4 + ")+", candidateNode.getData())));
                        if (infix2 == null) continue;
                    }
                    return infix1 + infix2;
                }
            }
        }
        return "";


//        if (beta1Nullable == 1 || beta2Nullable == 1) return "";
//        List<String> regexList1 = new ArrayList<>();
//        regexList1.add(beta1);
//        regexList1.add(beta2);
//        String infix = getExampleByDkBricsAutomaton(regexList1);
//        return infix != null ? infix : "";

//        // 考虑a[ab]+b这种情况，β1 = a, β2 = [ab]+b 应该部分交即可
//        List<String> regexList1 = new ArrayList<>();
//        regexList1.add(beta1 + "[\\s\\S]*");
//        regexList1.add("[\\s\\S]*" + beta2);
////        System.out.println(regexList1);
//        String infix = getExampleByDkBricsAutomaton(regexList1);
//        System.out.println("beta1 = " + beta1 + " beta2 = " + beta2 + " infix = " + infix);
//        return infix != null ? infix : "";
    }



    private static ReDoSBean getEOAReDoSBeanHelper(TreeNode sourceReDoSTree, TreeNode standardizedReDoSTree, String regex)  {
        ReDoSBean bean = new ReDoSBean();
        ArrayList<AttackBean> attackBeanList = new ArrayList<>();
        try {
            Set<String> rootAlphabet = standardizedReDoSTree.getLetterSet(true, true);
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
                if (isQuantifierNode(node) || node.isLeaf()) continue;
                node = getGroupSubNode(node);
                if (isInBrackets(node) || isInNegateNode(node)) continue;
                TreeNode specialParent = getTheNearestParentWithMaxNumGreaterThanOneGeneralizedCounting(node);
                if (specialParent != null) {
                    String specialParentR4 = getR4(standardizedReDoSTree, specialParent);    // 特殊后缀构造
                    TreeNode specialParentR4Tree = createReDoSTree(specialParentR4);
                    if (isCanEmptyNode(specialParentR4Tree)) {
                        if (specialParentR4.equals("")) {
                            specialParentR4 = node.getData();
                        } else {
                            specialParentR4 = "(" + node.getData() + ")(" + specialParentR4 + ")";
                        }
                        specialParentR4Tree = createReDoSTree(specialParentR4);
                    }
                    String specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree);


                    node = getGroupSubNode(node);

                    String nodeCounting = specialParent.getChild(1).getData();
                    // 这里做个优化 {2,256} 这种直接改写为{2,}
                    if (nodeCounting.contains(",")) {
                        nodeCounting = "{" + getCountingFirstNum(nodeCounting) + ",}";
                    }

                    if (node == specialParent) {
                        node = node.getChild(0);
                        node = getGroupSubNode(node);
                    }

    //                System.out.println(node.getData());
                    List<TreeNode> allPossibleChildren = node.getAllPossibleChildren();
    //                System.out.println(allPossibleChildren);

                    for (int i = 0; i < allPossibleChildren.size() - 1; i++) {
                        for (int j = i + 1; j < allPossibleChildren.size(); j++) {
                            TreeNode child1 = allPossibleChildren.get(i);
                            TreeNode child2 = allPossibleChildren.get(j);
                            if (child1.isNowNodeChildOrGrandchild(child2) || child2.isNowNodeChildOrGrandchild(child1))
                                continue;
                            // 获取最近的爹
                            TreeNode nearestParent = getNearestParent(child1, child2);
                            // 最近的爹如果不是连接结点 则不是EOA
                            if (!(!isOrNode(nearestParent) && !isBracketsNode(nearestParent) && !isNegateNode(nearestParent) && !nearestParent.isLeaf() && !isGeneralizedCountingNode(nearestParent) && nearestParent.getChildCount() >= 2)) {    // 不是是连接结点
                                continue;
                            }
                            String r0 = getR0(node, child1);            // 第一个counting前面的正则
                            String r1 = child1.getData();               // 第一个counting正则
                            String r2 = getR2(node, child1, child2);    // 第一个counting到第二个counting之间的正则
                            String r3 = child2.getData();               // 第二个counting正则
                            String r4 = getR4(node, child2);            // 第二个counting后面的正则

                            // 验证是否满足 β1.followLast ∩ β2.first ≠ ∅ 或 β1.first ∩ β2.followLast ≠ ∅
                            //  或 β1.last ∩ β2.first ≠ ∅ 且 产生last的那个子正则.nullable = 1 因为这里的last是特指a?这种形式的, 但凡有a{1,2}, 就有followlast了
                            //  或 β2.last ∩ β1.first ≠ ∅ 且 产生last的那个子正则.nullable = 1 同理(aa?)*这种
                            boolean isSatisfy = false;
                            // β1 = r0, β2 = r1 + r2 + r3 + r4
                            // β1 = r0 + r1, β2 = r2 + r3 + r4
                            // β1 = r0 + r1 + r2, β2 = r3 + r4
                            // β1 = r0 + r1 + r2 + r3, β2 = r4
                            List<String> rList = new ArrayList<>(Arrays.asList(r0, r1, r2, r3, r4));
                            List<String> rInitialChainIndexList = new ArrayList<>();
                            rInitialChainIndexList.addAll(getR0InitialChainIndexList(node, child1));
                            rInitialChainIndexList.add(child1.getInitialChainIndex());
                            rInitialChainIndexList.addAll(getR2InitialChainIndexList(node, child1, child2));
                            rInitialChainIndexList.add(child2.getInitialChainIndex());
                            rInitialChainIndexList.addAll(getR4InitialChainIndexList(node, child2));

                            StringBuilder beta1 = new StringBuilder();
                            StringBuilder beta2 = new StringBuilder();
                            Set<String> beta1FirstSet = null;
                            Set<String> beta2FirstSet = null;
                            Set<String> beta1FollowLastSet = null;
                            Set<String> beta2FollowLastSet = null;
                            Set<String> beta1LastSet = null;
                            Set<String> beta2LastSet = null;
                            for (int k = 0; k < rList.size(); k++) {
                                beta1 = new StringBuilder();
                                beta2 = new StringBuilder();
                                for (int l = 0; l < rList.size(); l++) {
                                    if (l <= k) {
                                        beta1.append(rList.get(l));
                                    } else {
                                        beta2.append(rList.get(l));
                                    }
                                }
                                if (beta1.toString().equals("") || beta2.toString().equals("")) continue;
                                TreeNode beta1Tree = createReDoSTree(beta1.toString());
                                TreeNode beta2Tree = createReDoSTree(beta2.toString());
                                beta1Tree.calculateFiveAttributesNullableAndFirstAndLastAndFlexibleAndFollowLast(rootAlphabet);
                                beta2Tree.calculateFiveAttributesNullableAndFirstAndLastAndFlexibleAndFollowLast(rootAlphabet);
                                beta1FirstSet = beta1Tree.getFirst();
                                beta2FirstSet = beta2Tree.getFirst();
                                beta1FollowLastSet = beta1Tree.getFollowLast();
                                beta2FollowLastSet = beta2Tree.getFollowLast();
                                beta1LastSet = beta1Tree.getLast();
                                beta2LastSet = beta2Tree.getLast();
    //                            System.out.println("beta1 = " + beta1 + " beta2 = " + beta2 + " beta1LastSet = " + beta1LastSet + " beta2FirstSet = " + beta2FirstSet + " rList.get(k) = " + rList.get(k) + " beta2LastSet = " + beta2LastSet + " beta1FirstSet = " + beta1FirstSet + " rList.get(rList.size() - 1) = " + rList.get(rList.size() - 1));
                                if (!Collections.disjoint(beta1FollowLastSet, beta2FirstSet) || !Collections.disjoint(beta1FirstSet, beta2FollowLastSet)
//                                        || (!Collections.disjoint(beta1LastSet, beta2FirstSet) && acceptEmptyString(rList.get(k)))
//                                        || (!Collections.disjoint(beta2LastSet, beta1FirstSet) && acceptEmptyString(rList.get(rList.size() - 1)))
                                        || (!Collections.disjoint(beta1LastSet, beta2FirstSet) && isCanEmptyNode(createReDoSTree(rList.get(k))))
                                        || (!Collections.disjoint(beta2LastSet, beta1FirstSet) && isCanEmptyNode(createReDoSTree(rList.get(rList.size() - 1))))
                                ) {
                                    isSatisfy = true;
                                    break;
                                }
                            }
                            if (!isSatisfy) continue;

                            String prefix1 = getPrefixString(standardizedReDoSTree, node, true); // 获取的是前缀正则+中缀正则的生成串
                            String prefix2 = getPrefixString(standardizedReDoSTree, node, false);
                            String prefix3 = treeNodeCopy.getMatchStr(treeNodeCopy.getChildByChainIndexString(node.getChainIndex()));

                            Set<String> prefixSet = new HashSet<>();
                            if (prefix1 != null) prefixSet.add(prefix1);
                            if (prefix2 != null) prefixSet.add(prefix2);
                            if (prefix3 != null) prefixSet.add(prefix3);
                            if (prefixSet.isEmpty()) continue;

                            int counting = getCounting(r1, r3, "");
                            Set<String> infixSet = new HashSet<>();
////                            String infix1 = generateInfixStringForEOA(r0, r1, r2, r3, r4, counting);
////                            String infix2 = generateInfixStringForEOA(r0, r1, r2, r3 ,r4, counting + 1);
//                            String infix1 = getInfixForEOA(r0, r1, r2, r3, r4, counting, false);
////                            System.out.println("r0 = " + r0 + " r1 = " + r1 + " r2 = " + r2 + " r3 = " + r3 + " r4 = " + r4 + " counting = " + counting);
//                            String infix2 = getInfixForEOA(r0, r1, r2, r3, r4, counting, true);
//                            String infix3 = getInfixForEOA(r0, r1, r2, r3, r4, counting + 1, false);
//                            String infix4 = getInfixForEOA(r0, r1, r2, r3, r4, counting + 1, true);
//                            if (infix1 != null) infixSet.add(infix1);
//                            if (infix2 != null) infixSet.add(infix2);
//                            if (infix3 != null) infixSet.add(infix3);
//                            if (infix4 != null) infixSet.add(infix4);
//                            if (infixSet.isEmpty()) continue;

                            List<Integer> candidateCountingList = new ArrayList<>();
                            candidateCountingList.add(counting);
                            candidateCountingList.add(counting + 1);
                            candidateCountingList.add(counting + 5);
                            Set<String> infixSet1 = getInfixSetForEOA(r0, r1, r2, r3, r4, candidateCountingList, false);
                            Set<String> infixSet2 = getInfixSetForEOA(r0, r1, r2, r3, r4, candidateCountingList, true);
                            if (infixSet1 != null) infixSet.addAll(infixSet1);
                            if (infixSet2 != null) infixSet.addAll(infixSet2);
                            if (infixSet.isEmpty()) continue;


                            Set<String> suffixSet = new HashSet<>();
                            suffixSet.add(treeNodeCopy.getNonMatchStr());
                            suffixSet.add(specialSuffixStringWithAllCountingNodes);
                            suffixSet.add(specialSuffixStringWithCountingAndEndLine);
                            suffixSet.addAll(specialSuffixStringWithNegativeAssertNode);
//                            specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, true);
//                            if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);
//                            specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, false);
//                            if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);

                            for (String infix: infixSet) {
                                suffixSet.addAll(getSuffixString(standardizedReDoSTree, node, infix));
                            }
                            if (suffixSet.isEmpty()) continue;

                            for (String suffix: suffixSet) {
                                for (String prefix: prefixSet) {
                                    for (String infix: infixSet) {
                                        AttackBean attackBean = new AttackBean();
                                        attackBean.setPrefix(prefix);
                                        attackBean.setInfix(infix);
                                        attackBean.setSuffix(suffix);
                                        attackBean.initType(AttackType.EXPONENT);
                                        if (!Collections.disjoint(beta1FollowLastSet, beta2FirstSet) || (!Collections.disjoint(beta1LastSet, beta2FirstSet) )) {
                                            attackBean.setPatternType(PatternType.EOA_i1);
                                        } else if (!Collections.disjoint(beta1FirstSet, beta2FollowLastSet) || !Collections.disjoint(beta2LastSet, beta1FirstSet)) {
                                            attackBean.setPatternType(PatternType.EOA_ii1);
                                        }
//                                        attackBean.setPatternType(PatternType.EOA_i_or_ii);
                                        AttackBean.conflictPointHelper conflictPointHelper = new AttackBean.conflictPointHelper();
                                        conflictPointHelper.setReDoSTree(sourceReDoSTree);
                                        conflictPointHelper.setOuterConflictPoint(specialParent.getInitialChainIndex());
                                        List<Pair<String, Boolean>> list = new LinkedList<>();
                                        list.add(new Pair<>(beta1.toString(), true));
                                        list.add(new Pair<>(beta2.toString(), true));
                                        conflictPointHelper.setSplicedInnerConflictPointList(list);
                                        conflictPointHelper.setInnerConflictPointList(rInitialChainIndexList);
                                        attackBean.setConflictPoint(conflictPointHelper);
                                        attackBeanList.add(attackBean);
                                    }
                                }
                            }
                        }
                    }



                    if (! isOrNode(node) && ! isGeneralizedCountingNode(node) && ! isBracketsNode(node) && ! isNegateNode(node) && ! node.isLeaf() && ! isGroupNode(node) && node.getChildCount() >= 2) {
                        List<TreeNode> childList = node.getChildList();
                        // 分割
                        for (int i = 0; i < childList.size() - 1; i++) {
                            // nullable
                            // 若为连接结点 则对其所有的孩子结点 跳过无效值 求并集 若并集中含有false 则为false 否则为true

                            // first
                            // 若为连接结点 对其所有的孩子结点 从左到右找到第一个nullable为false的孩子 则包括该孩子在内的 前面所有的孩子的first求并集
                            //                                若找不到 则把所有的孩子的first集求并集

                            // followLast
                            // 若为连接结点 否则从右往左找到第一个nullable为false的结点 若存在 记为child(i) 最后一个孩子结点记为child(n)
                            //            该结点的followLast为 getChild(i).getFollowLast() ∪ ... ∪ getChild(n).getFollowLast() ∪ getChild(i+1).getFirst() ∪ ... ∪ getChild(n).getFirst()
                            //                               若不存在 则上式中i = 0 依然成立

                            // last
                            // 若为连接结点 对其所有的孩子结点 从右往左找到第一个nullable为false的孩子 则包括该孩子在内的 往右全部的孩子的last求并集
                            //                                若找不到 则把所有的孩子的last集求并集

                            StringBuilder beta1 = new StringBuilder();
                            StringBuilder beta2 = new StringBuilder();
                            int beta1Nullable = 1;
                            int beta2Nullable = 1;
                            Set<String> beta1FirstSet = new HashSet<>();
                            Set<String> beta2FirstSet = new HashSet<>();
                            boolean beta1FirstNullableIsFalse = true;
                            boolean beta2FirstNullableIsFalse = true;
                            Set<String> beta1FollowLastSet = new HashSet<>();
                            Set<String> beta2FollowLastSet = new HashSet<>();
                            Set<String> beta1LastSet = new HashSet<>();
                            Set<String> beta2LastSet = new HashSet<>();


                            for (int j = 0; j < childList.size(); j++) {
                                if (j <= i) {
                                    beta1.append(childList.get(j).getData());
                                    // nullable
                                    if (beta1Nullable == 1 && beta1Nullable != -1)
                                        beta1Nullable = childList.get(j).getNullable();
                                    if (beta1FirstNullableIsFalse && beta1Nullable == 1) {
                                        beta1FirstSet.addAll(childList.get(j).getFirst());
                                    } else if (beta1FirstNullableIsFalse && beta1Nullable == 0) {
                                        beta1FirstSet.addAll(childList.get(j).getFirst());
                                        beta1FirstNullableIsFalse = false;
                                    }
                                } else {
                                    beta2.append(childList.get(j).getData());
                                    // nullable
                                    if (beta2Nullable == 1 && beta2Nullable != -1)
                                        beta2Nullable = childList.get(j).getNullable();
                                    if (beta2FirstNullableIsFalse && beta2Nullable == 1) {
                                        beta2FirstSet.addAll(childList.get(j).getFirst());
                                    } else if (beta2FirstNullableIsFalse && beta2Nullable == 0) {
                                        beta2FirstSet.addAll(childList.get(j).getFirst());
                                        beta2FirstNullableIsFalse = false;
                                    }
                                }
                            }
                            // last
                            for (int j = i; j >= 0; j--) {
                                beta1LastSet.addAll(childList.get(j).getLast());
                                if (childList.get(j).getNullable() == 0) break;
                            }
                            for (int j = node.getChildCount() - 1; j > i; j--) {
                                beta2LastSet.addAll(childList.get(j).getLast());
                                if (childList.get(j).getNullable() == 0) break;
                            }


                            int j = i;
                            for (; j >= 0; j--) {
                                if (node.getChild(j).getNullable() == 0) break;
                            }
                            if (j == -1) j = 0; // 修复索引超限
                            beta1FollowLastSet.addAll(node.getChild(j).getFollowLast());
                            j += 1;
                            for (; j <= i; j++) {
                                beta1FollowLastSet.addAll(node.getChild(j).getFollowLast());
                                beta1FollowLastSet.addAll(node.getChild(j).getFirst());
                            }

                            j = node.getChildCount() - 1;
                            for (; j > i; j--) {
                                if (node.getChild(j).getNullable() == 0) break;
                            }
                            if (j == -1) j = 0; // 修复索引超限
                            beta2FollowLastSet.addAll(node.getChild(j).getFollowLast());
                            j += 1;
                            for (; j < node.getChildCount(); j++) {
                                beta2FollowLastSet.addAll(node.getChild(j).getFollowLast());
                                beta2FollowLastSet.addAll(node.getChild(j).getFirst());
                            }

    //                        System.out.println("beta1 = " + beta1 + " beta2 = " + beta2);

                            // 验证是否满足 β1.followLast ∩ β2.first ≠ ∅ 或 β1.first ∩ β2.followLast ≠ ∅
                            //  或 β1.last ∩ β2.first ≠ ∅ 且 产生last的那个子正则.nullable = 1 因为这里的last是特指a?这种形式的, 但凡有a{1,2}, 就有followlast了
                            //  或 β2.last ∩ β1.first ≠ ∅ 且 产生last的那个子正则.nullable = 1 同理(aa?)*这种
    //                        Set<String> beta1FollowLastUnionLastSet = new HashSet<>();
    //                        beta1FollowLastUnionLastSet.addAll(beta1FollowLastSet);
    //                        beta1FollowLastUnionLastSet.addAll(beta1LastSet);
    //                        Set<String> beta2FollowLastUnionLastSet = new HashSet<>();
    //                        beta2FollowLastUnionLastSet.addAll(beta2FollowLastSet);
    //                        beta2FollowLastUnionLastSet.addAll(beta2LastSet);


    //                        if (Collections.disjoint(beta1FollowLastUnionLastSet, beta2FirstSet) &&
    //                        Collections.disjoint(beta1FirstSet, beta2FollowLastUnionLastSet)) continue;


                            boolean isSatisfy = false;
                            if (!Collections.disjoint(beta1FollowLastSet, beta2FirstSet) || !Collections.disjoint(beta1FirstSet, beta2FollowLastSet)
                                    || (!Collections.disjoint(beta1LastSet, beta2FirstSet) && childList.get(i).getNullable() == 1)
                                    || (!Collections.disjoint(beta2LastSet, beta1FirstSet) && childList.get(childList.size() - 1).getNullable() == 1)
                            ) {
                                isSatisfy = true;
                            }
                            if (!isSatisfy) continue;



                            String prefix1 = getPrefixString(standardizedReDoSTree, node, true); // 获取的是前缀正则+中缀正则的生成串
                            String prefix2 = getPrefixString(standardizedReDoSTree, node, false);
                            String prefix3 = treeNodeCopy.getMatchStr(treeNodeCopy.getChildByChainIndexString(node.getChainIndex()));

                            Set<String> prefixSet = new HashSet<>();
                            if (prefix1 != null) prefixSet.add(prefix1);
                            if (prefix2 != null) prefixSet.add(prefix2);
                            if (prefix3 != null) prefixSet.add(prefix3);
                            if (prefixSet.isEmpty()) continue;

                            // β1可空或β2可空的情况
                            Set<String> thirdConditionInfixSet = new HashSet<>();
                            String thirdConditionInfix1 = judgeThirdCondition(beta1.toString(), beta2.toString(), nodeCounting, beta1Nullable, beta2Nullable,false);
                            String thirdConditionInfix2 = judgeThirdCondition(beta1.toString(), beta2.toString(), nodeCounting, beta1Nullable, beta2Nullable,true);
                            if (!thirdConditionInfix1.equals("")) thirdConditionInfixSet.add(thirdConditionInfix1);
                            if (!thirdConditionInfix2.equals("")) thirdConditionInfixSet.add(thirdConditionInfix2);
                            if (!thirdConditionInfixSet.isEmpty()) {
                                Set<String> suffixSet = new HashSet<>();
                                suffixSet.add(treeNodeCopy.getNonMatchStr());
                                suffixSet.add(specialSuffixStringWithAllCountingNodes);
                                suffixSet.add(specialSuffixStringWithCountingAndEndLine);
                                suffixSet.addAll(specialSuffixStringWithNegativeAssertNode);
//                                specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, true);
//                                if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);
//                                specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, false);
//                                if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);

                                for (String infix: thirdConditionInfixSet) {
                                    suffixSet.addAll(getSuffixString(standardizedReDoSTree, node, infix));
                                }
                                if (suffixSet.isEmpty()) continue;

                                for (String suffix : suffixSet) {
                                    for (String prefix : prefixSet) {
                                        for (String thirdConditionInfix : thirdConditionInfixSet) {
                                            AttackBean attackBean = new AttackBean();
                                            attackBean.setPrefix(prefix);
                                            attackBean.setInfix(thirdConditionInfix);
                                            attackBean.setSuffix(suffix);
                                            attackBean.initType(AttackType.EXPONENT);
                                            if (!Collections.disjoint(beta1FollowLastSet, beta2FirstSet) || (!Collections.disjoint(beta1LastSet, beta2FirstSet) )) {
                                                attackBean.setPatternType(PatternType.EOA_i2);
                                            } else if (!Collections.disjoint(beta1FirstSet, beta2FollowLastSet) || !Collections.disjoint(beta2LastSet, beta1FirstSet)) {
                                                attackBean.setPatternType(PatternType.EOA_ii2);
                                            }
//                                            attackBean.setPatternType(PatternType.EOA_iii);
                                            AttackBean.conflictPointHelper conflictPointHelper = new AttackBean.conflictPointHelper();
                                            conflictPointHelper.setReDoSTree(sourceReDoSTree);
                                            conflictPointHelper.setOuterConflictPoint(specialParent.getInitialChainIndex());
                                            List<Pair<String, Boolean>> list = new LinkedList<>();
                                            list.add(new Pair<>(beta1.toString(), true));
                                            list.add(new Pair<>(beta2.toString(), true));
                                            conflictPointHelper.setSplicedInnerConflictPointList(list);
                                            conflictPointHelper.setInnerConflictPointList(Collections.singletonList(node.getInitialChainIndex()));
                                            attackBean.setConflictPoint(conflictPointHelper);
                                            attackBeanList.add(attackBean);
                                        }
                                    }
                                }
                            }

                            // β1不可空且β2不可空的情况
                            Set<String> fourthConditionSet = new HashSet<>();
                            String fourthConditionInfix1 = judgeFourthCondition(beta1.toString(), beta2.toString(), beta1Nullable, beta2Nullable, false);
                            String fourthConditionInfix2 = judgeFourthCondition(beta1.toString(), beta2.toString(), beta1Nullable, beta2Nullable, true);
                            if (!fourthConditionInfix1.equals("")) fourthConditionSet.add(fourthConditionInfix1);
                            if (!fourthConditionInfix2.equals("")) fourthConditionSet.add(fourthConditionInfix2);
                            if (!fourthConditionSet.isEmpty()) {
                                Set<String> suffixSet = new HashSet<>();
                                suffixSet.add(treeNodeCopy.getNonMatchStr());
                                suffixSet.add(specialSuffixStringWithAllCountingNodes);
                                suffixSet.add(specialSuffixStringWithCountingAndEndLine);
                                suffixSet.addAll(specialSuffixStringWithNegativeAssertNode);
//                                specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, true);
//                                if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);
//                                specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(specialParentR4Tree, false);
//                                if (specialSuffixStringWithCountingAndEndLine != null) suffixSet.add(specialSuffixStringWithCountingAndEndLine);

                                for (String infix: thirdConditionInfixSet) {
                                    suffixSet.addAll(getSuffixString(standardizedReDoSTree, node, infix));
                                }
                                if (suffixSet.isEmpty()) continue;

                                for (String suffix : suffixSet) {
                                    for (String prefix : prefixSet) {
                                        for (String fourthConditionInfix : fourthConditionSet) {
                                            AttackBean attackBean = new AttackBean();
                                            attackBean.setPrefix(prefix);
                                            attackBean.setInfix(fourthConditionInfix);
                                            attackBean.setSuffix(suffix);
                                            attackBean.initType(AttackType.EXPONENT);
                                            if (!Collections.disjoint(beta1FollowLastSet, beta2FirstSet) || (!Collections.disjoint(beta1LastSet, beta2FirstSet) )) {
                                                attackBean.setPatternType(PatternType.EOA_i3);
                                            } else if (!Collections.disjoint(beta1FirstSet, beta2FollowLastSet) || !Collections.disjoint(beta2LastSet, beta1FirstSet)) {
                                                attackBean.setPatternType(PatternType.EOA_ii3);
                                            }
//                                            attackBean.setPatternType(PatternType.EOA_iv);
                                            AttackBean.conflictPointHelper conflictPointHelper = new AttackBean.conflictPointHelper();
                                            conflictPointHelper.setReDoSTree(sourceReDoSTree);
                                            conflictPointHelper.setOuterConflictPoint(specialParent.getInitialChainIndex());
                                            List<Pair<String, Boolean>> list = new LinkedList<>();
                                            list.add(new Pair<>(beta1.toString(), true));
                                            list.add(new Pair<>(beta2.toString(), true));
                                            conflictPointHelper.setSplicedInnerConflictPointList(list);
                                            conflictPointHelper.setInnerConflictPointList(Collections.singletonList(node.getInitialChainIndex()));
                                            attackBean.setConflictPoint(conflictPointHelper);
                                            attackBeanList.add(attackBean);
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
//            System.out.println(regex);
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


    public static ReDoSBean getEOAReDoSBean(String regex, String language) {
        ReDoSBean bean = new ReDoSBean();
        try {
//            TreeNode tree = getReDoSTree(regex, language);
//            bean = getEOAReDoSBeanHelper(tree, regex);
            TreeNode sourceReDoSTree = getReDoSTree(regex, language);
            TreeNode standardizedReDoSTree = getStandardizedReDoSTree(sourceReDoSTree, language);
            bean = getEOAReDoSBeanHelper(sourceReDoSTree, standardizedReDoSTree, regex);
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
        regex = "(a*b?ab+)+";
        regex = "@Test\\s*\\(([^@\\n]*)\\)\\s+(@\\w+\\s*\\([^@\\n]*\\s*)*@CitrusTest\\(([^@\\n]*)\\)((?!(public|private|protected)).*\\s*)+(public|private|protected)\\s+((?!((description|}))).*\\s*)+(description\\s*\\(\\s*\\\"\\s*(.*)\\s*\\\"\\s*\\))?";
//        regex = "(ab*b{4,}b*c)+";
//        regex = "(a+b+a+)+";
        regex = "^(.+)\\s(\\/.*)\\?(.+=.+)+\\s(.+)\\/(\\d\\.\\d)$\\n(^(.+):\\s(.*)$\\n)*";
        regex = "^(?<azurePrefix>https:\\/\\/%%BLOBCONTAINER%%.blob.core.windows.net)\\/(?<containerId>\\S+)\\/(?<inDir>in)\\/(?<path>(\\S+\\/)*?)(?<filename>[^<>:\"/\\\\\\|\\?\\*]+(\\.[^<>:\"/\\\\\\|\\?\\*]+)*)$";
        regex = "(https:\\/\\/%%BLOBCONTAINER%%.blob.core.windows.net)\\/([\"%:<>*./?\\\\|!\\w]+)\\/(in)\\/(([\"%:<>*./?\\\\|!\\w]+\\/)*)([%.v!\\w\\s]+(\\.[%.v!\\w\\s]+)*)";
        regex = "(LEFT JOIN|JOIN) `([\\w\\{\\}$->]+)` (\\w+) ON (\\w+)\\.(\\w.+) = (\\w+)\\.(\\w.+)($)";
        regex = "(?i-mx:[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?!json|html|xml)(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*)|(?i-mx:[a-z0-9\\-]*)";
        regex = ">([^<]+)[^[]+\\[([^\\]]+)]\\[([^\\]]+)]";
        regex = "[\",:<>\\-/v_!B-Za-z\\d\\s]*(?:ASM:|A.*ASM:).*\\s+ASM:\"([,:<>\\-/v!\\w\\s]+)\",\"(\\d+\\-\\d+\\-\\d+\\s+\\d+:\\d+:\\d+)\",\"(\\w+)\",\"([,:<>\\-/v!\\w\\s]*)\",\"([,:<>\\-/v!\\w\\s]*)\",\"(\\d+)\",\"([,:<>\\-/v!\\w\\s]+)\",\"([,:<>\\-/v!\\w\\s]+)\",\"([,:<>\\-/v!\\w\\s]+)\",\"([,:<>\\-/v!\\w\\s]+)\",\"([,:<>\\-/v!\\w\\s]+)\",\"([,:<>\\-/v!\\w\\s]+)\",\"([,:<>\\-/v!\\w\\s]+)\"(?:,\"([,:<>\\-/v!\\w\\s]+)\",\"(\\d+)\",\"([,:<>\\-/v!\\w\\s]+)\",\"([,:<>\\-/v!\\w\\s]+)\",\"([,:<>\\-/v!\\w\\s]*)\",\"([,:<>\\-/v!\\w\\s]+)\"(?:,\"([,:<>\\-/v!\\w\\s]+(<viol_name>.*<\\/viol_name>)+.*(<context>.*<\\/context>)*.*(<param_name_pattern>.*<\\/param_name_pattern>)*[,:<>\\-/v!\\w\\s]+))?)?";
        regex = "(\\n|\\r|\\t|\\0|^\\s+|\\s+$|[\\b])";
        regex = "^(((((((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?([A-Za-z0-9!#-'*+\\/=?^_`{|}~-])+((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?)|(((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?\"((\\s? +)?(([!#-[\\]-~])|(\\\\([ -~]|\\s))))*(\\s? +)?\"))?)?(((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?<(((((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?(([A-Za-z0-9!#-'*+\\/=?^_`{|}~-])+(\\.([A-Za-z0-9!#-'*+\\/=?^_`{|}~-])+)*)((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?)|(((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?\"((\\s? +)?(([!#-[\\]-~])|(\\\\([ -~]|\\s))))*(\\s? +)?\"))@((((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?(([A-Za-z0-9!#-'*+\\/=?^_`{|}~-])+(\\.([A-Za-z0-9!#-'*+\\/=?^_`{|}~-])+)*)((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?)|(((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?\\[((\\s? +)?([!-Z^-~]))*(\\s? +)?\\]((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?)))>((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?))|(((((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?(([A-Za-z0-9!#-'*+\\/=?^_`{|}~-])+(\\.([A-Za-z0-9!#-'*+\\/=?^_`{|}~-])+)*)((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?)|(((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?\"((\\s? +)?(([!#-[\\]-~])|(\\\\([ -~]|\\s))))*(\\s? +)?\"))@((((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?(([A-Za-z0-9!#-'*+\\/=?^_`{|}~-])+(\\.([A-Za-z0-9!#-'*+\\/=?^_`{|}~-])+)*)((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?)|(((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?\\[((\\s? +)?([!-Z^-~]))*(\\s? +)?\\]((((\\s? +)?(\\(((\\s? +)?(([!-'*-[\\]-~]*)|(\\\\([ -~]|\\s))))*(\\s? +)?\\)))(\\s? +)?)|(\\s? +))?))))$";
        regex = "\\b((((https?:\\/\\/)?([-a-zA-Z0-9@:%_\\+~#=]\\.?){2,256}(?<!\\.)\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+~#?&=//]\\.?)*)?(/?ark:/))|(info:ark/))(([-a-zA-Z0-9@:%_\\+~#?&=//]\\.?)+)(?<!\\.)";
        regex = "^([a-zA-Z0-9]+)(((\\.|#)([^ (]*))+)?\\(([^)]*)\\)(\\[([^\\]]*)\\])?";
        regex = "(a*a(a*|bc)bc)*d";
        regex = "(abc.+)+";
        regex = "((a*a*))*";
        regex = "(a*b?ab+)+";
        regex = "(c?(a+b+)a+c?)+";
        regex = "(ab?b?)+";
        regex = "(ab?b?)+";
        regex = "((((a+ea+)b?)c?)d?)+";
        regex = "^((((ab|ab)c)d)e)+$";

        regex = "^(((a*a*)b)c)+$";
//        regex = "^((a+b+a+)a)+$";
//        regex = "^(((a+e+a+)b?)c?)+$";
//        regex = "^(((aa{1,2})b?)c?)+$";
//        regex = "(a*b?ab+)+";
//        regex = "Endkunde:\\n\\s{0,2}(^|)(Herr|Frau)\\s([A-Z][a-z]*)\\s([A-Z][a-z]*)*\\n\\s{0,2}Telefonnummer:\\s((?:0{2}|\\+)[0-9]{2,3} [1-9][0-9]* [0-9]+$)*\\n\\s{0,2}Mobiltelefonnummer:\\s((?:0{2}|\\+)[0-9]{2,3} [1-9][0-9]* [0-9]+$)";
//        regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[&-+!*$@%_])([&-+!*$@%_\\w]{8,15})$";
//        regex = "((25[0-5]|2[0-4][0-9]|[01]?[0-9]{1,2})(\\.(?=[0-9])|\\b)){4}";
//        regex = "(?<=X-Filename:[a-zA-Z\\s0-9.*+-_/]*\\n\\n)([^\\n\\r]*)";
//        regex = "(\\d)(?<!(?=\\1)..)(?=(\\1*)(?!\\1).*(?!\\1).\\1\\2(?!\\1))(?!(?:(?=\\2((\\3?+)(\\d)(\\5*)))){1,592}?(?=\\2\\3.*(?!\\5).\\5\\6(?!\\5))(?:\\1(?=\\1*\\4\\5(\\7?+\\5)))*+(?!\\1))\\2";
//        regex = "([(-.+]*+[0-9]+[()-.+ ]*+[0-9]+[()-.+ ]*+[0-9]+[()-.+ ]*+[0-9]+[()-.+ ]*+[0-9]+[()-.+ ]*+[0-9]+[()-.+ ]*+[0-9]+[()-.+ ]*+[0-9]*+[()-.+ ]*+[0-9]*+[()-.+ ]*+[0-9]*+[()-.+ ]*+[0-9]*+)";
//        regex = "(^|(?<=\\s))(([A-Za-z\\d]+)([_\\.\\-])?([A-Za-z\\d]+))@([a-z]+([\\.\\-_][a-z]+)+)($|)";


//        regex = "^(((a+|a+)c)d)+$";

//        regex = "(d?ab+b+c?)+";
//        regex = "((\\s)*[a-zA-Z](\\s)*)*";
//        regex = "^(ab*b{0,1})+";
//        regex = "(d?(ab+)b+c?)+";
//        regex = "(c?(a+b+a+)c?)+";

//        regex = "^((a*|b*)d(a*|c*))+";
//        regex = "([\\w\\-()]+(\\s[\\w\\-()]+)*)+";
//        regex = "(str\\=)\\s*(?&lt;value&gt;([a-zA-Z0-9\\,\\.]{1})*)";
//        regex = "^([A-Za-z0-9]+[A-Za-z0-9-_]*\\.)*(([A-Za-z0-9]+[A-Za-z0-9-_]*){3,}\\.)+([A-Za-z0-9]{2,4}\\.?)+)$";
        regex = "(ac[abc]+ca)*";
        regex = "(((?P<py>python)|(?P<fr>fakeroot))\\s*)*(?P<func>[\\w\\.\\-\\+\\{\\}\\$]+)?\\s*\\(\\s*\\)\\s*{$";
        regex = "(.*?)(?:@2x|~iphone|~ipad)*\\.png";
        regex = "(?P<rtn>\\S+)\\s+(?P<fname>[^\\s\\(\\){}]+(^{}]*})?)\\s*\\((?P<args>(({[^{}]*})+|(\\([^\\(\\)]*\\))+|[^\\(\\)]+)*)\\)";
        regex = "<([\\w\\:\\-]+)((?:\\s+[\\w\\-:]+(?:\\s*=\\s*(?:(?:\"[^\"]*\")|(?:\\\\'[^\\\\']*\\\\')|[^>\\s]+))?)*)\\s*(\\/?)>";
//        regex = "(a+ba+)+c";
        regex = "(ab[abc]+ab)+d";
//        regex = "(a?a?)*b";
        regex = "^(?=^.{1,254}$)(^(?:(?!\\.|-)([a-z0-9\\-\\*]{1,63}|([a-z0-9\\-]{1,62}[a-z0-9]))\\.)+(?:[a-z]{2,})$)$";
        regex = "^(([a-zA-Z]:)|(\\\\{2}\\w+)\\$?)(\\\\(\\w[\\w].*))+(.pdf)$";
        regex = "^(a(\\w[\\w].*))+(.pdf)$";
        regex = "^((a+b)+a(a+b)+)+$";
        regex = "(c[ab]+a[ab]+d)+";
        regex = "^((a+b)+a(a+b)+)+$";
        regex = "^((ab+)+b(ab+)+)+$";
        regex = "^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
        regex = "^[a]+@[a](?:[a]{0,61}[a])?(?:\\.[a](?:[a]{0,61}[a])?)*$";
        regex = "^(b(a?a)c)+$";
        regex = "^([01]?[0-9]\\.[01]?[0-9]\\.[01]?[0-9]\\.[01]?[0-9][0-9]?[,;])*$";
        regex = "&lt;/?(\\w+)(\\s*\\w*\\s*=\\s*(&quot;[^&quot;]*&quot;|'[^']'|[^&gt;]*))*|/?&gt;";
        regex = "((?:[^.]|.(?=[^.]*\\.))*)";    // 这是个EOD
        regex = "((?:.*?(?:<[ \\r\\t]*tag[^>]*>?.*?(?:<.*?/.*?tag.*?>)?)*)*)(<[^>]*?/[^>]*?tag[^>]*?>)";
        regex = "(?<!\\\\)\\[(\\\\\\[|\\\\\\]|[^\\[\\]]|(?<!\\\\)\\[.*(?<!\\\\)\\])*(?<!\\\\)\\]";
        regex = "\\svalues\\s*(\\(((?<!\\\\)'[^\\)]*?\\)[^\\)]*(?<!\\\\)?'|[^\\(\\)]|(?:\\([^\\)]*\\)))+\\))";
        regex = "((?<!\\\\)'[^\\)]*?\\)[^\\)]*(?<!\\\\)?')+";
        regex = "(\\(('[^\\)]*?\\)[^\\)]*'|[^\\(\\)]|(?:\\([^\\)]*\\)))+\\))";
        regex = "(\\(('[^\\)]*?\\)[^\\)]*'|[^\\(\\)])+\\))";
        regex = "\\svalues\\s*(\\(('[^\\)]*?\\)[^\\)]*'|[^\\(\\)]|(?:\\([^\\)]*\\)))+\\))";
        regex = "\\svalues\\s*(\\(((?<!\\\\)'[^\\)]*?\\)[^\\)]*(?<!\\\\)?'|[^\\(\\)]|(?:\\([^\\)]*\\)))+\\))";
        regex = "(?i)((pop\\=\\w+)+.*(aid\\=\\w+)+.*(sid\\=\\w+)+.*(key\\=\\w+))";
        regex = "(?<pgnGame>\\s*(?:\\[\\s*(?<tagName>\\w+)\\s*\"(?<tagValue>[^\"]*)\"\\s*\\]\\s*)+(?:(?<moveNumber>\\d+)(?<moveMarker>\\.|\\.{3})\\s*(?<moveValue>(?:[PNBRQK]?[a-h]?[1-8]?x?[a-h][1-8](?:\\=[PNBRQK])?|O(-?O){1,2})[\\+#]?(\\s*[\\!\\?]+)?)(?:\\s*(?<moveValue2>(?:[PNBRQK]?[a-h]?[1-8]?x?[a-h][1-8](?:\\=[PNBRQK])?|O(-?O){1,2})[\\+#]?(\\s*[\\!\\?]+)?))?\\s*(?:\\(\\s*(?<variation>(?:(?<varMoveNumber>\\d+)(?<varMoveMarker>\\.|\\.{3})\\s*(?<varMoveValue>(?:[PNBRQK]?[a-h]?[1-8]?x?[a-h][1-8](?:\\=[PNBRQK])?|O(-?O){1,2})[\\+#]?(\\s*[\\!\\?]+)?)(?:\\s*(?<varMoveValue2>(?:[PNBRQK]?[a-h]?[1-8]?x?[a-h][1-8](?:\\=[PNBRQK])?|O(-?O){1,2})[\\+#]?(\\s*[\\!\\?]+)?))?\\s*(?:\\((?<varVariation>.*)\\)\\s*)?(?:\\{(?<varComment>[^\\}]*?)\\}\\s*)?)*)\\s*\\)\\s*)*(?:\\{(?<comment>[^\\}]*?)\\}\\s*)?)*(?<endMarker>1\\-?0|0\\-?1|1/2\\-?1/2|\\*)?\\s*)";
        regex = "\\b(((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])(\\b|\\.)){4}";
        regex = "^((((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)))\\s*([,;]|$)+\\s*)*$";
        regex = "^(((([01]?[0-9][0-9]?)\\.([01]?[0-9][0-9]?)\\.([01]?[0-9][0-9]?)\\.([01]?[0-9][0-9]?)))\\s*([,;]|$)+\\s*)*$";
        regex = "^(((([01]?[0-9][0-9]?)\\.([01]?[0-9][0-9]?)))\\s*([,;]|$)+\\s*)*$";
        regex = "^(\\s|\\/\\*.*?\\*\\/)*[\\[\\(\\w]";
        regex = "(^|[^.])<\\/?\\w+(?:\\s+[^\\s>\\/=]+=(\\\"|')(?:\\\\\\1|\\\\?(?!\\1)[\\s\\S])*\\2)*\\s*\\/?";  // -</a x="\\\\\\\\\\\\\\\\\\\\\\\\\
//        regex = "^((?:.\\d*[0-9])?)*$";
        regex = "(?:\\b\\w*(\\w\\w?)\\1{2,}\\w*\\b)";
        TreeNode ReDoSTree = getReDoSTree(regex, "java");
//        printTree(ReDoSTree);
//        System.out.println(ReDoSTree.getChild(1).getData());
//        System.out.println(getR0(ReDoSTree, ReDoSTree.getChild(1)));
//        System.out.println(getOrSymbolTreeNode(ReDoSTree.getChild(0)));
        ReDoSBean reDosBean = getEOAReDoSBean(regex, "java");
        ArrayList<AttackBean> attackBeanList = reDosBean.getAttackBeanList();
        for (int i = 0; i < attackBeanList.size(); i++) {
            System.out.println(attackBeanList.get(i).getAttackStringFormat());
        }
    }
}
