package com.yongliang.mq.consumer.receive;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
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

public class FanoutReceiver {

    @RabbitListener(queues = "fanout")
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
