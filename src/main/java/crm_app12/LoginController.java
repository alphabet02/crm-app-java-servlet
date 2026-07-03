package crm_app12;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_app12.entity.UserEntity;

@WebServlet(name = "LoginController", urlPatterns = {"/log"})
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/**
		 * Buoc 1 chuan bi cau truy van
		 * Buoc 2 mo ket noi CSDL
		 * Buoc 3 truyen cau truy van vao ket noi moi vua mo va truyen tham so  (neu co)
		 * Buoc 4 thuc thi cau truy van
		 */
		
		try {
			//buoc 1 chuan bi cau truy van
			String query ="SELECT * FROM user u WHERE u.email = ? AND u.PASSWORD = ?";
			//buoc 2 mo ket noi toi CSDL
			Connection connection = MysqlConfig.gettingConnection();
			//buoc 3 truyen cau truy van vao ket noi
			PreparedStatement statement = connection.prepareStatement(query);
			//truyen tham so vao dau ? o cau query
			statement.setString(1, req.getParameter("email"));
			statement.setString(2,req.getParameter("password"));
			//buoc 4 thuc thi cau truy van
			//excuteQuery: khi cau truy van la cau select
			//excuteUpdate: khi cau truy van khong la cau select
			ResultSet resultSet = statement.executeQuery();
			
			
			//tao ra mot list rong để chứa dữ liệu từ câu truy vấn trả về trong result set
			List<UserEntity> listUser = new ArrayList<UserEntity>();
			
			while(resultSet.next()) {
				UserEntity userEntity = new UserEntity();
				userEntity.setId(resultSet.getInt("id"));
				userEntity.setFullname(resultSet.getString("fullname"));
				
				
				listUser.add(userEntity);
			}
			
			if(listUser.size()>0) {
				System.out.println("thanh cong");
			}else {
				System.out.println("that bai");
			}
			
			
			
		}catch (Exception e) {
			System.out.println("mo ket noi " + e.getMessage());
		}
		
	}
	
}
