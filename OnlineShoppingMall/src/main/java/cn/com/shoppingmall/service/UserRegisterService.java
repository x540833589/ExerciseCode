package cn.com.shoppingmall.service;

import cn.com.shoppingmall.dao.UserRegisterDao;
import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.form.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserRegisterService {

    @Autowired
    private UserRegisterDao userRegisterDao;

    @Transactional
    public User register(RegisterForm registerForm) {
        User user = new User();
        Integer isOK = 0;
        isOK = userRegisterDao.register(registerForm);
        if(isOK > 0) {
            String username = registerForm.getUsername();
            user = userRegisterDao.getUserByUserName(username);
        }
        return user;
    }

    private java.sql.Date stringToDate(String pattern) {
        java.sql.Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = new java.sql.Date(simpleDateFormat.parse(pattern).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    public void x() {
    	stringToDate("xxx");
    }
    
    /**
     * 获取所有已存在用户名的方法
     * @return
     */
    public List<String> getAllUsername() {
    	List<String> usernameList = new LinkedList<>();
    	usernameList = userRegisterDao.getAllUsername();
    	return usernameList;
    }

}
