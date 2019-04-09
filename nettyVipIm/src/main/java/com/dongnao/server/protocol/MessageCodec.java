package com.dongnao.server.protocol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Five老师
 * @createTime 2018年3月25日 下午10:23:46
 * 消息编解码
 */
public class MessageCodec {
	
	//将字符串指令解码为MessageObject对象
	public MessageObject decoder(String message){
		if(message==null||"".equals(message.trim())){
			return  null;
		}
		Pattern pattern=Pattern.compile("^\\[(.*)\\](\\s-\\s(.*))?");
		Matcher matcher=pattern.matcher(message);
		String headers="";//消息头
		String content=""; //消息体

		if(matcher.find()){
		 headers=	matcher.group(1);
		 content=matcher.group(3);
		 String[] split=headers.split("\\]\\[");
		 String cmd=split[0];
		 long time=Long.parseLong(split[1]);
		 String nickName=split[2];
//		 if(cmd.equals())
		 MessageObject messageObject=new MessageObject(cmd,time,nickName);
		}
		return null;
	}
	
	
	//将MessageObject对象编码为字符串指令
	public String encoder(MessageObject msg){
		
		return null;
	}
}
