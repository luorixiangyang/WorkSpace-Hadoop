package com.dongnao.server.handler;

import com.dongnao.server.processor.MessageProcessor;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * 
 * @author Five老师
 * @createTime 2018年3月25日 下午9:21:45
 * 
 */
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		//服务端与客户端的WebSocket交互
		MessageProcessor.messageHandler(ctx.channel(), msg.text());
	}

}
