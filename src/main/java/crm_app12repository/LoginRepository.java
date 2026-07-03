package crm_app12repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app12.config.MysqlConfig;
import crm_app12.entity.UserEntity;
public class LoginRepository {
	public List<UserEntity> findByUsernamePassword(String email, String password){
		/**
		 * Buoc 1 chuan bi cau truy van
		 * Buoc 2 mo ket noi CSDL
		 * Buoc 3 truyen cau truy van vao ket noi moi vua mo va truyen tham so  (neu co)
		 * Buoc 4 thuc thi cau truy van
		 */
		List<UserEntity> listUser = new ArrayList<UserEntity>();
		String query = "SELECT u.id, u.fullname, r.name FROM user u JOIN role r on r.id = u.role_id WHERE u.email = ? AND u.PASSWORD = ?";
		try {
			Connection connection = MysqlConfig.gettingConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(resultSet.getInt("id"));
				userEntity.setFullname(resultSet.getString("fullname"));
				userEntity.setRolename(resultSet.getString("name"));
				listUser.add(userEntity);
			}
			
			
		} catch (Exception e) {
			System.out.println("loi findALl " + e.getMessage());
		}
		return listUser;
		
		

	}
}
