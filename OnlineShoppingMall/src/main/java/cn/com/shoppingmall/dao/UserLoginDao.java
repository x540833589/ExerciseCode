package cn.com.shoppingmall.dao;

import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.form.LoginForm;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserLoginDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public User getUserByLoginForm(LoginForm loginForm){
        User user = new User();
        user = (User)sqlSessionTemplate.selectOne("UserMapper.getUserByLoginForm", loginForm);
        return user;
    }

}
