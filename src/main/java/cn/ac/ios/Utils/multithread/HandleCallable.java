package cn.ac.ios.Utils.multithread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import static cn.ac.ios.Utils.multithread.MultiThreadUtils.TASK_DONE;

/**
 * @author pqc
 * @ClassName: HandleCallable.java
 * @Description: 线程调用
 */
@SuppressWarnings("rawtypes")
public class HandleCallable<T, V> implements Callable<V> {

    private static Logger logger = Logger.getLogger(HandleCallable.class.getName());
    // 需要处理的数据
    private T data;
    // 辅助参数
    private Map<String, Integer> params;
    // 具体执行任务
    private ITask<T, V> task;
    private ArrayList<Integer> tasksTime;
    private V result;

    public HandleCallable(T data, HashMap<String, Integer> params, ITask<T, V> task, ArrayList<Integer> tasksTime) {
        this.data = data;
        this.params = params;
        this.task = task;
        this.tasksTime = tasksTime;
    }


    @Override
    public V call() {
        tasksTime.set(params.get("id") - 1, 0);
        V bean = task.execute(data, params);
        tasksTime.set(params.get("id") - 1, TASK_DONE);
        this.result = bean;
        return result;
    }

    public V getResult() {
        return result;
    }
}