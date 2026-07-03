package crm_app12.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app12.entity.TaskEntity;
import crm_app12.entity.UserEntity;
import crm_app12.services.ProfileServices;
import crm_app12.services.UserServices;

@WebServlet(name = "profileController", urlPatterns = { "/profile", "/profile-edit" })
public class ProfileController extends HttpServlet {
	private ProfileServices profileService = new ProfileServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if ("/profile".equals(path)) {
			Cookie[] cookies = req.getCookies();
			String idValue = null;
			if (cookies != null) {
				for (Cookie cooky : cookies) {
					if ("id".equals(cooky.getName())) {
						idValue = cooky.getValue();
					}
				}
			}
			if (idValue == null) {
				resp.sendRedirect(req.getContextPath() + "/login");
				return;
			}

			int idUser = Integer.parseInt(idValue);
			List<TaskEntity> listTasks = profileService.findTaskByUserId(idUser);
			UserEntity user = profileService.findUser(idUser);
			req.setAttribute("User", user);
			req.setAttribute("listTasks", listTasks);
			req.getRequestDispatcher("profile.jsp").forward(req, resp);
		}else if("/profile-edit".equals(path)) {
			int taskId = Integer.parseInt(req.getParameter("id"));
			TaskEntity task = profileService.findTaskByTaskId(taskId);
			req.setAttribute("task", task);
			req.getRequestDispatcher("profile-edit.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int taskId = Integer.parseInt(req.getParameter("id"));
		int statusId = Integer.parseInt(req.getParameter("statusId"));
		int result = profileService.updateTaskStatusid(taskId, statusId);
		if(result > 0) {
			System.out.println("update trang thai moi thanh cong");
		}else {
			System.out.println("update that bai");
		}
		
		resp.sendRedirect(req.getContextPath() + "/profile");
	}
}
