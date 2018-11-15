package cn.com.shoppingmall.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.shoppingmall.domain.Remark;

@Repository
public class DeliverRemarkDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public boolean deliverRemark(Remark remark) {
		Integer rowAffected = 0;
		Integer rowAffected2 = 0;
		boolean isOK = false;
		//评论存入数据库
		rowAffected = sqlSessionTemplate.insert("RemarkMapper.deliverRemark" , remark);
		//更新商品信息
		rowAffected2 = sqlSessionTemplate.update("GoodsMapper.updateNumberOfRemark" , remark.getGoodsId());
		if(rowAffected > 0 && rowAffected2 > 0)
			isOK = true;
		return isOK;
	}
	
}
