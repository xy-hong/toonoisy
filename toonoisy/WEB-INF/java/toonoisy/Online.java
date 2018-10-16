package toonoisy;

import java.io.IOException;
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

@ServerEndpoint("/Online/{id}")
public class Online {
	private static ConcurrentHashMap<String, Online> OnlinePool = new ConcurrentHashMap<String, Online>();
	private Session session;
	private String id;
	
	/**
	 * 此方法仅用于测试
	 * @param id
	 */
	/*public Online(String id) {
		this.id = id;
	}*/
	
	public String getId() {
		return id;
	}
	
	/**
	 * 将用户加入公共聊天室，加入online,向前端发送
	 */
	@OnOpen
	public void onOpen(@PathParam("id") String id, Session session) {
		this.id = id;
		System.out.println(id);
		this.session = session;
		OnlinePool.put(id, this);
		RoomManger.getInstance().get("public").put(id, this);
	}
	
	/*@OnOpen
	public void onOpen(Session session) {
		System.out.println("连接成功");
	}*/
	
	@OnMessage
	public void onMessage(String data) {
		new MessageHandler(session).handle(data);
	}
	
	@OnClose
	public void onClose(CloseReason reason) {
		OnlinePool.remove(this);
		RoomManger.getInstance().get("public").remove(id);
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

}
