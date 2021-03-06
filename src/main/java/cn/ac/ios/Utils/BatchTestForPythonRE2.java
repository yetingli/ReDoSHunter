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

public class BatchTestForPythonRE2 {

    public static final String match_path = "data/test_file/python_re_test_file/match/";
    public static final String search_path = "data/test_file/python_re_test_file/search/";

    public static HashMap<String, Integer> pythonHashMap = new HashMap<>();

    // attackFunctionName = "match", then generate match attack function
    // attackFunctionName = "search", then generate search attack function
    public static void generatePythonRE2File(String datasetName, String regex, ReDoSBean reDosBean, String attackFunctionName, String path) {
        int count = 0;
        File file = null;
        if (attackFunctionName.equals("match")) path = path.equals("") ? match_path : path;
        if (attackFunctionName.equals("search")) path = path.equals("") ? search_path : path;
        String pathName = path + datasetName + "_" + reDosBean.getRegexID() + "_" + attackFunctionName + "_";
        count = pythonHashMap.getOrDefault(pathName, 0);
        file = new File(pathName + count + ".py");

        ArrayList<AttackBean> attackBeanList = reDosBean.getAttackBeanList();
        AttackBean attackBeanSuccess = null;
        for (AttackBean attackBean: attackBeanList) {
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
            // ?????????????????????
//            regex = rewriteRegex(regex);
//            regex = reduceLocalFlags(regex);
//            regex = removeAnnotationByFlagX(regex);
//            regex = processLocalFlag(regex);
//            regex = replaceLocalFlagGM(regex);

            // ??????
            TreeNode newlyttree = createReDoSTree(regex);
            // ???group name
            newlyttree.deleteGroupName();
//            // ??????snort?????????????????????{??????? ?????????{??????\ ?????????????????????????????????????????????
//            newlyttree.addBackslashBeforeSomeCharacters();
//            // ??????????????????\0~\777?????????\u0000~\u0777
////                newlyttree.reWriteUnicodeNumberInBracketNode();
//            // ??????????????????\b?????? ?????????????????????\b???????????????
////                newlyttree.reWriteBackspace();
//            // ??????[\w-.] -> [\w\-.] ??? [a-z]?????? ??????regexlib
//            newlyttree.rewriteIllegalBarSymbol();

            formatRegex = newlyttree.getData();
        } catch (Exception e) {
            e.printStackTrace();
            formatRegex = regex;
        }

        String fileContent =
                "# " + reDosBean.getRegexID() + "\n" +
                "# " + regex + "\n" +
                "# " + attackBeanSuccess.getType().name() + "\n" +
                "# " + "nums:" + String.valueOf(reDosBean.getVul()) + "\n" +
                "# " + attackBeanSuccess.getType().name() + " AttackString:"+ attackBeanSuccess.getAttackStringFormat() + "\n" +
                "\n" +
                "import re\n" +
                "from time import perf_counter\n" +
                "\n" +
                "regex = r\'\'\'" + formatRegex + "\'\'\'\n" +
                "REGEX = re.compile(regex)\n" +
                "for i in range(1, 12):\n" +
                "    ATTACK = \"" + formatPrefix + "\" + \"" + formatRepeat + "\" * i * " + repeatTime + " + \"" + formatSuffix + "\"\n" +
                "    LEN = len(ATTACK)\n" +
                "    BEGIN = perf_counter()\n" +
                "    m = REGEX." + attackFunctionName + "(ATTACK)\n" +
                "    DURATION = perf_counter() - BEGIN\n" +
                "    # print(f\"{i * 10000}: took {DURATION} seconds!\")\n" +
                "    with open('" + datasetName + "_" + reDosBean.getRegexID() + "_" + attackFunctionName + "_" + count + ".txt', 'a+') as f:\n" +
                "        f.write(str(i * 10000) + ' ' + str(DURATION) + '\\n')";

        try {
            pythonHashMap.put(pathName, count + 1);
            FileUtils.write(file, fileContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generatePythonRE2File(String datasetName, String regex, ReDoSBean reDosBean, String attackFunctionName) {
        generatePythonRE2File(datasetName, regex, reDosBean, attackFunctionName, "");
    }
    public static void main(String[] args) {

    }
}
