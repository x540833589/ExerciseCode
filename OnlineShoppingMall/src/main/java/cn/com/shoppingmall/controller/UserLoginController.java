package cn.com.shoppingmall.controller;

import cn.com.shoppingmall.domain.Cart;
import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.form.LoginForm;
import cn.com.shoppingmall.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import java.util.Map;

@Controller
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    /**
     * 用户登录的验证方法
     * @param loginForm 用户输入的登录表单
     * @param session 会话
     * @return 根据校验结果返回指定的视图
     */
    @RequestMapping("/doLogin")
    public String login(LoginForm loginForm , HttpSession session) {
        User user = new User();
        //初始化错误信息提示列表
        Map<String, String> errorMessages = new Hashtable<>();
        //验证用户登录表单
        errorMessages = loginForm.validate();
        //格式错误
        if(errorMessages.size() > 0) {
            session.setAttribute("UsernameOrPasswordFormatError" , "用户名或密码格式错误。");
            return "redirect:/gotoLoginPage";
        //格式正确
        }else {
            //根据用户的输入去数据库中检索
            user = userLoginService.getUserByLoginForm(loginForm);
            //如果该用户存在
            if(user != null && user.getUsername().length() > 0) {
                //用户被锁定
                if(user.getLockedStatus().equals("Y")) {
                    session.setAttribute("lockedStatusError" , "用户已被锁定，请申请解锁。");
                    return "redirect:/gotoLoginPage";
                //用户未被锁定
                }if(user.getLockedStatus().equals("N")) {
                	//为用户创建购物车
                	session.setAttribute("cart" , new Cart());
                	//向会话中添加用户
                    session.setAttribute("user" , user);
                    return "forward:/index?currentPageNumber=1";
                }
                return "";
            //不存在这样的用户
            }else {
                session.setAttribute("UsernameOrPasswordError" , "用户名或密码输入错误。");
                return "redirect:/gotoLoginPage";
            }
        }
    }

}
