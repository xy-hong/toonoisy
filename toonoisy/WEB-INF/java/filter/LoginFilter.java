package filter;

import java.io.IOException;
import entity.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		//System.out.println("abc");
		HttpServletRequest request=(HttpServletRequest)req;
		HttpServletResponse response=(HttpServletResponse) res;
		HttpSession session=request.getSession(false);
		if(session==null){
			//System.out.println("cdf");
			response.sendRedirect(request.getContextPath()+"/SignInAndSignUp.html");
		return;
		}
		else{
			//System.out.println("qwer");
			entity.User login=(entity.User)session.getAttribute("user");
			if(login==null){
				response.sendRedirect(request.getContextPath()+"/SignInAndSignUp.html");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
