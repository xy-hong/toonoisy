package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RoomService;
import toonoisy.RoomManger;

/**
 * Servlet implementation class CreateRoom
 */
@WebServlet("/CreateRoom")
public class CreateRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRoom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		String roomName = request.getParameter("room");
		
		System.out.println(username+"请求进入房间"+roomName);
		
		if(new RoomService(roomName, username).createRoom()==true) {
			response.getWriter().print("创建成功");
		}else {
			response.getWriter().print("创建失败");
		}
		
		System.out.println("创建房间,房间管理"+RoomManger.getInstance());
		System.out.println("创建房间,当前房间"+RoomManger.getInstance().get(roomName));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
