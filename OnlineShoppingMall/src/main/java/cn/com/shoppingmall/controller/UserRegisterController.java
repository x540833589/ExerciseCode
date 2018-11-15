package cn.com.shoppingmall.controller;

import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.form.RegisterForm;
import cn.com.shoppingmall.service.UserRegisterService;
import cn.com.shoppingmall.utils.ReviseFormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Controller
public class UserRegisterController {

    @Autowired
    private UserRegisterService userRegisterService;

    @RequestMapping("/doRegister")
    public ModelAndView register(RegisterForm registerForm, HttpSession session) {
        User user = new User();
        Map<String, String> errorMessages = new Hashtable<>();
        ModelAndView mav = new ModelAndView();
        errorMessages = registerForm.validate();
        //注册信息非法
        if(errorMessages.size() > 0) {
            //使用工具进行校正
            RegisterForm newRegisterForm = ReviseFormUtils.reviseRegisterForm(registerForm, errorMessages);
            //校正好的表单存入会话
            session.setAttribute("newRegisterForm", newRegisterForm);
            //返回注册页面让用户重新填写注册信息
            mav.setViewName("register");
        //注册信息合法
        }else {
        	List<String> usernameList = userRegisterService.getAllUsername();
        	//用户名已被注册
        	if(usernameList.contains(registerForm.getUsername())) {
        		session.setAttribute("usernameExistError" , "用户名已被使用.");
        		//清空用户名
        		registerForm.setUsername("");
        		//其他信息保留并存入会话
        		session.setAttribute("newRegisterForm" , registerForm);
        		//返回注册页面
        		mav.setViewName("register");
        	//用户名未被使用
        	}else {
        		//将DTO转换成PO存入数据库
        		user = userRegisterService.register(registerForm);
                //会话中设置用户，表明已登录(注册成功直接进入首页)
        		session.setAttribute("user" , user);
                mav.setViewName("index");
			}
        }
        return mav;
    }

}
