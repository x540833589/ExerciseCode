package cn.com.shoppingmall.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.shoppingmall.domain.Goods;
import cn.com.shoppingmall.domain.Remark;
import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.relate.OrderItem;
import cn.com.shoppingmall.relate.PageInfo;

@Repository
public class PageJumpDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Goods> getSpecifiedPageGoods(PageInfo pageInfo) {
		List<Goods> pageSpecifiedGoodsList = new ArrayList<>();
		pageSpecifiedGoodsList = sqlSessionTemplate.selectList("GoodsMapper.getSpecifiedPageGoods" , pageInfo);
		return pageSpecifiedGoodsList;
	}
	
	/**
	 * 获得所有数据库中所有商品的数量
	 * @return
	 */
	public Integer getAllGoodsAmount() {
		Integer amount = (Integer)sqlSessionTemplate.selectOne("GoodsMapper.getAllGoodsAmount");
		return amount;
	}
	
	public Goods getGoodsByGoodsId(Integer goodsId) {
		Goods goods = new Goods();
		goods = (Goods)sqlSessionTemplate.selectOne("GoodsMapper.getGoodsByGoodsId" , goodsId);
		return goods;
	}
	
	/**
	 * 通过用户编号获取该用户的所有订单
	 * @param userId 用户编号
	 * @return 订单集合
	 */
	public List<OrderItem> getOrderItemListByUserId(Integer userId) {
		List<OrderItem> orderItemList = new ArrayList<>();
		orderItemList = sqlSessionTemplate.selectList("OrderItemMapper.getOrderItemListByUserId" , userId);
		return orderItemList;
	}
	
	/**
	 * 通过用户编号获取该用户发表的所有评论
	 * @param userId 用户编号
	 * @return 评论集合
	 */
	public List<Remark> getRemarkListByUserId(Integer userId) {
		List<Remark> remarkList = new ArrayList<>();
		remarkList = sqlSessionTemplate.selectList("RemarkMapper.getRemarkListByUserId" , userId);
		return remarkList;
	}
	
	public User getUserByUserName(String username) {
		User user = new User();
		user = sqlSessionTemplate.selectOne("UserMapper.getUserByUserName" , username);
		return user;
	}
	
}
