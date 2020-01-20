package com.yongliang.socket.controller;

import com.yongliang.socket.client.NettyClient;
import com.yongliang.socket.protobuf.MessageBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zhangyongliang
 * @create 2020-01-20 11:35
 **/
@RestController
public class ConsumerController {
    @Autowired
    private NettyClient nettyClient;

    @GetMapping("/send")
    public String send() {
        MessageBase.Message message = new MessageBase.Message()
                .toBuilder().setCmd(MessageBase.Message.CommandType.NORMAL)
                .setContent("Hello Netty")
                .setRequestId(UUID.randomUUID().toString()).build();
        nettyClient.sendMsg(message);
        return "send ok";
    }
}
