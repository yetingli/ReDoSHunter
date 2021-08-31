package cn.ac.ios.Utils.json.bean;

import java.util.LinkedList;

/**
 * 每一个文件输出对象
 */
public class FileObj {
    private String filename;
    private LinkedList<regexps> regexps;

    public FileObj(LinkedList<regexps> regexps, String filename) {
        this.filename = filename;
        this.regexps = regexps;
    }

    public LinkedList<regexps> getRegexObj() {
        return regexps;
    }

    public void setRegexObj(LinkedList<regexps> regexps) {
        this.regexps = regexps;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
