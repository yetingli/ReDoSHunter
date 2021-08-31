package cn.ac.ios.Utils.json.bean;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("data/json/out.js.json");
        int len;
        byte[] bytes = new byte[1024];
        StringBuilder stringBuffer = new StringBuilder();
        while ((len = fileInputStream.read(bytes)) != -1) {
            stringBuffer.append(new String(bytes, 0, len));
        }
        fileInputStream.close();
        JSONArray jsonArray = JSONObject.parseArray(stringBuffer.toString());
        ArrayList<String> regexs = new ArrayList<>();
        ArrayList<String> filenames = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String filename = jsonObject.getString("filename");
            JSONArray array = jsonObject.getJSONArray("regexps");
            for (int j = 0; j < array.size(); j++) {
                JSONObject object = array.getJSONObject(j);
                String pattern = object.getString("pattern");
                if("DYNAMIC-PATTERN".equals(pattern)){
                    continue;
                }
                regexs.add(pattern);
                filenames.add(filename);
            }
        }
        FileUtils.writeLines(new File("data/json/out_regex.js.txt"),regexs);
        FileUtils.writeLines(new File("data/json/out_filename.js.txt"),filenames);
    }
}
