package com.yongliang.socket.handler;

import cn.hutool.core.date.DateUtil;
import com.yongliang.socket.protobuf.MessageBase;
import com.yongliang.socket.protobuf.message.HeartbeatResponsePacket;
import com.yongliang.socket.utils.ChannelMapUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
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
        //实现Channel统一管理和服务端定向向客户端发送消息
        String hosCode = message.getRequestIdBytes().toStringUtf8();
        if (ChannelMapUtil.getChannelByName(hosCode) == null) {
//            log.info("客户端加入了：{}", message.getRequestIdBytes().toStringUtf8());
            ChannelMapUtil.addChannel(hosCode, channelHandlerContext);
        }
        if (message.getCmd().equals(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)) {
//            log.info("收到客户端发来的心跳消息：{}", message.toString());
            channelHandlerContext.writeAndFlush(new HeartbeatResponsePacket());
        } else if (message.getCmd().equals(MessageBase.Message.CommandType.NORMAL)) {
            //接收的消息为普通消息
            MessageBase.Message result = new MessageBase.Message()
                    .toBuilder().setCmd(MessageBase.Message.CommandType.NORMAL)
                    .setContent("这是结果消息：" + DateUtil.date())
                    .setRequestId("LZ20151203093957").build();
            channelHandlerContext.writeAndFlush(result);
            log.info("收到客户端的业务消息：{}", message.getRequestIdBytes().toStringUtf8() + "-" + message.getContentBytes().toStringUtf8());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = insocket.getAddress().getHostAddress();
        log.info("收到客户端连接IP:{}", clientIp);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelMapUtil.removeChannelContext(ctx);
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                //标志该链接已经close 了 
                ctx.close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("Netty-Server捕获的异常：{}", cause.getMessage());
        ctx.channel().close();
    }

}
