package cn.ac.ios.Utils;

import java.util.regex.Pattern;

/**
 * 字符串中存在 反斜杠+u 开头 的Unicode字符。本类用于把那些Unicode字符串转换成汉字
 *
 * @author 张超
 */
public class UnicodeBackslashU {
    // 单个字符的正则表达式
    private static final String singlePattern = "[0-9a-fA-F]";
    // 4个字符的正则表达式
    private static final String pattern = singlePattern + singlePattern + singlePattern + singlePattern;
    private static final String pattern_2 = "\\{" + singlePattern + singlePattern + singlePattern + singlePattern + "\\}";


    /**
     * 把 \\u 开头的单字转成汉字，如 \\u6B65 ->　步
     *
     * @param str
     * @param i
     * @return
     */
    private static String ustartToCn(String str, int i) {
        StringBuilder sb = new StringBuilder();
        if (i == 1) {
            sb = new StringBuilder().append("0x")
                    .append(str.substring(2, 6));
        } else if (i == 2) {
            sb = new StringBuilder().append("0x")
                    .append(str.substring(3, 7));
        }
        // 这三行为什么要写???
//        if ("0x0000".equals(sb.toString())) {
//            sb = new StringBuilder("0x0001");
//        }
        Integer codeInteger = Integer.decode(sb.toString());
        int code = codeInteger.intValue();
        char c = (char) code;
        String re = String.valueOf(c);
        if (re.equals("]") || re.equals("-") || re.equals("[") || re.equals("(") || re.equals(")") || re.equals("*") || re.equals("?") || re.equals("+") || re.equals(".") || re.equals("\\") || re.equals("/") || re.equals("|") || re.equals("$") || re.equals("^")) {
            re = "\\" + re;
        }
        return re;
    }

    /**
     * 字符串是否以Unicode字符开头。约定Unicode字符以 \\u开头。
     *
     * @param str 字符串
     * @return true表示以Unicode字符开头.
     */
    private static int isStartWithUnicode(String str) {
        if (null == str || str.length() == 0) {
            return 0;
        }
        if (!str.startsWith("\\u")) {
            return 0;
        }
        // \u6B65
        if (str.length() < 6) {
            return 0;
        }
        String content = str.substring(2, 6);

        boolean isMatch = Pattern.matches(pattern, content);
        if (!isMatch) {
            if (str.length() < 8) {
                return 0;
            }
            content = str.substring(2, 8);
            isMatch = Pattern.matches(pattern_2, content);
            if (isMatch) {
                return 2;
            } else {
                return 0;
            }


        } else {
            return 1;
        }

    }

    /**
     * 字符串中，所有以 \\u 开头的UNICODE字符串，全部替换成汉字
     *
     * @param str
     * @return
     */
    public static String unicodeToCn(String str) {
        if (!str.contains("\\u")) {
            return str;
        }
        // 用于构建新的字符串
        StringBuilder sb = new StringBuilder();
        // 从左向右扫描字符串。tmpStr是还没有被扫描的剩余字符串。
        // 下面有两个判断分支：
        // 1. 如果剩余字符串是Unicode字符开头，就把Unicode转换成汉字，加到StringBuilder中。然后跳过这个Unicode字符。
        // 2.反之， 如果剩余字符串不是Unicode字符开头，把普通字符加入StringBuilder，向右跳过1.
        int length = str.length();
        for (int i = 0; i < length; ) {
            String tmpStr = str.substring(i);
            if (isStartWithUnicode(tmpStr) == 1) {
                int count = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (str.charAt(j) != '\\') {
                        break;
                    }
                    count++;
                }
                if (count == 1 || count == 3) {
                    sb.append(str.substring(i, i + 1));
                    i++;
                    continue;
                }
                sb.append(ustartToCn(tmpStr, 1));
                i += 6;
            } else if (isStartWithUnicode(tmpStr) == 2) {
                int count = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (str.charAt(j) != '\\') {
                        break;
                    }
                    count++;
                }
                if (count == 1 || count == 3) {
                    sb.append(str.substring(i, i + 1));
                    i++;
                    continue;
                }
                sb.append(ustartToCn(tmpStr, 2));
                i += 8;
            } else { // 分支2
                sb.append(str.substring(i, i + 1));
                i++;
            }
        }
        return sb.toString();
    }
}