package com.yongliang.webflux.webfluxdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author zhangyongliang
 * @create 2019-03-08 17:53
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("Welcome to reactive world :)");
    }
}
