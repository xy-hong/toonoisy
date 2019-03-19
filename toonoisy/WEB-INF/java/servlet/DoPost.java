package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import DAO.Impl.UserDAOImpl;
import entity.User;
import login.Post;
import util.GetEndTime;

@WebServlet("/DoPost")
public class DoPost extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		HttpSession session = req.getSession();
		String email = req.getParameter("ownemail");
		String endtime = new GetEndTime().getendtime();
		
		UserDAO dao = new UserDAOImpl();
		User user = new User();
		user = dao.getDO(email);
		if(user==null) {
			resp.getWriter().print("�����ڸ��û�������������");
		}else{
		
		String x = "";
		for(int i=0;i<6;i++) {
		x += (int)(Math.random()*10);
		}
		
		new Post("smtp.qq.com",email,"..//..//host.properties","��toonosiy������","���ã�������֤����"+ x +"����֤����Ч��Ϊ10���ӣ��뾡������") ;
		session.setAttribute("passwordforgetcode", x);
		session.setAttribute("passwordforgetcodeendtime", endtime);
		resp.getWriter().print("��ȡ��֤��ɹ�������ʮ����������");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
