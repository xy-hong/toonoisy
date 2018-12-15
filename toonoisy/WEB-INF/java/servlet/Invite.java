package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import toonoisy.Message;
import toonoisy.Online;
import toonoisy.Room;
import toonoisy.RoomManger;

/**
 * Servlet implementation class Invite
 */
@WebServlet("/Invite")
public class Invite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Invite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		String roomName = request.getParameter("roomName");
		String receive = request.getParameter("receive");
		
		Room room = RoomManger.getInstance().get(roomName);
		Online user = Online.getOnlinePool().get(username);
		Online receiveUser = Online.getOnlinePool().get(receive);
		
		if(receiveUser==null) {
			response.getWriter().print("被邀请用户不在线");
			return;
		}
		
		if(room!=null && room.get(receive)!=null ) {
			response.getWriter().print("被邀请用户已经在房间内，无需重复邀请");
			return;
		}
		
		if(user!=null && receiveUser!=null) {
			Message message = new Message();
			message.setType("invite");
			message.setSend(username);
			message.setCreateTime(null);
			message.setReceive(receive);
			message.setData(roomName);
			
			
			receiveUser.send(message);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
