package com.dongnaoedu.forkjoin.SunWuKong.Impl;


import com.dongnaoedu.forkjoin.service.IPickTaoZi;
import com.dongnaoedu.forkjoin.service.IProcessTaoZi;
import com.dongnaoedu.forkjoin.vo.Color;
import com.dongnaoedu.forkjoin.vo.PanTao;
import com.dongnaoedu.forkjoin.vo.Size;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/10/12
 * 创建时间: 16:32
 */
public class WuKongPickImpl implements IPickTaoZi {

    private IProcessTaoZi processTaoZi;

    public WuKongPickImpl(IProcessTaoZi processTaoZi) {
        this.processTaoZi = processTaoZi;
    }

    @Override
    public boolean pick(PanTao[] src, int index) {
        if(src[index].getColor()== Color.RED&&
                src[index].getSize()== Size.BIG&&
                src[index].getYear()>=6000){
            processTaoZi.processTaoZi(src[index]);
            return true;
        }else{
            return false;
        }
    }
}
