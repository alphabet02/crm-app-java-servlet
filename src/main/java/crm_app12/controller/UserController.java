package crm_app12.controller;
import crm_app12.entity.*;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app12.services.UserServices;

@WebServlet(name = "userController", urlPatterns = {"/user", "/user-add"})
public class UserController extends HttpServlet {
	
	private UserServices userServices = new UserServices();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//lấy đuognừ dẫn servlet mà client gọi
		String path = req.getServletPath();
		if(path.equals("/user")) {
			String action = req.getParameter("action");

		    if("delete".equals(action)) {
		        int id = Integer.parseInt(req.getParameter("id"));
		        int result = userServices.deleteUser(id);
		        resp.sendRedirect(req.getContextPath() + "/user");
		        if(result > 0 ) {
		        	System.out.println("xoa thanh cong");
		        }else {
		        	System.out.println("xoa that bai");
		        }
		        return;
		    }else if("detail".equals(action)) {
		    	int id = Integer.parseInt(req.getParameter("id"));
		    	UserEntity user = userServices.findUser(id);
		    	List<TaskEntity> tasks = userServices.findTaskByUserId(id);
		    	req.setAttribute("user", user);
		    	req.setAttribute("tasks", tasks);
		    	req.getRequestDispatcher("user-details.jsp").forward(req, resp);
		    	return;
		    }
		    
			List<UserEntity> lisUserEntities = userServices.getAll();
			
			req.setAttribute("listUsers", lisUserEntities);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		}else if(path.equals("/user-add")) {
			
			List<RoleEntity> listRoleEntities = userServices.getAllRoles();
			req.setAttribute("listRoles", listRoleEntities);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String path = req.getServletPath();
		if(path.equals("/user")) {
			return ;
		}else if(path.equals("/user-add")) {
			System.out.println("kiemtra Post");
			String fullname = req.getParameter("fullname");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String phone = req.getParameter("phone");
			int idRole = Integer.parseInt(req.getParameter("role"));
				
			String firstName = "";
		    String lastName = "";

		    String[] parts = fullname.trim().split("\\s+");
		 // nếu chỉ nhập 1 chữ thì cả họ lẫn tên giống nhau
		    if(parts.length == 1) {

		        firstName = parts[0];
		        lastName = parts[0];

		    } else {

		        firstName = parts[0];
		        lastName = parts[parts.length - 1];

		    }
		    
		    System.out.println(email);
		    System.out.println(fullname);
		    System.out.println(password);
		    System.out.println(phone);
		    System.out.println(idRole);
		    System.out.println(firstName);
		    System.out.println(lastName);
		    
		    
			UserEntity userEntity = new UserEntity();
			userEntity.setEmail(email);
			userEntity.setFullname(fullname);
			userEntity.setPassword(password);
			userEntity.setPhone(phone);
			userEntity.setRoleId(idRole);
			userEntity.setFirstname(firstName);
			userEntity.setLastname(lastName);
			
		    
			int result = userServices.insertUser(userEntity);
			if(result > 0) {
				System.out.println("them thanh cong");
				req.getRequestDispatcher("user-add.jsp").forward(req, resp);
			}else {
				System.out.println("them that bai");
			}
			
			
		}
	}
}
