package cn.com.shoppingmall.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.shoppingmall.dao.PageJumpDao;
import cn.com.shoppingmall.domain.Goods;
import cn.com.shoppingmall.domain.Remark;
import cn.com.shoppingmall.domain.User;
import cn.com.shoppingmall.relate.OrderItem;
import cn.com.shoppingmall.relate.PageInfo;

@Service
public class PageJumpService {
	
	@Autowired
	private PageJumpDao pageJumpDao;
	
	public List<Goods> getSpecifiedPageGoods(PageInfo pageInfo) {
		List<Goods> specifiedPageGoodsList = new ArrayList<>();
		specifiedPageGoodsList = pageJumpDao.getSpecifiedPageGoods(pageInfo);
		return specifiedPageGoodsList;
	}
	
	public List<Integer> getGoodsPageIndex(PageInfo pageInfo) {
		Integer currentPageRecordAmount = pageInfo.getPageRecordAmount();
		Integer allGoodsAmount = pageJumpDao.getAllGoodsAmount();
		Integer maxPageNumber = 0;
		List<Integer> pageIndex = new ArrayList<>();
		if(allGoodsAmount % currentPageRecordAmount != 0)
			maxPageNumber = allGoodsAmount / currentPageRecordAmount + 1;
		if(allGoodsAmount % currentPageRecordAmount == 0)
			maxPageNumber = allGoodsAmount / currentPageRecordAmount;
		for(int i = 1 ; i <= maxPageNumber ; i++) {
			pageIndex.add(i);
		}
		return pageIndex;
	}
	
	public Goods getGoodsByGoodsId(Integer goodsId) {
		Goods goods = new Goods();
		goods = pageJumpDao.getGoodsByGoodsId(goodsId);
		return goods;
	}
	
	/**
	 * .通过用户编号获取该用户的所有购买记录
	 * @param userId 用户编号
	 * @return 订单商品集合
	 */
	public List<OrderItem> getOrderItemListByUserId(Integer userId) {
		List<OrderItem> orderItemList = new ArrayList<>();
		orderItemList = pageJumpDao.getOrderItemListByUserId(userId);
		return orderItemList;
	}
	
	/**
	 * .获得当前用户订单页码索引的方法
	 * @param userId 用户编号
	 * @param recordAmount 每页记录数
	 * @return 索引集合
	 */
	public List<Integer> getOrderPageIndex(Integer userId , Integer recordAmount) {
		//默认每页五条
		if(recordAmount == null)
			recordAmount = 5;
		Integer maxPageNumber = 0;
		List<Integer> orderPageIndex = new LinkedList<>();
		Integer size = getOrderItemListByUserId(userId).size();
		if(size % recordAmount == 0)
			maxPageNumber = size / recordAmount;
		else
			maxPageNumber = size / recordAmount + 1;
		for(int i = 1 ; i <= maxPageNumber ; i++) {
			orderPageIndex.add(i);
		}
		return orderPageIndex;
	}
	
	/**
	 * .通过用户编号获取该用户发表的所有评论
	 * @param userId 用户编号
	 * @return 评论集合
	 */
	public List<Remark> getRemarkListByUserId(Integer userId) {
		List<Remark> remarkList = new ArrayList<>();
		remarkList = pageJumpDao.getRemarkListByUserId(userId);
		return remarkList;
	}
	
	/**
	 * .获得当前用户评论页码索引的方法
	 * @param userId 用户编号
	 * @param recordAmount 每页记录数
	 * @return 索引集合
	 */
	public List<Integer> getRemarkPageIndex(Integer userId , Integer recordAmount) {
		//默认每页五条
		if(recordAmount == null)
			recordAmount = 5;
		Integer maxPageNumber = 0;
		List<Integer> remarkPageIndex = new LinkedList<>();
		Integer size = getRemarkListByUserId(userId).size();
		if(size % recordAmount == 0)
			maxPageNumber = size / recordAmount;
		else
			maxPageNumber = size / recordAmount + 1;
		for(int i = 1 ; i <= maxPageNumber ; i++) {
			remarkPageIndex.add(i);
		}
		return remarkPageIndex;
	}
	
	public User getUserByUserName(String username) {
		User user = new User();
		user = pageJumpDao.getUserByUserName(username);
		return user;
	}
	
}
