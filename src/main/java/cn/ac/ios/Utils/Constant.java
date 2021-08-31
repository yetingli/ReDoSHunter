package cn.ac.ios.Utils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author pqc
 */
public class Constant {

    /**
     * - "g" for global
     * - "i" for ignoreCase
     * - "m" for multiline
     * - "u" for unicode
     * - "y" for sticky
     * <p>
     * 用于判断 re 是否是一个RegExp对象
     */
    public static final String REGEXP_REGEX = "^/.*(/[A-Za-z]+)$";

    public static final String REGEXP_ILLEGAL_REGEX = "^/.*/.*$";


    /**
     * 提取 RegExp对象中的flags
     */
    public static final String REGEXP_REGEX_FLAGS = "(/[A-za-z]+)$";
    public static final Pattern PATTERN_REGEXP_REGEX_FLAGS = Pattern.compile(REGEXP_REGEX_FLAGS);
    public static final String REGEXP_REGEX_ILLEGAL_FLAGS = "/.*$";

    //忽略大小写模式
    public static final String FLAGS_I = "(?i:";
    public static final String FLAGS_I_START = "(?i)";
    public static final String FLAGS_I_END = "(?-i)";

    // 此模式不处理，直接替换
    public static final String FLAGS_GM = "(?-i:";
    public static final String FLAGS_GM_REMOVE = "(?:";

    // s模式，. 可以换行
    public static final String FLAGS_S = "(?s:";
    public static final String FLAGS_S_START = "(?s)";
    public static final String FLAGS_S_END = "(?-s)";
    // PCRE .不支持\n
    // JavaScript .不支持\n \r \u2028 \u2029
    // Python .不支持\n
    // Java8 .不支持\r\n \n \r \u2028 \u2029 \u0085
    public static final String FLAGS_REGEXP_S_REPLACE_PATTERN = "(?:.|\\n|\\r)";
    public static final String FLAGS_REGEXP_S_REPLACE_PATTERN_IN_BRACKETS = ".\\n\\r";

    // x模式，. 可以换行
    public static final String FLAGS_X = "(?x:";
    public static final String FLAGS_X_START = "(?x)";
    public static final String FLAGS_X_END = "(?-x)";

    public static final String FLAGS_REDUCES = "\\(\\?-?[imJUsx]+\\)";
    public static final Pattern PATTERN_FLAGS_REDUCES = Pattern.compile(FLAGS_REDUCES);
    public static final String FLAGS_REDUCES_START = "\\(\\?[imJUsx]+\\)";
    public static final String FLAGS_REDUCES_END = "\\(\\?_[imJUsx]+\\)";

    public static final String COUNTING = "\\{0*\\d*,?0*\\d*\\}";

    // !!! warning 这个正则有bug 会匹配{1}, {01} ...
    public static final String EXTENDED_COUNTING = "\\*|\\+|\\{0*\\d*,?0*(?!1})\\d*\\}";   // 包含除了,1} 1}和?的所有counting

    public static final String ALL_COUNTING = "\\?|\\*|\\+|\\{0*\\d*,?0*\\d*\\}";   // 所有的counting写法

    public static final String EQUAL_COUNTING = "\\{0*(\\d*)(?:,0*(?:\\1))?\\}";    // 相等counting的写法 接受{m,n} (m == n) 和 {m}

    //特殊字符 中括号外，不加入字母节点
    public static final Character[] SPECIAL_CHARACTERS = {'*', '?', '+', '\\', '(', ')', '^', '$', '|', '['};

    // 需要添加"\" 再加入字母表
    public static final Character[] SPECIAL_CHARACTERS_BRACKET = {'*', '?', '+', '\\', '/', '(', ')', '^', '$', '|', '[', ']', '{', '}', '.', '-'};

    public static final Character[] SPECIAL_CHARACTERS_NON_BRACKET = {'[', ']', '{', '}', '-'};

    public static final String[] SPECIAL_CHARACTERS_BRACKET_WITH_SLASH = {"\\."};

    // 针对snort数据集中出现的{?写法 需要在{前加\ 暂不知是否还有其他需要加斜杠的
    public static final Character[] SOME_CHARACTERS_NEED_ADD_A_BACKSLASH_BEFORE_IT = {'{'};


    public static final String[] w_MATCH_CHARACTER_LIST = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
            "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
            "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "_"};
    public static final String[] d_MATCH_CHARACTER_LIST = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static final String[] s_MATCH_CHARACTER_LIST = {" ", "\\r", "\\n", "\\t", "\\f", "\\v"};

    public static final String[] dot_MATCH_CHARACTER_LIST = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g",
            "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1",
            "2", "3", "4", "5", "6", "7", "8", "9", "~", "!", "@", "#", "`", "%", "&", ":", "-", "_", "=", ";", "\"",
            "'", "^", "$", "+", "*", "/", ".", "{", "}", "[", "]", "(", ")", "<", ">", "?", ";", "|", "\\",
            "\r", "\n", "\t", "\f", "\u000b", " "};
    public static final List<String> dotMatchCharacter = new ArrayList<>(Arrays.asList(dot_MATCH_CHARACTER_LIST));


    public static final HashMap<String, List<String>> COMPLEMENTARY_SET_SYMBOL = new HashMap<>();

    static {
        COMPLEMENTARY_SET_SYMBOL.put("\\W", Arrays.asList(w_MATCH_CHARACTER_LIST));
        COMPLEMENTARY_SET_SYMBOL.put("\\D", Arrays.asList(d_MATCH_CHARACTER_LIST));
        COMPLEMENTARY_SET_SYMBOL.put("\\S", Arrays.asList(s_MATCH_CHARACTER_LIST));
    }

    public static final Set<Character> w_MATCH_CHARACTER = new HashSet<>();
    public static final Set<Character> d_MATCH_CHARACTER = new HashSet<>();
    public static final Set<Character> s_MATCH_CHARACTER = new HashSet<>();
    // 点实际匹配的字符 不匹配\r和\n 斜杠u000d是斜杠r 斜杠u000a是斜杠n
    public static final Set<Character> dot_MATCH_CHARACTER = new HashSet<>();

    static {
        for (String str : w_MATCH_CHARACTER_LIST) {
            w_MATCH_CHARACTER.add(str.charAt(0));
        }

        for (String str : d_MATCH_CHARACTER_LIST) {
            d_MATCH_CHARACTER.add(str.charAt(0));
        }

        for (String str : s_MATCH_CHARACTER_LIST) {
            s_MATCH_CHARACTER.add(str.charAt(0));
        }

        dot_MATCH_CHARACTER.addAll(BracketUtils.getLetterSet("\u0000-\u0009"));
        dot_MATCH_CHARACTER.addAll(BracketUtils.getLetterSet("\u000b-\u000c"));
        dot_MATCH_CHARACTER.addAll(BracketUtils.getLetterSet("\u000e-\uffff"));
    }

    // 在使用dk.brics.automaton时候需要加方括号的字符集
    public static final String[] SPECIAL_STRING_NEED_ADD_SQUARE_BRACKETS_FOR_DK_BRICS_AUTOMATON = {"\"", "<", "~", "@", "{", "&", "#"};
    // 因为在上面添加了&字符 所以交集符号会被添加方括号 reWriteMetaEscape(r1 & r2) 就有问题 所以调用的时候用 ＆来代替r1 & r2
    public static final HashMap<String, String> SPECIAL_STRING_NEED_REDUCTION_FOR_DK_BRICS_AUTOMATON = new HashMap<>();

    static {
        SPECIAL_STRING_NEED_REDUCTION_FOR_DK_BRICS_AUTOMATON.put("＆", "&"); // 交集
        SPECIAL_STRING_NEED_REDUCTION_FOR_DK_BRICS_AUTOMATON.put("～", "~"); // 取反
        SPECIAL_STRING_NEED_REDUCTION_FOR_DK_BRICS_AUTOMATON.put("＃", "#"); // 空语言
    }


    public static final HashMap<String, String> SLASH_SET_SYMBOL = new HashMap<>();

    static {
        // 添加 \w
        StringBuilder w = new StringBuilder("(");
        for (int i = 0; i < w_MATCH_CHARACTER_LIST.length; i++) {
            String str = w_MATCH_CHARACTER_LIST[i];
            if (i == w_MATCH_CHARACTER_LIST.length - 1) {
                w.append(str);
            } else {
                w.append(str).append("|");
            }
        }
        w.append(")");
        SLASH_SET_SYMBOL.put("\\w", w.toString());

        // 添加 \d
        StringBuilder d = new StringBuilder("(");
        for (int i = 0; i < d_MATCH_CHARACTER_LIST.length; i++) {
            String str = d_MATCH_CHARACTER_LIST[i];
            if (i == d_MATCH_CHARACTER_LIST.length - 1) {
                d.append(str);
            } else {
                d.append(str).append("|");
            }
        }
        d.append(")");
        SLASH_SET_SYMBOL.put("\\d", d.toString());

        // 添加 \s
        StringBuilder s = new StringBuilder("(");
        for (int i = 0; i < s_MATCH_CHARACTER_LIST.length; i++) {
            String str = s_MATCH_CHARACTER_LIST[i];
            if (i == s_MATCH_CHARACTER_LIST.length - 1) {
                s.append(str);
            } else {
                s.append(str).append("|");
            }
        }
        s.append(")");
        SLASH_SET_SYMBOL.put("\\s", s.toString());

    }

    // 八进制数转十六进制数 用于[\0] -> [\u0000], [\777] -> [\u01FF]
    public static final HashMap<String, String> OCTAL_TO_HEX = new HashMap<>();

    static {
        // 斜杠u000d是斜杠r 斜杠u000a是斜杠n
        OCTAL_TO_HEX.put("\\00", "\u0000");
        OCTAL_TO_HEX.put("\\01", "\u0001");
        OCTAL_TO_HEX.put("\\02", "\u0002");
        OCTAL_TO_HEX.put("\\03", "\u0003");
        OCTAL_TO_HEX.put("\\04", "\u0004");
        OCTAL_TO_HEX.put("\\05", "\u0005");
        OCTAL_TO_HEX.put("\\06", "\u0006");
        OCTAL_TO_HEX.put("\\07", "\u0007");
        OCTAL_TO_HEX.put("\\000", "\u0000");
        OCTAL_TO_HEX.put("\\001", "\u0001");
        OCTAL_TO_HEX.put("\\002", "\u0002");
        OCTAL_TO_HEX.put("\\003", "\u0003");
        OCTAL_TO_HEX.put("\\004", "\u0004");
        OCTAL_TO_HEX.put("\\005", "\u0005");
        OCTAL_TO_HEX.put("\\006", "\u0006");
        OCTAL_TO_HEX.put("\\007", "\u0007");
        OCTAL_TO_HEX.put("\\010", "\u0008");
        OCTAL_TO_HEX.put("\\011", "\u0009");
        OCTAL_TO_HEX.put("\\012", "\n"); // 斜杠u000A
        OCTAL_TO_HEX.put("\\013", "\u000B");
        OCTAL_TO_HEX.put("\\014", "\u000C");
        OCTAL_TO_HEX.put("\\015", "\r");  // 斜杠u000D
        OCTAL_TO_HEX.put("\\016", "\u000E");
        OCTAL_TO_HEX.put("\\017", "\u000F");
        OCTAL_TO_HEX.put("\\020", "\u0010");
        OCTAL_TO_HEX.put("\\021", "\u0011");
        OCTAL_TO_HEX.put("\\022", "\u0012");
        OCTAL_TO_HEX.put("\\023", "\u0013");
        OCTAL_TO_HEX.put("\\024", "\u0014");
        OCTAL_TO_HEX.put("\\025", "\u0015");
        OCTAL_TO_HEX.put("\\026", "\u0016");
        OCTAL_TO_HEX.put("\\027", "\u0017");
        OCTAL_TO_HEX.put("\\030", "\u0018");
        OCTAL_TO_HEX.put("\\031", "\u0019");
        OCTAL_TO_HEX.put("\\032", "\u001A");
        OCTAL_TO_HEX.put("\\033", "\u001B");
        OCTAL_TO_HEX.put("\\034", "\u001C");
        OCTAL_TO_HEX.put("\\035", "\u001D");
        OCTAL_TO_HEX.put("\\036", "\u001E");
        OCTAL_TO_HEX.put("\\037", "\u001F");
        OCTAL_TO_HEX.put("\\040", "\u0020");
        OCTAL_TO_HEX.put("\\041", "\u0021");
        OCTAL_TO_HEX.put("\\042", "\""); // 斜杠u0022是双引号
        OCTAL_TO_HEX.put("\\043", "\u0023");
        OCTAL_TO_HEX.put("\\044", "\u0024");
        OCTAL_TO_HEX.put("\\045", "\u0025");
        OCTAL_TO_HEX.put("\\046", "\u0026");
        OCTAL_TO_HEX.put("\\047", "\u0027");
        OCTAL_TO_HEX.put("\\050", "\u0028");
        OCTAL_TO_HEX.put("\\051", "\u0029");
        OCTAL_TO_HEX.put("\\052", "\u002A");
        OCTAL_TO_HEX.put("\\053", "\u002B");
        OCTAL_TO_HEX.put("\\054", "\u002C");
        OCTAL_TO_HEX.put("\\055", "\u002D");
        OCTAL_TO_HEX.put("\\056", "\u002E");
        OCTAL_TO_HEX.put("\\057", "\u002F");
        OCTAL_TO_HEX.put("\\060", "\u0030");
        OCTAL_TO_HEX.put("\\061", "\u0031");
        OCTAL_TO_HEX.put("\\062", "\u0032");
        OCTAL_TO_HEX.put("\\063", "\u0033");
        OCTAL_TO_HEX.put("\\064", "\u0034");
        OCTAL_TO_HEX.put("\\065", "\u0035");
        OCTAL_TO_HEX.put("\\066", "\u0036");
        OCTAL_TO_HEX.put("\\067", "\u0037");
        OCTAL_TO_HEX.put("\\070", "\u0038");
        OCTAL_TO_HEX.put("\\071", "\u0039");
        OCTAL_TO_HEX.put("\\072", "\u003A");
        OCTAL_TO_HEX.put("\\073", "\u003B");
        OCTAL_TO_HEX.put("\\074", "\u003C");
        OCTAL_TO_HEX.put("\\075", "\u003D");
        OCTAL_TO_HEX.put("\\076", "\u003E");
        OCTAL_TO_HEX.put("\\077", "\u003F");


        OCTAL_TO_HEX.put("\\0", "\u0000");
        OCTAL_TO_HEX.put("\\1", "\u0001");
        OCTAL_TO_HEX.put("\\2", "\u0002");
        OCTAL_TO_HEX.put("\\3", "\u0003");
        OCTAL_TO_HEX.put("\\4", "\u0004");
        OCTAL_TO_HEX.put("\\5", "\u0005");
        OCTAL_TO_HEX.put("\\6", "\u0006");
        OCTAL_TO_HEX.put("\\7", "\u0007");
        OCTAL_TO_HEX.put("\\10", "\u0008");
        OCTAL_TO_HEX.put("\\11", "\u0009");
        OCTAL_TO_HEX.put("\\12", "\n"); // 斜杠u000A
        OCTAL_TO_HEX.put("\\13", "\u000B");
        OCTAL_TO_HEX.put("\\14", "\u000C");
        OCTAL_TO_HEX.put("\\15", "\r");  // 斜杠u000D
        OCTAL_TO_HEX.put("\\16", "\u000E");
        OCTAL_TO_HEX.put("\\17", "\u000F");
        OCTAL_TO_HEX.put("\\20", "\u0010");
        OCTAL_TO_HEX.put("\\21", "\u0011");
        OCTAL_TO_HEX.put("\\22", "\u0012");
        OCTAL_TO_HEX.put("\\23", "\u0013");
        OCTAL_TO_HEX.put("\\24", "\u0014");
        OCTAL_TO_HEX.put("\\25", "\u0015");
        OCTAL_TO_HEX.put("\\26", "\u0016");
        OCTAL_TO_HEX.put("\\27", "\u0017");
        OCTAL_TO_HEX.put("\\30", "\u0018");
        OCTAL_TO_HEX.put("\\31", "\u0019");
        OCTAL_TO_HEX.put("\\32", "\u001A");
        OCTAL_TO_HEX.put("\\33", "\u001B");
        OCTAL_TO_HEX.put("\\34", "\u001C");
        OCTAL_TO_HEX.put("\\35", "\u001D");
        OCTAL_TO_HEX.put("\\36", "\u001E");
        OCTAL_TO_HEX.put("\\37", "\u001F");
        OCTAL_TO_HEX.put("\\40", "\u0020");
        OCTAL_TO_HEX.put("\\41", "\u0021");
        OCTAL_TO_HEX.put("\\42", "\"");  // 斜杠u0022是双引号
        OCTAL_TO_HEX.put("\\43", "\u0023");
        OCTAL_TO_HEX.put("\\44", "\u0024");
        OCTAL_TO_HEX.put("\\45", "\u0025");
        OCTAL_TO_HEX.put("\\46", "\u0026");
        OCTAL_TO_HEX.put("\\47", "\u0027");
        OCTAL_TO_HEX.put("\\50", "\u0028");
        OCTAL_TO_HEX.put("\\51", "\u0029");
        OCTAL_TO_HEX.put("\\52", "\u002A");
        OCTAL_TO_HEX.put("\\53", "\u002B");
        OCTAL_TO_HEX.put("\\54", "\u002C");
        OCTAL_TO_HEX.put("\\55", "\u002D");
        OCTAL_TO_HEX.put("\\56", "\u002E");
        OCTAL_TO_HEX.put("\\57", "\u002F");
        OCTAL_TO_HEX.put("\\60", "\u0030");
        OCTAL_TO_HEX.put("\\61", "\u0031");
        OCTAL_TO_HEX.put("\\62", "\u0032");
        OCTAL_TO_HEX.put("\\63", "\u0033");
        OCTAL_TO_HEX.put("\\64", "\u0034");
        OCTAL_TO_HEX.put("\\65", "\u0035");
        OCTAL_TO_HEX.put("\\66", "\u0036");
        OCTAL_TO_HEX.put("\\67", "\u0037");
        OCTAL_TO_HEX.put("\\70", "\u0038");
        OCTAL_TO_HEX.put("\\71", "\u0039");
        OCTAL_TO_HEX.put("\\72", "\u003A");
        OCTAL_TO_HEX.put("\\73", "\u003B");
        OCTAL_TO_HEX.put("\\74", "\u003C");
        OCTAL_TO_HEX.put("\\75", "\u003D");
        OCTAL_TO_HEX.put("\\76", "\u003E");
        OCTAL_TO_HEX.put("\\77", "\u003F");
        OCTAL_TO_HEX.put("\\100", "\u0040");
        OCTAL_TO_HEX.put("\\101", "\u0041");
        OCTAL_TO_HEX.put("\\102", "\u0042");
        OCTAL_TO_HEX.put("\\103", "\u0043");
        OCTAL_TO_HEX.put("\\104", "\u0044");
        OCTAL_TO_HEX.put("\\105", "\u0045");
        OCTAL_TO_HEX.put("\\106", "\u0046");
        OCTAL_TO_HEX.put("\\107", "\u0047");
        OCTAL_TO_HEX.put("\\110", "\u0048");
        OCTAL_TO_HEX.put("\\111", "\u0049");
        OCTAL_TO_HEX.put("\\112", "\u004A");
        OCTAL_TO_HEX.put("\\113", "\u004B");
        OCTAL_TO_HEX.put("\\114", "\u004C");
        OCTAL_TO_HEX.put("\\115", "\u004D");
        OCTAL_TO_HEX.put("\\116", "\u004E");
        OCTAL_TO_HEX.put("\\117", "\u004F");
        OCTAL_TO_HEX.put("\\120", "\u0050");
        OCTAL_TO_HEX.put("\\121", "\u0051");
        OCTAL_TO_HEX.put("\\122", "\u0052");
        OCTAL_TO_HEX.put("\\123", "\u0053");
        OCTAL_TO_HEX.put("\\124", "\u0054");
        OCTAL_TO_HEX.put("\\125", "\u0055");
        OCTAL_TO_HEX.put("\\126", "\u0056");
        OCTAL_TO_HEX.put("\\127", "\u0057");
        OCTAL_TO_HEX.put("\\130", "\u0058");
        OCTAL_TO_HEX.put("\\131", "\u0059");
        OCTAL_TO_HEX.put("\\132", "\u005A");
        OCTAL_TO_HEX.put("\\133", "\u005B");
        OCTAL_TO_HEX.put("\\134", "\\"); // 斜杠u005C是反斜杠\
        OCTAL_TO_HEX.put("\\135", "\u005D");
        OCTAL_TO_HEX.put("\\136", "\u005E");
        OCTAL_TO_HEX.put("\\137", "\u005F");
        OCTAL_TO_HEX.put("\\140", "\u0060");
        OCTAL_TO_HEX.put("\\141", "\u0061");
        OCTAL_TO_HEX.put("\\142", "\u0062");
        OCTAL_TO_HEX.put("\\143", "\u0063");
        OCTAL_TO_HEX.put("\\144", "\u0064");
        OCTAL_TO_HEX.put("\\145", "\u0065");
        OCTAL_TO_HEX.put("\\146", "\u0066");
        OCTAL_TO_HEX.put("\\147", "\u0067");
        OCTAL_TO_HEX.put("\\150", "\u0068");
        OCTAL_TO_HEX.put("\\151", "\u0069");
        OCTAL_TO_HEX.put("\\152", "\u006A");
        OCTAL_TO_HEX.put("\\153", "\u006B");
        OCTAL_TO_HEX.put("\\154", "\u006C");
        OCTAL_TO_HEX.put("\\155", "\u006D");
        OCTAL_TO_HEX.put("\\156", "\u006E");
        OCTAL_TO_HEX.put("\\157", "\u006F");
        OCTAL_TO_HEX.put("\\160", "\u0070");
        OCTAL_TO_HEX.put("\\161", "\u0071");
        OCTAL_TO_HEX.put("\\162", "\u0072");
        OCTAL_TO_HEX.put("\\163", "\u0073");
        OCTAL_TO_HEX.put("\\164", "\u0074");
        OCTAL_TO_HEX.put("\\165", "\u0075");
        OCTAL_TO_HEX.put("\\166", "\u0076");
        OCTAL_TO_HEX.put("\\167", "\u0077");
        OCTAL_TO_HEX.put("\\170", "\u0078");
        OCTAL_TO_HEX.put("\\171", "\u0079");
        OCTAL_TO_HEX.put("\\172", "\u007A");
        OCTAL_TO_HEX.put("\\173", "\u007B");
        OCTAL_TO_HEX.put("\\174", "\u007C");
        OCTAL_TO_HEX.put("\\175", "\u007D");
        OCTAL_TO_HEX.put("\\176", "\u007E");
        OCTAL_TO_HEX.put("\\177", "\u007F");
        OCTAL_TO_HEX.put("\\200", "\u0080");
        OCTAL_TO_HEX.put("\\201", "\u0081");
        OCTAL_TO_HEX.put("\\202", "\u0082");
        OCTAL_TO_HEX.put("\\203", "\u0083");
        OCTAL_TO_HEX.put("\\204", "\u0084");
        OCTAL_TO_HEX.put("\\205", "\u0085");
        OCTAL_TO_HEX.put("\\206", "\u0086");
        OCTAL_TO_HEX.put("\\207", "\u0087");
        OCTAL_TO_HEX.put("\\210", "\u0081");
        OCTAL_TO_HEX.put("\\211", "\u0089");
        OCTAL_TO_HEX.put("\\212", "\u008A");
        OCTAL_TO_HEX.put("\\213", "\u008B");
        OCTAL_TO_HEX.put("\\214", "\u008C");
        OCTAL_TO_HEX.put("\\215", "\u008D");
        OCTAL_TO_HEX.put("\\216", "\u008E");
        OCTAL_TO_HEX.put("\\217", "\u008F");
        OCTAL_TO_HEX.put("\\220", "\u0090");
        OCTAL_TO_HEX.put("\\221", "\u0091");
        OCTAL_TO_HEX.put("\\222", "\u0092");
        OCTAL_TO_HEX.put("\\223", "\u0093");
        OCTAL_TO_HEX.put("\\224", "\u0094");
        OCTAL_TO_HEX.put("\\225", "\u0095");
        OCTAL_TO_HEX.put("\\226", "\u0096");
        OCTAL_TO_HEX.put("\\227", "\u0097");
        OCTAL_TO_HEX.put("\\230", "\u0098");
        OCTAL_TO_HEX.put("\\231", "\u0099");
        OCTAL_TO_HEX.put("\\232", "\u009A");
        OCTAL_TO_HEX.put("\\233", "\u009B");
        OCTAL_TO_HEX.put("\\234", "\u009C");
        OCTAL_TO_HEX.put("\\235", "\u009D");
        OCTAL_TO_HEX.put("\\236", "\u009E");
        OCTAL_TO_HEX.put("\\237", "\u009F");
        OCTAL_TO_HEX.put("\\240", "\u00A0");
        OCTAL_TO_HEX.put("\\241", "\u00A1");
        OCTAL_TO_HEX.put("\\242", "\u00A2");
        OCTAL_TO_HEX.put("\\243", "\u00A3");
        OCTAL_TO_HEX.put("\\244", "\u00A4");
        OCTAL_TO_HEX.put("\\245", "\u00A5");
        OCTAL_TO_HEX.put("\\246", "\u00A6");
        OCTAL_TO_HEX.put("\\247", "\u00A7");
        OCTAL_TO_HEX.put("\\250", "\u00A8");
        OCTAL_TO_HEX.put("\\251", "\u00A9");
        OCTAL_TO_HEX.put("\\252", "\u00AA");
        OCTAL_TO_HEX.put("\\253", "\u00AB");
        OCTAL_TO_HEX.put("\\254", "\u00AC");
        OCTAL_TO_HEX.put("\\255", "\u00AD");
        OCTAL_TO_HEX.put("\\256", "\u00AE");
        OCTAL_TO_HEX.put("\\257", "\u00AF");
        OCTAL_TO_HEX.put("\\260", "\u00B0");
        OCTAL_TO_HEX.put("\\261", "\u00B1");
        OCTAL_TO_HEX.put("\\262", "\u00B2");
        OCTAL_TO_HEX.put("\\263", "\u00B3");
        OCTAL_TO_HEX.put("\\264", "\u00B4");
        OCTAL_TO_HEX.put("\\265", "\u00B5");
        OCTAL_TO_HEX.put("\\266", "\u00B6");
        OCTAL_TO_HEX.put("\\267", "\u00B7");
        OCTAL_TO_HEX.put("\\270", "\u00B8");
        OCTAL_TO_HEX.put("\\271", "\u00B9");
        OCTAL_TO_HEX.put("\\272", "\u00BA");
        OCTAL_TO_HEX.put("\\273", "\u00BB");
        OCTAL_TO_HEX.put("\\274", "\u00BC");
        OCTAL_TO_HEX.put("\\275", "\u00BD");
        OCTAL_TO_HEX.put("\\276", "\u00BE");
        OCTAL_TO_HEX.put("\\277", "\u00BF");
        OCTAL_TO_HEX.put("\\300", "\u00C0");
        OCTAL_TO_HEX.put("\\301", "\u00C1");
        OCTAL_TO_HEX.put("\\302", "\u00C2");
        OCTAL_TO_HEX.put("\\303", "\u00C3");
        OCTAL_TO_HEX.put("\\304", "\u00C4");
        OCTAL_TO_HEX.put("\\305", "\u00C5");
        OCTAL_TO_HEX.put("\\306", "\u00C6");
        OCTAL_TO_HEX.put("\\307", "\u00C7");
        OCTAL_TO_HEX.put("\\310", "\u00C8");
        OCTAL_TO_HEX.put("\\311", "\u00C9");
        OCTAL_TO_HEX.put("\\312", "\u00CA");
        OCTAL_TO_HEX.put("\\313", "\u00CB");
        OCTAL_TO_HEX.put("\\314", "\u00CC");
        OCTAL_TO_HEX.put("\\315", "\u00CD");
        OCTAL_TO_HEX.put("\\316", "\u00CE");
        OCTAL_TO_HEX.put("\\317", "\u00CF");
        OCTAL_TO_HEX.put("\\320", "\u00D0");
        OCTAL_TO_HEX.put("\\321", "\u00D1");
        OCTAL_TO_HEX.put("\\322", "\u00D2");
        OCTAL_TO_HEX.put("\\323", "\u00D3");
        OCTAL_TO_HEX.put("\\324", "\u00D4");
        OCTAL_TO_HEX.put("\\325", "\u00D5");
        OCTAL_TO_HEX.put("\\326", "\u00D6");
        OCTAL_TO_HEX.put("\\327", "\u00D7");
        OCTAL_TO_HEX.put("\\330", "\u00D8");
        OCTAL_TO_HEX.put("\\331", "\u00D9");
        OCTAL_TO_HEX.put("\\332", "\u00DA");
        OCTAL_TO_HEX.put("\\333", "\u00DB");
        OCTAL_TO_HEX.put("\\334", "\u00DC");
        OCTAL_TO_HEX.put("\\335", "\u00DD");
        OCTAL_TO_HEX.put("\\336", "\u00DE");
        OCTAL_TO_HEX.put("\\337", "\u00DF");
        OCTAL_TO_HEX.put("\\340", "\u00E0");
        OCTAL_TO_HEX.put("\\341", "\u00E1");
        OCTAL_TO_HEX.put("\\342", "\u00E2");
        OCTAL_TO_HEX.put("\\343", "\u00E3");
        OCTAL_TO_HEX.put("\\344", "\u00E4");
        OCTAL_TO_HEX.put("\\345", "\u00E5");
        OCTAL_TO_HEX.put("\\346", "\u00E6");
        OCTAL_TO_HEX.put("\\347", "\u00E7");
        OCTAL_TO_HEX.put("\\350", "\u00E8");
        OCTAL_TO_HEX.put("\\351", "\u00E9");
        OCTAL_TO_HEX.put("\\352", "\u00EA");
        OCTAL_TO_HEX.put("\\353", "\u00EB");
        OCTAL_TO_HEX.put("\\354", "\u00EC");
        OCTAL_TO_HEX.put("\\355", "\u00ED");
        OCTAL_TO_HEX.put("\\356", "\u00EE");
        OCTAL_TO_HEX.put("\\357", "\u00EF");
        OCTAL_TO_HEX.put("\\360", "\u00F0");
        OCTAL_TO_HEX.put("\\361", "\u00F1");
        OCTAL_TO_HEX.put("\\362", "\u00F2");
        OCTAL_TO_HEX.put("\\363", "\u00F3");
        OCTAL_TO_HEX.put("\\364", "\u00F4");
        OCTAL_TO_HEX.put("\\365", "\u00F5");
        OCTAL_TO_HEX.put("\\366", "\u00F6");
        OCTAL_TO_HEX.put("\\367", "\u00F7");
        OCTAL_TO_HEX.put("\\370", "\u00F8");
        OCTAL_TO_HEX.put("\\371", "\u00F9");
        OCTAL_TO_HEX.put("\\372", "\u00FA");
        OCTAL_TO_HEX.put("\\373", "\u00FB");
        OCTAL_TO_HEX.put("\\374", "\u00FC");
        OCTAL_TO_HEX.put("\\375", "\u00FD");
        OCTAL_TO_HEX.put("\\376", "\u00FE");
        OCTAL_TO_HEX.put("\\377", "\u00FF");
        OCTAL_TO_HEX.put("\\400", "\u0100");
        OCTAL_TO_HEX.put("\\401", "\u0101");
        OCTAL_TO_HEX.put("\\402", "\u0102");
        OCTAL_TO_HEX.put("\\403", "\u0103");
        OCTAL_TO_HEX.put("\\404", "\u0104");
        OCTAL_TO_HEX.put("\\405", "\u0105");
        OCTAL_TO_HEX.put("\\406", "\u0106");
        OCTAL_TO_HEX.put("\\407", "\u0107");
        OCTAL_TO_HEX.put("\\410", "\u0108");
        OCTAL_TO_HEX.put("\\411", "\u0109");
        OCTAL_TO_HEX.put("\\412", "\u010A");
        OCTAL_TO_HEX.put("\\413", "\u010B");
        OCTAL_TO_HEX.put("\\414", "\u010C");
        OCTAL_TO_HEX.put("\\415", "\u010D");
        OCTAL_TO_HEX.put("\\416", "\u010E");
        OCTAL_TO_HEX.put("\\417", "\u010F");
        OCTAL_TO_HEX.put("\\420", "\u0110");
        OCTAL_TO_HEX.put("\\421", "\u0111");
        OCTAL_TO_HEX.put("\\422", "\u0112");
        OCTAL_TO_HEX.put("\\423", "\u0113");
        OCTAL_TO_HEX.put("\\424", "\u0114");
        OCTAL_TO_HEX.put("\\425", "\u0115");
        OCTAL_TO_HEX.put("\\426", "\u0116");
        OCTAL_TO_HEX.put("\\427", "\u0117");
        OCTAL_TO_HEX.put("\\430", "\u0118");
        OCTAL_TO_HEX.put("\\431", "\u0119");
        OCTAL_TO_HEX.put("\\432", "\u011A");
        OCTAL_TO_HEX.put("\\433", "\u011B");
        OCTAL_TO_HEX.put("\\434", "\u011C");
        OCTAL_TO_HEX.put("\\435", "\u011D");
        OCTAL_TO_HEX.put("\\436", "\u011E");
        OCTAL_TO_HEX.put("\\437", "\u011F");
        OCTAL_TO_HEX.put("\\440", "\u0120");
        OCTAL_TO_HEX.put("\\441", "\u0121");
        OCTAL_TO_HEX.put("\\442", "\u0122");
        OCTAL_TO_HEX.put("\\443", "\u0123");
        OCTAL_TO_HEX.put("\\444", "\u0124");
        OCTAL_TO_HEX.put("\\445", "\u0125");
        OCTAL_TO_HEX.put("\\446", "\u0126");
        OCTAL_TO_HEX.put("\\447", "\u0127");
        OCTAL_TO_HEX.put("\\450", "\u0128");
        OCTAL_TO_HEX.put("\\451", "\u0129");
        OCTAL_TO_HEX.put("\\452", "\u012A");
        OCTAL_TO_HEX.put("\\453", "\u012B");
        OCTAL_TO_HEX.put("\\454", "\u012C");
        OCTAL_TO_HEX.put("\\455", "\u012D");
        OCTAL_TO_HEX.put("\\456", "\u012E");
        OCTAL_TO_HEX.put("\\457", "\u012F");
        OCTAL_TO_HEX.put("\\460", "\u0130");
        OCTAL_TO_HEX.put("\\461", "\u0131");
        OCTAL_TO_HEX.put("\\462", "\u0132");
        OCTAL_TO_HEX.put("\\463", "\u0133");
        OCTAL_TO_HEX.put("\\464", "\u0134");
        OCTAL_TO_HEX.put("\\465", "\u0135");
        OCTAL_TO_HEX.put("\\466", "\u0136");
        OCTAL_TO_HEX.put("\\467", "\u0137");
        OCTAL_TO_HEX.put("\\470", "\u0138");
        OCTAL_TO_HEX.put("\\471", "\u0139");
        OCTAL_TO_HEX.put("\\472", "\u013A");
        OCTAL_TO_HEX.put("\\473", "\u013B");
        OCTAL_TO_HEX.put("\\474", "\u013C");
        OCTAL_TO_HEX.put("\\475", "\u013D");
        OCTAL_TO_HEX.put("\\476", "\u013E");
        OCTAL_TO_HEX.put("\\477", "\u013F");
        OCTAL_TO_HEX.put("\\500", "\u0140");
        OCTAL_TO_HEX.put("\\501", "\u0141");
        OCTAL_TO_HEX.put("\\502", "\u0142");
        OCTAL_TO_HEX.put("\\503", "\u0143");
        OCTAL_TO_HEX.put("\\504", "\u0144");
        OCTAL_TO_HEX.put("\\505", "\u0145");
        OCTAL_TO_HEX.put("\\506", "\u0146");
        OCTAL_TO_HEX.put("\\507", "\u0147");
        OCTAL_TO_HEX.put("\\510", "\u0148");
        OCTAL_TO_HEX.put("\\511", "\u0149");
        OCTAL_TO_HEX.put("\\512", "\u014A");
        OCTAL_TO_HEX.put("\\513", "\u014B");
        OCTAL_TO_HEX.put("\\514", "\u014C");
        OCTAL_TO_HEX.put("\\515", "\u014D");
        OCTAL_TO_HEX.put("\\516", "\u014E");
        OCTAL_TO_HEX.put("\\517", "\u014F");
        OCTAL_TO_HEX.put("\\520", "\u0150");
        OCTAL_TO_HEX.put("\\521", "\u0151");
        OCTAL_TO_HEX.put("\\522", "\u0152");
        OCTAL_TO_HEX.put("\\523", "\u0153");
        OCTAL_TO_HEX.put("\\524", "\u0154");
        OCTAL_TO_HEX.put("\\525", "\u0155");
        OCTAL_TO_HEX.put("\\526", "\u0156");
        OCTAL_TO_HEX.put("\\527", "\u0157");
        OCTAL_TO_HEX.put("\\530", "\u0158");
        OCTAL_TO_HEX.put("\\531", "\u0159");
        OCTAL_TO_HEX.put("\\532", "\u015A");
        OCTAL_TO_HEX.put("\\533", "\u015B");
        OCTAL_TO_HEX.put("\\534", "\u015C");
        OCTAL_TO_HEX.put("\\535", "\u015D");
        OCTAL_TO_HEX.put("\\536", "\u015E");
        OCTAL_TO_HEX.put("\\537", "\u015F");
        OCTAL_TO_HEX.put("\\540", "\u0160");
        OCTAL_TO_HEX.put("\\541", "\u0161");
        OCTAL_TO_HEX.put("\\542", "\u0162");
        OCTAL_TO_HEX.put("\\543", "\u0163");
        OCTAL_TO_HEX.put("\\544", "\u0164");
        OCTAL_TO_HEX.put("\\545", "\u0165");
        OCTAL_TO_HEX.put("\\546", "\u0166");
        OCTAL_TO_HEX.put("\\547", "\u0167");
        OCTAL_TO_HEX.put("\\550", "\u0168");
        OCTAL_TO_HEX.put("\\551", "\u0169");
        OCTAL_TO_HEX.put("\\552", "\u016A");
        OCTAL_TO_HEX.put("\\553", "\u016B");
        OCTAL_TO_HEX.put("\\554", "\u016C");
        OCTAL_TO_HEX.put("\\555", "\u016D");
        OCTAL_TO_HEX.put("\\556", "\u016E");
        OCTAL_TO_HEX.put("\\557", "\u016F");
        OCTAL_TO_HEX.put("\\560", "\u0170");
        OCTAL_TO_HEX.put("\\561", "\u0171");
        OCTAL_TO_HEX.put("\\562", "\u0172");
        OCTAL_TO_HEX.put("\\563", "\u0173");
        OCTAL_TO_HEX.put("\\564", "\u0174");
        OCTAL_TO_HEX.put("\\565", "\u0175");
        OCTAL_TO_HEX.put("\\566", "\u0176");
        OCTAL_TO_HEX.put("\\567", "\u0177");
        OCTAL_TO_HEX.put("\\570", "\u0178");
        OCTAL_TO_HEX.put("\\571", "\u0179");
        OCTAL_TO_HEX.put("\\572", "\u017A");
        OCTAL_TO_HEX.put("\\573", "\u017B");
        OCTAL_TO_HEX.put("\\574", "\u017C");
        OCTAL_TO_HEX.put("\\575", "\u017D");
        OCTAL_TO_HEX.put("\\576", "\u017E");
        OCTAL_TO_HEX.put("\\577", "\u017F");
        OCTAL_TO_HEX.put("\\600", "\u0180");
        OCTAL_TO_HEX.put("\\601", "\u0181");
        OCTAL_TO_HEX.put("\\602", "\u0182");
        OCTAL_TO_HEX.put("\\603", "\u0183");
        OCTAL_TO_HEX.put("\\604", "\u0184");
        OCTAL_TO_HEX.put("\\605", "\u0185");
        OCTAL_TO_HEX.put("\\606", "\u0186");
        OCTAL_TO_HEX.put("\\607", "\u0187");
        OCTAL_TO_HEX.put("\\610", "\u0188");
        OCTAL_TO_HEX.put("\\611", "\u0189");
        OCTAL_TO_HEX.put("\\612", "\u018A");
        OCTAL_TO_HEX.put("\\613", "\u018B");
        OCTAL_TO_HEX.put("\\614", "\u018C");
        OCTAL_TO_HEX.put("\\615", "\u018D");
        OCTAL_TO_HEX.put("\\616", "\u018E");
        OCTAL_TO_HEX.put("\\617", "\u018F");
        OCTAL_TO_HEX.put("\\620", "\u0190");
        OCTAL_TO_HEX.put("\\621", "\u0191");
        OCTAL_TO_HEX.put("\\622", "\u0192");
        OCTAL_TO_HEX.put("\\623", "\u0193");
        OCTAL_TO_HEX.put("\\624", "\u0194");
        OCTAL_TO_HEX.put("\\625", "\u0195");
        OCTAL_TO_HEX.put("\\626", "\u0196");
        OCTAL_TO_HEX.put("\\627", "\u0197");
        OCTAL_TO_HEX.put("\\630", "\u0198");
        OCTAL_TO_HEX.put("\\631", "\u0199");
        OCTAL_TO_HEX.put("\\632", "\u019A");
        OCTAL_TO_HEX.put("\\633", "\u019B");
        OCTAL_TO_HEX.put("\\634", "\u019C");
        OCTAL_TO_HEX.put("\\635", "\u019D");
        OCTAL_TO_HEX.put("\\636", "\u019E");
        OCTAL_TO_HEX.put("\\637", "\u019F");
        OCTAL_TO_HEX.put("\\640", "\u01A0");
        OCTAL_TO_HEX.put("\\641", "\u01A1");
        OCTAL_TO_HEX.put("\\642", "\u01A2");
        OCTAL_TO_HEX.put("\\643", "\u01A3");
        OCTAL_TO_HEX.put("\\644", "\u01A4");
        OCTAL_TO_HEX.put("\\645", "\u01A5");
        OCTAL_TO_HEX.put("\\646", "\u01A6");
        OCTAL_TO_HEX.put("\\647", "\u01A7");
        OCTAL_TO_HEX.put("\\650", "\u01A8");
        OCTAL_TO_HEX.put("\\651", "\u01A9");
        OCTAL_TO_HEX.put("\\652", "\u01AA");
        OCTAL_TO_HEX.put("\\653", "\u01AB");
        OCTAL_TO_HEX.put("\\654", "\u01AC");
        OCTAL_TO_HEX.put("\\655", "\u01AD");
        OCTAL_TO_HEX.put("\\656", "\u01AE");
        OCTAL_TO_HEX.put("\\657", "\u01AF");
        OCTAL_TO_HEX.put("\\660", "\u01B0");
        OCTAL_TO_HEX.put("\\661", "\u01B1");
        OCTAL_TO_HEX.put("\\662", "\u01B2");
        OCTAL_TO_HEX.put("\\663", "\u01B3");
        OCTAL_TO_HEX.put("\\664", "\u01B4");
        OCTAL_TO_HEX.put("\\665", "\u01B5");
        OCTAL_TO_HEX.put("\\666", "\u01B6");
        OCTAL_TO_HEX.put("\\667", "\u01B7");
        OCTAL_TO_HEX.put("\\670", "\u0188");
        OCTAL_TO_HEX.put("\\671", "\u01B9");
        OCTAL_TO_HEX.put("\\672", "\u01BA");
        OCTAL_TO_HEX.put("\\673", "\u01BB");
        OCTAL_TO_HEX.put("\\674", "\u01BC");
        OCTAL_TO_HEX.put("\\675", "\u01BD");
        OCTAL_TO_HEX.put("\\676", "\u01BE");
        OCTAL_TO_HEX.put("\\677", "\u01BF");
        OCTAL_TO_HEX.put("\\700", "\u01C0");
        OCTAL_TO_HEX.put("\\701", "\u01C1");
        OCTAL_TO_HEX.put("\\702", "\u01C2");
        OCTAL_TO_HEX.put("\\703", "\u01C3");
        OCTAL_TO_HEX.put("\\704", "\u01C4");
        OCTAL_TO_HEX.put("\\705", "\u01C5");
        OCTAL_TO_HEX.put("\\706", "\u01C6");
        OCTAL_TO_HEX.put("\\707", "\u01C7");
        OCTAL_TO_HEX.put("\\710", "\u01C8");
        OCTAL_TO_HEX.put("\\711", "\u01C9");
        OCTAL_TO_HEX.put("\\712", "\u01CA");
        OCTAL_TO_HEX.put("\\713", "\u01CB");
        OCTAL_TO_HEX.put("\\714", "\u01CC");
        OCTAL_TO_HEX.put("\\715", "\u01CD");
        OCTAL_TO_HEX.put("\\716", "\u01CE");
        OCTAL_TO_HEX.put("\\717", "\u01CF");
        OCTAL_TO_HEX.put("\\720", "\u01D0");
        OCTAL_TO_HEX.put("\\721", "\u01D1");
        OCTAL_TO_HEX.put("\\722", "\u01D2");
        OCTAL_TO_HEX.put("\\723", "\u01D3");
        OCTAL_TO_HEX.put("\\724", "\u01D4");
        OCTAL_TO_HEX.put("\\725", "\u01D5");
        OCTAL_TO_HEX.put("\\726", "\u01D6");
        OCTAL_TO_HEX.put("\\727", "\u01D7");
        OCTAL_TO_HEX.put("\\730", "\u01D8");
        OCTAL_TO_HEX.put("\\731", "\u01D9");
        OCTAL_TO_HEX.put("\\732", "\u01DA");
        OCTAL_TO_HEX.put("\\733", "\u01DB");
        OCTAL_TO_HEX.put("\\734", "\u01DC");
        OCTAL_TO_HEX.put("\\735", "\u01DD");
        OCTAL_TO_HEX.put("\\736", "\u01DE");
        OCTAL_TO_HEX.put("\\737", "\u01DF");
        OCTAL_TO_HEX.put("\\740", "\u01E0");
        OCTAL_TO_HEX.put("\\741", "\u01E1");
        OCTAL_TO_HEX.put("\\742", "\u01E2");
        OCTAL_TO_HEX.put("\\743", "\u01E3");
        OCTAL_TO_HEX.put("\\744", "\u01E4");
        OCTAL_TO_HEX.put("\\745", "\u01E5");
        OCTAL_TO_HEX.put("\\746", "\u01E6");
        OCTAL_TO_HEX.put("\\747", "\u01E7");
        OCTAL_TO_HEX.put("\\750", "\u01E8");
        OCTAL_TO_HEX.put("\\751", "\u01E9");
        OCTAL_TO_HEX.put("\\752", "\u01EA");
        OCTAL_TO_HEX.put("\\753", "\u01EB");
        OCTAL_TO_HEX.put("\\754", "\u01EC");
        OCTAL_TO_HEX.put("\\755", "\u01ED");
        OCTAL_TO_HEX.put("\\756", "\u01EE");
        OCTAL_TO_HEX.put("\\757", "\u01EF");
        OCTAL_TO_HEX.put("\\760", "\u01F0");
        OCTAL_TO_HEX.put("\\761", "\u01F1");
        OCTAL_TO_HEX.put("\\762", "\u01F2");
        OCTAL_TO_HEX.put("\\763", "\u01F3");
        OCTAL_TO_HEX.put("\\764", "\u01F4");
        OCTAL_TO_HEX.put("\\765", "\u01F5");
        OCTAL_TO_HEX.put("\\766", "\u01F6");
        OCTAL_TO_HEX.put("\\767", "\u01F7");
        OCTAL_TO_HEX.put("\\770", "\u01F8");
        OCTAL_TO_HEX.put("\\771", "\u01F9");
        OCTAL_TO_HEX.put("\\772", "\u01FA");
        OCTAL_TO_HEX.put("\\773", "\u01FB");
        OCTAL_TO_HEX.put("\\774", "\u01FC");
        OCTAL_TO_HEX.put("\\775", "\u01FD");
        OCTAL_TO_HEX.put("\\776", "\u01FE");
        OCTAL_TO_HEX.put("\\777", "\u01FF");


        // 特殊处理的 为了兼容snort数据集
        OCTAL_TO_HEX.put("\\8", "8");
    }

    // 特殊斜杠字符 在不同语言下解释不一样
    public static final HashMap<String, String> SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA = new HashMap<>();
    public static final HashMap<String, String> SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON = new HashMap<>();
    public static final HashMap<String, String> SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT = new HashMap<>();
    static {
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\a", "\u0007");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c0","\u0070");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c1","\u0071");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c2","\u0072");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c3","\u0073");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c4","\u0074");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c5","\u0075");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c6","\u0076");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c7","\u0077");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c8","\u0078");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c9","\u0079");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\ca","\u0021");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cA","\u0001");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cb","\"");  // 斜杠u0022是双引号
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cB","\u0002");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cc","\u0023");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cC","\u0003");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cd","\u0024");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cD","\u0004");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\ce","\u0025");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cE","\u0005");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cf","\u0026");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cF","\u0006");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cg","\u0027");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cG","\u0007");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\ch","\u0028");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cH","\u0008");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\ci","\u0029");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cI","\u0009");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cj","\u002A");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cJ","\n"); // 斜杠u000A
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\ck","\u002B");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cK","\u000B");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cl","\u002C");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cL","\u000C");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cm","\u002D");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cM","\r");  // 斜杠u000D
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cn","\u002E");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cN","\u000E");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\co","\u002F");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cO","\u000F");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cp","\u0030");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cP","\u0010");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cq","\u0031");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cQ","\u0011");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cr","\u0032");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cR","\u0012");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cs","\u0033");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cS","\u0013");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\ct","\u0034");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cT","\u0014");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cu","\u0035");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cU","\u0015");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cv","\u0036");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cV","\u0016");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cw","\u0037");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cW","\u0017");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cx","\u0038");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cX","\u0018");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cy","\u0039");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cY","\u0019");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cz","\u003A");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\cZ","\u001A");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c`","\u0020");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c~","\u003E");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c!","\u0061");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c@","\u0000");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c#","\u0063");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c$","\u0064");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c%","\u0065");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c^","\u001E");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c&","\u0066");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c*","\u006A");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c(","\u0068");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c)","\u0069");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c-","\u006D");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c_","\u001F");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c=","\u007D");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c+","\u006B");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c[","\u001B");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c{","\u003B");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c]","\u001D");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c}","\u003D");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c\\","\u001C");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c|","\u003C");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c;","\u007B");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c:","\u007A");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c'","\u0067");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c\"","\u0062");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c,","\u006C");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c<","\u007C");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c.","\u006E");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c>","\u007E");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c/","\u006F");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\c?","\u007F");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\e","\u001b");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\h","[\u0009\u0020\u00a0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000]");   // 任取其中一个
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\G","");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\H","[^\u0009\u0020\u00a0\u1680\u180e\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200a\u202f\u205f\u3000]");   // 任取其中一个
        //TODO: 处理\Q...\E
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\R","[\n\u000b\u000c\r\u0085\u2028\u2029]");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVA.put("\\V","[^\n\u000b\u000c\r\u0085\u2028\u2029]");


        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\a", "\u0007");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\c", "c");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\e","\u001b");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\g","g");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\h","h");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\i","i");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\j","j");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\k","k");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\l","l");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\m","m");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\o","o");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\p","p");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\q","q");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\y","y");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\z","z");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\C","C");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\E","E");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\F","F");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\G","G");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\I","I");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\J","J");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\K","K");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\L","L");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\M","M");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\N","N");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\O","O");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\P","P");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\Q","Q");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\R","R");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\T","T");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\V","V");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\W","W");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\X","X");
        SPECIAL_BACKSLASH_CHARACTER_FOR_PYTHON.put("\\Y","Y");

        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\a", "a");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\c", "\\\\c");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\ca","\u0021");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cA","\u0001");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cb","\"");  // 斜杠u0022是双引号
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cB","\u0002");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cc","\u0023");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cC","\u0003");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cd","\u0024");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cD","\u0004");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\ce","\u0025");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cE","\u0005");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cf","\u0026");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cF","\u0006");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cg","\u0027");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cG","\u0007");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\ch","\u0028");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cH","\u0008");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\ci","\u0029");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cI","\u0009");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cj","\u002A");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cJ","\n"); // 斜杠u000A
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\ck","\u002B");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cK","\u000B");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cl","\u002C");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cL","\u000C");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cm","\u002D");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cM","\r");  // 斜杠u000D
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cn","\u002E");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cN","\u000E");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\co","\u002F");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cO","\u000F");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cp","\u0030");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cP","\u0010");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cq","\u0031");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cQ","\u0011");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cr","\u0032");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cR","\u0012");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cs","\u0033");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cS","\u0013");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\ct","\u0034");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cT","\u0014");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cu","\u0035");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cU","\u0015");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cv","\u0036");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cV","\u0016");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cw","\u0037");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cW","\u0017");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cx","\u0038");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cX","\u0018");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cy","\u0039");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cY","\u0019");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cz","\u003A");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\cZ","\u001A");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\e","e");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\g","g");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\h","h");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\i","i");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\j","j");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\l","l");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\m","m");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\o","o");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\p","p");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\q","q");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\x","x");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\y","y");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\z","z");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\A","A");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\C","C");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\E","E");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\F","F");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\G","G");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\H","H");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\I","I");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\J","J");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\K","K");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\L","L");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\M","M");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\N","N");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\O","O");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\P","P");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\Q","Q");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\R","R");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\T","T");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\U","U");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\V","V");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\W","W");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\X","X");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\Y","Y");
        SPECIAL_BACKSLASH_CHARACTER_FOR_JAVASCRIPT.put("\\Z","Z");


    }


    // 删group name所用正则 （后面没有引用）
    // public static final String groupNameRegex = "\\((\\?P<[^?<>=!]+?>).*?\\)";
    // public static final String groupNameRegexForNoQuote = "\\((\\?P<[^?<>=!]+?>).*?";
//    public static final String groupNameRegexForNoQuote = "\\((\\?P?<[^?<>=!]+?>).*?";
    public static final String groupNameRegexForNoQuote = "\\((\\?(?:(?:P?<[^?<>=!]+?>)|(?:'[^?<>=!]+?'))).*?";
    public static final Pattern pattern_groupNameRegexForNoQuote = Pattern.compile(groupNameRegexForNoQuote);

    // 删group name所用正则 （后面有引用 e.g. (?P<named_group>cool)[a-z ]+(?P=named_group)kkk
    // 注意现在是不支持嵌套
    // public static final String groupNameRegexForQuote = "\\(\\?P\\<([^?<>=!]+?)\\>([^)]+)\\).*?(\\(\\?P\\=\\1\\)).*?";
    // 注意以下这两种
    // (?P<dup>\w+\s+)(abc)(?P=dup)
    // (?P<dup>(\w+\s+))(abc)(?P=dup)
    // (?<test>abc(?P<test2>def))
    // (?P<dup>\w+\s+)(abc)(?P=dup)
    // (?P<dup>(\w+\s+))(abc)(?P=dup)
//     public static final String groupNameRegexForQuote = "\\(\\?P?\\<([^?<>=!]+?)\\>([^()]*?)\\).*?(\\(\\?P\\=\\1\\)).*?";
//     public static final String groupNameRegexForQuoteCheck = "\\(\\?P?\\<([^?<>=!]+?)\\>(.*?)\\).*?(\\(\\?P\\=\\1\\)).*?";
//    "^\(\?P?\<([^?<>=!]+?)\>(.*)\)$"
//    public static final String groupNameRegexForReDoSTreeNode = "^\\(\\?P?<([^?<>=!]+?)>";
//    public static final String groupNameRegexForReDoSTreeNode = "^\\((\\?(?:(?:P?<[^?<>=!]+?>)|(?:'[^?<>=!]+?')))";
//    public static final String groupNameRegexForReDoSTreeNode = "^\\(\\?(?:(?:P?<([^?<>=!]+?)>)|(?:'[^?<>=!]+?'))";
    public static final String groupNameRegexForReDoSTreeNode = "^\\(\\?(?:(?:P?<([^?<>=!]+?)>)|(?:'([^?<>=!]+?)'))";
    public static final Pattern pattern_groupNameRegexForReDoSTreeNode = Pattern.compile(groupNameRegexForReDoSTreeNode);


    // 删除非贪婪匹配所用正则
    public static final String nonGreedyMatching = "(?<!\\\\)(?:\\+|\\*|\\?|\\{\\d+\\}|\\{\\d+,\\}|\\{\\d+,\\d+\\})([+?])";
    public static final Pattern pattern_nonGreedyMatching = Pattern.compile(nonGreedyMatching);

    // 处理POSIX正则表达式字符组所用正则
    // https://wenku.baidu.com/view/6194c12fcfc789eb172dc89f.html#
    // https://blog.csdn.net/shangboerds/article/details/7555332
    public static final String posixCharacterClass = "\\[.*?(\\[\\:(?:alnum|alpha|ascii|blank|cntrl|digit|graph|lower|print|punct|space|upper|word|xdigit):]).*?]";
    public static final Pattern pattern_posixCharacterClass = Pattern.compile(posixCharacterClass);


    // 用于处理{,4}这种类型的正则 注意\{,4}不匹配
    public static final String specialCountingRegex = "(?<!\\\\)\\{,\\d+}";
    public static final Pattern pattern_specialCountingRegex = Pattern.compile(specialCountingRegex);

    // 删除注释(?#...)所用正则
    public static final String annotationRegex = "(\\(\\?\\#.*?\\))";
    public static final Pattern pattern_annotationRegex = Pattern.compile(annotationRegex);

    // 处理\x 正则
    public static final String ESCAPE_HEXADECIMAL_REGEX_1 = "\\\\x[0123456789abcdefABCDEF]{2}";
    public static final Pattern PATTERN_ESCAPE_HEXADECIMAL_REGEX_1 = Pattern.compile(ESCAPE_HEXADECIMAL_REGEX_1);
    public static final String ESCAPE_HEXADECIMAL_REGEX_2 = "\\\\x\\{[0123456789abcdefABCDEF]{4,5}\\}";
    public static final Pattern PATTERN_ESCAPE_HEXADECIMAL_REGEX_2 = Pattern.compile(ESCAPE_HEXADECIMAL_REGEX_2);
    public static final String ESCAPE_HEXADECIMAL_REGEX_3 = "\\\\[01234567]{3}";
    public static final Pattern PATTERN_ESCAPE_HEXADECIMAL_REGEX_3 = Pattern.compile(ESCAPE_HEXADECIMAL_REGEX_3);

    // 判断奇怪反向引用所用正则
    //         \gn             reference by number
    public static final String BACKSLASH_G_N_REGEX_1 = "^\\\\g\\d+$";
    public static final Pattern PATTERN_BACKSLASH_G_N_REGEX_1 = Pattern.compile(BACKSLASH_G_N_REGEX_1);
    //         \g{n}           reference by number
    public static final String BACKSLASH_G_N_REGEX_2 = "^\\\\g\\{\\d+}$";
    public static final Pattern PATTERN_BACKSLASH_G_N_REGEX_2 = Pattern.compile(BACKSLASH_G_N_REGEX_2);
    //         \g{-n}          relative reference by number
    public static final String BACKSLASH_G_N_REGEX_3 = "^\\\\g\\{-\\d+}$";
    public static final Pattern PATTERN_BACKSLASH_G_N_REGEX_3 = Pattern.compile(BACKSLASH_G_N_REGEX_3);


    // 处理预查找
    public static final String ASSERT_REGEX = "\\(\\?<?[!=].*\\)|\\\\b|\\\\B";  // 添加\b\B
    public static final Pattern PATTERN_ASSERT_PATTERN = Pattern.compile(ASSERT_REGEX);

    public static enum SimplyLevel {
        /**
         * 字母集合不化简
         */
        LOW,
        /**
         * 字母集合化简为 x-x
         */
        MEDIUM,
        /**
         * 字母集合用 \w,\d 代理
         */
        HIGH
    }


    public static enum AssertType {
        POSITIVE_AHEAD,
        POSITIVE_BEHIND,
        NEGATIVE_AHEAD,
        NEGATIVE_BEHIND,
        START_ASSERT,
        END_ASSERT,
        WORD_BOUNDARY_ASSERT,
        NON_WORD_BOUNDARY_ASSERT,
        NO_ASSERT
    }
}
