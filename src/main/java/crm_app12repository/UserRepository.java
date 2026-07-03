package crm_app12repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import crm_app12.config.MysqlConfig;
import crm_app12.entity.TaskEntity;
import crm_app12.entity.UserEntity;
//Quản lý tất cả câu truy vấn liên quan đến bảng user
public class UserRepository {
	/*
	 * select -> find 
	 * where -> By
	 * username = '' -> userName
	 * vi ddu: select * from user where username = ''
	 * -> findByUsername(); 
	 */
	public List<UserEntity> findAll(){ //lấy tất cả tên của nhân viên 
		List<UserEntity> ListUserEntities = new ArrayList<UserEntity>();
		String query = "SELECT u.id,u.fullname ,u.first_name, u.last_name, u.email, r.name \r\n"
				+ "FROM user u\r\n"
				+ "JOIN role r on u.role_id = r.id;";
		try {
			Connection connection = MysqlConfig.gettingConnection();
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(resultSet.getInt("id"));
				userEntity.setFirstname(resultSet.getString("first_name"));
				userEntity.setFullname(resultSet.getString("fullname"));
				userEntity.setEmail(resultSet.getString("email"));
				userEntity.setLastname(resultSet.getString("last_name"));
				userEntity.setRolename(resultSet.getString("name"));
				
				ListUserEntities.add(userEntity);
			}
		}catch (Exception e) {
			System.out.println("loi findALl " + e.getMessage());
		}
		
		return ListUserEntities;
	}
	
	
	public int insertUser(UserEntity userEntity) {
		try {
			//buoc 1 chuan bi cau truy van
			String query = "INSERT INTO user(email, PASSWORD, fullname, role_id, first_name, last_name) "
	                 + "VALUES (?, ?, ?, ?, ?, ?)";
			// bước 2 mở kết nối tới CSDL
	        Connection connection = MysqlConfig.gettingConnection();

	        // bước 3 truyền câu query vào connection
	        PreparedStatement statement = connection.prepareStatement(query);

	        // bước 4 truyền dữ liệu vào các dấu ?
	        statement.setString(1, userEntity.getEmail());
	        statement.setString(2, userEntity.getPassword());
	        statement.setString(3, userEntity.getFullname());
	        statement.setInt(4, userEntity.getRoleId());
	        statement.setString(5, userEntity.getFirstname());
	        statement.setString(6, userEntity.getLastname());

	        // bước 5: thực thi câu insert
	        // executeUpdate trả về số dòng bị ảnh hưởng
	        int result = statement.executeUpdate();
	        return result;
			
		} catch (Exception e) {
			System.out.println("loi insert user " + e.getMessage());
		}
		return 0;
	}
	public int deleteUser(int id) {
	    String query = "DELETE FROM user WHERE id = ?";

	    try {
	        Connection connection = MysqlConfig.gettingConnection();
	        PreparedStatement statement = connection.prepareStatement(query);

	        statement.setInt(1, id);

	        return statement.executeUpdate();

	    } catch (Exception e) {
	        System.out.println("loi delete user " + e.getMessage());
	    }

	    return 0;
	}
	
	public UserEntity findUser(int userId){
		UserEntity user = new UserEntity();
		String query = "select u.fullname, u.email\r\n"
				+ "from user u \r\n"
				+ "where u.id = ?";
		try {
	        Connection connection = MysqlConfig.gettingConnection();
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, userId);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            user.setEmail(resultSet.getString("email"));
	            user.setFullname(resultSet.getString("fullname"));
	        }
	        System.out.println(user.getEmail());
	        System.out.println(user.getFullname());
	        
		 } catch (Exception e) {
		        System.out.println("loi find user by id " + e.getMessage());
		 }
		return user;
	}
	
	public List<TaskEntity> findTaskByUserId(int userId) {
	    List<TaskEntity> tasks = new ArrayList<>();
	    String query = "select t.name, t.start_date, t.end_date, t.status_id "
	                 + "from tasks t "
	                 + "where t.user_id = ?";
	    try {
	        Connection connection = MysqlConfig.gettingConnection();
	        PreparedStatement statement = connection.prepareStatement(query);
	        statement.setInt(1, userId);
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            TaskEntity task = new TaskEntity();
	            task.setName(resultSet.getString("name"));
	            task.setStartDate(resultSet.getString("start_date"));
	            task.setEndDate(resultSet.getString("end_date"));
	            task.setStatusId(resultSet.getInt("status_id"));
	            tasks.add(task);
	        }

	    } catch (Exception e) {
	        System.out.println("loi find task by user id " + e.getMessage());
	    }

	    return tasks;
	}
		
}
	
	
	
