package com.yongliang.socket.server;

import com.yongliang.socket.handler.NettyServerHandlerInitializer;
import com.yongliang.socket.protobuf.MessageBase;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * NettyServer启动服务类
 *
 * @author zhangyongliang
 * @create 2020-01-17 15:37
 **/
@Component
@Slf4j
public class NettyServer {
    @Value("${netty.port}")
    private Integer port;
    //线程组用于处理连接工作
    private EventLoopGroup boss = new NioEventLoopGroup();
    //work 线程组用于数据处理
    private EventLoopGroup work = new NioEventLoopGroup();

    //向客户端发送消息
    public boolean sendClientMsg(Channel channel, MessageBase.Message message) {
        if (channel.isActive()) {
            ChannelFuture resultFuture = channel.writeAndFlush(message);
            resultFuture.addListener((ChannelFutureListener) channelFuture -> log.info("服务端手动发送 Google Protocol 成功={}", message.getContentBytes().toStringUtf8()));
            return true;
        } else {
            log.info("该客户端不在线：{}", message.getRequestIdBytes().toStringUtf8());
            return false;
        }
    }

    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work)
                //指定channel
                .channel(NioServerSocketChannel.class)
                //使用指定的端口设置套接字地址
                .localAddress(new InetSocketAddress(port))
                //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG, 1024)
                //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.TCP_NODELAY, true)
                //设置为长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //用于操作接收缓冲区和发送缓冲区
                .childOption(ChannelOption.SO_RCVBUF, 256)
                //用于操作接收缓冲区和发送缓冲区
                .childOption(ChannelOption.SO_SNDBUF, 256)
                //将小的数据包包装成更大的帧进行传送，提高网络的负载
                .childHandler(new NettyServerHandlerInitializer());
        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            log.info("启动 Netty Server,netty端口为：" + port);
        }
    }

    @PreDestroy
    public void destory() throws InterruptedException {
        boss.shutdownGracefully().sync();
        work.shutdownGracefully().sync();
        log.info("关闭Netty");
    }
}
