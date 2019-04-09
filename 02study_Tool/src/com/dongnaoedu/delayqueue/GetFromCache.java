package com.dongnaoedu.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/12/05
 * 创建时间: 21:44
 */
public class GetFromCache implements Runnable {

    private DelayQueue<CacheBean<User>> queue;

    public GetFromCache(DelayQueue<CacheBean<User>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            try {
                CacheBean<User> item = queue.take();
                System.out.println("GetFromCache"+item.getId()+":"+item.getName()+
                        "data:"+((User)item.getData()).getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
