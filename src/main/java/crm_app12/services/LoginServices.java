package crm_app12.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import crm_app12repository.LoginRepository;
import crm_app12.entity.UserEntity;

public class LoginServices {
	private LoginRepository loginRepository = new LoginRepository();

	public void getByUsernamePassword(String email, String password, String remember, HttpServletResponse resp) {
		List<UserEntity> listUser = loginRepository.findByUsernamePassword(email, password);
		if (listUser.size() > 0) {
			System.out.println("dang nhap thanh cong");
			// tạo cookie này để làm dấu, nếu có vào lại trang web đã đăng nhập (sẽ thấy
			// cookie này) thì filter cho qua
			Cookie cRole = new Cookie("role", listUser.get(0).getRolename());
			cRole.setMaxAge(8 * 60 * 60);
			resp.addCookie(cRole);
			
			Cookie cUserId = new Cookie("id", String.valueOf(listUser.get(0).getId()));
			cUserId.setMaxAge(8 * 60 * 60);
			resp.addCookie(cUserId);
			try {
				resp.sendRedirect("/crm_app12/profile");
			} catch (Exception e) {
				System.out.println("loi loginservice: " + e.getMessage());
			}

			// tạo các cookie này lưu email mật khẩu để tiện đăng nhập lại
			if (remember != null) {
				Cookie cEmail = new Cookie("email", email);
				cEmail.setMaxAge(1 * 60);

				Cookie cPassWord = new Cookie("password", password);
				cPassWord.setMaxAge(1 * 60);

				resp.addCookie(cEmail);
				resp.addCookie(cPassWord);
			}
		} else {
			System.out.println("dang nhap that bai");
		}

	}

}
