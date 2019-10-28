package com.yongliang.mq.producer.controller;

import com.yongliang.mq.producer.producer.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 发送消息控制类
 *
 * @author zhangyongliang
 * @create 2019-10-28 15:24
 **/
@Controller
@RequestMapping("/rabbitmq")
public class RabbitMqController {
    @Autowired
    Sender sender;

    @RequestMapping("/sender")
    @ResponseBody
    public String sender(){
        System.out.println("send string:hello world");
        sender.send("hello world");
        return "sending...";
    }
}
