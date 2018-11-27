package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import DAO.Impl.UserDAOImpl;
import util.MD5Util;
import entity.User;

@WebServlet("/AlterPassword")
public class AlterPassword extends HttpServlet{


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		String id = req.getParameter("username");
		String OldPass = req.getParameter("oldPassword");
		String NewPass = req.getParameter("newPassword");
		PrintWriter out = resp.getWriter();
		
		UserDAO dao = new UserDAOImpl();
		User user = new User();
		
		user = dao.getDO(id);
		
		//String AfterOldPass = util.MD5Util.md5(OldPass);
		//String AfterNewPass = util.MD5Util.md5(NewPass);
		if(user.getPassword().equals(OldPass)) {
			user.setPassword(NewPass);
			dao.update(user);
			out.print("修改密码成功");
		}else {
			out.print("旧密码不正确");
		}
		
		
		
	}
	
	

}
