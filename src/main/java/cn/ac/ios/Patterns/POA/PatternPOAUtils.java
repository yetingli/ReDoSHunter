package cn.ac.ios.Patterns.POA;

import cn.ac.ios.Bean.*;
import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Utils.Constant;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

import static cn.ac.ios.TreeNode.Utils.*;
import static cn.ac.ios.Utils.BracketUtils.*;
import static cn.ac.ios.Utils.DkBricsAutomatonUtils.*;
import static cn.ac.ios.Utils.FlagsUtils.*;
import static cn.ac.ios.Utils.GenMatchStringUtils.getExampleByDkBricsAutomaton2;
import static cn.ac.ios.Utils.GenMatchStringUtils.getTranslateRegexForAssertionsList;
import static cn.ac.ios.Utils.NegateUtils.*;
import static cn.ac.ios.Utils.NegateUtils.deleteZeroWidthAssertion;
import static cn.ac.ios.Utils.RegexUtils.*;
import static cn.ac.ios.Utils.SpecialStringUtils.*;
import static cn.ac.ios.Utils.SplitRegexUtils.*;

public class PatternPOAUtils {
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
//        // 减少计数 {1024} -> {10}, {1024,} -> {10,} {1024,2048} -> {10,11} for snort 尝试
//        ReDoSTree.reduceQuantifier();
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
////        refactorAssertPattern(ReDoSTree);
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
////        regex = rewriteEmptyString(ReDoSTree.getData());
////
////        if (!regex.equals(ReDoSTree.getData())) {
////            ReDoSTree = createReDoSTree(regex);
////        }
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

    private static String generateInfixStringForPOA(TreeNode root, TreeNode parent, TreeNode child, boolean considerZeroWidthAssertions) throws InterruptedException {
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


    // 生成中缀串forPOA
    private static String getInfixForPOA(TreeNode root, TreeNode parent, TreeNode child, boolean considerZeroWidthAssertions) throws InterruptedException {
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

    private static void getTranslateRegexForAssertionsListHelper(TreeNode node) throws InterruptedException {
        // 使用重写后的去首尾^$
        node.deleteCaretAndDollarSymbols();
        // 新版重写空串
        node = removeBlankStr(node);
        // 重写反向引用后 删除NonCapturingGroupFlag ?:
        node.deleteNonCapturingGroupFlag();
    }

    private static Set<String> getInfixForPOA(String regex1, String regex2, String regex3, boolean considerZeroWidthAssertions) throws InterruptedException {
        if (considerZeroWidthAssertions) {
            Set<String> infixSet = new HashSet<>();

            // regex1, regex1 + regex2 + regex3, regex3
            // regex1 + regex3, regex1 + regex2 + regex3
            TreeNode regex1Tree = createReDoSTree(regex1);
            TreeNode regex1Regex2Regex3Tree = createReDoSTree("(" + regex1 + ")(" + regex2 + ")(" + regex3 + ")");
            TreeNode regex3Tree = createReDoSTree(regex3);
            TreeNode regex1Regex3Tree = createReDoSTree("(" + regex1 + ")(" + regex3 + ")");

            getTranslateRegexForAssertionsListHelper(regex1Tree);
            getTranslateRegexForAssertionsListHelper(regex1Regex2Regex3Tree);
            getTranslateRegexForAssertionsListHelper(regex3Tree);
            getTranslateRegexForAssertionsListHelper(regex1Regex3Tree);

            List<String> regex1Trans = getTranslateRegexForAssertionsList(regex1Tree);
            List<String> regex1Regex2Regex3Trans = getTranslateRegexForAssertionsList(regex1Regex2Regex3Tree);
            List<String> regex3Trans = getTranslateRegexForAssertionsList(regex3Tree);
            List<String> regex1Regex3Trans = getTranslateRegexForAssertionsList(regex1Regex3Tree);

            List<String> regexList1 = new ArrayList<>();
            regexList1.addAll(regex1Trans);
            regexList1.addAll(regex1Regex2Regex3Trans);
            regexList1.addAll(regex3Trans);

            String example = getExampleByDkBricsAutomaton2(regexList1, 0);
            if (example != null && !example.equals("")) infixSet.add(example);

            regexList1 = new ArrayList<>();
            regexList1.addAll(regex1Regex3Trans);
            regexList1.addAll(regex1Regex2Regex3Trans);
            example = getExampleByDkBricsAutomaton2(regexList1, 0);
            if (example != null && !example.equals("")) infixSet.add(example);

            return infixSet;
        } else {
            regex1 = getRegexWithoutZeroWidthAssertion(regex1);
            regex2 = getRegexWithoutZeroWidthAssertion(regex2);
            regex3 = getRegexWithoutZeroWidthAssertion(regex3);

            Set<String> infixSet = new HashSet<>();

            List<String> regexList1 = new ArrayList<>();
            regexList1.add(regex1);
            regexList1.add("("+ regex1 + ")(" + regex2 + ")(" + regex3 + ")");
            regexList1.add(regex3);
            String example = getExampleByDkBricsAutomaton(regexList1);
            if (example != null && !example.equals("")) infixSet.add(example);

            regexList1 = new ArrayList<>();
            regexList1.add("(" + regex1 + ")(" + regex3 + ")");
            regexList1.add("(" + regex1 + ")(" + regex2 + ")(" + regex3 + ")");
            example = getExampleByDkBricsAutomaton(regexList1);
            if (example != null && !example.equals("")) infixSet.add(example);

            return infixSet;
        }

    }

    // 生成中缀串forPOA
    private static Set<String> generateInfixStringForPOA(String regex1, String regex2, String regex3) throws InterruptedException {
//        System.out.println("regex1 = " + regex1 + " regex2 = " + regex2 + " regex3 = " + regex3);

        Set<String> infixSet = new HashSet<>();

        List<String> regexList1 = new ArrayList<>();
        regexList1.add(regex1);
        regexList1.add(regex1 + regex2 + regex3);
        regexList1.add(regex3);
        String example = getExampleByDkBricsAutomaton(regexList1);
        if (example != null) infixSet.add(example);

        regexList1 = new ArrayList<>();
        regexList1.add(regex1 + regex3);
        regexList1.add(regex1 + regex2 + regex3);
        example = getExampleByDkBricsAutomaton(regexList1);
        if (example != null) infixSet.add(example);

        return infixSet;
    }

    // 判断treeNode的父节点们的counting值都＜1
    private static boolean isAllParentCountingLessOrEqualToOne(TreeNode treeNode) {
        TreeNode parent = treeNode.getParent();
        while (parent != null) {
            if (isGeneralizedCountingNodeWithMaxNumGreaterThanOne(parent)) return false;
            parent = parent.getParent();
        }
        return true;
    }


    // warning: 这个方法判断有问题 不要使用
    // 例如 (\w*a\w*c)*是个POA  其中r1 = 第一个\w*    r3 = 第二个\w*    r4 = c
    // 按照下面的判断方法会判断为不是POA
    //
    // 判断是POA还是EOA 对于(a*aa*c)*就是POA 对于(a*aa*c?)*就是EOA
    // 判断方法是 r4 ∩ r1 = ∅ 且 r4 ∩ r3 = ∅ 就是POA
    // 其中 r4 = c/c?     r1 = 第一个a*     r3 = 第二个a*
    @Deprecated
    private static boolean isPOAnotEOA(String r1, String r3, String r4) throws InterruptedException {
        System.out.println("r1 = " + r1 + " r3 = " + r3 + " r4 = " + r4);

        if (r4.equals("")) return false;


        List<String> regexList1 = new ArrayList<>();
        regexList1.add(r4);
        regexList1.add(r1);
        String example = getExampleByDkBricsAutomaton(regexList1, null, 0);
        if (example != null) {
            return false;
        }

        regexList1.remove(r1);
        regexList1.add(r3);
        example = getExampleByDkBricsAutomaton(regexList1, null, 0);
        if (example != null) {
            return false;
        }

        return true;

//        String regex = reWriteMetaEscape("(" + r4 + ")＆(" + r1 + ")");
//        regex = reductSpecailStringForDkBricsAutomaton(regex);
//        RegExp regExp = new RegExp(regex);
//        Automaton automaton = regExp.toAutomaton(false);    // 这里要加第二个参数minimize: false 这样就是nfa了 比dfa快
//        if (! automaton.isEmpty()) return false;
//
//        regex = reWriteMetaEscape("(" + r4 + ")＆(" + r3 + ")");
//        regex = reductSpecailStringForDkBricsAutomaton(regex);
//        regExp = new RegExp(regex);
//        automaton = regExp.toAutomaton(false);    // 这里要加第二个参数minimize: false 这样就是nfa了 比dfa快
//        if (! automaton.isEmpty()) return false;
//
//        return true;
    }

    private static ReDoSBean getPOAReDoSBeanHelper(TreeNode sourceReDoSTree, TreeNode standardizedReDoSTree, String regex) {
        ReDoSBean bean = new ReDoSBean();
        ArrayList<AttackBean> attackBeanList = new ArrayList<>();
        try {
//        List<TreeNode> allPossibleChildren = root.getAllPossibleChildren();
            List<TreeNode> allPossibleChildren = standardizedReDoSTree.getAllGeneralizedCountingWithMaxNumLeqOneNode();
            Set<String> rootAlphabet = standardizedReDoSTree.getLetterSet(true, true);
            // 这里临时改一下
            TreeNode treeNodeCopy = SerializationUtils.clone(standardizedReDoSTree);    // 深拷贝
            // 去补
            removeNegateSymbol(treeNodeCopy, Constant.SimplyLevel.HIGH);

            // 获取特殊后缀 正则中所有的counting结点取反
            String specialSuffixStringWithAllCountingNodes = getSpecialSuffixStringWithAllCountingNodes(standardizedReDoSTree);
            // 获取特殊后缀 通过(?<! (?!不接受
            List<String> specialSuffixStringWithNegativeAssertNode = getSpecialSuffixStringWithNegativeAssertNode(standardizedReDoSTree);

            for (int i = 0; i < allPossibleChildren.size(); i++) {
                for (int j = i + 1; j < allPossibleChildren.size(); j++) {
                    TreeNode child1 = allPossibleChildren.get(i);
                    TreeNode child2 = allPossibleChildren.get(j);

                    if (child1.isNowNodeChildOrGrandchild(child2) || child2.isNowNodeChildOrGrandchild(child1))
                        continue;
                    // 获取最近的爹
                    TreeNode nearestParent = getNearestParent(child1, child2);
                    // 最近的爹如果不是连接结点 则不是EOA
                    if (!(!isOrNode(nearestParent) && !isInBrackets(nearestParent) && !isInNegateNode(nearestParent) && !nearestParent.isLeaf() && !isGeneralizedCountingNode(nearestParent) && nearestParent.getChildCount() >= 2)) {    // 不是是连接结点
                        continue;
                    }

                    String r4 = getR4(standardizedReDoSTree, child2);    // 特殊后缀构造
                    TreeNode r4Tree = createReDoSTree(r4);
                    if (isCanEmptyNode(r4Tree)) {
                        r4 = "(" + child2.getData() + ")(" + r4 + ")";
                        r4Tree = createReDoSTree(r4);
                    }
                    String specialSuffixStringWithCountingAndEndLine = getSpecialSuffixStringWithCountingAndEndLine(r4Tree);
//                    String specialSuffix1 = getSpecialSuffixStringWithCountingAndEndLine(r4Tree, true);
//                    String specialSuffix2 = getSpecialSuffixStringWithCountingAndEndLine(r4Tree, false);


                    String r2 = getR2(nearestParent, child1, child2);

//                System.out.println(nearestParent.getData() + "\t" + child1.getData() + "\t" + child2.getData() + "\t" + r2);

                    // 看一下 beta1.followLast ∩ beta2.first 是否为空集
                    // 其中 beta1 = child1.getData(), beta2 = r2 + child2.getData()
                    String beta1 = child1.getData();
                    String beta2 = r2 + child2.getData();
                    // 判断是否满足条件 beta1.followLast ∩ beta2.first ≠ ∅
                    TreeNode beta1Tree = child1;
                    TreeNode beta2Tree = createReDoSTree(beta2);
                    beta2Tree.calculateFiveAttributesNullableAndFirstAndLastAndFlexibleAndFollowLast(rootAlphabet);
                    if (!Collections.disjoint(beta1Tree.getFollowLast(), beta2Tree.getFirst())) {
 //                    System.out.println("child1.getData() = " + child1.getData());
//                    System.out.println("beta2Tree.getData() = " + beta2Tree.getData());
                        StringBuilder beta1StringBuilder = new StringBuilder(beta1);
                        Set<String> infixSet = new HashSet<>();
//                        infixSet.addAll(generateInfixStringForPOA(beta1StringBuilder.toString(), r2, child2.getData()));
                        Set<String> infixSet1 = getInfixForPOA(beta1StringBuilder.toString(), r2, child2.getData(), false);
                        Set<String> infixSet2 = getInfixForPOA(beta1StringBuilder.toString(), r2, child2.getData(), true);
                        if (!infixSet1.isEmpty()) infixSet.addAll(infixSet1);
                        if (!infixSet2.isEmpty()) infixSet.addAll(infixSet2);

                        if (!infixSet.isEmpty()) {

                            String prefix1 = getPrefixString(standardizedReDoSTree, child1, true); // 获取的是前缀正则+中缀正则的生成串
                            String prefix2 = getPrefixString(standardizedReDoSTree, child1, false);
                            String prefix3 = treeNodeCopy.getMatchStr(treeNodeCopy.getChildByChainIndexString(child1.getChainIndex()));

                            Set<String> prefixSet = new HashSet<>();
                            if (prefix1 != null) prefixSet.add(prefix1);
                            if (prefix2 != null) prefixSet.add(prefix2);
                            if (prefix3 != null) prefixSet.add(prefix3);
                            if (prefixSet.isEmpty()) continue;

                            Set<String> suffixSet = new HashSet<>();
                            suffixSet.add(treeNodeCopy.getNonMatchStr());
                            suffixSet.add(specialSuffixStringWithAllCountingNodes);
                            suffixSet.add(specialSuffixStringWithCountingAndEndLine);
                            suffixSet.addAll(specialSuffixStringWithNegativeAssertNode);
//                            if (specialSuffix1 != null) suffixSet.add(specialSuffix1);
//                            if (specialSuffix2 != null) suffixSet.add(specialSuffix2);
                            for (String infix : infixSet) {
                                suffixSet.addAll(getSuffixString(standardizedReDoSTree, child2, infix));
                            }

                            for (String suffix : suffixSet) {
                                for (String prefix : prefixSet) {
                                    for (String infix : infixSet) {
                                        AttackBean attackBean = new AttackBean();
                                        attackBean.setPrefix(prefix);
                                        attackBean.setInfix(infix);
                                        attackBean.setSuffix(suffix);
                                        attackBean.initType(AttackType.POLYNOMIAL);
                                        attackBean.setPatternType(PatternType.POA);
                                        AttackBean.conflictPointHelper conflictPointHelper = new AttackBean.conflictPointHelper();
                                        conflictPointHelper.setReDoSTree(sourceReDoSTree);
                                        List<Pair<String, Boolean>> splicedInnerConflictPointList = new LinkedList<>();
//                                        splicedInnerConflictPointList.add(new Pair<>(getR0(standardizedReDoSTree, child1), false));
                                        splicedInnerConflictPointList.add(new Pair<>(beta1, true));
                                        splicedInnerConflictPointList.add(new Pair<>(beta2, true));
//                                        splicedInnerConflictPointList.add(new Pair<>(getR4(standardizedReDoSTree, child2, false), false));
                                        conflictPointHelper.setSplicedInnerConflictPointList(splicedInnerConflictPointList);
                                        List<String> innerConflictPointList = new LinkedList<>();
                                        innerConflictPointList.add(child1.getInitialChainIndex());
                                        innerConflictPointList.addAll(getR2InitialChainIndexList(nearestParent, child1, child2));
                                        innerConflictPointList.add(child2.getInitialChainIndex());
                                        conflictPointHelper.setInnerConflictPointList(innerConflictPointList);
                                        attackBean.setConflictPoint(conflictPointHelper);
                                        attackBeanList.add(attackBean);
                                    }
                                }
                            }
                        }

                        // 向左扩充beta1
                        String r0 = getR0(standardizedReDoSTree, child1);
                        List<String> r0InitialChainIndexList = getR0InitialChainIndexList(standardizedReDoSTree, child1);
                        TreeNode r0Tree = createReDoSTree(r0);
                        r0Tree.calculateFiveAttributesNullableAndFirstAndLastAndFlexibleAndFollowLast(rootAlphabet);
                        List<TreeNode> childList = r0Tree.getChildList();
                        if (isGroupNode(r0Tree)) {
                            // 如果向左扩充的r0的followLast和beta1的followLast有交集 才扩充
                            if (Collections.disjoint(r0Tree.getFollowLast(), beta1Tree.getFollowLast())) continue;
                            beta1StringBuilder.insert(0, r0);
//                            infixSet = generateInfixStringForPOA(beta1StringBuilder.toString(), r2, child2.getData());
                            infixSet1 = getInfixForPOA(beta1StringBuilder.toString(), r2, child2.getData(), false);
                            infixSet2 = getInfixForPOA(beta1StringBuilder.toString(), r2, child2.getData(), true);
                            if (!infixSet1.isEmpty()) infixSet.addAll(infixSet1);
                            if (!infixSet2.isEmpty()) infixSet.addAll(infixSet2);

                            if (!infixSet.isEmpty()) {

                                Set<String> suffixSet = new HashSet<>();
                                suffixSet.add(treeNodeCopy.getNonMatchStr());
                                suffixSet.add(specialSuffixStringWithAllCountingNodes);
                                suffixSet.add(specialSuffixStringWithCountingAndEndLine);
                                suffixSet.addAll(specialSuffixStringWithNegativeAssertNode);
//                                if (specialSuffix1 != null) suffixSet.add(specialSuffix1);
//                                if (specialSuffix2 != null) suffixSet.add(specialSuffix2);
                                for (String infix : infixSet) {
                                    suffixSet.addAll(getSuffixString(standardizedReDoSTree, child2, infix));
                                }

                                for (String suffix : suffixSet) {
                                    for (String infix : infixSet) {
                                        AttackBean attackBean = new AttackBean();
                                        attackBean.setPrefix("");
                                        attackBean.setInfix(infix);
                                        attackBean.setSuffix(suffix);
                                        attackBean.initType(AttackType.POLYNOMIAL);
                                        attackBean.setPatternType(PatternType.POA);
                                        AttackBean.conflictPointHelper conflictPointHelper = new AttackBean.conflictPointHelper();
                                        conflictPointHelper.setReDoSTree(sourceReDoSTree);
                                        List<Pair<String, Boolean>> list = new LinkedList<>();
                                        list.add(new Pair<>(beta1StringBuilder.toString(), true));
                                        list.add(new Pair<>(beta2, true));
//                                        list.add(new Pair<>(getR4(standardizedReDoSTree, child2, false), false));
                                        conflictPointHelper.setSplicedInnerConflictPointList(list);
                                        List<String> innerConflictPointList = new LinkedList<>();
                                        innerConflictPointList.addAll(r0InitialChainIndexList);
                                        innerConflictPointList.add(child1.getInitialChainIndex());
                                        innerConflictPointList.addAll(getR2InitialChainIndexList(nearestParent, child1, child2));
                                        innerConflictPointList.add(child2.getInitialChainIndex());
                                        conflictPointHelper.setInnerConflictPointList(innerConflictPointList);
                                        attackBean.setConflictPoint(conflictPointHelper);
                                        attackBeanList.add(attackBean);
                                    }
                                }
                            }
                        } else {
                            for (int k = childList.size() - 1; k >= 0; k--) {
                                // 如果向左扩充的r0的followLast和beta1的followLast有交集 才扩充
                                if (Collections.disjoint(childList.get(k).getFollowLast(), beta1Tree.getFollowLast()))
                                    break;

                                beta1StringBuilder.insert(0, childList.get(k));
                                if (isQuantifierNode(childList.get(k))) continue;
//                                infixSet = generateInfixStringForPOA(beta1StringBuilder.toString(), r2, child2.getData());
                                infixSet1 = getInfixForPOA(beta1StringBuilder.toString(), r2, child2.getData(), false);
                                infixSet2 = getInfixForPOA(beta1StringBuilder.toString(), r2, child2.getData(), true);
                                if (!infixSet1.isEmpty()) infixSet.addAll(infixSet1);
                                if (!infixSet2.isEmpty()) infixSet.addAll(infixSet2);

                                if (!infixSet.isEmpty()) {

                                    String prefix1 = getPrefixString(r0Tree, childList.get(k), true); // 获取的是前缀正则+中缀正则的生成串
                                    String prefix2 = getPrefixString(r0Tree, childList.get(k), false);

                                    Set<String> prefixSet = new HashSet<>();
                                    if (prefix1 != null) prefixSet.add(prefix1);
                                    if (prefix2 != null) prefixSet.add(prefix2);
                                    if (prefixSet.isEmpty()) continue;

                                    Set<String> suffixSet = new HashSet<>();
                                    suffixSet.add(treeNodeCopy.getNonMatchStr());
                                    suffixSet.add(specialSuffixStringWithAllCountingNodes);
                                    suffixSet.add(specialSuffixStringWithCountingAndEndLine);
                                    suffixSet.addAll(specialSuffixStringWithNegativeAssertNode);
//                                    if (specialSuffix1 != null) suffixSet.add(specialSuffix1);
//                                    if (specialSuffix2 != null) suffixSet.add(specialSuffix2);
                                    for (String infix : infixSet) {
                                        suffixSet.addAll(getSuffixString(standardizedReDoSTree, child2, infix));
                                    }

                                    for (String suffix : suffixSet) {
                                        for (String prefix : prefixSet) {
                                            for (String infix : infixSet) {
                                                AttackBean attackBean = new AttackBean();
                                                attackBean.setPrefix(prefix);
                                                attackBean.setInfix(infix);
                                                attackBean.setSuffix(suffix);
                                                attackBean.initType(AttackType.POLYNOMIAL);
                                                attackBean.setPatternType(PatternType.POA);
                                                AttackBean.conflictPointHelper conflictPointHelper = new AttackBean.conflictPointHelper();
                                                conflictPointHelper.setReDoSTree(sourceReDoSTree);
                                                List<Pair<String, Boolean>> list = new LinkedList<>();
//                                                StringBuilder r0StringBuilder = new StringBuilder();
//                                                for (int l = k; l <= childList.size() - 1; l++) {
//                                                    r0StringBuilder.insert(0, childList.get(l).getData());
//                                                }
//                                                list.add(new Pair<>(r0StringBuilder.toString(), false));
                                                list.add(new Pair<>(beta1StringBuilder.toString(), true));
                                                list.add(new Pair<>(beta2, true));
//                                                list.add(new Pair<>(getR4(standardizedReDoSTree, child2, false), false));
                                                conflictPointHelper.setSplicedInnerConflictPointList(list);
                                                List<String> innerConflictPointList = new LinkedList<>();
                                                for (int l = k; l <= childList.size() - 1; l++) {
                                                    innerConflictPointList.add(r0InitialChainIndexList.get(l));
                                                }
                                                innerConflictPointList.add(child1.getInitialChainIndex());
                                                innerConflictPointList.addAll(getR2InitialChainIndexList(nearestParent, child1, child2));
                                                innerConflictPointList.add(child2.getInitialChainIndex());
                                                conflictPointHelper.setInnerConflictPointList(innerConflictPointList);
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

    public static ReDoSBean getPOAReDoSBean(String regex, String language) {
        ReDoSBean bean = new ReDoSBean();
        try {
//            TreeNode tree = getReDoSTree(regex, language);
//            bean = getPOAReDoSBeanHelper(tree, regex);
            TreeNode sourceReDoSTree = getReDoSTree(regex, language);
            TreeNode standardizedReDoSTree = getStandardizedReDoSTree(sourceReDoSTree, language);
            bean = getPOAReDoSBeanHelper(sourceReDoSTree, standardizedReDoSTree, regex);
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
        String regex = "@@@[1abc]+1abc\\w+2222\\w+!!!";
        regex = "a(\\w+|abc)d\\w+";
        regex = "(?P<amount>-?\\d+(?:\\.\\d*)?)[^\\S\\n]*(?P<degrees>°|deg(?:rees?)?|in)?[^\\S\\n]*(?P<unit>c(?:(?=el[cs]ius\\b|entigrades?\\b|\\b))|f(?:(?=ahrenheit\\b|\\b))|k(?:(?=elvins?\\b|\\b)))";
        regex = ".*(0a)?.*";
        regex = "<table.*?>(([\\s*].*(\\s*))+)?<\\/table>";
        regex = "(?<a>.*)(?<b>[0-9]\\.([0-9]*c)d$)";
        regex = "(ba*a(d|aa*)c)*";
        regex = ".*c((a.*(b))+)?\\b";
        regex = "^\\s*[+-]?\\s*(?:\\d{1,3}(?:(,?)\\d{3})?(?:\\1\\d{3})*(\\.\\d*)?|\\.\\d+)\\s*$";
        regex = "^p(ost)?[ |\\.]*o(ffice)?[ |\\.]*(box)?[ 0-9]*[^[a-z ]]*";
//        regex = "<a[a-zA-Z0-9 =\"'.:;?]*(href=[\\\"\\'](http:\\/\\/|\\.\\/|\\/)?\\w+(\\.\\w+)*(\\/\\w+(\\.\\w+)?)*(\\/|\\?\\w*=\\w*(&\\w*=\\w*)*)?[\\\"\\'])*(>[a-zA-Z0-9 =\"'<>.:;?]*</a>)";
//        regex = "([\\w-]+://?|www[.])[^\\s()<>]+";
//        regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{6,50})";
        regex = "^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,})).?)(?::\\d{2,5})?(?:[/?#]\\S*)?$";
//        regex = "^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z0-9]-*)*[a-z0-9]+)(?:\\.(?:[a-z0-9]-*)*[a-z0-9]+)*(?:\\.(?:[a-z]{2,})).?)(?::\\d{2,5})?(?:[/?#]\\S*)?$";
//        regex = "(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+@";
        regex = "gba\\([ \\n\\r\\t]*(.+?)[ \\n\\r\\t]*\\)";
        regex = "(a|b|c).(a.b)*.b+.c";
        regex = "^(([\\w][\\w\\-\\.]*)\\.)?([\\w][\\w\\-]+)(\\.([\\w][\\w\\.]*))?$";
//        regex = "^(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*$";
        regex = "(ab+)+b(ab+)+c";
        regex = "^((http|https|ftp|ftps)+(:\\/\\/))?(www\\.)?(([a-z0-9\\.-]{2,})\\.(ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cs|cu|cv|cx|cy|cz|dd|de|dj|dk|dm|do|dz|ec|ee|eg|eh|er|es|et|eu|fi|fj|fk|fm|fo|fr|fx|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|um|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|aero|asia|cat|coop|edu|gov|jobs|mil|mobi|museum|tel|travel|pro|post|biz|com|info|int|name|net|org|pro|arpa)|((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])))(:([1-9][0-9]?[0-9]?[0-9]?|[1-5][0-9][0-9][0-9][0-9]|6[0-4][0-9][0-9][0-9]|65[0-4][0-9][0-9]|655[0-2][0-9]|6553[0-5]|))?(((\\/(([a-zA-Z0-9_\\-\\%\\~\\+\\&\\;]{1,})+)*)*)|\\/$)?(\\.(php|html|htm|zip$|arj$|rar$|sit$|pdf$|gif$|jpg$|jpeg$|jpe$|tif$|tiff$))?(\\?([a-zA-Z0-9_\\-]+\\=[a-z-A-Z0-9_\\-\\%\\~\\+]+)?(\\&([a-zA-Z0-9_\\-]+\\=[a-z-A-Z0-9_\\-\\%\\~\\+]+))*)?(\\=\\?([a-zA-Z0-9_\\-])*)?(((\\+([a-zA-Z0-9_])*)?(\\-([a-zA-Z0-9_])*)?)*)?(\\#([a-z-A-Z0-9_\\-\\%\\~\\+\\&\\;]*$))?$";
        regex = "<!*[^<>]*>";
        regex = "^(\".+\"\\s)?<?[a-z\\._0-9]+[^\\._]@([a-z0-9]+\\.)+[a-z0-9]{2,6}>?;?";
        regex = "^((([hH][tT][tT][pP][sS]?|[fF][tT][pP])\\:\\/\\/)?([\\w\\.\\-]+(\\:[\\w\\.\\&%\\$\\-]+)*@)?((([^\\s\\(\\)\\<\\>\\\\\\\"\\.\\[\\]\\,@;:]+)(\\.[^\\s\\(\\)\\<\\>\\\\\\\"\\.\\[\\]\\,@;:]+)*(\\.[a-zA-Z]{2,4}))|((([01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d{1,2}|2[0-4]\\d|25[0-5])))(\\b\\:(6553[0-5]|655[0-2]\\d|65[0-4]\\d{2}|6[0-4]\\d{3}|[1-5]\\d{4}|[1-9]\\d{0,3}|0)\\b)?((\\/[^\\/][\\w\\.\\,\\?\\'\\\\\\/\\+&%\\$#\\=~_\\-@]*)*[^\\.\\,\\?\\\"\\'\\(\\)\\[\\]!;<>{}\\s\\x7F-\\xFF])?)$";
        regex = "^((([^\\s\\(\\)\\<\\>\\\\\\\"\\.\\[\\]\\,@;:]+)(\\.[a-zA-Z]{2,4}))((\\/[^\\/][\\w\\.\\,\\?\\'\\\\\\/\\+&%\\$#\\=~_\\-@]*)*[^\\.\\,\\?\\\"\\'\\(\\)\\[\\]!;<>{}\\s\\x7F-\\xFF])?)$";
        regex = "^([^\\s\\(\\)\\<\\>\\\\\\\"\\.\\[\\]\\,@;:]+)(\\.[a-zA-Z]{2,4})$";
        regex = "^((([^\\s\\(\\)\\<\\>\\\\\\\"\\.\\[\\]\\,@;:]+)(\\.[^\\s\\(\\)\\<\\>\\\\\\\"\\.\\[\\]\\,@;:]+)*(\\.[a-zA-Z]{2,4}))((\\/[^\\/][\\w\\.\\,\\?\\'\\\\\\/\\+&%\\$#\\=~_\\-@]*)*[^\\.\\,\\?\\\"\\'\\(\\)\\[\\]!;<>{}\\s\\x7F-\\xFF])?)$";
        regex = "([+(]?\\d{0,2}[)]?)([-/.\\s]?\\d+)+";
        regex = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";  // 这个能测出来 但是要从100开始增加   "1@1"+".0"*5000+"! _1"
        regex = "(((ht|f)tp(s?):\\/\\/)(www\\.[^ \\[\\]\\(\\)\\n\\r\\t]+)|(([012]?[0-9]{1,2}\\.){3}[012]?[0-9]{1,2})\\/)([^ \\[\\]\\(\\),;&quot;\\'&lt;&gt;\\n\\r\\t]+)([^\\. \\[\\]\\(\\),;&quot;\\'&lt;&gt;\\n\\r\\t])|(([012]?[0-9]{1,2}\\.){3}[012]?[0-9]{1,2})";
        regex = "\\b[P|p]?(OST|ost)?\\.?\\s*[O|o|0]?(ffice|FFICE)?\\.?\\s*[B|b][O|o|0]?[X|x]?\\.?\\s+[#]?(\\d+)\\b";
        regex = "\\b(\\w+).\\1";
        regex = "^[\\n &lt;&quot;']*([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+)";
        regex = "^[a-zA-Z0-9&#192;&#193;&#194;&#195;&#196;&#197;&#198;&#199;&#200;&#201;&#202;&#203;&#204;&#205;&#206;&#207;&#208;&#209;&#210;&#211;&#212;&#213;&#214;&#216;&#217;&#218;&#219;&#220;&#221;&#223;&#224;&#225;&#226;&#227;&#228;&#229;&#230;&#231;&#232;&#233;&#234;&#235;&#236;&#237;&#238;&#239;&#241;&#242;&#243;&#244;&#245;&#246;&#248;&#249;&#250;&#251;&#252;&#253;&#255;\\.\\,\\-\\/\\']+[a-zA-Z0-9&#192;&#193;&#194;&#195;&#196;&#197;&#198;&#199;&#200;&#201;&#202;&#203;&#204;&#205;&#206;&#207;&#208;&#209;&#210;&#211;&#212;&#213;&#214;&#216;&#217;&#218;&#219;&#220;&#221;&#223;&#224;&#225;&#226;&#227;&#228;&#229;&#230;&#231;&#232;&#233;&#234;&#235;&#236;&#237;&#238;&#239;&#241;&#242;&#243;&#244;&#245;&#246;&#248;&#249;&#250;&#251;&#252;&#253;&#255;\\.\\,\\-\\/\\' ]+$";
        regex = "<\\s*[\\/]?(?<tag>[a-z:_][-a-z0-9._:]*)(\\s+(?<attributes>[a-z:_]*[-a-z0-9._:]*[^\\s=><]*)\\s*=?\\s*(\"[^\"]*\"|'[^']*'|\"|')*[^\\s><]*)*\\s*[\\/]?>?";
        regex = "(?i)^(?:(?:METAR|SPECI)\\s*)*(?<ICAO>[\\w]{4})\\s*?(?<DateUTC>(?<DayOfMonth>\\d{0,2})(?<Hour>\\d{2})(?<Minutes>\\d{2}))Z{1}\\s*(?:[^\\r\\n])*";
        regex = "\\b(\\w+).\\1";
        regex = "^(((?:C/[\\-O]?[a-z\\ ]*?)?\\ *)?(P[\\.\\ ]?O[\\.\\ ]?\\ ?Box\\ *\\d+)|(?:((?:C/[\\-O]?)?[\\w\\ ,\\.']+?),?/?\\ *?)?\\ *?\\b((?:\\d+-)?\\d+[a-z]?)[\\ ](((?:[\\w\\ '\\-]|st)+)(?:\\b(ALLEY|ALLY|APPROACH|APP|ARCADE|ARC|AVENUE|AVE|BOULEVARD|BLVD|BROW|BYPASS|BYPA|CAUSEWAY|CWAY|CIRCUIT|CCT|CIRCUS|CIRC|CLOSE|CL|COPSE|CPSE|CORNER|CNR|COVE|COURT|CRT|CT|CRESCENT|CRES|DRIVE|DR|END|ESPLANANDE|ESP|FLAT|FREEWAY|FWAY|FRONTAGE|FRNT|GARDENS|GDNS|GLADE|GLD|GLEN|GREEN|GRN|GROVE|GR|HEIGHTS|HTS|HIGHWAY|HWY|LANE|LINK|LOOP|MALL|MEWS|PACKET|PCKT|PARADE|PDE|PARK|PARKWAY|PKWY|PLACE|PL|PROMENADE|PROM|RESERVE|RES|RIDGE|RDGE|RISE|ROAD|RD|ROW|SQUARE|SQ|STREET|ST|STRIP|STRP|TARN|TERRACE|TCE|THOROUGHFARE|TFRE|TRACK|TRAC|TRUNKWAY|TWAY|VIEW|VISTA|VSTA|WALK|WAY|WALKWAY|WWAY|YARD )\\b)))(?:,?\\ *?([a-z'.]+(?:,?\\ +[a-z'.]+)*?))?(?:,?\\ *?(Victoria|VIC|New South Wales|NSW|South Australia|SA|Northern Territory|NT|West Australia|WA|Tasmania|TAS|Australian Capital Territory|ACT|Queensland|QLD))?(?:,?\\ *?(\\d{3,4}))?(?:,?\\ *?(Au(?:stralia)?))?(?:(?=[^$])\\s)*$";
        regex = "^\\s*\\d+(?:\\x20+\\w+\\.?)+((?:(?:\\d+(?:\\x20+\\w+\\.?)+)|(?:General\\x20+Delivery)|(?:C[\\\\\\/]O\\x20+(?:\\w+\\x20*)+))\\,?\\x20*)?$";
        regex = "^\\s*((?:(?:\\d+(?:\\x20+\\w+\\.?)+(?:(?:\\x20+STREET|ST|DRIVE|DR|AVENUE|AVE|ROAD|RD|LOOP|COURT|CT|CIRCLE|LANE|LN|BOULEVARD|BLVD)\\.?)?)|(?:(?:P\\.\\x20?O\\.|P\\x20?O)\\x20*Box\\x20+\\d+)|(?:General\\x20+Delivery)|(?:C[\\\\\\/]O\\x20+(?:\\w+\\x20*)+))\\,?\\x20*(?:(?:(?:APT|BLDG|DEPT|FL|HNGR|LOT|PIER|RM|S(?:LIP|PC|T(?:E|OP))|TRLR|UNIT|\\x23)\\.?\\x20*(?:[a-zA-Z0-9\\-]+))|(?:BSMT|FRNT|LBBY|LOWR|OFC|PH|REAR|SIDE|UPPR))?)\\,?\\s+((?:(?:\\d+(?:\\x20+\\w+\\.?)+(?:(?:\\x20+STREET|ST|DRIVE|DR|AVENUE|AVE|ROAD|RD|LOOP|COURT|CT|CIRCLE|LANE|LN|BOULEVARD|BLVD)\\.?)?)|(?:(?:P\\.\\x20?O\\.|P\\x20?O)\\x20*Box\\x20+\\d+)|(?:General\\x20+Delivery)|(?:C[\\\\\\/]O\\x20+(?:\\w+\\x20*)+))\\,?\\x20*(?:(?:(?:APT|BLDG|DEPT|FL|HNGR|LOT|PIER|RM|S(?:LIP|PC|T(?:E|OP))|TRLR|UNIT|\\x23)\\.?\\x20*(?:[a-zA-Z0-9\\-]+))|(?:BSMT|FRNT|LBBY|LOWR|OFC|PH|REAR|SIDE|UPPR))?)?\\,?\\s+((?:[A-Za-z]+\\x20*)+)\\,\\s+(A[LKSZRAP]|C[AOT]|D[EC]|F[LM]|G[AU]|HI|I[ADLN]|K[SY]|LA|M[ADEHINOPST]|N[CDEHJMVY]|O[HKR]|P[ARW]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY])\\s+(\\d+(?:-\\d+)?)\\s*$";
        regex = "\\b(\\w+).\\1";
        regex = "^[a-zA-Z]([a-zA-Z[._][\\d]])*[@][a-zA-Z[.][\\d]]*[.][a-z[.][\\d]]*";
        regex = "\\[bible\\](([a-zäëïöüæø]*[[:space:]]{1}([a-zäëïöüæø]*[[:space:]]?[a-zäëïöüæø]*[[:space:]]{1})?)([0-9]{1,3})(:{1}([0-9]{1,3})(-{1}([0-9]{1,3}))?)?)\\[\\\\\\/bible\\]";
        regex = "\\[bible\\][[:space:]]{1}([a-z]*[a-z]*)$";
        regex = "\\bSmartWatch *\\( *([^;]+) *; *([^;]+) *;";
        regex = "@rx (?i:(?:[\\\"'`]\\s*?(?:(?:n(?:and|ot)|(?:x?x)?or|between|\\|\\||and|div|&&)\\s+[\\s\\w]+=\\s*?\\w+\\s*?having\\s+|like(?:\\s+[\\s\\w]+=\\s*?\\w+\\s*?having\\s+|\\W*?[\\\"'`\\d])|[^?\\w\\s=.,;)(]+\\s*?[(@\\\"'`]*?\\s*?\\w+\\W+\\w|\\*\\s*?\\w+\\W+[\\\"'`])|(?:union\\s*?(?:distinct|[(!@]*?|all)?\\s*?[(\\[]*?\\s*?select|select\\s+?[\\[\\]()\\s\\w\\.,\\\"'`-]+from)\\s+|\\w+\\s+like\\s+[\\\"'`]|find_in_set\\s*?\\(|like\\s*?[\\\"'`]%))";
        regex = "@rx (?i:(?:[\\\"'`]*\\s*?))";
        regex = "@rx (?i:(like(?:\\s+[\\s\\w]+=\\s*?\\w+\\s*?having\\s+|\\W*?[\\\"'`\\d])|[^?\\w\\s=.,;)(]+\\s*?[(@\\\"'`]*?\\s*?\\w+\\W+\\w|\\*\\s*?\\w+\\W+[\\\"'`]))";
        regex = "@rx (?i:([^?\\w\\s=.,;)(]+\\s*?[(@\\\"'`]*?\\s*?\\w+\\W+\\w))";
        regex = "@rx (?i:([^a]+[(]*?))";
        regex = "/\\x3Cinput\\s+type\\x3D\\x22checkbox\\x22\\s+id\\x3D(?P<q1>(\\x22|\\x27|))(?P<t>\\S+)(?P=q1).*?document\\x2EgetElementById\\x28(?P<q2>(\\x22|\\x27|))(?P=t)(?P=q2)\\x29\\x2EcreateTextRange/isO";
        TreeNode ReDoSTree = getReDoSTree(regex, "java");
        printTree(ReDoSTree);
//        TreeNode left = ReDoSTree.getChild(0);
//        TreeNode right = ReDoSTree.getChild(2).getChild(0).getChild(1).getChild(0).getChild(1).getChild(1);
//        System.out.println(left.getData() + " " + right.getData());
//        System.out.println(getR2(ReDoSTree, left, right));
//        System.exit(0);
//        System.out.println(isAllParentCountingLessOrEqualToOne(ReDoSTree.getChild(0).getChild(1).getChild(0).getChild(1).getChild(0)));
//        System.out.println(getOrSymbolTreeNode(ReDoSTree.getChild(0)));
        ReDoSBean reDosBean = getPOAReDoSBean(regex, "java");
        ArrayList<AttackBean> attackBeanList = reDosBean.getAttackBeanList();
        for (int i = 0; i < attackBeanList.size(); i++) {
            System.out.println(attackBeanList.get(i).getAttackStringFormat().replace("\u0000", "\\u0000"));
        }
    }

}
