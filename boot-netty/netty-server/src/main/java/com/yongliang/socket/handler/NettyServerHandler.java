package com.yongliang.socket.handler;

import com.yongliang.socket.protobuf.MessageBase;
import com.yongliang.socket.protobuf.message.HeartbeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * 服务处理类
 *
 * @author zhangyongliang
 * @create 2020-01-20 10:21
 **/
@Slf4j
@ChannelHandler.Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<MessageBase.Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBase.Message message) throws Exception {
        if (message.getCmd().equals(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)) {
//            log.info("收到客户端发来的心跳消息：{}", message.toString());
            channelHandlerContext.writeAndFlush(new HeartbeatResponsePacket());
        } else if (message.getCmd().equals(MessageBase.Message.CommandType.NORMAL)) {
            channelHandlerContext.writeAndFlush(message);
            log.info("收到客户端的业务消息：{}", message.toString());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        log.info("收到客户端连接IP:{}", clientIp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Netty-Server捕获的异常：{}", cause.getMessage());
        ctx.channel().close();
    }

}
