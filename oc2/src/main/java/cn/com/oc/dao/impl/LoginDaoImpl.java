package cn.com.oc.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.oc.dao.LoginDao;
import cn.com.oc.domain.Administrator;

@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	SqlSessionTemplate sqlsessionTemplate;

	@Override
	public Administrator validate(Administrator admin) {
		Administrator administrator = new Administrator();
		administrator = sqlsessionTemplate.selectOne("LoginMapper.validate" , admin);
		return administrator;
	}
	
}
