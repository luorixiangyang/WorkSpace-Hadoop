$(document).ready(function(){
	
	window.CHAT = {
		nickName:"匿名用户",
		
		login:function(){
			$("#error-msg").empty();
			//用户注册的名字
			var nickname = $("#nickname").val();
			CHAT.nickName = nickname;
			
			var _reg = /^\S{1,10}/;
			if(!_reg.test($.trim(nickname))){
				$("#error-msg").html("请输入1-10位正确的昵称");
				return false;
			}
			$("#shownikcname").html(nickname);
			$("#loginbox").hide();
			$("#chatbox").show();
			CHAT.init();
		},
		init:function(){
			//判断浏览器是否支持WebSocket协议
			if(!window.WebSocket){
				window.WebSocket = window.MozWebSocket;
			}
			
			if(window.WebSocket){
				socket = new WebSocket("ws://localhost:8080/im");
				socket.onopen = function(e){
					console.log("客户端连接成功.");
					socket.send("[LOGIN]["+new Date().getTime()+"]["+CHAT.nickName+"]");
				};
				socket.onclose = function(e){
					console.log("客户端关闭连接.");
				};
				socket.onmessage = function(e){
					console.log("客户端接收服务端信息："+e.data);
				}
			}else{
				alert("您的浏览器不支持WebSocket协议！");
			}
		},
		chat:function () {
			
		}
	};
});