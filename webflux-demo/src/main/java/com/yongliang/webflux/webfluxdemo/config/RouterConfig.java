package com.yongliang.webflux.webfluxdemo.config;

import com.yongliang.webflux.webfluxdemo.handler.TimeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author zhangyongliang
 * @create 2019-03-08 18:01
 **/
@Configuration
public class RouterConfig {
    @Bean
   public RouterFunction<ServerResponse> timeRouter() {
        return route(GET("/time"), TimeHandler::getTime).andRoute(GET("/date"), TimeHandler::getDate)
                .andRoute(GET("/times"), TimeHandler::sendTimePerSec);
    }
}
