package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import DAO.Impl.UserDAOImpl;
import entity.User;
import util.MyEmail;

/**
 * Servlet implementation class GetEmailCode
 */
@WebServlet("/GetEmailCode")
public class GetEmailCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEmailCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username_signup");
		
		//response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		UserDAO dao = new UserDAOImpl();
		User user = dao.getDO(username);
		
		if(user!=null) {
			out.print("账号已经被使用");
			out.flush();
			System.out.println("账号已经被使用");
		}else {
			Random random = new Random();
			StringBuffer code = new StringBuffer(6);
			for(int i=0; i<6; i++) {
				code.append(random.nextInt(10));
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("code", code.toString());
			
			this.sendEmail(username, code.toString());
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void sendEmail(String toEmail,String text) {
		//可以改成读取配置文件的形式
		try {
			
			MyEmail email = new MyEmail("1015683970@qq.com", "gajqmmvsocvzbbff");
			email.setTo(toEmail);
			email.setFrom("1015683970@qq.com");
			email.setSubject("toonoisy网站注册验证码");
			text+=" 是您注册用的验证码";
			email.setText(text, "utf-8");
			email.send();
			
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
