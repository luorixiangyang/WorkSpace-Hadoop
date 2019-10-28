package com.yongliang.mq.consumer.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangyongliang
 * @create 2019-10-28 15:58
 **/
@Component
@RabbitListener(queues = "fanout2")
public class FanoutReceiver2 {
    @RabbitHandler
    public void process(String message) {
        System.out.println("Fanout（FanoutReceiver2）消费消息：" + message);
    }
}
