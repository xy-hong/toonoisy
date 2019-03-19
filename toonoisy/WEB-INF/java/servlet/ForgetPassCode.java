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


@WebServlet("/ForgetPassCode")
public class ForgetPassCode extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		String rightcode = (String)session.getAttribute("passwordforgetcode");
		String inputcode = request.getParameter("code");
		String endtime = (String)session.getAttribute("passwordforgetcodeendtime");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date codeendtime = null;
		try {
			codeendtime = df.parse(endtime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date nowtime = new Date();
		
		if(nowtime.compareTo(codeendtime)>0) {
			response.getWriter().print("验证码过期，请重新获取并输入");
		}else if(!rightcode.equals(inputcode)){
			response.getWriter().print("输入验证码错误，请重新点击获取验证码");
		}else if(rightcode.equals(inputcode)){
				response.getWriter().print("success");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
