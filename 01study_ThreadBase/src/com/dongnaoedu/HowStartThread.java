package com.dongnaoedu;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/11/26
 * 创建时间: 20:26
 * 如何创建一个线程
 */
public class HowStartThread {

    private static class TestThread extends Thread{
        @Override
        public void run() {
            System.out.println("TestThread is runing");

        }
    }

    private static class TestRunable implements Runnable{

        @Override
        public void run() {
            System.out.println("TestRunable is runing");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new TestThread();
        Thread t2 = new Thread(new TestRunable());
        t1.start();
        t2.start();

    }

}
