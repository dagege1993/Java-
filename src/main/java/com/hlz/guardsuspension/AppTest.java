package com.hlz.guardsuspension;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author huanglingzhi
 */
public class AppTest {
    public static void main(String[] args) throws InterruptedException {
        GuardedQueue guardedQueue = new GuardedQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> {
            guardedQueue.get();
        });
        Thread.sleep(2000);
        executorService.execute(() -> {
            guardedQueue.put(20);
        });
        executorService.shutdown();
        // 使用之前 必须先手动关闭线程池，否则一直会阻塞到超时为止
        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }
}
