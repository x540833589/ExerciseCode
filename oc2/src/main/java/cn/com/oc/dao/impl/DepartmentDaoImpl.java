package cn.com.oc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.oc.dao.DepartmentDao;
import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.DepartmentPositionInfo;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int addDepartment(Department newDepartment) {
		int rowAffected1 = 0;
		int rowAffected2 = 0;
		System.out.println(newDepartment.getUpOneLevel().equals("D1000"));
		if(!newDepartment.getUpOneLevel().equals("D1000"))
			rowAffected1 = sqlSessionTemplate.update("DepartmentMapper.updateChildNumByUpOneLevel" , newDepartment.getUpOneLevel());
		else
			rowAffected1 = 1;
		rowAffected2 = sqlSessionTemplate.insert("DepartmentMapper.addDepartment", newDepartment);
		if(rowAffected1 != 0 && rowAffected2 != 0)
			return 1;
		else
			return 0;
	}

	@Override
	public List<Department> getAllSuperiorDepartment() {
		List<Department> superiorDepartmentList = new ArrayList<>();
		superiorDepartmentList = sqlSessionTemplate.selectList("DepartmentMapper.getAllSuperiorDepartment");
		return superiorDepartmentList;
	}

	@Override
	public List<Department> getAllChildrenDepartmentByDeptID(String deptID) {
		List<Department> childrenDepartmentList = new ArrayList<>();
		childrenDepartmentList = sqlSessionTemplate.selectList("DepartmentMapper.getChildrenDepartment" , deptID);
		return childrenDepartmentList;
	}

	@Override
	public int addSharedPosition(DepartmentPositionInfo departmentPositionInfo) {
		Integer rowAffected = 0;
		rowAffected = sqlSessionTemplate.insert("DepartmentPositionMapper.updateDepartmentPosition" , departmentPositionInfo);
		return rowAffected;
	}

	@Override
	public int addNewPosition(Position position) {
		Integer rowAffected = 0;
		rowAffected = sqlSessionTemplate.insert("DepartmentMapper.addNewPosition" , position);
		return rowAffected;
	}

	@Override
	public Position getNewestPosition() {
		Position position = new Position();
		position = sqlSessionTemplate.selectOne("DepartmentMapper.getNewestPosition");
		return position;
	}

}
