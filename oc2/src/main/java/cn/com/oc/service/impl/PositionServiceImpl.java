package cn.com.oc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.oc.dao.PositionDao;
import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.AddPositionForm;
import cn.com.oc.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {

	@Autowired
	private PositionDao positionDao;
	
	/**
	 * .添加新职位、新的部门职位关联关系的方法
	 */
	@Override
	public boolean addPosition(AddPositionForm addPositionForm) {
		//插入数据成功与否的标志位
		boolean isOK = false;
		//获取所有用户提交的选中部门的部门编号数组
		String[] departmentSelected = addPositionForm.getDepartmentSelected();
		//获取所有用户提交的选中的共享职位的职位编号数组
		Integer[] positionSelected = addPositionForm.getPositionSelected();
		//初始化新职位对象
		Position newPosition = new Position();
		newPosition.setPosition_CN(addPositionForm.getNewPosition_CN());
		newPosition.setPosition_EN(addPositionForm.getNewPosition_EN());
		newPosition.setIsHeadStatus(Integer.parseInt(addPositionForm.getIsHeadStatus()));
		newPosition.setStructureDistribution(Integer.parseInt(addPositionForm.getStructureDistribution()));
		//添加职位必然是新职位添加或共享职位添加，只有当其中一个不为空的时候才能提交到dao层
		if(positionSelected.length != 0 || newPosition.getPosition_CN() != null)
			isOK = positionDao.addPosition(departmentSelected , positionSelected , newPosition);
		return isOK;
	}
	
	/**
	 * 获得所有已经存在的部门
	 */
	@Override
	public List<Department> getAllExistedDepartment() {
		List<Department> allExistedDepartmentList = new ArrayList<>();
		allExistedDepartmentList = positionDao.getAllExistedDepartment();
		return allExistedDepartmentList;
	}

}
