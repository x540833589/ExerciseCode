package cn.com.oc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.oc.dao.DepartmentDao;
import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.DepartmentPositionInfo;
import cn.com.oc.service.DepartmentService;
import cn.com.oc.vo.NewDepartmentForm;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public int addDepartment(NewDepartmentForm newDepartmentForm) {
		Department newDepartment = new Department();
		newDepartment.setBu_Head(newDepartmentForm.getBu_Head());
		newDepartment.setDeptID(newDepartmentForm.getDeptID());
		newDepartment.setDepartment_CN(newDepartmentForm.getDepartment_CN());
		newDepartment.setDepartment_EN(newDepartmentForm.getDepartment_EN());
		newDepartment.setUpOneLevel(newDepartmentForm.getUpOneLevel());
		newDepartment.setDeptLevel(newDepartmentForm.getDeptLevel());
		return departmentDao.addDepartment(newDepartment);
	}

	@Override
	public List<Department> getAllSuperiorDepartment() {
		List<Department> superiorDepartmentList = new ArrayList<>();
		superiorDepartmentList = departmentDao.getAllSuperiorDepartment();
		return superiorDepartmentList;
	}

	@Override
	public List<Department> getAllChildrenDepartmentByDeptID(String deptID) {
		List<Department> childrenDepartmentList = new ArrayList<>();
		childrenDepartmentList = departmentDao.getAllChildrenDepartmentByDeptID(deptID);
		return childrenDepartmentList;
	}

	@Override
	public int addSharedPosition(DepartmentPositionInfo departmentPositionInfo) {
		Integer rowAffected = 0;
		rowAffected = departmentDao.addSharedPosition(departmentPositionInfo);
		return rowAffected;
	}

	@Override
	public int addNewPosition(Position position) {
		Integer rowAffected = 0;
		rowAffected = departmentDao.addNewPosition(position);
		return rowAffected;
	}

	@Override
	public Position getNewestPosition() {
		Position position = new Position();
		position = departmentDao.getNewestPosition();
		return position;
	}
	
}
