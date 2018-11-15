package cn.com.oc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.oc.dto.DepartmentPositionInfo;
import cn.com.oc.vo.StaffDetail;

@Repository
public class CheckPositionIsActiveDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * .检索员工表中是否已经存在这样的(总监、经理、组长)职位
	 * @param departmentPositionInfo
	 * @return
	 */
	public StaffDetail checkPositionActiveStatus(DepartmentPositionInfo departmentPositionInfo) {
		List<StaffDetail> staffDetailList = new ArrayList<>();
		//使用selectList的原因:查询的部门下的职位可能有多人存在(如开发工程师)
		staffDetailList = sqlSessionTemplate.selectList("StaffMapper.checkPositionIsActiveStatus" , departmentPositionInfo);
		//如果该部门下该职位没有人，返回空
		if(staffDetailList.size() == 0)
			return null;
		//有一个或多个人，返回集合的第一个元素(多个人的情况下每一个元素的部门职位信息都是一样的，返回一个即可)
		else
			return staffDetailList.get(0);
	}
	
}
