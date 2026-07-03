package crm_app12.services;

import java.util.List;

import crm_app12repository.RoleRepository;
import crm_app12repository.UserRepository;
import crm_app12.entity.*;
public class UserServices {
	
	private UserRepository userRepository = new UserRepository(); 
	private RoleRepository roleRepository = new RoleRepository();
	
	public List<UserEntity> getAll() {
		List<UserEntity> listUser = userRepository.findAll();
		return listUser;
	}
	
	public List<RoleEntity> getAllRoles(){
		return roleRepository.findAll();
	}
	
	public int insertUser(UserEntity userEntity) {
		return userRepository.insertUser(userEntity);
	}
	public int deleteUser(int id) {
	    return userRepository.deleteUser(id);
	}
	
	public UserEntity findUser (int userId) {
		return userRepository.findUser(userId);
	}
	public List<TaskEntity> findTaskByUserId(int userId) {
		return userRepository.findTaskByUserId(userId);
	}
	
}
