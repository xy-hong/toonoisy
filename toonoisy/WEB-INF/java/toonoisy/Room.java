package toonoisy;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import entity.UserInfo;



public class Room extends ConcurrentHashMap<String, Online>{
	/**
	 * 房间信息
	 */
	private RoomInfo roomInfo = new RoomInfo();
	
	/**
	 * 构造函数
	 * @param roomName 房间名
	 */
	public Room(String roomName) {
		this.roomInfo.setRoomName(roomName);
	}
	
	public String getRoomName() {
		return roomInfo.getRoomName();
	}
	
	public RoomInfo getRoomInfo() {
		return roomInfo;
	}
	
	/**
	 * 
	 * @param userId 用户id
	 * @param user 用户的online对象
	 * @return
	 */
	public boolean enterRoom(String userId,Online user) {
		if(this.get(userId)!=null) {
			return false;
		}else {
			this.put(userId, user);
			roomInfo.setNumber(roomInfo.getNumber()+1);
			List<UserInfo> l = roomInfo.getMembersInfo();
			l.add(user.getInfo());
			return true;
		}
	}
	
	/**
	 * 内部调用了另一个enterRoom
	 */
	public boolean enterRoom(String userId) {
		Online user = Online.getOnlinePool().get(userId);
		return enterRoom(userId,user);
	}
	
	/**
	 * 
	 * @param userId 用户id
	 * @return
	 */
	public boolean outRoom(String userId) {
		if(this.remove(userId)!=null) {
			roomInfo.setNumber(roomInfo.getNumber()-1);
			
			UserInfo userInfo = Online.getOnlinePool().get(userId).getInfo();
			roomInfo.getMembersInfo().remove(userInfo);
			
			if(this.isEmpty() && !this.getRoomInfo().getRoomName().equals("public")) {
				RoomManger.getInstance().remove(roomInfo.getRoomName());
			}
			
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param message 信息
	 */
	public void sendEveryone(Message message) {
		Collection<Online> c = this.values();
		for (Online online : c) {
			online.send(message);
		}
	}
	
	/**
	 * 
	 * @param message 信息
	 */
	public void sendEveryone(String message) {
		Collection<Online> c = this.values();
		for (Online online : c) {
			online.send(message);
		}
	}
	
}
