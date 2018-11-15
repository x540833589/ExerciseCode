package cn.com.shoppingmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.shoppingmall.dao.DeliverRemarkDao;
import cn.com.shoppingmall.domain.Remark;

@Service
public class DeliverRemarkService {

	@Autowired
	private DeliverRemarkDao deliverRemarkDao;
	
	public boolean deliverRemark(Remark remark) {
		boolean isOK = false;
		isOK = deliverRemarkDao.deliverRemark(remark);
		return isOK;
	}
	
}
