package cn.ac.ios.Bean;

import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Utils.TrimUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.ac.ios.TreeNode.Utils.createReDoSTree;
import static cn.ac.ios.Utils.RegexUtils.removeBlankStr;

/**
 * @author pqc
 */
public class AttackBean implements Serializable {

    public static final int STACK_ERROR = -2;
    public static final int REGEX_ERROR = -3;
    public static final int REPEAT_INCREASE = -4;
    public static final int ATTACK_TOO_MANY = -5;
    public static final int INTERRUPTED = -6;
    public static final int TIME_OUT = 1000;

    private static final int POLYNOMIAL_MAX_REPEAT_TIMES = 80001;
    private static final int EXPONENT_MAX_REPEAT_TIMES = 1025;

    private static final int DEF_EXPONENT_TIMES = 2;
    private static final int DEF_EXPONENT_OR_POLYNOMIAL_TIMES = 2;
    private static final int DEF_POLYNOMIAL_TIMES = 5000;

    private static final int DEF_STACK_ERROR_TIMES = 10;

    private static final int INCREASE_STACK_ERROR_TIMES = 500;
    private static final int INCREASE_EXPONENT_TIMES = 100;

    private static final int MAX_CIRCULATION_STACK_ERROR_TIMES = 500;

    public static class conflictPointHelper {
        // 攻击用的正则树
        private TreeNode ReDoSTree;

        // 最外部的counting的chainIndex
        private String outerConflictPoint;

        // 内部冲突点的chainIndex
        private List<String> innerConflictPointList = new LinkedList<>();

        // counting内部冲突点的拼接式正则 按从左往右的顺序 若Boolean = true, 则定位(标记) 若Boolean = false, 则不定位(标记)
        private List<Pair<String, Boolean>> SplicedInnerConflictPointList = new LinkedList<>();

        public void setReDoSTree(TreeNode reDoSTree) {
            this.ReDoSTree = reDoSTree;
        }

        public String getOuterConflictPoint() {
            return outerConflictPoint;
        }

        public void setOuterConflictPoint(String outerConflictPoint) {
            this.outerConflictPoint = outerConflictPoint;
        }

        public List<String> getInnerConflictPointList() {
            return innerConflictPointList;
        }

        public void setInnerConflictPointList(List<String> innerConflictPointList) {
            this.innerConflictPointList = innerConflictPointList.stream().distinct().collect(Collectors.toList());
        }

        public List<Pair<String, Boolean>> getSplicedInnerConflictPointList() {
            return SplicedInnerConflictPointList;
        }

        public void setSplicedInnerConflictPointList(List<Pair<String, Boolean>> splicedInnerConflictPointList) {
            SplicedInnerConflictPointList = splicedInnerConflictPointList;
        }
    }

    // 第一个参数String是最大counting的chainIndex
    // 第二个参数是conflictPointHelper类 根据不同的patternType选择conflictPointHelper内的信息
    private conflictPointHelper conflictPoint;
    private Pair<int[], int[]> conflictIndex;
    private String locateVulnerabilityRegex = "Unable to locate Vulnerability position";  // 定位有风险的正则表达式位置
    private String vulnerabilityRegexSource = "Unable to find Vulnerability source";  // 有风险的原因
    private String prefix;
    private String suffix;
    private String infix;
    private boolean attackSuccess;
    private long attackTime = -1;
    private int repeatTimes;
    private int circulationTimes;//栈溢出时防止死循环
    public AttackType type;
    private PatternType patternType;
    public String msg;


    public AttackBean() {
        attackSuccess = false;
        repeatTimes = DEF_POLYNOMIAL_TIMES;
        circulationTimes = 0;
        type = AttackType.POLYNOMIAL;
    }

    public void increase() {
        if (type == AttackType.EXPONENT) {
            repeatTimes *= 2;
        } else if (type == AttackType.POLYNOMIAL) {
            repeatTimes *= 2;
        } else if (type == AttackType.EXPONENT_OR_POLYNOMIAL) {
            if (repeatTimes <= EXPONENT_MAX_REPEAT_TIMES) {
                repeatTimes *= 2;
                if (repeatTimes > EXPONENT_MAX_REPEAT_TIMES) {
                    repeatTimes = DEF_POLYNOMIAL_TIMES;
                }
            } else {
                repeatTimes = repeatTimes * 2;
            }
        } else if (type == AttackType.STACK_ERROR) {
            repeatTimes += INCREASE_STACK_ERROR_TIMES;
            circulationTimes++;
        }
    }

    public boolean isTerminal() {
        if (type == AttackType.EXPONENT) {
            return repeatTimes > EXPONENT_MAX_REPEAT_TIMES;
        } else if (type == AttackType.POLYNOMIAL) {
            return repeatTimes > POLYNOMIAL_MAX_REPEAT_TIMES;
        } else if (type == AttackType.EXPONENT_OR_POLYNOMIAL) {
            return repeatTimes > POLYNOMIAL_MAX_REPEAT_TIMES;
        } else if (type == AttackType.STACK_ERROR) {
            return circulationTimes > MAX_CIRCULATION_STACK_ERROR_TIMES;
        }
        return true;
    }

    public void setConflictPoint(conflictPointHelper conflictPoint) {
        this.conflictPoint = conflictPoint;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix.replace("\\f", "\f")
                .replace("\\n", "\n")
                .replace("\\t", "\t")
                .replace("\\r", "\r")
                .replace("\\v", "\u000b")
                .replace("\\u000b", "\u000b");
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix.replace("\\f", "\f")
                .replace("\\n", "\n")
                .replace("\\t", "\t")
                .replace("\\r", "\r")
                .replace("\\v", "\u000b")
                .replace("\\u000b", "\u000b");;
    }

    public String getInfix() {
        return infix;
    }

    public void setInfix(String infix) {
        this.infix = infix.replace("\\f", "\f")
                .replace("\\n", "\n")
                .replace("\\t", "\t")
                .replace("\\r", "\r")
                .replace("\\v", "\u000b")
                .replace("\\u000b", "\u000b");;
    }

    public void setAttackString(List<String> attack) {
        prefix = attack.get(0);
        infix = attack.get(1);
        suffix = attack.get(2);
    }

    public boolean isAttackSuccess() {
        return attackSuccess;
    }

    public void setAttackSuccess(boolean attackSuccess) {
        this.attackSuccess = attackSuccess;
    }

    public long getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(long attackTime) {
        this.attackTime = attackTime;
    }

    public int getRepeatTimes() {
        return repeatTimes;
    }

    public void setRepeatTimes(int times) {
        this.repeatTimes = times;
    }

    public String getAttackString() {
        StringBuilder stringBuilder = new StringBuilder(prefix);
        for (int i = 0; i < repeatTimes; i++) {
            stringBuilder.append(infix);
        }
        stringBuilder.append(suffix);
        return stringBuilder.toString();
    }

    public String getAttackStringFormatType() {
        String formatPrefix = getPrefix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\u000b", "\\u000b");
        String formatRepeat = getInfix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\u000b", "\\u000b");;
        String formatSuffix = getSuffix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\u000b", "\\u000b");;

        return type.name() + "\tAttackString:" + "\"" + formatPrefix + "\"+\"" + formatRepeat + "\"*" + repeatTimes + "+\"" + formatSuffix + "\"";
    }

    public String getAttackStringFormat() {
        String formatPrefix = getPrefix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\u000b", "\\u000b")
                .replace("\u0000", "\\u0000");
        String formatRepeat = getInfix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\u000b", "\\u000b")
                .replace("\u0000", "\\u0000");
        String formatSuffix = getSuffix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\u000b", "\\u000b")
                .replace("\u0000", "\\u0000");

        return "\"" + formatPrefix + "\"+\"" + formatRepeat + "\"*" + repeatTimes + "+\"" + formatSuffix + "\"";
    }

    public String getAttackStringFormatSp() {
        String formatPrefix = getPrefix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\u000b", "\\u000b");
        String formatRepeat = getInfix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\u000b", "\\u000b");
        String formatSuffix = getSuffix().replace("\\", "\\\\")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
                .replace("\r", "\\r")
                .replace("\u000b", "\\u000b");

        return formatPrefix + "IOS_AC_CN" + formatRepeat + "IOS_AC_CN" + formatSuffix;
    }

    public AttackType getType() {
        return type;
    }

    public void initType(AttackType type) {
        this.type = type;
        if (type == AttackType.EXPONENT) {
            repeatTimes = DEF_EXPONENT_TIMES;
        } else if (type == AttackType.POLYNOMIAL) {
            repeatTimes = DEF_POLYNOMIAL_TIMES;
        } else if (type == AttackType.EXPONENT_OR_POLYNOMIAL) {
            repeatTimes = DEF_EXPONENT_OR_POLYNOMIAL_TIMES;
        } else if (type == AttackType.STACK_ERROR) {
            repeatTimes = DEF_STACK_ERROR_TIMES;
        }
    }


    public boolean isRateIncrease(int time, long lastTime) {
        System.out.println(time);
        System.out.println(lastTime);

        return (time / lastTime) >= 2;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AttackBean bean = (AttackBean) o;

        if (!TrimUtils.rtrim(prefix, infix).equals(TrimUtils.rtrim(bean.prefix, bean.infix))) {
            return false;
        }
        if (!suffix.equals(bean.suffix)) {
            return false;
        }
        return infix.equals(bean.infix);
    }

    @Override
    public int hashCode() {
        int result = TrimUtils.rtrim(prefix, infix).hashCode();
        result = 31 * result + suffix.hashCode();
        result = 31 * result + infix.hashCode();
        return result;
    }

    public AttackType confirmType() {
        if (repeatTimes < EXPONENT_MAX_REPEAT_TIMES) {
            type = AttackType.EXPONENT;
        } else {
            type = AttackType.POLYNOMIAL;
        }
        return type;
    }

    /**
     * 二次验证，防止误判
     */
    public void secondaryValidation() {
        repeatTimes *= 2;
    }

    public void reset() {
        repeatTimes /= 2;
    }

    public PatternType getPatternType() {
        return patternType;
    }

    public void setPatternType(PatternType patternType) {
        this.patternType = patternType;
    }

    public Pair<int[], int[]> getConflictIndex() {
        return conflictIndex;
    }

    public int getAttackCallType() {
        if (patternType != null && patternType.toString().contains("SLQ")) {
            return 2;
        }
        return 0;
        // 这里保持为0最好 在regexlib数据集中id 7404
        // ((?<=,\s\")([^\"]*|([^\"]*\"\"[^\"\"]*\"\"[^\"]*)+)(?=\"\s*,))|((?<=,)[^,\"]*(?=,))
        // 前缀,\t"""""   中缀\u0000"""" 后缀空
        // 使用match测不出来 改用find是成功的
    }

    public String getLocateVulnerabilityRegex() {
        return locateVulnerabilityRegex;
    }

    public String getVulnerabilityRegexSource() {
        return vulnerabilityRegexSource;
    }

    // 攻击成功后 定位位置 并 注明原因
    public void locateVenture() {
        if (this.conflictPoint == null) {
            return;
        }

        String outerConflictPoint = "";
        List<String> innerConflictPointList = null;
        TreeNode outerConflictChild = null;
        TreeNode innerConflictChild = null;
        List<String> innerConflictStringList = null;
        String beta1 = "";
        String beta2 = "";
        List<TreeNode> allLeafNodes = null;
        StringBuilder locateVulnerabilityRegexStringBuilder = new StringBuilder();
        StringBuilder locateVulnerabilityRegexStringBuilder1 = new StringBuilder();
        StringBuilder locateVulnerabilityRegexStringBuilder2 = new StringBuilder();
        StringBuilder locateVulnerabilityRegexStringBuilder3 = new StringBuilder();
        StringBuilder locateVulnerabilityRegexStringBuilder4 = new StringBuilder();
        StringBuilder locateVulnerabilityRegexStringBuilder5 = new StringBuilder();
        boolean flag1;
        boolean flag2;
        boolean flag3;
        StringBuilder innerConflictStringBuilder = null;
        switch (patternType) {
            case NQ:
                if (this.conflictPoint.ReDoSTree == null) break;

                outerConflictPoint = this.conflictPoint.getOuterConflictPoint();    // 最外层的counting结点
                innerConflictPointList = this.conflictPoint.getInnerConflictPointList();  // 获取内部的counting结点
                outerConflictChild = this.conflictPoint.ReDoSTree.getChildByChainIndexString(outerConflictPoint);
                innerConflictChild = this.conflictPoint.ReDoSTree.getChildByChainIndexString(innerConflictPointList.get(0));

                allLeafNodes = this.conflictPoint.ReDoSTree.getLeafNodes();

                // sb1 (sb2 sb3* sb4)* sb5  ->  sb1 ►(sb2 ▻sb3*◅ sb4)*◄ sb5
                locateVulnerabilityRegexStringBuilder1 = new StringBuilder();
                locateVulnerabilityRegexStringBuilder2 = new StringBuilder();
                locateVulnerabilityRegexStringBuilder3 = new StringBuilder();
                locateVulnerabilityRegexStringBuilder4 = new StringBuilder();
                locateVulnerabilityRegexStringBuilder5 = new StringBuilder();
                flag1 = true;
                flag2 = true;
                for (int i = 0; i < allLeafNodes.size(); i++) {
                    if (! outerConflictChild.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) &&
                            outerConflictChild != allLeafNodes.get(i)) {
                        if (flag1) {
                            locateVulnerabilityRegexStringBuilder1.append(allLeafNodes.get(i).getData());
                        } else {
                            locateVulnerabilityRegexStringBuilder5.append(allLeafNodes.get(i).getData());
                        }
                    } else if (outerConflictChild.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) &&
                            ! innerConflictChild.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) &&
                            innerConflictChild != allLeafNodes.get(i)) {
                        if (flag2) {
                            locateVulnerabilityRegexStringBuilder2.append(allLeafNodes.get(i).getData());
                            flag1 = false;
                        } else {
                            locateVulnerabilityRegexStringBuilder4.append(allLeafNodes.get(i).getData());
                        }
                    } else if (innerConflictChild.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) ||
                        innerConflictChild == allLeafNodes.get(i)) {
                        locateVulnerabilityRegexStringBuilder3.append(allLeafNodes.get(i).getData());
                        flag2 = false;
                    }
                }
                this.locateVulnerabilityRegex = locateVulnerabilityRegexStringBuilder1.toString() +
                        "►" + locateVulnerabilityRegexStringBuilder2.toString() +
                        "▻" + locateVulnerabilityRegexStringBuilder3.toString() + "◅" +
                        locateVulnerabilityRegexStringBuilder4.toString() + "◄" +
                        locateVulnerabilityRegexStringBuilder5.toString();

                this.vulnerabilityRegexSource = "There is a nested quantifier node " + innerConflictChild.getData() + " in " + outerConflictChild.getData() + ".";
                break;

//                case EOA_i_or_ii:
            case EOA_i1:
            case EOA_i2:
            case EOA_i3:
            case EOA_ii1:
            case EOA_ii2:
            case EOA_ii3:
                innerConflictStringBuilder = new StringBuilder();
                innerConflictStringList = new ArrayList<>();
                for (Pair<String, Boolean> pair: this.conflictPoint.getSplicedInnerConflictPointList()) {
                    innerConflictStringList.add(pair.getKey());
                }

                if (this.conflictPoint.ReDoSTree == null) break;

                outerConflictPoint = this.conflictPoint.getOuterConflictPoint();    // 最外层的counting结点
                outerConflictChild = this.conflictPoint.ReDoSTree.getChildByChainIndexString(outerConflictPoint);

                innerConflictPointList = this.conflictPoint.getInnerConflictPointList();  // 获取内部的counting结点
                List<TreeNode> innerConflictChildList = new LinkedList<>();
                for (String innerConflictPoint: innerConflictPointList) {
                    innerConflictChildList.add(this.conflictPoint.ReDoSTree.getChildByChainIndexString(innerConflictPoint));
                }

                allLeafNodes = this.conflictPoint.ReDoSTree.getLeafNodes();

                // sb1 (sb2*){m,n} sb3 -> sb1 ►(▻sb2*◅){m,n}◄ sb3
                locateVulnerabilityRegexStringBuilder1 = new StringBuilder();
                locateVulnerabilityRegexStringBuilder2 = new StringBuilder();
                locateVulnerabilityRegexStringBuilder3 = new StringBuilder();
                flag1 = true;
                for (TreeNode leafNode: allLeafNodes) {
                    if (! outerConflictChild.isNowNodeChildOrGrandchild(leafNode) && outerConflictChild != leafNode) {
                        if (flag1) {
                            locateVulnerabilityRegexStringBuilder1.append(leafNode.getData());
                        } else {
                            locateVulnerabilityRegexStringBuilder3.append(leafNode.getData());
                        }
                    } else {
                        flag1 = false;
                        flag2 = false;
                        for (TreeNode child: innerConflictChildList) {
                            if (child.isNowNodeChildOrGrandchild(leafNode) || child == leafNode) {
                                flag2 = true;
                                break;
                            }
                        }
                        if (flag2) {
                            locateVulnerabilityRegexStringBuilder2.append("▻" + leafNode.getData() + "◅");
                        } else {
                            locateVulnerabilityRegexStringBuilder2.append(leafNode.getData());
                        }
                    }
                }

                this.locateVulnerabilityRegex = locateVulnerabilityRegexStringBuilder1.toString() +
                        "►" + locateVulnerabilityRegexStringBuilder2.toString().replace("◅▻", "") + "◄" +
                        locateVulnerabilityRegexStringBuilder3.toString();

                // sb1 sb2* sb3* sb4  ->  sb1 ▻sb2*◅ ▻sb3*◅ sb4
//                StringBuilder locateVulnerabilityRegexStringBuilder = new StringBuilder();
//                for (TreeNode leafNode: allLeafNodes) {
//                    boolean flag = false;
//                    for (TreeNode child: innerConflictChildList) {
//                        if (child.isNowNodeChildOrGrandchild(leafNode) || child == leafNode) {
//                            flag = true;
//                            break;
//                        }
//                    }
//                    if (flag) {
//                        locateVulnerabilityRegexStringBuilder.append("▻" + leafNode.getData() + "◅");
//                    } else {
//                        locateVulnerabilityRegexStringBuilder.append(leafNode.getData());
//                    }
//                }
//
//                this.locateVulnerabilityRegex = locateVulnerabilityRegexStringBuilder.toString().replace("◅▻", "");
//                beta1 = innerConflictStringList.get(0);
//                beta2 = innerConflictStringList.get(1);
//                this.vulnerabilityRegexSource = "β1 = " + beta1 + " β2 = " + beta2 + " (β1.followLast) ∩ (β2.first) ≠ ∅";
//
//
//                if (this.conflictPoint.ReDoSTree == null) break;
//
//                outerConflictPoint = this.conflictPoint.getOuterConflictPoint();    // 最外层的counting结点
//                outerConflictChild = this.conflictPoint.ReDoSTree.getChildByChainIndexString(outerConflictPoint);
//
//                // sb1 innerConflictStringBuilder sb2 -> sb1 ►(innerConflictStringBuilder){m,n}◄ sb2
//                innerConflictStringBuilder = new StringBuilder();
//                innerConflictStringList = new ArrayList<>();
//                for (Pair<String, Boolean> pair: this.conflictPoint.getSplicedInnerConflictPointList()) {
//                    if (pair.getValue()) {
//                        innerConflictStringBuilder.append("▻" + pair.getKey() + "◅");
//                        innerConflictStringList.add(pair.getKey());
//                    } else {
//                        innerConflictStringBuilder.append(pair.getKey());
//                    }
//                }
//
//                allLeafNodes = this.conflictPoint.ReDoSTree.getLeafNodes();
//                locateVulnerabilityRegexStringBuilder1 = new StringBuilder();
//                locateVulnerabilityRegexStringBuilder2 = new StringBuilder();
//                flag1 = true;
//                for (int i = 0; i < allLeafNodes.size(); i++) {
//                    if (! outerConflictChild.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) && outerConflictChild != allLeafNodes.get(i)) {
//                        if (flag1) {
//                            locateVulnerabilityRegexStringBuilder1.append(allLeafNodes.get(i).getData());
//                        } else {
//                            locateVulnerabilityRegexStringBuilder2.append(allLeafNodes.get(i).getData());
//                        }
//                    } else {
//                        flag1 = false;
//                    }
//                }
//
//                this.locateVulnerabilityRegex = locateVulnerabilityRegexStringBuilder1.toString() +
//                        "►" + "(" + innerConflictStringBuilder.toString() + ")" + outerConflictChild.getChild(1).getData() + "◄" +
//                        locateVulnerabilityRegexStringBuilder2.toString();

                beta1 = innerConflictStringList.get(0);
                beta2 = innerConflictStringList.get(1);
                if (patternType == PatternType.EOA_i1 || patternType == PatternType.EOA_i2 || patternType == PatternType.EOA_i3) {
                    this.vulnerabilityRegexSource = "β1 = " + beta1 + " β2 = " + beta2 + " (β1.followLast ∪ β1.last) ∩ (β2.first) ≠ ∅";
                } else if (patternType == PatternType.EOA_ii1 || patternType == PatternType.EOA_ii2 || patternType == PatternType.EOA_ii3) {
                    this.vulnerabilityRegexSource = "β1 = " + beta1 + " β2 = " + beta2 + " (β1.first) ∩ (β2.followLast ∪ β2.last) ≠ ∅";
                }
                break;

            case EOD_i:
            case EOD_ii1:
            case EOD_ii2:
                if (this.conflictPoint.ReDoSTree == null) break;

                outerConflictPoint = this.conflictPoint.getOuterConflictPoint();    // 最外层的counting结点
                innerConflictPointList = this.conflictPoint.getInnerConflictPointList();  // 获取内部的counting结点
                outerConflictChild = this.conflictPoint.ReDoSTree.getChildByChainIndexString(outerConflictPoint);
                TreeNode innerConflictChild1 = this.conflictPoint.ReDoSTree.getChildByChainIndexString(innerConflictPointList.get(0));
                TreeNode innerConflictChild2 = null;
                // innerConflictPointList中可能只含有一个内冲突点
                if (innerConflictPointList.size() > 1) {
                    innerConflictChild2 = this.conflictPoint.ReDoSTree.getChildByChainIndexString(innerConflictPointList.get(1));
                } else {    // 先让它指向第一个冲突点
                    innerConflictChild2 = this.conflictPoint.ReDoSTree.getChildByChainIndexString(innerConflictPointList.get(0));
                }

                allLeafNodes = this.conflictPoint.ReDoSTree.getLeafNodes();

                // sb1 (sb2 innerConflictPointList[0]* sb3 innerConflictPointList[1]* sb4)* sb5 ->
                // sb1 ►(sb2 ▻sinnerConflictPointList[0]*◅ sb3 ▻innerConflictPointList[1]*◅ sb4)*◄ sb5
                locateVulnerabilityRegexStringBuilder1 = new StringBuilder();
                locateVulnerabilityRegexStringBuilder2 = new StringBuilder();
                locateVulnerabilityRegexStringBuilder3 = new StringBuilder();
                locateVulnerabilityRegexStringBuilder4 = new StringBuilder();
                locateVulnerabilityRegexStringBuilder5 = new StringBuilder();
                flag1 = true;
                flag2 = true;
                flag3 = true;
                for (int i = 0; i < allLeafNodes.size(); i++) {
                    if (! outerConflictChild.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) &&
                        outerConflictChild != allLeafNodes.get(i)) {
                        if (flag1) {
                            locateVulnerabilityRegexStringBuilder1.append(allLeafNodes.get(i).getData());
                        } else {
                            locateVulnerabilityRegexStringBuilder5.append(allLeafNodes.get(i).getData());
                        }
                    } else if ((outerConflictChild.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) || outerConflictChild == allLeafNodes.get(i)) &&
                            ! innerConflictChild1.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) &&
                            innerConflictChild1 != allLeafNodes.get(i) &&
                            ! innerConflictChild2.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) &&
                            innerConflictChild2 != allLeafNodes.get(i)) {
                        if (flag2) {
                            locateVulnerabilityRegexStringBuilder2.append(allLeafNodes.get(i).getData());
                            flag1 = false;
                        } else if (flag3) {
                            locateVulnerabilityRegexStringBuilder3.append(allLeafNodes.get(i).getData());
                        } else {
                            locateVulnerabilityRegexStringBuilder4.append(allLeafNodes.get(i).getData());
                        }
                    } else if (innerConflictChild1.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) || innerConflictChild1 == allLeafNodes.get(i)) {
                        flag2 = false;
                    } else if (innerConflictChild2.isNowNodeChildOrGrandchild(allLeafNodes.get(i)) || innerConflictChild2 == allLeafNodes.get(i)) {
                        flag3 = false;
                    }
                }
                if (innerConflictChild1 != innerConflictChild2) {
                    this.locateVulnerabilityRegex = locateVulnerabilityRegexStringBuilder1.toString() +
                            "►" + locateVulnerabilityRegexStringBuilder2.toString() +
                            "▻" + innerConflictChild1.getData() + "◅" +
                            locateVulnerabilityRegexStringBuilder3.toString() +
                            "▻" + innerConflictChild2.getData() + "◅" +
                            locateVulnerabilityRegexStringBuilder4.toString() + "◄" +
                            locateVulnerabilityRegexStringBuilder5.toString();
                } else {
                    this.locateVulnerabilityRegex = locateVulnerabilityRegexStringBuilder1.toString() +
                            "►" + locateVulnerabilityRegexStringBuilder2.toString() +
                            "▻" + innerConflictChild1.getData() + "◅" +
                            locateVulnerabilityRegexStringBuilder3.toString() +
                            locateVulnerabilityRegexStringBuilder4.toString() + "◄" +
                            locateVulnerabilityRegexStringBuilder5.toString();
                }

                beta1 = innerConflictChild1.getData();
                beta2 = innerConflictChild2.getData();
                if (patternType == PatternType.EOD_i) {
                    this.vulnerabilityRegexSource = "β1 = " + beta1 + " β2 = " + beta2 + " (β1.first) ∩ (β2.first) ≠ ∅";
                } else if (patternType == PatternType.EOA_ii1) {
                    this.vulnerabilityRegexSource = "β1 = " + beta1 + " β2 = " + beta2 + " (β1.first) ∩ (β2.followLast) ≠ ∅";
                } else if (patternType == PatternType.EOD_ii2) {
                    this.vulnerabilityRegexSource = "β1 = " + beta1 + " β2 = " + beta2 + " (β2.first) ∩ (β1.followLast) ≠ ∅";
                }
                break;//                case POA_i:
//                case POA_ii:
            case POA:
                innerConflictStringBuilder = new StringBuilder();
                innerConflictStringList = new ArrayList<>();
                for (Pair<String, Boolean> pair: this.conflictPoint.getSplicedInnerConflictPointList()) {
//                    if (pair.getValue()) {
//                        innerConflictStringBuilder.append("▻" + pair.getKey() + "◅");
                        innerConflictStringList.add(pair.getKey());
//                    } else {
//                        innerConflictStringBuilder.append(pair.getKey());
//                    }
                }
//                this.locateVulnerabilityRegex = innerConflictStringBuilder.toString();
//
//                beta1 = innerConflictStringList.get(0);
//                beta2 = innerConflictStringList.get(1);
//                this.vulnerabilityRegexSource = "β1 = " + beta1 + " β2 = " + beta2 + " (β1.followLast) ∩ (β2.first) ≠ ∅";
//                break;

                if (this.conflictPoint.ReDoSTree == null) break;

                innerConflictPointList = this.conflictPoint.getInnerConflictPointList();  // 获取内部的counting结点
                innerConflictChildList = new LinkedList<>();
                for (String innerConflictPoint: innerConflictPointList) {
                    innerConflictChildList.add(this.conflictPoint.ReDoSTree.getChildByChainIndexString(innerConflictPoint));
                }

                allLeafNodes = this.conflictPoint.ReDoSTree.getLeafNodes();

                // sb1 sb2* sb3* sb4  ->  sb1 ▻sb2*◅ ▻sb3*◅ sb4
                locateVulnerabilityRegexStringBuilder = new StringBuilder();
                for (TreeNode leafNode: allLeafNodes) {
                    boolean flag = false;
                    for (TreeNode child: innerConflictChildList) {
                        if (child.isNowNodeChildOrGrandchild(leafNode) || child == leafNode) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        locateVulnerabilityRegexStringBuilder.append("▻" + leafNode.getData() + "◅");
                    } else {
                        locateVulnerabilityRegexStringBuilder.append(leafNode.getData());
                    }
                }

                this.locateVulnerabilityRegex = locateVulnerabilityRegexStringBuilder.toString().replace("◅▻", "");
                beta1 = innerConflictStringList.get(0);
                beta2 = innerConflictStringList.get(1);
                this.vulnerabilityRegexSource = "β1 = " + beta1 + " β2 = " + beta2 + " (β1.followLast) ∩ (β2.first) ≠ ∅";
                break;


            case SLQ_1:
            case SLQ_2:
            case SLQ_3:
            case SLQ2:
                break;
            default:
                break;
        }
    }

//    /**
//     * 攻击成功后 定位位置
//     *
//     * @param root
//     */
//    public void locateVenture(TreeNode root) {
//        switch (patternType) {
//            case NQ:
//                break;
//            case EOA
//                break;
//            case EOD:
//                break;
//            case POA:
//                Stack<TreeNode> stack = new Stack<>();
//                stack.add(root);
//                int length = 0;
//                int count = 0;
//                int[] left = new int[2];
//                int[] right = new int[2];
//                while (!stack.isEmpty()) {
//                    TreeNode node = stack.pop();
//                    if (RegexUtils.isGeneralizedCountingNode(node)) {
//                        count++;
//                        if (count == conflictPoint.getKey()) {
//                            left[0] = length;
//                            left[1] = length + node.getData().length();
//                        }
//                        if (count == conflictPoint.getValue()) {
//                            right[0] = length;
//                            right[1] = length + node.getData().length();
//                            break;
//                        }
//                    }
//                    if (node.getChildCount() == 0) {
//                        length += node.getData().length();
//                    }
//                    for (int i = node.getChildCount() - 1; i >= 0; i--) {
//                        stack.push(node.getChild(i));
//                    }
//                }
//                conflictIndex = new Pair<>(left, right);
//                break;
//            case SLQ_1:
//                break;
//            default:
//                break;
//        }
//    }
}



