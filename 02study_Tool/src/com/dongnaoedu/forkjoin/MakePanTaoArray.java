package com.dongnaoedu.forkjoin;


import com.dongnaoedu.forkjoin.vo.Color;
import com.dongnaoedu.forkjoin.vo.PanTao;
import com.dongnaoedu.forkjoin.vo.Size;

import java.util.Random;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/10/12
 * 创建时间: 16:06
 */
public class MakePanTaoArray {

    //数组长度
    public static final int ARRAY_LENGTH  = 40000;
    //作为基准的值
    public static final int STANDARD_VAL  = 66694523;

    public static PanTao[] makeArray() {

        //new三个随机数发生器
        Random rColor = new Random();
        Random rSize = new Random();
        Random rYear = new Random();
        PanTao[] result = new PanTao[ARRAY_LENGTH];
        for(int i=0;i<ARRAY_LENGTH;i++){
            //填充数组
            PanTao panTao = new PanTao(
                    rColor.nextBoolean() ? Color.RED:Color.GREEN,
                    rSize.nextBoolean() ? Size.BIG:Size.SMALL,
                    rYear.nextInt(9001));
            result[i] =  panTao;
        }
        return result;
    }

    public static void main(String[] args) {
        PanTao[] panTaos = makeArray();
        for(PanTao panTao:panTaos){
            System.out.println(panTao);
        }
    }

}
