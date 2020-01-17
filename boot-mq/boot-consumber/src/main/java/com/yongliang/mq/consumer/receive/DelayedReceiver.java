package com.yongliang.mq.consumer.receive;

import cn.hutool.core.date.DateUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 延迟接收消息者
 *
 * @author zhangyongliang
 * @create 2019-10-28 16:59
 **/
@Component
@RabbitListener(queues = "delayed.goods.order")
public class DelayedReceiver {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("接收时间:" + DateUtil.now());
        System.out.println("消息内容：" + msg);
    }
}
