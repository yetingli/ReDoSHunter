package cn.ac.ios.Bean;

import cn.ac.ios.TreeNode.TreeNode;
import cn.ac.ios.Utils.timeout.TimeoutTask;
import cn.ac.ios.Utils.timeout.TimeoutTaskUtils;

import java.util.ArrayList;

import static cn.ac.ios.Main.ATTACK_MODEL_SINGLE;
import static cn.ac.ios.Bean.AttackBean.*;
import static cn.ac.ios.TreeNode.Utils.createReDoSTree;

/**
 * @author pqc
 */
public class ReDoSBean {

    private String regex;
    private Integer id;
    private Integer regexID;
    private boolean reDoS;
    private ArrayList<AttackBean> attackBeanList;
    // 表示已经验证过的攻击串
    private ArrayList<AttackBean> successBeanList = new ArrayList<>();
    private String message;
    private AttackType type = AttackType.POLYNOMIAL;
    private int vul = 0;
    private TreeNode root;

    public ReDoSBean() {
        this.reDoS = false;
        attackBeanList = new ArrayList<>();
    }

    public ReDoSBean(String regex, int id) {
        this.regex = regex;
        this.id = id;
        this.reDoS = false;
        this.attackBeanList = new ArrayList<>();
    }

    public ReDoSBean(String regex, int id, int regexID, String message) {
        this.regex = regex;
        this.id = id;
        this.regexID = regexID;
        this.reDoS = false;
        attackBeanList = new ArrayList<>();
        this.message = message;
    }


    public boolean isReDoS() {
//        return reDoS;
        // 只靠isReDoS不大行
        // 例如 ^(?=^.{1,254}$)(^(?:(?!\.|-)([a-z0-9\-\*]{1,63}|([a-z0-9\-]{1,62}[a-z0-9]))\.)+(?:[a-z]{2,})$)$
        // AttackString:""+"a0a."*2+" "
        // 是成功的 通过bean.getAttackBeanList().get(i).isAttackSuccess()是可以获取到成功的
        // 因此要加上这个判断
        if (reDoS) {
            return true;
        } else {
            for (AttackBean attackBean : attackBeanList) {
                if (attackBean.isAttackSuccess()) {
                    return true;
                }
            }
            return false;
        }
    }



    public void setReDoS(boolean reDoS) {
        this.reDoS = reDoS;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public ArrayList<AttackBean> getAttackBeanList() {
        return attackBeanList;
    }

    public void setAttackBeanList(ArrayList<AttackBean> attackBeanList) {
        this.attackBeanList = attackBeanList;
    }

    /**
     * @param model 攻击模式 s 表示攻击成功则退出，m 表示会攻击所有的攻击串
     */
    public void attack(String model) {
        reDoS = false;
        for (int i = 0; i < attackBeanList.size(); ) {
            //如果正在执行TimeoutTask，中断异常会被TimeoutTask捕获，此处不再捕获
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            AttackBean attackBean = attackBeanList.get(i);
            if (successBeanList.contains(attackBean)) {
                for (AttackBean bean : successBeanList) {
                    if (bean.equals(attackBean)) {
                        attackBean.setAttackSuccess(bean.isAttackSuccess());
                        attackBean.setRepeatTimes(bean.getRepeatTimes());
                        attackBean.setAttackTime(bean.getAttackTime());
                        attackBean.confirmType();
                        break;
                    }
                }
                successBeanList.add(attackBean);
            } else {
                Pair<Boolean, Integer> pair = TimeoutTaskUtils.execute(new TimeoutTask(attackBean, regex));
                Boolean timeout = pair.getKey();
                int time = pair.getValue();
                if (time == STACK_ERROR) {
                    if (attackBean.getType() != AttackType.STACK_ERROR) {
                        attackBean.initType(AttackType.STACK_ERROR);
                        attackBean.increase();
                        if (!attackBean.isTerminal()) {
                            continue;
                        }
                    }
                }
                if (time == REPEAT_INCREASE) {
                    attackBean.increase();
                    if (!attackBean.isTerminal()) {
                        continue;
                    }
                }
                attackBean.setAttackSuccess(timeout);
                attackBean.setAttackTime(time);
                if (timeout) {
                    type = attackBean.confirmType();
                    if (time >= TIME_OUT) {
                        reDoS = secondaryValidation(attackBean, regex);
                        attackBean.reset();
                    } else {
                        reDoS = true;
                    }
                    if (reDoS) {
//                        locateVenture(attackBean);
                        attackBean.locateVenture();
                        if (model.equals(ATTACK_MODEL_SINGLE)) {
                            break;
                        }
                    }
                }
                successBeanList.add(attackBean);
                //正则错误
                if (time == REGEX_ERROR || time == INTERRUPTED) {
                    break;
                }
            }
            i++;
        }
    }

//    private void locateVenture(AttackBean attackBean) {
//        if (root == null) {
//            try {
//                root = createReDoSTree(regex);
//            } catch (InterruptedException e) {
//                return;
//            }
//        }
////        attackBean.locateVenture(root);
//    }

    /**
     * 二次验证 防止误判
     *
     * @param attackBean
     * @param regex
     * @return
     */
    private boolean secondaryValidation(AttackBean attackBean, String regex) {
        attackBean.secondaryValidation();
        Pair<Boolean, Integer> pair = TimeoutTaskUtils.execute(new TimeoutTask(attackBean, regex));
        // 二次验证可能栈溢出, 直接返回true
        if (pair.getValue() == STACK_ERROR) {
            return true;
        }
        return pair.getKey();
    }

    /**
     * 对攻击串去重
     */
    public void duplicate() {
        ArrayList<AttackBean> list = new ArrayList<>();
        for (AttackBean attackBean : attackBeanList) {
            if (!list.contains(attackBean)) {
                list.add(attackBean);
            }
        }
        attackBeanList = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AttackType getType() {
        return type;
    }

    public int getVul() {
        for (int i = 0; i < attackBeanList.size(); i++) {
            if (attackBeanList.get(i).isAttackSuccess()) {
                vul++;
            }
        }
        return vul;
    }

    public Integer getRegexID() {
        return regexID;
    }

    public void setRegexID(Integer regexID) {
        this.regexID = regexID;
    }
}
