package cn.com.oc.service;

import org.springframework.stereotype.Service;

import cn.com.oc.domain.Administrator;

@Service
public interface LoginService {
	
	//数据库中查出用户名是否存在
	Administrator validate(Administrator admin);
	
}
