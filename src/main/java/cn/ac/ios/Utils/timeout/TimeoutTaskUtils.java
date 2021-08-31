package cn.ac.ios.Utils.timeout;


import cn.ac.ios.Bean.Pair;

import java.util.concurrent.*;

import static cn.ac.ios.Bean.AttackBean.*;

/**
 * @author pqc
 */
public class TimeoutTaskUtils {

    /**
     * 执行一个有时间限制的任务
     *
     * @param task 待执行的任务
     * @return
     */
    public static Pair<Boolean, Integer> execute(TimeoutTask task) {
        Pair<Boolean, Integer> result = new Pair<>(false, 0);
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        long time = System.currentTimeMillis();
        try {
            Future<Pair<Boolean, Integer>> future = threadPool.submit(task);
            result = future.get(TIME_OUT, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            time = System.currentTimeMillis() - time;
            result = new Pair<>(time >= TIME_OUT, (int) time);
            threadPool.shutdownNow();
        } catch (ExecutionException e) {
            result = new Pair<>(false, STACK_ERROR);
            threadPool.shutdownNow();
        } catch (InterruptedException in) {
            result = new Pair<>(false, INTERRUPTED);
            threadPool.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            task.close();
            threadPool.shutdownNow();
        }
        return result;
    }
}