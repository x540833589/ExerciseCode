package cn.com.oc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.QueryInfo;
import cn.com.oc.vo.StaffDetail;

@Service
public interface StaffQueryService {
	
	List<StaffDetail> getAllStaffInfo(QueryInfo queryInfo);
	
	StaffDetail getStaffInfoDetails(Integer no);
	
	StaffDetail getCurrentNewStaffInfo();
	
	Department getDepartmentByDeptID(String deptID);
	
	Position getPositionByPositionID(Integer positionID);
	
}
