package cn.com.oc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.domain.Staff;
import cn.com.oc.dto.DepartmentPositionInfo;
import cn.com.oc.service.StaffService;

@Controller
public class NewStaffController {
	
	@Autowired
	private StaffService staffService;
	
	@RequestMapping(value = "/newStaff" , method = RequestMethod.POST)
	@ResponseBody
	public String newStaff(MultipartFile file , HttpServletRequest request , Staff staff) {
		boolean isOK = false;
		String message = "";
		if(file!=null) {
       		String photo = staffService.getPhoto(file, request);
       		staff.setPhoto(photo);  //将图片路径存入staff对象
       	}
       	DepartmentPositionInfo departmentPositionInfo = new DepartmentPositionInfo();
       	departmentPositionInfo.setDeptID(staff.getDeptID());
       	departmentPositionInfo.setPositionID(staff.getPositionID());
       	isOK = staffService.insertDepartmentPosition(departmentPositionInfo);
       	System.out.println("更新关联表:" + isOK);
       	//先插入关联记录,后再插入员工信息到员工表中
       	message = staffService.addStaff(staff);
       	return message;
	}
	
	/**
	 * 获取添加员工页面三级联动(部门)
	 * @return
	 */
	@RequestMapping(value = "/getAllDepartment" , method = RequestMethod.POST)
	@ResponseBody
	public List<Department> getAllDepartment() {
		List<Department> details = new ArrayList<>();
		details = staffService.getAllDetail();
		return details;
	}
	
	/**
	 * h获取添加员工页面三级联动(职位)
	 * @return
	 */
	@RequestMapping(value = "/getAllPosition" , method = RequestMethod.POST)
	@ResponseBody
	public List<Position> getAllPosition(HttpServletRequest request) {
		String deptID = request.getParameter("deptID");
		List<Position> details = new ArrayList<>();
		if(!deptID.equals("")) {
			System.out.println(deptID);
			details = staffService.getAllPositionByDeptID(deptID);
		}
		return details;
	}
	
	@RequestMapping(value = "/checkHeadStatus" , method = RequestMethod.POST)
	@ResponseBody
	public String checkHeadStatus(Integer positionID) {
		String status = "";
		status = staffService.checkHeadStatus(positionID);
		return status;
	}
	
	/**
	 * 获得所有已存在的职位的所有信息
	 * @return
	 */
	@RequestMapping(value = "/getAllExistedPosition" , method = RequestMethod.POST)
	@ResponseBody
	public List<Position> getAllExistedPosition() {
		List<Position> allExistedPositionList = new ArrayList<>();
		allExistedPositionList = staffService.getAllExistedPosition();
		return allExistedPositionList;
	}
	
}
