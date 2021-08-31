package cn.ac.ios.Utils;


import cn.ac.ios.TreeNode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.ac.ios.TreeNode.Utils.*;
import static cn.ac.ios.Utils.Utils.readFile;
import static cn.ac.ios.Utils.Utils.writeFile;

public class CalculateIndicatorsUtils {
    // 数starHeight * + {m,} 的最大嵌套深度
    public static void calculateMaxStarHeight1(TreeNode newlyttree, int starHeight, List<Integer> maxStarHeight) {
        List<TreeNode> chirdren = newlyttree.getChildList();
//        System.out.println(starHeight);
        if (starHeight > maxStarHeight.get(0)) {
            maxStarHeight.set(0, starHeight);
        }
        if (chirdren.size() > 1) {
            for (int i = 1; i < chirdren.size(); i++) {
                TreeNode pre = chirdren.get(i - 1);
                TreeNode now = chirdren.get(i);

                if ("*".equals(now.getData()) || "+".equals(now.getData()) || (now.getData().startsWith("{") && now.getData().endsWith(",}"))) {
//                    System.out.println(pre.data);
                    calculateMaxStarHeight1(pre, starHeight + 1, maxStarHeight);
                } else {
                    calculateMaxStarHeight1(pre, starHeight, maxStarHeight);
                }
            }
            calculateMaxStarHeight1(chirdren.get(chirdren.size() - 1), starHeight, maxStarHeight);
        }
    }

    // 数starHeight * + {m,} {m,n} 的最大嵌套深度
    public static void calculateMaxStarHeight2(TreeNode newlyttree, boolean starHeightFlag, int starHeight, List<Integer> maxStarHeight) {
        if (!starHeightFlag) {
            if (newlyttree.getData().contains("*") || newlyttree.getData().contains("+") || (newlyttree.getData().startsWith("{") && newlyttree.getData().endsWith(",}"))) {
                starHeightFlag = true;
            }
        }

        List<TreeNode> chirdren = newlyttree.getChildList();
//        System.out.println(starHeight);
        if (starHeight > maxStarHeight.get(0)) {
            maxStarHeight.set(0, starHeight);
        }
        if (chirdren.size() > 1) {
            for (int i = 1; i < chirdren.size(); i++) {
                TreeNode pre = chirdren.get(i - 1);
                TreeNode now = chirdren.get(i);

                if ("*".equals(now.getData()) || "+".equals(now.getData()) || (now.getData().startsWith("{") && now.getData().endsWith("}") && now.getData().contains(",")) && starHeightFlag) {
//                    System.out.println(pre.data);
                    calculateMaxStarHeight2(pre, starHeightFlag, starHeight + 1, maxStarHeight);
                } else {
                    calculateMaxStarHeight2(pre, starHeightFlag, starHeight, maxStarHeight);
                }
            }
            calculateMaxStarHeight2(chirdren.get(chirdren.size() - 1), starHeightFlag, starHeight, maxStarHeight);
        }
    }


    // 数* + {m,} 的个数
    // 通过层序遍历
    public static int calculateCount(TreeNode newlyttree) {
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(newlyttree);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.getData().equals("*") || node.getData().equals("+") || (node.getData().startsWith("{") && node.getData().endsWith(",}"))) {
                count += 1;
            }
            for (TreeNode child : node.getChildList()) {
                queue.offer(child);
            }
        }
        return count;
    }

    // 数$的个数
    public static int calculateDollar(String regex) {
        String subString_1 = "$", subString_2 = "\\$";
        String regex_copy_1 = regex, regex_copy_2 = regex;
        int count_1 = 0, count_2 = 0;
        while (regex_copy_1.contains(subString_1)) {
            regex_copy_1 = regex_copy_1.substring(regex_copy_1.indexOf(subString_1) + 1);
            count_1 ++;
        }
        while (regex_copy_2.contains(subString_2)) {
            regex_copy_2 = regex_copy_2.substring(regex_copy_2.indexOf(subString_2) + 1);
            count_2 ++ ;
        }
        return count_1 - count_2;
    }

    // 计算三个指标
    public static String calculateIndicators(String regex) throws InterruptedException {
        String initalRegex = regex;

        // 最开头的预处理
        regex = rewriteRegex(regex);

        // 预处理recurses the subpattern
        regex = regex.replace("(?&", "(?P=");
        // 预处理掉两种情况[\*\+], {m,n} 其中m==n
        regex = regex.replace("[^\\\\]", "@")
                .replace("[^\\\"\\\\]", "@")
                .replace("[\\\\]", "@")
                .replace("[^/\\\\]","@")
                .replace("[^'\\\\]", "@")
                .replace("[^\\.\\*\\?\\s\\r\\n\\\\]", "@")
                .replace("[/\\\\]", "@")
                .replace("[/?\\\\]", "@")
                .replace("[^\\\\\"\\\\]","@")
                .replace("[^\\'\\\\]","@")
                .replace("+!(){}\\[\\]^\\\"~*?:\\\\]","@")
                .replace("[^ \\n:#@\\[\\]\\\"<>]", "@")
                .replace("[:#@\\[\\]<>]", "@")
                .replace("[^*\\\\]","@")
                .replace("[^>\\\\]", "@")
                .replace("[^\\s\\\"\\\\]", "@")
                .replace("[^=\\s]", "@")
                .replace("[^\\n'\\\\]", "@")
                .replace("[^\\n\\\"\\\\]", "@")
                .replace("[^'\\\\]", "@")
                .replace("[^'\\\\\"\\\\]", "@")
                .replace("['\\\\\"]", "@")
                .replace("[^'\\\\\"\\\\]", "@")
                .replace("[^\\s'\\\"]", "@")
                .replace("[^ _\\\\]", "@")
                .replace("[^+\\\\]", "@")
                .replace("[^$\\\\]", "@")
                .replace("[^)}\\\\]", "@")
                .replace("[\\._\\-\\/\\\\]", "@")
                .replace("[^*]", "@")
                .replace("(+)", "@")
                .replace("(?)", "@")
                .replace("\\P", "@")
                .replace("\\o", "@")
                .replace("\\y", "@")
                .replace("\\T", "@")
                .replace("\\E", "@")
                .replace("\\p", "@")
                .replace("\\m", "@")
                .replace("\\Q", "@");
        regex = regex.replace("\\[","@");
//        .replace("\\]", "@");
        regex = regex.replaceAll("\\[.*?]", "@").replaceAll("\\{(\\d+),\\1}|\\{\\d+}", "");
        regex = regex.replace("(?x:", "(")
                .replace("(?<!", "(")
                .replace("(?x)", "")
                .replace("(?!", "(")
                .replace("(?s:", "(")
                .replace("(?xm)", "")
                .replace("(?i-mx:", "(")
                .replace("(?m:", "(")
                .replace("(?xm:", "(")
                .replace("(?(?", "((?")
                .replace("(?-i:", "(")
                .replace("(?u)","");
        Pattern pattern = Pattern.compile("\\[.*?\\]");
        Matcher matcher = pattern.matcher(regex);

        while (matcher.find()) {
            for (int k = 1; k <= matcher.groupCount(); k++) {
                regex = regex.replace(matcher.group(k), "@");
            }
        }


//        System.out.println(regex);

//         去注释group name （后面没有引用）
//        regex = deleteGroupNameForQuote(regex); // 注意这里是不支持嵌套
//        regex = deleteGroupNameForNoQuote(regex);
//		regex = deleteSlashSymbols(regex);
//        System.out.println(regex);
        // 去group name
        regex = deleteGroupName(regex);


        // 调用prism.js去掉(?: (?= (?<=
        regex = callPrism(regex);
//		System.out.println(regex);

        // 计算$的个数
        int dollarNumber = calculateDollar(regex);

        System.out.println(regex);

        // 建树
//        TreeNode newlyttree = regex2newlyttree(regex);
        TreeNode newlyttree = createReDoSTree(regex);


        // 重写反向引用
        newlyttree.rewriteBackReferences();

        // 去首尾^$
//        newlyttree.deleteCaretAndDollarSymbols();


        // 计算Count
//        System.out.println(calculateCount(newlyttree));
        int count = calculateCount(newlyttree);

        // 计算最大嵌套深度
        List<Integer> maxStarHeight2 = new LinkedList<>();
        maxStarHeight2.add(0);
        boolean starHeightFlag = false;
        if (newlyttree.getData().contains("*") || newlyttree.getData().contains("+") || (newlyttree.getData().contains("{") && newlyttree.getData().contains(",}"))) {
            starHeightFlag = true;
        }
        calculateMaxStarHeight2(newlyttree, starHeightFlag, 0, maxStarHeight2);
//        System.out.println(maxStarHeight2.get(0));

        // 计算最大嵌套深度
        List<Integer> maxStarHeight1 = new LinkedList<>();
        maxStarHeight1.add(0);
        calculateMaxStarHeight1(newlyttree, 0, maxStarHeight1);
//        System.out.println(maxStarHeight1.get(0));

//        System.out.println(regex + "\t" + maxStarHeight1.get(0) + "\t" + maxStarHeight2.get(0) + "\t" + count);
        return initalRegex + "\t" + maxStarHeight1.get(0) + "\t" + maxStarHeight2.get(0) + "\t" + count + "\t" + dollarNumber;
    }

    public static void callCalculateIndicators(String sourceFilePath, String saveFilePath) {
        List<String> regexLineContent = readFile(sourceFilePath);
        List<String> threeIndicatorsLineContent = new ArrayList<>();
        assert regexLineContent != null;
//        for (String regex : regexLineContent) {
//            try {
//                threeIndicatorsLineContent.add(calculateThreeIndicators(regex));
//            } catch (Exception e) {
//                System.out.println(regex);
//                threeIndicatorsLineContent.add(regex);
//            }
//        }
        for (int i = 0; i < regexLineContent.size(); i++) {
            System.out.println(i + " / " + regexLineContent.size());
            try {
                String result = calculateIndicators(regexLineContent.get(i));
                System.out.println(result);
                threeIndicatorsLineContent.add(result);
            } catch (Exception e) {
                try {
                    String regex = regexLineContent.get(i).replace("&quot;", "\"")
                            .replace("&amp;", "&")
                            .replace("&lt;","\\<")
                            .replace("&gt;","\\>")
                            .replace("&nbsp;"," ");
                    String result = calculateIndicators(regex);
                    threeIndicatorsLineContent.add(result);
                } catch (Exception e1) {
                    System.out.println(regexLineContent.get(i));
                    threeIndicatorsLineContent.add(regexLineContent.get(i));
                }
            }
        }
        writeFile(saveFilePath, threeIndicatorsLineContent);
    }

    public static void main(String[] args) {
//        List<String> contentList = readFile("src/main/java/cn/ac/ios/regex101_wrong.txt");
//        for (String regex: contentList) {
//            System.out.println(regex);
//            System.out.println(calculateIndicators(regex));
//        }
//        String regex = "(\\w+)\\s*=\\s*(@|@)\\s*@.*(\\w+)\\s*=\\s*\\s*\\s*\\s*\\1\\s*(\\s*\\s*(@)\\s*|.*\\3\\s*\\s*(@)\\s*)|(\\w+)\\s*=\\s*@\\s*@\\s*\\s*(x22x22|x27x27)\\s*(\\s*\\s*()\\s*|.*\\7\\s*\\s*(LoadLibrary)\\s*)";
//        System.out.println(calculateIndicators(regex));


        String filePath = "data/small";
        String fileName = "regexlib.txt";
        callCalculateIndicators(filePath + "/" + fileName, filePath + "/" + fileName.replace(".txt", "_results.txt"));


//        // 获取当前目录下所有文件
//        String filePath = "data/small";
//        // 获取指定你文件对象
//        File file = new File(filePath);
//        // 获得该文件夹内的所有文件
//        File[] array = file.listFiles();
//        for (File value : array) {
//            if (value.isFile()) {    // 如果是文件
//                System.out.println(value.getName());
//                callCalculateIndicators(filePath + "/" + value.getName(), filePath + "/" + value.getName().replace(".txt", "_results.txt"));
//            }
//        }
    }
}


