package cn.com.oc.service;

import java.util.List;


import org.springframework.stereotype.Service;

import cn.com.oc.vo.StaffDetail;

@Service
public interface ShowStructureDetailService {
	
	List<StaffDetail> getStructureDistribution(String deptID);
	
	List<StaffDetail> getStructureDistributionLeader(String deptID);
	
	List<StaffDetail> getSpecialStructureLeader(); // pmo D5000
	
	List<StaffDetail> getSpecialStructureMember(); // pmo D5300
	
	List<StaffDetail> getFirstLevelDepartment(); 

//	List<StaffDetail> getDepartmentMember(String deptID);
	
//	List<StaffDetail> getFirstLevelStructure(String deptID);
//	
//	List<StaffDetail> getSecondLevelStructure(String deptID);
//	
//	List<StaffDetail> getThirdLevelStructure(String deptID);
//	
//	List<StaffDetail> getTeamMemberStructure(String deptID);
	
//	List<StaffDetail> getDepartmentEmployee(String deptID);
	
}
