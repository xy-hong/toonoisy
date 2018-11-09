
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FriendDAO;
import DAO.Impl.FriendDAOImpl;
import entity.User;

@WebServlet("/CancelFollowFriends")
public class CancelFollowFriends extends HttpServlet{


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String userid =req.getParameter("username");
		String CancelFollowFriendid = req.getParameter("friendid");
		
		User user = new User();
		FriendDAO frienddao = new FriendDAOImpl();
		
		user.setId(userid);
		frienddao.setTname(user);
		frienddao.delete(CancelFollowFriendid);
		
		resp.getWriter().print("取消关注成功");
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	

}