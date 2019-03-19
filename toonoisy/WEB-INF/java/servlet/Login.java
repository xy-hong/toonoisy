package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.MyTimerTask;
import DAO.UserDAO;
import DAO.Impl.LockDaoImpl;
import DAO.Impl.UserDAOImpl;
import entity.LockUser;
import entity.User;

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
		LockDaoImpl lk=new LockDaoImpl();
		if(user==null) {
			out.print("账号或者密码错误");
			out.flush();
		}else if(!user.getPassword().equals(password)) {
			if(lk.getLk(user.getId()).getCount()>=3){
				out.print("登陆失败次数超出限制，请10分钟后再尝试");
				out.flush();
			}
			else{
				int count=lk.getLk(user.getId()).getCount();
				if(count<3){
					count++;
					lk.update(user.getId(), count);
				}
				if(count>=1){
					//lock 记录最后一次时间
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String now=sdf1.format(new Date());
					lk.update(user.getId(), count);
					lk.updateTime(user.getId(), now);
				}
				out.print("账号或者密码错误");
				out.flush();
			}
		}else if(user.getPassword().equals(password)) {
			if(lk.getLk(user.getId()).getCount()<3){
			lk.update(user.getId(), 0);
			lk.updateTime(user.getId(), null);
			out.print("登录成功");
			HttpSession session=request.getSession(true);
			session.setAttribute("user", user);			
			}
			else{
				out.print("登陆失败次数超出限制，请10分钟后再尝试");
			}
			//response.sendRedirect("ChatMain.html?username="+username);
		}
	}

}