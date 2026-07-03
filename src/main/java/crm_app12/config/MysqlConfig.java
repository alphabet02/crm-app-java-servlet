package crm_app12.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConfig {
	// hàm mở kết nố tới csdl
	public static Connection gettingConnection() {
		Connection connection = null;
		try {
			String url = "jdbc:mysql://localhost:3307/CRMAPP";
			String username = "root";
			String password = "admin123";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			System.out.println("getConnection " + e.getMessage());
		}
		
		return connection;
	}
}
