package cn.com.oc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.oc.dao.PositionDao;
import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.DepartmentPositionInfo;

@Repository
public class PositionDaoImpl implements PositionDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 为已存在的部门(可能多个)添加新职位(一个)或共享职位(可能多个)
	 * @param allDeptID 所有选择的部门的部门编号数组
	 * @param allExistedPositionID 所有选择的共享职位的职位编号数组
	 * @param newPosition 新职位对象
	 * @return 全部成功true , 失败false
	 */
	@Override
	public boolean addPosition(String[] allDeptID , Integer[] allExistedPositionID , Position newPosition) {
//		for(int i = 0 ; i < allDeptID.length ; i++)
//			System.out.println(allDeptID[i]);
//		for(int j = 0 ; j < allExistedPositionID.length ; j++)
//			System.out.println(allExistedPositionID[j]);
//		System.out.println(newPosition.getPosition_CN());
//		System.out.println(newPosition.getPosition_EN());
//		System.out.println(newPosition.getIsHeadStatus());
//		System.out.println(newPosition.getStructureDistribution());
		//添加新职位受影响的行数
		Integer rowAffectedAddNewPosition = 0;
		//添加新职位到关联表中的受影响的行数
		Integer rowAffectedAddNewPositionToTable = 0;
		//添加共享职位到关联表中的受影响的行数
		Integer rowAffectedExistedPosition = 0;
		//最新的职位的职位编号
		Integer newestPotisionID = 0;
		//所有添加操作是否成功的标志位(所有都成功为true , 否则为false)
		boolean isOK = true;
		//填写了新职位的信息，将新职位添加到数据库Position表中
		if(!newPosition.getPosition_CN().equals("")) {
			//新职位添加
			rowAffectedAddNewPosition = sqlSessionTemplate.insert("PositionMapper.addNewPosition" , newPosition);
			//添加失败
			if(rowAffectedAddNewPosition <= 0)
				isOK = false;
			else
				//获取新添加的职位的职位编号(no)
				newestPotisionID = (Integer)sqlSessionTemplate.selectOne("PositionMapper.getNewestPositionID");
		}
		//遍历部门列表，为每一个部门添加新职位或共享职位
		for(String currentDeptID : allDeptID) {
			//填写了新职位的信息
			if(!newPosition.getPosition_CN().equals("")) {
				//包装成DTO
				DepartmentPositionInfo departmentPositionInfo = new DepartmentPositionInfo();
				departmentPositionInfo.setDeptID(currentDeptID);
				departmentPositionInfo.setPositionID(newestPotisionID);
				//将部门和新职位的关联关系添加到表中
				rowAffectedAddNewPositionToTable = sqlSessionTemplate.insert("DepartmentPositionMapper.insertDepartmentPosition" , departmentPositionInfo);
				//添加失败
				if(rowAffectedAddNewPositionToTable <= 0)
					isOK = false;
			}
			//选择了一个或多个共享职位
			if(allExistedPositionID.length > 0) {
				//遍历共享职位编号列表，为每一个已选择的部门添加相应的关联关系
				for(Integer currentPositionID : allExistedPositionID) {
					//包装成DTO
					DepartmentPositionInfo departmentPositionInfo = new DepartmentPositionInfo();
					departmentPositionInfo.setDeptID(currentDeptID);
					departmentPositionInfo.setPositionID(currentPositionID);
					//检查这样的部门职位关联关系是否已经存在
					boolean isExisted = checkRecordExistedStatus(departmentPositionInfo);
					//若关联表中不存在这样的关联关系
					if(isExisted == false) {
						//添加关联关系
						rowAffectedExistedPosition = sqlSessionTemplate.insert("DepartmentPositionMapper.insertDepartmentPosition" , departmentPositionInfo);
						//添加失败
						if(rowAffectedExistedPosition <= 0)
							isOK = false;
					}
				}
			}
		}
		return isOK;
	}
	
	/**
	 * 检查待添加的关联关系是否已经存在(DeptID <--> PositionID)
	 * @param departmentPositionInfo
	 * @return 存在true , 不存在false
	 */
	private boolean checkRecordExistedStatus(DepartmentPositionInfo departmentPositionInfo) {
		boolean isExisted = true;
		DepartmentPositionInfo departmentPositionInfo2 = new DepartmentPositionInfo();
		//查询是否存在关联记录
		departmentPositionInfo2 = sqlSessionTemplate.selectOne("DepartmentPositionMapper.checkDepartmentPosition" , departmentPositionInfo);
		//查询结果为空，关联关系不存在
		if(departmentPositionInfo2 == null)
			isExisted = false;
		return isExisted;
	}

	/**
	 * 获得所有已经存在的部门
	 */
	@Override
	public List<Department> getAllExistedDepartment() {
		List<Department> allExistedDepartmentList = new ArrayList<>();
		allExistedDepartmentList = sqlSessionTemplate.selectList("DepartmentMapper.getAllExistedDepartment");
		return allExistedDepartmentList;
	}

}
