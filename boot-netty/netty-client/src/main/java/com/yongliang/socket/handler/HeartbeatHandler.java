package com.yongliang.socket.handler;

import com.yongliang.socket.client.NettyClient;
import com.yongliang.socket.protobuf.MessageBase;
import com.yongliang.socket.utils.SpringUtil;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangyongliang
 * @create 2018-10-25 17:15
 */
@Slf4j
@Component
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private NettyClient nettyClient;
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                log.info("长期未收到服务器推送信息，进行重连");
                if(nettyClient==null){
                    nettyClient=SpringUtil.getBean(NettyClient.class);
                }
                EventLoop eventLoop = ctx.channel().eventLoop();
                eventLoop.schedule(() -> nettyClient.start(), 5L, TimeUnit.SECONDS);
            }
            if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                log.info("已经10s没有发送消息给服务端");
                //向服务端送心跳包
                MessageBase.Message heartbeat = new MessageBase.Message().toBuilder().setCmd(MessageBase.Message.CommandType.HEARTBEAT_REQUEST)
                        .setRequestId("LZ20151203093957")
                        .setContent("heartbeat").build();
                //发送心跳消息，并在发送失败时关闭该连接
                ctx.writeAndFlush(heartbeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //如果运行过程中服务端挂了,执行重连机制
        EventLoop eventLoop = ctx.channel().eventLoop();
        log.info("正在进行服务端重连操作...");
        if(nettyClient==null){
            nettyClient=SpringUtil.getBean(NettyClient.class);
        }
        eventLoop.schedule(() -> nettyClient.start(), 5L, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("捕获的异常：{}", cause.getMessage());
        ctx.channel().close();
    }
}
