package com.yongliang.mq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题模式
 *
 * @author zhangyongliang
 * @create 2019-10-28 16:31
 **/

@Configuration
public class TopicConfig {
    //队列名称1
   public final static String QUEUE_NAME = "log";
    //队列名称2
    public final static String QUEUE_NAME2 = "log.all";
    //队列名称3
    public final static String QUEUE_NAME3 = "log.all.error";
    //交换器名称
    public final static String EXCHANGE_NAME = "topicExchange";

    @Bean
    public Queue queuetopic() {
        return new Queue(TopicConfig.QUEUE_NAME);
    }
    @Bean
    public Queue queuetopic2() {
        return new Queue(TopicConfig.QUEUE_NAME2);
    }
    @Bean
    public Queue queuetopic3() {
        return new Queue(TopicConfig.QUEUE_NAME3);
    }
    // 配置交换器
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TopicConfig.EXCHANGE_NAME);
    }
    // 绑定队列到交换器，并设置路由键（log.#）
    @Bean
    Binding bindingtopicExchangeQueue(Queue queuetopic, TopicExchange topicExchange) {
        return BindingBuilder.bind(queuetopic).to(topicExchange).with("log.#");
    }
    // 绑定队列到交换器，并设置路由键（log.*）
    @Bean
    Binding bindingtopicExchangeQueue2(Queue queuetopic2, TopicExchange topicExchange) {
        return BindingBuilder.bind(queuetopic2).to(topicExchange).with("log.*");
    }
    // 绑定队列到交换器，并设置路由键（log.*.error）
    @Bean
    Binding bindingtopicExchangeQueue3(Queue queuetopic3, TopicExchange topicExchange) {
        return BindingBuilder.bind(queuetopic3).to(topicExchange).with("log.*.error");
    }
}

