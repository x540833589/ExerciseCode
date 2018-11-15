package cn.com.shoppingmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.shoppingmall.domain.Remark;
import cn.com.shoppingmall.service.DeliverRemarkService;

@Controller
public class DeliverRemarkController {

	@Autowired
	private DeliverRemarkService deliverRemarkService;
	
	/**
	 * 发表评论的业务方法
	 * @param remark 评论对象
	 * @return 浏览商品页面
	 */
	@RequestMapping("/deliverRemark")
	public String deliverRemark(Remark remark) {
		boolean isOK = false;
		isOK = deliverRemarkService.deliverRemark(remark);
		if(isOK == true)
			return "forward:/viewGoods";
		else
			return "";
	}
	
}
