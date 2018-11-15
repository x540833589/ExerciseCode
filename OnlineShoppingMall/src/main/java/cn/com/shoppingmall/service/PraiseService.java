package cn.com.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.shoppingmall.dao.PraiseDao;
import cn.com.shoppingmall.relate.Praise;

@Service
public class PraiseService {

	@Autowired
	private PraiseDao praiseDao;
	
	/**
	 * 点赞
	 * @param praise
	 * @return
	 */
	public boolean addPraise(Praise praise) {
		boolean isOK = false;
		isOK = praiseDao.addPraise(praise);
		return isOK;
	}
	
	/**
	 * 取消赞
	 * @param praise
	 * @return
	 */
	public boolean cancelPraise(Praise praise) {
		boolean isOK = false;
		isOK = praiseDao.cancelPraise(praise);
		return isOK;
	}
	
}
