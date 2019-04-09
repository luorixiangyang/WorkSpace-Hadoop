package com.yongliang.proxy.serviceImp;

import com.yongliang.proxy.dao.Subject;

/**
 * @author zhangyongliang
 * @create 2019-03-31 15:56
 **/
public class RealSubject implements Subject {
    @Override
    public void rent() {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello: " + str);
    }
}
