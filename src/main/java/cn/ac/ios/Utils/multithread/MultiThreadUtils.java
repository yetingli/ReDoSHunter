package cn.ac.ios.Utils.multithread;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;


/**
 * @author pqc
 * @ClassName: MultiThreadUtils.java
 * @Description: 多线程工具类
 */
@SuppressWarnings({"all"})
public class MultiThreadUtils<T, V> {

    private static Logger logger = Logger.getLogger(String.valueOf(MultiThreadUtils.class));

    public static int[] re;

    // 线程个数，如不赋值，默认为5
    private int threadCount = 5;
    // 具体业务任务
    private ITask<T, V> task;
    // 线程池管理器
    private ArrayList<Integer> tasksTime;

    private int TIME_LIMTE;
    private static final int TASK_NOT_START = -1;
    public static final int TASK_DONE = -3;
    private static final int TASK_INTERRPURT = -2;

    /**
     * 初始化线程池和线程个数<BR>
     */
    public static MultiThreadUtils newInstance(int threadCount, int time) {
        MultiThreadUtils instance = new MultiThreadUtils();
        threadCount = threadCount;
        instance.setThreadCount(threadCount);
        instance.setTimeOut(time);
        return instance;
    }

    private void setTimeOut(int time) {
        this.TIME_LIMTE = time;
    }

    /**
     * 多线程分批执行list中的任务<BR>
     * 方法名：execute<BR>
     */
    public MultiBaseBean execute(List<T> data, Map<String, Object> params, ITask<T, V> task) {
        tasksTime = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            tasksTime.add(TASK_NOT_START);
        }
        ThreadPoolExecutor threadpool = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
        int length = data.size();
        List<Future<V>> futureList = new ArrayList<Future<V>>();
        List<HandleCallable> HandleCallableList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            T subData = data.get(i);
            HashMap<String, Integer> hashMap = new HashMap<>();
            hashMap.put("id", new Integer(i + 1));
            HandleCallable execute = new HandleCallable<T, V>(subData, hashMap, task, tasksTime);
            HandleCallableList.add(execute);
            Future<V> future = threadpool.submit(execute);
            futureList.add(future);
        }
        threadpool.shutdown();
        List<V> result = new ArrayList<>();
        long temp = System.currentTimeMillis();
        while (true) {
            if (threadpool.isTerminated()) {
                for (int i = 0; i < HandleCallableList.size(); i++) {
                    try {
                        if (HandleCallableList.get(i).getResult() != null) {
                            result.add((V) HandleCallableList.get(i).getResult());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                Logger.getGlobal().info("任务结束！");
                break;
            }
            try {
                long completedTaskCount = threadpool.getCompletedTaskCount();
                Logger.getGlobal().info("目前已运行：" + String.valueOf(completedTaskCount));
                long time = System.currentTimeMillis() - temp;
                double leftTime = (double) (length - completedTaskCount) / (double) completedTaskCount * (double) time / (double) 1000;
                Logger.getGlobal().info("剩余时间：" + String.valueOf(leftTime) + "s");
                timeCounting();
                cancelTimeOutTasks(futureList);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return MultiBaseBean.newInstance().setData(result);
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    /**
     * 为开始任务计时
     */
    public void timeCounting() {
        if (TIME_LIMTE <= 0) {
            return;
        }
        StringBuilder now = new StringBuilder();
        StringBuilder runing = new StringBuilder();
        for (int i = 0; i < tasksTime.size(); i++) {
            if (tasksTime.get(i) == TASK_INTERRPURT) {
                runing.append(i + 1).append(" ");
            }
            if (tasksTime.get(i) > TIME_LIMTE) {
                tasksTime.set(i, TASK_INTERRPURT);
            } else if (tasksTime.get(i) >= 0) {
                now.append(i + 1).append(" ");
                int time = tasksTime.get(i);
                tasksTime.set(i, time + 1);
            }
        }
        Logger.getGlobal().info("当前执行：" + now.toString());
        if (runing.length() != 0) {
            Logger.getGlobal().info("中断后仍在执行：" + runing.toString());
        }
    }


    /**
     * 打断超时任务
     *
     * @param futureList
     * @param <V>
     */
    public <V> void cancelTimeOutTasks(List<Future<V>> futureList) {
        if (TIME_LIMTE <= 0) {
            return;
        }
        for (int i = 0; i < tasksTime.size(); i++) {
            int time = tasksTime.get(i);
            if (time == TASK_INTERRPURT) {
                if (!futureList.get(i).isDone()) {
                    futureList.get(i).cancel(true);
                    Logger.getGlobal().info("任务 " + (i + 1) + ":超时中断");
                }
            }
        }
    }
}