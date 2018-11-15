package cn.com.shoppingmall.service;

import cn.com.shoppingmall.dao.UserLoginDao;
import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.form.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserLoginService {

    @Autowired
    private UserLoginDao userLoginDao;

    @Transactional
    public User getUserByLoginForm(LoginForm loginForm){
        User user = new User();
        user = userLoginDao.getUserByLoginForm(loginForm);
        return user;
    }

}
