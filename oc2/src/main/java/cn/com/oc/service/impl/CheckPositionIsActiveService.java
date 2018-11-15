package cn.com.oc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.oc.dao.impl.CheckPositionIsActiveDao;
import cn.com.oc.dto.DepartmentPositionInfo;
import cn.com.oc.vo.StaffDetail;

@Service
public class CheckPositionIsActiveService {

	@Autowired
	private CheckPositionIsActiveDao checkPositionIsActiveDao;
	
	/**
	 * .检索员工表中是否已经存在这样的(总监、经理、组长)职位
	 * @param departmentPositionInfo
	 * @return
	 */
	public StaffDetail checkPositionActiveStatus(DepartmentPositionInfo departmentPositionInfo) {
		StaffDetail staffDetail = new StaffDetail();
		staffDetail = checkPositionIsActiveDao.checkPositionActiveStatus(departmentPositionInfo);
		return staffDetail;
		/*if(staffDetail != null) {
			String position_CN = staffDetail.getPosition_CN();
			//如果是部门leader, 则返回该职位已存在员工在职
			if(position_CN.contains("组长") || position_CN.contains("经理") || position_CN.contains("主管") || position_CN.contains("总监")) {
				return true;
			//如果是底层(工程师、顾问、专员等),可以允许添加
			}else {
				return false;
			}
		}else
			return false;*/
	}
	
}
