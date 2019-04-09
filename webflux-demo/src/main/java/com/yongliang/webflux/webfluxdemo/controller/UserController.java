package com.yongliang.webflux.webfluxdemo.controller;

import com.yongliang.webflux.webfluxdemo.model.User;
import com.yongliang.webflux.webfluxdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @author zhangyongliang
 * @create 2019-03-08 18:49
 **/
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("")
    public Mono<User> save(User user){
        return  userService.save(user);

    }
    @DeleteMapping("/{ussername}")
    public Mono<Long> deleteByUsername(@PathVariable String username) {
        return this.userService.deletebyUsername(username);
    }
    @GetMapping("/{username}")
    public Mono<User> findByUsername(@PathVariable String username) {
        return this.userService.findByUsername(username);
    }
    @GetMapping(value = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)    public Flux<User> findAll() {
        return this.userService.findAll().delayElements(Duration.ofSeconds(1));
    }
}
