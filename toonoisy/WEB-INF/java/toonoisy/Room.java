package toonoisy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import entity.UserInfo;

public class Room extends ConcurrentHashMap<String, Online>{
	private String roomName;
	private List membersNames;
	
	public Room(String roomName) {
		super();
		this.roomName = roomName;
	}
	
	/**import org.junit.After;
import org.junit.AfterClass;
	 * 
	 * @return String 房间名
	 */
	public String getRoomName() {
		return roomName;
	}
	
	/**
	 * @return List 获得房间成员的名字
	 */
	public List getMembersNames() {
		membersNames = Collections.list(this.keys());
		return membersNames;
	}
	
	@Override
	public Online put(String key, Online value) {
		super.put(key, value);
		return value;
	}
	
	@Override
	public Online remove(Object key){
		return super.remove(key);
	}
	
	public List getMembersInfo() {
		List<UserInfo> list = new LinkedList<UserInfo>();
		
		Collection<Online> c = this.values();
		for (Online online : c) {
			list.add(online.getInfo());
		}
		
		return list;
	}
}
