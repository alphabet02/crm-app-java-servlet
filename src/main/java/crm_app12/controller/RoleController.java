package crm_app12.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app12.entity.RoleEntity;
import crm_app12.services.RoleServices;

@WebServlet(name = "roleController", urlPatterns = {"/role", "/role-add"})
public class RoleController extends HttpServlet {
	private RoleServices roleServices = new RoleServices();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if(path.equals("/role")) {
			String action = req.getParameter("action");
			if("delete".equals(action)) {
				int id = Integer.parseInt(req.getParameter("id"));
		        int result = roleServices.deleteRole(id);
		        resp.sendRedirect(req.getContextPath() + "/role");
		        if(result > 0 ) {
		        	System.out.println("xoa thanh cong");
		        }else {
		        	System.out.println("xoa that bai");
		        }
		        return;
			}
			List<RoleEntity> listRoles = roleServices.findAll();
			req.setAttribute("listRoles", listRoles);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		}else if(path.equals("/role-add")) {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		}
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ktra pthuc post");
		String name = req.getParameter("name");
		String desciption = req.getParameter("description");
		System.out.println(name);
		System.out.println(desciption);
		
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setName(name);
		roleEntity.setDesciption(desciption);
		
		int result = roleServices.insertRole(roleEntity);
		if(result > 0) {
			System.out.println("them thanh cong");
		}else {
			System.out.println("them that bai");
		}
	
	}
}
