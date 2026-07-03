package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName = "authenFilter", urlPatterns = {"/user", "/user-add", "/role", "/role-add", "/job", "/job-add", "/task", "/task-add"})
public class AuthenticationFilter extends HttpFilter {

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		Cookie[] cookies = req.getCookies();
		String role = null;
		if(cookies != null) { 
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("role")){
					role = cookie.getValue();
				}
			}
		}
		
		if(role != null) {
			String path = req.getServletPath();
			if("ADMIN".equals(role) && ("/user-add".equals(path)|| "/role-add".equals(path)|| "/role".equals(path) ||"/job".equals(path)||"/job-add".equals(path)||"/task".equals(path)||"/task-add".equals(path))) {
				chain.doFilter(req, res);
			}else if("/user".equals(path)) {
				chain.doFilter(req, res);
			}else {
				res.sendRedirect(req.getContextPath() + "/login");	
			}
			
		}else {
			res.sendRedirect(req.getContextPath() + "/login");
			}
		
	}
		
	
}


