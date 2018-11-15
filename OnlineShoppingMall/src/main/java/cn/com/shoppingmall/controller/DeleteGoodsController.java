package cn.com.shoppingmall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.shoppingmall.domain.Cart;
import cn.com.shoppingmall.relate.PurchaseItem;

@Controller
public class DeleteGoodsController {

	/**
	 * .删除购物车中的某件商品
	 * @param goodsId 商品编号
	 * @param session 会话
	 * @return 购物车页面
	 */
	@RequestMapping("/deleteGoods")
	public ModelAndView deleteGoodsByGoodsId(Integer goodsId , HttpSession session) {
		//获取购物车
		Cart cart = (Cart)session.getAttribute("cart");
		//获取购物清单
		List<PurchaseItem> itemList = cart.getGoodsList();
		//根据商品编号删除指定的商品
		for(int i = 0 ; i < itemList.size() ; i++) {
			if(itemList.get(i).getGoods().getGoodsId() == goodsId) {
				itemList.remove(itemList.get(i));
				break;
			}
		}
		//更新会话中的购物车对象
		session.setAttribute("cart" , cart);
		ModelAndView mav = new ModelAndView("cart");
		return mav;
	}
	
}
