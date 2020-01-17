package com.yongliang.mq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 发布/订阅模式
 *
 * @author zhangyongliang
 * @create 2019-10-28 15:53
 **/
@Configuration
public class FanoutConfig {
    //队列名称
   public  final static String QUEUE_NAME = "fanout";
    //队列名称
    public  final static String QUEUE_NAME2 = "fanout2";
    //交换器名称
    public final static String EXCHANGE_NAME = "myfanout";
    @Bean
    public Queue queueFanout() {
        return new Queue(FanoutConfig.QUEUE_NAME);
    }
    @Bean
    public Queue queueFanout2() {
        return new Queue(FanoutConfig.QUEUE_NAME2);
    }
    //配置交换器
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FanoutConfig.EXCHANGE_NAME);
    }
    // 绑定队列到交换器
    @Bean
    Binding bindingFanoutExchangeQueue(Queue queueFanout, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanout).to(fanoutExchange);
    }
    // 绑定队列到交换器
    @Bean
    Binding bindingFanoutExchangeQueue2(Queue queueFanout2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanout2).to(fanoutExchange);
    }

}
