package cn.com.oc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;

@Repository
public interface PositionDao {

	boolean addPosition(String[] allDeptID , Integer[] allExistedPositionID , Position newPosition);
	
	List<Department> getAllExistedDepartment();
	
}
