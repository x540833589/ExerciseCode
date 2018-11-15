package cn.com.oc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.oc.dao.ShowStructureDetailDao;
import cn.com.oc.service.ShowStructureDetailService;
import cn.com.oc.vo.StaffDetail;

@Service
public class ShowStructureDetailServiceImpl implements ShowStructureDetailService {

	@Autowired
	private ShowStructureDetailDao showStructureDetailDao;

	@Override
	public List<StaffDetail> getStructureDistribution(String deptID) {
		String structureDistribution = "";
		List<StaffDetail> StructureDistributionList = new ArrayList<>();
		if(deptID.endsWith("000")) {
			if(deptID.equals("D1000")) {
				String sub = deptID.substring(2);
				sub = "%" + sub + "4";
				structureDistribution = sub;
			}else {
				String sub1 = deptID.substring(1,2);
				String sub2 = deptID.substring(3);
				sub2 = sub1 + "%" + sub2 + "3";
				structureDistribution = sub2;
			}
//			StructureDistributionList = showStructureDetailDao.getStructureDistribution(structureDistribution);
		}else if(deptID.endsWith("00")) {
			String sub1 = deptID.substring(1,3);
			String sub2 = deptID.substring(4);
			sub2 = sub1 + "%"+ sub2 + "2";
			structureDistribution = sub2;
//			StructureDistributionList = showStructureDetailDao.getStructureDistribution(structureDistribution);
		}else if(deptID.endsWith("0")) {
				String sub = deptID.substring(1);
				sub += "1";
				structureDistribution = sub;
//				StructureDistributionList = showStructureDetailDao.getStructureDistribution(structureDistribution);
		}
		StructureDistributionList = showStructureDetailDao.getStructureDistribution(structureDistribution);
		return StructureDistributionList;
	}

	@Override
	public List<StaffDetail> getStructureDistributionLeader(String deptID) {
		String structureDistribution = "";
		List<StaffDetail> StructureDistributionLeaderList = new ArrayList<>();
		structureDistribution = deptID.substring(1);
		if(deptID.endsWith("000")) {
			if(deptID.equals("D1000")) {
				structureDistribution += "5";
			}else {
				structureDistribution += "4";
			}	
		}else if(deptID.endsWith("00")) {
			structureDistribution += "3";
		}else if(deptID.endsWith("0")) {
			structureDistribution += "2";
		}
//		int structureDistributionID = Integer.parseInt(structureDistribution);
		StructureDistributionLeaderList = showStructureDetailDao.getStructureDistributionLeader(structureDistribution);
		return StructureDistributionLeaderList;
	}
	
	@Override
	public List<StaffDetail> getSpecialStructureLeader() {
		List<StaffDetail> specialLeaderList = new ArrayList<>();
		specialLeaderList = showStructureDetailDao.getSpecialStructureLeader();
		return specialLeaderList;
	}

	@Override
	public List<StaffDetail> getSpecialStructureMember() {
		List<StaffDetail> specialMemberList = new ArrayList<>();
		specialMemberList = showStructureDetailDao.getSpecialStructureMember();
		return specialMemberList;
		
	}

	@Override
	public List<StaffDetail> getFirstLevelDepartment() {
		List<StaffDetail> staffDetailList = new ArrayList<>();
		staffDetailList = showStructureDetailDao.getSpecialStructureMember();
		return staffDetailList;
	}
	
//	/**
//	 * 获取当前部门编号对应的leader
//	 */
//	@Override
//	public List<StaffDetail> getDepartmentLeader(String deptID) {
//		List<StaffDetail> departmentLeaderList = new ArrayList<>();
//		departmentLeaderList = showStructureDetailDao.getDepartmentLeader(deptID);
//		return departmentLeaderList;
//	}

	/**
	 * 获取当前部门编号对应的下级员工
	 */
//	@Override
//	public List<StaffDetail> getDepartmentMember(String deptID) {
//		List<StaffDetail> departmentMemberList = new ArrayList<>();
//		if(deptID.equals("D1000")) {
//			//当传D1000时查Department， 当传D2000-D8000时查DEV
//			departmentMemberList = showStructureDetailDao.getFirstLevelStructure(deptID);
//		}else if(deptID.endsWith("000") && !deptID.equals("D1000")) {
//			departmentMemberList = showStructureDetailDao.getSecondLevelStructure(deptID);
//		}else if(deptID.endsWith("00")) {
//			departmentMemberList = showStructureDetailDao.getThirdLevelStructure(deptID);
//		}else if(deptID.endsWith("0")) {
//			departmentMemberList = showStructureDetailDao.getTeamMemberStructure(deptID);
//		}
//		return departmentMemberList;
//	}

	

//	@Override
//	public List<StaffDetail> getDepartmentEmployee(String deptID) {
//		List<StaffDetail> departmentEmployeeList = new ArrayList<>();
//		departmentEmployeeList = showStructureDetailDao.getDepartmentEmployee(deptID);
//		return departmentEmployeeList;
//	}

}
