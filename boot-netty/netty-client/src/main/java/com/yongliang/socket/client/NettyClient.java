package com.yongliang.socket.client;

import com.yongliang.socket.handler.ClientHandlerInitilizer;
import com.yongliang.socket.protobuf.MessageBase;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * netty客户端
 *
 * @author zhangyongliang
 * @create 2020-01-20 10:28
 **/
@Component
@Slf4j
public class NettyClient {
    private EventLoopGroup group = new NioEventLoopGroup();
    @Value("${netty.port}")
    private int port;
    @Value("${netty.host}")
    private String host;
    private SocketChannel socketChannel;

    public void sendMsg(MessageBase.Message message) {
        socketChannel.writeAndFlush(message);
    }

    @PostConstruct
    public void start() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(host, port)
                .handler(new ClientHandlerInitilizer());
        ChannelFuture future = bootstrap.connect();
        //客户端断线重连
        future.addListener((ChannelFutureListener) channelFuture -> {
            if (channelFuture.isSuccess()) {
                log.info("连接Netty服务端成功");
            } else {
                log.info("连接失败，进行断线重连");
                channelFuture.channel().eventLoop().schedule(() -> start(), 20, TimeUnit.SECONDS);
            }

        });
        socketChannel = (SocketChannel) future.channel();
    }
}
