package cn.com.oc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.oc.dao.StaffQueryDao;
import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.QueryInfo;
import cn.com.oc.vo.StaffDetail;
@Repository
public class StaffQueryDaoImpl implements StaffQueryDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<StaffDetail> getAllStaffInfo(QueryInfo queryInfo) {
		List<StaffDetail> allStaffInfoList = new ArrayList<>();
		allStaffInfoList = sqlSessionTemplate.selectList("QueryStaffMapper.allStaffDetails", queryInfo);
		return allStaffInfoList;
	}

	@Override
	public StaffDetail getStaffInfoDetails(Integer no) {
		StaffDetail staffDetails = new StaffDetail();
		staffDetails = sqlSessionTemplate.selectOne ("QueryStaffMapper.StaffDetails", no);
		return staffDetails;
	}

	@Override
	public StaffDetail getCurrentNewStaffInfo() {
		StaffDetail staffDetails = new StaffDetail();
		staffDetails = sqlSessionTemplate.selectOne ("StaffMapper.getNewStaffInfo");
		return staffDetails;
	}
	
	public Department getDepartmentByDeptID(String deptID) {
		Department department = new Department();
		department = sqlSessionTemplate.selectOne("DepartmentMapper.getDepartmentByDeptID" , deptID);
		return department;
	}
	
	public Position getPositionByPositionID(Integer positionID) {
		Position position = new Position();
		position = sqlSessionTemplate.selectOne("PositionMapper.getPositionByPositionId" , positionID);
		return position;
	}

}
