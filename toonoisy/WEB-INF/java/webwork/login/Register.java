package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FriendDAO;
import DAO.RecordDAO;
import DAO.UserDAO;
import DAO.Impl.FriendDAOImpl;
import DAO.Impl.RecordDAOImpl;
import DAO.Impl.UserDAOImpl;
import entity.User;

public class Register extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		
		
		String account = req.getParameter("username_signup");  //用户注册名
		String psd = req.getParameter("password_signup");
		String code = req.getParameter("verification");
		boolean exist = false;
		
		
		User user = null;
		RecordDAO recorddao = null;
		FriendDAO frienddao = null;
		UserDAO userdao = new UserDAOImpl();
		
		
		User userExist = userdao.getDO(account);
		
		if(userExist!=null) {
			exist = true;
			recorddao = new RecordDAOImpl();
			frienddao = new FriendDAOImpl();
		}
		
		
		
		
		HttpSession session = req.getSession();
		String correctcode = (String)session.getAttribute("code");
		String codeendtime = (String)session.getAttribute("codeendtime");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endtime = null;
		try {
			endtime = df.parse(codeendtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nowtime = new Date();
		
		
		
		if(nowtime.compareTo(endtime)>0) {
			out.println("验证码过期，请重新获取并输入");
		}else if(!(correctcode.equals(code))) {
			out.println("验证码不正确，请重新获取并输入");
		}else if(correctcode.equals(code)) {
			if(!exist) {
			user = new User();
			user.setId(account);
			user.setPassword(psd);
			userdao.insert(user);
			recorddao.setTname(user);//......
			frienddao.setTname(user);//......
			recorddao.createTable();
			frienddao.createTable();
			out.println("注册成功");
			resp.sendRedirect("webui/SignInAndSignUp.html");
			}
			
		}
	}
	

}
