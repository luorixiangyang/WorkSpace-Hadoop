package com.yongliang.mq.producer.producer;

import com.yongliang.mq.producer.config.FanoutConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息发送
 *
 * @author zhangyongliang
 * @create 2019-10-28 15:22
 **/
@Component
public class Sender {
    @Autowired
    AmqpTemplate rabbitmqTemplate;

    public void send(Object message){
        System.out.println("发送消息："+message);
        rabbitmqTemplate.convertAndSend("direct",message);
    }
    public void sendFanoutMsg1(Object message){
        System.out.println("发送消息：" + message);
        rabbitmqTemplate.convertAndSend(FanoutConfig.EXCHANGE_NAME, FanoutConfig.QUEUE_NAME, message);
    }
    public void sendFanoutMsg2(Object message) {
        System.out.println("发送消息2：" + message);
        rabbitmqTemplate.convertAndSend(FanoutConfig.EXCHANGE_NAME,FanoutConfig.QUEUE_NAME2, message);
    }


}
