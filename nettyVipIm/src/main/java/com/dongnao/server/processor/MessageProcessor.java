package com.dongnao.server.processor;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 
 * @author Five老师
 * @createTime 2018年3月25日 下午10:03:52
 * 
 */
public class MessageProcessor {

	//用于记录/管理所有客户端的Channel
	private static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	public static void messageHandler(Channel client, String message){
		
		System.out.println("客户端发送的消息："+message);
		client.writeAndFlush(new TextWebSocketFrame("对接成功！"));
		users.add(client);
		System.out.println("当前在线人数："+users.size());
	}
}
