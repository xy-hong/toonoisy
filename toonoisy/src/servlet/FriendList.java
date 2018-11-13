package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import DAO.FriendDAO;
import DAO.UserInfDAO;
import DAO.Impl.FriendDAOImpl;
import DAO.Impl.UserInfDAOImpl;
import entity.User;
import entity.UserInfo;
import entity.WebFriend;

@WebServlet("/FriendList")
public class FriendList extends HttpServlet{


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		
		String id = req.getParameter("username");
		User u = new User();
		u.setId(id);
		
		//UserDAO dao = new UserDAOImpl();
		FriendDAO dao = new FriendDAOImpl();
		//UserInfo userinfo = dao.getDO(id);
		
		
		dao.setTname(u);
		List<WebFriend> list = dao.listDO();
		
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(list,SerializerFeature.WriteNullStringAsEmpty));
	}
	
	

}
