package crm_app12repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app12.config.MysqlConfig;
import crm_app12.entity.JobEntity;
import crm_app12.entity.TaskEntity;

public class JobRepository {
	public List<JobEntity> findAll(){
		List<JobEntity> listJobs =  new ArrayList<JobEntity>();
		String query = "SELECT * FROM jobs";
		try {
			Connection connection = MysqlConfig.gettingConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				JobEntity jobEntity = new JobEntity();
				jobEntity.setId(resultSet.getInt("id"));
				jobEntity.setName(resultSet.getString("name"));
				jobEntity.setStartDate(resultSet.getString("start_date"));
				jobEntity.setEndDate(resultSet.getString("end_date"));
				listJobs.add(jobEntity);
			}
		}catch (Exception e) {
			System.out.println("loi findALL job: " + e.getMessage());
		}
	
		return listJobs;
		}
	
	public List<TaskEntity> findTaskByJobId(int jobId){
		List<TaskEntity> listTasks = new ArrayList<TaskEntity>();
		String query = "select t.name, u.fullname, t.status_id \r\n"
				+ "from tasks t join user u on t.user_id = u.id\r\n"
				+ "where t.job_id = ?";
		try {
			Connection connection = MysqlConfig.gettingConnection();
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, jobId);
	        ResultSet resultSet = statement.executeQuery();
	        while(resultSet.next()) {
	        	TaskEntity task = new TaskEntity();
	        	task.setName(resultSet.getString("name"));
	        	task.setUserName(resultSet.getString("fullname"));
	        	task.setStatusId(resultSet.getInt("status_id"));
	        	listTasks.add(task);
	        }       
		} catch (Exception e) {
			System.out.println("loi findTaskByJobId: " + e.getMessage());
		}
		return listTasks;
	}
	
	public int insertJob (JobEntity jobEntity) {
		try {
			String query = "INSERT INTO jobs(name, start_date, end_date) VALUES (?, ?, ?)";
			Connection connection = MysqlConfig.gettingConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, jobEntity.getName());
			statement.setString(2, jobEntity.getStartDate());
			statement.setString(3, jobEntity.getEndDate());
			int result = statement.executeUpdate();
	        return result;
		} catch (Exception e) {
			System.out.println("loi insert JOB " + e.getMessage());
		}
		return 0;
	}
	
}
