package cn.com.shoppingmall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.shoppingmall.domain.Cart;
import cn.com.shoppingmall.domain.Order;
import cn.com.shoppingmall.relate.OrderItem;
import cn.com.shoppingmall.relate.PurchaseItem;
import cn.com.shoppingmall.relate.UpdateInfo;
import cn.com.shoppingmall.service.CreateOrderService;

@Controller
public class CreateOrderController {

	@Autowired
	private CreateOrderService createOrderService;
	
	/**
	 * 创建订单并存入数据库
	 * @param order 订单
	 * @return URL 提交成功页面
	 */
	@RequestMapping("/createOrder")
	public String orderSubmitSuccess(Order order , HttpSession session) {
		//将订单数据存入数据库
		createOrderService.createOrder(order);
		//将购物车中的购物清单数据存入数据库并进行相应的更新操作
		String URL = createOrder(order.getUserId() , session);
		return URL;
	}
	
	/**
	 * .将订单中的每一条购买记录存入数据库的方法
	 * @param userId 用户编号
	 * @param session 会话
	 * @return 指定视图
	 */
	private String createOrder(Integer userId , HttpSession session) {
		List<PurchaseItem> itemList = new ArrayList<>();
		//标志位
		boolean isOK = true;
		//本次下单的订单编号
		Integer orderId = 0;
		//获得购物车
		Cart cart = (Cart)session.getAttribute("cart");
		//获取购物清单
		itemList = cart.getGoodsList();
		//获取本次下单的订单编号
		orderId = createOrderService.getLatestOrderId();
		//将每一条商品记录读取出来
		for(PurchaseItem item : itemList) {
			//当前商品的商品编号
			Integer goodsId = item.getGoods().getGoodsId();
			//当前商品名称
			String goodsName = item.getGoods().getName();
			//购买数量
			Integer amount = item.getAmount();
			//单价
			Double unitPrice = item.getGoods().getPrice();
			//当前总价
			Double totalPrice = item.getCurrentPrice();
			//封装成订单条目
			OrderItem orderItem = new OrderItem(orderId , userId , goodsId  , goodsName , unitPrice , amount , totalPrice);
			//封装更新信息
			UpdateInfo info = new UpdateInfo(userId , goodsId , amount , totalPrice);
			//更新被购买商品的相关数据(月销量、剩余库存、成交数)
			boolean updateGoodsOK = createOrderService.updateGoodsInfo(info);
			//将这条购买记录存入数据库
			Integer rowAffected = createOrderService.addOrderItem(orderItem);
			//若失败则标志位置为false
			if(rowAffected <= 0 || updateGoodsOK == false) {
				isOK = false;
				break;
			}
		}
		//全部存入数据库
		if(isOK == true) {
			//封装用户更新信息对象
			UpdateInfo info = new UpdateInfo(userId , 0 , 0 , cart.getTotalPrice() / 10);
			//更新用户信息(财富积分)
			boolean updateOK = createOrderService.updateUserInfo(info);
			//清空购物车
			cart.getGoodsList().clear();
			if(updateOK == true) {
				//到达成功页面
				return "forward:/gotoSuccessPage";
			}
			else
				return "";
		//有记录失败
		}else {
			//回到订单页面
			return "forward:/gotoOrderPage";
		}
	}
	
}
