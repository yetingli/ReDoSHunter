package cn.ac.ios.Utils;

import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Patterns.NQ.PatternNQUtils;

import java.util.LinkedList;
import java.util.List;

import static cn.ac.ios.TreeNode.Utils.printTree;
import static cn.ac.ios.TreeNode.Utils.rewritePatten;
import static cn.ac.ios.Utils.RegexUtils.*;
import static cn.ac.ios.Utils.RegexUtils.isQuantifierNode;

public class SplitRegexUtils {
    // 获取从root开始到left结点之前结点的InitialChainIndex
    // 其他逻辑与getR0一致
    public static List<String> getR0InitialChainIndexList(TreeNode root, TreeNode left) {
        List<String> initialChainIndexList = new LinkedList<>();
        StringBuilder midRegex = new StringBuilder();
        boolean start;
        if (left == root) {
            return initialChainIndexList;
        }
        while (left.getParent() != root) {
            TreeNode parent = left.getParent();
            if (parent != null) {
                if (!isOrNode(parent)) {
                    start = false;
                    for (int i = parent.getChildCount() - 1; i >= 0; i--) {
                        if (start) {
                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(left)
                                    && parent.getChild(i) != left
                                    && !isQuantifierNode(parent.getChild(i))
                                    && !isSpecialNode(parent.getChild(i))
                                    && !(isGroupNode(parent) && (parent.getChild(i).isFirstChild() || parent.getChild(i).isLastChild())))
                            {
                                midRegex.insert(0, parent.getChild(i).getData());
                                initialChainIndexList.add(parent.getChild(i).getInitialChainIndex());
                            }
                        }
                        if (parent.getChild(i) == left) {
                            start = true;
                        }
                    }
                }
            }
            left = parent;
        }
        if (!isOrNode(root)) {
            start = false;
            for (int i = root.getChildCount() - 1; i >= 0; i--) {
                TreeNode child = root.getChild(i);
                if (start) {
                    if (!isQuantifierNode(child) &&
                            !isSpecialNode(child)) {
                        midRegex.insert(0, child.getData());
                        initialChainIndexList.add(child.getInitialChainIndex());
                    }
                }
                if (child == left) {
                    start = true;
                }
            }
        }

        // 如果是只有$ 则返回空
        String result = midRegex.toString();
//        return result.equals("$") ? "" : result;
        if (result.equals("$")) {
            return new LinkedList<>();
        } else {
            return initialChainIndexList;
        }
    }

    // 截取两个counting结点之间的InitialChainIndex 不包含两端 输入中left是左counting结点 right是右counting结点
    // 其他逻辑与getR2一致
    public static List<String> getR2InitialChainIndexList(TreeNode root, TreeNode left, TreeNode right) {
        List<String> initialChainIndexList = new LinkedList<>();

        TreeNode left2 = left;      // left的备份指针
        TreeNode right2 = right;    // right的备份指针

        StringBuilder midRegex = new StringBuilder();
        boolean start = false;
        if (left2 == root) {
            return initialChainIndexList;
        }
        while (left2.getParent() != root) {
            TreeNode parent = left2.getParent();
            if (parent != null) {
                if (!isOrNode(parent)) {
                    start = false;
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        if (start) {
                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(left)
                                    && parent.getChild(i) != left2
                                    && !parent.getChild(i).isNowNodeChildOrGrandchild(right)
                                    && parent.getChild(i) != right2
                                    && !isQuantifierNode(parent.getChild(i))
                                    && !isSpecialNode(parent.getChild(i))
                                    && !(isGroupNode(parent) && (parent.getChild(i).isFirstChild() || parent.getChild(i).isLastChild())))
                            {
                                midRegex.append(parent.getChild(i).getData());
                                initialChainIndexList.add(parent.getChild(i).getInitialChainIndex());
                            }
                        }
                        if (parent.getChild(i).isNowNodeChildOrGrandchild(right2) || parent.getChild(i) == right2) {
                            start = false;
                        }
                        if (parent.getChild(i) == left2) {
                            start = true;
                        }
                    }
                }
            }
            left2 = parent;
        }

        if (right2 == root) {
            return initialChainIndexList;
        }
        StringBuilder midRegex2 = new StringBuilder();
//        while (right2.getParent() != root) {
//            TreeNode parent = right2.getParent();
//            if (parent != null && !isGroupNode(parent)) {
//                if (!isOrNode(parent)) {
//                    for (int i = 0; i < parent.getChildCount(); i++) {
//                        if (parent.getChild(i) == right2 || parent.getChild(i).isNowNodeChildOrGrandchild(left2) || parent.getChild(i) == left2) {
//                            break;
//                        } else {
//                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(left2) && parent.getChild(i) != left2) {
//                                midRegex2.append(parent.getChild(i).getData());
//                            }
//                        }
//                    }
//                }
//            }
//            right2 = parent;
//        }
        while (right2.getParent() != root) {
            TreeNode parent = right2.getParent();
            if (parent != null) {
                if (!isOrNode(parent)) {
                    start = false;
                    for (int i = parent.getChildCount() - 1; i >= 0; i--) {
                        if (start) {
//                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(right2) &&
//                                    parent.getChild(i) != right2 &&
//                                    !isQuantifierNode(parent.getChild(i)))
                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(left)
                                    && parent.getChild(i) != left2
                                    && !parent.getChild(i).isNowNodeChildOrGrandchild(right)
                                    && parent.getChild(i) != right2
                                    && !isQuantifierNode(parent.getChild(i))
                                    && !isSpecialNode(parent.getChild(i))
                                    && !(isGroupNode(parent) && (parent.getChild(i).isFirstChild() || parent.getChild(i).isLastChild())))
                            {
                                midRegex2.insert(0, parent.getChild(i).getData());
                                initialChainIndexList.add(parent.getChild(i).getInitialChainIndex());
                            }
                        }
                        if (parent.getChild(i) == right2) {
                            start = true;
                        }
                    }
                }
            }
            right2 = parent;
        }

        StringBuilder midRegex3 = new StringBuilder();
        start = false;
        for (int i = 0; i < root.getChildCount(); i++) {
            TreeNode child = root.getChild(i);
            if (child == right2) {
                break;
            }
            if (start) {
                if (!isQuantifierNode(child) &&
                        !isSpecialNode(child))
                    midRegex3.append(child.getData());
                    initialChainIndexList.add(child.getInitialChainIndex());
            }
            if (child == left2) {
                start = true;
            }
        }

        // 如果是只有$ 则返回空
        String result = midRegex.toString() + midRegex3.toString() + midRegex2.toString();
//        return result.equals("$") ? "" : result;
        if (result.equals("$")) {
            return new LinkedList<>();
        } else {
            return initialChainIndexList;
        }
    }

    // 截取从left开始到root结点结尾的内容
    // 其他逻辑与getR4一致
    public static List<String> getR4InitialChainIndexList(TreeNode root, TreeNode right) {
        List<String> initialChainIndexList = new LinkedList<>();

        StringBuilder midRegex = new StringBuilder();
        boolean start;

        if (right == root) {
            return initialChainIndexList;
        };
        while (right.getParent() != root) {
            TreeNode parent = right.getParent();
            if (parent != null) {
                if (!isOrNode(parent)) {
                    start = false;
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        if (start) {
                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(right)
                                    && parent.getChild(i) != right
                                    && !isQuantifierNode(parent.getChild(i))
                                    && !isSpecialNode(parent.getChild(i))
                                    && !(isGroupNode(parent) && (parent.getChild(i).isFirstChild() || parent.getChild(i).isLastChild())))
                            {
                                midRegex.append(parent.getChild(i).getData());
                                initialChainIndexList.add(parent.getChild(i).getInitialChainIndex());
                            }
                        }
                        if (parent.getChild(i) == right) {
                            start = true;
                        }
                    }
                }
            }
            right = parent;
        }

        if (!isOrNode(root)) {
            start = false;
            for (int i = 0; i < root.getChildCount(); i++) {
                TreeNode child = root.getChild(i);
                if (start) {
                    if (!isQuantifierNode(child) &&
                            !isSpecialNode(child))
                        midRegex.append(child.getData());
                    initialChainIndexList.add(child.getInitialChainIndex());
                }
                if (child == right) {
                    start = true;
                }
            }
        }

        // 如果是只有$ 则返回空
        String result = midRegex.toString();
        if (result.equals("$")) {
            return new LinkedList<>();
        } else {
            return initialChainIndexList;
        }
    }





    // 截取从root开始到left结点之前的内容
    // 如果当前结点在或结点里面(或结点是当前结点的爹)，就跳过
    public static String getR0(TreeNode root, TreeNode left) {
        root = getGroupSubNode(root);
        StringBuilder midRegex = new StringBuilder();
        boolean start;
        if (left == root) {
            return "";
        }
        while (left.getParent() != null && left.getParent() != root) {
            TreeNode parent = left.getParent();
            if (parent != null && !isGroupNode(parent)) {
                if (!isOrNode(parent)) {
                    start = false;
                    for (int i = parent.getChildCount() - 1; i >= 0; i--) {
                        if (start) {
                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(left)
                                    && parent.getChild(i) != left
                                    && !isQuantifierNode(parent.getChild(i))
                                    && !isSpecialNode(parent.getChild(i))) {
                                    midRegex.insert(0, parent.getChild(i).getData());
                                }
                        }
                        if (parent.getChild(i) == left) {
                            start = true;
                        }
                    }
                }
            }
            left = parent;
        }
        if (!isOrNode(root)) {
            start = false;
            for (int i = root.getChildCount() - 1; i >= 0; i--) {
                TreeNode child = root.getChild(i);
                if (start) {
                    if (!isQuantifierNode(child) &&
                        !isSpecialNode(child)) {
                        midRegex.insert(0, child.getData());
                    }
                }
                if (child == left) {
                    start = true;
                }
            }
        }

        // 如果是只有$ 则返回空
        String result = midRegex.toString();
        return result.equals("$") ? "" : result;
    }

    // 判断是不是特殊结点,
    // (?!)的第一第二第三和最后一个结点
    // (?=)的第一第二第三和最后一个结点
    // (?<!)的第一第二第三第四和最后一个结点
    // (?<=)的第一第二第三第四和最后一个结点
    private static boolean isSpecialNode(TreeNode node){
        if (node.getParent() == null) return false;
        if (!isLookAroundNode(node.getParent())) return false;
        if (node.getParent().getData().startsWith("(?!") || node.getParent().getData().startsWith("(?=")) {
            if (node.isFirstChild() || node.isSecondChild() || node.isThirdChild() || node.isLastChild()) {
                return true;
            } else {
                return false;
            }
        } else if (node.getParent().getData().startsWith("(?<!") || node.getParent().getData().startsWith("(?<=")) {
            if (node.isFirstChild() || node.isSecondChild() || node.isThirdChild() || node.isForthChild() || node.isLastChild()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    // 截取两个counting结点之间的正则 不包含两端 输入中left是左counting结点 right是右counting结点
    // 如果当前结点在或结点里面(或结点是当前结点的爹)，就跳过
    public static String getR2(TreeNode root, TreeNode left, TreeNode right) {
        root = getGroupSubNode(root);
        TreeNode left2 = left;      // left的备份指针
        TreeNode right2 = right;    // right的备份指针

        StringBuilder midRegex = new StringBuilder();
        boolean start = false;
        if (left2 == root) return midRegex.toString();
        while (left2.getParent() != null && left2.getParent() != root) {
            TreeNode parent = left2.getParent();
            if (parent != null && !isGroupNode(parent)) {
                if (!isOrNode(parent)) {
                    start = false;
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        if (start) {
                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(left)
                                    && parent.getChild(i) != left2
                                    && !parent.getChild(i).isNowNodeChildOrGrandchild(right)
                                    && parent.getChild(i) != right2
                                    && !isQuantifierNode(parent.getChild(i))
                                    && !isSpecialNode(parent.getChild(i))) {
                                midRegex.append(parent.getChild(i).getData());
                            }
                        }
                        if (parent.getChild(i).isNowNodeChildOrGrandchild(right2) || parent.getChild(i) == right2) {
                            start = false;
                        }
                        if (parent.getChild(i) == left2) {
                            start = true;
                        }
                    }
                }
            }
            left2 = parent;
        }

        if (right2 == root) return midRegex.toString();
        StringBuilder midRegex2 = new StringBuilder();
//        while (right2.getParent() != root) {
//            TreeNode parent = right2.getParent();
//            if (parent != null && !isGroupNode(parent)) {
//                if (!isOrNode(parent)) {
//                    for (int i = 0; i < parent.getChildCount(); i++) {
//                        if (parent.getChild(i) == right2 || parent.getChild(i).isNowNodeChildOrGrandchild(left2) || parent.getChild(i) == left2) {
//                            break;
//                        } else {
//                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(left2) && parent.getChild(i) != left2) {
//                                midRegex2.append(parent.getChild(i).getData());
//                            }
//                        }
//                    }
//                }
//            }
//            right2 = parent;
//        }
        while (right2.getParent() != null && right2.getParent() != root) {
            TreeNode parent = right2.getParent();
            if (parent != null && !isGroupNode(parent)) {
                if (!isOrNode(parent)) {
                    start = false;
                    for (int i = parent.getChildCount() - 1; i >= 0; i--) {
                        if (start) {
//                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(right2) &&
//                                    parent.getChild(i) != right2 &&
//                                    !isQuantifierNode(parent.getChild(i)))
                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(left)
                                    && parent.getChild(i) != left2
                                    && !parent.getChild(i).isNowNodeChildOrGrandchild(right)
                                    && parent.getChild(i) != right2
                                    && !isQuantifierNode(parent.getChild(i))
                                    && !isSpecialNode(parent.getChild(i)))
                            {
                                midRegex2.insert(0, parent.getChild(i).getData());
                            }
                        }
                        if (parent.getChild(i) == right2) {
                            start = true;
                        }
                    }
                }
            }
            right2 = parent;
        }

        StringBuilder midRegex3 = new StringBuilder();
        start = false;
        for (int i = 0; i < root.getChildCount(); i++) {
            TreeNode child = root.getChild(i);
            if (child == right2) {
                break;
            }
            if (start) {
                if (!isQuantifierNode(child) &&
                    !isSpecialNode(child))
                    midRegex3.append(child.getData());
            }
            if (child == left2) {
                start = true;
            }
        }

        // 如果是只有$ 则返回空
        String result = midRegex.toString() + midRegex3.toString() + midRegex2.toString();
        return result.equals("$") ? "" : result;
    }

    public static String getR4(TreeNode root, TreeNode right) {
        return getR4(root, right, true);
    }

    // 截取从left开始到root结点结尾的内容
    // 如果当前结点在或结点里面(或结点是当前结点的爹)，就跳过
    // flag作用为是否保留$
    public static String getR4(TreeNode root, TreeNode right, boolean flag) {
        root = getGroupSubNode(root);
        StringBuilder midRegex = new StringBuilder();
        boolean start;

        if (right == root) return "";
        while (right.getParent() != null && right.getParent() != root) {
            TreeNode parent = right.getParent();
            if (parent != null && !isGroupNode(parent)) {
                if (!isOrNode(parent)) {
                    start = false;
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        if (start) {
                            if (!parent.getChild(i).isNowNodeChildOrGrandchild(right)
                                    && parent.getChild(i) != right
                                    && !isQuantifierNode(parent.getChild(i))
                                    && !isSpecialNode(parent.getChild(i))) {
                                midRegex.append(parent.getChild(i).getData());
                            }
                        }
                        if (parent.getChild(i) == right) {
                            start = true;
                        }
                    }
                }
            }
            right = parent;
        }

        if (!isOrNode(root)) {
            start = false;
            for (int i = 0; i < root.getChildCount(); i++) {
                TreeNode child = root.getChild(i);
                if (start) {
                    if (!isQuantifierNode(child) &&
                            !isSpecialNode(child))
                        midRegex.append(child.getData());
                }
                if (child == right) {
                    start = true;
                }
            }
        }

        // 如果是只有$ 则返回空
        String result = midRegex.toString();
        if (flag) {
            return result.equals("$") ? "" : result;
        } else {
            return result;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String regex = "(a*b)?(ac+)(d(ef*))?";
        regex = "(a[^c]+)*(a)(d[^d]c*)*";
        regex = "(a(b[c]+)|d)([e]+)f|g";
        regex = "(?!.*\\n)([^:]*)";
        regex = "(a)";
//        TreeNode treeNode = PatternNQUtils.getReDoSTree(regex, "java");
//        printTree(treeNode);
//        TreeNode child1 = treeNode.getChild(0).getChild(0).getChild(1).getChild(0);
//        TreeNode child2 = treeNode.getChild(2).getChild(0).getChild(1).getChild(1).getChild(1).getChild(1);
//        TreeNode child1 = treeNode.getChild(0);
//        TreeNode child2 = treeNode.getChild(2).getChild(0).getChild(1).getChild(2);
//        TreeNode child1 = treeNode.getChild(0).getChild(0).getChild(1).getChild(0).getChild(1).getChild(1).getChild(1);
//        TreeNode child2 = treeNode.getChild(0).getChild(1).getChild(1);
//        TreeNode child1 = treeNode.getChild(0).getChild(3).getChild(0);
//        TreeNode child2 = treeNode.getChild(1).getChild(1);
//        TreeNode child1 = treeNode.getChild(1);
//        System.out.println(child1.getData() + " " + child2.getData());
//        System.out.println(getR2(treeNode, child1, child2));
//        System.out.println(getR0(treeNode, child1));
    }
}
