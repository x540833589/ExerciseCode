package cn.com.oc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.oc.dao.GetStructureDao;
import cn.com.oc.domain.Department;


@Repository
public class GetStructureDaoImpl implements GetStructureDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * 获得一级部门的方法
	 */
	@Override
	public List<Department> getFirstLevelDepartment() {
		List<Department> firstLevelDepartmentList = new ArrayList<>();
		firstLevelDepartmentList = sqlSessionTemplate.selectList("DepartmentMapper.getFirstLevelDepartment");
		return firstLevelDepartmentList;
	}

	/**
	 * 获得当前部门的所有子部门的方法
	 */
	@Override
	public List<Department> getChildrenDepartment(String deptID) {
		List<Department> childrenDepartmentList = new ArrayList<>();
		childrenDepartmentList = sqlSessionTemplate.selectList("DepartmentMapper.getChildrenDepartment" , deptID);
		return childrenDepartmentList;
	}
	
}
