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

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
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
		String username = request.getParameter("username_signup");
		String password = request.getParameter("password_signup");
		String code = request.getParameter("verification");
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
	//	response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		String realCode = (String)session.getAttribute("code");
		
		//是否存在同名,验证码是否正确
		UserDAO dao = new UserDAOImpl();
		User user = dao.getDO(username);
		
		if(user!=null) {
			out.print("用户已经存在");
			out.flush();
		}else if(code.equals(realCode)) {
			user = new User();
			user.setId(username);
			user.setPassword(password);
			//新建好友列表。消息表
			//------------待补充
			dao.insert(user);
			out.print("注册成功,可以去登录了");
			//response.setHeader("refresh", "3;url=SignInAndSignUp.html");
			out.flush();
		}
		
		session.setAttribute("code", null);
	}

}
