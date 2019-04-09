package com.yongliang.webflux.webfluxdemo.handler;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author zhangyongliang
 * @create 2019-03-08 18:02
 **/
@Component
public class TimeHandler {

    public static Mono<ServerResponse> getTime(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Now is " + DateUtil.now()), String.class);
    }

    public static Mono<ServerResponse> getDate(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_PLAIN).body(Mono.just("Today is " + DateUtil.now()), String.class);
    }

    public static Mono<ServerResponse> sendTimePerSec(ServerRequest serverRequest) {
        return ok().contentType(MediaType.TEXT_EVENT_STREAM).body(
                Flux.interval(Duration.ofSeconds(1)).
                        map(l -> DateUtil.now()), String.class);
    }
}
