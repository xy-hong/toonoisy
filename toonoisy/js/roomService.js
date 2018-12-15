/*
    此js文件包含房间的操作
*/

/**
 * 点击房间标签，切换房间，更新视图
 * @param {String} newRoomName 
 */
function switchRoom(newRoomName){
	console.log("切换房间");
	currentRoomName = newRoomName;

	updateRoomMemberView();
	updateRoomInfoView();

	screenRoll();
}

function invite(receive){
    var master = document.getElementById("master").value;
    var roomName = document.getElementById("roomName").innerHTML;
    var request = new XMLHttpRequest();

    request.onreadystatechange = function(){
        if(request.readyState == 4 && request.status == 200){
			alert(request.responseText);
		}
    }

    request.open("POST","Invite",true);
    request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    var data = "username="+master+"&roomName="+roomName+"&receive="+receive;
    request.send(data);

}

/**
 * 进入指定房间
 * @param {Sting} room
 */
function enterRoom(room){
	var username = document.getElementById("master").value;
	
	var request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			alert(request.responseText);
		}
	}
	
	request.open("POST","EnterRoom",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var data = "username="+username+"&room="+room;
	request.send(data);
	
}

/**
 * 创建新房间
 */
function createRoom(){
	var username =document.getElementById("master").value;
	var room  = document.getElementById("tab_Title").value;

	var request = new XMLHttpRequest();

	request.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			alert(request.responseText);
			if(request.responseText == '创建成功'){
				addTabFunction();
			}
		}
	}

	request.open("POST","CreateRoom",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var data = "username="+username+"&room="+room;
	request.send(data);

}

function outRoom(roomName){
	var username =document.getElementById("master").value;
	//var room  = document.getElementById("tab_Title").value;
	console.log("请求退出房间"+roomName);
	var request = new XMLHttpRequest();

	request.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			alert(request.responseText);
			roomManger.delete(roomName);
		}
	}

	request.open("POST","OutRoom",true);
	request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	var data = "username="+username+"&roomName="+roomName;
	request.send(data);

}

function sendRecord(roomName){
	var username = document.getElementById("master").value;

	var room = roomManger.get(roomName);

	var httpRequest = new XMLHttpRequest();

	httpRequest.onreadystatechange = function(){
		if(request.readyState == 4 && request.status == 200){
			alert(request.responseText);
			roomManger.delete(roomName);
		}
	}

	room.username = username;
	httpRequest.open("POST","SaveRecord",true);
	httpRrequest.setRequestHeader("Content-type","application/x-www-form-urlencoded");

	httpRequest.send(JSON.stringify(room));

}