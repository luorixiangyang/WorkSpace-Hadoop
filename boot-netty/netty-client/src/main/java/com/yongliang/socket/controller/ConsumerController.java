package com.yongliang.socket.controller;

import com.yongliang.socket.client.NettyClient;
import com.yongliang.socket.protobuf.MessageBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .setContent("中华人民共和国")
                .setRequestId("LZ20151203093957").build();
        nettyClient.sendMsg(message);
        return "send ok";
    }
}
