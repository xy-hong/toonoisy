/**
 * @description 信息处理，传入JSON类型的字符串
 * @param {String} JSONString 
 */
function messageHandler(JSONString){
	var message = JSON.parse(JSONString);
	switch (message.type)
    {
	      //普通文本处理
		  case "text" : textHandler(message);break;
		  
		  //好友列表
		  case "roomMember": roomMemberHandler(message);break;
		  
		  case "roomNumber" : roomNumberHandler(message); break;
		  
		  case "enterNote" : enterNoteHandler(message); break;
    	
  		default:
  	}
}


/**
 * 处理
 * @param {*} message
 */
function textHandler(message){
	var screen = document.getElementById("screen");
	
	var master = document.getElementById("master").value;
	//screen.innerHTML += "<div>"+ message.creatTime+" "+message.send+":"+message.data+"</div>";
	var getcolor = document.getElementById("fontColor").value;
	if(master==message.send){
		console.log("aaaaaaaaaaa");
		screen.innerHTML+="<div><i>"+message.send+'</i> <span style="color:#C0C0C0">'+message.createTime+"</span><p><b class='myText' style=color:"+getcolor+">"+message.data+"</b> </p></div>";
	}else{
		console.log(master);
		console.log(message.send);
		screen.innerHTML+="<div><i>"+message.send+'</i> <span style="color:#C0C0C0">'+message.createTime+'</span><p><b>'+message.data+"</b> </p></div>";
	}
}

/**
 * @todo
 * @param {} message
 */
function imageHandler(message){
	
}

/**
 * @todo
 * @param {*} message
 */
function fileHandler(message){
	
}

function roomMemberHandler(message){
	var roomMember = document.getElementById("roomMember");
	var currentRoomName = document.getElementById("roomName").innerHTML;
	
	
	//因为被封装成字符串的形式，需要再一次prase
	var receive = message.receive;
	var members = JSON.parse(message.data);
	//console.log(members);
	if(currentRoomName==receive){
		roomMember.innerHTML="";
		for(var i=0; i<members.length; i++){
			roomMember.innerHTML+="<tr><td>"+members[i].id+"</td><td>"+members[i].name+"</td></tr>";
		}
	}
}

/**
 * 显示房间人数
 * @param message
 * @returns
 */
function roomNumberHandler(message){
	var roomNumber = document.getElementById("roomNumber");
	var currentRoomName = document.getElementById("roomName").innerHTML;
	
	var receive = message.receive;
	var number = message.data;
	if(currentRoomName==receive){
		roomNumber.innerHTML = "当前房间有"+number+"人在线";
	}
	
	
}

/**
 * 显示进入房间提示
 * @param message
 * @returns
 */
function enterNoteHandler(message){
	var screen = document.getElementById("screen");
	var currentRoomName = document.getElementById("roomName");

	screen.innerHTML += "<i style='position:abslute;margin-left:200px;'>"+message.data+"进入当前房间</i><br/>"
}