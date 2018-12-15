package toonoisy;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
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
import service.RoomService;

@ServerEndpoint("/Online/{id}")
public class Online {
	private static ConcurrentHashMap<String, Online> OnlinePool = new ConcurrentHashMap<String, Online>();
	private Set<String> rooms = new TreeSet<String>();  
	private Session session;
	private UserInfo info;
	
	
	public UserInfo getInfo() {
		return this.info;
	}
	
	public String getId() {
		return info.getId();
	}
	
	@OnOpen
	public void onOpen(@PathParam("id") String id, Session session) {
		this.session = session;
		
		UserInfDAOImpl dao = new UserInfDAOImpl();
		this.info = dao.getDO(id);
		
		this.init();
	}
	
	@OnMessage
	public void onMessage(String data) {
		new MessageHandler(session).handle(data);
	}
	
	@OnClose
	public void onClose(CloseReason reason) {
		System.out.println("用户退出，准备释放房间");
		release();
		try {
			session.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("连接断开");
	}
	
	@OnError
	public void onError(Throwable throwable,Session session) {
		System.out.println("用户发生错误，准备释放房间");
		release();
		System.out.println("发生错误");
		throwable.printStackTrace();
	}
	
	public void send(Message message) {

		String s = JSON.toJSONString(message,SerializerFeature.WriteNullStringAsEmpty);
		this.send(s);
	}
	
	public void send(String JSONString) {
		System.out.println("Online.send()----"+JSONString);
		if(session.isOpen()) {
			try {
				session.getBasicRemote().sendText(JSONString);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void init() {
		OnlinePool.put(info.getId(), this);
		new RoomService("public", info.getId()).enterRoom();
	}
	
	public  static ConcurrentHashMap<String, Online> getOnlinePool() {
		return OnlinePool;
	}
	
	public Set<String> getRooms() {
		return this.rooms;	
	}
	
	public void setRooms(Set<String> rooms) {
		this.rooms = rooms;
	}
	
	public void release() {
		String userId = info.getId();
		Object[] r =  rooms.toArray();
		
		for(int i=0; i<r.length; i++) {
			new RoomService(r[i].toString(), userId).outRoom();
		}
		
		/*for (String roomName : rooms) {
			System.out.println("释放房间"+roomName);
			new RoomService(roomName, userId).outRoom();
		}*/
		
		System.out.println("从在线池移除用户");
		OnlinePool.remove(userId);
	}
}
