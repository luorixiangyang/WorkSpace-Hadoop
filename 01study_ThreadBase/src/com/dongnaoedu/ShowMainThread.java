package com.dongnaoedu;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/11/26
 * 创建时间: 20:08
 * 一个java程序包含的线程
 */
public class ShowMainThread {

    public static void main(String[] args) {
        //java虚拟机的线程管理接口
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //获取线程信息的方法
        ThreadInfo[] threadInfos =
                threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo:threadInfos){
            System.out.println(threadInfo.getThreadId()+":"+threadInfo.getThreadName());
        }
    }



}
