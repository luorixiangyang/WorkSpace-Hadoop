package com.yongliang.webflux.webfluxdemo.service;

import com.yongliang.webflux.webfluxdemo.model.User;
import com.yongliang.webflux.webfluxdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author zhangyongliang
 * @create 2019-03-08 18:43
 **/
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Mono<Long> deletebyUsername(String username){
        return  userRepository.deleteByUsername(username);
    }
    public Mono<User> findByUsername(String name){
        return userRepository.findByUsername(name);
    }
    public Mono<User> save(User user){
        return userRepository.save(user)
                .doOnError(System.out::println)
                .onErrorResume(e-> userRepository.findByUsername(user.getUsername()).flatMap(originalUser->{
                    user.setId(originalUser.getId());
                    return userRepository.save(user);
                }));
    }
    public Flux<User> findAll(){
        return userRepository.findAll();
    }
}
