/**
 * socket 是websocket连接的对象
 */
var socket;

/**
 * 
 * @param {String} name  参数，表示用户的账户 
 */
function online(name){
	if('WebSocket' in window){
		alert("正在连接websocket");
		
		socket = new WebSocket('ws://localhost:8080/toonoisy/Online/'+name);
		//var socket = new WebSocket('ws://localhost:8080/toonoisy/Online');
		
		socket.onopen = function(){
			 console.log("连接打开了");
		};
		
		socket.onmessage = function(event){
			//var obj = JSON.parse(event);
			/*console.log(event);
			console.log(event.vaule);*/
			var s = JSON.parse(event.data);
			console.log(s);
			messageHandler(event.data);
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
 * 获取当前时间的字符串
 * @returns String 
 */
function getNowTime(){
	var time = new Date();
	return time.toLocaleDateString()+" "+time.toLocaleTimeString();
}

/**
 * 
 * 
 */
function sendMessage(){
	var username = document.getElementById("master").value;
	var roomName = document.getElementById("roomName").innerHTML;
	var createTime = getNowTime();
	console.log(createTime);
	var data = document.getElementById("input").value;
	document.getElementById("input").value="";    //清除输入框的内容
	var text = message("text",username,roomName,createTime,data);
	socket.send(text);
}

/**
 * @description 传入参数，返回对应的JSON字符串
 * @param {String} type 
 * @param {String} send 
 * @param {String} receive 
 * @param {String} createTime 
 * @param {String} data 
 */
function message(type, send, receive, createTime, data){
	return JSON.stringify({"type":type, "send":send, "receive":receive, "createTime":createTime, "data":data });
}
