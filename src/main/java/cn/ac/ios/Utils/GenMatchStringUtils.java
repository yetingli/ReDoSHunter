package cn.ac.ios.Utils;

import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Patterns.NQ.PatternNQUtils;
import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.TreeNode.Utils.printTree;
import static cn.ac.ios.Utils.BracketUtils.isInLookAroundNode;
import static cn.ac.ios.Utils.DkBricsAutomatonUtils.*;
import static cn.ac.ios.Utils.NegateUtils.deleteZeroWidthAssertion;
import static cn.ac.ios.Utils.RegexUtils.*;
import static cn.ac.ios.Utils.SplitRegexUtils.getR0;
import static cn.ac.ios.Utils.SplitRegexUtils.getR4;

public class GenMatchStringUtils {

    // 待测试 生成匹配串
    public static TreeNode slimTree(TreeNode treeNode) throws InterruptedException {
        TreeNode treeNodeCopy = SerializationUtils.clone(treeNode); // 深拷贝
        Stack<TreeNode> stack = new Stack<>();
        stack.add(treeNodeCopy);
        // 获取叶子节点集合
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            node = getGroupSubNode(node);
            if (isOrNode(node) && !isInLookAroundNode(node)) {
//                node = node.getChild(0);
                // 改为找其中首先不含有lookaround的结点，其次找其中有效子正则少的结点
                List<TreeNode> orChildList = new ArrayList<>();
                for (int i = 0; i < node.getChildCount(); i += 2) {
                    orChildList.add(node.getChild(i));
                }
                orChildList.sort(new Comparator<TreeNode>() {
                    @Override
                    public int compare(TreeNode o1, TreeNode o2) {
                        if (o1.containsLookAroundChild() && !o2.containsLookAroundChild()) {
                            return 1;
                        } else if (!o1.containsLookAroundChild() && o2.containsLookAroundChild()) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });
//                System.nanoTime();
                node.updateTreeByModifyNode(orChildList.get(0).getData());
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
        }
        return treeNodeCopy;
    }

    // 修正换行符、回车符和制表符
    // \\t ->\t
    // \\n -> \n
    // \\f -> \f
    // \\v -> \v
    // \\r -> \r
    public static String repairLineFeedsCarriageReturnsAndTabCharacters(String regex) throws InterruptedException {
        if (!regex.contains("\\t") &&
                !regex.contains("\\n") &&
                !regex.contains("\\r") &&
                !regex.contains("\\v") &&
                !regex.contains("\\u000b") &&
                !regex.contains("\\f")) {
            return regex;
        }
        TreeNode treeNode = createReDoSTree(regex);
        List<TreeNode> allLeafNodes = treeNode.getLeafNodes();
        for (TreeNode node: allLeafNodes) {
            String data = node.getData();
            String newData = data;
            if (data.equals("\\t")) {
                newData = "\t";
            } else if (data.equals("\\f")) {
                newData = "\f";
            } else if (data.equals("\\n")) {
                newData = "\n";
            } else if (data.equals("\\v") || data.equals("\\u000b")) {
                newData = "\u000b";
            } else if (data.equals("\\r")) {
                newData = "\r";
            }
            if (! newData.equals(data)) {
                node.updateTreeByModifyNode(newData);
            }
        }
        return treeNode.getData();
    }


    // TODO: 宽断言在或结点中的情况不对, 但也不会修, 先这样
    public static List<String> getTranslateRegexForAssertionsList(TreeNode treeNode) throws InterruptedException {
        // 后序遍历树 对于每一个树中的结点 根据assertType修改对应的写法 写的时候.*可以写成[\u0000-\uFFFF]*  &要写成＆   ~要写成～
        // AssertType.POSITIVE_AHEAD    r1(?=r2)r3   r1 ( r2.* & r3 )
        // AssertType.NEGATIVE_AHEAD    r1(?!r2)r3   r1 ( ~(r2.*) & r3 )
        // AssertType.POSITIVE_BEHIND   r1(?<=r2)r3  ( r1 & .*r2 ) r3
        // AssertType.NEGATIVE_BEHIND   r1(?<!r2)r3  ( r1 & ~(.*r2) ) r3
        // AssertType.WORD_BOUNDARY_ASSERT  r1\br2  w=w1w2,  where w1∈L(r1) ∧ w2∈L(r2) ∧ ( ((w1∈L(.*\W) ∨ w1=ε) ∧ w2∈L(\w.*)) ∨ (w1∈L(.*\w) ∧ (w2∈L(\W.*) ∨ w2=ε)) )
        //                                          ^↓\w | \w↓$ | \W↓\w | \w↓\W
        //                                          如果r1没有 且 r2有   则为 r2&\w.*
        //                                          如果r1有   且 r2有   则为 r1&.*\W连接\w.*&r2 或 r1&.*\w连接\W.*&r2
        //                                          如果r1有   且 r2没有 则为 r1&.*\w
        // AssertType.NON_WORD_BOUNDARY_ASSERT  r1\Br2  w=w1w2,w=w1w2,  where w1∈L(r1) ∧ w2∈L(r2) ∧ ( ((w1∉L(.*\W) ∧ w1∉ε) ∨ w2∉L(\w.*)) ∧ (w1∉L(.*\w) ∨ (w2∉L(\W.*) ∧ w2∉ε)) )
        //                                          ^↓\W | \W↓$ | \W↓\W | \w↓\w
        //                                          如果r1没有 且 r2有   则为 r2&\W.*
        //                                          如果r1有   且 r2有   则为 r1&.*\w连接\w.*&r2 或 r1&.*\W连接\W.*&r2
        //                                          如果r1有   且 r2没有 则为 r1&.*\W


        // 考虑零宽断言在或结点中的情况
        // r = (?:(?=a{3,})a|(?=b)|ee).*(?:(?=c)|(?=d)).*
        // 对于第一个或结点(?:(?=a{3,})a|(?=b)|ee) 中的
        // 每一个零宽断言转约束(a{3,}.*)&a.*    (b.*)&.*
        // 调用getPureTextInOrNode方法得到(ee), 拼上getR4(r, 这个或结点)为.*(?:(?=c)|(?=d)).*, deleteZeroWidthAssertion得到.*.*
        // 然后用或连接起来((a{3,}.*)&a.*|(b.*)&.*|(ee).*.*)
        // 即第一个或结点得到的完整约束
        // 对于第二个或结点(?:(?=c)|(?=d))
        // 同理得到完整的约束(((a|ee).*(c.*&.*))|((a|ee).*(d.*&.*)))
        // 两个的交集即为最终约束((a{3,}.*)&a.*|(b.*)&.*|(ee).*.*)&(((a|ee).*(c.*&.*))|((a|ee).*(d.*&.*)))

        // 后序遍历
        // 初始化一个栈S
        // 如果当前结点是零宽断言结点且是在一个或结点中, 就不处理,
        // 如果当前结点是一个或结点orNode, 且包含零宽结点, 则将orNode与栈S中的栈顶元素top比较
        //      若top是orNode的孩子或者孙子isNowNodeChildOrGrandchild, 则弹出
        //      重复上述过程 直到栈为空或者top不是当前结点的孩子或者孙子
        //      将orNode压栈
        // 对栈中每一个或结点按照上述考虑零宽断言在或结点中的情况进行处理

        List<TreeNode> orAssertionsList = new LinkedList<>();
        List<String> translateResults = new LinkedList<>();

        Stack<TreeNode> auxiliaryStack = new Stack<>();
        Stack<TreeNode> treeNodeStack = new Stack<>();
        auxiliaryStack.push(treeNode);
        while (!auxiliaryStack.isEmpty()) {
            TreeNode tmp = auxiliaryStack.pop();
            treeNodeStack.push(tmp);
            for (int i = 0; i < tmp.getChildCount(); i++) {
                auxiliaryStack.push(tmp.getChild(i));
            }
        }
        // 后序遍历处理
        while (!treeNodeStack.isEmpty()) {
            TreeNode node = treeNodeStack.pop();
            Constant.AssertType assertType = getAssertType(node);
            if (assertType != Constant.AssertType.NO_ASSERT) {
//                // 如果是在一个或结点中, 则先不处理
//                if (isInOrNode(node)) {
//                    orAssertionsList.add(node);
//                    continue;
//                }

                String r1 = getR0(treeNode, node);
                TreeNode treeNode1 = createReDoSTree(r1);
                // 删除零宽断言
                deleteZeroWidthAssertion(treeNode1);
                // 使用重写后的去首尾^$
                treeNode1.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode1 = removeBlankStr(treeNode1);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode1.deleteNonCapturingGroupFlag();
                r1 = treeNode1.getData();

                String r3 = getR4(treeNode, node);
                TreeNode treeNode2 = createReDoSTree(r3);
                // 删除零宽断言
                deleteZeroWidthAssertion(treeNode2);
                // 使用重写后的去首尾^$
                treeNode2.deleteCaretAndDollarSymbols();
                // 新版重写空串
                treeNode2 = removeBlankStr(treeNode2);
                // 重写反向引用后 删除NonCapturingGroupFlag ?:
                treeNode2.deleteNonCapturingGroupFlag();
                r3 = treeNode2.getData();

                if (assertType == Constant.AssertType.POSITIVE_AHEAD || assertType == Constant.AssertType.NEGATIVE_AHEAD ||
                        assertType == Constant.AssertType.POSITIVE_BEHIND || assertType == Constant.AssertType.NEGATIVE_BEHIND) {
                    String r2 = getLookAroundRegex(node);

                    // 可能出现 内部嵌套的情况 比如node: (?<!(?<!.)) 这种
                    // r2 = (?<!.) 后面就错了
                    // 这里加一个 去掉内部lookaround的操作
                    if (r2.contains("(?=") || r2.contains("(?!") || r2.contains("(?<=") || r2.contains("(?<!")) {
                        TreeNode r2Temp = createReDoSTree(r2);
                        // 删除零宽断言
                        deleteZeroWidthAssertion(r2Temp);
                        // 新版重写空串
                        r2Temp = removeBlankStr(r2Temp);
                        // 重写反向引用后 删除NonCapturingGroupFlag ?:
                        r2Temp.deleteNonCapturingGroupFlag();
                        r2 = r2Temp.getData();
                    }

//                    if ("".equals(r2)) {// 若出现(?=) (?!) (?<=) (?<!)这种 相当于无lookaround 无需修改
//                        continue;
//                    }

                    if (assertType == Constant.AssertType.POSITIVE_AHEAD) {
                        String newData = "";    // r1 ( r2.* & r3 )
                        if (r3.equals("")) {
                            newData = r1 + "(" + r2 + "[\u0000-\uFFFF]*)";
                        } else {
                            newData = r1 + "(" + r2 + "[\u0000-\uFFFF]*" + "＆" + r3 + ")";
                        }
                        translateResults.add(newData);
                    } else if (assertType == Constant.AssertType.NEGATIVE_AHEAD) {
                        String newData = "";    // r1 ( ~(r2.*) & r3 )
                        if (r3.equals("")) {
                            newData = r1 + "(" + "～(" + r2 + "[\u0000-\uFFFF]*" + "))";
                        } else {
                            newData = r1 + "(" + "～(" + r2 + "[\u0000-\uFFFF]*" + ")＆" + r3 + ")";
                        }
                        translateResults.add(newData);
                    } else if (assertType == Constant.AssertType.POSITIVE_BEHIND) {
                        String newData = "";    // ( r1 & .*r2 ) r3
                        if (r1.equals("")) {
                            newData = "([\u0000-\uFFFF]*" + r2 + ")" + r3;
                        } else {
                            newData = "(" + r1 + "＆[\u0000-\uFFFF]*" + r2 + ")" + r3;
                        }
                        translateResults.add(newData);
                    } else if (assertType == Constant.AssertType.NEGATIVE_BEHIND) {
                        String newData = "";    // ( r1 & ~(.*r2) ) r3
                        if (r1.equals("")) {
                            newData = "(～([\u0000-\uFFFF]*" + r2 + "))" + r3;
                        } else {
                            newData = "(" + r1 + "＆～([\u0000-\uFFFF]*" + r2 + "))" + r3;
                        }
                        translateResults.add(newData);
                    }
                } else if (assertType == Constant.AssertType.WORD_BOUNDARY_ASSERT) {
                    if (r1.equals("") && !r3.equals("")) {  // 如果r1没有 且 r2有   则为 r2&\w.*
                        String newData = r3 + "＆\\w[\u0000-\uFFFF]*";
                        translateResults.add(newData);
                    } else if (!r1.equals("") && !r3.equals("")) {  // 如果r1有   且 r2有   则为 r1&.*\W连接\w.*&r2 或 r1&.*\w连接\W.*&r2
                        String newData =
                                "((" + r1 + "＆[\u0000-\uFFFF]*\\W)(\\w[\u0000-\uFFFF]*＆" + r3 + "))" + "|" +
                                        "((" + r1 + "＆[\u0000-\uFFFF]*\\w)(\\W[\u0000-\uFFFF]*＆" + r3 + "))";
                        translateResults.add(newData);
                    } else if (!r1.equals("") && r3.equals("")) {    // 如果r1有   且 r2没有 则为 r1&.*\w
                        String newData = r1 + "＆[\u0000-\uFFFF]*\\w";
                        translateResults.add(newData);
                    }
                } else if (assertType == Constant.AssertType.NON_WORD_BOUNDARY_ASSERT) {
                    if (r1.equals("") && !r3.equals("")) {  //  如果r1没有 且 r2有   则为 r2&\W.*
                        String newData = r3 + "＆\\W[\u0000-\uFFFF]*";
                        translateResults.add(newData);
                    } else if (!r1.equals("") && !r3.equals("")) {  //  如果r1有   且 r2有   则为 r1&.*\w连接\w.*&r2 或 r1&.*\W连接\W.*&r2
                        String newData =
                                "((" + r1 + "＆[\u0000-\uFFFF]*\\w)(\\w[\u0000-\uFFFF]*＆" + r3 + "))" + "|" +
                                        "((" + r1 + "＆[\u0000-\uFFFF]*\\W)(\\W[\u0000-\uFFFF]*＆" + r3 + "))";
                        translateResults.add(newData);
                    } else if (!r1.equals("") && r3.equals("")) {    //  如果r1有   且 r2没有 则为 r1&.*\W
                        String newData = r1 + "＆[\u0000-\uFFFF]*\\W";
                        translateResults.add(newData);
                    }
                }
            }
        }

        if (translateResults.isEmpty()) translateResults.add(treeNode.getData());
        return translateResults;
    }


    public static String translateRegexForLookAround(TreeNode treeNode) throws InterruptedException {
        // 后序遍历树 对于每一个树中的结点 根据assertType修改对应的写法 写的时候.*可以写成[\u0000-\uFFFF]*  &要写成＆   ~要写成～
        // AssertType.POSITIVE_AHEAD    r1(?=r2)r3   r1 ( r2.* & r3 )
        // AssertType.NEGATIVE_AHEAD    r1(?!r2)r3   r1 ( ~(r2.*) & r3 )
        // AssertType.POSITIVE_BEHIND   r1(?<=r2)r3  ( r1 & .*r2 ) r3
        // AssertType.NEGATIVE_BEHIND   r1(?<!r2)r3  ( r1 & ~(.*r2) ) r3

        // lookaround的父节点不可能是或结点 否则无意义  ((?!a)|a).*

        List<String> translateResults = new LinkedList<>();

        TreeNode treeNodeCopy = SerializationUtils.clone(treeNode);    // 深拷贝

        Stack<TreeNode> auxiliaryStack = new Stack<>();
        Stack<TreeNode> treeNodeStack = new Stack<>();
        auxiliaryStack.push(treeNodeCopy);
        while (!auxiliaryStack.isEmpty()) {
            TreeNode tmp = auxiliaryStack.pop();
            treeNodeStack.push(tmp);
            for (int i = 0; i < tmp.getChildCount(); i++) {
                auxiliaryStack.push(tmp.getChild(i));
            }
        }
        // 后序遍历处理
        while (!treeNodeStack.isEmpty()) {
            TreeNode node = treeNodeStack.pop();
            Constant.AssertType assertType = getAssertType(node);
            if (assertType != null) {
                String r2 = "";
                if (node.getChild(0).getChildCount() == 3) {
                    r2 = node.getChild(0).getChild(1).getData();
                } else {    // 若出现(?=) (?!) (?<=) (?<!)这种 相当于无lookaround 无需修改
                    continue;
                }
                String r1 = getR0(treeNodeCopy, node);
                String r3 = getR4(treeNodeCopy, node);


                if (assertType == Constant.AssertType.POSITIVE_AHEAD) {
                    String newData = "";    // r1 ( r2.* & r3 )
                    if (r3.equals("")) {
                        newData = r1 + "(" + r2 + "[\\s\\S]*)";
                    } else {
                        newData = r1 + "(" + r2 + "[\\s\\S]*" + "＆" + r3 + ")";
                    }
                    if (node.getParent() != null) {
                        String oldData = node.getParent().getData();
                        node.getParent().updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.getParent().updateTreeByModifyNode(oldData);
                    } else {
                        String oldData = node.getData();
                        node.updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.updateTreeByModifyNode(oldData);
                    }
                } else if (assertType == Constant.AssertType.NEGATIVE_AHEAD) {
                    String newData = "";    // r1 ( ~(r2.*) & r3 )
                    if (r3.equals("")) {
                        newData = r1 + "(" + "～(" + r2 + "[\\s\\S]*" + "))";
                    } else {
                        newData = r1 + "(" + "～(" + r2 + "[\\s\\S]*" + ")＆" + r3 + ")";
                    }
                    if (node.getParent() != null) {
                        String oldData = node.getParent().getData();
                        node.getParent().updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.getParent().updateTreeByModifyNode(oldData);
                    } else {
                        String oldData = node.getData();
                        node.updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.updateTreeByModifyNode(oldData);
                    }
                } else if (assertType == Constant.AssertType.POSITIVE_BEHIND) {
                    String newData = "";    // ( r1 & .*r2 ) r3
                    if (r1.equals("")) {
                        newData = "([\\s\\S]*" + r2 + ")" + r3;
                    } else {
                        newData = "(" + r1 + "＆[\\s\\S]*" + r2 + ")" + r3;
                    }
                    if (node.getParent() != null) {
                        String oldData = node.getData();
                        node.getParent().updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.getParent().updateTreeByModifyNode(oldData);
                    } else {
                        String oldData = node.getData();
                        node.updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.updateTreeByModifyNode(oldData);
                    }
                } else if (assertType == Constant.AssertType.NEGATIVE_BEHIND) {
                    String newData = "";    // ( r1 & ~(.*r2) ) r3
                    if (r1.equals("")) {
                        newData = "(～([\\s\\S]*" + r2 + "))" + r3;
                    } else {
                        newData = "(" + r1 + "＆～([\\s\\S]*" + r2 + "))" + r3;
                    }
                    if (node.getParent() != null) {
                        String oldData = node.getData();
                        node.getParent().updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.getParent().updateTreeByModifyNode(oldData);
                    } else {
                        String oldData = node.getData();
                        node.updateTreeByModifyNode(newData);
                        translateResults.add(treeNodeCopy.getData());
                        node.updateTreeByModifyNode(oldData);
                    }
                }
            }
        }

        if (translateResults.isEmpty()) translateResults.add(treeNode.getData());

//        System.out.println("translateResults = " + translateResults);

//        return treeNodeCopy.getData();
        return String.join("＆", translateResults);
    }

    public static List<String> matchedStrings = new ArrayList<>();
    public static String finalString;

    private static void generate(String strMatch, State state, List<Automaton> automataList) throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
        List<Transition> transitions = state.getSortedTransitions(true);
        if (transitions.size() == 0 || state.isAccept()) {
//            matchedStrings.add(strMatch);
            System.out.println(strMatch);
            boolean accept = true;
            for (int i = 0; i < automataList.size(); i++) {
                if (!automataList.get(i).run(strMatch)) {
                    accept = false;
                    break;
                }
            }
            if (accept) {
                finalString = strMatch;
                return;
            }
        }
        for (Transition transition : transitions) {
            for (char c = transition.getMin(); c <= transition.getMax(); ++c) {
                generate(strMatch + c, transition.getDest(), automataList);
            }
        }
    }

//    public static String getMatchedStrings(List<Automaton> automataList) throws InterruptedException {
//        Automaton automaton = automataList.get(0);
//        generate("", automaton.getInitialState(), automataList);
//        return finalString;
//    }


    private static boolean randomPrepared(
            String strMatch,
            State state,
            int minLength,
            int maxLength,
            List<Transition> transitions) throws InterruptedException {

        Random random = new Random();

        if (state.isAccept()) {
            if (strMatch.length() == maxLength) {
                return true;
            }
            if (random.nextInt() > 0.3 * Integer.MAX_VALUE && strMatch.length() >= minLength) {
                return true;
            }
        }

        return transitions.size() == 0;
    }


    /**
     * Generate and return a random String that match the pattern used in this Generex.
     *
     * @return
     */
    public String random(Automaton automaton) throws InterruptedException {
        return prepareRandom("", automaton.getInitialState(), 1, Integer.MAX_VALUE);
    }

    /**
     * Generate and return a random String that match the pattern used in this Generex, and the string has a length >=
     * <code>minLength</code>
     *
     * @param minLength
     * @return
     */
    public static String random(Automaton automaton, int minLength) throws InterruptedException {
        return prepareRandom("", automaton.getInitialState(), minLength, Integer.MAX_VALUE);
    }

    /**
     * Generate and return a random String that match the pattern used in this Generex, and the string has a length >=
     * <code>minLength</code> and <= <code>maxLength</code>
     *
     * @param minLength
     * @param maxLength
     * @return
     */
    public String random(Automaton automaton, int minLength, int maxLength) throws InterruptedException {
        return prepareRandom("", automaton.getInitialState(), minLength, maxLength);
    }

    private static String prepareRandom(String strMatch, State state, int minLength, int maxLength) throws InterruptedException {
        Random random = new Random();

        List<Transition> transitions = state.getSortedTransitions(false);
        Set<Integer> selectedTransitions = new HashSet<Integer>();
        String result = strMatch;

        for (int resultLength = -1;
             transitions.size() > selectedTransitions.size()
                     && (resultLength < minLength || resultLength > maxLength);
             resultLength = result.length()) {

            if (randomPrepared(strMatch, state, minLength, maxLength, transitions)) {
                return strMatch;
            }

            int nextInt = random.nextInt(transitions.size());
            if (!selectedTransitions.contains(nextInt)) {
                selectedTransitions.add(nextInt);

                Transition randomTransition = transitions.get(nextInt);
                int diff = randomTransition.getMax() - randomTransition.getMin() + 1;
                int randomOffset = diff > 0 ? random.nextInt(diff) : diff;
                char randomChar = (char) (randomOffset + randomTransition.getMin());
                result = prepareRandom(strMatch + randomChar, randomTransition.getDest(), minLength, maxLength);
            }
        }

        return result;
    }

    public static String getExampleByDkBricsAutomaton2(List<String> regexList, int minLength) throws InterruptedException {
        List<Automaton> automatonList = new ArrayList<>();
        for (int i = 0; i < regexList.size(); i++) {
//            String regex = repairLineFeedsCarriageReturnsAndTabCharacters(regexList.get(i));
            String regex = regexList.get(i);
            Automaton automaton = getAutomaton(regex);
            automatonList.add(automaton);
        }
        return getCommonMatchString(automatonList, minLength);
    }

    public static String getCommonMatchString(List<Automaton> automatonList, int minLength) throws InterruptedException {
        Automaton lengthAutomata = null;
        if (minLength > 0) {
            lengthAutomata = new RegExp(".{" + minLength + ",}").toAutomaton(false);
        }

        HashMap<String, Boolean> hashMap = new HashMap<>();
        for (int i = 0; i < automatonList.size(); i++) {
            Automaton automaton = automatonList.get(i);
            if (lengthAutomata != null) automaton = automaton.intersection(lengthAutomata);
            String example = automaton.getExample(true);
            if (example == null) continue;
            if (hashMap.containsKey(example)) {
                if (hashMap.get(example)) return example;
                else continue;
            }
            boolean accept = true;
            for (int j = 0; j < automatonList.size(); j++) {
                if (i == j) continue;
                if (!automatonList.get(j).run(example)) {
                    accept = false;
                    break;
                }
            }
            if (accept) {
                return example;
            } else {
                hashMap.put(example, false);
            }
        }
        Automaton automaton = automatonList.get(0);
        if (lengthAutomata != null) automaton = automaton.intersection(lengthAutomata);
        for (int i = 1; i < automatonList.size(); i++) {
            automaton = automaton.intersection(automatonList.get(i));
        }
        String example = automaton.getExample(true);
        return example;
    }


    public static void main(String[] args) throws InterruptedException {
//        String regex = "&lt;(span|font).*?(?:(?:(\\s?style=&quot;?).*?((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*(&quot;?)|(\\s?size=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(\\s?color=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(?=&gt;)).*?){3}&gt;";
        String regex = "(?:(?:(\\s?style=&quot;?).*?((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*((?:\\s?font-size:.+?\\s*(?:;|,|(?=&quot;))+)|(?:\\s?color:.+?\\s*(?:;|,|(?=&quot;))+))[^&quot;]*(&quot;?)|(\\s?size=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(\\s?color=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(?=&gt;)).*?){3}";
        regex = "\\w*";
//        regex = "(?:((\\s?color=&quot;?.*?(?:(?=\\s)|&quot;|(?=&gt;)))|(?=&gt;)).*?){3}";
//        TreeNode treeNode = PatternNQUtils.getReDoSTree(regex, "java");
//        printTree(treeNode);
        Automaton automaton = getAutomaton(regex);
        String exam = automaton.getExample(true);
        System.out.println(exam);
        System.out.println(exam.length());
        System.out.println(automaton.run(exam));
////        System.out.println(treeNode.getChild(3).containsLookAroundChild());
////        String trans = translateRegexForLookAround(treeNode);
////        List<String> list1 = new LinkedList<>();
////        list1.add(trans);
////        System.out.println(getExampleByDkBricsAutomaton(list1));
//        List<String> translateRegexForLookAroundList = getTranslateRegexForLookAroundList(treeNode);
//        System.out.println(translateRegexForLookAroundList);
//        List<Automaton> automatonList = new ArrayList<>();
//        for (int i = 0; i < translateRegexForLookAroundList.size(); i++) {
//            Automaton automaton = getAutomaton(translateRegexForLookAroundList.get(i));
//            automatonList.add(automaton);
//        }
////        Automaton automaton1 = automatonList.get(0).intersection(automatonList.get(1));
////        System.out.println(automaton1.getShortestExample(true));
//        System.out.println(getCommonMatchString(automatonList, 0));
    }
}
