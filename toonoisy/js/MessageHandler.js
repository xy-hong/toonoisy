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
		  
		  case "invite" : inviteHandler(message);break;
    	
  		default:
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
    document.getElementById("mytab").innerHTML+="<li  id='"+ tabtitle +"'><a href='#"+div_pane + "' data-toggle='tab' onclick='changeRoomName(parentElement.id)' >"+tabtitle+"<span class='glyphicon glyphicon-remove' style='cursor:pointer;' onclick='removeTab(parentElement.parentElement.id)'></span></a></li>";
    
    
    document.getElementById(div_pane).scrollIntoView(false);
    
}

/**
 * 显示进入房间提示
 * @param message
 * @returns
 */
function enterNoteHandler(message){
	var screenName = message.receive+"_screen";
	var screen = document.getElementById(screenName);

	screen.innerHTML += "<i style='position:abslute;margin-left:200px;'>"+message.data+"进入当前房间</i><br/>"
}