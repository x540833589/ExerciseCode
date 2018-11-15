package cn.com.oc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.oc.dao.ShowStructureDetailDao;
import cn.com.oc.vo.StaffDetail;
@Repository
public class ShowStructureDetailDaoImpl implements ShowStructureDetailDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<StaffDetail> getStructureDistribution(String structureDistribution) {
		List<StaffDetail> departmentMemberList = new ArrayList<>();
		departmentMemberList = sqlSessionTemplate.selectList("StructureMapper.getStructureMemberByStructureDistribution" , structureDistribution);
		return departmentMemberList;
	}

	@Override
	public List<StaffDetail> getStructureDistributionLeader(String structureDistributionID) {
		List<StaffDetail> departmentLeaderList = new ArrayList<>();
		departmentLeaderList = sqlSessionTemplate.selectList("StructureMapper.getStructureLeaderByStructureDistributionID" , structureDistributionID);
		return departmentLeaderList;
	}
	
	@Override
	public List<StaffDetail> getSpecialStructureLeader() {
		List<StaffDetail> specialLeaderList = new ArrayList<>();
		specialLeaderList = sqlSessionTemplate.selectList("StructureMapper.getSpecialStructureLeader");
		return specialLeaderList;
	}

	@Override
	public List<StaffDetail> getSpecialStructureMember() {
		List<StaffDetail> specialMemberList = new ArrayList<>();
		specialMemberList = sqlSessionTemplate.selectList("StructureMapper.getSpecialStructureMember");
		return specialMemberList;
	}

	@Override
	public List<StaffDetail> getFirstLevelDepartment() {
		List<StaffDetail> staffDeatailList = new ArrayList<>();
		staffDeatailList = sqlSessionTemplate.selectList("StructureMapper.getFirstLevelDepartment");
		return staffDeatailList;
	}

//	/**
//	 * 查询所有层级的leader
//	 */
//	@Override
//	public List<StaffDetail> getDepartmentLeader(String deptID) {
//		List<StaffDetail> departmentLeaderList = new ArrayList<>();
//		departmentLeaderList = sqlSessionTemplate.selectList("StructureMapper.getStructureLeaderByDeptID" , deptID);
//		return departmentLeaderList;
//	}
//
//	/**
//	 * 查询一级部门（除了总经办）
//	 */
//	@Override
//	public List<StaffDetail> getFirstLevelStructure(String deptID) {
//		List<StaffDetail> departmentLeaderList = new ArrayList<>();
//		departmentLeaderList = sqlSessionTemplate.selectList("StructureMapper.getFirstStructureByDeptID" , deptID);
//		return departmentLeaderList;
//	}
//
//	/**
//	 * 查询所有二级部门（DEV_1）
//	 */
//	@Override
//	public List<StaffDetail> getSecondLevelStructure(String deptID) {
//		List<StaffDetail> departmentLeaderList = new ArrayList<>();
//		departmentLeaderList = sqlSessionTemplate.selectList("StructureMapper.getSecondStructureByDeptID" , deptID);
//		return departmentLeaderList;
//	}
//
//	/**
//	 * 查询所有三级部门（TEAM_1）
//	 */
//	@Override
//	public List<StaffDetail> getThirdLevelStructure(String deptID) {
//		List<StaffDetail> departmentLeaderList = new ArrayList<>();
//		departmentLeaderList = sqlSessionTemplate.selectList("StructureMapper.getThirdStructureByDeptID" , deptID);
//		return departmentLeaderList;
//	}
//
//	/**
//	 * 查询所有teamMember
//	 */
//	@Override
//	public List<StaffDetail> getTeamMemberStructure(String deptID) {
//		List<StaffDetail> departmentLeaderList = new ArrayList<>();
//		departmentLeaderList = sqlSessionTemplate.selectList("StructureMapper.getTeamMemberStructureByDeptID" , deptID);
//		return departmentLeaderList;
//	}

//	@Override
//	public List<StaffDetail> getDepartmentEmployee(String deptID) {
//		List<StaffDetail> departmentEmployeeList = new ArrayList<>();
//		departmentEmployeeList = sqlSessionTemplate.selectList("StructureMapper.getStructureEmployeeByDeptID" , deptID);
//		return departmentEmployeeList;
//	}

}
