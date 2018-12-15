package service;

import java.util.Set;

import com.alibaba.fastjson.JSON;

import toonoisy.Message;
import toonoisy.Online;
import toonoisy.Room;
import toonoisy.RoomManger;

/*
 * 此类包含关于房间的功能
 */
public class RoomService {
	
	private String userId;
	private Online user;
 	
	private String roomName;
	private Room room;
	
	public RoomService(String roomName,String userId) {
		
		this.userId = userId;
		this.user = Online.getOnlinePool().get(userId);
		
		this.roomName = roomName;
		//获得房间实例，如果房间不存在则为null
		this.room = RoomManger.getInstance().get(roomName);   
	}
	
	public boolean createRoom() {
		if(room!=null) {
			return false;
		}else {
			this.room = new Room(roomName);
			this.room.enterRoom(userId);
			
			RoomManger.getInstance().put(roomName, room);
			
			addUserRooms();
			enterNotice();
			updateNotice();
			
			return true;
		}
	}
	
	public boolean enterRoom() {
		if(room==null) {
			return false;
		}else {
			this.room.enterRoom(userId);
			
			addUserRooms();
			enterNotice();
			updateNotice();
			
			return true;
		}
	}
	
	public boolean outRoom() {
		if(room==null) {
			return false;
		}else {
			room.outRoom(userId);
	
			deleteUserRooms();
			outNotice();
			updateNotice();
			
			return true;
		}
	}
	
	
	private void enterNotice() {
		Message message = new Message();
		message.setType("enterNotice");
		message.setReceive(roomName);
		message.setData(userId);
		
		room.sendEveryone(message);
	}
	
	private void outNotice() {
		Message message = new Message();
		message.setType("outNotice");
		message.setReceive(roomName);
		message.setData(userId);
		
		room.sendEveryone(message);
	}
	
	private void updateNotice() {
		Message message = new Message();
		message.setType("updateNotice");
		message.setReceive(roomName);
		message.setData(JSON.toJSONString(room.getRoomInfo()));
		
		room.sendEveryone(message);
	}
	
	/**
	 * 更新用户保存的已进入房间列表
	 */
	private boolean addUserRooms() {
		if(room!=null && user!=null) {
			 Set<String> set = user.getRooms();
		      set.add(roomName);
		      System.out.println("用户添加了"+roomName);
		      System.out.println("用户已进入房间"+set);
		      return true;
		}else {
			return false;
		}
     
	}
	
	private boolean deleteUserRooms() {
		if(room!=null && user!=null) {
			 Set<String> set = user.getRooms();
		      set.remove(roomName);
		      System.out.println("用户删除了"+roomName);
		      return true;
		}else {
			return false;
		}
    
	}
	
}
