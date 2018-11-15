package cn.com.oc.dao;

import org.springframework.stereotype.Repository;

import cn.com.oc.domain.Administrator;
@Repository
public interface LoginDao {
	
	//数据库中查出用户名是否存在
	Administrator validate(Administrator admin);
	
}
