package cn.ac.ios.Utils;

import cn.ac.ios.TreeNode.TreeNode;

import java.util.*;

import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.TreeNode.Utils.printTree;
import static cn.ac.ios.Utils.BracketUtils.*;
import static cn.ac.ios.Utils.Constant.*;
import static cn.ac.ios.Utils.RegexUtils.*;

/**
 * @author pqc
 */
public class NegateUtils {

    /**
     * 获取中括号取补节点的反向集合
     * 去掉反斜杠
     *
     * @param treeNode
     * @return
     */
    public static Set<String> getSetFromNegateNode(TreeNode treeNode) {
        Set<String> set = new LinkedHashSet<>();
        for (int i = 2; i < treeNode.getChildCount() - 1; i++) {
            TreeNode child = treeNode.getChild(i);
            String str = child.getData();
            if ("\\w".equals(str)) {
                set.addAll(Arrays.asList(w_MATCH_CHARACTER_LIST));
            } else if ("\\d".equals(str)) {
                set.addAll(Arrays.asList(d_MATCH_CHARACTER_LIST));
            } else if ("\\s".equals(str)) {
                set.addAll(Arrays.asList(s_MATCH_CHARACTER_LIST));
            } else if (isCollectionSymbol(str)) {
                long start = str.charAt(0);
                long end = str.charAt(2);
                for (; start <= end; start++) {
                    char c = (char) start;
                    set.add(String.valueOf(c));
                }
            } else if (child.isLeaf()) {
                set.add(str);
            } else {
                // 处理特殊的集合 如 a-\z
                if (str.length() > 3 && str.contains("-") && str.contains("\\")) {
                    str = str.replace("\\\\", "special_slash_ios_ac_cn");
                    str = str.replace("\\", "");
                    str = str.replace("special_slash_ios_ac_cn", "\\");
                    if (str.length() == 3) {
                        char start = str.charAt(0);
                        char end = str.charAt(2);
                        for (; start <= end; start++) {
                            set.add(String.valueOf(start));
                        }
                    }
                }

            }
        }
        Set<String> newSet = new LinkedHashSet<>();
        for (String str : set) {
            if (str.length() == 1 && isSpecialCharacterBracket(str.charAt(0))) {
                newSet.add("\\" + str);
            } else {
                newSet.add(str);
            }
        }
        return newSet;
    }

    /**
     * 获取最后一个Negate node
     *
     * @param treeNode
     * @return
     */
    @Deprecated
    public static TreeNode getLastNegateNode(TreeNode treeNode) {
        if (!treeNode.getData().contains("\\h") && !treeNode.getData().contains("[^") && !treeNode.getData().contains("\\W") && !treeNode.getData().contains("\\S") && !treeNode.getData().contains("\\D") && !treeNode.getData().contains("\\b")) {
            return null;
        }
        while (treeNode != null && !treeNode.getChildList().isEmpty()) {
            if (isGeneralizedCountingNode(treeNode)) {
                treeNode = getGroupSubNode(treeNode);
                treeNode = treeNode.getChild(0);
            }
            if (isNegateNode(treeNode) || isNegateSingleNode(treeNode) || isDotNode(treeNode)) {
                return getGroupSubNode(treeNode);
            }
            treeNode = treeNode.getLastChild();
            if (getAssertType(treeNode) != AssertType.NO_ASSERT && getAssertType(treeNode)  != null) {
                treeNode = treeNode.getParent().getChild(treeNode.getParent().getChildCount() - 2);
            }
        }
        return treeNode;
    }

    /**
     * 获取最后一个Negate node 的非匹配字符
     *
     * @param treeNode
     * @return
     */
    @Deprecated
    public static String getSuffixByNegateNode(TreeNode treeNode) {
        treeNode = getLastNegateNode(treeNode);
        if (treeNode == null) {
            return "";
        }
        if (isNegateSingleNode(treeNode)) {
            return "!";
        } else if (isDotNode(treeNode)) {
            return "\\n";
        } else if (isNegateNode(treeNode)) {
            Set<String> letterSet = getSetFromNegateNode(treeNode);
            String ch = "";
            for (String s : letterSet) {
                if (s.startsWith("\\") && s.length() == 2 && (!"\\t".equals(s) && !"\\n".equals(s) && !"\\r".equals(s) && !"\\f".equals(s) && !"\\v".equals(s))) {
                    s = s.substring(1, 2);
                }
                if (s.length() == 1) {
                    char c = s.charAt(0);
                    if (Character.isLowerCase(c) || Character.isUpperCase(c) || Character.isDigit(c)) {
                        ch = String.valueOf(c);
                        return ch;
                    } else if (c >= 41) {
                        ch = String.valueOf(c);
                    }
                }
                if ("".equals(ch)) {
                    ch = s;
                }
            }
            return ch;
        } else if (treeNode.isLeaf() && treeNode.getData().length() <= 2) {
            return treeNode.getData().equals("!") ? "a" : "!";
        }
        return "!@ \n_1";
    }

    /**
     * 将中括号的取反转为可接受字符串
     * 替换 \h 为\s
     *
     * @param treeNode
     * @param level    化简等级
     * @return
     */
    public static void removeNegateSymbol(TreeNode treeNode, SimplyLevel level) throws InterruptedException {
        if (!treeNode.getData().contains("[^") && !treeNode.getData().contains("\\W") && !treeNode.getData().contains("\\S") && !treeNode.getData().contains("\\D")) {
            return;
        }
        Set<String> letterSet = treeNode.getLetterSet(true);
        Stack<TreeNode> stack = new Stack<>();
        stack.add(treeNode);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            node = getGroupSubNode(node);
            if (isNegateNode(node)) {
                Set<String> set = getSetFromNegateNode(node);
                Set<String> temp = new LinkedHashSet<>(letterSet);
                // 加上特殊符号
                temp.add("!");
                // 处理去补中括号里的单独去补符号如[^\W]
                if (set.contains("\\W")) {
                    temp.clear();
                    temp.addAll(COMPLEMENTARY_SET_SYMBOL.get("\\W"));
                }
                if (set.contains("\\D")) {
                    temp.clear();
                    temp.addAll(COMPLEMENTARY_SET_SYMBOL.get("\\D"));
                }
                if (set.contains("\\S")) {
                    temp.clear();
                    temp.addAll(COMPLEMENTARY_SET_SYMBOL.get("\\S"));
                }
                temp.removeAll(set);
                temp = simplifyLetters(temp, level);
                StringBuilder builder = new StringBuilder();
                for (String str : temp) {
                    if (str.equals("\\w") || str.equals("\\d") || str.equals("\\s") || str.equals("\\r") || str.equals("\\t") || str.equals("\\f") || str.equals("\\n")) {
                        builder.append(str);
                        continue;
                    }
                    //特殊字符加入中括号内 可以去掉 \
                    if (str.length() == 2 && str.startsWith("\\") && !"\\\\".equals(str)) {
                        str = str.substring(1);
                    }
                    // 这几个特殊字符必须加上 \ ，否则会改变中括号内部含义
                    if ("-".equals(str) || "]".equals(str) || "[".equals(str)) {
                        str = "\\" + str;
                    }
                    builder.append(str);
                }
                if ("".equals(builder.toString())) {
                    node.updateTreeByModifyNode("[◆]",true);
                } else {
                    node.updateTreeByModifyNode("[" + builder.toString() + "]",true);
                }
            } else if (isNegateSingleNode(node) && !isInBrackets(node) && !isInNegateNode(node)) {
                Set<String> set = new HashSet<>(COMPLEMENTARY_SET_SYMBOL.get(node.getData()));
                Set<String> temp = new LinkedHashSet<>(letterSet);
                // 加上特殊符号
                temp.add("!");
                temp.removeAll(set);
                temp = simplifyLetters(temp, level);
                StringBuilder builder = new StringBuilder();
                for (String str : temp) {
                    if (str.equals("\\w") || str.equals("\\d") || str.equals("\\s") || str.equals("\\r") || str.equals("\\t") || str.equals("\\f") || str.equals("\\n")) {
                        builder.append(str);
                        continue;
                    }
                    //特殊字符加入中括号内 可以去掉 \
                    if (str.length() == 2 && str.startsWith("\\") && !"\\\\".equals(str)) {
                        str = str.substring(1);
                    }
                    // 这几个特殊字符必须加上 \ ，否则会改变中括号内部含义
                    if ("-".equals(str) || "]".equals(str) || "[".equals(str)) {
                        str = "\\" + str;
                    }
                    builder.append(str);
                }
                if (isInBrackets(node)) {
                    node.updateTreeByModifyNode(builder.toString(),true);
                } else {
                    if ("".equals(builder.toString())) {
                        node.updateTreeByModifyNode("[◆]",true);
                    } else {
                        node.updateTreeByModifyNode("[" + builder.toString() + "]",true);
                    }
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.add(node.getChild(i));
                }
            }
        }
    }


    /**
     * 去除无意义括号
     *
     */
    @Deprecated
    public static TreeNode removeGroup(TreeNode treeNode) throws InterruptedException {
        if (isGroupNode(treeNode)) {
            return removeGroup(getGroupSubNode(treeNode));
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(treeNode);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.isChildReferencesNode()) {
                continue;
            }
            if (isGroupNode(node)) {
                TreeNode subNode = getGroupSubNode(node);
                if (isGeneralizedCountingNode(node.getParent()) && isGeneralizedCountingNode(subNode)) {
                    if (!("(" + subNode.getData() + ")").equals(node.getData())) {
                        node.updateTreeByModifyNode("(" + subNode.getData() + ")");
                    }
                } else if (isOrNode(subNode)) {
                    if (!("(" + subNode.getData() + ")").equals(node.getData())) {
                        node.updateTreeByModifyNode("(" + subNode.getData() + ")");
                    }
                } else if (isGeneralizedCountingNode(node.getParent()) && isGeneralizedCountingNode(subNode.getLastChild())) {
                    if (!("(" + subNode.getData() + ")").equals(node.getData())) {
                        node.updateTreeByModifyNode("(" + subNode.getData() + ")");
                    }
                } else if (isGeneralizedCountingNode(node.getParent()) && subNode.getChildCount() >= 2) {
                    if (!("(" + subNode.getData() + ")").equals(node.getData())) {
                        node.updateTreeByModifyNode("(" + subNode.getData() + ")");
                    }
                } else {
                    node.updateTreeByModifyNode(subNode.getData());
                }
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        return treeNode;
    }


    public static TreeNode removeGroup2(TreeNode treeNode) throws InterruptedException {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(treeNode);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isGroupNode(node)) {
                TreeNode cur = node;
                while (isGroupNode(cur)) {
                    cur = cur.getChild(1);
                }
                if (isBracketsNode(cur)) {
                    node.updateTreeByModifyNode(cur.getData());
                } else if (!isGeneralizedCountingNode(cur) && !isOrNode(cur) && (cur.getChildCount() == 1 || cur.isLeaf())) {
                    node.updateTreeByModifyNode(cur.getData());
                } else if (isOrNode(cur) && isGroupNode(cur.getParent().getParent())) { // ((a|b))+ -> (a|b)+
                    node.updateTreeByModifyNode(cur.getParent().getData());
//                } else if (isOrNode(cur) && isInOrNode(cur.getParent())) { ((a|b)|c)+ -> (a|b|c)+
//                    node.updateTreeByModifyNode(cur.getParent().getParent().getData());
                } else if (!cur.getData().equals(node.getData())) {
                    if (isGeneralizedCountingNode(node.getParent()) || (!isGroupNode(cur.getParent()) && cur.getParent().getChildCount() != 1)) {
                        node.updateTreeByModifyNode("(" + cur.getData() + ")");
                    } else {
                        node.updateTreeByModifyNode(cur.getData());
                    }
                }
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        while (isGroupNode(treeNode)) {
            treeNode = treeNode.getChild(1);
        }
        return treeNode;
    }

    /**
     * 将可能的集合节点转为.
     *
     * @param treeNode
     * @return
     */
    public static TreeNode refactorToDot(TreeNode treeNode) throws InterruptedException {

        Set<String> alphaSet = treeNode.getLetterSetMustHas();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(treeNode);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isBracketsNode(node) || "\\w".equals(node.getData()) || "\\d".equals(node.getData())) {
                Set<String> subSet = node.getLetterSet(false);
                if (subSet.containsAll(alphaSet)) {
                    node.setData(".");
                    node.getChildList().clear();
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }

        String regex = treeNode.getLeafsData();
        return createReDoSTree(regex);

    }


    /**
     * 重构预查找节点 lookAround
     * waring: ^((?!Bauxite|Iridium))+(\w)+(\s)(dust)+$ 会报错
     * (?m:((?<=").*?$)|((?i:\*)([\t ].*?)?$)) 会报错
     * (?xm:^(?:.*Product\s+Version:\s+)(?<version>\d+\.\d+\.\d+)(?:.*status:\s+(?<status>\d+)).*$) 会报错
     *
     * @param treeNode
     * @return
     * @throws InterruptedException
     */
    public static TreeNode refactorAssertPattern(TreeNode treeNode) throws InterruptedException {
        if (PATTERN_ASSERT_PATTERN.matcher(treeNode.getData()).find()) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(treeNode);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (isGeneralizedCountingNode(node)) {
                    TreeNode child = getGroupSubNode(node.getChild(0));
                    if (isLookAroundNode(child)) {
                        AssertType type = getAssertType(child);
                        String data = getGroupSubNode(child).getChildCount() == 5 ? getGroupSubNode(child).getChild(3).getData() : getGroupSubNode(child).getChild(4).getData();
                        node.updateTreeByModifyNode("(?:" + data + ")?");
                        node.setAssertType(type);
                    }
                } else if (isLookAroundNode(node)) {
                    AssertType type = getAssertType(node);
                    String data = getGroupSubNode(node).getChildCount() == 5 ? getGroupSubNode(node).getChild(3).getData() : getGroupSubNode(node).getChild(4).getData();
                    node.updateTreeByModifyNode("(?:" + data + ")?");
                    node.setAssertType(type);
                }
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
        return treeNode;
    }


    /**
     * 为 零宽断言设置type
     *
     * @param treeNode
     * @return
     */
    public static void setZeroWidthAssertion(TreeNode treeNode) {
        if (PATTERN_ASSERT_PATTERN.matcher(treeNode.getData()).find()) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(treeNode);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (isGeneralizedCountingNode(node)) {
                    TreeNode child = getGroupSubNode(node.getChild(0));
                    if (isZeroWidthAssertionNode(child)) {
                        AssertType type = getAssertType(child);
                        node.setAssertType(type);
                    }
                } else if (isZeroWidthAssertionNode(node)) {
                    AssertType type = getAssertType(node);
                    node.setAssertType(type);
                }
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
    }

    /**
     * 删除零宽断言
     *
     * @param treeNode
     * @return
     */
    public static void deleteZeroWidthAssertion(TreeNode treeNode) throws InterruptedException {
        if (PATTERN_ASSERT_PATTERN.matcher(treeNode.getData()).find()) {
            Stack<TreeNode> stack = new Stack<>();
            stack.add(treeNode);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                if (isGeneralizedCountingNode(node)) {
                    TreeNode child = getGroupSubNode(node.getChild(0));
                    if (isZeroWidthAssertionNode(child)) {
//                        node.getParent().deleteChild(node);
                        // fix bug, 如果regex = (?=a) 则不存在parent
                        if (node.isRoot()) {
                            node.updateTreeByModifyNode("");
                        } else {
                            node.getParent().deleteChild(node);
                        }
                    }
                } else if (isZeroWidthAssertionNode(node)) {
//                    node.getParent().deleteChild(node);
                    // fix bug, 如果regex = (?=a) 则不存在parent
                    if (node.isRoot()) {
                        node.updateTreeByModifyNode("");
                    } else {
                        node.getParent().deleteChild(node);
                    }
                }
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String regex = "(?<!\\\\)?";
        TreeNode treeNode = createReDoSTree(regex);
        printTree(treeNode);
        deleteZeroWidthAssertion(treeNode);
        printTree(treeNode);
    }
}
