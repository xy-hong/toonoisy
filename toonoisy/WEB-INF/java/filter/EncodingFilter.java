package filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncodingFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Myrequest myRequest=new Myrequest((HttpServletRequest)request);
		chain.doFilter(myRequest, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
class Myrequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	public Myrequest(HttpServletRequest request) {
		super(request);
		this.request=request;
	}
	public String getParameter(String name){
		try {
			String value =this.request.getParameter(name);
			if("GET".equals(this.request.getMethod())){
				value=new String(value.getBytes("iso-8859-1"),"utf-8");
			}
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}