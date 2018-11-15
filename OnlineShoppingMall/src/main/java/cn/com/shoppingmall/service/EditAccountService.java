package cn.com.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.shoppingmall.dao.EditAccountDao;
import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.form.RegisterForm;

@Service
public class EditAccountService {

	@Autowired
	private EditAccountDao editAccountDao;
	
	/**
	 * 用户修改资料
	 * @param registerForm 表单
	 * @return 用户
	 */
	public User editAccount(RegisterForm registerForm) {
		User user = new User();
		user = editAccountDao.editAccount(registerForm);
		return user;
	}
	
}
