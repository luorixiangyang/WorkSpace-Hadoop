package com.yongliang.socket.controller;

import cn.hutool.core.date.DateUtil;
import com.yongliang.socket.protobuf.MessageBase;
import com.yongliang.socket.server.NettyServer;
import com.yongliang.socket.utils.ChannelMapUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务端控制类
 *
 * @author zhangyongliang
 * @create 2020-01-21 10:37
 **/
@RestController
public class ProducerController {
    @Autowired
    public NettyServer nettyServer;

    @GetMapping("/sendClient")
    public String sendClientMsg() {
        MessageBase.Message message = new MessageBase.Message()
                .toBuilder().setCmd(MessageBase.Message.CommandType.NORMAL)
                .setContent("这是服务端发送给客户端的消息:" + DateUtil.now())
                .setRequestId("LZ20151203093957").build();
        ChannelHandlerContext handlerContext = ChannelMapUtil.getChannelByName("LZ20151203093957");
        if (handlerContext != null) {
            boolean sendFlag = nettyServer.sendClientMsg(handlerContext.channel(), message);
        }
        return "send ok";
    }
}
