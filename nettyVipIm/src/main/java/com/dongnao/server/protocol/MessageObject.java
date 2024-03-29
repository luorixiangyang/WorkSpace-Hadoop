package com.dongnao.server.protocol;
/**
 * 
 * @author Five老师
 * @createTime 2018年3月25日 下午10:24:29
 * 
 */
public class MessageObject {

	private String cmd; //指令类型 例如：LOGIN\LOGOUT\CHAT\SYSTEM
	private long time; //消息发送的时间戳
	private String nickName; //消息发送人
	private String content; //消息体
	private int online;//在线人数
	
	/**
	 * @param cmd
	 * @param time
	 * @param nickName
	 * @param content
	 * @param online
	 */
	public MessageObject(String cmd, long time, String nickName, String content, int online) {
		super();
		this.cmd = cmd;
		this.time = time;
		this.nickName = nickName;
		this.content = content;
		this.online = online;
	}

	public MessageObject(String type, long time, String nickName) {
		this.cmd = type;
		this.time = time;
		this.nickName = nickName;
		this.content = content;
	}

	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
}
