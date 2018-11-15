package cn.com.shoppingmall.dao;

import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.form.RegisterForm;

import java.util.LinkedList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRegisterDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public Integer register(RegisterForm registerForm) {
        Integer rowAffected = 0;
        rowAffected = sqlSessionTemplate.insert("UserMapper.register", registerForm);
        return rowAffected;
    }

    public User getUserByUserName(String username) {
        User user = new User();
        user = sqlSessionTemplate.selectOne("UserMapper.getUserByUserName", username);
        return user;
    }
    
    /**
     * 获取所有已存在用户名的方法
     * @return
     */
    public List<String> getAllUsername() {
    	List<String> usernameList = new LinkedList<>();
    	usernameList = sqlSessionTemplate.selectList("UserMapper.getAllUsername");
    	return usernameList;
    }

}
