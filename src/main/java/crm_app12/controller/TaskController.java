package crm_app12.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app12.entity.JobEntity;
import crm_app12.entity.TaskEntity;
import crm_app12.entity.UserEntity;
import crm_app12.services.TaskServices;

@WebServlet(name = "taskController", urlPatterns = { "/task", "/task-add" })
public class TaskController extends HttpServlet {
	private TaskServices taskServices = new TaskServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if ("/task".equals(path)) {
			List<TaskEntity> listTasks = taskServices.findTask();
			req.setAttribute("listTasks", listTasks);
			req.getRequestDispatcher("task.jsp").forward(req, resp);
		} else if ("/task-add".equals(path)) {
			List<JobEntity> listJobs = taskServices.findJobs();
			List<UserEntity> listUsers = taskServices.findUsers();
			req.setAttribute("listJobs", listJobs);
			req.setAttribute("listUsers", listUsers);
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("goi phuong thuc post");
		TaskEntity task = new TaskEntity();
		task.setName(req.getParameter("name"));
		task.setStartDate(req.getParameter("startDate"));
		task.setEndDate(req.getParameter("endDate"));
		task.setUserId(Integer.parseInt(req.getParameter("userId")));
		task.setJobId(Integer.parseInt(req.getParameter("jobId")));
		task.setStatusId(Integer.parseInt(req.getParameter("statusId")));
		
		int result = taskServices.insertTask(task);
		if(result>0) {
			System.out.println("them task thanh cong");
		}else {
			System.out.println("them that bai");
		}
	}
}
