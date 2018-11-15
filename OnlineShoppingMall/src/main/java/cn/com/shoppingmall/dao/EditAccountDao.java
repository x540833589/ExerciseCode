package cn.com.shoppingmall.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.form.RegisterForm;

@Repository
public class EditAccountDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 用户修改资料
	 * @param registerForm 表单
	 * @return 用户
	 */
	public User editAccount(RegisterForm registerForm) {
		//更新用户信息
		Integer rowAffected = sqlSessionTemplate.update("UserMapper.editPersonalAccount" , registerForm);
		User user = new User();
		//如果操作成功
		if(rowAffected > 0)
			//获取更新后的用户信息
			user = sqlSessionTemplate.selectOne("UserMapper.getUserByUserName" , registerForm.getUsername());
		return user;
	}
	
}
