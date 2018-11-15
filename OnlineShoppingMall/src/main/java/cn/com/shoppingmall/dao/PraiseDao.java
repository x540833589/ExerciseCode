package cn.com.shoppingmall.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.shoppingmall.relate.Praise;

@Repository
public class PraiseDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 点赞
	 * @param praise
	 * @return
	 */
	public boolean addPraise(Praise praise) {
		Integer isOK1 = 0;
		Integer isOK2 = 0;
		//加入点赞关联表
		isOK1 = sqlSessionTemplate.insert("PraiseMapper.addPraise" , praise);
		//更新商品信息
		isOK2 = sqlSessionTemplate.update("GoodsMapper.addPraise" , praise.getGoodsId());
		if(isOK1 * isOK2 > 0)
			return true;
		else
			return false;
	}
	
	/**
	 * 取消赞
	 * @param praise
	 * @return
	 */
	public boolean cancelPraise(Praise praise) {
		Integer isOK1 = 0;
		Integer isOK2 = 0;
		isOK1 = sqlSessionTemplate.delete("PraiseMapper.cancelPraise" , praise);
		isOK2 = sqlSessionTemplate.update("GoodsMapper.cancelPraise" , praise.getGoodsId());
		if(isOK1 * isOK2 > 0)
			return true;
		else
			return false;
	}
	
}
