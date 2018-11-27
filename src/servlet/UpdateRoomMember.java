package servlet;
/**
 * 获取房间信息
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import toonoisy.Message;
import toonoisy.Room;
import toonoisy.RoomManger;

/**
 * Servlet implementation class RoomMessage
 */
@WebServlet("/UpdateRoomMember")
public class UpdateRoomMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRoomMember() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String roomName = request.getParameter("roomName");
		
		PrintWriter out = response.getWriter();
		
		out.print(this.getRoomMember(roomName));
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	/**
	 * 
	 * @return String 返回封装房间成员列表的JSON字符串
	 */
	private String getRoomMember(String roomName) {
		System.out.println("getRoomMember"+roomName);
		
		RoomManger manger = RoomManger.getInstance();
		Room room = manger.get(roomName);
		List MembersInfo = room.getMembersInfo();
		
		String information = JSON.toJSONString(MembersInfo, SerializerFeature.WriteNullStringAsEmpty);
		
		Message m = new Message();
		m.setType("roomMember");
		m.setSend("system");
		m.setReceive(roomName);
		m.setData(information);
		
		
		String message = JSON.toJSONString(m, SerializerFeature.WriteNullStringAsEmpty);
		
		return message;
		
	}
	
	

}
