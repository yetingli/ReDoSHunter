package cn.ac.ios.Utils.json.bean;

/**
 * regex 对象
 */
public class regexps {
    private String pattern;
    private int line;
    private String flags;

    public regexps(int line, String pattern) {

        this.pattern = pattern;
        this.line = line;
        this.flags = "";
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
