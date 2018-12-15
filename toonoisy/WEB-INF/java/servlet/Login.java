package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import DAO.Impl.UserDAOImpl;
import entity.User;
import toonoisy.Online;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("countNumber");
		String password = request.getParameter("password");
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//response.setContentType("text/html;charset=UTF-8");
		
		//用户不存在，用户密码错误，用户密码正确
		UserDAO dao = new UserDAOImpl();
		User user =  dao.getDO(username);
		
		if(user==null) {
			out.print("账号户不存在");
			out.flush();
		}else if(!user.getPassword().equals(password)) {
			out.print("账号或者密码错误");
			out.flush();
		}else if(user.getPassword().equals(password)) {
			out.print("登录成功");
			HttpSession session=request.getSession(true);
			session.setAttribute("user", user);			
			
			//response.sendRedirect("ChatMain.html?username="+username);
		}
	}

}
