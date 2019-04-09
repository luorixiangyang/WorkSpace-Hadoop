package com.dongnaoedu;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/11/23
 * 创建时间: 14:44
 * isEmpty()和size()的性能差异
 */
public class ConcurrentLinkedQueueTest {
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();
    private static int count = 50000;
    private static int count2 = 2;
    private static CountDownLatch cd = new CountDownLatch(count2);

    public static void dothis() {
        for (int i = 0; i < count; i++) {
            queue.offer(i);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        ExecutorService es = Executors.newFixedThreadPool(4);
        ConcurrentLinkedQueueTest.dothis();
        //启用两个线程取数据
        for (int i = 0; i < count2; i++) {
            es.submit(new Poll());
        }
        cd.await();
        System.out.println("cost time "
                + (System.currentTimeMillis() - timeStart) + "ms");
        es.shutdown();
    }
    static class Poll implements Runnable {
        @Override
        public void run() {
            //while (queue.size()>0) {
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
            cd.countDown();
        }
    }
}
