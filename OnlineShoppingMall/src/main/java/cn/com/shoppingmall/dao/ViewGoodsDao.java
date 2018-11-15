package cn.com.shoppingmall.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.shoppingmall.domain.Goods;
import cn.com.shoppingmall.domain.Remark;
import cn.com.shoppingmall.relate.Praise;

@Repository
public class ViewGoodsDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 获取该商品的详细信息
	 * @param goodsId 商品编号
	 * @return 商品对象
	 */
	public Goods getGoodsByGoodsId(Integer goodsId) {
		Goods goods = new Goods();
		goods = sqlSessionTemplate.selectOne("GoodsMapper.getGoodsByGoodsId" , goodsId);
		return goods;
	}
	
	/**
	 * 获取商品编号对应的所有评论
	 * @param goodsId 商品编号
	 * @return 评论列表 remarkList
	 */
	public List<Remark> getRemarkListByGoodsId(Integer goodsId) {
		List<Remark> remarkList = new ArrayList<>();
		remarkList = sqlSessionTemplate.selectList("RemarkMapper.getRemarkListByGoodsId" , goodsId);
		return remarkList;
	}
	
	public boolean checkPraiseStatus(Praise praise) {
		Praise praisee = new Praise();
		praisee = sqlSessionTemplate.selectOne("PraiseMapper.findPraise" , praise);
		if(praisee != null) 
			return true;
		else
			return false;
	}
	
	/**
	 * .通过类别名称查找相应的商品
	 * @param category 类别名
	 * @return 商品列表
	 */
	public List<Goods> getGoodsByCategory(String category) {
		List<Goods> goodsList = new LinkedList<>();
		goodsList = sqlSessionTemplate.selectList("GoodsMapper.getGoodsByCategory" , category);
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
		Integer maxPageNumber = 0;
		Integer size = getGoodsByCategory(category).size();
		if(size % recordAmount == 0)
			maxPageNumber = size / recordAmount;
		else
			maxPageNumber = size / recordAmount + 1;
		for(int i = 1 ; i <= maxPageNumber ; i++)
			pageIndex.add(i);
		return pageIndex;
	}
	
}
