package cn.com.shoppingmall.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.shoppingmall.domain.Order;
import cn.com.shoppingmall.relate.OrderItem;
import cn.com.shoppingmall.relate.UpdateInfo;

@Repository
public class CreateOrderDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * .存入某一条购物记录
	 * @param orderItem 一条商品记录
	 * @return 受影响的行数
	 */
	public Integer addOrderItem(OrderItem orderItem) {
		Integer isOK = 0;
		isOK = sqlSessionTemplate.insert("OrderItemMapper.addOrderItem" , orderItem);
		return isOK;
	}
	
	/**
	 * .获取当前最大的订单编号
	 * @return 最大的订单编号
	 */
	public Integer getLatestOrderId() {
		Integer max = 0;
		max = (Integer)sqlSessionTemplate.selectOne("OrderItemMapper.getPreviousOrderId");
		return max;
	}
	
	/**
	 * 创建订单
	 * @param order 订单
	 * @return 受影响的行数
	 */
	public Integer createOrder(Order order) {
		Integer isOK = 0;
		isOK = sqlSessionTemplate.insert("OrderMapper.createOrder" , order);
		return isOK;
	}
	
	/**
	 * 购物成功，更新商品信息
	 * @param info 待更新信息
	 * @return 是否成功
	 */
	public boolean updateGoodsInfo(UpdateInfo info) {
		Integer isOK = 0;
		isOK = sqlSessionTemplate.update("GoodsMapper.updateGoodsInfo" , info);
		return isOK > 0 ? true : false;
	}
	
	/**
	 * 购物成功，更新用户信息
	 * @param info 待更新信息
	 * @return 是否成功
	 */
	public boolean updateUserInfo(UpdateInfo info) {
		Integer isOK = 0;
		isOK = sqlSessionTemplate.update("UserMapper.updateUserInfo" , info);
		return isOK > 0 ? true : false;
	}
	
}
