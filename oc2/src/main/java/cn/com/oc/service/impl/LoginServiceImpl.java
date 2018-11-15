package cn.com.oc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.oc.dao.LoginDao;
import cn.com.oc.domain.Administrator;
import cn.com.oc.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private LoginDao loginDao;
	
	@Override
	public Administrator validate(Administrator admin) {
		Administrator administrator = new Administrator();
		administrator = loginDao.validate(admin);
		return administrator;
	}

}
