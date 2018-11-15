package cn.com.oc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.DepartmentPositionInfo;

@Repository
public interface DepartmentDao {

	int addDepartment(Department newDepartment);
	
	List<Department> getAllSuperiorDepartment();
	
	List<Department> getAllChildrenDepartmentByDeptID(String deptID);
	
	int addSharedPosition(DepartmentPositionInfo departmentPositionInfo);
	
	int addNewPosition(Position position);
	
	Position getNewestPosition();
}
