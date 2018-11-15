package cn.com.oc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.oc.domain.Administrator;
import cn.com.oc.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * .管理员登录的验证方法
	 * @param admin
	 * @return 结果视图
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(Administrator admin , HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Administrator administrator = new Administrator();
		administrator = loginService.validate(admin);
		if(administrator != null && !administrator.getName().equals("")) {
			mav.setViewName("admin/adminMain");
			session.setAttribute("admin" , administrator);
		}else {
			mav.setViewName("admin/login");
			//不是第一次进入登录界面，而是用户名密码错误返回登录界面
			session.setAttribute("firstGoInLoginPage" , null);
			//错误提示信息
			session.setAttribute("usernameOrPasswordError" , "用户名或密码错误.");
		}
		return mav;
	}
	
	/**
	 * 管理员注销方法
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout" , method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpSession session) {
		session.setAttribute("admin", null);
		return session.getAttribute("admin") == null ? "注销成功" : "注销失败";
	}
	
	/**
	 * 已登录后再次进入管理员页面,根据session中是否存在admin来判断进入哪一个页面
	 * @return
	 */
	@RequestMapping("/gotoAdminMainPage")
	public ModelAndView gotoAdminMainPage(HttpSession session) {
		System.out.println("会话中admin存在状态:" + session.getAttribute("admin"));
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("admin") != null)
			mav.setViewName("admin/adminMain");
		else {
			mav.setViewName("admin/login");
			//第一次进入登录界面时，设置信息
			session.setAttribute("firstGoInLoginPage" , "第一次进入登录界面");
		}
		return mav;
	}
//	
//	@RequestMapping(value = "/checkLoginStatus" , method = RequestMethod.POST)
//	@ResponseBody
//	public String checkLoginStatus(HttpSession session) {
//		return session.getAttribute("admin") == null ? "no" : "yes";
//	}
	
}
