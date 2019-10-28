package com.yongliang.mq.consumer.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * FanoutReceiver
 *
 * @author zhangyongliang
 * @create 2019-10-28 15:57
 **/
@Component
@RabbitListener(queues = "fanout")
public class FanoutReceiver {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("Fanout（FanoutReceiver）消费消息：" + msg);
    }
}
