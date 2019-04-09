package com.dongnaoedu.forkjoin.service;


import com.dongnaoedu.forkjoin.vo.PanTao;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/10/12
 * 创建时间: 16:27
 *
 * 摘桃子的接口
 */
public interface IPickTaoZi {
    boolean pick(PanTao[] src, int index);
}
