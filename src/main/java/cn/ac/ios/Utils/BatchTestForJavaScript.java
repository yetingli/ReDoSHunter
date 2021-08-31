package cn.ac.ios.Utils;

import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Bean.AttackBean;
import cn.ac.ios.Bean.ReDoSBean;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.TreeNode.Utils.rewriteRegex;
import static cn.ac.ios.Utils.FlagsUtils.*;
import static cn.ac.ios.Utils.FlagsUtils.replaceLocalFlagGM;

public class BatchTestForJavaScript {
    public static final String exec_path = "data/test_file/javascript_test_file/exec/";
    public static final String match_path = "data/test_file/javascript_test_file/match/";
    public static final String search_path = "data/test_file/javascript_test_file/search/";
    public static final String test_path = "data/test_file/javascript_test_file/test/";

    public static HashMap<String, Integer> javascriptHashMap = new HashMap<>();

    // attackFunctionName = "exec", then generate exec attack function
    // attackFunctionName = "match", then generate match attack function
    // attackFunctionName = "search", then generate search attack function
    // attackFunctionName = "test", then generate test attack function
    public static void generateJavaScriptFile(String datasetName, String regex, ReDoSBean reDosBean, String attackFunctionName, String path) {
        int count = 0;
        File file = null;
        if (attackFunctionName.equals("exec")) path = path.equals("") ? exec_path : path;
        else if (attackFunctionName.equals("match")) path = path.equals("") ? match_path : path;
        else if (attackFunctionName.equals("search")) path = path.equals("") ? search_path : path;
        else if (attackFunctionName.equals("test")) path = path.equals("") ? test_path : path;
        String pathName = path + datasetName + "_" + reDosBean.getRegexID() + "_" + attackFunctionName + "_";
        count = javascriptHashMap.getOrDefault(pathName, 0);
        file = new File(pathName + count + ".js");

        ArrayList<AttackBean> attackBeanList = reDosBean.getAttackBeanList();
        AttackBean attackBeanSuccess = null;
        for (AttackBean attackBean : attackBeanList) {
            if (attackBean.isAttackSuccess()) {
                attackBeanSuccess = attackBean;
                break;
            }
        }

        assert attackBeanSuccess != null;

        int repeatTime = 10000;
        String attackType = attackBeanSuccess.getType().name();
        if (attackType.equals("EXPONENT")) {
            repeatTime = 1;
        } else if (attackType.equals("POLYNOMIAL")) {
            repeatTime = 10000;
        }

        String formatPrefix = attackBeanSuccess.getPrefix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\"", "\\\"")
                .replace("'", "\\'");
        String formatRepeat = attackBeanSuccess.getInfix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\"", "\\\"")
                .replace("'", "\\'");
        String formatSuffix = attackBeanSuccess.getSuffix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\"", "\\\"")
                .replace("'", "\\'");

        String formatRegex = null;
        try {
            // 最开头的预处理
            regex = rewriteRegex(regex);
            regex = reduceLocalFlags(regex);
            regex = removeAnnotationByFlagX(regex);
            regex = processLocalFlag(regex);
            regex = replaceLocalFlagGM(regex);

            // 建树
            TreeNode newlyttree = createReDoSTree(regex);
            // 去group name
            newlyttree.deleteGroupName();
//            // 针对snort数据集中出现的{?写法 需要在{前加\ 暂不知是否还有其他需要加斜杠的
//            newlyttree.addBackslashBeforeSomeCharacters();
//            // 将方括号中的\0~\777重写为\u0000~\u0777
////                newlyttree.reWriteUnicodeNumberInBracketNode();
//            // 将方括号中的\b删除 因为方括号中的\b表示退格符
////                newlyttree.reWriteBackspace();
//            // 转换[\w-.] -> [\w\-.] 而 [a-z]保留 为了regexlib
//            newlyttree.rewriteIllegalBarSymbol();

            formatRegex = newlyttree.getData();
        } catch (Exception e) {
            e.printStackTrace();
            formatRegex = regex;
        }

        formatRegex = formatRegex.replace("(?s)", "")
                .replace("(?i)", "")
                .replace("(?x)", "")
                .replace("(?-i)", "")
                .replace("(?-s)","")
                .replace("(?m)", "")
                .replace("(?m:", "(");

        String fileContent =
                "var fs= require(\"fs\");\n" +
                "\n" +
                "// " + reDosBean.getRegexID() + "\n" +
                "// " + regex + "\n" +
                "// " + attackBeanSuccess.getType().name() + "\n" +
                "// " + "nums:" + String.valueOf(reDosBean.getVul()) + "\n" +
                "// " + attackBeanSuccess.getType().name() + " AttackString:" + attackBeanSuccess.getAttackStringFormat() + "\n";

//        if (formatRegex.contains("/") && formatRegex.contains("\"")) {
//            fileContent +=
//                    "var REGEX = new RegExp(\'" + formatRegex + "\')\n";
//        } else if ((formatRegex.contains("/")) && !formatRegex.contains("\"")) {
//            fileContent +=
//                    "var REGEX = new RegExp(\"" + formatRegex + "\")\n";
//        } else {
            fileContent +=
                    "var REGEX = /" + formatRegex + "/\n";
//        }
        fileContent +=
                "for(var i = 1; i <= 12; i++) {\n" +
                "    var time = Date.now();\n" +
                "    var attack_str = '" + formatPrefix + "' + '" + formatRepeat + "'.repeat(i*" + repeatTime + ") + '" + formatSuffix + "'\n";

        if (attackFunctionName.equals("exec")) {
            fileContent = fileContent +
                    "    REGEX.exec(attack_str)\n";
        } else if (attackFunctionName.equals("test")) {
            fileContent = fileContent +
                    "    REGEX.test(attack_str);\n";
        } else if (attackFunctionName.equals("search")) {
            fileContent = fileContent +
                    "    attack_str.search(REGEX)\n";
        } else if (attackFunctionName.equals("match")) {
            fileContent = fileContent +
                    "    attack_str.match(REGEX)\n";
        }

        fileContent = fileContent +
                "    var time_cost = Date.now() - time;\n" +
                "    // console.log(i * " + repeatTime + " + \": \" + time_cost+\" ms\")\n" +
                "    fs.appendFileSync('" + datasetName + "_" + reDosBean.getRegexID() + "_" + attackFunctionName + "_" + count + ".txt', i * 10000 + \" \" + time_cost / 1000 + \"\\n\");\n" +
                "}";


        try {
            javascriptHashMap.put(pathName, count + 1);
            FileUtils.write(file, fileContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateJavaScriptFile(String datasetName, String regex, ReDoSBean reDosBean, String attackFunctionName) {
        generateJavaScriptFile(datasetName, regex, reDosBean, attackFunctionName, "");
    }
}