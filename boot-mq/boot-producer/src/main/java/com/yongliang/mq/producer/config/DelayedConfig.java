package com.yongliang.mq.producer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 延迟消息处理机制
 *
 * @author zhangyongliang
 * @create 2019-10-28 16:56
 **/
@Configuration
public class DelayedConfig {
    public final static String DELAY_QUEUE_NAME = "delayed.goods.order";
    public final static String DELAY_EXCHANGE_NAME = "delayedec";

    @Bean
    public Queue queue() {
        return new Queue(DelayedConfig.DELAY_QUEUE_NAME);
    }

    // 配置默认的交换机
    @Bean
    CustomExchange customExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        //参数二为类型：必须是x-delayed-message
        return new CustomExchange(DelayedConfig.DELAY_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    // 绑定队列到交换器
    @Bean
    Binding binding(Queue queue, CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DelayedConfig.DELAY_QUEUE_NAME).noargs();
    }
}
