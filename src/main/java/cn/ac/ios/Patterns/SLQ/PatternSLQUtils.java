package cn.ac.ios.Patterns.SLQ;

import cn.ac.ios.Bean.*;
import cn.ac.ios.TreeNode.TreeNode;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

import static cn.ac.ios.Patterns.SLQ.SLQUtils.*;
import static cn.ac.ios.TreeNode.Utils.*;
import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.Utils.BracketUtils.*;
import static cn.ac.ios.Utils.DkBricsAutomatonUtils.*;
import static cn.ac.ios.Utils.FlagsUtils.*;
import static cn.ac.ios.Utils.FlagsUtils.replaceLocalFlagGM;
import static cn.ac.ios.Utils.GenMatchStringUtils.getExampleByDkBricsAutomaton2;
import static cn.ac.ios.Utils.GenMatchStringUtils.getTranslateRegexForAssertionsList;
import static cn.ac.ios.Utils.NegateUtils.refactorAssertPattern;
import static cn.ac.ios.Utils.RegexUtils.*;
import static cn.ac.ios.Utils.RegexUtils.isCanEmptyNode;
import static cn.ac.ios.Utils.SpecialStringUtils.getSpecialSuffixStringWithCountingAndEndLine;
import static cn.ac.ios.Utils.SplitRegexUtils.getR0;
import static cn.ac.ios.Utils.SplitRegexUtils.getR4;

public class PatternSLQUtils {
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

        return ReDoSTree;
    }

    /**
     * SLQ 模式 ，条件一
     *
     * @param root
     * @param regex 暂时不用
     * @return 一组攻击串
     */
    @Deprecated
    private static ArrayList<AttackBean> getSLQConditionOne(TreeNode root, String regex) throws InterruptedException {
        ArrayList<AttackBean> list = new ArrayList<>();
        if (isGroupNode(root)) {
            return getSLQConditionOne(getGroupSubNode(root), getGroupSubNode(root).getData());
        }
        if (isOrNode(root)) {
            root = getGroupSubNode(root);
            for (TreeNode child : root.getChildList()) {
                if (!child.getData().equals("|")) {
                    list.addAll(getSLQConditionOne(child, child.getData()));
                }
            }
            return list;
        }
        ArrayList<Pair<String, String>> pairArrayList = getFirstAndCountingNode(root);
        if (pairArrayList == null) {
            return list;
        }
        for (Pair<String, String> pair : pairArrayList) {
            String repeat = pair.getKey();
            if (repeat.length() == 0) {
                continue;
            }
            String suffix = pair.getValue();
            AttackBean attackBean = new AttackBean();
            attackBean.setPrefix("");
            attackBean.setInfix(repeat);
            attackBean.setSuffix(suffix);
            attackBean.initType(AttackType.POLYNOMIAL);
            attackBean.setPatternType(PatternType.SLQ_1);
            list.add(attackBean);
        }
        return list;
    }

    /**
     * SLQ模式,条件一，找到第一个节点，如果是集合节点，则生成对应攻击串
     *
     * @param root
     * @return 一组pair，包含infix和suffix
     * 支持潜逃
     */
    @Deprecated
    private static ArrayList<Pair<String, String>> getFirstAndCountingNode(TreeNode root) {
        if (isGroupNode(root)) {
            return getFirstAndCountingNode(getGroupSubNode(root));
        }
        if (isOrNode(root)) {
            root = getGroupSubNode(root);
            ArrayList<Pair<String, String>> list = new ArrayList<>();
            for (TreeNode child : root.getChildList()) {
                if (!"|".equals(child.getData())) {
                    list.addAll(getFirstAndCountingNode(child));
                }
            }
            return list;
        }
        if (root.getChildList().isEmpty()) {
            return new ArrayList<>();
        }
        if (isSLQCountingNode(root)) {
            ArrayList<Pair<String, String>> list = new ArrayList<>();
            String repeat = root.getMatchStrWithCounting();
            list.add(new Pair<>(repeat, ""));
            return list;
        }
        TreeNode node = root.getChild(0);
        while (!node.isLeaf() && node.getLastChild().getData().equals("?") && getFirstAndCountingNode(node).isEmpty()) {
//            node = node.getNextNode();

            if (node.getNextNode() != null) {   // 修改
                node = node.getNextNode();
            } else {
                break;
            }
        }
        if (isGroupNode(node)) {
            node = getGroupSubNode(node);
            StringBuilder suffix = new StringBuilder();
            ArrayList<Pair<String, String>> list = new ArrayList<>();
            ArrayList<Pair<String, String>> pairArrayList = getFirstAndCountingNode(node);
            if (pairArrayList != null) {
                if (suffix.length() == 0) {
                    for (int i = 1; i < root.getChildCount(); i++) {
                        String data = root.getChild(i).getNonMatchStr();
                        if (data.length() > 0) {
                            suffix.append(data);
                            break;
                        }
                    }
                    if (suffix.length() == 0) {
                        suffix.append(root.getNonMatchStr());
                    }
                }
                for (Pair<String, String> pair : pairArrayList) {
                    list.add(new Pair<>(pair.getKey(), pair.getValue() + suffix));
                }
            }
            return list;
        }
        if (isOrNode(node)) {
            node = getGroupSubNode(node);
            StringBuilder suffix = new StringBuilder();
            ArrayList<Pair<String, String>> list = new ArrayList<>();
            for (TreeNode child : node.getChildList()) {
                if (!"|".equals(child.getData())) {
                    if (isSLQCountingNode(child)) {
                        if (suffix.length() == 0) {
                            for (int i = 1; i < root.getChildCount(); i++) {
                                String data = root.getChild(i).getNonMatchStr();
                                if (data.length() > 0) {
                                    suffix.append(data);
                                    break;
                                }
                            }
                            if (suffix.length() == 0) {
                                suffix.append(root.getNonMatchStr());
                            }
                        }
                        list.add(new Pair<>(child.getMatchStrWithCounting(), suffix.toString()));
                    } else {
                        ArrayList<Pair<String, String>> pairArrayList = getFirstAndCountingNode(child);
                        if (pairArrayList != null) {
                            if (suffix.length() == 0) {
                                for (int i = 1; i < root.getChildCount(); i++) {
                                    String data = root.getChild(i).getNonMatchStr();
                                    if (data.length() > 0) {
                                        suffix.append(data);
                                        break;
                                    }
                                }
                                if (suffix.length() == 0) {
                                    suffix.append(root.getNonMatchStr());
                                }
                            }
                            for (Pair<String, String> pair : pairArrayList) {
                                list.add(new Pair<>(pair.getKey(), pair.getValue() + suffix));
                            }
                        }
                    }
                }
            }
            return list;
        } else {
            if (isSLQCountingNode(node)) {
                StringBuilder suffix = new StringBuilder();
                for (int i = 1; i < root.getChildCount(); i++) {
                    String data = root.getChild(i).getNonMatchStr();
                    if (data.length() > 0) {
                        suffix.append(data);
                        break;
                    }
                }
                if (suffix.length() == 0) {
                    suffix.append(root.getNonMatchStr());
                }
                ArrayList<Pair<String, String>> list = new ArrayList<>();
                list.add(new Pair<>(node.getMatchStrWithCounting(), suffix.toString()));
                return list;
            }
        }
        return new ArrayList<>();
    }

    /**
     * SLQ模式，条件二，对应的攻击串
     *
     * @return 多个攻击串
     */
    private static ArrayList<AttackBean> getSLQConditionTwo(TreeNode ReDoSTree, String regex) throws InterruptedException {
        ReDoSTree = getGroupSubNode(ReDoSTree);
        ArrayList<AttackBean> list = new ArrayList<>();
        List<TreeNode> treeNodeList = ReDoSTree.getChildList();
        ArrayList<Integer> dotList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < treeNodeList.size(); i++) {
            if (treeNodeList.get(i).getData().length() >= 3) {
                ArrayList<AttackBean> sublist = getSLQConditionTwo(treeNodeList.get(i), treeNodeList.get(i).getData());
                for (AttackBean attackBean : sublist) {
                    // 以下6行新增
                    AttackBean originAttackBean = SerializationUtils.clone(attackBean); // 深拷贝
                    originAttackBean.setPrefix(originAttackBean.getPrefix() + stringBuilder.toString());
                    if (i > 1) {
                        originAttackBean.setSuffix(treeNodeList.get(i - 1).getNonMatchStr());
                    }
                    list.add(originAttackBean);

                    attackBean.setPrefix(attackBean.getPrefix() + stringBuilder.toString());
                    if (i > 1) {
                        attackBean.setSuffix(treeNodeList.get(i - 1).getNonMatchStr() + attackBean.getSuffix());
                    }
                }
                list.addAll(sublist);
            }
            stringBuilder.append(treeNodeList.get(i).getMatchStr());
            if (isSetRepeatNode(treeNodeList.get(i))) {
                dotList.add(i);
            } else if (isSingleCountingNode(treeNodeList.get(i))) {
                dotList.add(i);
            } else if (isGeneralizedCountingNodeWithMaxNumGreaterThanOne(treeNodeList.get(i))) {
                dotList.add(i);
            }
        }

        for (Integer index : dotList) {
            StringBuilder repeat = new StringBuilder();
            for (int i = 0; i < index; i++) {
                repeat.append(treeNodeList.get(i).getMatchStrWithCounting());
            }
            if (index == 0) {
                repeat.append(treeNodeList.get(0).getMatchStrWithCounting());
            }
            if (repeat.length() == 0) {
                continue;
            }

            StringBuilder suffix = new StringBuilder();
            for (int i = index; i < treeNodeList.size(); i++) {
                suffix.append(treeNodeList.get(i).getNonMatchStr());
                if (!suffix.toString().isEmpty()) {
                    break;
                }
            }
            if (index > 1) {
                suffix.append(treeNodeList.get(index - 1).getNonMatchStr());
            }
            AttackBean attackBean = new AttackBean();
            attackBean.setPrefix("");
            attackBean.setInfix(repeat.toString());
            attackBean.setSuffix(suffix.toString());
            attackBean.initType(AttackType.POLYNOMIAL);
            attackBean.setPatternType(PatternType.SLQ_2);
            list.add(attackBean);
            if (suffix.toString().contains(" ")) {
                attackBean = new AttackBean();
                attackBean.setPrefix("");
                attackBean.setInfix(repeat.toString());
                attackBean.setSuffix(suffix.toString().replace(" ", ""));
                attackBean.initType(AttackType.POLYNOMIAL);
                attackBean.setPatternType(PatternType.SLQ_2);
                list.add(attackBean);
            }
        }
        return list;
    }

    private static ArrayList<AttackBean> getSLQConditionThree(TreeNode root, String regex) {
        ArrayList<AttackBean> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isSLQThreeCountingNode(node)) {
                node = getGroupSubNode(node);
                node = node.getChild(0);
                node = getGroupSubNode(node);
                ArrayList<String> arrayList = getMatchExamples(node);
                for (String str : arrayList) {
                    AttackBean attackBean = new AttackBean();
                    attackBean.setPrefix(root.getMatchStr(node));
                    attackBean.setSuffix(root.getNonMatchStr(node) + root.getNonMatchStr());
                    attackBean.setInfix(str);
                    attackBean.initType(AttackType.POLYNOMIAL);
                    attackBean.setPatternType(PatternType.SLQ_3);
                    list.add(attackBean);
                }
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        return list;
    }

    /**
     * 处理特殊正则 ^\\s+|\\s+$
     *
     * @param root
     * @param regex
     * @return
     */
    private static ArrayList<AttackBean> getSLQConditionSp(TreeNode root, String regex) throws InterruptedException {
        ArrayList<AttackBean> list = new ArrayList<>();
        if (regex.startsWith("^") && regex.endsWith("$") && isOrNode(root)) {
            root = getGroupSubNode(root);
            if (root.getChild(0).getData().equals(root.getChild(2).getData())) {
                list = getSLQConditionOne(root.getChild(0), root.getChild(0).getData());
                for (AttackBean attackBean : list) {
                    attackBean.setPrefix(root.getChild(0).getNonMatchStr());
                }
            }
        }
        return list;
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

    // 处理特殊正则 ^\\s+|\\s+$
    private static ArrayList<AttackBean> getSLQConditionSp(TreeNode root) throws InterruptedException {
        ArrayList<AttackBean> list = new ArrayList<>();
        // 有start of a line和end of a line
        List<TreeNode> leafNodes = root.getLeafNodes();
        boolean flag1 = false, flag2 = false;
        for (TreeNode leaf: leafNodes) {
            if (isInBrackets(leaf) || isInNegateNode(leaf)) continue;
            if (leaf.getData().equals("^") || leaf.getData().equals("\\A")) flag1 = true;
            if (leaf.getData().equals("$") || leaf.getData().equals("\\Z") || leaf.getData().equals("\\z")) flag2 = true;
            if (flag1 && flag2) break;
        }
        if (flag1 && flag2) {
            TreeNode regexTree = SerializationUtils.clone(root);    // 深拷贝
            // 删除零宽断言
//            deleteZeroWidthAssertion(regexTree);
            refactorAssertPattern(regexTree);
            // 使用重写后的去首尾^$
            regexTree.deleteCaretAndDollarSymbols();
            // 新版重写空串
            regexTree = removeBlankStr(regexTree);
            // 重写反向引用后 删除NonCapturingGroupFlag ?:
            regexTree.deleteNonCapturingGroupFlag();

            regexTree = getGroupSubNode(regexTree);
            if (isOrNode(regexTree)) {
                StringBuilder prefixStringBuilder = new StringBuilder();
                for (int i = 0; i < regexTree.getChildCount(); i += 2) {
                    String prefix = getNoMatchString(regexTree.getChild(i).getData());
                    if (prefix != null) prefixStringBuilder.append(prefix);
                }

                for (int i = 0; i < regexTree.getChildCount() - 2; i += 2) {
                    for (int j = i + 2; j < regexTree.getChildCount(); j++) {
                        // 删除orNode中orNode.getChild(i)和orNode.getChild(j)
                        String tempRegex = deleteTwoChildFromOrNode(regexTree, regexTree.getChild(i), regexTree.getChild(j));
                        String regex1n = tempRegex.equals("") ? regexTree.getChild(i).getData() : tempRegex + "|" + regexTree.getChild(i).getData();    // 除掉r2
                        String regex2n = tempRegex.equals("") ? regexTree.getChild(j).getData() : tempRegex + "|" + regexTree.getChild(j).getData();    // 除掉r1
                        List<String> regexList1 = new ArrayList<>();
                        regexList1.add(regex1n);
                        regexList1.add(regex2n);
                        String infix = getExampleByDkBricsAutomaton(regexList1, null, 1);
                        if (infix == null) continue;
                        AttackBean attackBean = new AttackBean();
                        attackBean.setPrefix(prefixStringBuilder.toString());
                        attackBean.setInfix(infix);
                        attackBean.setSuffix(getSpecialSuffixStringWithCountingAndEndLine(regexTree));
                        attackBean.initType(AttackType.POLYNOMIAL);
                        attackBean.setPatternType(PatternType.SLQ_1);
                        list.add(attackBean);
                    }
                }

            }
        }
        return list;
    }



    private static ReDoSBean getSLQReDoSBeanHelper(TreeNode sourceReDoSTree, TreeNode standardizedReDoSTree, String regex) throws InterruptedException {
        ReDoSBean bean = new ReDoSBean();
        ArrayList<AttackBean> list1 = getSLQConditionOne(standardizedReDoSTree, regex);
        ArrayList<AttackBean> list2 = getSLQConditionTwo(standardizedReDoSTree, regex);
        ArrayList<AttackBean> beans = new ArrayList<>();
        for (AttackBean attackBean : list2) {
            AttackBean item = new AttackBean();
            item.setPrefix("");
            item.setInfix(attackBean.getPrefix() + attackBean.getInfix());
            item.setSuffix(attackBean.getSuffix());
            item.initType(AttackType.POLYNOMIAL);
            item.setPatternType(attackBean.getPatternType());
            beans.add(item);
        }
        list1.addAll(beans);
        list1.addAll(list2);//todo slq2 应该没有前缀
        ArrayList<AttackBean> list3 = getSLQConditionThree(standardizedReDoSTree, regex);
        if (list3.size() < 100) {
            list1.addAll(list3);
        }
//        ArrayList<AttackBean> list4 = getSLQConditionSp(standardizedReDoSTree, regex);
        ArrayList<AttackBean> list4 = getSLQConditionSp(standardizedReDoSTree);
        list1.addAll(list4);
        list1.sort(Comparator.comparingInt(o -> o.getInfix().length()));
//        list1.addAll(0, getSLQReDoSBeanHelper2(standardizedReDoSTree, regex).getAttackBeanList());
        ArrayList<AttackBean> list5 = getSLQCondition(standardizedReDoSTree, regex);
        list5.sort(Comparator.comparingInt(o -> o.getInfix().length()));
        list1.addAll(0, list5);

        bean.getAttackBeanList().addAll(list1);
        return bean;
    }

    /**
     * @param root
     * @param regex
     * @return
     */
    private static ArrayList<AttackBean> getSLQCondition(TreeNode root, String regex) throws InterruptedException {
        ArrayList<AttackBean> list = new ArrayList<>();
        ArrayList<TreeNode> nodes = getAllCountingNode(root);
        for (TreeNode node : nodes) {
            String preRegex = getR0(root, node);
            String suffixRegex = getR4(root, node);
            if (getParOrNode(node) != null) {
                String orRegex = getParOrNode(node).getData();
            }
            String infixRegex = node.getData();
            if (suffixRegex.length() == 0) {
                suffixRegex = infixRegex;
            }
            try {
                String attackInfix;
                TreeNode pre = createReDoSTree(preRegex);
                if (preRegex.length() == 0 || isCanEmptyNode(pre)) {
                    attackInfix = getMatchString(infixRegex, suffixRegex);
                } else {
                    attackInfix = getMatchString(preRegex, infixRegex, suffixRegex);
                }

                if (attackInfix == null || attackInfix.length() == 0) {
                    continue;
                }
                String attackSuffix = getNoMatchString(suffixRegex);
                if (attackSuffix == null || attackSuffix.length() == 0) {
                    attackSuffix = getNoMatchString(infixRegex);
                }

                if (attackSuffix == null || attackSuffix.length() == 0) {
                    continue;
                }

                AttackBean attackBean = new AttackBean();
                attackBean.setPrefix("");
                attackBean.setInfix(attackInfix);
                attackBean.setSuffix(attackSuffix);
                attackBean.initType(AttackType.POLYNOMIAL);
                attackBean.setPatternType(PatternType.SLQ2);
                list.add(attackBean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private static String getMatchString(String infixRegex, String suffixRegex) {
        String regex = infixRegex;
        if (suffixRegex.length() != 0) {
            regex = "(" + infixRegex + ")＆(～(" + suffixRegex + "))";
        }
        try {
            return getMatchString(regex);
        } catch (InterruptedException e) {
            return "";
        } catch (Exception e) { // 新增
            return "";
        }
    }

    private static String getMatchString(String preRegex, String infixRegex, String suffixRegex) {
        try {
            String attackInfix;
            if (suffixRegex.length() != 0) {
                String regex = "(" + preRegex + ".*)＆" + "(" + infixRegex + ")＆(～(" + suffixRegex + "))";
                attackInfix = getMatchString(regex);
                if (attackInfix == null || attackInfix.length() == 0) {
                    regex = "(.*" + preRegex + ")＆" + "(" + infixRegex + ")＆(～(" + suffixRegex + "))";
                    attackInfix = getMatchString(regex);
                }
                if (attackInfix == null || attackInfix.length() == 0) {
                    regex = "(.*" + preRegex + ".*)＆" + "(" + infixRegex + ")＆(～(" + suffixRegex + "))";
                    attackInfix = getMatchString(regex);
                }
            } else {
                String regex = "(" + preRegex + ".*)＆" + "(" + infixRegex + ")";
                attackInfix = getMatchString(regex);
                if (attackInfix == null || attackInfix.length() == 0) {
                    regex = "(.*" + preRegex + ")＆" + "(" + infixRegex + ")";
                    attackInfix = getMatchString(regex);
                }
                if (attackInfix == null || attackInfix.length() == 0) {
                    regex = "(.*" + preRegex + ".*)＆" + "(" + infixRegex + ")";
                    attackInfix = getMatchString(regex);
                }
            }
            return attackInfix;
        } catch (InterruptedException e) {
            return "";
        } catch (Exception e) { // 新增
            return "";
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

    private static String getMatchString(String regex) throws InterruptedException {
        TreeNode node = createReDoSTree(regex);
        List<String> innerTrans = getTranslateRegexForAssertionsList(node);
        List<String> regexList1 = new ArrayList<>(innerTrans);
        return getExampleByDkBricsAutomaton2(regexList1, 1);
    }

    private static String getNoMatchString(String regex) throws InterruptedException {
        TreeNode node = createReDoSTree("～(" + regex + ")");
        List<String> innerTrans = getTranslateRegexForAssertionsList(node);
        List<String> regexList1 = new ArrayList<>(innerTrans);
        return getExampleByDkBricsAutomaton2(regexList1, 1);
    }

    public static ReDoSBean getSLQReDoSBean(String regex, String language) {
        ReDoSBean bean = new ReDoSBean();
        try {
            TreeNode sourceReDoSTree = getReDoSTree(regex, language);
            TreeNode standardizedReDoSTree = getStandardizedReDoSTree(sourceReDoSTree, language);
            bean = getSLQReDoSBeanHelper(sourceReDoSTree, standardizedReDoSTree, regex);
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
        regex = "^\\s+|\\s*$";
        ReDoSBean reDosBean = getSLQReDoSBean(regex, "java");
        ArrayList<AttackBean> attackBeanList = reDosBean.getAttackBeanList();
        for (int i = 0; i < attackBeanList.size(); i++) {
            System.out.println(attackBeanList.get(i).getAttackStringFormat());
        }
    }
}

