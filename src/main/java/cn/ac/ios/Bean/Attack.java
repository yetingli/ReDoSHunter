package cn.ac.ios.Bean;

public class Attack {

    public String prefix;
    public String infix;
    public String suffix;
    public AttackType type;
    public PatternType patternType;
    public String reDos = "";

    public Attack(String prefix, String infix, String suffix, AttackType type, PatternType patternType) {
        this.prefix = prefix;
        this.infix = infix;
        this.suffix = suffix;
        this.type = type;
        this.patternType = patternType;
    }
}
