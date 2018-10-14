package login;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetCode extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String postaddress = req.getParameter("username_signup");
		
		PrintWriter out = resp.getWriter();
		
		String endtime = new GetEndTime().getendtime();
		/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endtime;
		try {
			endtime = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		
		//生成验证码
		//String x = "" +(int)(Math.random()*1000000);
		String x = null;
		for(int i=0;i<6;i++) {
		x = "" + (int)(Math.random()*10);
		}
		
		Post p = new Post("smtp.qq.com",postaddress,"..\\..\\host.properties","【toonosiy官网】","您好，您的验证码是"+ x +"，验证码有效期为10分钟，请尽快输入") ;
		
		HttpSession session = req.getSession();
		session.setAttribute("code", x);
		session.setAttribute("codeendtime",endtime);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	

}
