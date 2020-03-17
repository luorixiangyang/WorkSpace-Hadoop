package com.yongliang.mq.consumer.receive;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangyongliang
 * @create 2019-10-28 16:37
 **/
@Component

public class TopicReceive {
    @RabbitHandler
    @RabbitListener(queues = "log")
    public void handler(Message message, Channel channe) throws Exception {
        System.out.println("接收消息：" + message);
        channe.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
