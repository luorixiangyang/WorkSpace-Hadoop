package com.yongliang.mq.consumer.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangyongliang
 * @create 2019-10-28 16:38
 **/
@Component
@RabbitListener(queues = "log.all")
public class TopicReceiver2 {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("log.* 消费消息：" + msg);
    }
}
