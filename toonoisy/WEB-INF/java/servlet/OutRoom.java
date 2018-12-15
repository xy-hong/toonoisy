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
 * Servlet implementation class OutRoom
 */
@WebServlet("/OutRoom")
public class OutRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutRoom() {
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
		
		System.out.println(username+"请求退出房间"+roomName);
		
		if(new RoomService(roomName, username).outRoom()==true) {
			response.getWriter().print("退出房间");
		}else {
			response.getWriter().print("无法退出房间");
		}
		
		System.out.println("退出房间,房间管理"+RoomManger.getInstance());
		System.out.println("退出房间，当前房间"+RoomManger.getInstance().get(roomName));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
