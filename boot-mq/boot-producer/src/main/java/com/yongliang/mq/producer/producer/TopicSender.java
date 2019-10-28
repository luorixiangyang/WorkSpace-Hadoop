package com.yongliang.mq.producer.producer;

import com.yongliang.mq.producer.config.TopicConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangyongliang
 * @create 2019-10-28 16:35
 **/
@Component
public class TopicSender {
    @Autowired
   private AmqpTemplate rabbitmqTemplate;
    public void topicSender(Object message) {
        String routingKey = "log.all.error";
        System.out.println(routingKey + " 发送消息：" + message);
        rabbitmqTemplate.convertAndSend(TopicConfig.EXCHANGE_NAME, routingKey, message);
    }
}
