package cn.com.oc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.DepartmentPositionInfo;
import cn.com.oc.vo.NewDepartmentForm;

@Service
public interface DepartmentService {
	
	int addDepartment(NewDepartmentForm newDepartment);
	
	List<Department> getAllSuperiorDepartment();
	
	List<Department> getAllChildrenDepartmentByDeptID(String deptID);
	
	int addSharedPosition(DepartmentPositionInfo departmentPositionInfo);
	
	int addNewPosition(Position position);
	
	Position getNewestPosition();
	
}
