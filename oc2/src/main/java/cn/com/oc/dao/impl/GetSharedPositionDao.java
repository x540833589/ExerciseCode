package cn.com.oc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.oc.domain.Position;

@Repository
public class GetSharedPositionDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<Position> getAllSharedPosition() {
		List<Position> allSharedPositionList = new ArrayList<>();
		allSharedPositionList = sqlSessionTemplate.selectList("PositionMapper.getAllSharedPosition");
		return allSharedPositionList;
	}
	
}
