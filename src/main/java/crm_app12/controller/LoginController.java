package crm_app12.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app12.UserEntity;
import crm_app12.services.LoginServices;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	private LoginServices loginServices = new LoginServices();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] listCookies = req.getCookies();
		String email = "";
		String password = "";
		
		//Duyệt qua từng cookie và kiếm cookie có tên ứng với tên đã đặt trước đó để lấy giá trị
		if(listCookies != null) {
			for (Cookie cookie : listCookies) {
				if(cookie.getName().equals("email")) {
					email = cookie.getValue();
				}
			
				if(cookie.getName().equals("password")) {
					password = cookie.getValue();
				}
			
			}
		}
		req.setAttribute("email", email);
		req.setAttribute("password", password);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		System.out.println("goi phuong thuc post thanh cong " + email + " " + password);
		loginServices.getByUsernamePassword(email, password, remember, resp);
	}
}
