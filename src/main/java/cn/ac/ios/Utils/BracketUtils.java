package cn.ac.ios.Utils;

import cn.ac.ios.TreeNode.TreeNode;

import java.util.*;

import static cn.ac.ios.Utils.Constant.*;
import static cn.ac.ios.Utils.RegexUtils.*;

/**
 * 处理中括号以及中括号内部集合的工具类
 *
 * @author pqc
 */
public class BracketUtils {


    /**
     * 判断是否为字母或数字集合 如 a-z 1-9
     * 识别 a-z
     * waring ：限制结合范围小于1000，防止耗时
     * @param data a-z
     */
    public static boolean isCollectionLetter(String data) {
        if (data.length() == 3) {
            if (data.charAt(2) - data.charAt(0) < 1000) {
                return "-".equals(data.substring(1, 2));
            }
        }
        return false;
    }

    /**
     * 判断是否为字母或数字集合 如 a-z 1-9
     * 识别 a-z
     * warning: 只能判断是否为字母或数字集合 [+-\/]中的+-\/就判断错误 应改用isGeneralizedCollectionSymbol
     * @param data a-z
     */
    public static boolean isCollectionSymbol(String data) {
        if (data.length() == 3) {
            return "-".equals(data.substring(1, 2));
        }
        return false;
    }

    // 判断当前结点是否为[a-z]中的a-z  [+-\/]中的+-\/
    public static boolean isGeneralizedCollectionSymbol(TreeNode treeNode) {
        if (! isInBrackets(treeNode) && ! isInNegateNode(treeNode)) return false;
        if (treeNode.getChildCount() == 3) {
            if (treeNode.getChild(1).getData().equals("-")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回 正则如 a-z 的所有字母集合
     *
     * @param data 如 a-z/A-B
     * @return [a, b, c, d, ...x, y, z]
     */
    public static Set<Character> getLetterSet(String data) {
        Set<Character> characterSet = new LinkedHashSet<>();
        char start = data.charAt(0);
        char end = data.charAt(2);

        for (; start <= end; start++) {
            characterSet.add(start);
            if (start == '\uFFFF') break;   // 防溢出
        }
        return characterSet;
    }

    /**
     * 从字母或数字集合 返回一组正则,区分大小写
     *
     * @param characterSet [a,b,c,...x,y,x]
     * @return a-z
     */
    public static ArrayList<String> getLettersSimply(Set<Character> characterSet) {
        ArrayList<String> letters = new ArrayList<>();
        ArrayList<Character> sortSet = new ArrayList<>(characterSet);
        sortSet.sort(Comparator.naturalOrder());
        if (characterSet.isEmpty()) {
            return letters;
        }

        String item = String.valueOf(sortSet.get(0));
        String temp = String.valueOf(sortSet.get(0));
        long start = sortSet.get(0);
        for (int i = 1; i < sortSet.size(); i++) {
            long end = sortSet.get(i);
            if (end - start == 1) {
                start++;
                item = temp + "-" + (char) end;
            } else {
                start = end;
                letters.add(item);
                item = String.valueOf((char) end);
                temp = String.valueOf((char) end);
            }
        }
        letters.add(item);
        return letters;
    }

    /**
     * 从字母或数字集合 返回一组正则,忽略大小写使用
     *
     * @param characterSet [a,b,c,...x,y,x]
     * @return a-z
     */
    public static ArrayList<String> getLettersSimplyInsensitive(Set<Character> characterSet) {

        ArrayList<String> letters = new ArrayList<>();
        Set<Character> newSet = new HashSet<>();
        for (Character character : characterSet) {
            if (Character.isUpperCase(character) || Character.isLowerCase(character)) {
                newSet.add(Character.toUpperCase(character));
                newSet.add(Character.toLowerCase(character));
            } else {
                newSet.add(character);
            }
        }

        ArrayList<Character> sortSet = new ArrayList<>(newSet);
        sortSet.sort(Comparator.naturalOrder());
        String item = String.valueOf(sortSet.get(0));
        String temp = String.valueOf(sortSet.get(0));
        Character start = sortSet.get(0);
        for (int i = 1; i < sortSet.size(); i++) {
            Character end = sortSet.get(i);
            if (end - start == 1) {
                start++;
                item = temp + "-" + end;
            } else {
                start = end;
                if ("-".equals(item)) {
                    item = "\\" + item;
                }
                letters.add(item);
                item = String.valueOf(end);
                temp = String.valueOf(end);
            }
        }
        if ("-".equals(item)) {
            item = "\\" + item;
        }
        letters.add(item);
        return letters;
    }

    /**
     * 判断是否为中括号内特殊字符
     *
     * @param character
     * @return
     */
    public static boolean isSpecialCharacterBracket(Character character) {
        for (Character sp : SPECIAL_CHARACTERS_BRACKET) {
            if (character.equals(sp)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否在SOME_CHARACTERS_NEED_ADD_A_BACKSLASH_BEFORE_IT集合内
    public static boolean isSomeCharactersNeedAddABackslashBeforeIt(String string) {
        for (Character sp: SOME_CHARACTERS_NEED_ADD_A_BACKSLASH_BEFORE_IT) {
            if (string.equals(sp.toString())) {
                return true;
            }
        }
        return false;
    }

    // 重载isSpecialCharacterBracket
    public static boolean isSpecialCharacterBracket(String string) {
        for (Character sp: SPECIAL_CHARACTERS_BRACKET) {
            if (string.equals(sp.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为中括号外部特殊字符 需要加\
     *
     * @param character
     * @return
     */
    public static boolean isSpecialCharacterNonBracket(Character character) {
        for (Character sp : SPECIAL_CHARACTERS_NON_BRACKET) {
            if (character.equals(sp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为中括号内 特殊字符加了\
     *
     * @param str "\\."
     * @return
     */
    @Deprecated
    public static boolean isSpecialCharacterBracketWithSlash(String str) {
        for (String sp : SPECIAL_CHARACTERS_BRACKET_WITH_SLASH) {
            if (str.equals(sp)) {
                return true;
            }
        }
        return false;
    }


    // 判断当前结点是否在lookaround结点中
    public static boolean isInLookAroundNode(TreeNode treeNode) {
        TreeNode temp = treeNode;
        while (!temp.isRoot()) {
            temp = temp.getParent();
            if (isLookAroundNode(temp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断该节点是否在方括号内
     *
     * @return
     */
    public static boolean isInBrackets(TreeNode treeNode) {
        TreeNode temp = treeNode;
        while (!temp.isRoot()) {
            temp = temp.getParent();
            if (isBracketsNode(temp)) {
                return true;
            }
        }
        return false;
    }

    // 判断当前结点是否在[^...]结点中
    public static boolean isInNegateNode(TreeNode treeNode) {
        TreeNode temp = treeNode;
        while (!temp.isRoot()) {
            temp = temp.getParent();
            if (isNegateNode(temp)) {
                return true;
            }
        }
        return false;
    }

//    // 判断是是否在或结点中
//    public static boolean isInOrNode(TreeNode treeNode) {
//        while (isGroupNode(treeNode.getParent())) {
//            treeNode = treeNode.getParent();
//        }
//        return isOrNode(treeNode);
//    }

    /**
     * 判断当前节点是否为[]节点
     *
     * @param treeNode
     * @return
     */
    public static boolean isBracketsNode(TreeNode treeNode) {
//        if (isGroupNode(treeNode)) {
//            return isBracketsNode(treeNode.getChild(1));
//        }
        String data = treeNode.getData();
        if (data.startsWith("[") && (data.endsWith("]") && !data.startsWith("[^"))) {
            if (treeNode.getChildCount() >= 2) {
                return "[".equals(treeNode.getChild(0).getData()) &&
                        "]".equals(treeNode.getChild(treeNode.getChildCount() - 1).getData()) &&
                        ! "^".equals(treeNode.getChild(1).getData());
            }
        }
        return false;
    }

    // 是交集结点 character_class && character_class && .. && character_class
    public static boolean isIntersectionNode(TreeNode treeNode) {
        String data = treeNode.getData();
        if (data.contains("&&") && treeNode.getChildCount() >= 4) {
            for (int i = 1, j = 2; i < treeNode.getChildCount() && j < treeNode.getChildCount(); i+= 3, j += 3) {
                if (!("&".equals(treeNode.getChild(i).getData()) && "&".equals(treeNode.getChild(j).getData()))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 将集合化简
     *
     * @param set   a,b,c,d ... x,y,z,\n,\r
     * @param level 化简等级
     * @return a-z,\n,\r
     */
    public static Set<String> simplifyLetters(Set<String> set, Constant.SimplyLevel level) {
        Set<Character> characterSet = new LinkedHashSet<>();
        Set<String> newSet = new LinkedHashSet<>();
        if (level == Constant.SimplyLevel.LOW) {
            // 当字母表长度大于500时，必须将非ascii码的unicode码转成集合，否则树太长解析速度会很慢，
            if (set.size() > 1000) {
                for (String str : set) {
                    if (str.length() == 1 &&
                            str.charAt(0) > 127 &&
                            (!(Character.isUpperCase(str.charAt(0)) || Character.isLowerCase(str.charAt(0))))) {
                        characterSet.add(str.charAt(0));
                    } else {
                        newSet.add(str);
                    }
                }
            } else {
                newSet.addAll(set);
            }
            // 字符集合大于300时，树过深，为防止超时，将unicode大于127的再次化简
            if (newSet.size() > 1000) {
                Set<String> reSet = new LinkedHashSet<>();
                for (String str : newSet) {
                    if (str.length() == 1 && str.charAt(0) > 127) {
                        characterSet.add(str.charAt(0));
                    } else {
                        reSet.add(str);
                    }
                }
                reSet.addAll(getLettersSimply(characterSet));
                return reSet;
            } else {
                newSet.addAll(getLettersSimply(characterSet));
                return newSet;
            }
        } else {
            for (String str : set) {
                if (str.length() == 1 && (Character.isLetterOrDigit(str.charAt(0)) || str.charAt(0) > 127)) {
                    characterSet.add(str.charAt(0));
                } else {
                    newSet.add(str);
                }
            }
            newSet.addAll(getLettersSimply(characterSet));
            if (level == Constant.SimplyLevel.HIGH) {
                if (newSet.containsAll(Arrays.asList("a-z", "A-Z", "0-9", "_"))) {
                    newSet.removeAll(Arrays.asList("a-z", "A-Z", "0-9", "_"));
                    newSet.add("\\w");
                }
                if (newSet.contains("0-9")) {
                    newSet.remove("0-9");
                    newSet.add("\\d");
                }
                if (newSet.containsAll(Arrays.asList("\\v", "\\r", "\\n", "\\t", "\\f", " "))) {
                    newSet.removeAll(Arrays.asList("\\v", "\\r", "\\n", "\\t", "\\f", " "));
                    newSet.add("\\s");
                }
                return newSet;
            }
        }
        return newSet;
    }
}
