package com.yongliang.socket.client;

import com.yongliang.socket.protobuf.MessageBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author zhangyongliang
 * @create 2020-01-20 11:31
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientMsgTest {
    @Autowired
    NettyClient nettyClient;
    @Test
    public void testSendMsg(){
        MessageBase.Message message=new MessageBase.Message().toBuilder().setCmd(MessageBase.Message.CommandType.NORMAL)
                .setContent("Hello Netty")
                .setRequestId(UUID.randomUUID().toString()).build();
        nettyClient.sendMsg(message);
    }
}
