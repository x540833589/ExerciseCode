package cn.com.oc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.dto.DepartmentPositionInfo;
import cn.com.oc.service.DepartmentService;
import cn.com.oc.vo.NewDepartmentForm;


@Controller
public class AddDepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(value = "/addDepartment" , method = RequestMethod.POST)
	@ResponseBody
	public String newStaff(NewDepartmentForm newDepartmentForm) {
		Integer rowAffected = departmentService.addDepartment(newDepartmentForm);
		if(rowAffected != 0)
			return "添加成功";
		else
			return "添加失败";
	}
	
	/**
	 * 加载页面时获取所有可供选择的上级部门
	 * @return 上级部门集合
	 */
	@RequestMapping(value = "/getAllSuperiorDepartment" , method = RequestMethod.POST)
	@ResponseBody
	public List<Department> getAllSuperiorDepartment() {
		List<Department> superiorDepartmentList = new ArrayList<>();
		superiorDepartmentList = departmentService.getAllSuperiorDepartment();
		return superiorDepartmentList;
	}
	
	/**
	 * 已选择上级部门的情况下，查询其直属下级部门
	 * @param request 上级部门编号
	 * @return 子部门集合
	 */
	@RequestMapping(value = "/getAllChildrenDepartmentByDeptID" , method = RequestMethod.POST)
	@ResponseBody
	public List<Department> getAllChildrenDepartmentByDeptID(HttpServletRequest request){
		List<Department> childrenDepartmentList = new ArrayList<>();
		String deptID = request.getParameter("deptID");
		childrenDepartmentList = departmentService.getAllChildrenDepartmentByDeptID(deptID);
		return childrenDepartmentList;
	}
	
	@RequestMapping(value = "/addSharedPositionByAddDepartment" , method = RequestMethod.POST)
	@ResponseBody
	public String addSharedPositionByAddDepartment(HttpServletRequest request) {
		Integer rowAffected = 0;
		String deptID = request.getParameter("deptID");
		Integer positionID = Integer.decode(request.getParameter("positionID"));
		DepartmentPositionInfo departmentPositionInfo = new DepartmentPositionInfo();
		departmentPositionInfo.setDeptID(deptID);
		departmentPositionInfo.setPositionID(positionID);
		rowAffected = departmentService.addSharedPosition(departmentPositionInfo);
		if(rowAffected > 0)
			return "添加成功";
		else
			return "添加失败";
	}
	
	/**
	 * 添加新部门的同时添加一个新的leader职位
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addNewPositionByAddDepartment" , method = RequestMethod.POST)
	@ResponseBody
	public String addNewPositionByAddDepartment(HttpServletRequest request) {
		Integer rowAffected1 = 0;
		Integer rowAffected2 = 0;
		String deptID = request.getParameter("deptID");
		Integer isHeadStatus = Integer.decode(request.getParameter("isHeadStatus"));
		Integer structureDistribution = Integer.decode(request.getParameter("structureDistribution"));
		String position_CN = request.getParameter("position_CN");
		String position_EN = request.getParameter("position_EN");
		Position position = new Position();
		position.setPosition_CN(position_CN);
		position.setPosition_EN(position_EN);
		position.setIsHeadStatus(isHeadStatus);
		position.setStructureDistribution(structureDistribution);
		//更新新职位到数据库
		rowAffected1 = departmentService.addNewPosition(position);
		Position newestPostion = departmentService.getNewestPosition();
		//新建关联记录
		DepartmentPositionInfo departmentPositionInfo = new DepartmentPositionInfo();
		departmentPositionInfo.setDeptID(deptID);
		departmentPositionInfo.setPositionID(newestPostion.getNo());
		//向关联表中插入新数据(新的职位与新的部门相关联)
		rowAffected2 = departmentService.addSharedPosition(departmentPositionInfo);
		if(rowAffected1 > 0 && rowAffected2 > 0)
			return "添加成功";
		else
			return "添加失败";
	}
	
}