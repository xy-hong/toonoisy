package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FriendDAO;
import DAO.UserDAO;
import DAO.Impl.FriendDAOImpl;
import DAO.Impl.UserDAOImpl;
import entity.User;
import entity.WebFriend;

@WebServlet("/FollowFriends")
public class FollowFriends extends HttpServlet{


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String userid =req.getParameter("username");
		String FollowFriendid = req.getParameter("friendid");
		String FollowFriendnick =req.getParameter("usernick");
		
		UserDAO userdao = new UserDAOImpl();
		
		if("".equals(FollowFriendid)) {
			resp.getWriter().print("好友id不能为空，关注失败");
			}else if(userdao.getDO(FollowFriendid)==null) {
				resp.getWriter().print("用户不存在，请重新输入");
			}else if(FollowFriendid.equals(userid)){
				resp.getWriter().print("不能关注自己，请重新输入");
			}else {
					User user = new User();
					user.setId(userid);
			
					WebFriend friend = new WebFriend();
					FriendDAO frienddao = new FriendDAOImpl();
					frienddao.setTname(user);
					List<WebFriend> friends = frienddao.listDO();
					
					//todo 判断是否已经位于好友列表，是否昵称为null
					
					Iterator<WebFriend> iterator =friends.iterator();
					boolean exist = false;
					while(iterator.hasNext()) {
						if(FollowFriendid.equals(iterator.next().getFriend_id())) {
							exist = true;
					}
					}
					 if(exist) {
						resp.getWriter().print("好友已存在，关注失败");
					 }else {
						friend.setFriend_id(FollowFriendid);
						friend.setFriend_nick(FollowFriendnick);
						frienddao.insert(friend);
						
						resp.getWriter().print("关注成功");
					 }
			}
			
		}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	

}
