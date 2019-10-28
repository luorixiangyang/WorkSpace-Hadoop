package com.yongliang.mq.consumer.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangyongliang
 * @create 2019-10-28 16:38
 **/
@Component
@RabbitListener(queues = "log.all.error")
public class TopicReceiver3 {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("log.*.error 消费消息：" + msg);
    }
}
