package cn.ac.ios.Utils;

import cn.ac.ios.TreeNode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.ac.ios.TreeNode.Utils.*;
import static cn.ac.ios.Utils.Utils.readFile;

public class CalculateMaxNestedHeight {
    
    
    // 数starHeight * + {m,} {m,n} 的最大嵌套深度
    public static void calculateMaxStarHeight2(TreeNode newlyttree, boolean starHeightFlag, int starHeight, List<Integer> maxStarHeight) {
        if (!starHeightFlag) {
            if (newlyttree.getData().contains("*") || newlyttree.getData().contains("+") || (newlyttree.getData().startsWith("{") && newlyttree.getData().endsWith("}"))) {
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

                if ("*".equals(now.getData()) || "+".equals(now.getData()) || "?".equals(now.getData()) || (now.getData().startsWith("{") && now.getData().endsWith("}")) && starHeightFlag) {
//                    System.out.println(pre.data);
                    calculateMaxStarHeight2(pre, starHeightFlag, starHeight + 1, maxStarHeight);
                } else {
                    calculateMaxStarHeight2(pre, starHeightFlag, starHeight, maxStarHeight);
                }
            }
            calculateMaxStarHeight2(chirdren.get(chirdren.size() - 1), starHeightFlag, starHeight, maxStarHeight);
        }
    }


    // 计算
    public static int calculate(String regex) {
        try {
            String initalRegex = regex;

            // 最开头的预处理
            regex = rewriteRegex(regex);

            // 预处理recurses the subpattern
            regex = regex.replace("(?&", "(?P=");
            // 预处理掉两种情况[\*\+], {m,n} 其中m==n
            regex = regex.replace("[^\\\\]", "@")
                    .replace("[^\\\"\\\\]", "@")
                    .replace("[\\\\]", "@")
                    .replace("[^/\\\\]", "@")
                    .replace("[^'\\\\]", "@")
                    .replace("[^\\.\\*\\?\\s\\r\\n\\\\]", "@")
                    .replace("[/\\\\]", "@")
                    .replace("[/?\\\\]", "@")
                    .replace("[^\\\\\"\\\\]", "@")
                    .replace("[^\\'\\\\]", "@")
                    .replace("+!(){}\\[\\]^\\\"~*?:\\\\]", "@")
                    .replace("[^ \\n:#@\\[\\]\\\"<>]", "@")
                    .replace("[:#@\\[\\]<>]", "@")
                    .replace("[^*\\\\]", "@")
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
            regex = regex.replace("\\[", "@");
//        .replace("\\]", "@");
            regex = regex.replaceAll("\\[.*?]", "@");
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
                    .replace("(?u)", "");
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

//        // 调用prism.js去掉(?: (?= (?<=
//        regex = callPrism(regex);
////		System.out.println(regex);

            // 去掉(?: (?= (?<=
            regex = regex.replace("(?:", "(").replace("(?=", "(").replace("(?<=", "(").replace("(?!", "(").replace("(?<!", "(");

            // 建树
            TreeNode newlyttree = createReDoSTree(regex);

//        // 重写反向引用
//        newlyttree.rewriteBackreferences();


            // 计算最大嵌套深度
            List<Integer> maxStarHeight2 = new LinkedList<>();
            maxStarHeight2.add(0);
            boolean starHeightFlag = false;
            if (newlyttree.getData().contains("*") || newlyttree.getData().contains("+") || newlyttree.getData().contains("?") || (newlyttree.getData().contains("{") && newlyttree.getData().contains("}"))) {
                starHeightFlag = true;
            }
            calculateMaxStarHeight2(newlyttree, starHeightFlag, 0, maxStarHeight2);
//        System.out.println(maxStarHeight2.get(0));

//        System.out.println(regex + "\t" + maxStarHeight2.get(0));
//        String result = regex + "\t" + maxStarHeight2.get(0);
//        return result;
             return maxStarHeight2.get(0);
        } catch (InterruptedException e) {
            return 0;
        } catch (Exception e){
            return 0;
        }

    }

    public static void main(String[] args) {
        String regex = "^([-\\w\\d\\.]+?)(?:\\s+at\\s+|\\s*@\\s*|\\s*(?:[\\[\\]@]){3}\\s*)([-\\w\\d\\.]*?)\\s*(?:dot|\\.|(?:[\\[\\]dot\\.]){3,5})\\s*(\\w+)$";
//        regex = "^([-\\w\\d\\.]+?)(?:\\s+at\\s+|\\s*@\\s*|\\s*(?:[\\[\\]@]){3}\\s*)([-\\w\\d\\.]*?)\\s*(?:dot|\\.|(?:[\\[\\]dot\\.]){3,5})\\s*(\\w+)$";
//        System.out.println(calculate(regex));
//        System.exit(0);
        String sourceFilePath = "data/large/regex101.txt";
        List<String> regexLineContent = readFile(sourceFilePath);
        for (int i = 0; i < regexLineContent.size(); i++) {
            System.out.println(i + " / " + regexLineContent.size());
            int result = calculate(regexLineContent.get(i));
            System.out.println(regexLineContent.get(i) + "\t" + result);
        }

    }
}
