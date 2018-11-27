package toonoisy;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

import javax.websocket.Session;

import com.alibaba.fastjson.JSON;

public class MessageHandler{
	private Session session;
	
	public MessageHandler(Session session) {
		this.session = session;
	}
	
	public void handle(String josn ) {
		Message message = JSON.parseObject(josn, Message.class);
		System.out.println("message的方法"+message.toJSONString());
		String type = message.getType();
		
		switch(type) {
			case "text": this.textHandle(message); break;
			
			case "createRoom": createRoomHandler(message);break;
			
			case "enterRoom" : enterRoomHandler(message); break;
			
			case "invite" : inviteHandler(message); break;
			
			default: break;
		}
	}
	
	
	/**
	 * 用户创建房间
	 * @param message
	 */
	private void createRoomHandler(Message message) {
		System.out.println("进行创建房间");
		String userID = message.getSend();
		String roomName = message.getData();
		
		Online user = Online.getOnlinePool().get(userID);
		
		RoomManger manger = RoomManger.getInstance();
		Room room = new Room(roomName);
		room.put(userID, user);
		manger.put(roomName, room);
		
		System.out.println(manger);
		System.out.println(manger.get(roomName));
		
		
		Message enterMessage = new Message();
		enterMessage.setType("enterNote");
		enterMessage.setReceive(roomName);
		enterMessage.setSend("system");
		enterMessage.setData(userID);
		String JSONEnterNote = JSON.toJSONString(enterMessage);
		
		Collection<Online> c = room.values();
		for (Online p : c) {
			p.send(JSONEnterNote);
		}
	}
	
	
	/*
	 * 将用户加入room集合
	 */
	private void enterRoomHandler(Message message) {
		System.out.println("调用MessageHandler 的 enterRoomHandler(Message message) ");
		String userID = message.getSend();
		String roomName = message.getData();
		
		Online o = Online.getOnlinePool().get(userID);
		Room room = new Room(roomName);
		room.put(userID, o);
		RoomManger.getInstance().put(userID, room);
		
		
	//	System.out.println(message.toJSONString());
		
		Message enterMessage = new Message();
		enterMessage.setType("enterNote");
		enterMessage.setReceive(roomName);
		enterMessage.setSend("system");
		enterMessage.setData(userID);
		String JSONEnterNote = JSON.toJSONString(enterMessage);
		
		Collection<Online> c = room.values();
		for (Online p : c) {
			p.send(JSONEnterNote);
		}
		
	}

	/**
	 * 
	 * @param message 接收的信息，并对其作出相应处理
	 */
	private void textHandle(Message message) {
		String roomName = message.getReceive();
		Room room =  RoomManger.getInstance().get(roomName);
		//遍历房间中的每个人，获取对应的socket,发送信息
		Collection<Online> c  = room.values();
		for (Online people : c) {
			people.send(message);
		}
	}
	
	
	private void inviteHandler(Message message) {
		String receive = message.getReceive();
		Online.getOnlinePool().get(receive).send(message);

	}
	
	
	
	
}
