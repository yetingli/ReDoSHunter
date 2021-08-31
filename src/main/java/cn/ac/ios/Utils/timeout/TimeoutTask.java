package cn.ac.ios.Utils.timeout;

import cn.ac.ios.Bean.AttackBean;
import cn.ac.ios.Bean.Pair;

import java.util.concurrent.Callable;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.ac.ios.Bean.AttackBean.*;

/**
 * @author pqc
 * 计时任务，仅用于redos验证
 */
public class TimeoutTask implements Callable<Pair<Boolean, Integer>> {

    public String regex;
    public String str;
    private Matcher matcher;
    private Matcher findMatcher;
    public AttackBean attackBean;
    private int funType;


    public TimeoutTask(AttackBean attackBean, String regex) {
        this.regex = regex;
        this.str = attackBean.getAttackString();
        this.attackBean = attackBean;
        this.funType = attackBean.getAttackCallType();
    }

    @Override
    public Pair<Boolean, Integer> call() {
        try {
            long time = System.currentTimeMillis();
            this.matcher = Pattern.compile(regex).matcher(str);
            this.findMatcher = Pattern.compile(regex).matcher(str);
            if (funType == 0) {
                boolean find = findMatcher.find();
                boolean match = matcher.matches();
            } else if (funType == 1) {
                boolean match = matcher.matches();
            } else if (funType == 2) {
                boolean find = findMatcher.find();
            }
            long lastCurr = attackBean.getAttackTime();
            long curr = System.currentTimeMillis() - time;
            attackBean.setAttackTime(curr);
            if (curr >= TIME_OUT) {
                return new Pair<>(true, (int) curr);
            } else {
//                if (lastCurr != 0 && curr / lastCurr >= 2) {
//                    return new Pair<>(true, (int) curr);
//                }
                if (attackBean.isTerminal()) {
                    return new Pair<>(false, ATTACK_TOO_MANY);
                } else {
                    return new Pair<>(false, REPEAT_INCREASE);
                }
            }
        } catch (Exception e) {
            return new Pair<>(false, REGEX_ERROR);
        }
    }

    /**
     * 超时后结束正则匹配
     * todo 并不是很理想
     */
    public void close() {
        try {
            if (matcher != null) {
                matcher.reset("a");
                matcher.matches();
            }
            if (findMatcher != null) {
                findMatcher.reset("a");
                findMatcher.matches();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getGlobal().info("macher null");
        }
    }
}