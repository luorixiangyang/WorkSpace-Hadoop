package com.yongliang.mq.producer.producer;

import cn.hutool.core.date.DateUtil;
import com.yongliang.mq.producer.config.DelayedConfig;
import com.yongliang.mq.producer.config.FanoutConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
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

    public void sendDelayMsg(String msg) {
        System.out.println("发送时间：" + DateUtil.now());
        rabbitmqTemplate.convertAndSend(DelayedConfig.DELAY_EXCHANGE_NAME, DelayedConfig.DELAY_QUEUE_NAME, msg, message -> {
            message.getMessageProperties().setHeader("x-delay", 3000);
            return message;
        });
    }
}
