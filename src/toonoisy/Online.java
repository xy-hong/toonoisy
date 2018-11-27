package toonoisy;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import DAO.UserDAO;
import DAO.Impl.UserInfDAOImpl;
import entity.UserInfo;

@ServerEndpoint("/Online/{id}")
public class Online {
	private static ConcurrentHashMap<String, Online> OnlinePool = new ConcurrentHashMap<String, Online>();
	private List rooms;
	private Session session;
	private String id;
	private UserInfo info;
	
	/**
	 * 此方法仅用于测试
	 * @param id
	 */
	/*public Online(String id) {
		this.id = id;
	}*/
	
	public UserInfo getInfo() {
		return this.info;
	}
	
	public String getId() {
		return id;
	}
	
	/**
	 * 将用户加入公共聊天室，加入online,向前端发送
	 */
	@OnOpen
	public void onOpen(@PathParam("id") String id, Session session) {
		this.id = id;
		//System.out.println(this.getClass().getName()+":"+id);
		this.session = session;
		
		UserInfDAOImpl dao = new UserInfDAOImpl();
		this.info = dao.getDO(id);
		
		this.init();
		
	}
	
	/*@OnOpen
	public void onOpen(Session session) {
		System.out.println("连接成功");
	}*/
	
	@OnMessage
	public void onMessage(String data) {
		System.out.println("websockt"+data);
		new MessageHandler(session).handle(data);
	}
	
	@OnClose
	public void onClose(CloseReason reason) {
		OnlinePool.remove(this);
		RoomManger.getInstance().get("public").remove(id);
		System.out.println("连接断开");
	}
	
	@OnError
	public void onError(Throwable throwable,Session session) {
		System.out.println("发生错误");
		throwable.printStackTrace();
	}
	
	public void send(Message message) {
		String s = JSON.toJSONString(message);
		this.send(s);
	}
	
	public void send(String JSONString) {
		try {
			session.getBasicRemote().sendText(JSONString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 对用户进行一些初始化
	 */
	public void init() {
		// 加入public房间
		OnlinePool.put(id, this);
		Room room = RoomManger.getInstance().get("public");
		room.put(id, this);
		
		/**
		 * 封装进入房间提醒
		 */
		Message enterMessage = new Message();
		enterMessage.setType("enterNote");
		enterMessage.setReceive("public");
		enterMessage.setSend("system");
		enterMessage.setData(this.id);
		String JSONEnterNote = JSON.toJSONString(enterMessage);
		
		
		/**
		 * 发送房间成员的名字
		 */
		/*List nameList = room.getMembersNames();
		String memberNames = JSON.toJSONString(nameList);
		//封装房间成员信息并转化为JSON
		Message memberMessage = new Message();
		memberMessage.setType("roomMember");
		memberMessage.setReceive("public");
		memberMessage.setData(memberNames);
		String JSONRoomMessage = JSON.toJSONString(memberMessage);*/
		
		/**
		 * 封装房间成员的信息
		 */
		List InfoList = room.getMembersInfo();
		String memberInfo = JSON.toJSONString(InfoList, SerializerFeature.WriteNullStringAsEmpty);
		Message memberMessage = new Message();
		memberMessage.setType("roomMember");
		memberMessage.setReceive("public");
		memberMessage.setData(memberInfo);
		String JSONRoomMessage = JSON.toJSONString(memberMessage);
		
		/**
		 * 封装房间人数信息
		 */
		Message numberMessage = new Message();
		numberMessage.setType("roomNumber");
		numberMessage.setReceive("public");
		String size = Integer.toString(room.size());
		numberMessage.setData(size);
		String JSONNumberMessage = JSON.toJSONString(numberMessage);
		
		
		//向房间所有成员发送信息
		Collection<Online> c  = room.values();
		for (Online people : c) {
			people.send(JSONRoomMessage);
			people.send(JSONNumberMessage);
			people.send(JSONEnterNote);
		}
		
	}
	
	
	public  static ConcurrentHashMap<String, Online> getOnlinePool() {
		return OnlinePool;
	}
	
	public List getRooms() {
		return this.rooms;
	}
	
	/*
	 * 
	 */
	/*public boolean exitRoom(String roomName ) {
		
	}*/

}
