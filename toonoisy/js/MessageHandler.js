/**
 * @description 信息处理，传入JSON类型的字符串
 * @param {String} JSONString 
 */
function messageHandler(JSONString){
	var message = JSON.parse(JSONString);
	console.log("接收到"+message);
	switch (message.type)
    {
	      //普通文本处理
		  case "text" : textHandler(message); updateRoomRecord(message); screenRoll(); break;

		  case "image" : imageHandler(message); updateRoomRecord(message);screenRoll(); break;

		  case "file" : fileHandler(message); updateRoomRecord(message); screenRoll();break;
		  
		  case "enterNotice" : enterNoticeHandler(message); break;

		  case "outNotice" : outNoticeHandler(message);break;
		  
		  case "invite" : inviteHandler(message);break;

		  case "updateNotice" : updateNoticeHandler(message);break;
    	
  		default:
  	}
}

function updateNoticeHandler(message){
	console.log(message.receive);
	var room = roomManger.get(message.receive);
	
	if(room == null){
		room = new Room(message.receive,0,null);
		roomManger.set(room.roomName,room);
	}

	var roomInfo = JSON.parse(message.data);

	console.log(roomInfo.number);
	console.log(roomInfo.membersInfo);  

	room.roomNumber = roomInfo.number;
	room.roomMember = roomInfo.membersInfo;

	//如果当前页面是被更新的房间，则更新视图
	if(message.receive == currentRoomName){
		//调用切换标签，更新房间的方法
		updateRoomInfoView();
		updateRoomMemberView();
	}
}




/**
 * 处理
 * @param {*} message
 */
function textHandler(message){
	var screenName = message.receive+"_screen";
	console.log(screenName);
	var screen = document.getElementById(screenName);
	console.log(screen);
	
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
	console.log("调用了img");
	var screenName = message.receive+"_screen";
	var screen = document.getElementById(screenName);

	//var master = document.getElementById("master").value;
	var imgURL = "image/"+message.data;
	screen.innerHTML+="<div><i>"+message.send+'</i> <span style="color:#C0C0C0">'+message.createTime+"</span><p><img  src="+imgURL+" width='140px' heigth='125px' ></img> </p></div>";
	
}

/**
 * @todo
 * @param {*} message
 */
function fileHandler(message){
	console.log("调用了file");
	var screenName = message.receive+"_screen";
	var screen = document.getElementById(screenName);

	//var master = document.getElementById("master").value;
	var fileURL = "file/"+message.data;
	screen.innerHTML+="<div><i>"+message.send+'</i> <span style="color:#C0C0C0">'+message.createTime+"</span><p><a  href="+fileURL+" width='140px' heigth='125px' download="+message.data+"><img src='systemImage/file.png' heigth='70px' width='55px' alt='"+message.data+"' title='"+message.data+",点击下载'></img></a> </p></div>";
}

function inviteHandler(message){
	var m = "用户"+message.send+"邀请您进入房间"+message.data+",是否同意进入房间？";
	var comfirm = confirm(m);
	if(comfirm==true){
		addTabFunction2(message.data);
		enterRoom(message.data);
	}
		
	
}

function addTabFunction2(roomName){
	   
   
    var tabtitle = roomName;
    
    //标签页格式
    tabTemplate = "<li id="+tabtitle+"><a href='#{href}' data-toggle='tab' >#{tabtitle}<span class='glyphicon glyphicon-remove' style='cursor:pointer;' onclick='parent().id'></span></a></li>";

    //ul id mytab  div id screen
   
    var div_pane = tabtitle+'_screen';
    document.getElementById("screen").innerHTML+="<div id='"+div_pane +"' class='tab-pane'></div>"
    document.getElementById("mytab").innerHTML+="<li  id='"+ tabtitle +"'><a href='#"+div_pane + "' data-toggle='tab' onclick='switchRoom(parentElement.id)' >"+tabtitle+"<span class='glyphicon glyphicon-remove' style='cursor:pointer;' onclick='removeTab(parentElement.parentElement.id),outRoom(parentElement.id)'></span></a></li>";
    
    
    document.getElementById(div_pane).scrollIntoView(false);
    
}

/**
 * 显示进入房间提示
 * @param message
 * @returns
 */
function enterNoticeHandler(message){
	var screenName = message.receive+"_screen";
	var screen = document.getElementById(screenName);

	screen.innerHTML += "<i style='position:abslute;margin-left:200px;'>"+message.data+"进入当前房间</i><br/>"
}

/**
 * 退出房间提示
 * @param {*} message 
 */
function outNoticeHandler(message){
	var screenName = message.receive+"_screen";
	var screen = document.getElementById(screenName);

	screen.innerHTML += "<i style='position:abslute;margin-left:200px;'>"+message.data+"退出当前房间</i><br/>"
}

/**
 * 当接收到信息时，将消息添加到对应room对象的roomRecord中
 * @param {Message} message 
 */
function updateRoomRecord(message){
	var receive = message.receive;
	var room = roomManger.get(receive);

	room.roomRecord.push(message);
}

/**
 * 将滚动条移动到最下
 * @param {*} message 
 */
function screenRoll(){
	
	//var screenName = message.receive+"_screen";
	var screen = document.getElementById("screen");
	//console.log("gundong"+screenName);
	screen.scrollTop = screen.scrollHeight;
}