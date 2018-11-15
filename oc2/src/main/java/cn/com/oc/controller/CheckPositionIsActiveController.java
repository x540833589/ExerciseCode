package cn.com.oc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.oc.dto.DepartmentPositionInfo;
import cn.com.oc.service.impl.CheckPositionIsActiveService;
import cn.com.oc.vo.StaffDetail;

@Controller
public class CheckPositionIsActiveController {

	@Autowired
	private CheckPositionIsActiveService checkPositionIsActiveService;
	
	@RequestMapping(value = "/checkPositionActiveStatus" , method = RequestMethod.POST)
	@ResponseBody
	public StaffDetail checkPositionActiveStatus(HttpServletRequest request) {
		DepartmentPositionInfo departmentPositionInfo = new DepartmentPositionInfo();
		Integer positionID = Integer.decode(request.getParameter("positionID"));
		String deptID = request.getParameter("deptID");
		System.out.println("查询重复的员工编号:" + deptID);
		System.out.println("查询重复的职位ID" + positionID);
		departmentPositionInfo.setDeptID(deptID);
		departmentPositionInfo.setPositionID(positionID);
		StaffDetail staffDetail = new StaffDetail();
		staffDetail = checkPositionIsActiveService.checkPositionActiveStatus(departmentPositionInfo);
		//System.out.println("查询重复的职位编号:" + staffDetail.getPositionID());
		return staffDetail;
	}
	
}
