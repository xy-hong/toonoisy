package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import DAO.Impl.UserDAOImpl;
import entity.User;

public class Login extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("username_signup");
		String psd = req.getParameter("password_signup");
		
		UserDAO userdao = new UserDAOImpl();
		User user = userdao.getDO(name);
		
		if(user==null) {
			out.println("用户不存在");
		}else if(!(user.getPassword().equals(psd))) {
			out.println("账号密码错误");
		}else if(user.getId().equals(name)&&user.getPassword().equals(psd)) {
			
			out.print("登陆成功");
			
			//req.getRequestDispatcher("").forward(req, resp);
		}
		
	}
	
	

}
