/*
    此文件包含了roomManger的集合，并初始化了public房间
 */

var currentRoomName = "public";
var roomManger = new Map();

function Room(roomName,roomNumber,roomMember){
    this.roomName = roomName;
    this.roomNumber = roomNumber;
    this.roomMember = roomMember;

    this.roomRecord = new Array();
}

var publicRoom = new Room("public",0,null);

roomManger.set("public",publicRoom);

