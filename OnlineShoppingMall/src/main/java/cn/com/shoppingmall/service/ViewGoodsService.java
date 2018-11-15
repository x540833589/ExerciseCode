package cn.com.shoppingmall.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.shoppingmall.dao.ViewGoodsDao;
import cn.com.shoppingmall.domain.Goods;
import cn.com.shoppingmall.domain.Remark;
import cn.com.shoppingmall.relate.Praise;

@Service
public class ViewGoodsService {

	@Autowired
	private ViewGoodsDao viewGoodsDao;
	
	/**
	 * 获取该商品的详细信息
	 * @param goodsId 商品编号
	 * @return 商品对象
	 */
	public Goods getGoodsByGoodsId(Integer goodsId) {
		Goods goods = new Goods();
		goods = viewGoodsDao.getGoodsByGoodsId(goodsId);
		return goods;
	}
	
	/**
	 * 获取商品编号对应的所有评论
	 * @param goodsId 商品编号
	 * @return 评论列表 remarkList
	 */
	public List<Remark> getRemarkListByGoodsId(Integer goodsId) {
		List<Remark> remarkList = new ArrayList<>();
		remarkList = viewGoodsDao.getRemarkListByGoodsId(goodsId);
		return remarkList;
	}
	
	/**
	 * 根据记录总数和每页显示条目数动态生成页码索引
	 * @param goodsId 商品编号
	 * @param pageRecordAmount 每页记录数
	 * @return 页码索引集合
	 */
	public List<Integer> getPageIndex(Integer goodsId , Integer pageRecordAmount) {
		List<Integer> pageIndex = new ArrayList<>();
		Integer maxPageNumber = 0;
		Integer remarkAmount = getRemarkListByGoodsId(goodsId).size();
		if(remarkAmount % pageRecordAmount == 0)
			maxPageNumber = remarkAmount / pageRecordAmount;
		else
			maxPageNumber = remarkAmount / pageRecordAmount + 1;
		for(int i = 1 ; i <= maxPageNumber ; i++) {
			pageIndex.add(i);
		}
		return pageIndex;
	}
	
	/**
	 * 检查当前用户对当前商品点赞状态
	 * @param praise
	 * @return
	 */
	public boolean checkPraiseStatus(Praise praise) {
		boolean isOK = false;
		isOK = viewGoodsDao.checkPraiseStatus(praise);
		return isOK;
	}
	
	/**
	 * .通过类别名称查找相应的商品
	 * @param category 类别名
	 * @return 商品列表
	 */
	public List<Goods> getGoodsByCategory(String category) {
		List<Goods> goodsList = new LinkedList<>();
		goodsList = viewGoodsDao.getGoodsByCategory(category);
		return goodsList;
	}
	
	/**
	 * .根据记录总数和每页条目数动态生成页码索引
	 * @param category 种类
	 * @param recordAmount 每页数量
	 * @return 页码索引
	 */
	public List<Integer> getPageIndexByCategory(String category , Integer recordAmount) {
		List<Integer> pageIndex = new LinkedList<>();
		pageIndex = viewGoodsDao.getPageIndexByCategory(category , recordAmount);
		return pageIndex;
	}
	
}
