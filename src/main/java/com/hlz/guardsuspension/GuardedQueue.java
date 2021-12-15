package com.hlz.guardsuspension;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author huanglingzhi
 */
public class GuardedQueue {
    private final Queue<Integer> sourceList;

    public GuardedQueue() {
        this.sourceList = new LinkedBlockingDeque<>();
    }

    public synchronized Integer get() {
        while (sourceList.isEmpty()) {
            try {
                // 如果队列为null,等待
                System.out.println("队列为空,开始等待");
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sourceList.peek();
    }

    public synchronized void put(Integer e) {
        sourceList.add(e);
        // 通知,继续执行
        System.out.println("队列不为空,通知all 开始执行");
        notifyAll();
    }
}
