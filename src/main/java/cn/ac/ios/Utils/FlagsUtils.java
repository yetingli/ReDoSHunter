package cn.ac.ios.Utils;


import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Bean.RegexBean;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.TreeNode.Utils.rewriteSlash;
import static cn.ac.ios.Utils.BracketUtils.*;
import static cn.ac.ios.Utils.Constant.*;
import static cn.ac.ios.Utils.RegexUtils.isNegateNode;

/**
 * @author pqc
 */
public class FlagsUtils {
    /**
     * 去掉RegExp 中的flags信息 i s
     *
     * @param node
     */
    public static TreeNode getNodeByRemoveRegExpFlag(TreeNode node) throws InterruptedException {
        if (node.getFlags().isEmpty()) {
            return node;
        }
        for (String str : node.getFlags()) {
            switch (str) {
                case "i":
                    removeI(node);
                    break;
                case "s":
                    removeS(node);
                    break;
                default:
                    break;
            }
        }
        return createReDoSTree(node.getData());
    }

    /**
     * 去除正则中的局部模式信息 i,s,x
     *
     * @param treeNode
     * @return
     */
    public static TreeNode getNodeByRemoveLocalFlag(TreeNode treeNode) throws InterruptedException {
        if (treeNode.getData().contains(FLAGS_X_START) || treeNode.getData().contains(FLAGS_X_END)) {
            TreeNode newNode = getNodeByRemoveLocalFlagX(treeNode);
            if (newNode != null) {
                treeNode = createReDoSTree(newNode.getData());
            } else {
                return createReDoSTree("!");
            }
        }
        if (treeNode.getData().contains(FLAGS_I_START) || treeNode.getData().contains(FLAGS_I_END)) {
            TreeNode newNode = getNodeByRemoveLocalFlagI(treeNode);
            if (newNode != null) {
                treeNode = createReDoSTree(newNode.getData());
            } else {
                return createReDoSTree("!");
            }
        }
        if (treeNode.getData().contains(FLAGS_S_START) || treeNode.getData().contains(FLAGS_S_END)) {
            TreeNode newNode = getNodeByRemoveLocalFlagS(treeNode);
            if (newNode != null) {
                treeNode = createReDoSTree(newNode.getData());
            } else {
                return createReDoSTree("!");
            }
        }
        return treeNode;
    }

    /**
     * 删除 LocalFlagX   Flag X包含的空格和 Flag X标志
     *
     * @param treeNode
     * @return
     */
    private static TreeNode getNodeByRemoveLocalFlagX(TreeNode treeNode) {
        if (isLocalFlagsX(treeNode.getData())) {
            return null;
        }
        List<TreeNode> children = treeNode.getChildList();
        if (children.isEmpty()) {
            return treeNode;
        }
        List<TreeNode> afterList = new ArrayList<>();
        boolean flag = false;
        for (TreeNode child : children) {
            if (flag) {
                if (child.getData().equals(FLAGS_X_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_X_END)) {
                    flag = false;
                } else {
                    afterList.add(removeLocalX(child));
                }
            } else {
                if (child.getData().equals(FLAGS_X_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_X_END)) {
                    flag = false;
                } else {
                    afterList.add(getNodeByRemoveLocalFlagX(child));
                }
            }
        }
        // 删除原有节点
        return treeNode.refactorChildren(afterList);
    }

    /**
     * 去除 <?i:>模式信息
     *
     * @param treeNode
     * @return
     */
    private static TreeNode getNodeByRemoveLocalFlagI(TreeNode treeNode) throws InterruptedException {
        if (isLocalFlagsI(treeNode.getData())) {
            return null;
        }
        List<TreeNode> children = treeNode.getChildList();
        if (children.isEmpty()) {
            return treeNode;
        }
        LinkedList<TreeNode> afterList = new LinkedList<>();
        boolean flag = false;
        for (TreeNode child : children) {
            if (flag) {
                if (child.getData().equals(FLAGS_I_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_I_END)) {
                    flag = false;
                } else {
                    afterList.add(removeLocalI(child));
                }
            } else {
                if (child.getData().equals(FLAGS_I_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_I_END)) {
                    flag = false;
                } else {
                    afterList.add(getNodeByRemoveLocalFlagI(child));
                }
            }
        }
        return treeNode.refactorChildren(afterList);
    }

    /**
     * 去掉局部模式 s  "."支持换行
     *
     * @return
     */
    private static TreeNode getNodeByRemoveLocalFlagS(TreeNode treeNode) throws InterruptedException {
        if (isLocalFlagsS(treeNode.getData())) {
            return null;
        }
        List<TreeNode> children = treeNode.getChildList();
        if (children.isEmpty()) {
            return treeNode;
        }
        List<TreeNode> afterList = new ArrayList<>();
        boolean flag = false;
        for (TreeNode child : children) {
            if (flag) {
                if (child.getData().equals(FLAGS_S_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_S_END)) {
                    flag = false;
                } else {
                    afterList.add(removeLocalS(child));
                }
            } else {
                if (child.getData().equals(FLAGS_S_START)) {
                    flag = true;
                } else if (child.getData().equals(FLAGS_S_END)) {
                    flag = false;
                } else {
                    afterList.add(getNodeByRemoveLocalFlagS(child));
                }
            }
        }
        // 删除原有节点
        return treeNode.refactorChildren(afterList);
    }

    /**
     * 去掉flag i
     *
     * @param treeNode
     */
    private static void removeI(TreeNode treeNode) throws InterruptedException {
        String data = treeNode.getData();
        if (isLocalFlagsX(data) || isLocalFlagsI(data) || isLocalFlagsS(data)) {
            return;
        }
        if (data.length() == 1) {
            letterReplace(treeNode);
        } else {
            LinkedList<TreeNode> children = (LinkedList<TreeNode>) treeNode.getChildList();

            // [a-z](a|b)[a-z] 特殊处理
            //  [] 中括号内部处理，不考虑中括号内部嵌套
            if (isBracketsNode(treeNode) || isNegateNode(treeNode)) {
                boolean isNegateNodeFlag = false;
                if (isNegateNode(treeNode)) isNegateNodeFlag = true;
                LinkedList<TreeNode> afterProcess = new LinkedList<>();
                Set<Character> letterSet = new LinkedHashSet<>();
                for (TreeNode child : children) {
                    // 如果是[^...]结点 则不将^加入到afterProcess中
                    if (isNegateNodeFlag && "^".equals(child.getData()) && child.isSecondChild()) continue;

                    // 单字母加入集合
                    if (child.getData().length() == 1 && Character.isLetter(child.getData().charAt(0))) {
                        letterSet.add(child.getData().charAt(0));
                    } else if ("-".equals(child.getData())) {
                        afterProcess.add(createReDoSTree("\\-"));
                    }
                    // 字母集合加入集合 如 a-z
                    else if (isCollectionLetter(child.getData())) {
                        letterSet.addAll(getLetterSet(child.getData()));
                    }
                    // 否则不作处理
                    else {
                        afterProcess.add(child);
                    }
                }
                // 添加新的字母节点
                if (letterSet.size() > 0) {
                    // 删除原有节点
                    for (int i = children.size() - 1; i >= 0; i--) {
                        treeNode.deleteChild(i);
                    }
                    treeNode.addChild(afterProcess.getFirst());
                    // 如果是[^...]结点 将^添加到treeNode中
                    if (isNegateNodeFlag) treeNode.addChild("^");

                    // 添加新的字母节点
                    ArrayList<String> letters = getLettersSimplyInsensitive(letterSet);
                    for (String str : letters) {
                        treeNode.addChild(str);
                    }
                    for (int i = 1; i < afterProcess.size() - 1; i++) {
                        treeNode.addChild(afterProcess.get(i));
                    }
                    // 添加以前的节点非字母节点
                    treeNode.addChild(afterProcess.getLast());
                }
            } else {
                for (TreeNode node : children) {
                    removeI(node);
                }
            }
        }
    }

    /**
     * 去掉flag s
     *
     * @param treeNode
     */
    private static void removeS(TreeNode treeNode) throws InterruptedException {
        if (FLAGS_REGEXP_S_REPLACE_PATTERN.equals(treeNode.getData())) {
            return;
        }
        if (".".equals(treeNode.getData())) {
            if (!isInBrackets(treeNode) && !isInNegateNode(treeNode)) {
                treeNode.updateTreeByModifyNode(FLAGS_REGEXP_S_REPLACE_PATTERN);
            }
        } else {
            LinkedList<TreeNode> children = (LinkedList<TreeNode>) treeNode.getChildList();
            for (TreeNode node : children) {
                removeS(node);
            }
        }
    }

    /**
     * 去掉局部flag i
     *
     * @param treeNode
     */
    private static TreeNode removeLocalI(TreeNode treeNode) throws InterruptedException {
        String data = treeNode.getData();
        if (isLocalFlagsI(data)) {
            return null;
        }
        if (isLocalFlagsX(data)) {
            return treeNode;
        }
        if (isLocalFlagsS(data)) {
            return treeNode;
        }
        // 单字母处理 直接替换
        else if (data.length() == 1) {
            return letterReplace(treeNode);
        } else {
            LinkedList<TreeNode> children = (LinkedList<TreeNode>) treeNode.getChildList();
            if (children.isEmpty()) {
                return treeNode;
            }
            //  [] 中括号内部处理，不考虑中括号内部嵌套
            if (isBracketsNode(treeNode)) {
                LinkedList<TreeNode> afterList = new LinkedList<>();
                Set<Character> letterSet = new LinkedHashSet<>();
                for (TreeNode child : children) {
                    // 单字母加入集合
                    if (child.getData().length() == 1 && Character.isLetter(child.getData().charAt(0))) {
                        letterSet.add(child.getData().charAt(0));
                    } else if ("-".equals(child.getData())) {
                        afterList.add(createReDoSTree("\\-"));
                    }
                    // 字母集合加入集合
                    else if (isCollectionLetter(child.getData())) {
                        letterSet.addAll(getLetterSet(child.getData()));
                    } else {
                        if (!isLocalFlagsI(child.getData())) {
                            afterList.add(removeLocalI(child));
                        }
                    }
                }
                // 添加新的字母节点
                if (letterSet.size() > 0) {
                    // 删除原有节点
                    for (int i = children.size() - 1; i >= 0; i--) {
                        treeNode.deleteChild(i);
                    }
                    treeNode.addChild(afterList.getFirst());
                    // 添加新的字母节点
                    ArrayList<String> letters = getLettersSimplyInsensitive(letterSet);
                    for (String str : letters) {
                        treeNode.addChild(str);
                    }
                    // 添加以前的节点非字母节点
                    for (int i = 1; i < afterList.size() - 1; i++) {
                        treeNode.addChild(afterList.get(i));
                    }
                    treeNode.addChild(afterList.getLast());
                }
            } else {
                LinkedList<TreeNode> afterList = new LinkedList<>();
                for (TreeNode node : children) {
                    afterList.add(removeLocalI(node));
                }
                treeNode.refactorChildren(afterList);
            }
        }
        return treeNode;
    }


    /**
     * 移除当前节点的空格
     *
     * @return
     */
    private static TreeNode removeLocalX(TreeNode treeNode) {
        if (" ".equals(treeNode.getData())) {
            return null;
        } else if (isLocalFlagsX(treeNode.getData())) {
            return null;
        } else if (treeNode.isLeaf() || isBracketsNode(treeNode)) {
            return treeNode;
        } else {
            LinkedList<TreeNode> children = (LinkedList<TreeNode>) treeNode.getChildList();
            LinkedList<TreeNode> afterList = new LinkedList<>();
            for (TreeNode node : children) {
                afterList.add(removeLocalX(node));
            }
            // 删除原有节点
            treeNode.refactorChildren(afterList);
        }
        return treeNode;
    }

    /**
     * 移除局部flag s
     *
     * @return
     */
    private static TreeNode removeLocalS(TreeNode treeNode) throws InterruptedException {
        if (".".equals(treeNode.getData())) {
            if (!isInBrackets(treeNode) && !isInNegateNode(treeNode)) {
                treeNode.updateTreeByModifyNode(FLAGS_REGEXP_S_REPLACE_PATTERN);
            }
            return treeNode;
        } else if (isLocalFlagsX(treeNode.getData())) {
            return null;
        } else if (treeNode.isLeaf()) {
            return treeNode;
        } else {
            LinkedList<TreeNode> children = (LinkedList<TreeNode>) treeNode.getChildList();
            LinkedList<TreeNode> afterList = new LinkedList<>();
            for (TreeNode node : children) {
                afterList.add(removeLocalS(node));
            }
            // 删除原有节点
            treeNode.refactorChildren(afterList);
        }
        return treeNode;
    }


    /**
     * 将单字母忽略大小写
     *
     * @param treeNode
     */
    private static TreeNode letterReplace(TreeNode treeNode) throws InterruptedException {
        String data = treeNode.getData();
        char c = data.charAt(0);
        if (Character.isLetter(c)) {
            if (Character.isLowerCase(c)) {
                data = "(?:" + data + "|" + data.toUpperCase() + ")";
            } else {
                data = "(?:" + data + "|" + data.toLowerCase() + ")";
            }
            treeNode.updateTreeByModifyNode(data);
        }
        return treeNode;
    }

    /**
     * 判断是否为特殊字符
     *
     * @param character
     * @return
     */
    public static boolean isSpecialCharacter(Character character) {
        for (Character sp : SPECIAL_CHARACTERS) {
            if (character.equals(sp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为特殊字符
     *
     * @param node
     * @return
     */
    public static boolean isSpecialCharacter(TreeNode node) {
        if (node.isLeaf() && node.getData().length() == 1) {
            Character character = node.getData().charAt(0);
            for (Character sp : SPECIAL_CHARACTERS) {
                if (character.equals(sp)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将正则表达式的 / /去掉，同时提取模式信息，需要在最开始处理
     * !!! warning: 有bug //.*:.*@
     * @param regex regex
     * @return 「regex flags」
     */
    public static RegexBean divideRegexByFlags(String regex) {
        // fix bug
        regex = rewriteSlash(regex);

        String flags = "";
        // 合法 RegExp
        if (Pattern.matches(REGEXP_REGEX, regex)) {
            Matcher matcher = PATTERN_REGEXP_REGEX_FLAGS.matcher(regex);
            if (matcher.find()) {
                flags = matcher.group().substring(1).toLowerCase();
                regex = regex.substring(1, regex.length() - flags.length() - 1);
            }
        } else {
            // 非法 RegExp
            if (regex.startsWith("/")) {
                int i = regex.length() - 1;
                boolean isfind = false;
                for (; i >= 1; i--) {
                    if ("/".equals(regex.substring(i, i + 1))) {
                        isfind = true;
                        break;
                    }
                }
                if (isfind) {
                    regex = regex.substring(1, i);
                }
            }
        }
        return new RegexBean(regex, flags);
    }

    /**
     * 将 (?i:...) 变为 (?i) ...(?-i),需要在最开始处理
     * warning: 将会产生多余的(?i) ...(?-i)信息.
     * bug: @rx (?i:([^a]+[(]*?))处理错误
     * @param regex 带(?i:)正则
     * @return (? i) ...(?-i) 的正则
     */
    public static String processLocalFlagI(String regex) {
        //对特殊字符进行处理 //todo 需要改进
        if (regex.contains("(?ni:")) {
            regex = regex.replace("(?ni:", "(?i:");
        }
        if (regex.contains("(?mi:")) {
            regex = regex.replace("(?mi:", "(?i:");
        }
        if (regex.contains("(?n:")) {
            regex = regex.replace("(?n:", "(?:");
        }
        if (regex.contains("(?in:")) {
            regex = regex.replace("(?in:", "(?i:");
        }
        if (regex.contains("(?n)")) {
            regex = regex.replace("(?n)", "");
        }
        if (regex.contains(FLAGS_I)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(regex.substring(0, regex.indexOf(FLAGS_I)));
            int start = regex.indexOf(FLAGS_I) + FLAGS_I.length();
            int i = start;
            int end;
            Stack<Character> stack = new Stack<>();
            while (i < regex.length()) {
                char c = regex.charAt(i);
                if ('(' == c && '\\' != regex.charAt(i - 1)) {
                    stack.push(c);
                } else if (')' == c && '\\' != regex.charAt(i - 1)) {
                    if (stack.isEmpty()) {
                        end = i;
                        String subRegex = regex.substring(start, end);
                        if (subRegex.contains(FLAGS_I)) {
                            subRegex = processLocalFlagI(subRegex);
                        }
                        stringBuilder.append("(?:").append(FLAGS_I_START).append(subRegex).append(FLAGS_I_END).append(")");
                        start = regex.substring(end + 1).indexOf(FLAGS_I);
                        if (start == -1) {
                            stringBuilder.append(regex.substring(end + 1));
                            break;
                        } else {
                            stringBuilder.append(regex, end + 1, start + end + 1);
                            start = end + (start + 1) + FLAGS_I.length();
                            i = start;
                        }
                    } else {
                        stack.pop();
                    }
                }
                i++;
            }
            return stringBuilder.toString();
        }
        return regex;
    }

    public static String processLocalFlag(String regex) {
        regex = processLocalFlagI(regex);
        regex = processLocalFlagS(regex);
        regex = processLocalFlagX(regex);
        return regex;

    }


    /**
     * 将 (?s:...) 变为 (?s) ...(?-s),需要在最开始处理
     * warning: 将会产生多余的(?s) ...(?-s)信息.
     *
     * @param regex 带(?s:)正则
     * @return (? s) ...(?-s) 的正则
     */
    public static String processLocalFlagS(String regex) {
        if (regex.contains(FLAGS_S)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(regex.substring(0, regex.indexOf(FLAGS_S)));
            int start = regex.indexOf(FLAGS_S) + FLAGS_S.length();
            int i = start;
            int end;
            Stack<Character> stack = new Stack<>();
            while (i < regex.length()) {
                char c = regex.charAt(i);
                if ('(' == c && '\\' != regex.charAt(i - 1)) {
                    stack.push(c);
                } else if (')' == c && '\\' != regex.charAt(i - 1)) {
                    if (stack.isEmpty()) {
                        end = i;
                        String subRegex = regex.substring(start, end);
                        if (subRegex.contains(FLAGS_S)) {
                            subRegex = processLocalFlagS(subRegex);
                        }
                        stringBuilder.append("(?:").append(FLAGS_S_START).append(subRegex).append(FLAGS_S_END).append(")");
                        start = regex.substring(end + 1).indexOf(FLAGS_S);
                        if (start == -1) {
                            stringBuilder.append(regex.substring(end + 1));
                            break;
                        } else {
                            stringBuilder.append(regex, end + 1, start + end + 1);
                            start = end + (start + 1) + FLAGS_S.length();
                            i = start;
                        }
                    } else {
                        stack.pop();
                    }
                }
                i++;
            }
            return stringBuilder.toString();
        }
        return regex;
    }

    /**
     * 将 (?s:...) 变为 (?s) ...(?-s),需要在最开始处理
     * warning: 将会产生多余的(?s) ...(?-s)信息.
     *
     * @param regex 带(?s:)正则
     * @return (? s) ...(?-s) 的正则
     */
    public static String processLocalFlagX(String regex) {
        if (regex.contains(FLAGS_X)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(regex.substring(0, regex.indexOf(FLAGS_X)));
            int start = regex.indexOf(FLAGS_X) + FLAGS_X.length();
            int i = start;
            int end;
            Stack<Character> stack = new Stack<>();
            while (i < regex.length()) {
                char c = regex.charAt(i);
                if ('(' == c && '\\' != regex.charAt(i - 1)) {
                    stack.push(c);
                } else if (')' == c && '\\' != regex.charAt(i - 1)) {
                    if (stack.isEmpty()) {
                        end = i;
                        String subRegex = regex.substring(start, end);
                        if (subRegex.contains(FLAGS_X)) {
                            subRegex = processLocalFlagX(subRegex);
                        }
                        stringBuilder.append("(?:").append(FLAGS_X_START).append(subRegex).append(FLAGS_X_END).append(")");
                        start = regex.substring(end + 1).indexOf(FLAGS_X);
                        if (start == -1) {
                            stringBuilder.append(regex.substring(end + 1));
                            break;
                        } else {
                            stringBuilder.append(regex, end + 1, start + end + 1);
                            start = end + (start + 1) + FLAGS_X.length();
                            i = start;
                        }
                    } else {
                        stack.pop();
                    }
                }
                i++;
            }
            return stringBuilder.toString();
        }
        return regex;
    }

    /**
     * 将 (?-i:...) 去替换为 (?:...)
     * .
     *
     * @param regex 带(?-i:...)正则 FLAGS_I_REMOVE
     * @return (? : ...) 的正则 FLAGS_I_REMOVE
     */
    public static String replaceLocalFlagGM(String regex) {
        regex = regex.replace(FLAGS_GM, FLAGS_GM_REMOVE);
        return regex;
    }

    /**
     * 判断是否为FLAGS_I标志，用于去除正则中的FLAGS_I标志
     *
     * @param s 当前节点值
     * @return 否为FLAGS_I标志
     */
    public static boolean isLocalFlagsI(String s) {
        return FLAGS_I_START.equals(s) || FLAGS_I_END.equals(s);
    }


    public static boolean isLocalFlagsX(String s) {
        return FLAGS_X_START.equals(s) || FLAGS_X_END.equals(s);
    }


    public static boolean isLocalFlagsS(String s) {
        return FLAGS_S_START.equals(s) || FLAGS_S_END.equals(s);
    }


    /**
     * 将(?imJUsx) 等模式化简，只保留 i,x,s
     *
     * @param regex (?mis)
     * @return 化简后的正则  (?s)(?i)
     * warning:返回顺序不可调换
     */
    public static String reduceLocalFlags(String regex) {
        Matcher matcher = PATTERN_FLAGS_REDUCES.matcher(regex);
        StringBuilder stringBuilder = new StringBuilder();
        int start = 0;
        while (matcher.find()) {
            int end = matcher.start();
            stringBuilder.append(regex, start, end);
            String group = matcher.group();
            Set<Character> set = new LinkedHashSet<>();
            group = group.replace("m", "");
            group = group.replace("J", "");
            group = group.replace("U", "");
            for (int i = 0; i < group.length(); i++) {
                char c = group.charAt(i);
                if (Character.isLetter(c)) {
                    set.add(c);
                }
            }
            ArrayList<Character> sortSet = new ArrayList<>(set);
            if (group.startsWith("(?-")) {
                sortSet.sort(Comparator.naturalOrder());
                for (Character character : sortSet) {
                    stringBuilder.append("(?-").append(character).append(")");
                }
            } else {
                sortSet.sort(Comparator.reverseOrder());
                for (Character character : sortSet) {
                    stringBuilder.append("(?").append(character).append(")");
                }
            }

            start = matcher.end();
        }
        stringBuilder.append(regex.substring(start));
        return stringBuilder.toString();
    }


    /**
     * 去除 模式 x 中的注释
     *
     * @param regex re
     * @return 删除注释的正则
     */
    public static String removeAnnotationByFlagX(String regex) {
        if (regex.contains(FLAGS_X_START)) {
            int start = regex.indexOf(FLAGS_X_START);
            int end = regex.indexOf(FLAGS_X_END);
            end = end == -1 ? regex.length() : end;
            for (int i = start + FLAGS_X_START.length(); i < end; i++) {
                if (regex.charAt(i) == '#' && regex.charAt(i - 1) != '\\') {
                    return regex.substring(0, i);
                }
            }
        }
        return regex;
    }
}
