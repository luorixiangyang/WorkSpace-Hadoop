package com.dongnaoedu.forkjoin.SunWuKong.Impl;


import com.dongnaoedu.forkjoin.service.IProcessTaoZi;
import com.dongnaoedu.forkjoin.vo.PanTao;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/10/12
 * 创建时间: 16:38
 */
public class WuKongProcessImpl implements IProcessTaoZi {
    @Override
    public void processTaoZi(PanTao taoZi) {
        //看看桃子，放到口袋里
        inBag();
    }

    private void inBag(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
