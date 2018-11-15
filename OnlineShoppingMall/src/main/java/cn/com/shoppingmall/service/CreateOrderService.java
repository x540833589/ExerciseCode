package cn.com.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.shoppingmall.dao.CreateOrderDao;
import cn.com.shoppingmall.domain.Order;
import cn.com.shoppingmall.relate.OrderItem;
import cn.com.shoppingmall.relate.UpdateInfo;

@Service
public class CreateOrderService {

	@Autowired
	private CreateOrderDao createOrderDao;
	
	/**
	 * .存入某一条购物记录
	 * @param orderItem 一条商品记录
	 * @return 受影响的行数
	 */
	public Integer addOrderItem(OrderItem orderItem) {
		Integer isOK = 0;
		isOK = createOrderDao.addOrderItem(orderItem);
		return isOK;
	}
	
	/**
	 * .获取当前最大的订单编号
	 * @return 最大的订单编号
	 */
	public Integer getLatestOrderId() {
		Integer max = 0;
		max = createOrderDao.getLatestOrderId();
		return max;
	}
	
	/**
	 * 创建订单
	 * @param order 订单
	 * @return 受影响的行数
	 */
	public Integer createOrder(Order order) {
		Integer isOK = 0;
		isOK = createOrderDao.createOrder(order);
		return isOK;
	}
	
	/**
	 * 购物成功，更新购买的商品信息
	 * @param info 待更新信息
	 * @return 是否成功
	 */
	public boolean updateGoodsInfo(UpdateInfo info) {
		boolean isOK = false;
		isOK = createOrderDao.updateGoodsInfo(info);
		return isOK;
	}
	
	/**
	 * 购物成功，更新用户信息
	 * @param info 待更新信息
	 * @return 是否成功
	 */
	public boolean updateUserInfo(UpdateInfo info) {
		boolean isOK = false;
		isOK = createOrderDao.updateUserInfo(info);
		return isOK;
	}
	
}
