package cn.ac.ios.Utils.multithread;

import java.util.Map;

/**
 * 任务处理接口
 * 具体业务逻辑可实现该接口
 * T 返回值类型
 * E 传入值类型
 * @author pqc
 */
public interface ITask<T, V> {

    /**
     * 任务执行方法接口<BR>
     * 方法名：execute<BR>
     *
     * @param e      传入对象
     * @param params 其他辅助参数
     * @return V<BR> 返回值类型
     */
    V execute(T e, Map<String, Integer> params);
}