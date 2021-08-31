package cn.ac.ios;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author pqc
 */
public class TestUtils {

    public static void main(String[] args) throws IOException {

        Set<Integer> set = new HashSet<>();
        set.addAll(getIds("data/expr/snort_only_redos_true_s_java_10000_0_2021_08_12_13_16_49.txt"));
        set.addAll(getIds("data/expr/snort_only_redos_true_s_java_01000_0_2021_08_06_00_24_00.txt"));
        set.addAll(getIds("data/expr/snort_only_redos_true_s_java_00100_0_2021_08_06_00_34_10.txt"));
        set.addAll(getIds("data/expr/snort_only_redos_true_s_java_00010_0_2021_08_06_04_22_07.txt"));
        set.addAll(getIds("data/expr/snort_only_redos_true_s_java_00001_0_2021_08_06_08_40_05.txt"));
        set.removeAll(getIds("data/expr/snort_only_redos_true_s_java_11111_0_2021_08_26_18_34_55.txt"));
        System.out.println(set.size());
        System.out.println(set);

//        ArrayList<Integer> list = new ArrayList<>(set);
//        ArrayList<Integer> old =  getIds("data/expr/snort_only_redos_true_s_java_11111_0_2021_07_27_20_43_28.txt");
//        System.out.println(old.size());
//        old.removeAll(list);
//        System.out.println(old.size());

//        System.out.println(getMiss("data/expr/regexlib_only_redos_true_s_java_11111_0_2021_08_25_11_21_27.txt",
//                "data/expr/regexlib_only_redos_true_s_java_11111_0_2021_08_24_21_14_55.txt"));

    }

    /**
     * 获取正则id
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static ArrayList<Integer> getIds(String fileName) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        List<String> lines = FileUtils.readLines(new File(fileName), "utf-8");
        for (String line : lines) {
            if (line.startsWith("id:")) {
                list.add(Integer.parseInt(line.replace("id:", "")));
            }
        }
        return list;
    }

    /**
     * 获取新文件miss的id
     *
     * @param newFile
     * @param oldFile
     * @return
     * @throws IOException
     */
    public static ArrayList<Integer> getMiss(String newFile, String oldFile) throws IOException {
        ArrayList<Integer> newIds = getIds(newFile);
        ArrayList<Integer> oldIds = getIds(oldFile);
        oldIds.removeAll(newIds);
        oldIds.sort(Comparator.naturalOrder());
        return oldIds;
    }

    /**
     * 获取新文件新增的id
     *
     * @param newFile
     * @param oldFile
     * @return
     * @throws IOException
     */
    public static ArrayList<Integer> getIncrease(String newFile, String oldFile) throws IOException {
        ArrayList<Integer> newIds = getIds(newFile);
        ArrayList<Integer> oldIds = getIds(oldFile);
        newIds.removeAll(oldIds);
        newIds.sort(Comparator.naturalOrder());
        return newIds;
    }
}
