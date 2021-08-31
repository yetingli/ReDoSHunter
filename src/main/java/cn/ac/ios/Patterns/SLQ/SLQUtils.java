package cn.ac.ios.Patterns.SLQ;

import cn.ac.ios.TreeNode.TreeNode;
import edu.emory.mathcs.backport.java.util.Arrays;

import java.util.*;
import java.util.regex.Pattern;

import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.Utils.BracketUtils.isBracketsNode;
import static cn.ac.ios.Utils.Constant.*;
import static cn.ac.ios.Utils.FlagsUtils.isSpecialCharacter;
import static cn.ac.ios.Utils.RegexUtils.*;

public class SLQUtils {


    public static void main(String[] args) throws InterruptedException {
        String s = "(ab|a|b)";
        System.out.println(getMatchExamples(s));
    }

    /**
     * 判断节点是否是广义的带计数修饰节点  * + couting {m,n} m-n >1000
     *
     * @param node
     * @return
     */
    public static boolean isSLQCountingNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isSLQCountingNode(node.getChild(1));
        }
        if (node.getChildCount() == 2) {
            if (Pattern.matches(EXTENDED_COUNTING, node.getChild(1).getData())) {
                int left = getCountingFirstNum(node.getChild(1).getData());
                int right = getCountingSecondNum(node.getChild(1).getData());
                return right == -1 || right - left > 1000;
            }
        }
        return false;
    }

    /**
     * SLQ 条件三
     * 如 (a|b|c)+
     *
     * @param node
     * @return
     */
    public static boolean isSLQThreeCountingNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isSLQThreeCountingNode(node.getChild(1));
        } else {
            if (node.getChildCount() == 2 && Pattern.matches(EXTENDED_COUNTING, node.getChild(1).getData())) {
                TreeNode child = node.getChild(0);
                return isOrNode(child);
            }
        }
        return false;
    }

    /**
     * 获取正则生成串长度
     *
     * @param root
     * @return
     */
    public static int getMatchLength(TreeNode root) {
        if (isGroupNode(root)) {
            return getMatchLength(getGroupSubNode(root));
        } else if (isCountingNode(root)) {
            int left = getCountingFirstNum(getGroupSubNode(root).getChild(1).getData());
            int right = getCountingSecondNum(getGroupSubNode(root).getChild(1).getData());
            return getMatchLength(getGroupSubNode(root).getChild(0)) * (left == right ? left : left + 1);
        } else if (isStarNode(root)) {
            return getMatchLength(getGroupSubNode(root).getChild(0)) * 2;
        } else if (isOrNode(root)) {
            root = getGroupSubNode(root);
            int max = 0;
            for (int i = 0; i < root.getChildCount(); i++) {
                max = Math.max(max, getMatchLength(root.getChild(i)));
            }
            return max;
        } else if (root.isLeaf()) {
            String data = root.getData();
            if (data.length() == 1 && isSpecialCharacter(data.charAt(0))) {
                return 0;
            } else if ("\\b".equals(data) || "\\B".equals(data)) {
                return 0;
            } else {
                return 1;
            }
        } else if (isBracketsNode(root)) {
            return 1;
        } else if (isLookAroundNode(root)) {
            return 0;
        } else {
            int num = 0;
            for (int i = 0; i < root.getChildCount(); i++) {
                num += getMatchLength(root.getChild(i));
            }
            return num;
        }
    }

    /**
     * 获取正则的所有匹配穿
     *
     * @param regex
     * @return
     */
    public static ArrayList<String> getMatchExamples(String regex) {
        try {
            return getMatchExamples(createReDoSTree(regex));
        } catch (InterruptedException e) {
            return new ArrayList<>();
        }
    }


    /**
     * 获取正则的所有匹配串
     *
     * @param root
     * @return
     */
    public static ArrayList<String> getMatchExamples(TreeNode root) {
        if (isGroupNode(root)) {
            return getMatchExamples(getGroupSubNode(root));
        }
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            TreeNode node = stack.pop();
            if (isStarNode(node)) {
                ArrayList<String> list = getMatchExamples(getGroupSubNode(node).getChild(0));
                if (isEmptyOrNode(node) && !list.contains("")) {
                    list.add("");
                }
                arrayLists.add(list);
                arrayLists.add(list);
            } else if (isCountingNode(node)) {
                ArrayList<String> tempList = getMatchExamples(getGroupSubNode(node).getChild(0));
                int left = getCountingFirstNum(getGroupSubNode(node).getChild(1).getData());
                if (left == 0 && !tempList.contains("")) {
                    tempList.add("");
                }
                ArrayList<String> list = getRandomByLimit(tempList, getMatchLength(node));
                arrayLists.add(list);
            } else if (isOrNode(node)) {
                node = getGroupSubNode(node);
                ArrayList<String> tempList = new ArrayList<>();
                for (int i = 0; i < node.getChildCount(); i++) {
                    TreeNode child = node.getChild(i);
                    if (!child.getData().equals("|")) {
                        tempList.addAll(getMatchExamples(child));
                    }
                }
                ArrayList<String> list = getRandomByLimit(tempList, getMatchLength(node));
                arrayLists.add(list);
            } else if (isNonCapturingGroupNode(node) || isGroupNode(node)) {
                arrayLists.add(getMatchExamples(getGroupSubNode(node)));
            } else if (isLookAroundNode(root)) {
                continue;
            } else if (isBracketsNode(node) || isNegateNode(node)) {    // 修改
                ArrayList<String> temp = new ArrayList<>();
                Pattern pattern = Pattern.compile(node.getData());
                for(String c: dot_MATCH_CHARACTER_LIST) {
                    boolean isMatch = pattern.matcher(String.valueOf(c)).find();
                    if (isMatch) {
                        temp.add(c);
                    }
                }
                arrayLists.add(temp);
            }
//            else if (isBracketsNode(node)) {
//                node = getGroupSubNode(node);
//                arrayLists.add(new ArrayList<>(node.getLetterSet(false)));
//            }
            else if (node.isLeaf()) {
                String data = node.getData();
                if (("\\w").equals(data)) {
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(w_MATCH_CHARACTER_LIST));
                    arrayLists.add(list);
                } else if (("\\s").equals(data)) {
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(s_MATCH_CHARACTER_LIST));
                    arrayLists.add(list);
                } else if (("\\d").equals(data)) {
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(d_MATCH_CHARACTER_LIST));
                    arrayLists.add(list);
                } else if ((".").equals(data)) {
                    ArrayList<String> list = new ArrayList<>(node.getRoot().getLetterSet(false));
                    list.remove("\\n");
                    list.remove("\\r");
                    arrayLists.add(list);
                } else {
                    if (data.length() == 1 && isSpecialCharacter(data.charAt(0))) {
                        continue;
                    } else if ("\\b".equals(data) || "\\B".equals(data) || data.length() == 0) {
                        continue;
                    } else {
                        ArrayList<String> list = new ArrayList<>();
                        list.add(node.getData());
                        arrayLists.add(list);
                    }
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }

        return getAllStrings(arrayLists);

    }

    /**
     * 从一个字符串数组生成任意组合的字符串（字符串长度必须小于 matchLength）
     *
     * @param arrayLists
     * @param matchLength
     * @return
     */
    private static ArrayList<String> getRandomByLimit(ArrayList<String> arrayLists, int matchLength) {
        ArrayList<String> list = new ArrayList<>(arrayLists);
        while (true) {
            int tempCount = list.size();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < arrayLists.size(); j++) {
                    String newStr = list.get(i) + arrayLists.get(j);
                    if (newStr.length() <= matchLength) {
                        if (!list.contains(newStr)) {
                            list.add(newStr);
                        }
                    }
                    if (list.size() > 100) {
                        return list;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        return list;
                    }
                }
            }
            if (tempCount == list.size()) {
                break;
            }
        }
        return list;
    }

    /**
     * 从二维字符串数组生成任意组合的所有字符串
     *
     * @param arrayLists
     * @return
     */
    private static ArrayList<String> getAllStrings(ArrayList<ArrayList<String>> arrayLists) {
        if (arrayLists.size() == 0) {
            return new ArrayList<>();
        }
        ArrayList<String> list = new ArrayList<>(arrayLists.get(0));
        for (int i = 1; i < arrayLists.size(); i++) {
            ArrayList<String> temp = new ArrayList<>();
            for (String s : list) {
                for (int k = 0; k < arrayLists.get(i).size(); k++) {
                    String newStr = s + arrayLists.get(i).get(k);
                    temp.add(newStr);
                    if (temp.size() > 100) {
                        return list;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        return list;
                    }
                }
            }
            list.clear();
            list.addAll(temp);
        }
        return new ArrayList<>(new HashSet<>(list));
    }


    /**
     * 获取所有counting节点
     *
     * @param root
     */
    public static ArrayList<TreeNode> getAllCountingNode(TreeNode root) {
        ArrayList<TreeNode> nodes = new ArrayList<>();
        if (isSLQCountingNode(root)) {
            nodes.add(root);
            nodes.addAll(getAllCountingNode(getGroupSubNode(root).getChild(0)));
        } else {
            for (int i = 0; i < root.getChildCount(); i++) {
                TreeNode child = root.getChild(i);
                if (isSLQCountingNode(child)) {
                    nodes.add(child);
                    nodes.addAll(getAllCountingNode(getGroupSubNode(child).getChild(0)));
                } else {
                    nodes.addAll(getAllCountingNode(child));
                }
            }
        }
        return nodes;
    }

    /**
     * 获取当前节点的 前缀正则，后缀正则，如果当前节点位于or节点中，额外返回or节点的正则
     *
     * @param root
     * @param target
     * @return
     *
     */
    public static ArrayList<String> splitTreeByNode(TreeNode root, TreeNode target) {
        int leftParentheses = 0;
        int rightParentheses = 0;
        boolean flag = false;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        StringBuilder preRegex = new StringBuilder();
        StringBuilder suffixRegex = new StringBuilder();
        StringBuilder orRegex = new StringBuilder();
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (!isGroupNode(node) && isOrNode(node) && getParOrNode(target) == getGroupSubNode(node)) {
                orRegex.append(node.getData());
                node = getGroupSubNode(node);
                for (int i = 0; i < node.getChildCount(); i++) {
                    TreeNode child = node.getChild(i);
                    if (!child.getData().equals("|")) {
                        if (isAncestor(child, target)) {
                            node = child;
                            break;
                        }
                    }
                }
            }
            if (node == target || getGroupSubNode(node) == getGroupSubNode(target) || (isGeneralizedCountingNode(node) && getGroupSubNode(getGroupSubNode(node).getChild(0)) == getGroupSubNode(target))) {
                flag = true;
            } else {
                if (node.isLeaf() || isBracketsNode(node)) {
                    if (flag) {
                        suffixRegex.append(node.getData());
                        if (node.getData().equals("(")) {
                            rightParentheses++;
                        } else if (node.getData().equals(")")) {
                            rightParentheses--;
                        }
                    } else {
                        preRegex.append(node.getData());
                        if (node.getData().equals("(")) {
                            leftParentheses++;
                        } else if (node.getData().equals(")")) {
                            leftParentheses--;
                        }
                    }
                } else {
                    for (int i = node.getChildCount() - 1; i >= 0; i--) {
                        stack.push(node.getChild(i));
                    }
                }
            }
        }

        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < leftParentheses; i++) {
            preRegex.append(")");
        }
        String regex = preRegex.toString();
        try {
            regex = removeBlankStr(createReDoSTree(preRegex.toString()), true).getData();
        } catch (Exception e) {
            System.out.println(regex);
            e.printStackTrace();
        }
        arrayList.add(regex);
        for (int i = 0; i < Math.abs(rightParentheses); i++) {
            suffixRegex.insert(0, "(");
        }
        regex = suffixRegex.toString();
        try {
            regex = removeBlankStr(createReDoSTree(suffixRegex.toString()), true).getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        arrayList.add(regex);
        if (orRegex.toString().length() > 0) {
            arrayList.add(orRegex.toString());
        }
        return arrayList;

    }

    /**
     * 判断root是否是target的祖先节点，或者相等
     *
     * @param root
     * @param target
     * @return
     */
    private static boolean isAncestor(TreeNode root, TreeNode target) {
        while (target != null) {
            if (target == root) {
                return true;
            }
            target = target.getParent();
        }
        return false;
    }

}
