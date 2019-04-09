package com.dongnaoedu.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/12/05
 * 创建时间: 21:47
 */
public class PutInCache implements Runnable {

    private DelayQueue<CacheBean<User>> queue;

    public PutInCache(DelayQueue<CacheBean<User>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        CacheBean cacheBean = new CacheBean("1","5秒",
                new User("Mark"),5000);
        CacheBean cacheBean2 = new CacheBean("1","3秒",
                new User("Mike"),3000);
        queue.offer(cacheBean);
        System.out.println("put in cache:"+cacheBean.getId()+":"+cacheBean.getName());
        queue.offer(cacheBean2);
        System.out.println("put in cache:"+cacheBean2.getId()+":"+cacheBean2.getName());

    }
}
