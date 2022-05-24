package cn.ac.ios;

import cn.ac.ios.Bean.Attack;
import cn.ac.ios.Bean.AttackBean;
import cn.ac.ios.Bean.Output;
import cn.ac.ios.Bean.ReDoSBean;
import cn.ac.ios.Utils.multithread.ITask;
import cn.ac.ios.Utils.multithread.MultiBaseBean;
import cn.ac.ios.Utils.multithread.MultiThreadUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.ac.ios.Main.ATTACK_MODEL_MULTI;
import static cn.ac.ios.Main.ATTACK_MODEL_SINGLE;
import static cn.ac.ios.Utils.Utils.readFile;

/**
 * @author pqc
 */
public class Test {
    public static void main(String[] args) throws IOException {
        String filePath = "data/paper_dataset";
        String fileName = "regexlib.txt";
        run(filePath + "/" + fileName, fileName);
    }

    private static void run(String s, String fileName) {
        run(s, fileName, "s", "java", "11111", 0, 15, 1, 60);
    }

    public static void run(String sourceFile, String outfileName, String model, String language, String patternModel, int funcType, int checkThreadCount, int validateThreadCount, int timeout) {
        List<String> tasksData = readFile(sourceFile);
        long startTime = System.currentTimeMillis();
        MultiThreadUtils<String, ReDoSBean> threadUtils = MultiThreadUtils.newInstance(checkThreadCount, timeout);
        MultiBaseBean<List<ReDoSBean>> multiBaseBean;
        if (tasksData == null || tasksData.isEmpty()) {
            multiBaseBean = new MultiBaseBean<>(null);
        } else {
            multiBaseBean = threadUtils.execute(tasksData, null, new ITask<String, ReDoSBean>() {
                @Override
                public ReDoSBean execute(String regex, Map<String, Integer> params) {
                    return (ReDoSMain.checkReDoS(regex, params.get("id"), patternModel, language));
                }
            });
        }

        long checkEndTime = System.currentTimeMillis();

        MultiThreadUtils<ReDoSBean, ReDoSBean> threadValidateUtils = MultiThreadUtils.newInstance(validateThreadCount, timeout);
        MultiBaseBean<List<ReDoSBean>> validateBeans;
        validateBeans = threadValidateUtils.execute(multiBaseBean.getData(), null, new ITask<ReDoSBean, ReDoSBean>() {
            @Override
            public ReDoSBean execute(ReDoSBean bean, Map<String, Integer> params) {
                return (ReDoSMain.validateReDoS(bean, model, language));
            }
        });

        long validateEndTime = System.currentTimeMillis();

        List<ReDoSBean> resultBeans = new ArrayList<>();
        for (int i = 0; i < tasksData.size(); i++) {
            resultBeans.add(new ReDoSBean(tasksData.get(i), i + 1, i + 1, "TIME-OUT"));
        }
        for (ReDoSBean bean : validateBeans.getData()) {
            resultBeans.set(bean.getId() - 1, bean);
        }
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();

        list2.add("real sum time = " + (validateEndTime - startTime) / 1000 + "(s)");
        list2.add("real check time = " + (checkEndTime - startTime) / 1000 + "(s)");
        list2.add("real validate time = " + (validateEndTime - checkEndTime) / 1000 + "(s)");
        list2.add("real average time = " + (validateEndTime - startTime) / 1000 / (double) tasksData.size() + "(s)");

        for (ReDoSBean bean : resultBeans) {
            list.add("id:" + String.valueOf(bean.getRegexID()));
            list.add(bean.getRegex());
            if (bean.isReDoS()) {
                list2.add("id:" + String.valueOf(bean.getRegexID()));
                list2.add(bean.getRegex());
                list2.add("RESULT-TRUE");
                list2.add(bean.getType().name());
                list2.add("nums:" + String.valueOf(bean.getVul()));
                list.add("RESULT-TRUE");
                boolean flag = true;
                for (int i = 0; i < bean.getAttackBeanList().size(); i++) {
                    if (bean.getAttackBeanList().get(i).isAttackSuccess()) {
                        list.add("success TYPE: " + bean.getAttackBeanList().get(i).getType() + "\t AttackString：" + bean.getAttackBeanList().get(i).getAttackStringFormat());
                        list.add("vulnerability Position: " + bean.getAttackBeanList().get(i).getLocateVulnerabilityRegex());
                        list.add("vulnerability Source: " + bean.getAttackBeanList().get(i).getVulnerabilityRegexSource());
                        if ((model.equals(ATTACK_MODEL_SINGLE) && flag) || model.equals(ATTACK_MODEL_MULTI)) {  // 修改
                            list2.add(bean.getAttackBeanList().get(i).getAttackStringFormatType());
                            list2.add("patternType: " + bean.getAttackBeanList().get(i).getPatternType());
                            list2.add("vulnerability Position: " + bean.getAttackBeanList().get(i).getLocateVulnerabilityRegex());
                            list2.add("vulnerability Source: " + bean.getAttackBeanList().get(i).getVulnerabilityRegexSource());
                            flag = false;
                        }
                    } else {
                        list.add("failed TYPE: " + bean.getAttackBeanList().get(i).getType() + "\t AttackString：" + bean.getAttackBeanList().get(i).getAttackStringFormat());
//                        list2.add(bean.getAttackBeanList().get(i).getAttackStringFormatType());
                        list.add("patternType: " + bean.getAttackBeanList().get(i).getPatternType());
                    }
                }
                list2.add("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//                generateJavaFile(outfileName.replace(".txt", ""), bean.getRegex(), bean, "match");
//                generateJavaFile(outfileName.replace(".txt", ""), bean.getRegex(), bean, "find");
//                generateJavaScriptFile(outfileName.replace(".txt", ""), bean.getRegex(), bean, "exec");
//                generateJavaScriptFile(outfileName.replace(".txt", ""), bean.getRegex(), bean, "match");
//                generateJavaScriptFile(outfileName.replace(".txt", ""), bean.getRegex(), bean, "search");
//                generateJavaScriptFile(outfileName.replace(".txt", ""), bean.getRegex(), bean, "test");
//                generatePythonREFile(outfileName.replace(".txt", ""), bean.getRegex(), bean, "match");
//                generatePythonREFile(outfileName.replace(".txt", ""), bean.getRegex(), bean, "search");
//                generatePythonRE2File(outfileName.replace(".txt", ""), bean.getRegex(), bean, "match");
//                generatePythonRE2File(outfileName.replace(".txt", ""), bean.getRegex(), bean, "search");
            } else {
                list.add("RESULT-FALSE");
                list.add(bean.getMessage());
                for (int i = 0; i < bean.getAttackBeanList().size(); i++) {
                    if (bean.getAttackBeanList().get(i).isAttackSuccess()) {
                        list.add("success TYPE: " + bean.getAttackBeanList().get(i).getType() + "\t AttackString：" + bean.getAttackBeanList().get(i).getAttackStringFormat() + "\t patternType: " + bean.getAttackBeanList().get(i).getPatternType());
                    } else {
                        list.add("failed TYPE:" + bean.getAttackBeanList().get(i).getType() + "\t AttackString：" + bean.getAttackBeanList().get(i).getAttackStringFormat() + "\t patternType: " + bean.getAttackBeanList().get(i).getPatternType());

                    }
                }
            }
            list.add("-------------------------");
        }
        try {
            Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
            DateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String tsStr = sdf.format(timeStamp);
            tsStr = model + "_" + language + "_" + patternModel + "_" + funcType + "_" + tsStr;
            FileUtils.writeLines(new File("data/expr/" + outfileName.replace(".txt", "") + "_redos_" + tsStr + ".txt"), list);
            FileUtils.writeLines(new File("data/expr/" + outfileName.replace(".txt", "") + "_only_redos_true_" + tsStr + ".txt"), list2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0); // 新增
    }

    public static void onlyCheck(String sourceFile, String outfileName, String model, String language, String patternModel, int funcType, int checkThreadCount, int validateThreadCount, int timeout) {
        List<String> tasksData = readFile(sourceFile);
        MultiThreadUtils<String, ReDoSBean> threadUtils = MultiThreadUtils.newInstance(checkThreadCount, timeout);
        MultiBaseBean<List<ReDoSBean>> multiBaseBean;
        if (tasksData == null || tasksData.isEmpty()) {
            multiBaseBean = new MultiBaseBean<>(null);
        } else {
            multiBaseBean = threadUtils.execute(tasksData, null, new ITask<String, ReDoSBean>() {
                @Override
                public ReDoSBean execute(String regex, Map<String, Integer> params) {
                    return (ReDoSMain.checkReDoS(regex, params.get("id"), patternModel, language));
                }
            });
        }
        List<ReDoSBean> list = multiBaseBean.getData();

        try {
            Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
            DateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
            String tsStr = sdf.format(timeStamp);
            tsStr = model + "_" + language + "_" + patternModel + "_" + funcType + "_" + tsStr;
            ArrayList<Output> outputs = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                ReDoSBean reDoSBean = list.get(i);
                ArrayList<Attack> attackArrayList = new ArrayList<>();
                int k = 0;
                for (AttackBean bean : reDoSBean.getAttackBeanList()) {
                    Attack attack = new Attack(bean.getPrefix(), bean.getInfix(), bean.getSuffix(), bean.getType(), bean.getPatternType());
                    attackArrayList.add(attack);
                    k++;
                }
                outputs.add(new Output(reDoSBean.getRegexID(), reDoSBean.getRegex(), attackArrayList));
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(outputs);
//            String json= JSON.toJSONString(outputs);
            FileUtils.write(new File("C:\\Users\\pengqc\\Desktop\\pqc\\csharp_only_check\\" + outfileName.replace(".txt", "") + "_only_check_" + tsStr + ".txt"), json, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void pkg(String[] args) throws ParseException {

        CommandLineParser parser = new BasicParser();
        Options options = new Options();
        options.addOption("h", "help", false, "Print this usage information");
        options.addOption("s", "source file", true, "file to run");
        options.addOption("o", "output file", true, "file to save program output to");
        options.addOption("m", "model", false, "attack model s or m");
        options.addOption("l", "language", false, "validate language such as java python or js");
        options.addOption("p", "pattern model", false, "check model such 11111 is all pattern model");
        options.addOption("f", "attack func", false, "attack func 0-all,  1-match or 2-find");
        options.addOption("c", "check", false, "check thread num");
        options.addOption("v", "validate", false, "validate thread num");
        options.addOption("t", "time", false, "time_out second");
        CommandLine commandLine = parser.parse(options, args);

        String sourcefile = "";
        String outfileName = "";
        String model = "s";
        String language = "java";
        String patternModel = "11111";
        int funcType = 0;
        int checkThreadCount = 15;
        int validateThreadCount = 1;
        int time = 60;
        if (commandLine.hasOption('h')) {
            HelpFormatter formatter = new HelpFormatter();
            PrintWriter writer = new PrintWriter(System.out);
            formatter.printUsage(writer, 80, "ReDoSHunter", options);
            formatter.printHelp("ReDoSHunter", options);
            writer.flush();
            System.exit(0);
        }
        if (commandLine.hasOption('s')) {
            sourcefile = commandLine.getOptionValue("s");
        }
        if (commandLine.hasOption('o')) {
            outfileName = commandLine.getOptionValue("o");
        }
        if (commandLine.hasOption('m')) {
            model = commandLine.getOptionValue('m');
        }
        if (commandLine.hasOption('l')) {
            language = commandLine.getOptionValue('l');
        }
        if (commandLine.hasOption('p')) {
            patternModel = commandLine.getOptionValue('p');
        }
        if (commandLine.hasOption('f')) {
            funcType = Integer.parseInt(commandLine.getOptionValue('f'));
        }
        if (commandLine.hasOption('c')) {
            checkThreadCount = Integer.parseInt(commandLine.getOptionValue('c'));
        }
        if (commandLine.hasOption('v')) {
            validateThreadCount = Integer.parseInt(commandLine.getOptionValue('v'));
        }

        if (commandLine.hasOption('t')) {
            time = Integer.parseInt(commandLine.getOptionValue('t'));
        }
        System.out.println(sourcefile);
        System.out.println(outfileName);
        System.out.println(model);
        System.out.println(language);
        System.out.println(patternModel);
        System.out.println(funcType);
        System.out.println(checkThreadCount);
        System.out.println(validateThreadCount);
        System.out.println(time);

        run(sourcefile, outfileName, model, language, patternModel, funcType, checkThreadCount, validateThreadCount, time);
        System.out.println("exit");
        System.exit(0);
    }
}