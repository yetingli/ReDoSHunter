package cn.ac.ios.Utils;

import cn.ac.ios.Patterns.NQ.PatternNQUtils;
import cn.ac.ios.TreeNode.TreeNode;

import java.util.*;
import java.util.regex.Pattern;

import static cn.ac.ios.TreeNode.Utils.*;
import static cn.ac.ios.Utils.BracketUtils.isBracketsNode;
import static cn.ac.ios.Utils.Constant.*;
import static cn.ac.ios.Utils.DkBricsAutomatonUtils.getExampleByDkBricsAutomaton;
import static cn.ac.ios.Utils.FlagsUtils.isSpecialCharacter;
import static cn.ac.ios.Utils.RegexUtils.*;
import static cn.ac.ios.Utils.SplitRegexUtils.getR0;

public class SpecialStringUtils {
    // 从带$的或结点中生成另一个结点的串 例如([,;]|$)中生成,或;
    // 跳过空结点
    public static String getMatchStringWithEndLineInOrNode(TreeNode treeNode) {
        StringBuilder s = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(treeNode);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            node = getGroupSubNode(node);
            if (isZeroWidthAssertionNode(node)) {
                continue;
            }
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                boolean flag = true;
                for (int i = 0; i < node.getChildCount(); i += 2) {
                    if (!isZeroWidthAssertionNode(node.getChild(i))) {
                        node = node.getChild(i);
                        flag = false;
                        break;
                    }
                }
                if (flag) continue;
            }
            if (isGeneralizedCountingNode(node)) {
                node = getGroupSubNode(node);
                int num = getCountingFirstNum(node.getChild(1).getData());
//                num = num == 0 ? 1 : num;
                String subStr = getMatchStringWithEndLineInOrNode(node.getChild(0));
                for (int i = 0; i < num; i++) {
                    s.append(subStr);
                }
                continue;
            }
            if (node.isLeaf()) {
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                s.append(getMatchLeafNode(node));
            } else if (isBracketsNode(node) || isNegateNode(node)) {
                boolean flag = false;
                Pattern pattern = Pattern.compile(node.getData());
                for(String c: dot_MATCH_CHARACTER_LIST) {
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (isMatch) {
                        s.append(c);
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                for (int i = 0; i < 65536; i++) {
                    char c = (char) i;
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (isMatch) {
                        s.append(c);
                        break;
                    }
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                        stack.push(child);
                    }
                }
            }
        }
        return s.toString();
    }

    // 从叶子节点返回不匹配串
    private static String getNonMatchLeafNode(TreeNode node) {
        if (!node.isLeaf()) {
            return "";
        }
        String data = node.getData();
        StringBuilder s = new StringBuilder();
        if (data.equals(".")) {
            s.append("\n");
        } else if (data.length() == 1) {
            s.append("!".equals(data) ? "\u0000" : "◎");
        } else {
            switch (data) {
                case "\\W":
                    s.append("a");
                    break;
                case "\\D":
                    s.append("1");
                    break;
                case "\\S":
                    s.append(" ");
                    break;
                case "\\w":
                    s.append("!");
                    break;
                case "\\d":
                    s.append("!");
                    break;
                case "\\s":
                    s.append("!");
                    break;
                default:
                    s.append("!");
                    break;
            }
        }
        return s.toString();
    }

    // 获取每一个结点的不匹配串 不支持lookaround
    // 不跳过空结点
    private static String getNonMatchString(TreeNode treeNode) {
        StringBuilder s = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(treeNode);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            node = getGroupSubNode(node);
            if (isZeroWidthAssertionNode(node)) {
                continue;
            }
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
//                node = node.getChild(0);
                for (int i = node.getChildCount() - 1; i >= 0; i -= 2) {
                    stack.push(node.getChild(i));
                }
                continue;
            }
            if (isGeneralizedCountingNode(node)) {
                node = getGroupSubNode(node);
                int num = getCountingFirstNum(node.getChild(1).getData());
                num = num == 0 ? 1 : num;
                String subStr = getNonMatchString(node.getChild(0));
                for (int i = 0; i < num; i++) {
                    s.append(subStr);
                }
                continue;
            }
            if (node.isLeaf()) {
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                s.append(getNonMatchLeafNode(node));
            } else if (isBracketsNode(node) || isNegateNode(node)) {
                boolean flag = false;
                Pattern pattern = Pattern.compile(node.getData());
                for(String c: dot_MATCH_CHARACTER_LIST) {
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (!isMatch) {
                        s.append(c);
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                for (int i = 0; i < 65536; i++) {
                    char c = (char) i;
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (!isMatch) {
                        s.append(c);
                        break;
                    }
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                        stack.push(child);
                    }
                }
            }
        }
        return s.toString();
    }

    // 获取每一个结点的匹配串  不支持lookaround
    // 不跳过空结点
    private static String getMatchString(TreeNode treeNode) {
        StringBuilder s = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(treeNode);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            node = getGroupSubNode(node);
            if (isZeroWidthAssertionNode(node)) {
                continue;
            }
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                node = node.getChild(0);
            }
            if (isGeneralizedCountingNode(node)) {
                node = getGroupSubNode(node);
                int num = getCountingFirstNum(node.getChild(1).getData());
                num = num == 0 ? 1 : num;
                String subStr = getMatchString(node.getChild(0));
                for (int i = 0; i < num; i++) {
                    s.append(subStr);
                }
                continue;
            }
            if (node.isLeaf()) {
                if (Pattern.matches(COUNTING, node.getData()) || (node.getData().length() == 1 && isSpecialCharacter(node.getData().charAt(0)))) {
                    continue;
                }
                s.append(getMatchLeafNode(node));
            } else if (isBracketsNode(node) || isNegateNode(node)) {
                boolean flag = false;
                Pattern pattern = Pattern.compile(node.getData());
                for(String c: dot_MATCH_CHARACTER_LIST) {
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (isMatch) {
                        s.append(c);
                        flag = true;
                        break;
                    }
                }
                if (flag) continue;
                for (int i = 0; i < 65536; i++) {
                    char c = (char) i;
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (isMatch) {
                        s.append(c);
                        break;
                    }
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    TreeNode child = node.getChild(i);
                    if (!(Pattern.matches(FLAGS_REDUCES, child.getData()))) {
                        stack.push(child);
                    }
                }
            }
        }
        return s.toString();
    }

    // 获取特殊后缀 counting + $ 结尾
    // 反正反  特指最后一个counting
    public static String getSpecialSuffixStringWithCountingAndEndLine(TreeNode node) {
        List<TreeNode> allCountingNode = node.getAllGeneralizedCountingWithMaxNumLeqOneNode();
        if (allCountingNode.isEmpty()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        TreeNode countingNode = allCountingNode.get(allCountingNode.size() - 1);
//        for (TreeNode countingNode: allCountingNode) {
            String rejectString = getNonMatchString(countingNode);
            String acceptString = getMatchString(countingNode);
            stringBuilder.append(rejectString + acceptString + rejectString);
//        }
//        return stringBuilder.toString();
        return duplicateSameElementsInTheString(stringBuilder.toString());
    }

    // 获取特殊后缀 正则中所有的counting结点取反    重复两次
    public static String getSpecialSuffixStringWithAllCountingNodes(TreeNode node) {
        List<TreeNode> allCountingNode = node.getAllGeneralizedCountingWithMaxNumLeqOneNode();
        StringBuilder stringBuilder = new StringBuilder();
        for (TreeNode countingNode: allCountingNode) {
            stringBuilder.append(getNonMatchString(countingNode));
        }
//        return stringBuilder.toString() + stringBuilder.toString();

        // 将字符串中相邻的相同元素去重
        return duplicateSameElementsInTheString(stringBuilder.toString() + stringBuilder.toString());
    }

    private static String duplicateSameElementsInTheString(String text) {
//        StringBuilder sb = new StringBuilder();
//        char[] chars = text.toCharArray();
//        char previous = chars[0];
//        sb.append(chars[0]);
//        for(int i = 1 ; i < chars.length; i++) {
//            if(chars[i] != previous) {
//                sb.append(chars[i]);
//                previous = chars[i];
//            }
//        }
//        return sb.toString();

        char[] chars = text.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c: chars) {
            set.add(c);
        }
        for (char c: set) {
            text = text.replaceAll(c + "+", String.valueOf(c));
        }
        return text;
    }

    // 通过(?<! (?!不接受
    // ^(?:[^\\\/#\s]|\\[\s\S]|\/(?!\/\/)|\#(?!\{)|\s+(?:#(?!\{).*)?)*
    // 前缀空 中缀空格 后缀#{
    public static List<String> getSpecialSuffixStringWithNegativeAssertNode(TreeNode node) throws InterruptedException {
        List<String> suffixList = new LinkedList<>();
        LinkedList<TreeNode> allNegativeAssertNode = node.getAllNegativeAssertNode();
        for (TreeNode negativeAssertNode: allNegativeAssertNode) {
            String r0 = getR0(node, negativeAssertNode);
            if (isNegativeLookAheadNode(negativeAssertNode)) {
                suffixList.add(getMatchString(createReDoSTree(r0)) + getMatchString(negativeAssertNode.getChild(3)));
            } else if (isNegativeLookBehindNode(negativeAssertNode)) {
                suffixList.add(getMatchString(createReDoSTree(r0)) + getMatchString(negativeAssertNode.getChild(4)));
            }
        }
        return suffixList;
    }


//    // 获取特殊后缀 counting + $ 结尾
//    // 反正反
//    public static String getSpecialSuffixStringWithCountingAndEndLine(TreeNode node, boolean considerZeroWidthAssertions) throws InterruptedException {
//        if (considerZeroWidthAssertions) {
//            TreeNode newTreeNode = SerializationUtils.clone(node);
//            // 使用重写后的去首尾^$
//            newTreeNode.deleteCaretAndDollarSymbols();
//            // 新版重写空串
//            newTreeNode = removeBlankStr(newTreeNode);
//            // 重写反向引用后 删除NonCapturingGroupFlag ?:
//            newTreeNode.deleteNonCapturingGroupFlag();
//            List<String> translateRegexForLookAroundList = getTranslateRegexForAssertionsList(newTreeNode);
//            // 获取正例
//            String acceptString = getExampleByDkBricsAutomaton2(translateRegexForLookAroundList, 1);
//            if (acceptString == null) return null;
//
//            // 获取反例
//            List<String> translateRegexForLookAroundList2 = new ArrayList<>();
//            for (int i = 0; i < translateRegexForLookAroundList.size(); i++) {
//                translateRegexForLookAroundList2.add("～(" + translateRegexForLookAroundList.get(i) + ")");
//            }
//            String rejectString = getExampleByDkBricsAutomaton2(translateRegexForLookAroundList2, 1);
//            if (rejectString == null) return null;
//
//            return rejectString + acceptString + rejectString;
//        } else {
//            TreeNode newTreeNode = SerializationUtils.clone(node);
//            // 删除零宽断言
//            deleteZeroWidthAssertion(newTreeNode);
//            // 使用重写后的去首尾^$
//            newTreeNode.deleteCaretAndDollarSymbols();
//            // 新版重写空串
//            newTreeNode = removeBlankStr(newTreeNode);
//            // 重写反向引用后 删除NonCapturingGroupFlag ?:
//            newTreeNode.deleteNonCapturingGroupFlag();
//            List<String> translateRegexForLookAroundList = getTranslateRegexForAssertionsList(newTreeNode);
//
//            // 获取正例
//            String acceptString = getExampleByDkBricsAutomaton2(translateRegexForLookAroundList, 1);
//            if (acceptString == null) return null;
//
//            // 获取反例
//            List<String> translateRegexForLookAroundList2 = new ArrayList<>();
//            for (int i = 0; i < translateRegexForLookAroundList.size(); i++) {
//                translateRegexForLookAroundList2.add("～(" + translateRegexForLookAroundList.get(i) + ")");
//            }
//            String rejectString = getExampleByDkBricsAutomaton2(translateRegexForLookAroundList2, 1);
//            if (rejectString == null) return null;
//
//            return rejectString + acceptString + rejectString;
//        }
//    }
//
//    // 获取特殊后缀 正则中所有的counting结点取反
//    public static String getSpecialSuffixStringWithAllCountingNodes(TreeNode node) throws InterruptedException {
//        List<TreeNode> allCountingNode = node.getAllGeneralizedCountingWithMaxNumLeqOneNode();
//        StringBuilder stringBuilder = new StringBuilder();
//        for (TreeNode countingNode: allCountingNode) {
//            TreeNode child = countingNode.getChild(0);
//            child = getGroupSubNode(child);
//            List<String> regexList1 = new ArrayList<>();
//            regexList1.add("～(" + child.getData() + ")");
//            String example = getExampleByDkBricsAutomaton(regexList1);
//            if (example != null) stringBuilder.append(example);
//        }
//        return stringBuilder.toString();
//    }
    

    public static void main(String[] args) throws InterruptedException {
        String regex = "^(?:[^\\\\\\/#\\s]|\\\\[\\s\\S]|\\/(?!\\/\\/)|\\#(?!\\{)|\\s+(?:#(?!\\{).*)?)*";
        TreeNode treeNode = PatternNQUtils.getReDoSTree(regex, "java");
        treeNode = PatternNQUtils.getStandardizedReDoSTree(treeNode, "java");
        printTree(treeNode);
//        TreeNode child = treeNode.getChild(1).getChild(0).getChild(1);
//        String r4 = getR4(treeNode, child);
//        System.out.println(r4);
//        TreeNode r4Tree = createReDoSTree(r4);
//        printTree(r4Tree);
//        System.out.println(getSpecialSuffixStringWithCountingAndEndLine(treeNode, false)
//                .replace("\u0000", "\\u0000")
//                .replace("\n", "\\n")
//                );
//        System.out.println(
//                getSpecialSuffixStringWithAllCountingNodes(treeNode)
//                .replace("\t", "\\t")
//                .replace("\n", "\\n")
//                .replace("\u0000", "\\u0000")
//        );
//        System.out.println(getMatchString(treeNode));
//        System.out.println(getSpecialSuffixStringWithCountingAndEndLine(treeNode));
        System.out.println(getSpecialSuffixStringWithNegativeAssertNode(treeNode));
    }
}
