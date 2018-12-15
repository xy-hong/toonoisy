package toonoisy;

import java.util.concurrent.ConcurrentHashMap;

public class RoomManger extends ConcurrentHashMap<String, Room>{
	
	private static RoomManger manger = new RoomManger(); 
	
	static {
		Room publicRoom = new Room("public");
	
		manger.put("public", publicRoom);	
	}
	
	public static RoomManger getInstance() {
		return manger;
	}
	
}
