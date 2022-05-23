package cn.ac.ios.Bean;

/**
 * @author pqc
 * 这里参数用于其他语言验证的进程通信，不要改动名字和大小写。
 *
 */
public class Attack {

    public String prefix;
    public String infix;
    public String suffix;
    public AttackType type;
    public PatternType patternType;
    public String redos = "";

    public Attack(String prefix, String infix, String suffix, AttackType type, PatternType patternType) {
        this.prefix = prefix;
        this.infix = infix;
        this.suffix = suffix;
        this.type = type;
        this.patternType = patternType;
    }
}
