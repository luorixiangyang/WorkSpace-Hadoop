package com.yongliang.mq.consumer.receive;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
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

public class Receiver {
    @RabbitListener(queues = "direct")
    @RabbitHandler
    public void handler(Message message, Channel channe) throws Exception {
        System.out.println("接收消息：" + message);
        channe.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
