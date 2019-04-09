package com.dongnaoedu.interrupt;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/11/26
 * 创建时间: 20:32
 * 使用自定义的取消标志位中断线程（不靠谱）
 */
public class FlagCancel {

    private static class TestRunable implements Runnable{

        private volatile boolean on = true;
        private long i =0;

        @Override
        public void run() {
            while(on){
                System.out.println(i++);
                /*阻塞方法，on不起作用
                如wait,sleep,阻塞队列中的方法(put,take)
                */
                try {
                    synchronized (this){
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("TestRunable is runing :"+i);
        }

        public void cancel(){
            System.out.println("Ready stop thread......");
            on = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestRunable testRunable = new TestRunable();
        Thread t = new Thread(testRunable);
        t.start();
        Thread.sleep(10);
        testRunable.cancel();
    }

}
