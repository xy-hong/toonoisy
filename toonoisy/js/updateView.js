/**
 * 更新房间成员信息
 */
function updateRoomMemberView(){
	var room = roomManger.get(currentRoomName);
	var members = room.roomMember;
    var roomMemberNode = document.getElementById("roomMember");
    
    roomMemberNode.innerHTML ="";
	for(var i=0; i<members.length; i++){
		roomMemberNode.innerHTML += "<tr><td>"+members[i].id+"</td><td>"+members[i].name+"</td></tr>";
	}
}

/** 
* 更新左上角房间信息
*/
function updateRoomInfoView(){
    var currentRoom = roomManger.get(currentRoomName);
    
    document.getElementById("roomName").innerText = currentRoom.roomName;
   document.getElementById("roomNumber").innerText = currentRoom.roomNumber;

	//roomName.innerText = currentRoom.roomName;
	//roomNumber.innerHTML = currentRoom.roomNumber;
}
