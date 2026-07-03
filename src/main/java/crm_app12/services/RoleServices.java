package crm_app12.services;

import java.util.List;

import crm_app12.entity.RoleEntity;
import crm_app12repository.RoleRepository;

public class RoleServices {
	private RoleRepository roleRepository = new RoleRepository();
	
	public List<RoleEntity> findAll(){
		return roleRepository.findAll();
	}
	
	public int insertRole(RoleEntity roleEntity) {
		return roleRepository.insertRole(roleEntity);
	}
	
	public int deleteRole(int idRole) {
		return roleRepository.deleteRole(idRole);
	}
}
