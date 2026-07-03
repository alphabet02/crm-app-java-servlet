package crm_app12.services;

import java.util.List;

import crm_app12.entity.TaskEntity;
import crm_app12.entity.UserEntity;
import crm_app12repository.TaskRepository;
import crm_app12repository.UserRepository;

public class ProfileServices {
	private UserRepository userRepository = new UserRepository();
	private TaskRepository taskRepository = new TaskRepository();
	
	
	public UserEntity findUser (int userId) {
		return userRepository.findUser(userId);
	}
	
	public List<TaskEntity> findTaskByUserId (int userId){
		return taskRepository.findTaskByUserId(userId);
		
	}
	
	public TaskEntity findTaskByTaskId (int taskId) {
		return taskRepository.findTaskByTaskId(taskId);
	}
	
	public int updateTaskStatusid( int taskId, int statusId) {
		return taskRepository.updateTaskStatus(taskId, statusId);
	}
}
