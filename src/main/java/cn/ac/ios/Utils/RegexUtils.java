package cn.ac.ios.Utils;

import cn.ac.ios.TreeNode.TreeNode;

import java.util.*;
import java.util.regex.Pattern;

import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.TreeNode.Utils.printTree;
import static cn.ac.ios.Utils.BracketUtils.*;
import static cn.ac.ios.Utils.BracketUtils.isCollectionSymbol;
import static cn.ac.ios.Utils.Constant.*;
import static cn.ac.ios.Utils.FlagsUtils.isSpecialCharacter;

/**
 * @author pqc
 */
public class RegexUtils {

    public static final String NO_LETTER_MATCH = "IOS_AC_CN_NO_LETTER_MATCH";

    /**
     * 将集合化简
     *
     * @param set   a,b,c,d ... x,y,z,\n,\r
     * @param level 化简等级
     * @return a-z,\n,\r
     */
    private static Set<String> simplifyLetters(Set<String> set, SimplyLevel level) {
        if (level == SimplyLevel.LOW) {
            return set;
        }
        Set<String> newSet = new LinkedHashSet<>();
        Set<Character> characterSet = new LinkedHashSet<>();
        for (String str : set) {
            if (str.length() == 1 && Character.isLetterOrDigit(str.charAt(0))) {
                characterSet.add(str.charAt(0));
            } else {
                newSet.add(str);
            }
        }
        newSet.addAll(getLettersSimply(characterSet));
        if (level == SimplyLevel.HIGH) {
            if (newSet.containsAll(Arrays.asList("a-z", "A-Z", "0-9", "_"))) {
                newSet.removeAll(Arrays.asList("a-z", "A-Z", "0-9", "_"));
                newSet.add("\\w");
            }
            if (newSet.contains("0-9")) {
                newSet.remove("0-9");
                newSet.add("\\d");
            }
            if (newSet.containsAll(Arrays.asList("\\r", "\\n", "\\t", "\\f", " "))) {
                newSet.removeAll(Arrays.asList("\\r", "\\n", "\\t", "\\f", " "));
                newSet.add("\\s");
            }
            return newSet;
        }
        return newSet;
    }

    /**
     * 判断当前节点是否为捕获括号组，排除括号集合
     * 支持(abc) 不支持(?:abc) (?!) (?=) (?<!) (?<=)
     *
     * @param treeNode
     * @return
     */
    public static boolean isGroupNode(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.startsWith("(") && data.endsWith(")")) {
            List<TreeNode> list = treeNode.getChildList();
            return list.size() == 3 && "(".equals(list.get(0).getData()) && ")".equals(list.get(2).getData());
        }
        return false;
    }

    // 判断当前结点是否为广义的capturingGroup (a)或(?<b>a)或(?'b'a)或(?P<b>a)
    public static boolean isGeneralizedCapturingGroupNode(TreeNode treeNode) {
        return isGroupNode(treeNode) || isNamedCapturingGroupNode(treeNode);
    }

    // 获取当前结点的groupName 若有则返回groupName, 否则返回"" 需要先调用isGeneralizedCapturingGroupNode
    public static String getGroupNameForCapturingGroupNode(TreeNode treeNode) {
        if (isGroupNode(treeNode)) return "";
        if (isNamedCapturingGroupNode1(treeNode)) return treeNode.getChild(4).getData();
        if (isNamedCapturingGroupNode2(treeNode)) return treeNode.getChild(3).getData();
        if (isNamedCapturingGroupNode3(treeNode)) return treeNode.getChild(3).getData();
        return "";
    }

    // 判断当前结点是否是counting结点中的quantifier 例如(a)+中的+ a{2,3}中的{2,3}
    public static boolean isQuantifierNode(TreeNode treeNode) {
        String data = treeNode.getData();
        if (!Pattern.matches(ALL_COUNTING, data)) return false;
        if (treeNode.getParent() == null) return false;
        if (treeNode.getParent().getChildCount() != 2) return false;
        if (treeNode.getNextNode() != null) return false;
        if (treeNode.getPreviousNode() == null) return false;
        return true;
    }

    // 判断当前结点是否在Non-CapturingGroup结点中
    public static boolean isInNonCapturingGroupNode(TreeNode treeNode) {
        TreeNode temp = treeNode;
        while (!temp.isRoot()) {
            temp = temp.getParent();
            if (isNonCapturingGroupNode(temp)) {
                return true;
            }
        }
        return false;
    }

    // 判断当前结点是否为Non-CapturingGroup结点 例如(?:a)
    public static boolean isNonCapturingGroupNode(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.startsWith("(?:") && data.endsWith(")")) {
            List<TreeNode> list = treeNode.getChildList();
            return list.size() >= 4 && "(".equals(list.get(0).getData()) && "?".equals(list.get(1).getData())
                    && ":".equals(list.get(2).getData()) && ")".equals(list.get(list.size() - 1).getData());
        }
        return false;
    }

    // 判断当前节点是否为带groupName的capturingGroupName结点 例如(?<foo>x)\k'foo'\k<foo>(?P=foo)\1 中的(?<foo>x)
    // CAPTURING
    //         (?<name>...)    named capturing group (Perl)
    public static boolean isNamedCapturingGroupNode1(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.startsWith("(?P<") && data.endsWith(")")) {
            List<TreeNode> list = treeNode.getChildList();
            return list.size() == 8 &&
                    "(".equals(list.get(0).getData()) &&
                    "?".equals(list.get(1).getData()) &&
                    "P".equals(list.get(2).getData()) &&
                    "<".equals(list.get(3).getData()) &&
                    ">".equals(list.get(5).getData()) &&
                    ")".equals(list.get(7).getData());
        }
        return false;
    }

    //         (?'name'...)    named capturing group (Perl)
    public static boolean isNamedCapturingGroupNode2(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.startsWith("(?<") && data.endsWith(")")) {
            List<TreeNode> list = treeNode.getChildList();
            return list.size() == 7 &&
                    "(".equals(list.get(0).getData()) &&
                    "?".equals(list.get(1).getData()) &&
                    "<".equals(list.get(2).getData()) &&
                    ">".equals(list.get(4).getData()) &&
                    ")".equals(list.get(6).getData());
        }
        return false;
    }

    //         (?P<name>...)   named capturing group (Python)
    public static boolean isNamedCapturingGroupNode3(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.startsWith("(?'") && data.endsWith(")")) {
            List<TreeNode> list = treeNode.getChildList();
            return list.size() == 7 &&
                    "(".equals(list.get(0).getData()) &&
                    "?".equals(list.get(1).getData()) &&
                    "'".equals(list.get(2).getData()) &&
                    "'".equals(list.get(4).getData()) &&
                    ")".equals(list.get(6).getData());

        }
        return false;
    }

    public static boolean isNamedCapturingGroupNode(TreeNode treeNode) {
        return isNamedCapturingGroupNode1(treeNode) ||
                isNamedCapturingGroupNode2(treeNode) ||
                isNamedCapturingGroupNode3(treeNode);
    }

    // 判断当前结点是否为奇奇怪怪的反向引用 即除了\1\2这种写法的
    // BACKREFERENCES
    //         \n              reference by number (can be ambiguous)
    //         \gn             reference by number
    public static boolean isUnusualBackReferencesNode1(TreeNode treeNode) {
        String data = treeNode.getData();
        if (PATTERN_BACKSLASH_G_N_REGEX_1.matcher(data).matches()) {    // \gn
            return true;
        }
        return false;
    }

    //         \g{n}           reference by number
    public static boolean isUnusualBackReferencesNode2(TreeNode treeNode) {
        String data = treeNode.getData();
        if (PATTERN_BACKSLASH_G_N_REGEX_2.matcher(data).matches()) { //  \g{n}
            return true;
        }
        return false;
    }

    //         \g{-n}          relative reference by number
    public static boolean isUnusualBackReferencesNode3(TreeNode treeNode) {
        String data = treeNode.getData();
        if (PATTERN_BACKSLASH_G_N_REGEX_3.matcher(data).matches()) { // \g{-n}
            return true;
        }
        return false;
    }

    //         \k<name>        reference by name (Perl)
    public static boolean isUnusualBackReferencesNode4(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.startsWith("\\k<") && data.endsWith(">")) { // \k<name>
            List<TreeNode> list = treeNode.getChildList();
            return list.size() == 4 &&
                    "\\k".equals(list.get(0).getData()) &&
                    "<".equals(list.get(1).getData()) &&
                    ">".equals(list.get(3).getData());
        }
        return false;
    }

    //         \k'name'        reference by name (Perl)
    public static boolean isUnusualBackReferencesNode5(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.startsWith("\\k'") && data.endsWith("'")) { // \k'name'
            List<TreeNode> list = treeNode.getChildList();
            return list.size() == 4 &&
                    "\\k".equals(list.get(0).getData()) &&
                    "'".equals(list.get(1).getData()) &&
                    "'".equals(list.get(3).getData());
        }
        return false;
    }

    //         \g{name}        reference by name (Perl)
    public static boolean isUnusualBackReferencesNode6(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.startsWith("\\g{") && data.endsWith("}")) { // \g{name}
            List<TreeNode> list = treeNode.getChildList();
            return list.size() == 4 &&
                    "\\g".equals(list.get(0).getData()) &&
                    "{".equals(list.get(1).getData()) &&
                    "}".equals(list.get(3).getData());
        }
        return false;
    }

    //         \k{name}        reference by name (.NET)
    public static boolean isUnusualBackReferencesNode7(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.startsWith("\\k{") && data.endsWith("}")) { // \k{name}
            List<TreeNode> list = treeNode.getChildList();
            return list.size() == 4 &&
                    "\\k".equals(list.get(0).getData()) &&
                    "{".equals(list.get(1).getData()) &&
                    "}".equals(list.get(3).getData());
        }
        return false;
    }

    //         (?P=name)       reference by name (Python)
    public static boolean isUnusualBackReferencesNode8(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.startsWith("(?P=") && data.endsWith(")")) {  // (?P=name)
            List<TreeNode> list = treeNode.getChildList();
            return list.size() == 6 &&
                    "(".equals(list.get(0).getData()) &&
                    "?".equals(list.get(1).getData()) &&
                    "P".equals(list.get(2).getData()) &&
                    "=".equals(list.get(3).getData()) &&
                    ")".equals(list.get(5).getData());
        }
        return false;
    }

    public static boolean isUnusualBackReferencesNode(TreeNode treeNode) {
        return isUnusualBackReferencesNode1(treeNode) ||
                isUnusualBackReferencesNode2(treeNode) ||
                isUnusualBackReferencesNode3(treeNode) ||
                isUnusualBackReferencesNode4(treeNode) ||
                isUnusualBackReferencesNode5(treeNode) ||
                isUnusualBackReferencesNode6(treeNode) ||
                isUnusualBackReferencesNode7(treeNode) ||
                isUnusualBackReferencesNode8(treeNode);
    }


    // 查找两个结点的最近公共父节点
    public static TreeNode getNearestParent(TreeNode child1, TreeNode child2) {
        if (child1 == null || child2 == null) return null;
        if (child1.isNowNodeChildOrGrandchild(child2)) return child2;
        if (child2.isNowNodeChildOrGrandchild(child1)) return child1;
        List<TreeNode> parentList1 = new ArrayList<>();
        TreeNode parent1 = child1.getParent();
        while (parent1 != null) {
            parentList1.add(parent1);
            parent1 = parent1.getParent();
        }
        TreeNode parent2 = child2.getParent();
        while (parent2 != null) {
            if (parentList1.contains(parent2)) return parent2;
            parent2 = parent2.getParent();
        }
        return null;
    }

    /**
     * 判断或节点是否可以接受空串
     * waring 只支持或节点
     *
     * @param node
     * @return
     */
    public static boolean isEmptyOrNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isEmptyOrNode(node.getChild(1));
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            TreeNode treeNode = node.getChild(i);
            if (!treeNode.getData().equals("|")) {
                if (treeNode.getData().equals("()") || treeNode.getData().equals("(())") || treeNode.getData().equals("[]")) {
                    return true;
                } else if (isCanEmptyNode(treeNode)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断节点是否是只能是空节点，删除空括号()
     *
     * @param node
     * @return
     */
    private static boolean isOnlyEmptyNode(TreeNode node) {

        if (isGroupNode(node)) {
            return isOnlyEmptyNode(getGroupSubNode(node));
        }
        if (isGeneralizedCountingNode(node)) {
            node = getGroupSubNode(node);
            return isOnlyEmptyNode(node.getChild(0));
        }
        if ("".equals(node.getData())) {
            return true;
        } else if (node.getChildCount() >= 2) {
            boolean flag = true;
            for (int i = 0; i < node.getChildCount(); i++) {
                flag = isOnlyEmptyNode(node.getChild(i));
                if (!flag) {
                    return false;
                }
            }
            return flag;
        }
        return false;
    }

    /**
     * 判断节点是否是可以为null的counting节点
     * 如 (abc){0,2} a? a*
     *
     * @param node
     * @return
     */
    public static boolean isZeroCountingNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isZeroCountingNode(node.getChild(1));
        } else {
            TreeNode last = node.getLastChild();
            if ((node.getChildCount() == 2 && (last != null && (last.getData().equals("*") || last.getData().equals("?"))))) {
                return true;
            }
            if ((node.getChildCount() == 2 && (last != null && last.getData().equals("+")))) {
                return isZeroCountingNode(node.getChild(0));
            }
            if (isCountingNode(node)) {
                return getCountingFirstNum(node.getChild(1).getData()) == 0;
            }
        }
        return false;
    }

    // 判断结点是否为空结点 即该结点的parent为空 data为空 孩子为空
     public static boolean isNullNode(TreeNode node) {
        return node.isRoot() && node.getData().equals("") && node.getChildCount() == 0;
     }

    /**
     * 判断节点是否是可以接受空串
     * 如 (abc){0,2} a? a* a?(a|c?)
     *
     * @param node
     * @return
     */
    public static boolean isCanEmptyNode(TreeNode node) {
        // fix bug 新增对空结点的判断
        if (isNullNode(node)) {
            return true;
        }
        if (isGroupNode(node)) {
            return isCanEmptyNode(node.getChild(1));
        } else if (isGeneralizedCountingNode(node)) {
            if (isZeroCountingNode(node)) {
                return true;
            } else {
                return isCanEmptyNode(getGroupSubNode(node).getChild(0));
            }
        } else if (isOrNode(node)) {
            return isEmptyOrNode(node);
        } else if (isZeroWidthAssertionNode(node)) {
            return true;
        } else if (node.isLeaf()) {
            if (isQuantifierNode(node)) {
                return true;
            } else if (isSpecialCharacter(node)) {
                return true;
            }
            return false;
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                if (!isCanEmptyNode(node.getChild(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 判断节点是否在或节点内部
     *
     * @param node
     * @return
     */
    public static boolean isInOrNode(TreeNode node) {
        TreeNode par = node.getParent();
        while (par != null) {
            if (isOrNode(par)) {
                return true;
            }
            par = par.getParent();
        }
        return false;
    }

    /**
     * 获取节点的外部或节点
     *
     * @param node
     * @return
     */
    public static TreeNode getParOrNode(TreeNode node) {
        TreeNode par = node.getParent();
        while (par != null) {
            if (isOrNode(par)) {
                return par;
            }
            par = par.getParent();
        }
        return null;
    }

    /**
     * 判断节点是否是｜链接的节点
     * 判断后如果需要get 操作 需要调用  getGroupSubNode()
     *
     * @param node
     * @return
     */
    public static boolean isOrNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isOrNode(node.getChild(1));
        }
        // 加入判断是否在[]内 例如[a|b|c]会出现判断错误
        if (isBracketsNode(node) ||
                isInBrackets(node) ||
                isNegateNode(node) ||
                isInNegateNode(node) ||
                isLookAroundNode(node) ||
                isNonCapturingGroupNode(node) //||
//                isInNonCapturingGroupNode(node)
        ) return false;
        // TODO: fix bug (?:abc|def)会出错
        if (node.getChildCount() >= 1) {
            for (int i = 0; i < node.getChildCount(); i++) {
                if (node.getChild(i).getData().equals("|")) {
                    return true;
                }
            }
        }
        return false;

//        if (node.getChildCount() >= 1) {
//            for (int i = 1; i < node.getChildCount(); i += 2) {
//                if (!"|".equals(node.getChild(i).getData())) {
//                    return false;
//                }
//            }
//        } else {
//            return false;
//        }
//        return true;
    }

    /**
     * 获取括号节点的内部节点
     *
     * @param node
     * @return
     */
    public static TreeNode getGroupSubNode(TreeNode node) {
        if (isGroupNode(node)) {
            return getGroupSubNode(node.getChild(1));
        }
        return node;
    }

    /**
     * 判断节点是否是counting 节点
     * 只支持 {1,2} 类型
     *
     * @param node
     * @return
     */
    public static boolean isCountingNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isCountingNode(node.getChild(1));
        }
        if (node.getChildCount() == 2) {
            return Pattern.matches(COUNTING, node.getChild(1).getData());
        }
        return false;
    }

    /**
     * 判断节点是否是广义的带计数修饰节点 counting ? * +
     *
     * @param node
     * @return
     */
    public static boolean isGeneralizedCountingNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isGeneralizedCountingNode(node.getChild(1));
        }
        if (node.getChildCount() == 2) {
            if (Pattern.matches(COUNTING, node.getChild(1).getData())) {
                return true;
            }
            String data = node.getChild(1).getData();
            return "+".equals(data) || "*".equals(data) || "?".equals(data);
        }
        return false;
    }


    //判断节点是否是大于1的带计数修饰节点 counting  * +
    //copy from isSLQOneCountingNode by czx
    public static boolean isGeneralizedCountingNodeWithMaxNumGreaterThanOne(TreeNode node) {
        if (isGroupNode(node)) {
            return isGeneralizedCountingNodeWithMaxNumGreaterThanOne(node.getChild(1));
        }
        if (node.getChildCount() == 2) {
            TreeNode child = node.getChild(1);
            if (!isQuantifierNode(child)) return false;
            if (child.getData().equals("{,}")) return true;
            int secondNum = getCountingSecondNum(child.getData());
            if (secondNum == -1 || secondNum > 1) return true;
//            if (Pattern.matches(EXTENDED_COUNTING, node.getChild(1).getData())) {
//                return true;
//            }
        }
        return false;
    }

    // 判断当前结是否是在 大于1的带计数修饰节点中 counting  * +
    public static boolean isInGeneralizedCountingNodeWithMaxNumGreaterThanOne(TreeNode node) {
        TreeNode parent = node.getParent();
        while (parent != null) {
            if (isGeneralizedCountingNodeWithMaxNumGreaterThanOne(parent)) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    // 获取最近的父节点 这个父节点要是 大于1的带计数修饰节点 counting  * +
    // 如果找到则返回treeNode, 找不到则返回null
    public static TreeNode getTheNearestParentWithMaxNumGreaterThanOneGeneralizedCounting(TreeNode node) {
        TreeNode parent = node;
        while (parent != null) {
            if (isGeneralizedCountingNodeWithMaxNumGreaterThanOne(parent)) {
                return parent;
            }
            parent = parent.getParent();
        }
        return null;
    }


    // 判断结点是否是{m,n}的计数修饰结点 {0,1} ?
    public static boolean isGeneralizedCountingNodeWithMaxNumLessThanOrEqualToOne(TreeNode node) {
        if (isGroupNode(node)) {
            return isGeneralizedCountingNodeWithMaxNumLessThanOrEqualToOne(node.getChild(1));
        }
        if (node.getChildCount() == 2) {
            if (node.getChild(1).getData().equals("{0,1}") || node.getChild(1).getData().equals("?")) {
                return true;
            }
        }
        return false;
    }

    // 判断是否为counting相等的结点 比如a{2,2} a{3}
    public static boolean isEqualCountingNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isEqualCountingNode(node);
        }
        if (node.getChildCount() == 2) {
            if (Pattern.matches(EQUAL_COUNTING, node.getChild(1).getData())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取counting 数字
     * 不支持{,}
     *
     * @param data
     * @return
     */
    public static int getCountingFirstNum(String data) {
        if (data.equals("*") || data.equals("?")) return 0;
        if (data.equals("+")) return 1;
        int index = 0;
        if (data.contains(",")) {
            index = data.indexOf(',');
        } else {
            index = data.indexOf('}');
        }
        return Integer.parseInt(data.substring(1, index));
    }

    /**
     * 获取coutning数字中的大值 即{m,n}中的n 用-1表示∞
     * 例如{2,3}返回3 {4,}返回-1 {,5}返回5
     * 不支持 {,}
     *
     * @param data
     * @return
     */
    public static int getCountingSecondNum(String data) {
        if (data.contains(",}") || data.equals("*") || data.equals("+")) return -1;
        if (data.equals("?")) return 1;
        if (data.contains(",")) {
            int index = data.indexOf(",");
            return Integer.parseInt(data.substring(index + 1, data.length() - 1));
        } else {
            return Integer.parseInt(data.substring(1, data.length() - 1));
        }
    }

    /**
     * 判断节点是否是集合节点
     * \w . (.) (\w)
     *
     * @param node
     * @return
     */
    public static boolean isSetNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isSetNode(node.getChild(1));
        }

        String data = node.getData();
        boolean result = ".".equals(data) || "\\w".equals(data) || "\\d".equals(data) || "\\s".equals(data);
        return result;
    }

    /**
     * 判断节点是否是集合节点，且必须以 * 或 + 修饰
     * [abc]* \w* (a?\\w)*
     *
     * @param node
     * @return
     */
    public static boolean isSetRepeatNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isSetRepeatNode(node.getChild(1));
        }
        if (node.getChildCount() == 2 && ("*".equals(node.getChild(1).getData()) || "+".equals(node.getChild(1).getData()))) {
            node = node.getChild(0);
            if (isGroupNode(node)) {
                node = node.getChild(1);
            }
            boolean result = isSetNode(node) || isBracketsNode(node) || isNegateNode(node);
            if (result) {
                return true;
            } else {
                try {
                    return isBracketsNode(node) || isNegateNode(node) || isSetNode(removeOption(node));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    /**
     * 判断节点是否是集合节点，且节点内容为单字母
     * 如 a+ (a)+ (a+)
     *
     * @param node
     * @return
     */
    public static boolean isSingleCountingNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isSingleCountingNode(node.getChild(1));
        }
        if (node.getChildCount() == 2 && ("*".equals(node.getChild(1).getData()) || "+".equals(node.getChild(1).getData()))) {
            node = node.getChild(0);
            if (isGroupNode(node)) {
                node = node.getChild(1);
            }
            if (node.isLeaf()) {
                return true;
            } else {
                try {
                    return removeOption(node).isLeaf();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 去除节点中可选项
     *
     * @param node (a?.)
     * @return .
     */
    public static TreeNode removeOption(TreeNode node) throws InterruptedException {
        if (isGroupNode(node)) {
            return removeOption(node.getChild(0));
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < node.getChildCount(); i++) {
            TreeNode child = node.getChild(i);
            if (isSetNode(node) || isBracketsNode(node)) {
                stringBuilder.append(child.getData());
            } else {
                if (!child.getLetterSetMustHas().isEmpty()) {
                    stringBuilder.append(child.getData());
                }
            }
        }
        return createReDoSTree(stringBuilder.toString());
    }


    /**
     * 判断节点是否以 * 或 + 修饰
     *
     * @param node
     * @return
     */
    public static boolean isStarNode(TreeNode node) {
        if (isGroupNode(node)) {
            node = node.getChild(1);
        }
        if (node.getChildCount() == 2 && ("*".equals(node.getChild(1).getData()) || "+".equals(node.getChild(1).getData()))) {
            return true;
        }
        return false;
    }

    /**
     * 判断节点是否以 * 或 + 修饰
     *
     * @param node
     * @return
     */
    public static boolean isDotNode(TreeNode node) {
        if (isGroupNode(node)) {
            node = node.getChild(1);
        }
        return node.getData().equals(".");
    }

    /**
     * 判断节点是否由括号修饰节点（括号内必须存在一个以上的歧义点），且必须以 * 或 + 修饰
     *
     * @param node
     * @return
     */
    @Deprecated
    public static boolean isExponentNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isExponentNode(node.getChild(1));
        }
        if (node.getChildCount() == 2 && isGroupNode(node.getChild(0)) && ("*".equals(node.getChild(1).getData()) || "+".equals(node.getChild(1).getData()))) {
            node = node.getChild(0).getChild(1);
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                for (int i = 0; i < node.getChildCount(); i++) {
                    if ("|".equals(node.getChild(i).getData())) {
                        continue;
                    }
                    if (node.getChild(i).getChildCount() == 2 && ("*".equals(node.getChild(i).getChild(1).getData()) || "+".equals(node.getChild(i).getChild(1).getData()) || "?".equals(node.getChild(i).getChild(1).getData()))) {
                        return true;
                    }
                    for (int j = 0; j < node.getChild(i).getChildCount(); j++) {
                        TreeNode child = node.getChild(i).getChild(j);
                        if (isGroupNode(child)) {
                            child = child.getChild(1);
                        }
                        if (child.getChildCount() == 2 && ("*".equals(child.getChild(1).getData()) || "+".equals(child.getChild(1).getData()) || "?".equals(child.getChild(1).getData()))) {
                            return true;
                        }

                    }
                }

            } else {
                if (node.getChildCount() == 2 && ("*".equals(node.getChild(1).getData()) || "+".equals(node.getChild(1).getData()) || "?".equals(node.getChild(1).getData()))) {
                    return true;
                }
                for (int i = 0; i < node.getChildCount(); i++) {
                    TreeNode child = node.getChild(i);
                    if (isGroupNode(child)) {
                        child = child.getChild(1);
                    }
                    if (child.getChildCount() == 2 && ("*".equals(child.getChild(1).getData()) || "+".equals(child.getChild(1).getData()) || "?".equals(child.getChild(1).getData()))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断节点是否为零宽断言 lookaround,^ $ \b \B
     *
     * @param node
     * @return
     */
    public static boolean isZeroWidthAssertionNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isZeroWidthAssertionNode(node.getChild(1));
        }
        if (isLookAroundNode(node)) {
            return true;
        }
        if (node.isLeaf() && !isInBrackets(node) && !isInNegateNode(node)) {
            if (node.getData().equals("\\b") ||
                    node.getData().equals("\\B") ||
                    node.getData().equals("^") ||
                    node.getData().equals("$") ||
                    node.getData().equals("\\A") || // 新增
                    node.getData().equals("\\Z") || // 新增
                    node.getData().equals("\\z")    // 新增
            ) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为预查找节点
     * 因为会出现(?!)这样的结点 所以node.getChildCount()有两个值 4或5
     * @param node
     * @return
     */
    public static boolean isLookAroundNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isLookAroundNode(node.getChild(1));
        }
        if ((node.getChildCount() == 4 || node.getChildCount() == 5) && node.getData().startsWith("(?!")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "!".equals(node.getChild(2).getData())) {
                return true;
            }
        }
        if ((node.getChildCount() == 5 || node.getChildCount() == 6) && node.getData().startsWith("(?<!")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "<".equals(node.getChild(2).getData()) && "!".equals(node.getChild(3).getData())) {
                return true;
            }
        }
        if ((node.getChildCount() == 4 || node.getChildCount() == 5) && node.getData().startsWith("(?=")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "=".equals(node.getChild(2).getData())) {
                return true;
            }
        }
        if ((node.getChildCount() == 5 || node.getChildCount() == 6) && node.getData().startsWith("(?<=")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "<".equals(node.getChild(2).getData()) && "=".equals(node.getChild(3).getData())) {
                return true;
            }
        }
        return false;
    }

    // 获取lookaround内的正则
    public static String getLookAroundRegex(TreeNode node) {
        AssertType assertType = getAssertType(node);
        if (assertType == AssertType.NEGATIVE_AHEAD) {
            if (node.getChildCount() == 5) {
                return node.getChild(3).getData();
            } else if (node.getChildCount() == 4) {
                return "";
            }
        } else if (assertType == AssertType.NEGATIVE_BEHIND) {
            if (node.getChildCount() == 6) {
                return node.getChild(4).getData();
            } else if (node.getChildCount() == 5) {
                return "";
            }
        } else if (assertType == AssertType.POSITIVE_AHEAD) {
            if (node.getChildCount() == 5) {
                return node.getChild(3).getData();
            } else if (node.getChildCount() == 4) {
                return "";
            }
        } else if (assertType == AssertType.POSITIVE_BEHIND) {
            if (node.getChildCount() == 6) {
                return node.getChild(4).getData();
            } else if (node.getChildCount() == 5) {
                return "";
            }
        }
        return "";
    }

    /**
     * 获取零宽断言节点的类型，非零宽断言节点返回null
     *
     * @param node
     * @return
     */
    public static AssertType getAssertType(TreeNode node) {
        if (isLookAroundNode(node)) {
            if ((node.getChildCount() == 4 || node.getChildCount() == 5) && node.getData().startsWith("(?!")) {
                if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "!".equals(node.getChild(2).getData())) {
                    return AssertType.NEGATIVE_AHEAD;
                }
            }
            if ((node.getChildCount() == 5 || node.getChildCount() == 6) && node.getData().startsWith("(?<!")) {
                if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "<".equals(node.getChild(2).getData()) && "!".equals(node.getChild(3).getData())) {
                    return AssertType.NEGATIVE_BEHIND;
                }
            }
            if ((node.getChildCount() == 4 || node.getChildCount() == 5) && node.getData().startsWith("(?=")) {
                if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "=".equals(node.getChild(2).getData())) {
                    return AssertType.POSITIVE_AHEAD;
                }
            }
            if ((node.getChildCount() == 5 || node.getChildCount() == 6) && node.getData().startsWith("(?<=")) {
                if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "<".equals(node.getChild(2).getData()) && "=".equals(node.getChild(3).getData())) {
                    return AssertType.POSITIVE_BEHIND;
                }
            }
        } else if (node.isLeaf() && !isInBrackets(node) && !isInNegateNode(node)) { // fix bug 不能在[...]或[^...]中
            switch (node.getData()) {
                case "\\b":
                    return AssertType.WORD_BOUNDARY_ASSERT;
                case "\\B":
                    return AssertType.NON_WORD_BOUNDARY_ASSERT;
                case "^":
                    return AssertType.START_ASSERT;
                case "$":
                    return AssertType.END_ASSERT;
                case "\\A": // 新增
                    return AssertType.START_ASSERT;
                case "\\Z": // 新增
                    return AssertType.END_ASSERT;
                case "\\z": // 新增
                    return AssertType.END_ASSERT;
            }
        }
        return AssertType.NO_ASSERT;
    }


    // 判断是(?!
    @Deprecated
    public static boolean isNegativeLookAheadNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isLookAroundNode(node.getChild(1));
        }
        if (node.getChildCount() == 5 && node.getData().startsWith("(?!")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "!".equals(node.getChild(2).getData())) {
                return true;
            }
        }
        return false;
    }

    // 判断是(?<!
    @Deprecated
    public static boolean isNegativeLookBehindNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isLookAroundNode(node.getChild(1));
        }
        if (node.getChildCount() == 6 && node.getData().startsWith("(?<!")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "<".equals(node.getChild(2).getData()) && "!".equals(node.getChild(3).getData())) {
                return true;
            }
        }
        return false;
    }

    // 判断是(?=
    @Deprecated
    public static boolean isPositiveLookAheadNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isLookAroundNode(node.getChild(1));
        }
        if ((node.getChildCount() == 4 || node.getChildCount() == 5) && node.getData().startsWith("(?=")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "=".equals(node.getChild(2).getData())) {
                return true;
            }
        }
        return false;
    }

    // 判断是(?<=
    @Deprecated
    public static boolean isPositiveLookBehindNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isLookAroundNode(node.getChild(1));
        }
        if ((node.getChildCount() == 5 || node.getChildCount() == 6) && node.getData().startsWith("(?<=")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "<".equals(node.getChild(2).getData()) && "=".equals(node.getChild(3).getData())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为否定预查找节点
     *
     * @param node
     * @return
     */
    @Deprecated
    public static boolean isNegativeAssertNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isLookAroundNode(node.getChild(1));
        }
        if ((node.getChildCount() == 4 || node.getChildCount() == 5) && node.getData().startsWith("(?!")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "!".equals(node.getChild(2).getData())) {
                return true;
            }
        }
        if ((node.getChildCount() == 5 || node.getChildCount() == 6) && node.getData().startsWith("(?<!")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "<".equals(node.getChild(2).getData()) && "!".equals(node.getChild(3).getData())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为匹配预查找节点
     *
     * @param node
     * @return
     */
    @Deprecated
    public static boolean isPositiveAssertNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isLookAroundNode(node.getChild(1));
        }
        if ((node.getChildCount() == 4 || node.getChildCount() == 5) && node.getData().startsWith("(?=")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "=".equals(node.getChild(2).getData())) {
                return true;
            }
        }
        if ((node.getChildCount() == 5 || node.getChildCount() == 6) && node.getData().startsWith("(?<=")) {
            if ("(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "<".equals(node.getChild(2).getData()) && "=".equals(node.getChild(3).getData())) {
                return true;
            }
        }
        return false;
    }

    // 判断该结点是否为注释结点
    public static boolean isAnnotationNode(TreeNode node) {
        String data = node.getData();
        if (data.startsWith("(?#") && data.endsWith(")")) {
            if (node.getChildCount() == 5) {
                return "(".equals(node.getChild(0).getData()) && "?".equals(node.getChild(1).getData()) && "#".equals(node.getChild(2).getData()) && ")".equals(node.getChild(node.getChildCount() - 1).getData());
            }
        }
        return false;
    }

    /**
     * 判断该节点是否为中括号取反节点
     *
     * @param node
     * @return
     */
    public static boolean isNegateNode(TreeNode node) {
//        if (isGroupNode(node)) {
//            return isNegateNode(node.getChild(1));
//        }
        String data = node.getData();
        if (data.startsWith("[^") && data.endsWith("]")) {
            if (node.getChildCount() >= 3) {
                return "[".equals(node.getChild(0).getData()) && "^".equals(node.getChild(1).getData()) && "]".equals(node.getChild(node.getChildCount() - 1).getData());
            }
        }
        return false;
    }

    /**
     * 判断节点是否为单独的去补节点如 \W \D \S \b
     *
     * @param node
     * @return
     */
    public static boolean isNegateSingleNode(TreeNode node) {
        if (isGroupNode(node)) {
            return isNegateSingleNode(node.getChild(1));
        }
        if (node.isLeaf()) {
            return COMPLEMENTARY_SET_SYMBOL.containsKey(node.getData());
        }
        return false;
    }


    /**
     * 从叶子节点返回匹配串
     * 如 \w \W . \( 1
     * waring: 只支持叶子节点
     *
     * @param node
     * @return
     */
    public static String getMatchLeafNode(TreeNode node) {
        if (!node.isLeaf()) {
            return "";
        }
        String data = node.getData();
        StringBuilder s = new StringBuilder();
        if (data.length() == 1) {
            if (".".equals(data)) {
                s.append("1");
            } else {
                s.append(data);
            }
        } else {
            switch (data) {
                case "\\W":
                    s.append("!");
                    break;
                case "\\D":
                    s.append("a");
                    break;
                case "\\S":
                    s.append("1");
                    break;
                case "\\w":
                    s.append("1");
                    break;
                case "\\d":
                    s.append("1");
                    break;
                case "\\s":
                    s.append(" ");
                    break;
                default:
                    if (data.length() == 2 && data.startsWith("\\") && !"\\n".equals(data) && !"\\t".equals(data) && !"\\r".equals(data) && !"\\f".equals(data) && !"\\v".equals(data)) {
                        s.append(data.substring(1));
                    } else if (data.equals("\\u000b")) {
                        s.append("\u000b");
                    } else {
                        s.append(data);
                    }
                    break;
            }
        }
        return s.toString();
    }

    /**
     * 从叶子节点返回不匹配串
     * 如 \w \W . \( 1
     * waring: 只支持叶子节点
     *
     * @param node
     * @return
     */
    public static String getNonMatchLeafNode(TreeNode node) {
        if (!node.isLeaf()) {
            return "";
        }
        String data = node.getData();
        StringBuilder s = new StringBuilder();
        if (data.equals(".")) {
            s.append("\n");
        }
        if (data.length() == 1) {
//            s.append("!".equals(data) ? "@ _1" : "! _1");
            s.append("!".equals(data) ? "◎" : "@");
        } else {
            switch (data) {
//                case "\\W":
//                    s.append("a! _1");
//                    break;
//                case "\\D":
//                    s.append("1! _1");
//                    break;
//                case "\\S":
//                    s.append(" !_1");
//                    break;
//                case "\\w":
//                    s.append("! _1");
//                    break;
//                case "\\d":
//                    s.append("! _1");
//                    break;
//                case "\\s":
//                    s.append("!_1");
//                    break;
//                default:
//                    s.append("◎@! _1");
//                    break;
                case "\\W":
                    s.append("a");
                    break;
                case "\\D":
                    s.append("0");
                    break;
                case "\\S":
                    s.append(" ");
                    break;
                case "\\w":
                    s.append("!");
                    break;
                case "\\d":
                    s.append("a");
                    break;
                case "\\s":
                    s.append("!");
                    break;
                default:
                    s.append("◎");
                    break;
            }
        }
        return s.toString();
    }

    /**
     * 从中括号返回匹配串
     * [a-z] [\w] [123]
     *
     * @param node
     * @return
     */
    public static String getMatchBracketNode(TreeNode node) {
        String set = "";
        for (int i = 1; i < node.getChildCount() - 1; i++) {
            String childData = node.getChild(i).getData();
            if (childData.length() == 1 && isSpecialCharacterBracket(childData.charAt(0))) {
                // 中括号类的特殊字符，需要加上\才能在外部被识别
                set = "\\" + childData;
                break;
            } else {
                //todo  childData 必须是叶子节点
                if (childData.contains("-") && childData.length() > 3 && childData.length() <= 5) {
                    childData = childData.replace("\\\\", "special_slash_ios_ac_cn");
                    childData = childData.replace("\\", "");
                    childData = childData.replace("special_slash_ios_ac_cn", "\\");
                    set = childData;
                    break;
                } else if (isCollectionSymbol(childData)) {
                    set = childData;
                    break;
                } else {
                    if (node.getChild(i).isLeaf()) {
                        set = childData;
                        break;
                    } else {
                        LinkedList<String> list = node.getChild(i).getLeafs();
                        set = list.get(0);
                        break;
                    }
                }
            }
        }
        StringBuilder s = new StringBuilder();
        if ("\\w".equals(set)) {
            s.append("1");
        } else if ("\\d".equals(set)) {
            s.append("1");
        } else if ("\\s".equals(set)) {
            s.append(" ");
        } else if ("\\W".equals(set)) {
            s.append("!");
        } else if ("\\S".equals(set)) {
            s.append("1");
        } else if ("\\D".equals(set)) {
            s.append("a");
        } else if (isCollectionSymbol(set)) {
            String ch = "";
            long start = set.charAt(0);
            long end = set.charAt(2);
            for (; start <= end; start++) {
                char c = (char) start;
                if (Character.isLowerCase(c) || Character.isUpperCase(c) || Character.isDigit(c)) {
                    ch = String.valueOf(c);
                    break;
                } else if (c >= 41) {
                    ch = String.valueOf(c);
                }
            }
            if (ch.equals("")) {
                s.append(set.charAt(0));
            } else {
                s.append(ch);
            }
        } else {
            if (set.length() == 2 && set.startsWith("\\") && !"\\n".equals(set) && !"\\t".equals(set) && !"\\r".equals(set)) {
                s.append(set.substring(1));
            } else {
                s.append(set);
            }
        }
        return s.toString();
    }

    /**
     * 生成倾向于conflictList集合的匹配串
     *
     * @param letter       集合节点
     * @param conflictList 目前元素集合
     * @return
     */
    public static String getMatchLetter(String letter, List<String> conflictList) {
        String returnLetter = NO_LETTER_MATCH;
        switch (letter) {
            case "\\w":
                for (String str : w_MATCH_CHARACTER_LIST) {
                    if (conflictList.contains(str)) {
                        returnLetter = str;
                        break;
                    }
                }
                break;
            case "\\d":
                for (String str : d_MATCH_CHARACTER_LIST) {
                    if (conflictList.contains(str)) {
                        returnLetter = str;
                        break;
                    }
                }
                break;
            case "\\s":
                for (String str : s_MATCH_CHARACTER_LIST) {
                    if (conflictList.contains(str)) {
                        returnLetter = str;
                        break;
                    }
                }
                break;
            case ".":
                returnLetter = conflictList.get(0);
                for (String str : conflictList) {
                    if (str.length() == 1) {
                        char c = str.charAt(0);
                        if (Character.isLowerCase(c) || Character.isUpperCase(c) || Character.isDigit(c)) {
                            returnLetter = String.valueOf(c);
                            break;
                        } else if (c >= 41) {
                            returnLetter = String.valueOf(c);
                        }
                    }
                }
                break;
            default:
                if (conflictList.contains(letter)) {
                    if (letter.length() == 2 && letter.startsWith("\\") && !"\\n".equals(letter) && !"\\t".equals(letter) && !"\\r".equals(letter) && !"\\f".equals(letter) && !"\\v".equals(letter)) {
                        returnLetter = letter.substring(1);
                    } else {
                        returnLetter = letter;
                    }
                } else {
                    if (isSpecialCharacterBracket(letter.charAt(0)) && conflictList.contains("\\" + letter)) {
                        returnLetter = letter;
                    } else {
                        returnLetter = NO_LETTER_MATCH;
                    }
                }
                break;
        }
        return returnLetter;
    }


    /**
     * 生成倾向于conflictList集合的匹配串
     *
     * @param subList      中括号集合节点
     * @param conflictList 目前元素集合
     * @return
     */
    public static String getMatchLetter(List<String> subList, List<String> conflictList) {
        conflictList.retainAll(subList);
        if (conflictList.isEmpty()) {
            return NO_LETTER_MATCH;
        } else {
            String letter = conflictList.get(0);
            for (String str : conflictList) {
                if (str.length() == 1) {
                    char c = str.charAt(0);
                    if (Character.isLowerCase(c) || Character.isUpperCase(c) || Character.isDigit(c)) {
                        letter = String.valueOf(c);
                        break;
                    } else if (c >= 41) {
                        letter = String.valueOf(c);
                    }
                }
            }
            return letter;
        }
    }


    // 判断当前结点是否为 包含大于等于某个Num的counting子结点 的结点
    // 例如 Num = 10000 (a|b?) 是 (a|b)? 是 (a|b)不是
    public static boolean isContainGeneralizedCountingNodeWithMaxNumGreaterThanSpecialNum(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (isGeneralizedCountingNode(node) && !isGroupNode(node)) {
                int secondNum = getCountingSecondNum(node.getChild(1).getData());
                if (secondNum == -1 || secondNum >= 10000) return true;
            }
            queue.addAll(node.getChildList());
        }
        return false;
    }

    /**
     * 重写空串
     *
     * @param root
     * @param flag 为true 表示SLQ中使用，不添加？
     * @return
     * @throws InterruptedException
     * @waring 建议使用 removeBlankStr(root)
     */
    public static TreeNode removeBlankStr(TreeNode root, boolean flag) throws InterruptedException {
        Stack<TreeNode> stack = new Stack<>();
        // 删除空括号()
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isOnlyEmptyNode(node)) {
                TreeNode par = node.getParent();
                if (par == null) {
                    return createReDoSTree("");
                } else {
                    par.deleteChild(node);
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isOrNode(node)) {
                node = getGroupSubNode(node);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < node.getChildCount(); i++) {
                    TreeNode child = node.getChild(i);
                    if (!"|".equals(child.getData()) && !"".equals(child.getData())) {
                        stringBuilder.append(child.getData()).append("|");
                    }
                }
                if (stringBuilder.length() == 0) {
                    node.updateTreeByModifyNode("");
                } else {
                    String data = stringBuilder.substring(0, stringBuilder.length() - 1);
                    if (!data.equals(node.getData())) {
                        if (flag) {
                            node.updateTreeByModifyNode(data);
                        } else {
                            node.updateTreeByModifyNode("(?:" + data + ")?");
                        }
                    }
                }
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        // 删除空括号()
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isOnlyEmptyNode(node)) {
                TreeNode par = node.getParent();
                if (par == null) {
                    return createReDoSTree("");
                } else {
                    par.deleteChild(node);
                }
            } else {
                for (int i = node.getChildCount() - 1; i >= 0; i--) {
                    stack.push(node.getChild(i));
                }
            }
        }
        return root;
    }

    /**
     * 重写空串
     *
     * @param root
     * @return
     * @throws InterruptedException
     */
    public static TreeNode removeBlankStr(TreeNode root) throws InterruptedException {
        return removeBlankStr(root, false);
    }


    /**
     * 获取或结点中的纯文本正则
     * 例如(abc|x(?=y)z|((?=e)|g)) 获得(abc|(g))
     * @return
     */
    public static String getPureTextInOrNode(TreeNode orNode) throws InterruptedException {
        String result = getPureTextInOrNodeHelper(orNode);
        // (((?=c)|(?=d)).*|a) -> (().*|a)
        // 需要调用去空
        TreeNode resultTree = createReDoSTree(result);
        resultTree = removeBlankStr(resultTree);
        return resultTree.getData();
    }

    private static String getPureTextInOrNodeHelper(TreeNode root) {
        if (!isOrNode(root)) {
            return "";
        } else {
            root = getGroupSubNode(root);
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < root.getChildCount(); i++) {
                TreeNode child = root.getChild(i);
                if (!child.getData().equals("|")) {
                    if (child.containsZeroWidthAssertionChild()) {
                        StringBuilder stringBuilder = new StringBuilder();
                        Stack<TreeNode> stack = new Stack<>();
                        stack.push(child);
                        boolean flag = false;
                        while (!stack.isEmpty()) {
                            TreeNode node = stack.pop();
                            if (isZeroWidthAssertionNode(node)) {
                                flag = true;
                                break;
                            } else if (isOrNode(node)) {
                                stringBuilder.append(getPureTextInOrNodeHelper(node));
                            } else if (node.isLeaf()) {
                                stringBuilder.append(node.getData());
                            } else {
                                for (int j = node.getChildCount() - 1; j >= 0; j--) {
                                    stack.push(node.getChild(j));
                                }
                            }
                        }
                        if (!flag) {
                            list.add(stringBuilder.toString());
                        }
                    } else {
                        list.add(child.getData());
                    }
                }
            }
            StringBuilder stringBuilder = new StringBuilder("(");
            for (int i = 0; i < list.size(); i++) {
                stringBuilder.append(list.get(i));
                if (i != list.size() - 1) {
                    stringBuilder.append("|");
                }
            }
            return stringBuilder.append(")").toString();
        }
    }



    public static void main(String[] args) throws InterruptedException {
        String re = "(|.*(|a|))+";
        TreeNode treeNode = createReDoSTree(re);
        treeNode = removeBlankStr(treeNode);
        printTree(treeNode);
    }
}
