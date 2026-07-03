package crm_app12.services;

import java.util.List;

import crm_app12.entity.JobEntity;
import crm_app12.entity.TaskEntity;
import crm_app12.entity.UserEntity;
import crm_app12repository.JobRepository;
import crm_app12repository.TaskRepository;
import crm_app12repository.UserRepository;

public class TaskServices {
	private TaskRepository tasks = new TaskRepository();
	private UserRepository users = new UserRepository();
	private JobRepository jobs = new JobRepository();
	
	
	public List<TaskEntity> findTask(){
		return tasks.findTask();
	}
	
	public List<UserEntity> findUsers() {
		return users.findAll();
	}
	
	public List<JobEntity> findJobs() {
		return jobs.findAll();
	}
	public int insertTask(TaskEntity taskEntity) {
		return tasks.insertTask(taskEntity);
	}
}
