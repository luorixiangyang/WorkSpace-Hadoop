package com.yongliang.mq.consumer.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息消费者
 *
 * @author zhangyongliang
 * @create 2019-10-28 15:23
 **/
@Component
@RabbitListener(queues = "direct")
public class Receiver {
    @RabbitHandler
    public void handler(String message){
        System.out.println("接收消息："+message);
    }
}
