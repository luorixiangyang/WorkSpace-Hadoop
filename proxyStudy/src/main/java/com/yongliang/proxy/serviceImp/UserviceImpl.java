package com.yongliang.proxy.serviceImp;

import com.yongliang.proxy.dao.UserService;

/**
 * @author zhangyongliang
 * @create 2019-03-31 16:21
 **/
public class UserviceImpl implements UserService {
    @Override
    public String getName(int id) {
        System.out.println("------getName------");
        return "Tom";
    }

    @Override
    public Integer getAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }
}
