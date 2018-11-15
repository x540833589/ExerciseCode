package cn.com.shoppingmall.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.shoppingmall.domain.Cart;
import cn.com.shoppingmall.relate.PurchaseItem;

/**
 * .异步提交用户修改购物车中商品数量的控制器
 * @author Administrator
 */
@Controller
public class HandleModifyAmount {

	/**
	 * .获取用户提交数据并修改会话中的购物车
	 * @param request 异步请求
	 * @return 异步请求执行结果
	 */
	@RequestMapping(value = "/handle", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> modify(HttpServletRequest request) {
		Map<String, Object> mappp = new HashMap<>();
		Cart cart = new Cart();
		try { 
			Integer goodsId = Integer.decode(request.getParameter("goodsId"));
			Integer amount = Integer.decode(request.getParameter("amount"));
			cart = (Cart)request.getSession().getAttribute("cart");
			for(PurchaseItem item : cart.getGoodsList()) {
				if(item.getGoods().getGoodsId() == goodsId) {
					item.setAmount(amount);
					item.setCurrentPrice(item.getGoods().getPrice() * item.getAmount());
					break;
				}
			}
			request.getSession().setAttribute("cart" , cart);
		}catch (Exception e){
			mappp.put("success", false);
		}
		mappp.put("success", true);
		return mappp;
	}
	
}
