package cn.com.shoppingmall.controller;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.form.RegisterForm;
import cn.com.shoppingmall.service.EditAccountService;
import cn.com.shoppingmall.utils.ReviseFormUtils;

@Controller
public class EditAccountController {
	
	@Autowired
	private EditAccountService editAccountService;
	
	/**
	 * 用户修改资料
	 * @param registerForm 表单
	 * @param userId 用户编号
	 * @param session 会话
	 * @return 指定控制器下的业务方法
	 */
	@RequestMapping("/editSave")
	public ModelAndView editAccount(RegisterForm registerForm , Integer userId , HttpSession session) {
		//清空用户的原始信息
		session.removeAttribute("previousInfo");
		session.removeAttribute("newForm");
		ModelAndView mav = new ModelAndView();
		Map<String , String> errorMessages = new Hashtable<>();
		errorMessages = registerForm.validate();
		//输入信息非法
		if(errorMessages.size() > 0) {
			RegisterForm newForm = new RegisterForm();
			//校正表单
			newForm = ReviseFormUtils.reviseRegisterForm(registerForm , errorMessages);
			//存入会话
			session.setAttribute("newForm" , newForm);
			//返回编辑页面
			mav.setViewName("edit");
			return mav;
		//输入信息合法
		}else {
			User user = new User();
			//存入数据库并获取更新后的用户对象
			user = editAccountService.editAccount(registerForm);
			//更新会话中的用户信息
			session.setAttribute("user" , user);
			//操作成功
			if(user != null) {
				//返回个人中心
				mav.setViewName("personalCenter");
				return mav;
			}
			else 
				return mav;
		}
	}
	
}
