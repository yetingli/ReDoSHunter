package cn.ac.ios.Utils;

import cn.ac.ios.Bean.AttackBean;
import cn.ac.ios.Bean.ReDoSBean;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BatchTestForJava {

    public static final String matches_path = "data/test_file/java_test_file/matches/";
    public static final String find_path = "data/test_file/java_test_file/find/";

    public static HashMap<String, Integer> javaHashMap = new HashMap<>();

    // attackFunctionName = "match", then generate match attack function
    // attackFunctionName = "find", then generate find attack function
    public static void generateJavaFile(String datasetName, String regex, ReDoSBean reDosBean, String attackFunctionName, String path) {
        int count = 0;
        File file = null;
        if (attackFunctionName.equals("match")) path = path.equals("") ? matches_path : path;
        else if (attackFunctionName.equals("find")) path = path.equals("") ? find_path : path;
        String pathName = path + datasetName + "_" + reDosBean.getRegexID() + "_" + attackFunctionName + "_";
        count = javaHashMap.getOrDefault(pathName, 0);
        file = new File(pathName + count + ".java");

        ArrayList<AttackBean> attackBeanList = reDosBean.getAttackBeanList();
        AttackBean attackBeanSuccess = null;
        for (AttackBean attackBean : attackBeanList) {
            if (attackBean.isAttackSuccess()) {
                attackBeanSuccess = attackBean;
                break;
            }
        }

        assert attackBeanSuccess != null;

        int repeatTime = 1000;
        String attackType = attackBeanSuccess.getType().name();
        if (attackType.equals("EXPONENT")) {
            repeatTime = 1;
        } else if (attackType.equals("POLYNOMIAL")) {
            repeatTime = 1000;
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
        String formatRegex = reDosBean.getRegex().replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("'", "\\'");

//        try {
//            Pattern.compile(formatRegex);
//        } catch (Exception e) {
//            try {
//                // 建树
//                TreeNode newlyttree = createnewlyttree(formatRegex);
//                // 去group name
//                newlyttree.deleteGroupName();
//                // 针对snort数据集中出现的{?写法 需要在{前加\ 暂不知是否还有其他需要加斜杠的
//                newlyttree.addBackslashBeforeSomeCharacters();
//                // 将方括号中的\0~\777重写为\u0000~\u0777
////                newlyttree.reWriteUnicodeNumberInBracketNode();
//                // 将方括号中的\b删除 因为方括号中的\b表示退格符
////                newlyttree.reWriteBackspace();
//                // 转换[\w-.] -> [\w\-.] 而 [a-z]保留 为了regexlib
//                newlyttree.rewriteIllegalBarSymbol();
//
//                formatRegex = newlyttree.getData();
//            } catch (Exception e1) {
//
//            }
//        }


        String fileContent =
                "import java.io.File;\n" +
                "import java.io.FileWriter;\n" +
                "import java.io.IOException;\n" +
                "import java.io.PrintWriter;\n" +
                "import java.util.regex.Matcher;\n" +
                "import java.util.regex.Pattern;\n" +
                "\n";

        fileContent = fileContent +
                "public class " + datasetName + "_" + reDosBean.getRegexID() + "_" + attackFunctionName + "_" + count + " {\n" +
                "    // " + reDosBean.getRegexID() + "\n" +
                "    // " + regex + "\n" +
                "    // " + attackBeanSuccess.getType().name() + "\n" +
                "    // " + "nums:" + String.valueOf(reDosBean.getVul()) + "\n" +
                "    // " + attackBeanSuccess.getType().name() + " AttackString:" + attackBeanSuccess.getAttackStringFormat() + "\n" +
                "    public static void printToFile(String fileName, String content) {\n" +
                "        FileWriter fw = null;\n" +
                "        try {\n" +
                "            File f=new File(fileName);\n" +
                "            fw = new FileWriter(f, true);\n" +
                "        } catch (IOException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "        PrintWriter pw = new PrintWriter(fw);\n" +
                "        pw.println(content);\n" +
                "        pw.flush();\n" +
                "        try {\n" +
                "            fw.flush();\n" +
                "            pw.close();\n" +
                "            fw.close();\n" +
                "        } catch (IOException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "    public static void main(String[] args) throws InterruptedException {\n" +
                "        String regex = \"" + formatRegex + "\";\n" +
                "        for (int i = 1; i < 110; i++) {\n" +
                "            StringBuilder attackString = new StringBuilder();\n" +
                "            // prefix\n" +
                "            attackString.append(\"" + formatPrefix + "\");\n" +
                "            // infix\n" +
                "            for (int j = 0; j < i * " + repeatTime + "; j++) {\n" +
                "                attackString.append(\"" + formatRepeat + "\");\n" +
                "            }\n" +
                "            // suffix\n" +
                "            attackString.append(\"" + formatSuffix + "\");\n" +
                "//            System.out.println(attackString);\n" +
                "            long time1 = System.nanoTime();\n";

        if (attackFunctionName.equals("match")) {
            fileContent = fileContent +
                    "            boolean isMatch = Pattern.matches(regex, attackString);\n";
        } else if (attackFunctionName.equals("find")) {
            fileContent = fileContent +
                    "            boolean isMatch = Pattern.compile(regex).matcher(attackString).find();\n";
        }

        fileContent = fileContent +
                "            long time2 = System.nanoTime();\n" +
                "//            System.out.println(i * " + repeatTime + " + \" \" + isMatch + \" \" + (time2 - time1)/1e9);\n" +
                "            printToFile(\"" + datasetName + "_" + reDosBean.getRegexID() + "_" + attackFunctionName + "_" + count + ".txt\", i * " + repeatTime + " + \" \" + isMatch + \" \" + (time2 - time1)/1e9);\n" +
                "        }\n" +
                "    }\n" +
                "}";


        try {
            javaHashMap.put(pathName, count + 1);
            FileUtils.write(file, fileContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateJavaFile(String datasetName, String regex, ReDoSBean reDosBean, String attackFunctionName) {
        generateJavaFile(datasetName, regex, reDosBean, attackFunctionName, "");
    }

    public static void main(String[] args) {

    }
}
