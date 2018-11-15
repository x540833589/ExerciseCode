package cn.com.oc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oc.vo.StaffDetail;

@Repository
public interface ShowStructureDetailDao {
	
	List<StaffDetail> getStructureDistribution(String structureDistribution);
	
	List<StaffDetail> getStructureDistributionLeader(String structureDistributionID);

	List<StaffDetail> getSpecialStructureLeader(); // pmo D5000
	
	List<StaffDetail> getSpecialStructureMember(); // pmo D5300
	
	List<StaffDetail> getFirstLevelDepartment(); 
	
//	List<StaffDetail> getDepartmentLeader(String deptID);
//	
//	List<StaffDetail> getFirstLevelStructure(String deptID);
//	
//	List<StaffDetail> getSecondLevelStructure(String deptID);
//	
//	List<StaffDetail> getThirdLevelStructure(String deptID);
//	
//	List<StaffDetail> getTeamMemberStructure(String deptID);
//	
//	List<StaffDetail> getDepartmentEmployee(String deptID);
	
}
