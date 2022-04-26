package cn.ac.ios.TreeNode;

import cn.ac.ios.JavaRegex.JavaRegexBuilder;
import cn.ac.ios.JavaRegex.JavaRegexParser;
import cn.ac.ios.JavaScriptRegex.JavaScriptRegexBuilder;
import cn.ac.ios.JavaScriptRegex.JavaScriptRegexParser;
import cn.ac.ios.PCRERegex.PCREBuilder;
import cn.ac.ios.PCRERegex.PCREParser;
import cn.ac.ios.Bean.Pair;
import cn.ac.ios.Bean.RegexBean;
import cn.ac.ios.PythonRegex.PythonRegexBuilder;
import cn.ac.ios.PythonRegex.PythonRegexParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.ac.ios.Utils.BracketUtils.isBracketsNode;
import static cn.ac.ios.Utils.BracketUtils.simplifyLetters;
import static cn.ac.ios.Utils.Constant.*;
import static cn.ac.ios.Utils.FlagsUtils.divideRegexByFlags;
import static cn.ac.ios.Utils.UnicodeBackslashU.unicodeToCn;

public class Utils {
    // 利用深度优先赋值initialChainIndex
    public static void setInitialChainIndex(TreeNode root) {
        root.setInitialChainIndex(root.getChainIndex());
        List<TreeNode> children = root.getChildList();
        for (TreeNode child : children) {
            setInitialChainIndex(child);
        }
    }

    // 利用深度优先更新结点chainIndex
    public static void updateChainIndex(TreeNode root, String chainIndex) {
//		System.out.println(root.data + "\t" + level);
        root.setChainIndex(chainIndex);
        List<TreeNode> children = root.getChildList();
        for (int i = 0; i < children.size(); i++) {
            String subChainIndex = chainIndex + "." + i;
            updateChainIndex(children.get(i), subChainIndex);
        }
    }

    // 删group name （后面没有引用）
    public static String deleteGroupNameForNoQuote(String regex) {
        Matcher matcher = pattern_groupNameRegexForNoQuote.matcher(regex);

        while (matcher.find()) {
            for (int k = 1; k <= matcher.groupCount(); k++) {
                regex = regex.replace(matcher.group(k), "");
            }
        }
        return regex;
    }

//    // 删group name （后面有引用 e.g. ?P<named_group>cool)[a-z ]+(?P=named_group)kkk
//    public static String deleteGroupNameForQuote(String regex) {
//        Pattern pattern = Pattern.compile(groupNameRegexForQuote);
//        Matcher matcher = pattern.matcher(regex);
//
//        while (matcher.find()) {
////            System.out.println(matcher.group(0) + ", pos: " + matcher.start() + "-" + (matcher.end() - 1));
////            System.out.println(matcher.group(1) + ", pos: " + matcher.start(1) + "-" + (matcher.end(1) - 1));
////            System.out.println(matcher.group(2) + ", pos: " + matcher.start(2) + "-" + (matcher.end(2) - 1));
////            System.out.println(matcher.group(3) + ", pos: " + matcher.start(3) + "-" + (matcher.end(3) - 1));
//            regex = regex.replace(matcher.group(3), "(?:" + matcher.group(2) + ")");
//        }
//        return regex;
//    }


    // 删除group name 只能通过树
    // (?P<dup>\w+\s+)(abc)(?P=dup)
    // (?P<dup>(\w+\s+))(abc)(?P=dup)
    // (?<test>abc(?P<test2>def))
    // (?P<dup>\w+\s+)(abc)(?P=dup)
    // (?P<dup>(\w+\s+))(abc)(?P=dup)
    // (?<test>abc(?P<test2>def)ghi)(?P<test3>)
    public static String deleteGroupName(String regex) throws InterruptedException {
        if (!(regex.contains("(?P=") || regex.contains("\\k'") || regex.contains("\\k<"))) { // 如果不包含引用的地方
            return deleteGroupNameForNoQuote(regex);
        }

        // 否则包含引用 那就要先获取对应的group name 和patten
        List<Pair<String, String>> groupContentList = new LinkedList<>();
        TreeNode ReDoSTree = createReDoSTree(regex);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(ReDoSTree);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            Matcher matcher = pattern_groupNameRegexForReDoSTreeNode.matcher(node.getData());
            if (matcher.find()) {
                if (node.getChildCount() >= 3) {
                    if ((node.getChildList().get(node.getChildCount() - 3).getData().equals(">") || node.getChildList().get(node.getChildCount() - 3).getData().equals("'")) && node.getChildList().get(node.getChildCount() - 1).getData().equals(")")) {
                        if (matcher.group(1) != null) {
                            groupContentList.add(new Pair<>(matcher.group(1), node.getChildList().get(node.getChildCount() - 2).getData()));
                        } else if (matcher.group(2) != null) {
                            groupContentList.add(new Pair<>(matcher.group(2), node.getChildList().get(node.getChildCount() - 2).getData()));
                        }
                    }
                }
            }
            queue.addAll(node.getChildList());
        }

        for (int i = groupContentList.size() - 1; i >= 0; i--) {

            // 新加入两种引用方式
            // 例子 regex = "(?<foo>x)\\k'foo'\\k<foo>(?P=foo)\\1";
            regex = regex.replace("\\k<" + groupContentList.get(i).getKey() + ">", "(?:" + groupContentList.get(i).getValue() + ")");
            regex = regex.replace("\\k'" + groupContentList.get(i).getKey() + "'", "(?:" + groupContentList.get(i).getValue() + ")");
            regex = regex.replace("(?P=" + groupContentList.get(i).getKey() + ")", "(?:" + groupContentList.get(i).getValue() + ")");
        }

        return deleteGroupNameForNoQuote(regex);
    }

    // 删非贪婪匹配
    public static String deleteNonGreedyMatching(String regex) {
        Matcher matcher = pattern_nonGreedyMatching.matcher(regex);

        List<Integer> posList = new LinkedList<>();
        while (matcher.find()) {
            int pos = matcher.end(1) - 1;
            int bracketNumber = 0; // [和]数量 用于判断{,}是否在[]内 向前数 若遇到]则-1 若遇到[则+1 最终结果为0则不在[]内 否则在[]内
            for (int i = pos; i >= 0; i--) {
                if (regex.charAt(i) == '[') {
                    bracketNumber += 1;
                } else if (regex.charAt(i) == ']') {
                    bracketNumber -= 1;
                }
            }
            if (bracketNumber == 0) {
                posList.add(pos);
            }
        }
        for (int i = posList.size() - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder(regex);
            sb.replace(posList.get(i), posList.get(i) + 1, "");
            regex = sb.toString();
        }

        return regex;
    }


    // 重写patten
    public static String rewritePatten(String regex) {
        regex = regex.replace("(?:", "(");
        regex = regex.replace("(?=", "(");
        regex = regex.replace("(?<=", "(");
        regex = regex.replace("(?<!", "~(");
        regex = regex.replace("(?!", "~(");
        return regex;
    }

    // 处理POSIX 字符组
    // https://wenku.baidu.com/view/6194c12fcfc789eb172dc89f.html#
    // https://blog.csdn.net/shangboerds/article/details/7555332
    public static String rewritePOSIXCharacterClass(String regex) {
        Matcher matcher = pattern_posixCharacterClass.matcher(regex);

        List<Pair<String, Pair<Integer, Integer>>> posixList = new LinkedList<>();
        while (matcher.find()) {
//            System.out.println(matcher.group(0) + ", pos: " + matcher.start() + "-" + (matcher.end() - 1));
//            System.out.println(matcher.group(1) + ", pos: " + matcher.start(1) + "-" + (matcher.end(1) - 1));
            posixList.add(new Pair<>(matcher.group(1), new Pair<>(matcher.start(1), matcher.end(1))));
        }
        for (int i = posixList.size() - 1; i >= 0; i--) {
            StringBuilder sb = new StringBuilder(regex);
            Integer posixIndex1 = posixList.get(i).getValue().getKey();
            Integer posixIndex2 = posixList.get(i).getValue().getValue();
            switch (posixList.get(i).getKey()) {
                case "[:alnum:]":
                    sb.replace(posixIndex1, posixIndex2, "a-zA-Z0-9");
                    break;
                case "[:alpha:]":
                    sb.replace(posixIndex1, posixIndex2, "a-zA-Z");
                    break;
                case "[:ascii:]":
                    sb.replace(posixIndex1, posixIndex2, "\\x00-\\x7F");
                    break;
                case "[:blank:]":
                    sb.replace(posixIndex1, posixIndex2, " \\t");
                    break;
                case "[:cntrl:]":
                    sb.replace(posixIndex1, posixIndex2, "\\x00-\\x1F\\x7F");
                    break;
                case "[:digit:]":
                    sb.replace(posixIndex1, posixIndex2, "0-9");
                    break;
                case "[:graph:]":
                    sb.replace(posixIndex1, posixIndex2, "\\x21-\\x7E");
                    break;
                case "[:lower:]":
                    sb.replace(posixIndex1, posixIndex2, "a-z");
                    break;
                case "[:print:]":
                    sb.replace(posixIndex1, posixIndex2, "\\x20-\\x7E");
                    break;
                case "[:punct:]":
//                    sb.replace(posixIndex1, posixIndex2, "][!\"#$%&'()*+,./:;<=>?@\\^_`{|}~\\-");
                    sb.replace(posixIndex1, posixIndex2, "\\]\\[!\"#$%&'\\(\\)*+,./:;<=>?@\\^_`\\{|\\}~\\-");
                    break;
                case "[:space:]":
                    sb.replace(posixIndex1, posixIndex2, " \\t\\r\\n\\v\\f");
                    break;
                case "[:upper:]":
                    sb.replace(posixIndex1, posixIndex2, "A-Z");
                    break;
                case "[:word:]":
                    sb.replace(posixIndex1, posixIndex2, "A-Za-z0-9_");
                    break;
                case "[:xdigit:]":
                    sb.replace(posixIndex1, posixIndex2, "A-Fa-f0-9");
                    break;
                case "[:^alnum:]":
                    sb.replace(posixIndex1, posixIndex2, "^a-zA-Z0-9");
                    break;
                case "[:^alpha:]":
                    sb.replace(posixIndex1, posixIndex2, "^a-zA-Z");
                    break;
                case "[:^ascii:]":
                    sb.replace(posixIndex1, posixIndex2, "^\\x00-\\x7F");
                    break;
                case "[:^blank:]":
                    sb.replace(posixIndex1, posixIndex2, "^ \\t");
                    break;
                case "[:^cntrl:]":
                    sb.replace(posixIndex1, posixIndex2, "^\\x00-\\x1F\\x7F");
                    break;
                case "[:^digit:]":
                    sb.replace(posixIndex1, posixIndex2, "^0-9");
                    break;
                case "[:^graph:]":
                    sb.replace(posixIndex1, posixIndex2, "^\\x21-\\x7E");
                    break;
                case "[:^lower:]":
                    sb.replace(posixIndex1, posixIndex2, "^a-z");
                    break;
                case "[:^print:]":
                    sb.replace(posixIndex1, posixIndex2, "^\\x20-\\x7E");
                    break;
                case "[:^punct:]":
//                    sb.replace(posixIndex1, posixIndex2, "^][!\"#$%&'()*+,./:;<=>?@\\^_`{|}~\\-");
                    sb.replace(posixIndex1, posixIndex2, "^\\]\\[!\"#$%&'\\(\\)*+,./:;<=>?@\\^_`\\{|\\}~\\-");
                    break;
                case "[:^space:]":
                    sb.replace(posixIndex1, posixIndex2, "^ \\t\\r\\n\\v\\f");
                    break;
                case "[:^upper:]":
                    sb.replace(posixIndex1, posixIndex2, "^A-Z");
                    break;
                case "[:^word:]":
                    sb.replace(posixIndex1, posixIndex2, "^A-Za-z0-9_");
                    break;
                case "[:^xdigit:]":
                    sb.replace(posixIndex1, posixIndex2, "^A-Fa-f0-9");
                    break;
                default:
                    break;
            }
            regex = sb.toString();
//            System.out.println(regex);
        }

        return regex;
    }

    // 处理POSIX 中特殊的单词分界符号[[:<:]] -> \b(?=\w)    [[:>:]] -> \b(?<=\w)
    public static String rewritePOSIXWordBoundary(String regex) {
        regex = regex.replaceAll("\\[\\[:<:]]", "\\b(?=\\w)");
        regex = regex.replaceAll("\\[\\[:>:]]", "\\b(?<=\\w)");
        return regex;
    }

    // 去首尾的斜杠所用正则
    public static final String slashRegex = "\\/(.*)\\/";

    // 去首尾的斜杠
    public static String deleteSlashSymbols(String regex) {
        Pattern pattern = Pattern.compile(slashRegex);
        Matcher matcher = pattern.matcher(regex);

        while (matcher.find()) {
            for (int k = 1; k <= matcher.groupCount(); k++) {
                regex = regex.replace(matcher.group(k), "");
            }
        }
        return regex;
    }

//	// 调用prism的js去掉(?: (?= (?<=
//	// https://github.com/PrismJS/prism/issues/2583
//	private static void callPrism() throws Exception {
//		ScriptEngineManager mgr = new ScriptEngineManager();
//		ScriptEngine engine = mgr.getEngineByName("javascript");
//		engine.eval(readJSFile("src/main/java/util/prism.js"));
//		Invocable inv = (Invocable) engine;
//		Object res = (Object) inv.invokeFunction("toCapturing", new String[] { "(?=b)(a)\\1", "false" });
//		System.out.println("res:" + res);
//	}
//
//	// 读写javascript文件
//	private static String readJSFile(String filePath) throws Exception {
//		StringBuffer script = new StringBuffer();
//		File file = new File(filePath);
//		FileReader filereader = new FileReader(file);
//		BufferedReader bufferreader = new BufferedReader(filereader);
//		String tempString = null;
//		while ((tempString = bufferreader.readLine()) != null) {
//			script.append(tempString).append("\n");
//		}
//		bufferreader.close();
//		filereader.close();
//		return script.toString();
//	}

    // 调用prism的js去掉(?: (?= (?<=
    // https://github.com/PrismJS/prism/issues/2583
    @Deprecated
    public static String callPrism(String regex) {

        if (!(regex.contains("(?:") || regex.contains("(?=") || regex.contains("(?<="))) {
            return regex;
        }
        //创建ProcessBuilder对象
        ProcessBuilder processBuilder = new ProcessBuilder();
        //设置执行的第三方程序(命令)
//        processBuilder.command("ping","127.0.0.1");
        processBuilder.command("node", "src/main/java/cn/ac/ios/prism.js", regex);
//        processBuilder.command("java","-jar","f:/xc-service-manage-course.jar");
        //将标准输入流和错误输入流合并，通过标准输入流读取信息就可以拿到第三方程序输出的错误信息、正常信息
        processBuilder.redirectErrorStream(true);

        //启动一个进程
        Process process = null;
        try {
            process = processBuilder.start();
            //由于前边将错误和正常信息合并在输入流，只读取输入流
            InputStream inputStream = process.getInputStream();
            //将字节流转成字符流
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            //字符缓冲区
            char[] chars = new char[1024];
//		int len = -1;
//		while((len = reader.read(chars))!=-1){
//			String string = new String(chars,0,len);
//			System.out.println(string);
//		}

            StringBuilder sb = new StringBuilder();
            int len = -1;
            while ((len = reader.read(chars)) != -1) {
                sb.append(new String(chars, 0, len));
            }

            inputStream.close();
            reader.close();

            // 判断控制台返回的结果是否需正确
            if (sb.toString().contains("src/main/java/cn/ac/ios/node_modules/regexpp/index.js")) {
                // 包含 则返回错误 原样返回
                return regex;
            }

            // 调用后最后会多一个/n 要删掉
            return sb.substring(0, sb.length() - 1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return regex;
    }

    /***
     * //todo 在树的基础上进行操作
     * @param regex
     * @return
     */
    // 将 |...   ...||...   ...|  |...| 转换为 (?:...)?
    public static String rewriteEmptyString(String regex) throws InterruptedException {
        TreeNode ReDoSTree = createReDoSTree(regex);
        Queue<TreeNode> queue = new LinkedList<>();
        List<String> dataList = new LinkedList<>();
        queue.offer(ReDoSTree);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (isBracketsNode(node)) {
                continue;
            }
            String data = node.getData();
            if (data.length() > 1 && ((data.endsWith("|") && !data.endsWith("\\|")) || (data.contains("||") && !data.contains("\\||")) || (data.startsWith("|") && !data.startsWith("\\|")))) {
//                dataList.add(data);
                String newData = data;
                if (data.contains("||") && !data.contains("\\||")) {
                    newData = data.replace("||", "|");
                }
                if (data.startsWith("|") && !data.startsWith("\\|")) {
                    newData = newData.substring(1, newData.length());
                }
                if (data.endsWith("|") && !data.endsWith("\\|")) {
                    newData = newData.substring(0, newData.length() - 1);
                }
                node.setData("(?:(?:" + newData + ")?)");
                node.getChildList().clear();
            } else {
                queue.addAll(node.getChildList());
            }
        }
//        for (String data : dataList) {
//            String newData = data;
//            if (data.contains("||") && !data.contains("\\||")) {
//                newData = data.replace("||", "|");
//            }
//            if (data.startsWith("|") && !data.startsWith("\\|")) {
//                newData = newData.substring(1, newData.length());
//            }
//            if (data.endsWith("|") && !data.endsWith("\\|")) {
//                newData = newData.substring(0, newData.length() - 1);
//            }
////            regex = regex.replace(data, "(?:" + newData + ")?");
//            // (?:^|\s)(?:\/{2}|#)=(0|i|\u2026|)(?:\s+([`'\"\/~])|\s*$)
//            // 修正为
//            regex = regex.replace(data, "(?:(?:" + newData + ")?)");
//        }
        return ReDoSTree.getLeafsData();
    }


    // 预处理{,4}和{,}的问题
    public static String rewriteCounting(String regex) {
        if (!(regex.contains("{,") || regex.contains("{,}"))) {
            return regex;
        }

        // 处理{,4}这种counting
        Matcher matcher = pattern_specialCountingRegex.matcher(regex);

        while (matcher.find()) {
            String newCounting = matcher.group(0).replace("{,", "{0,");
            regex = regex.replace(matcher.group(0), newCounting);
        }

        // 处理{,}这种counting
        // [tT][+]{,}([0-9.]{1,})|None
        // ^\s{,}[a-zA-Z]{1,}\s{,}[=]{1}
        // [\-+]?\d{1,}\.?\d{,}([eEdD]\d+)?$
        // ^\w{,}\s{,}[=]{1}\s{,}\w{,}$
        // # *patches_ignore=([\w *.+?[\]{,}\-_]+)
        // [*{,}]
        // [{,},$]
        // \${|( (?=[{,}])|(?=[{,}]) )|{}|{,}|\\,(?=.*[{}])|\/\.(?=.*[{}])|\\\.(?={)|\\{|\\}
        // [{,},-]
        // [{,}]
        // [#{,}]|order:ignore
        // [{,}*]
        // ^(?:'[^']*'|\"[^\"]*\"|\/\*[\s\S]*?\*\/|[^{,}])+
        // [-[\]{,}(.)*+?|^$\\\/]
        // \$\{|[ \t]|{}|{,}|\\,|\/\.|\\\.|\\{|\\}
        // \$\{|[ \t]|{}|{,}|\\,(?=.*[{}])|\/\.(?=.*[{}])|\\\.(?={)|\\{|\\}
        int pos = -1;    // {,}所在下标
        while (regex.indexOf("{,}", pos + 1) != -1) {
            pos = regex.indexOf("{,}", pos + 1);
//            System.out.println(pos + " " + regex);

            int bracketNumber = 0; // [和]数量 用于判断{,}是否在[]内 向前数 若遇到]则-1 若遇到[则+1 最终结果为0则不在[]内 否则在[]内
            for (int i = pos; i >= 0; i--) {
                if (regex.charAt(i) == '[') {
                    bracketNumber += 1;
                } else if (regex.charAt(i) == ']') {
                    bracketNumber -= 1;
                }
            }
            if (bracketNumber == 0) {
                StringBuilder sb = new StringBuilder(regex);
                sb.replace(pos, pos + 3, "*");
                regex = sb.toString();
            }
        }


        return regex;
    }

    // 重写斜杠/
    public static String rewriteSlash(String regex) {
        if (!regex.contains("/")) {
            return regex;
        }
        String prefix = "";
        String suffix = "";
        int prefixPos = 0;
        int suffixPos = regex.length();
        if (regex.charAt(0) == '/') {
            suffixPos = regex.lastIndexOf("/");
            boolean isFlag = true;
            String flagSubstring = regex.substring(suffixPos + 1);
            for (int i = 0; i < flagSubstring.length(); i++) {
                if (!(flagSubstring.charAt(i) == 'i' ||
                        flagSubstring.charAt(i) == 'm' ||
                        flagSubstring.charAt(i) == 'x' ||
                        flagSubstring.charAt(i) == 's' ||
                        flagSubstring.charAt(i) == 'g' ||
                        flagSubstring.charAt(i) == 'u' ||
                        flagSubstring.charAt(i) == 'U' ||
                        flagSubstring.charAt(i) == 'n' ||
                        flagSubstring.charAt(i) == 'P' ||
                        flagSubstring.charAt(i) == 'R' ||
                        flagSubstring.charAt(i) == 'O' ||
                        flagSubstring.charAt(i) == 'I' ||
                        flagSubstring.charAt(i) == 'C' ||
                        flagSubstring.charAt(i) == 'H' ||
                        flagSubstring.charAt(i) == 'B' // 新增
                )) {
                    isFlag = false;
                    break;
                }
            }
            if (isFlag) {
                // 这种特殊处理 /Users\/.+?.pyz\/
                if (regex.startsWith("/") && (regex.endsWith("\\/"))) {
                    prefixPos = 0;
                    prefix = "";
                    suffixPos = regex.length();
                    suffix = "";
                } else {
                    prefixPos = 1;
                    prefix = "/";
                    suffixPos = regex.lastIndexOf("/");
                    suffix = regex.substring(suffixPos);
                }
            } else {
                prefixPos = 0;
                prefix = "";
                suffixPos = regex.length();
                suffix = "";
            }
        }
        String subregex = regex.substring(prefixPos, suffixPos);
//        System.out.println(subregex);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < subregex.length(); i++) {
            if (subregex.charAt(i) == '/') {
                if (i == 0) {
                    sb.append("\\/");
                } else {
                    //计算/ 前 \的个数
                    int count = 0;
                    for (int j = i - 1; j >= 0; j--) {
                        if (subregex.charAt(j) != '\\') {
                            break;
                        }
                        count++;
                    }
                    if (count % 2 == 0) {
                        sb.append("\\/");
                    } else {
                        sb.append("/");
                    }
                }
            } else {
                sb.append(subregex.charAt(i));
            }
        }
        return prefix + sb.toString() + suffix;
    }

    // 序列化 返回序列化后的regex及字典
    public static String serializeRegex(TreeNode treeNode, Map<String, String> sereializeMap) throws InterruptedException {
        Set<String> alphabet = treeNode.getLetterSet(false);
        alphabet = simplifyLetters(alphabet, SimplyLevel.LOW);
//        System.out.println("alphabet = " + alphabet);

        TreeNode ReDoSTreeWithComma = regexToReDoSTreeWithComma(treeNode.getData());

//        printTree(ReDoSTreeWithComma);

        int num = 0;
        Map<String, String> map = new HashMap<>();

        List<String> regexList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(ReDoSTreeWithComma);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isBracketsNode(node) && node.getData().length() == 5) {
                regexList.add(node.getData().replace("[", "").replace("]", ""));
                continue;
            }
            for (int i = node.getChildCount() - 1; i >= 0; i--) {
                stack.push(node.getChild(i));
            }
            if (node.isLeaf()) {
                if ("{".equals(node.getData()) || "}".equals(node.getData()) || "-".equals(node.getData()) || "[".equals(node.getData()) || "]".equals(node.getData()) || "/".equals(node.getData())) {
                    regexList.add("\\" + node.getData());
                } else {
                    regexList.add(node.getData());
                }
            }
        }


        for (int i = 0; i < regexList.size(); i++) {
            if (alphabet.contains(regexList.get(i))) {
                if (!map.containsKey(regexList.get(i))) {
                    map.put(regexList.get(i), "a" + num);
                    sereializeMap.put("a" + num, regexList.get(i));
                    num += 1;
                }
                regexList.set(i, map.get(regexList.get(i)));
            } else if (regexList.get(i).length() == 3 && regexList.get(i).charAt(1) == '-') {
//                System.out.println(regexList.get(i));
                if (!map.containsKey(regexList.get(i))) {
                    map.put(regexList.get(i), "a" + num);
                    sereializeMap.put("a" + num, regexList.get(i));
                    num += 1;
                }
                regexList.set(i, map.get(regexList.get(i)));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String subregex : regexList) {
            sb.append(subregex);
        }

//        return sb.toString();
        return sb.toString().replace("◎", ",");
    }

    // 删除注释(?#...)
    public static String deleteAnnotation(String regex) {
        Matcher matcher = pattern_annotationRegex.matcher(regex);

        while (matcher.find()) {
            for (int k = 1; k <= matcher.groupCount(); k++) {
                regex = regex.replace(matcher.group(k), "");
            }
        }
        return regex;
    }

    public static TreeNode createReDoSTree(String regex) throws InterruptedException {
        return createReDoSTree(regex, "java");
    }


    /**
     * 从regex 建立一个树，包含JavaScript RegExp 对象
     *
     * @param regex
     * @return
     */
    public static TreeNode createReDoSTree(String regex, String language) throws InterruptedException {
        language = language.toLowerCase();
        if (language.equals("java")) {
            RegexBean regexBean = divideRegexByFlags(regex);
            regex = regexBean.getRegex();
            String flags = regexBean.getFlags();
            TreeNode ReDoSTree = regexToReDoSTreeForJava(regex);
            Set<String> flagSet = new LinkedHashSet<>();
            for (int i = 0; i < regexBean.getFlags().length(); i++) {
                flagSet.add(flags.substring(i, i + 1));
            }
            ReDoSTree.setFlags(flagSet);
            return ReDoSTree;
        } else if (language.equals("python")) {
            RegexBean regexBean = divideRegexByFlags(regex);
            regex = regexBean.getRegex();
            String flags = regexBean.getFlags();
            TreeNode ReDoSTree = regexToReDoSTreeForPython(regex);
            Set<String> flagSet = new LinkedHashSet<>();
            for (int i = 0; i < regexBean.getFlags().length(); i++) {
                flagSet.add(flags.substring(i, i + 1));
            }
            ReDoSTree.setFlags(flagSet);
            return ReDoSTree;
        } else if (language.equals("javascript")) {
            RegexBean regexBean = divideRegexByFlags(regex);
            regex = regexBean.getRegex();
            String flags = regexBean.getFlags();
            TreeNode ReDoSTree = regexToReDoSTreeForJavaScript(regex);
            Set<String> flagSet = new LinkedHashSet<>();
            for (int i = 0; i < regexBean.getFlags().length(); i++) {
                flagSet.add(flags.substring(i, i + 1));
            }
            ReDoSTree.setFlags(flagSet);
            return ReDoSTree;
        } else {
            RegexBean regexBean = divideRegexByFlags(regex);
            regex = regexBean.getRegex();
            String flags = regexBean.getFlags();
            TreeNode ReDoSTree = regexToReDoSTree(regex);
            Set<String> flagSet = new LinkedHashSet<>();
            for (int i = 0; i < regexBean.getFlags().length(); i++) {
                flagSet.add(flags.substring(i, i + 1));
            }
            ReDoSTree.setFlags(flagSet);
            return ReDoSTree;
        }
    }


    public static TreeNode regexToReDoSTreeForJava(String regex) throws InterruptedException {
        JavaRegexParser parser = new JavaRegexBuilder.Parser(regex).build();
        ParseTree parseTree = parser.parse();
        TreeNode ReDoSTree = new TreeNode(regex);
        ReDoSTree = getReDoSTreeHelper1(parseTree, ReDoSTree);
        TreeNode newReDoSTree = new TreeNode(regex);
//		dealWithBrackets(ReDoSTree,newReDoSTree);
//		getSimplifyLYTTree(ReDoSTree, newReDoSTree);
        getReDoSTreeHelper2(ReDoSTree, newReDoSTree);
        // 不知道为什么 不加这一行更新 建树时可能会有错误的chainIndex [3]|2[a] 2的chainIndex应该是0.2.0 但如果不加 就成了0.0？？？
        updateChainIndex(newReDoSTree, "0");
        return newReDoSTree;
    }

    public static TreeNode regexToReDoSTreeForPython(String regex) throws InterruptedException {
        PythonRegexParser parser = new PythonRegexBuilder.Parser(regex).build();
        ParseTree parseTree = parser.parse();
        TreeNode ReDoSTree = new TreeNode(regex);
        ReDoSTree = getReDoSTreeHelper1(parseTree, ReDoSTree);
        TreeNode newReDoSTree = new TreeNode(regex);
//		dealWithBrackets(ReDoSTree,newReDoSTree);
//		getSimplifyLYTTree(ReDoSTree, newReDoSTree);
        getReDoSTreeHelper2(ReDoSTree, newReDoSTree);
        // 不知道为什么 不加这一行更新 建树时可能会有错误的chainIndex [3]|2[a] 2的chainIndex应该是0.2.0 但如果不加 就成了0.0？？？
        updateChainIndex(newReDoSTree, "0");
        return newReDoSTree;
    }

    public static TreeNode regexToReDoSTreeForJavaScript(String regex) throws InterruptedException {
        JavaScriptRegexParser parser = new JavaScriptRegexBuilder.Parser(regex).build();
        ParseTree parseTree = parser.parse();
        TreeNode ReDoSTree = new TreeNode(regex);
        ReDoSTree = getReDoSTreeHelper1(parseTree, ReDoSTree);
        TreeNode newReDoSTree = new TreeNode(regex);
//		dealWithBrackets(ReDoSTree,newReDoSTree);
//		getSimplifyLYTTree(ReDoSTree, newReDoSTree);
        getReDoSTreeHelper2(ReDoSTree, newReDoSTree);
        // 不知道为什么 不加这一行更新 建树时可能会有错误的chainIndex [3]|2[a] 2的chainIndex应该是0.2.0 但如果不加 就成了0.0？？？
        updateChainIndex(newReDoSTree, "0");
        return newReDoSTree;
    }

    /**
     * 不包含JavaScript RegExp 对象，建立树，不建议使用
     *
     * @param regex
     * @return
     */
    public static TreeNode regexToReDoSTree(String regex) throws InterruptedException {
        PCREParser parser = new PCREBuilder.Parser(regex).build();
        ParseTree parseTree = parser.parse();
        TreeNode ReDoSTree = new TreeNode(regex);
        ReDoSTree = getReDoSTreeHelper1(parseTree, ReDoSTree);
        TreeNode newReDoSTree = new TreeNode(regex);
//		dealWithBrackets(ReDoSTree,newReDoSTree);
//		getSimplifyLYTTree(ReDoSTree, newReDoSTree);
        getReDoSTreeHelper2(ReDoSTree, newReDoSTree);
        // 不知道为什么 不加这一行更新 建树时可能会有错误的chainIndex [3]|2[a] 2的chainIndex应该是0.2.0 但如果不加 就成了0.0？？？
        updateChainIndex(newReDoSTree, "0");
        return newReDoSTree;
    }

    // 建立带有连接符,的树
    public static TreeNode regexToReDoSTreeWithComma(String regex) throws InterruptedException {
        PCREParser parser = new PCREBuilder.Parser(regex).build();
        ParseTree tree = parser.parse();
        TreeNode ReDoSTree = new TreeNode(regex);
        ReDoSTree = getReDoSTreeHelper1(tree, ReDoSTree);
        TreeNode newReDoSTree = new TreeNode(regex);
//		dealWithBrackets(ReDoSTree,newReDoSTree);
//		getSimplifyLYTTree(ReDoSTree, newReDoSTree);
        getLYTTreeWithComma(ReDoSTree, newReDoSTree);
        return newReDoSTree;
    }


    // 树到正则表达式
    public static String ReDoSTreeToRegex(TreeNode ReDoSTree) {
        return ReDoSTree.getChildDataByChainIndexString("0");
    }

    // 打印树
    public static void printTree(TreeNode ReDoSTree) {
        StringBuilder builder1 = new StringBuilder();
        walk1(ReDoSTree, builder1);
        System.out.println(builder1);
    }

    // 最开头的预处理
    public static String rewriteRegex(String regex) {
        // 处理Branch Reset Group结构 不一定对 (?|...|...) -> （...|...)
        regex = regex.replace("(?|", "(?:");

        // 添加对错误字符/的处理 除/.../xxx结构外的所有/ 均应改成\/
        regex = rewriteSlash(regex);
        // 临时增加对&...;等的网络转义的处理
//        regex = regex
//                .replace("&quot;", "\"")
//                .replace("&amp;", "&")
//                .replace("&lt;", "<")
//                .replace("&gt;", ">")
//                .replace("&nbsp;", " ");

        // 临时增加对?& 和 ?>的处理 未验证 目测是错误的
        regex = regex
                .replace("(?&", "(?P=")
                .replace("(?>", "(?:");


        // 删除注释(?#...)
//        regex = deleteAnnotation(regex);

//        // 增加两个特殊字符的转换
//        regex = regex.replace("\\u005C", "\\\\");
//        regex = regex.replace("\\u002F", "\\/");


        // 预处理\\uxxxx的问题
        regex = unicodeToCn(regex);

        // 预处理{,4}的问题
//        regex = rewriteCounting(regex);


        // 师弟，你再最前边加上这三条规则: {0,1}->? {1,}->+ {0,}->*
//        regex = regex.replace("{0,1}", "?").replace("{1,}", "+").replace("{0,}", "*");

        // 再加上删除非贪婪匹配
        // (1) ++ -> +, (2) *+ -> *, (3) ?+ -> ?, (4) {m,n}+ -> {m,n}, (5) {m}+ -> {m}, (6) {m,}+ -> {m,}
        // (7) +? -> +, (8) *? -> *, (9) ?? -> ?, (10) {m,n}? -> {m,n}, (11) {m}? -> {m}, (12) {m,}? -> {m,}
        // 未验证是否可行
//        regex = deleteNonGreedyMatching(regex);

        // 处理POSIX 字符组
        // https://wenku.baidu.com/view/6194c12fcfc789eb172dc89f.html#
        // https://blog.csdn.net/shangboerds/article/details/7555332
        // 先注释掉
//        regex = rewritePOSIXCharacterClass(regex);

        // 强制重写[]内的转义字符两种括号为斜杠的形式 e.g. [()] -> [\(\)], [[] -> [\[]
        // warning: >([^<]+)[^[]+\[([^\]]+)]\[([^\]]+)] 会错
//        regex = rewriteSquareBrackets(regex);
        return regex;
    }


    // 强制重写[]内的转义字符两种括号为斜杠的形式 e.g. [()] -> [\(\)], [[] -> [\[]
    public static final String squareBracketsRegex = "\\[(.*?)]";
    public static final Pattern squareBracketsPattern = Pattern.compile(squareBracketsRegex);
    public static final String singleRightAndLeftSquareBracketsRegex = "(?<!\\\\)\\[(].*?)\\]";  // []abc[] -> [\]abc\[]
    public static final Pattern singleRightAndLeftSquareBracketPattern = Pattern.compile(singleRightAndLeftSquareBracketsRegex);
    public static final List<String> forcedEscapeCharacterList = Arrays.asList("(", ")", "[", "]");

    public static String rewriteSquareBrackets(String regex) {
        int pos = regex.length();
        while ((pos = regex.lastIndexOf("\\[", pos - 1)) != -1) {
            int pos2;
            for (pos2 = pos; pos2 >= 0; pos2--) {
                if (regex.charAt(pos2) != '\\') {
                    break;
                }
            }
            if ((pos - pos2) % 2 != 0) {    // 如果前面有偶数个反斜杠 则不是转义的[  否则如果有奇数个反斜杠 则是转义的[
                regex = regex.substring(0, pos + 1) + "◆" + regex.substring(pos + 2);   // 用◆来表示替代[
            }
        }
        pos = regex.length();
        while ((pos = regex.lastIndexOf("\\]", pos - 1)) != -1) {
            int pos2;
            for (pos2 = pos; pos2 >= 0; pos2--) {
                if (regex.charAt(pos2) != '\\') {
                    break;
                }
            }
            if ((pos - pos2) % 2 != 0) {    // 如果前面有偶数个反斜杠 则不是转义的]  否则如果有奇数个反斜杠 则是转义的]
                regex = regex.substring(0, pos + 1) + "■" + regex.substring(pos + 2);   // 用■来表示替代]
            }
        }

        pos = regex.length();
        while ((pos = regex.lastIndexOf("\\(", pos - 1)) != -1) {
            int pos2;
            for (pos2 = pos; pos2 >= 0; pos2--) {
                if (regex.charAt(pos2) != '\\') {
                    break;
                }
            }
            if ((pos - pos2) % 2 != 0) {    // 如果前面有偶数个反斜杠 则不是转义的(  否则如果有奇数个反斜杠 则是转义的(
                regex = regex.substring(0, pos + 1) + "★" + regex.substring(pos + 2);   // 用★来表示替代(
            }
        }

        pos = regex.length();
        while ((pos = regex.lastIndexOf("\\)", pos - 1)) != -1) {
            int pos2;
            for (pos2 = pos; pos2 >= 0; pos2--) {
                if (regex.charAt(pos2) != '\\') {
                    break;
                }
            }
            if ((pos - pos2) % 2 != 0) {    // 如果前面有偶数个反斜杠 则不是转义的)  否则如果有奇数个反斜杠 则是转义的)
                regex = regex.substring(0, pos + 1) + "▲" + regex.substring(pos + 2);   // 用▲来表示替代)
            }
        }


//        System.out.println(regex);
        while (true) {
            Matcher matcher = singleRightAndLeftSquareBracketPattern.matcher(regex);
            if (matcher.find()) {
                StringBuilder sb = new StringBuilder(regex);
//                System.out.println(matcher.group(0));

                String subRegexWithoutBrackets = matcher.group(1);
                if (subRegexWithoutBrackets.endsWith("[")) {    //  []abc[]中的]abc[
                    sb.replace(matcher.start(), matcher.start() + matcher.group(0).length(), "[\\" + subRegexWithoutBrackets.substring(0, subRegexWithoutBrackets.length() - 1) + "\\[]");
                } else {  // []abc]中的]abc
                    sb.replace(matcher.start(), matcher.start() + matcher.group(0).length(), "[\\" + subRegexWithoutBrackets + "]");
                }
                regex = sb.toString();
//                System.out.println(regex);
            } else {
                break;
            }
        }


        pos = regex.length();
        while ((pos = regex.lastIndexOf("\\[", pos - 1)) != -1) {
            int pos2;
            for (pos2 = pos; pos2 >= 0; pos2--) {
                if (regex.charAt(pos2) != '\\') {
                    break;
                }
            }
            if ((pos - pos2) % 2 != 0) {    // 如果前面有偶数个反斜杠 则不是转义的[  否则如果有奇数个反斜杠 则是转义的[
                regex = regex.substring(0, pos + 1) + "◆" + regex.substring(pos + 2);   // 用◆来表示替代[
            }
        }
        pos = regex.length();
        while ((pos = regex.lastIndexOf("\\]", pos - 1)) != -1) {
            int pos2;
            for (pos2 = pos; pos2 >= 0; pos2--) {
                if (regex.charAt(pos2) != '\\') {
                    break;
                }
            }
            if ((pos - pos2) % 2 != 0) {    // 如果前面有偶数个反斜杠 则不是转义的]  否则如果有奇数个反斜杠 则是转义的]
                regex = regex.substring(0, pos + 1) + "■" + regex.substring(pos + 2);   // 用■来表示替代]
            }
        }

        pos = regex.length();
        while ((pos = regex.lastIndexOf("\\(", pos - 1)) != -1) {
            int pos2;
            for (pos2 = pos; pos2 >= 0; pos2--) {
                if (regex.charAt(pos2) != '\\') {
                    break;
                }
            }
            if ((pos - pos2) % 2 != 0) {    // 如果前面有偶数个反斜杠 则不是转义的(  否则如果有奇数个反斜杠 则是转义的(
                regex = regex.substring(0, pos + 1) + "★" + regex.substring(pos + 2);   // 用★来表示替代(
            }
        }

        pos = regex.length();
        while ((pos = regex.lastIndexOf("\\)", pos - 1)) != -1) {
            int pos2;
            for (pos2 = pos; pos2 >= 0; pos2--) {
                if (regex.charAt(pos2) != '\\') {
                    break;
                }
            }
            if ((pos - pos2) % 2 != 0) {    // 如果前面有偶数个反斜杠 则不是转义的)  否则如果有奇数个反斜杠 则是转义的)
                regex = regex.substring(0, pos + 1) + "▲" + regex.substring(pos + 2);   // 用▲来表示替代)
            }
        }


        while (true) {
            Matcher matcher = squareBracketsPattern.matcher(regex);
            if (matcher.find()) {
                StringBuilder sb = new StringBuilder(regex);
//                System.out.println(matcher.group(0));

                String subRegexWithoutBrackets = matcher.group(1);

                subRegexWithoutBrackets = subRegexWithoutBrackets.replace("[", "\\◆");
                subRegexWithoutBrackets = subRegexWithoutBrackets.replace("]", "\\■");
                subRegexWithoutBrackets = subRegexWithoutBrackets.replace("(", "\\★");
                subRegexWithoutBrackets = subRegexWithoutBrackets.replace(")", "\\▲");

                sb.replace(matcher.start(), matcher.start() + matcher.group(0).length(), "◆" + subRegexWithoutBrackets + "■");

                regex = sb.toString();
//                System.out.println(regex);
            } else {
                break;
            }
        }
        regex = regex.replace("◆", "[").replace("■", "]").replace("★", "(").replace("▲", ")");
//        System.out.println(regex);
//        return null;
        return regex;
    }

    public static TreeNode getSimplifyLYTTree(TreeNode root, TreeNode lyttree) {
        if (root == null) {
            return null;
        } else {
            while (root.getChildCount() == 1) {
                root = root.getChild(0);
            }
            for (int i = 0; i < root.getChildCount(); i++) {
                TreeNode itemTreeNode = root.getChild(i);
                String data = itemTreeNode.getText();
                lyttree.addChild(data);
                getSimplifyLYTTree(itemTreeNode, lyttree.getChild(lyttree.getChildCount() - 1));
            }
            return lyttree;
        }
    }

    public static String Reorganization(TreeNode root) {
        if (root == null) {
            return null;
        } else {
            if (root.getChildCount() == 0) {
                return root.getText();
            } else {
                String res = "";
                for (int i = 0; i < root.getChildCount(); i++) {
                    TreeNode itemTreeNode = root.getChild(i);
                    res += Reorganization(itemTreeNode);
                }
                return res;
            }
        }
    }

    // 处理[]
    public static TreeNode dealWithBrackets(TreeNode root, TreeNode lyttree) {
        if (root == null) {
            return null;
        } else {
            while (root.getChildCount() == 1) {
                root = root.getChild(0);
            }
            for (int i = 0; i < root.getChildCount(); i++) {
                TreeNode itemTreeNode = root.getChild(i);
                String data = itemTreeNode.getText();
//				System.out.println(data);

                if (data.startsWith("[") && data.endsWith("]")) {
                    System.out.println(data);
                    boolean isHasRange = false;
                    for (int j = 0; j < itemTreeNode.getChildCount(); j++) {
                        TreeNode subTreeNode = root.getChild(j);
                        String subData = subTreeNode.getText();
                        if (subData.indexOf("-") != -1 && subData.length() > 2) {
                            isHasRange = true;
                        }
                    }
                    if (isHasRange == false) {
                        lyttree.addChild(data);//.substring(0,data.length()-1)
                    } else {

                    }
                } else {
                    lyttree.addChild(data);
                }
                dealWithBrackets(itemTreeNode, lyttree.getChild(lyttree.getChildCount() - 1));


            }
            return lyttree;
        }
    }


    // 真 加连接符,
    public static TreeNode getLYTTreeWithComma(TreeNode root, TreeNode lyttree) {
        if (root == null) {
            return null;
        } else {
            while (root.getChildCount() == 1) {
                root = root.getChild(0);
            }
            for (int i = 0; i < root.getChildCount(); i++) {
                TreeNode itemTreeNode = root.getChild(i);
                String data = itemTreeNode.getText();
//                lyttree.addChild(data);
                lyttree.addChildForCreateReDoSTree(data);
                getLYTTreeWithComma(itemTreeNode, lyttree.getChild(lyttree.getChildCount() - 1));
                if ((i < root.getChildCount() - 1)) {
                    String nextData = root.getChild(i + 1).getText();
                    String cntPattern = "\\{.*?,?.*?\\}";
                    boolean isNextMatch = Pattern.matches(cntPattern, nextData);
                    if ((!data.equals("(")) && (!nextData.equals(")")) && (!nextData.equals("*")) &&
                            (!nextData.equals("+")) && (!nextData.equals("?")) && (!isNextMatch) &&
                            (!data.equals("|")) && (!nextData.equals("|")) && (!nextData.equals("*?")) &&
                            (!nextData.equals("+?")) && (!nextData.equals("??"))
                    ) {
//                        lyttree.addChild(",");//,
//                        lyttree.addChild("◎");// 用这个符号代替,
                        lyttree.addChildForCreateReDoSTree("◎");
                    }
                }

            }
            return lyttree;
        }
    }


    public static <T> void dfs(TreeNode tree, List<Integer> ids, int depth) {

        if (tree != null) {

            //打印节点值以及深度
            System.out.println(ids);
            if (ids.isEmpty()) {

                System.out.println(tree.getText().toString() + ",   " + depth + ",   " + 0);
                ids.add(0);
            } else {

                ids.add(ids.get(ids.size() - 1) + 1);
                System.out.println(tree.getText().toString() + ",   " + depth + ",   " + ids.get(ids.size() - 1));

            }
            if (tree.getChildList() != null && !tree.getChildList().isEmpty()) {
                for (TreeNode item : tree.getChildList()) {
                    dfs(item, ids, depth + 1);
                }
            }
        }
    }

    // Post-Order Traversal
    private static <T> void dfsPOT(TreeNode tree, int depth) {
        if (tree != null) {
            if (tree.getChildList() != null && !tree.getChildList().isEmpty()) {
                for (TreeNode item : tree.getChildList()) {
                    dfsPOT(item, depth + 1);
                }
            }
            //打印节点值以及深度
            System.out.println(tree.getText() + ",   " + depth);
        }
    }


    // Map<TreeNode<T>, Integer> dict1
    public static void getIDs(TreeNode tree, List<Integer> ids, List<String> nodes) {
        if (tree != null) {
            //跟上面一样，使用 Map 也只是为了保存树的深度，没这个需要可以不用 Map
            Queue<Map<TreeNode, Integer>> queue = new ArrayDeque<>();
            Map<TreeNode, Integer> root = new HashMap<>();
            root.put(tree, 0);
            queue.offer(root);
            while (!queue.isEmpty()) {
                Map<TreeNode, Integer> itemMap = queue.poll();
                TreeNode itemTreeNode = itemMap.keySet().iterator().next();
                int depth = itemMap.get(itemTreeNode);
                //打印节点值以及深度
//				System.out.println(itemTreeNode.getText() + ",   " + depth);
                if (ids.isEmpty()) {
                    ids.add(0);
                } else {
                    ids.add(ids.get(ids.size() - 1) + 1);
                }
                nodes.add(itemTreeNode.getText());
//				System.out.println(itemTreeNode.getText() + ",   " +ids.get(ids.size()-1));
                if (itemTreeNode.getChildList() != null &&
                        !itemTreeNode.getChildList().isEmpty()) {
                    for (TreeNode child : itemTreeNode.getChildList()) {
                        Map<TreeNode, Integer> map = new HashMap<>();
                        map.put(child, depth + 1);
                        queue.offer(map);
                    }
                }
            }
        }
    }


    public static void bfsNotRecursive(TreeNode tree, Map<Integer, String> dict1) {
        if (tree != null) {
            //跟上面一样，使用 Map 也只是为了保存树的深度，没这个需要可以不用 Map
            Queue<Map<TreeNode, Integer>> queue = new ArrayDeque<>();
            Map<TreeNode, Integer> root = new HashMap<>();
            root.put(tree, 0);
            queue.offer(root);
            while (!queue.isEmpty()) {
                Map<TreeNode, Integer> itemMap = queue.poll();
                TreeNode itemTreeNode = itemMap.keySet().iterator().next();
                int depth = itemMap.get(itemTreeNode);
                //打印节点值以及深度
                System.out.println(itemTreeNode.getText() + ",   " + depth);
                if (itemTreeNode.getChildList() != null &&
                        !itemTreeNode.getChildList().isEmpty()) {
                    System.out.println(itemTreeNode.getChildList());
                    List<Integer> tmpNodes = new ArrayList<>();
                    for (TreeNode child : itemTreeNode.getChildList()) {

                        Map<TreeNode, Integer> map = new HashMap<>();
                        map.put(child, depth + 1);
                        queue.offer(map);
                    }
                }
            }
        }
    }


    public static void walk1(TreeNode tree, StringBuilder builder) {

        List<TreeNode> firstStack = new ArrayList<TreeNode>();
        firstStack.add(tree);

        List<List<TreeNode>> childListStack = new ArrayList<List<TreeNode>>();
        childListStack.add(firstStack);

        while (!childListStack.isEmpty()) {

            List<TreeNode> childStack = childListStack.get(childListStack.size() - 1);

            if (childStack.isEmpty()) {
                childListStack.remove(childListStack.size() - 1);
            } else {
                tree = childStack.remove(0);

                String node = tree.getClass().getSimpleName();
                node = Character.toLowerCase(node.charAt(0)) + node.substring(1);

                String indent = "";

                for (int i = 0; i < childListStack.size() - 1; i++) {
                    indent += (childListStack.get(i).size() > 0) ? "|  " : "   ";
                }

                builder.append(indent)
                        .append(childStack.isEmpty() ? "'- " : "|- ")
                        .append(tree.getText())
                        .append("\n");

                if (tree.getChildCount() > 0) {
                    List<TreeNode> children = new ArrayList<TreeNode>();
                    for (int i = 0; i < tree.getChildCount(); i++) {
                        children.add(tree.getChild(i));
                    }
                    childListStack.add(children);
                }
            }
        }
    }


    public static TerminalNode getStartNode(ParseTree context) {
        if (context == null) {
            return null;
        }
        if (context instanceof TerminalNode) {
            return (TerminalNode) context;
        }
        for (int i = 0; i < context.getChildCount(); i++) {
            TerminalNode startNode = getStartNode(context.getChild(i));
            if (startNode != null) {
                return startNode;
            }
        }
        return null;
    }

    public static TerminalNode getStopNode(ParseTree context) {
        if (context == null) {
            return null;
        }
        if (context instanceof TerminalNode) {
            return (TerminalNode) context;
        }
        for (int i = context.getChildCount() - 1; i >= 0; i--) {
            TerminalNode stopNode = getStopNode(context.getChild(i));
            if (stopNode != null) {
                return stopNode;
            }
        }
        return null;
    }


    public static int maxDepth(ParseTree root) {
        if (root == null) {
            return 0;
        } else {
            int maxDep = 0;
            for (int i = root.getChildCount() - 1; i >= 0; i--) {
                ParseTree node = root.getChild(i);
                int currDep = maxDepth(node);
                maxDep = currDep > maxDep ? currDep : maxDep;
            }
            return maxDep + 1;
        }
    }

    // 加连接符
    public static TreeNode getReDoSTreeHelper2(TreeNode root, TreeNode ReDoSTree) throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
        if (root == null) {
            return null;
        } else {
            while (root.getChildCount() == 1) {
                root = root.getChild(0);
            }
            for (int i = 0; i < root.getChildCount(); i++) {
                TreeNode itemTreeNode = root.getChild(i);
                String data = itemTreeNode.getText();
//				System.out.println(data);

                // 以下7行用于 ?? -> ?, +? -> +, *? -> *, {_,_}? -> {_,_}
//                if (data.equals("??") || data.equals("+?") || data.equals("*?") || Pattern.matches("\\{.*?,?.*?\\}\\?", data)) {
////                    ReDoSTree.addChild(data.substring(0, data.length() - 1));
//                    ReDoSTree.addChildForCreateReDoSTree(data.substring(0, data.length() - 1));
//                } else {
////                    ReDoSTree.addChild(data);
//                    ReDoSTree.addChildForCreateReDoSTree(data);
//                }
                ReDoSTree.addChildForCreateReDoSTree(data);

                getReDoSTreeHelper2(itemTreeNode, ReDoSTree.getChild(ReDoSTree.getChildCount() - 1));

                // 和上面的一样 同时注释掉的
//                if ((i < root.getChildCount() - 1)) {
//                    String nextData = root.getChild(i + 1).getText();
//                    String cntPattern = "\\{.*?,?.*?\\}";
//                    boolean isNextMatch = Pattern.matches(cntPattern, nextData);
//                    if ((!data.equals("(")) && (!nextData.equals(")")) && (!nextData.equals("*")) &&
//                            (!nextData.equals("+")) && (!nextData.equals("?")) && (!isNextMatch) &&
//                            (!data.equals("|")) && (!nextData.equals("|")) && (!nextData.equals("*?")) &&
//                            (!nextData.equals("+?")) && (!nextData.equals("??"))
//                    ) {
////                        ReDoSTree.addChild("comma");//,
//                    }
//                }

            }
            return ReDoSTree;
        }
    }



    public static TreeNode getReDoSTreeHelper1(ParseTree parseTree, TreeNode ReDoSTree) throws InterruptedException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException();
        }
        if (parseTree == null) {
            return null;
        } else {
            for (int i = 0; i < parseTree.getChildCount(); i++) {
                ParseTree itemTreeNode = parseTree.getChild(i);
                String text = itemTreeNode.getClass().getSimpleName().replace("Context", "");
                text = Character.toLowerCase(text.charAt(0)) + text.substring(1);
//                String data = text.startsWith("terminal") ? itemTreeNode.getText()  : text;
                String data = itemTreeNode.getText();
                if (!data.equals("<EOF>")) {// data.equals("<EOF>")==false
                    if (!text.equals("quantifier")) { // quantifier element
//                        lyttree.addChild(data);
                        ReDoSTree.addChildForCreateReDoSTree(data);
                        getReDoSTreeHelper1(itemTreeNode, ReDoSTree.getChild(ReDoSTree.getChildList().size() - 1));
                    } else {
//                        System.out.println(data);
//                        lyttree.addChild(data);
                        ReDoSTree.addChildForCreateReDoSTree(data);
                    }
                }// end  if (data != "<EOF>") {
            }
            return ReDoSTree;
        }
    }

    private static void ParseTree2LYTTree(ParseTree tree, List<Integer> ids, List<String> nodes, Map<Integer, List<Integer>> dict1) {
        if (tree != null) {
            //跟上面一样，使用 Map 也只是为了保存树的深度，没这个需要可以不用 Map
            Queue<Map<ParseTree, Integer>> queue = new ArrayDeque<>();
            Map<ParseTree, Integer> root = new HashMap<>();
            root.put(tree, 0);
            queue.offer(root); // add
            while (!queue.isEmpty()) {
                // 从queue取出（删除）第一个元素
                Map<ParseTree, Integer> itemMap = queue.poll();
                ParseTree itemTreeNode = itemMap.keySet().iterator().next();
//                System.out.println(itemTreeNode);
                int depth = itemMap.get(itemTreeNode);
                //打印节点值以及深度
                String text = itemTreeNode.getClass().getSimpleName().replace("Context", "");
                text = Character.toLowerCase(text.charAt(0)) + text.substring(1);

                if (ids.isEmpty()) {
                    ids.add(0);
                } else {
                    ids.add(ids.get(ids.size() - 1) + 1);
                }
                nodes.add(text.startsWith("terminal") ? itemTreeNode.getText() : text);
//                System.out.println(text.startsWith("terminal") ? itemTreeNode.getText() + "\t" + depth+"\t"+ids.get(ids.size()-1) : text + "\t" + depth+"\t"+ids.get(ids.size()-1));

                if (itemTreeNode.getParent() != null) {
                    String text1 = itemTreeNode.getParent().getClass().getSimpleName().replace("Context", "");
                    text1 = Character.toLowerCase(text1.charAt(0)) + text1.substring(1);

                    System.out.println(text1.startsWith("terminal") ? itemTreeNode.getParent().getText() : text1);
                    System.out.println(text.startsWith("terminal") ? itemTreeNode.getText() : text);
                    System.out.println("*******************************");
                }

//                lyttree.addChild(itemTreeNode.getText());
//                if (dict1.containsKey(ids.get(ids.size()-1))==true){}
//                else {}


                if (itemTreeNode.getChildCount() > 0) {
                    for (int j = 0; j < itemTreeNode.getChildCount(); j++) {
                        ParseTree child = itemTreeNode.getChild(j);
                        Map<ParseTree, Integer> map = new HashMap<>();
                        map.put(child, depth + 1);
                        queue.offer(map);
                    }
                }
            }
        }


    }


    private static void simplifyParseTree(ParseTree tree) {
        if (tree != null) {
            Queue<Map<ParseTree, Integer>> queue = new ArrayDeque<>();
            Map<ParseTree, Integer> root = new HashMap<>();

            root.put(tree, 0);
            queue.offer(root);
            while (!queue.isEmpty()) {
                Map<ParseTree, Integer> itemMap = queue.poll();
                ParseTree itemTreeNode = itemMap.keySet().iterator().next();
                int depth = itemMap.get(itemTreeNode);
                //打印节点值以及深度
//				System.out.println(itemTreeNode.getText() + ",   " + depth);

//				System.out.println(itemTreeNode.getText() + ",   " +ids.get(ids.size()-1));
                if (itemTreeNode.getChildCount() > 0) {
                    for (int j = 0; j < itemTreeNode.getChildCount(); j++) {
//                    for (ParseTree child : itemTreeNode.getChildList()) {
                        ParseTree child = itemTreeNode.getChild(j);
                        Map<ParseTree, Integer> map = new HashMap<>();
                        map.put(child, depth + 1);
                        queue.offer(map);
                    }
                }
            }
        }


    }
}
