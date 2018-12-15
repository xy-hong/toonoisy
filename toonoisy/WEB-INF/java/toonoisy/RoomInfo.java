package toonoisy;

import java.util.LinkedList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import entity.UserInfo;

public class RoomInfo {
	private String roomName = "";
	private int number;
	private List<UserInfo> membersInfo = new LinkedList<UserInfo>();
	
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<UserInfo> getMembersInfo() {
		return membersInfo;
	}
	public void setMembersInfo(List<UserInfo> membersInfo) {
		this.membersInfo = membersInfo;
	}
	
	public String toString() {
		return JSON.toJSONString(this,SerializerFeature.WriteNullStringAsEmpty);
	}
	
	
}
