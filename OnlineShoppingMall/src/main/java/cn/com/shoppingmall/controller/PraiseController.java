package cn.com.shoppingmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.shoppingmall.relate.Praise;
import cn.com.shoppingmall.service.PraiseService;

/**
 * (点/取消)赞的控制器
 * @author Administrator
 */
@Controller
public class PraiseController {
	
	@Autowired
	private PraiseService praiseService;
	
	/**
	 * 点赞业务方法
	 * @param praise 关联用户商品的包装类
	 * @param session 会话
	 * @return 指定的业务方法
	 */
	@RequestMapping("/addPraise")
	public String praise(Praise praise , HttpSession session) {
		if(praise.getUserId() == 0) {
			session.setAttribute("praiseError" , "请先登录。");
			return "redirect:/gotoLoginPage";
		}else {
			boolean isOK = praiseService.addPraise(praise);
			if(isOK == true)
				return "forward:/viewGoods?userId=" + praise.getUserId();
			else
				return "";
		}
	}
	
	/**
	 * 取消赞
	 * @param praise 赞
	 * @param session 会话
	 * @return 指定业务方法
	 */
	@RequestMapping("/cancelPraise")
	public String cancelPraise(Praise praise , HttpSession session) {
		if(praise.getUserId() == 0) {
			session.setAttribute("praiseError" , "请先登录。");
			return "redirect:/gotoLoginPage";
		}else {
			boolean isOK = praiseService.cancelPraise(praise);
			if(isOK == true)
				return "forward:/viewGoods?userId=" + praise.getUserId();
			else
				return "";
		}
	}
	
}
