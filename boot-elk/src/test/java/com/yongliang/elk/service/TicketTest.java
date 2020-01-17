package com.yongliang.elk.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangyongliang
 * @create 2019-11-14 16:47
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketTest {
    @Test
    public void sellTicket(){
        int threadNum = 3;    //模拟多个窗口 进行售票
        final CyclicBarrier barrier = new CyclicBarrier(threadNum);
        final CountDownLatch count = new CountDownLatch(threadNum);  // 用于统计 执行时长

        StopWatch watch = new StopWatch();
        watch.start();
        TickRunThread tickets = new TickRunThread(count, barrier);
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        for (int i = 0; i < threadNum; i++) {   //此处 设置数值  受限于 线程池中的数量
            executorService.submit(tickets);
        }
        try {
            count.await();
            executorService.shutdown();
            watch.stop();
            System.out.println("耗 时:" + watch.getTotalTimeSeconds() + "秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
