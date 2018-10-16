/**
 * 在线池的连接
 */
var socket;
function online(name){
	if('WebSocket' in window){
		alert("正在连接websocket");
		
		socket = new WebSocket('ws://localhost:8080/toonoisy/Online/'+name);
		//var socket = new WebSocket('ws://localhost:8080/toonoisy/Online');
		
		socket.onopen = function(){
			 console.log("连接打开了");
		};
		
		socket.onmessage = function(event){
			var obj = JSON.parse(event);
			messageHandler(obj);
		};
		
		socket.onclose = function(){
			 console.log("连接关闭了");
		}
		
		socket.onerror = function(){
			 console.log("连接发生错误");
		}
		
	}else{
		alert("浏览器不支持websocket,请更换浏览器。");
	}
}

/**
 * 将输入框输入的信息封装成JSON的格式
 */
function packageMessage(){
	var text = document.getElementById("inputArea").value;
	
}

/**
 * 发送信息
 */
function sendMessage(text){
	socket.send(text);
}

