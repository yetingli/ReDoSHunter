package cn.ac.ios.Bean;

/**
 * @author pqc
 */
public class RegexBean {

    private String regex;
    private String flags;

    public RegexBean(String regex, String flags) {
        this.regex = regex;
        this.flags = flags;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getFlags() {
        if (flags.contains("s") && flags.contains("i")) {
            return "is";
        }
        if (flags.contains("s")) {
            return "s";
        }
        if (flags.contains("i")) {
            return "i";
        }
        return "";
    }

    public String getAllFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }
}
