package cn.ac.ios.Utils;

import cn.ac.ios.Bean.AttackBean;
import cn.ac.ios.Bean.AttackType;
import cn.ac.ios.Bean.ReDoSBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static cn.ac.ios.Utils.Utils.readFile;

public class GenerateTestCode {
    public static void main(String[] args) throws IOException {
        String filePath = "data/expr";
        String fileName;
        fileName = "corpus_redos_s_2021_05_21_23_59_04.txt";
        run(filePath + "/" + fileName, fileName);
    }

    public static void run(String sourceFilePath, String fileName) {
        List<String> tasksData = readFile(sourceFilePath);

        ArrayList<ReDoSBean> beans = new ArrayList<>();

        int count = 1;
        int id = 0;
        String regex = "";
        for (int i = 0; i < tasksData.size(); i++) {
            String str = tasksData.get(i);
            if (str.startsWith("id:")) {
                id = Integer.parseInt(str.replace("id:", ""));
                i++;
                regex = tasksData.get(i);
                i++;
                if (tasksData.get(i).equals("RESULT-TRUE")) {
                    System.out.println(id);
                    i++;
                    str = tasksData.get(i);
                    while (!str.equals("-------------------------")) {
                        if (str.startsWith("failed TYPE:POLYNOMIAL\t AttackString：")
                                || str.startsWith("failed TYPE:EXPONENT\t AttackString：")
                                || str.startsWith("success TYPE:POLYNOMIAL\t AttackString：")
                                || str.startsWith("success TYPE:EXPONENT\t AttackString：")
                        ) {
                            ReDoSBean reDosBean = new ReDoSBean(regex, count, id, "");
                            str = str.replace("failed TYPE:", "").replace("success TYPE:", "").
                                    replace("EXPONENT\t AttackString：", "").
                                    replace("POLYNOMIAL\t AttackString：", "");
                            str = str.substring(1, str.length() - 2);
                            String[] strs = str.split("\"(\\*\\d{0,10})?\\+\"");
                            AttackBean attackBean = new AttackBean();
                            attackBean.initType(AttackType.POLYNOMIAL);
                            attackBean.setPrefix(strs.length >= 1 ? strs[0] : "");
                            attackBean.setInfix(strs.length >= 2 ? strs[1] : "");
                            attackBean.setSuffix(strs.length >= 3 ? strs[2] : "");
                            reDosBean.getAttackBeanList().add(attackBean);
                            attackBean.setAttackSuccess(true);
                            beans.add(reDosBean);
                            count++;
                        }
                        i++;
                        str = tasksData.get(i);
                    }
                }
            }
        }

//        for (int i = 0; i < beans.size(); i++) {
//            ReDosBean bean = beans.get(i);
//            generateJavaFile(fileName.replace(".txt", ""), bean.getRegex(), bean, "match", "regexploit/test_file/java_test_file/matches/");
//            generateJavaFile(fileName.replace(".txt", ""), bean.getRegex(), bean, "find", "regexploit/test_file/java_test_file/find/");
//            generateJavaScriptFile(fileName.replace(".txt", ""), bean.getRegex(), bean, "exec", "regexploit/test_file/javascript_test_file/exec/");
//            generateJavaScriptFile(fileName.replace(".txt", ""), bean.getRegex(), bean, "match", "regexploit/test_file/javascript_test_file/match/");
//            generateJavaScriptFile(fileName.replace(".txt", ""), bean.getRegex(), bean, "search", "regexploit/test_file/javascript_test_file/search/");
//            generateJavaScriptFile(fileName.replace(".txt", ""), bean.getRegex(), bean, "test", "regexploit/test_file/javascript_test_file/test/");
//            generatePythonREFile(fileName.replace(".txt", ""), bean.getRegex(), bean, "match", "regexploit/test_file/python_re_test_file/match/");
//            generatePythonREFile(fileName.replace(".txt", ""), bean.getRegex(), bean, "search", "regexploit/test_file/python_re_test_file/search/");
//            generatePythonRE2File(fileName.replace(".txt", ""), bean.getRegex(), bean, "match", "regexploit/test_file/python_re2_test_file/match/");
//            generatePythonRE2File(fileName.replace(".txt", ""), bean.getRegex(), bean, "search", "regexploit/test_file/python_re2_test_file/search/");
//        }
    }
}
