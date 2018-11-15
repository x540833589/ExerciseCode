package cn.com.oc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.oc.dao.StaffQueryDao;
import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.QueryInfo;
import cn.com.oc.service.StaffQueryService;
import cn.com.oc.vo.StaffDetail;

@Service
public class StaffQueryServiceImpl implements StaffQueryService {
	
	@Autowired
	StaffQueryDao staffQueryDao;
	
	@Override
	/**
	 * 获得跟查询信息相关的所有员工的详细信息
	 */
	public List<StaffDetail> getAllStaffInfo(QueryInfo queryInfo) {
		String fuzzyString = "";
		if(!queryInfo.getDeptID().equals("")) {
			//获取传入的部门编号
			String currentDeptID = queryInfo.getDeptID();
			//进行模糊匹配字符串的拼接
			if(currentDeptID.endsWith("000")) {
				fuzzyString = currentDeptID.substring(0, 2);
				fuzzyString += "%";
				queryInfo.setDeptID(fuzzyString);
			}else if(currentDeptID.endsWith("00")) {
				fuzzyString = currentDeptID.substring(0, 3);
				fuzzyString += "%";
				queryInfo.setDeptID(fuzzyString);
			}else if(currentDeptID.endsWith("0")) {
				fuzzyString = currentDeptID.substring(0, 4);
				fuzzyString += "%";
				queryInfo.setDeptID(fuzzyString);
			}
		}
		System.out.println("查询的职位中文名为:" + queryInfo.getPosition_CN());
		List<StaffDetail> allStaffInfoList = new ArrayList<>();
		allStaffInfoList = staffQueryDao.getAllStaffInfo(queryInfo);
		return allStaffInfoList;
	}

	@Override
	public StaffDetail getStaffInfoDetails(Integer no) {
		StaffDetail staffDetails = new StaffDetail();
		staffDetails = staffQueryDao.getStaffInfoDetails(no);
		return staffDetails;
	}

	@Override
	public StaffDetail getCurrentNewStaffInfo() {
		StaffDetail staffDetails = new StaffDetail();
		staffDetails = staffQueryDao.getCurrentNewStaffInfo();
		return staffDetails;
	}
	
	public Department getDepartmentByDeptID(String deptID) {
		Department department = new Department();
		department = staffQueryDao.getDepartmentByDeptID(deptID);
		return department;
	}

	@Override
	public Position getPositionByPositionID(Integer positionID) {
		Position position = new Position();
		position = staffQueryDao.getPositionByPositionID(positionID);
		return position;
	}

}
