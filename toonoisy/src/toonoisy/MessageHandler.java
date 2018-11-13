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
			default: break;
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
	
	
	
	
	
}
