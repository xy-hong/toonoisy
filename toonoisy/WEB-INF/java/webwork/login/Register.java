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

import DAO.UserDAO;
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
		
		String account = req.getParameter("username_signup");  //�û�ע����
		String psd = req.getParameter("password_signup");
		String code = req.getParameter("verification");
		
		User user;
		UserDAO dao = new UserDAOImpl();
		
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
			out.println("��֤����ڣ������»�ȡ������");
		}else if(!(correctcode.equals(code))) {
			out.println("��֤�벻��ȷ�������»�ȡ������");
		}else if(correctcode.equals(code)) {
			user = new User();
			user.setId(account);
			user.setPassword(psd);
			dao.insert(user);
			out.println("ע��ɹ�");
			
			resp.sendRedirect("..//..//..//webui//SignInAndSignUp.html");
			
		}
		
		
		
		resp.sendRedirect(req.getContextPath()+""); //������ҳ�ļ���+��׺
	}
	

}
