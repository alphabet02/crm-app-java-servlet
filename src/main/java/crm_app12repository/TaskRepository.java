package crm_app12repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app12.config.MysqlConfig;
import crm_app12.entity.TaskEntity;
public class TaskRepository {
	
	
	public List<TaskEntity> findTask() {
	    List<TaskEntity> tasks = new ArrayList<>();
	    String query = "select t.name, u.fullname, j.name as jobName, t.start_date, t.end_date, t.status_id "
	                 + "from jobs j join tasks t on j.id = job_id join user u on t.user_id = u.id ";
	    try {
	        Connection connection = MysqlConfig.gettingConnection();
	        PreparedStatement statement = connection.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            TaskEntity task = new TaskEntity();
	            task.setName(resultSet.getString("name"));
	            task.setStartDate(resultSet.getString("start_date"));
	            task.setEndDate(resultSet.getString("end_date"));
	            task.setStatusId(resultSet.getInt("status_id"));
	            task.setUserName(resultSet.getNString("fullname"));
	            task.setJobName(resultSet.getNString("jobName"));
	            tasks.add(task);
	        }

	    } catch (Exception e) {
	        System.out.println("loi find task by user id " + e.getMessage());
	    }

	    return tasks;
	}
	
	public int insertTask(TaskEntity taskEntity) {
		try {
			String query = "INSERT INTO tasks(name, start_date, end_date, user_id, job_id, status_id) VALUES (?, ?, ?, ?, ?, ?)";
			Connection connection = MysqlConfig.gettingConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, taskEntity.getName());
			statement.setString(2, taskEntity.getStartDate());
			statement.setString(3, taskEntity.getEndDate());
			statement.setInt(4, taskEntity.getUserId());
			statement.setInt(5, taskEntity.getJobId());
			statement.setInt(6, taskEntity.getStatusId());
			int result = statement.executeUpdate();
	        return result;
		} catch (Exception e) {
			System.out.println("loi insert task " + e.getMessage());
		}
		return 0;
	}
	
	public List<TaskEntity> findTaskByUserId(int userId) {
	    List<TaskEntity> tasks = new ArrayList<>();
	    String query = "select t.id, t.name, j.name as jobName, t.end_date, t.start_date, t.status_id\r\n"
	    		+ "from tasks t join jobs j on t.job_id = j.id\r\n"
	    		+ "where t.user_id = ?";
	    try {
	        Connection connection = MysqlConfig.gettingConnection();
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, userId);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            TaskEntity task = new TaskEntity();
	            task.setId(resultSet.getInt("id"));
	            task.setName(resultSet.getString("name"));
	            task.setStartDate(resultSet.getString("start_date"));
	            task.setEndDate(resultSet.getString("end_date"));
	            task.setStatusId(resultSet.getInt("status_id"));
	            task.setJobName(resultSet.getNString("jobName"));
	            tasks.add(task);
	        }

	    } catch (Exception e) {
	        System.out.println("loi find task by user id " + e.getMessage());
	    }

	    return tasks;
	}
	
	public TaskEntity findTaskByTaskId(int taskId) {
	    TaskEntity task = new TaskEntity();
	    String query = "select t.id, t.name, j.name as jobName, t.job_id, t.end_date, t.start_date, t.status_id\r\n"
	    		+ "from tasks t join jobs j on t.job_id = j.id\r\n"
	    		+ "where t.id = ?";
	    try {
	        Connection connection = MysqlConfig.gettingConnection();
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, taskId);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            task.setId(resultSet.getInt("id"));
	            task.setName(resultSet.getString("name"));
	            task.setJobId(resultSet.getInt("job_id"));
	            task.setStartDate(resultSet.getString("start_date"));
	            task.setEndDate(resultSet.getString("end_date"));
	            task.setStatusId(resultSet.getInt("status_id"));
	            task.setJobName(resultSet.getNString("jobName"));
	        }

	    } catch (Exception e) {
	        System.out.println("loi find task by user id " + e.getMessage());
	    }

	    return task;
	}
	
	public int updateTaskStatus(int taskId, int statusId) {
		 String query = "UPDATE tasks SET status_id = ? WHERE id = ?";

		    try {
		        Connection connection = MysqlConfig.gettingConnection();
		        PreparedStatement statement = connection.prepareStatement(query);

		        statement.setInt(1, statusId);
		        statement.setInt(2, taskId);

		        return statement.executeUpdate();
		    } catch (Exception e) {
		        System.out.println("loi update status task: " + e.getMessage());
		    }

		    return 0;
	} 
	
}
