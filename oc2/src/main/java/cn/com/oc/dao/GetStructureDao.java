package cn.com.oc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oc.domain.Department;


@Repository
public interface GetStructureDao {
	
	List<Department> getFirstLevelDepartment();
	
	List<Department> getChildrenDepartment(String deptID);
	
}