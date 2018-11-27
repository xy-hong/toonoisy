package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import toonoisy.Message;
import toonoisy.Room;
import toonoisy.RoomManger;

/**
 * Servlet implementation class updateRoomNumber
 */
@WebServlet("/UpdateRoomNumber")
public class UpdateRoomNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRoomNumber() {
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
		
		out.print(this.getRoomNumber(roomName));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private String getRoomNumber(String roomName) {
		System.out.println("getRoomNumber"+roomName);
		
		RoomManger manger = RoomManger.getInstance();
		Room room = manger.get(roomName);
		
		String size =  Integer.toString(room.size());
		
		Message m = new Message();
		m.setData(size);
		m.setReceive(roomName);
		m.setType("roomNumber");
		
		String message = JSON.toJSONString(m,SerializerFeature.WriteNullStringAsEmpty);
		
		return message;
		
	}
}
