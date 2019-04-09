package com.dongnaoedu.forkjoin.SunWuKong;


import com.dongnaoedu.forkjoin.MakePanTaoArray;
import com.dongnaoedu.forkjoin.SunWuKong.Impl.WuKongPickImpl;
import com.dongnaoedu.forkjoin.SunWuKong.Impl.WuKongProcessImpl;
import com.dongnaoedu.forkjoin.service.IPickTaoZi;
import com.dongnaoedu.forkjoin.service.IProcessTaoZi;
import com.dongnaoedu.forkjoin.vo.PanTao;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/10/12
 * 创建时间: 16:45
 */
public class ForkJoinWuKong {

  private static class XiaoWuKong extends RecursiveTask<Integer>{

      private final static int THRESHOLD = 100;//阈值，数组多小，进行具体的业务操作
      private PanTao[] src;
      private int fromIndex;
      private int toIndex;
      private IPickTaoZi pickTaoZi;

      public XiaoWuKong(PanTao[] src, int fromIndex, int toIndex, IPickTaoZi pickTaoZi) {
          this.src = src;
          this.fromIndex = fromIndex;
          this.toIndex = toIndex;
          this.pickTaoZi = pickTaoZi;
      }

      @Override
      protected Integer compute() {
          if (toIndex-fromIndex<THRESHOLD){
              int count =0 ;
              for(int i=fromIndex;i<toIndex;i++){
                  if (pickTaoZi.pick(src,i)) count++;
              }
              return count;
          }else{
              //fromIndex....mid......toIndex
              int mid = (fromIndex+toIndex)/2;
              XiaoWuKong left = new XiaoWuKong(src,fromIndex,mid,pickTaoZi);
              XiaoWuKong right = new XiaoWuKong(src,mid,toIndex,pickTaoZi);
              invokeAll(left,right);
              return left.join()+right.join();

          }
      }
  }

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();
        PanTao[] src = MakePanTaoArray.makeArray();
        IProcessTaoZi processTaoZi = new WuKongProcessImpl();
        IPickTaoZi pickTaoZi = new WuKongPickImpl(processTaoZi);

        long start = System.currentTimeMillis();

        XiaoWuKong xiaoWuKong = new XiaoWuKong(src,0,
                src.length-1,pickTaoZi);

        pool.invoke(xiaoWuKong);
        //System.out.println("Task is Running.....");

        System.out.println("The count is "+ xiaoWuKong.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");

    }

}
