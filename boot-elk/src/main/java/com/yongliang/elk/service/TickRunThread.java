package com.yongliang.elk.service;

import com.yongliang.elk.lock.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangyongliang
 * @create 2019-11-14 16:26
 **/
@Component
@Slf4j
public class TickRunThread implements Runnable {
    private CountDownLatch count;
    private CyclicBarrier barrier;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DistributedLock distributedLock;
    private static final Integer ExecuteTime = 5000;
    private static final Integer RetryInterval = 20;
    private static final String lockKey = "LockKey";
    private volatile static boolean working = true;
    private final static String LOCK_ID = "tickLock";

    public TickRunThread(CountDownLatch count, CyclicBarrier barrier) {
        this.count = count;
        this.barrier = barrier;
    }

    private int num = 20;  // 总票数

    public void sellTicket() {
        String name = Thread.currentThread().getName();
        boolean gotLock = false;
        try {
            gotLock = distributedLock.getLock(LOCK_ID, 10 * 1000);
            if (gotLock && working) {
                // Do your job
                if (redisTemplate.hasKey("tickNum")) {
                    num = Integer.parseInt(redisTemplate.opsForValue().get("tickNum").toString());
                }
                if (num > 0) {
                    num--;
                    redisTemplate.opsForValue().set("tickNum", num);
                    if (num != 0) {
                        System.out.println("==============" + name + "===============  售出票号" + (num + 1) + ",还剩" + num + "张票--");
                    } else {
                        System.out.println("==============" + name + "===============  售出票号" + (num + 1) + "，票已经票完!--");
                        return;
                    }
                }
                if (num == 0) {
                    System.out.println("==============" + name + "============票已经被抢空啦");
                    working = false;
                }
                Thread.sleep(ExecuteTime);
            } else {
                System.out.println(name + " Try to get the Lock,and wait " + RetryInterval + " millisecond....");
                Thread.sleep(RetryInterval);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if (!gotLock || !working) {
                    //未获取到锁的线程不用解锁
                    return;
                }
                /**
                 * 解锁成功后 sleep, 尝试让出cpu给其他线程机会
                 * 解锁失败 说明锁已经失效 被其他线程获取到
                 */
                if (distributedLock.releaseLock(LOCK_ID)) {
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void run() {
        String prefix = "#";
        String threadName = Thread.currentThread().getName();
        Thread.currentThread().setName(prefix + threadName);
        System.out.println(Thread.currentThread().getName() + "到达,等待中...");
        try {
            barrier.await();    // 此处阻塞  等所有线程都到位后 一起进行抢票
            if (Thread.currentThread().getName().equals(prefix + "pool-1-thread-2")) {
                System.out.println("-----------------全部线程准备就绪,开始抢票------------------");
            } else {
                Thread.sleep(5);
            }
            while (working) {
                sellTicket();
            }
            count.countDown();  //当前线程结束后，计数器-1
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
