package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CleanSession extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().invalidate();//clean session   CE3F9780D12980562905370DC0FCC0D2
		if(request.getCookies()!=null){
			Cookie[] cookies=request.getCookies();
			for(int i=0;i<cookies.length;i++){
				if(cookies[i].getName().toUpperCase().equals("JSESSIONID")){
					cookies[i].setMaxAge(0);//JSESSIONID has gone
				}
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
