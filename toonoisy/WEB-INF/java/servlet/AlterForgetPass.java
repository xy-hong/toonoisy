package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import DAO.Impl.UserDAOImpl;
import entity.User;
import util.MD5Util;

@WebServlet("/AlterForgetPass")
public class AlterForgetPass extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
	
		String email = req.getParameter("ownemail");
		String newpass = req.getParameter("newpass");
		String afternewpass = MD5Util.md5(newpass);
		UserDAO dao = new UserDAOImpl();
		User user = new User();
		user = dao.getDO(email);
		if(MD5Util.md5(newpass).equals(user.getPassword())) {
			resp.getWriter().print("�����������µ�����");
		}else {
			user.setPassword(afternewpass);
			dao.update(user);
			resp.getWriter().print("�ɹ��޸�");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
