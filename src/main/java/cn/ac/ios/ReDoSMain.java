package cn.ac.ios;

import cn.ac.ios.Bean.*;
import cn.ac.ios.Patterns.SLQ.PatternSLQUtils;
import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Patterns.EOA.PatternEOAUtils;
import cn.ac.ios.Patterns.EOD.PatternEODUtils;
import cn.ac.ios.Patterns.NQ.PatternNQUtils;
import cn.ac.ios.Patterns.POA.PatternPOAUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.Utils.Constant.EXTENDED_COUNTING;
import static cn.ac.ios.Utils.FlagsUtils.*;

/**
 * @author pqc
 */
public class ReDoSMain {

    public static String PYTHON = "python3";
    public static String JS = "node";

    public static void main(String[] args) throws IOException, InterruptedException {
        String regex = "\\u003cli(.*?)\\u003e(.*?)\\u003c\\\\/li\\u003e";
        regex = "^(\\w+)\\w+$";
        regex = "^Set-Cookie:\\\\s*([^=]+)=([^;]+)";
//        regex = "^Set-Cookie:(\\w+)a(\\w+)$";
//        regex = "a+a+b";
        ReDoSBean bean = validateReDoS(checkReDoS(regex, 1, "11111", "perl"), "s", "perl");
        System.out.println(bean.getRegex());
        for (int i = 0; i < bean.getAttackBeanList().size(); i++) {
            if (bean.getAttackBeanList().get(i).isAttackSuccess()) {
                System.out.println("Is attack success: " + bean.getAttackBeanList().get(i).isAttackSuccess());
                System.out.println("Attack time: " + bean.getAttackBeanList().get(i).getAttackTime() + " (ms)");
                System.out.println("Vulnerability Position: " + bean.getAttackBeanList().get(i).getLocateVulnerabilityRegex());
                System.out.println("Attack String: " + bean.getAttackBeanList().get(i).getAttackStringFormat());
                System.out.println("Vulnerability Source: " + bean.getAttackBeanList().get(i).getVulnerabilityRegexSource());
                System.out.println("Vulnerability Degree: " + bean.getAttackBeanList().get(i).getType());
            }
        }
    }

    /**
     * 获取正则的判断结果
     * 阶段一：检测
     *
     * @param regex
     * @param id
     * @return
     */
    public static ReDoSBean checkReDoS(String regex, Integer id, String options, String language) {
        ReDoSBean bean = new ReDoSBean();
        bean.setRegex(regex);
        bean.setId(id);
        bean.setRegexID(id);
        if (mustBeNoReDoS(regex)) {
            bean.setMessage("MUST_NOT_BE_REDOS");
            return bean;
        }

        if (options.charAt(0) == '1') {
            ReDoSBean bean1 = PatternNQUtils.getNQReDoSBean(regex, language);
            bean.getAttackBeanList().addAll(bean1.getAttackBeanList());
        }

        if (options.charAt(1) == '1') {
            ReDoSBean bean2 = PatternEODUtils.getEODReDoSBean(regex, language);
            bean.getAttackBeanList().addAll(bean2.getAttackBeanList());
        }

        if (options.charAt(2) == '1') {
            ReDoSBean bean3 = PatternEOAUtils.getEOAReDoSBean(regex, language);
            bean.getAttackBeanList().addAll(bean3.getAttackBeanList());
        }

        if (options.charAt(3) == '1') {
            ReDoSBean bean4 = PatternPOAUtils.getPOAReDoSBean(regex, language);
            bean.getAttackBeanList().addAll(bean4.getAttackBeanList());
        }

        if (options.charAt(4) == '1') {
            ReDoSBean bean5 = PatternSLQUtils.getSLQReDoSBean(regex, language);
            bean.getAttackBeanList().addAll(bean5.getAttackBeanList());
        }

        return bean;
    }

    /**
     * 获取正则的判断结果
     * 阶段一：检测
     *
     * @param regex
     * @param id
     * @return
     */
    public static ReDoSBean checkReDoS(String regex, Integer id) {
        return checkReDoS(regex, id, "11111", "java");
    }

    /**
     * 利用攻击串验证判断结果
     * 阶段二：验证
     *
     * @param bean
     * @return
     */
    public static ReDoSBean validateReDoS(ReDoSBean bean, String model, String language) {
        bean.setReDoS(false);
        if (bean.getAttackBeanList().isEmpty()) {
            return bean;
        }
        bean.duplicate();
        if ("python".equals(language)) {
            return getPython(bean, model);
        } else if ("js".equals(language)) {
            return getJS(bean, model);
        } else if ("perl".endsWith(language)) {
            return getPerl(bean, model);
        } else if ("php".endsWith(language)) {
            return getPHP(bean, model);
        } else {
            return getJava(bean, model);
        }
    }

    /**
     * 使用java语言验证
     *
     * @param bean
     * @param model
     * @return
     */
    public static ReDoSBean getJava(ReDoSBean bean, String model) {
        RegexBean divideRegexByFlagsBean = divideRegexByFlags(bean.getRegex());
        String newRegex = divideRegexByFlagsBean.getRegex();
        String allFlags = divideRegexByFlagsBean.getAllFlags();
        if (allFlags.contains("s")) {
            newRegex = "(?s)" + newRegex;
        }
        if (allFlags.contains("i")) {
            newRegex = "(?i)" + newRegex;
        }
        if (allFlags.contains("m")) {
            newRegex = "(?m)" + newRegex;
        }
        if (allFlags.contains("x")) {
            newRegex = "(?x)" + newRegex;
        }
        bean.setRegex(newRegex);

//        bean.setRegex(divideRegexByFlags(bean.getRegex()).getRegex());
        try {
            Pattern.compile(bean.getRegex());
        } catch (Exception e) {
            try {
//                String regex = deleteGroupName(bean.getRegex());

                // 最开头的预处理
//                regex = rewriteRegex(regex);
//                regex = reduceLocalFlags(regex);
//                regex = removeAnnotationByFlagX(regex);
//                regex = processLocalFlag(regex);
//                regex = replaceLocalFlagGM(regex);
                String regex = bean.getRegex();
                // 建树
                TreeNode ReDoSTree = createReDoSTree(regex);
                // 删除注释
                ReDoSTree.deleteAnnotation();
                // 去group name
                ReDoSTree.deleteGroupName();
                // 针对snort数据集中出现的{?写法 需要在{前加\ 暂不知是否还有其他需要加斜杠的
                ReDoSTree.addBackslashBeforeSomeCharacters();
                // 将方括号中的\0~\777重写为\u0000~\u0777
                ReDoSTree.rewriteUnicodeNumberInBracketNode();
                // 将方括号中的\b删除 因为方括号中的\b表示退格符
                ReDoSTree.reWriteBackspace();
                // 转换[\w-.] -> [\w\-.] 而 [a-z]保留 为了regexlib
                ReDoSTree.rewriteIllegalBarSymbol();
                // 处理特殊斜杠字符 根据不同的语言
                ReDoSTree.rewriteSpecialBackslashCharacterForDifferentLanguage("java");

                regex = ReDoSTree.getData();
                bean.setRegex(regex);
            } catch (Exception exception) {
                bean.setReDoS(false);
                return bean;
            }
        }
        bean.attack(model);
        return bean;
    }


    /**
     * 使用python语言验证
     *
     * @param bean
     * @param model
     * @return
     */
    public static ReDoSBean getPython(ReDoSBean bean, String model) {
        bean.setRegex(divideRegexByFlags(bean.getRegex()).getRegex());
        System.out.println("waring:Your environment must support the command \"python3\" and not support for Windows");
        List<String> list = new ArrayList<>();
        list.add(bean.getRegex());
        list.add(model);
        for (int i = 0; i < bean.getAttackBeanList().size(); i++) {
            AttackBean attackBean = bean.getAttackBeanList().get(i);
            list.add(attackBean.getType().name());
            list.add(attackBean.getAttackStringFormatSp());
        }
        String name = System.currentTimeMillis() + "python_attack.txt";
        try {
            FileUtils.writeLines(new File("python/" + name), list);

            Process proc;
            String[] args = new String[]{PYTHON, "python/attack.py", "python/" + name};
            proc = Runtime.getRuntime().exec(args);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
            ArrayList<String> results = (ArrayList<String>) FileUtils.readLines(new File("python/" + name.replace(".txt", "_result.txt")), "utf-8");
            for (int i = 0; i < bean.getAttackBeanList().size(); i++) {
                String[] s = results.get(i).toLowerCase().split("IOS_AC_CN".toLowerCase());
                bean.getAttackBeanList().get(i).setAttackSuccess(Boolean.parseBoolean(s[0]));
                if (Boolean.parseBoolean(s[0])) {
                    bean.setReDoS(true);
                }
                bean.getAttackBeanList().get(i).setRepeatTimes(Integer.parseInt(s[1]));
                bean.getAttackBeanList().get(i).setAttackTime(Integer.parseInt(s[2]));
                bean.getAttackBeanList().get(i).confirmType();
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.deleteQuietly(new File("python/" + name));
            FileUtils.deleteQuietly(new File("python/" + name.replace(".txt", "_result.txt")));
        }
        return bean;
    }

    /**
     * 使用js语言验证
     *
     * @param bean
     * @param model
     * @return
     */
    public static ReDoSBean getJS(ReDoSBean bean, String model) {
        System.out.println("waring:Your environment must support the command \"node\"");
        List<String> list = new ArrayList<>();
        String regex = divideRegexByFlags(bean.getRegex()).getRegex();
        String flags = divideRegexByFlags(bean.getRegex()).getFlags();
        list.add(regex);
        list.add(flags);
        list.add(model);
        for (int i = 0; i < bean.getAttackBeanList().size(); i++) {
            AttackBean attackBean = bean.getAttackBeanList().get(i);
            list.add(attackBean.getType().name());
            list.add(attackBean.getAttackStringFormatSp());
        }
        String name = System.currentTimeMillis() + "js_attack.txt";
        try {
            FileUtils.writeLines(new File("js/" + name), list);

            Process proc;
            String[] args = new String[]{JS, "js/attack.js", "js/" + name};
            proc = Runtime.getRuntime().exec(args);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
            ArrayList<String> results = (ArrayList<String>) FileUtils.readLines(new File("js/" + name.replace(".txt", "_result.txt")), "utf-8");
            for (int i = 0; i < bean.getAttackBeanList().size(); i++) {
                String[] s = results.get(i).toLowerCase().split("IOS_AC_CN".toLowerCase());
                bean.getAttackBeanList().get(i).setAttackSuccess(Boolean.parseBoolean(s[0]));
                if (Boolean.parseBoolean(s[0])) {
                    bean.setReDoS(true);
                }
                bean.getAttackBeanList().get(i).setRepeatTimes(Integer.parseInt(s[1]));
                bean.getAttackBeanList().get(i).setAttackTime(Integer.parseInt(s[2]));
                bean.getAttackBeanList().get(i).confirmType();

            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.deleteQuietly(new File("js/" + name));
            FileUtils.deleteQuietly(new File("js/" + name.replace(".txt", "_result.txt")));
        }
        return bean;
    }

    /**
     * 使用perl语言验证
     *
     * @param model
     * @return
     */
    public static ReDoSBean getPerl(ReDoSBean bean, String model) {
        System.out.println("waring:Your environment must support the command \"perl\"");
        String name = "perl/" + System.currentTimeMillis() + ".perl.cache.json";
        String input = name;
        String output = name.replace(".json", ".result.json");
        ArrayList<Output> outputs = new ArrayList<>();
        ArrayList<Attack> attackArrayList = new ArrayList<>();
        for (AttackBean item : bean.getAttackBeanList()) {
            Attack attack = new Attack(item.getPrefix(), item.getInfix(), item.getSuffix(), item.getType(), item.getPatternType());
            attackArrayList.add(attack);
        }
        outputs.add(new Output(bean.getRegexID(), bean.getRegex(), attackArrayList));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(outputs);
        try {
            FileUtils.write(new File(input), json, "utf-8");
            Process proc;
            String[] args = new String[]{PYTHON, "perl/main.py", input, output, model};
            proc = Runtime.getRuntime().exec(args);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();

            ArrayList<Output> dataBeanArrayList = new Gson().fromJson(FileUtils.readFileToString(new File(output), "utf-8"), new TypeToken<ArrayList<Output>>() {
            }.getType());

            Output resultBean = dataBeanArrayList.get(0);
            for (int i = 0; i < bean.getAttackBeanList().size(); i++) {
                Attack attack = resultBean.attackArrayList.get(i);
                String redos = attack.redos.toLowerCase();
                if (redos.equals("true")) {
                    bean.setReDoS(true);
                    bean.getAttackBeanList().get(i).setPatternType(attack.patternType);
                    bean.getAttackBeanList().get(i).setAttackSuccess(true);
                    if (attack.type == AttackType.EXPONENT) {
                        bean.getAttackBeanList().get(i).setRepeatTimes(1000);
                    } else if (attack.type == AttackType.POLYNOMIAL) {
                        bean.getAttackBeanList().get(i).setRepeatTimes(100000);
                    }
                    bean.getAttackBeanList().get(i).confirmType();
                    bean.getAttackBeanList().get(i).setAttackTime(1000);
                } else {
                    bean.getAttackBeanList().get(i).setAttackSuccess(false);
                }
                bean.getAttackBeanList().get(i).msg = attack.redos;
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.deleteQuietly(new File(input));
            FileUtils.deleteQuietly(new File(output));
        }
        return bean;
    }

    /**
     * 使用php语言验证
     *
     * @param model
     * @return
     */
    public static ReDoSBean getPHP(ReDoSBean bean, String model) {
        System.out.println("waring:Your environment must support the command \"php\"");
        String name = "php/" + System.currentTimeMillis() + ".php.cache.json";
        String input = name;
        String output = name.replace(".json", ".result.json");
        ArrayList<Output> outputs = new ArrayList<>();
        ArrayList<Attack> attackArrayList = new ArrayList<>();
        for (AttackBean item : bean.getAttackBeanList()) {
            Attack attack = new Attack(item.getPrefix(), item.getInfix(), item.getSuffix(), item.getType(), item.getPatternType());
            attackArrayList.add(attack);
        }
        outputs.add(new Output(bean.getRegexID(), bean.getRegex(), attackArrayList));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(outputs);
        try {
            FileUtils.write(new File(input), json, "utf-8");
            Process proc;
            String[] args = new String[]{PYTHON, "php/main.py", input, output, model};
            proc = Runtime.getRuntime().exec(args);// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();

            ArrayList<Output> dataBeanArrayList = new Gson().fromJson(FileUtils.readFileToString(new File(output), "utf-8"), new TypeToken<ArrayList<Output>>() {
            }.getType());

            Output resultBean = dataBeanArrayList.get(0);
            for (int i = 0; i < bean.getAttackBeanList().size(); i++) {
                Attack attack = resultBean.attackArrayList.get(i);
                String redos = attack.redos.toLowerCase();
                if (redos.equals("true")) {
                    bean.setReDoS(true);
                    bean.getAttackBeanList().get(i).setPatternType(attack.patternType);
                    bean.getAttackBeanList().get(i).setAttackSuccess(true);
                    if (attack.type == AttackType.EXPONENT) {
                        bean.getAttackBeanList().get(i).setRepeatTimes(1000);
                    } else if (attack.type == AttackType.POLYNOMIAL) {
                        bean.getAttackBeanList().get(i).setRepeatTimes(100000);
                    }
                    bean.getAttackBeanList().get(i).confirmType();
                    bean.getAttackBeanList().get(i).setAttackTime(1000);
                } else {
                    bean.getAttackBeanList().get(i).setAttackSuccess(false);
                }
                bean.getAttackBeanList().get(i).msg = attack.redos;
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.deleteQuietly(new File(input));
            FileUtils.deleteQuietly(new File(output));
        }
        return bean;
    }

    /**
     * 筛选肯定不是redos的正则
     *
     * @param regex
     * @return
     */
    public static boolean mustBeNoReDoS(String regex) {
        Matcher matcher = Pattern.compile(EXTENDED_COUNTING).matcher(regex);
        return !matcher.find();
    }
}
