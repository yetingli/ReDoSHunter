package cn.ac.ios.Utils.multithread;

import java.io.Serializable;

/**
 * 返回结果统一bean
 * @author pqc
 */
public class MultiBaseBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    // 成功状态
    public static final int SUCCESS = 1;
    // 处理中状态
    public static final int PROCESSING = 0;
    // 失败状态
    public static final int FAIL = -1;
    // 描述
    private String msg = "success";
    // 状态默认成功
    private int code = SUCCESS;
    // 备注
    private String remark;
    // 返回数据
    private T data;


    public MultiBaseBean() {
        super();
    }

    public MultiBaseBean(T data) {
        super();
        this.data = data;
    }

    /**
     * 使用异常创建结果
     */
    public MultiBaseBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }

    /**
     * 实例化结果默认成功状态<BR>
     * 方法名：newInstance<BR>
     */
    public static <T> MultiBaseBean<T> newInstance() {
        MultiBaseBean<T> instance = new MultiBaseBean<T>();
        //默认返回信息
        instance.code = SUCCESS;
        instance.msg = "success";
        return instance;
    }

    /**
     * 实例化结果默认成功状态和数据<BR>
     * 方法名：newInstance<BR>
     */
    public static <T> MultiBaseBean<T> newInstance(T data) {
        MultiBaseBean<T> instance = new MultiBaseBean<T>();
        //默认返回信息
        instance.code = SUCCESS;
        instance.msg = "success";
        instance.data = data;
        return instance;
    }

    /**
     * 实例化返回结果<BR>
     * 方法名：newInstance<BR>
     */
    public static <T> MultiBaseBean<T> newInstance(int code, String msg) {
        MultiBaseBean<T> instance = new MultiBaseBean<T>();
        //默认返回信息
        instance.code = code;
        instance.msg = msg;
        return instance;
    }

    /**
     * 实例化返回结果<BR>
     * 方法名：newInstance<BR>
     */
    public static <T> MultiBaseBean<T> newInstance(int code, String msg, T data) {
        MultiBaseBean<T> instance = new MultiBaseBean<T>();
        //默认返回信息
        instance.code = code;
        instance.msg = msg;
        instance.data = data;
        return instance;
    }

    /**
     * 设置返回数据<BR>
     * 方法名：setData<BR>
     */
    public MultiBaseBean<T> setData(T data){
        this.data = data;
        return this;
    }

    /**
     * 设置结果描述<BR>
     * 方法名：setMsg<BR>
     */
    public MultiBaseBean<T> setMsg(String msg){
        this.msg = msg;
        return this;
    }

    /**
     * 设置状态<BR>
     * 方法名：setCode<BR>
     */
    public MultiBaseBean<T> setCode(int code){
        this.code = code;
        return this;
    }

    /**
     * 设置备注)<BR>
     * 方法名：setRemark<BR>
     */
    public MultiBaseBean<T> setRemark(String remark){
        this.remark = remark;
        return this;
    }

    /**
     * 设置成功描述和返回数据<BR>
     * 方法名：success<BR>
     */
    public MultiBaseBean<T> success(String msg, T data){
        this.code = SUCCESS;
        this.data = data;
        this.msg = msg;
        return this;
    }

    /**
     * 设置成功返回结果描述<BR>
     * 方法名：success<BR>
     */
    public MultiBaseBean<T> success(String msg){
        this.code = SUCCESS;
        this.msg = msg;
        return this;
    }

    /**
     * 设置处理中描述和返回数据<BR>
     * 方法名：success<BR>
     */
    public MultiBaseBean<T> processing(String msg, T data){
        this.code = PROCESSING;
        this.data = data;
        this.msg = msg;
        return this;
    }

    /**
     * 设置处理中返回结果描述<BR>
     * 方法名：success<BR>
     */
    public MultiBaseBean<T> processing(String msg){
        this.code = PROCESSING;
        this.msg = msg;
        return this;
    }

    /**
     * 设置失败返回描述和返回数据<BR>
     * 方法名：fail<BR>
     */
    public MultiBaseBean<T> fail(String msg, T data){
        this.code = FAIL;
        this.data = data;
        this.msg = msg;
        return this;
    }

    /**
     * 设置失败返回描述<BR>
     * 方法名：fail<BR>
     */
    public MultiBaseBean<T> fail(String msg){
        this.code = FAIL;
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }
    public int getCode() {
        return code;
    }
    public String getRemark() {
        return remark;
    }
}