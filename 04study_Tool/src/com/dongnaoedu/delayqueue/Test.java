package com.dongnaoedu.delayqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/12/05
 * 创建时间: 21:51
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<CacheBean<User>> queue = new DelayQueue<CacheBean<User>>();
        new Thread(new PutInCache(queue)).start();
        new Thread(new GetFromCache(queue)).start();

        for(int i=1;i<20;i++){
            Thread.sleep(500);
            System.out.println(i*500);
        }
    }
}
