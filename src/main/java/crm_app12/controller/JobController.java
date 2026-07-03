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
import crm_app12.services.JobServices;

@WebServlet(name = "JobController", urlPatterns = { "/job", "/job-add" })
public class JobController extends HttpServlet {
	private JobServices jobServices = new JobServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/job")) {
			String action = req.getParameter("action");
			if("detail".equals(action)) {
				int jobId = Integer.parseInt(req.getParameter("id"));
				List<TaskEntity> listTasks = jobServices.findTaskByJobId(jobId);
				req.setAttribute("listTasks", listTasks);
				req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
				return;
			}else if("delete".equals(action)) {
				int jobId = Integer.parseInt(req.getParameter("id"));
			}
			List<JobEntity> listJobs = jobServices.findAll();
			req.setAttribute("listJobs", listJobs);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		} else if (path.equals("/job-add")) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/job")) {
			return;
		} else if (path.equals("/job-add")) {
			System.out.println("goi phuong thuc Post");
			JobEntity jobEntity = new JobEntity();
			jobEntity.setName(req.getParameter("name"));
			jobEntity.setStartDate(req.getParameter("startDate"));
			jobEntity.setEndDate(req.getParameter("endDate"));
			int result = jobServices.insertJob(jobEntity);
			if(result>0) {
				System.out.println("them job thanh cong");
			}else {
				System.out.println("them job that bai");
			}
		}
	}
}
