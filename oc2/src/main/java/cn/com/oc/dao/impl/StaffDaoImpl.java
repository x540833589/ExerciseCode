package cn.com.oc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.oc.dao.StaffDao;
import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.domain.Staff;
import cn.com.oc.dto.DepartmentPositionInfo;
@Repository
public class StaffDaoImpl implements StaffDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer getNewStffNo() {
		
		return sqlSessionTemplate.selectOne("StaffMapper.getNewStaffNo");
	}

	@Override
	public String addStaff(Staff staff) {
		String result = sqlSessionTemplate.insert("StaffMapper.newStaff" , staff) > 0 ? "插入成功" : "插入失败";
		System.out.println("结果-------" + result);
		return result;
	}

	@Override
	public Staff getStaffByNo(Integer no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateStaff(Staff staff) {
		int result = sqlSessionTemplate.update("StaffMapper.updateStaff", staff);
		return result > 0 ? "修改成功" : "修改失败";
	}
	
	/**
	 * 获得添加员工页面三级联动
	 * @return
	 */
	@Override
	public List<Department> getAllDetail() {
		List<Department> details = new ArrayList<>();
		details = sqlSessionTemplate.selectList("StructureMapper.getAllDetail");
		return details;
	}

	/**
	 * 根据已选择的部门获取相应的职位
	 */
	@Override
	public List<Position> getAllPositionByDeptID(String deptID) {
		System.out.println(deptID);
		List<Position> allPosition = new ArrayList<>();
		allPosition = sqlSessionTemplate.selectList("PositionMapper.getAllPositionByDeptID" , deptID);
		return allPosition;
	}

	@Override
	public String checkHeadStatus(Integer positionID) {
		Integer checkCode = (Integer)sqlSessionTemplate.selectOne("PositionMapper.checkHeadStatus" , positionID);
		if(checkCode == 1)
			return "yes";
		else
			return "no";
	}

	@Override
	public List<Position> getAllExistedPosition() {
		List<Position> allHeadPositionList = new ArrayList<>();
		allHeadPositionList = sqlSessionTemplate.selectList("PositionMapper.getAllExistedPosition");
		return allHeadPositionList;
	}

	@Override
	public boolean insertDepartmentPosition(DepartmentPositionInfo departmentPositionInfo) {
		boolean isExisted = false;
		Integer rowAffected1 = 0;
		//Integer rowAffected2 = 0;
		DepartmentPositionInfo departmentPositionInfo2 = new DepartmentPositionInfo();
		//DepartmentPositionInfo parentDepartmentPositionInfo = new DepartmentPositionInfo();
		departmentPositionInfo2 = (DepartmentPositionInfo)sqlSessionTemplate.selectOne("DepartmentPositionMapper.checkDepartmentPosition" , departmentPositionInfo);
		//parentDepartmentPositionInfo = (DepartmentPositionInfo)sqlSessionTemplate.selectOne("DepartmentPositionMapper.checkParentDepartmentPosition" , departmentPositionInfo);
		//已经存在这样的关联记录了，什么也不做
		if(departmentPositionInfo2 != null/*&& parentDepartmentPositionInfo != null*/)
			isExisted = true;
		//不存在这样的关联记录,就把传过来的对象插入数据库表中(该部门下应该存在一个这样的职位)
		else {
			/*if(parentDepartmentPositionInfo == null)
				rowAffected2 = sqlSessionTemplate.insert("DepartmentPositionMapper.insertParentDepartmentPosition" , departmentPositionInfo);*/
			if(departmentPositionInfo2 == null)
				rowAffected1 = sqlSessionTemplate.insert("DepartmentPositionMapper.insertDepartmentPosition" , departmentPositionInfo);
			//插入成功修改标志位
			if(rowAffected1 > 0/* && rowAffected2 > 0*/)
				isExisted = true;
		}
		//返回执行结果状态
		return isExisted;
	}

	//通过编号获取员工照片路径
	@Override
	public String getPhotoByNo(Integer no) {
		String photo = "";
		photo = sqlSessionTemplate.selectOne("StaffMapper.getPhotoPathByNo" , no);
		return photo;
	}

}