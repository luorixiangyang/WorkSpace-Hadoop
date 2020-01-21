package com.yongliang.socket.handler;

import com.yongliang.socket.protobuf.MessageBase;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * netty客户端处理实现
 *
 * @author zhangyongliang
 * @create 2020-01-20 10:50
 **/
@Slf4j
public class NettyClientHandler  extends SimpleChannelInboundHandler<MessageBase.Message> {
    /**
     * 服务端给客户端发送消息，下面方法进行接收消息
     * @param channelHandlerContext
     * @param message
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageBase.Message message) throws Exception {
        log.info("客户端收到消息：{}",message.getContentBytes().toStringUtf8());
    }

    /**
     * 处理异常, 一般将实现异常处理逻辑的Handler放在ChannelPipeline的最后
     * 这样确保所有入站消息都总是被处理，无论它们发生在什么位置，下面只是简单的关闭Channel并打印异常信息
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
