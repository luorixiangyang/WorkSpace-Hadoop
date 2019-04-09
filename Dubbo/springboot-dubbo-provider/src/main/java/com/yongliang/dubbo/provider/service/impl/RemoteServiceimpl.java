package com.yongliang.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yongliang.dubbo.api.RemoteUserService;
import org.springframework.stereotype.Component;

/**
 * @author zhangyongliang
 * @create 2019-04-08 18:44
 **/
@Service(interfaceClass = RemoteUserService.class,retries = 0,version = "1.0.0")
@Component
public class RemoteServiceimpl implements RemoteUserService {
    @Override
    public String sayHello(String name) {
        return "Hello"+name;
    }
}
