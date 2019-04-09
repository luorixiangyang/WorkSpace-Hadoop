package com.yongliang.dubbo.consumer.rest;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yongliang.dubbo.api.RemoteUserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyongliang
 * @create 2019-04-08 18:48
 **/
@RestController
public class RemoteUserRest  {
    @Reference(version = "1.0.0")
    private RemoteUserService remoteUserService;

    @GetMapping("/dubboTest")
    public String dubboTest() {
        return remoteUserService.sayHello("dubbo");
    }
}
