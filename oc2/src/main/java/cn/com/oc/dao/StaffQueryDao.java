package cn.com.oc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.QueryInfo;
import cn.com.oc.vo.StaffDetail;

@Repository
public interface StaffQueryDao {
	
	List<StaffDetail> getAllStaffInfo(QueryInfo queryInfo);
	
	StaffDetail getStaffInfoDetails(Integer no);
	
	StaffDetail getCurrentNewStaffInfo();
	
	Department getDepartmentByDeptID(String deptID);
	
	Position getPositionByPositionID(Integer positionID);
	
}
