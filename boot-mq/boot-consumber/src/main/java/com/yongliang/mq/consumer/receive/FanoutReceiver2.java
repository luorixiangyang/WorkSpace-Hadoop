package com.yongliang.mq.consumer.receive;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

/**
 * @author zhangyongliang
 * @create 2019-10-28 15:58
 **/
@Component

public class FanoutReceiver2 {
    @RabbitHandler
    @RabbitListener(queues = "fanout2")
    public void onMessage(Message message, Channel channel) throws Exception{
        System.out.println("Fanout（FanoutReceiver2）消费消息：" + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
