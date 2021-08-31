package cn.ac.ios.Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import java.io.OutputStreamWriter;
import java.util.*;

public class Utils {

    // 按行读取文件
    public static List<String> readFile(String filePath) {
        try {
            return FileUtils.readLines(new File(filePath), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 写文件
    public static void writeFile(String filePath, List<String> lineContent) {
        try {
            FileUtils.writeLines(new File(filePath), lineContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 追加写入文件
    public static void appendWriteFileLine(String saveFile, String contentLine) {
        File file = new File(saveFile);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (!file.exists()) {
                boolean hasFile = file.createNewFile();
                if (hasFile) {
                    System.out.println("file not exists, create new file");
                }
                fos = new FileOutputStream(file);
            } else {
                System.out.println("file exists");
                fos = new FileOutputStream(file, true);
            }

            osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(contentLine); //写入内容
            osw.write("\r\n");  //换行
        } catch (Exception e) {
            e.printStackTrace();
        } finally {   //关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static Set<String> getIntersection(Set<String> left, Set<String> right) {
        if (left.isEmpty()) {
            return right;
        } else if (right.isEmpty()) {
            return left;
        } else {
            Set<String> set = new HashSet<>(left);
            set.retainAll(right);
            return set;
        }
    }

}
