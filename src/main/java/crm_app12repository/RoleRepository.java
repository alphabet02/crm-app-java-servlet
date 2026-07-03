package crm_app12repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app12.config.MysqlConfig;
import crm_app12.entity.RoleEntity;
import crm_app12.entity.UserEntity;

public class RoleRepository {
	public List<RoleEntity> findAll(){
		List<RoleEntity> listRoles =  new ArrayList<RoleEntity>();
		String query = "SELECT * FROM role";
		try {
			Connection connection = MysqlConfig.gettingConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setId(resultSet.getInt("id"));
				roleEntity.setName(resultSet.getString("name"));
				roleEntity.setDesciption(resultSet.getString("desciption"));

				listRoles.add(roleEntity);
			}
		}catch (Exception e) {
			System.out.println("loi findALl " + e.getMessage());
		}
	
		return listRoles;
		}
	
	public int insertRole(RoleEntity roleEntity) {
		try {
			String query = "INSERT INTO role(name, desciption) "
	                 + "VALUES (?, ?)";
	        Connection connection = MysqlConfig.gettingConnection();
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setString(1, roleEntity.getName());
	        statement.setString(2, roleEntity.getDesciption());
	        int result = statement.executeUpdate();
	        return result;
			
		} catch (Exception e) {
			System.out.println("loi insert user " + e.getMessage());
		}
		return 0;
	}
	
	public int deleteRole(int roleId) {
	    String query = "DELETE FROM role WHERE id = ?";

	    try {
	        Connection connection = MysqlConfig.gettingConnection();
	        PreparedStatement statement = connection.prepareStatement(query);

	        statement.setInt(1, roleId);

	        return statement.executeUpdate();

	    } catch (Exception e) {
	        System.out.println("loi delete user " + e.getMessage());
	    }

	    return 0;
	
	
	}
}
