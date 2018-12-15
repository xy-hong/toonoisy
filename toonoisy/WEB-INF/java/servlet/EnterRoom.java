package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RoomService;
import toonoisy.Online;
import toonoisy.RoomManger;

/**
 * Servlet implementation class EnterRoom
 */
@WebServlet("/EnterRoom")
public class EnterRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterRoom() {
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
		String room = request.getParameter("room");
		
		System.out.println(username+"请求进入房间"+room);
		
		if(new RoomService(room, username).enterRoom()) {
			response.getWriter().print("进入房间");
		}else {
			response.getWriter().print("无法进入房间");
		}
		
		System.out.println("当前房间管理"+RoomManger.getInstance());
		System.out.println("当前房间"+RoomManger.getInstance().get(room));
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
