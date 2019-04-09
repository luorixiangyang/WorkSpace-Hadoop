package com.yongliang.proxy;

import com.yongliang.proxy.dao.UserService;
import com.yongliang.proxy.serviceImp.UserviceImpl;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author zhangyongliang
 * @create 2019-03-31 16:41
 **/
public class CgLibMain {
    public static void main(String[] args) {
        com.yongliang.proxy.proxy.proxy.CglibProxy cglibProxy = new com.yongliang.proxy.proxy.proxy.CglibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserviceImpl.class);
        enhancer.setCallback(cglibProxy);
        UserService o = (UserService)enhancer.create();
        System.out.println( o.getName(1));
        System.out.println(o.getAge(1));
    }
}
