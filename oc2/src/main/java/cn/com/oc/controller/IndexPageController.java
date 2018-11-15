package cn.com.oc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.oc.dao.GetStructureDao;
import cn.com.oc.dao.ShowStructureDetailDao;
import cn.com.oc.domain.Administrator;
import cn.com.oc.domain.Department;
import cn.com.oc.service.StaffService;
import cn.com.oc.vo.StaffDetail;

@Controller
public class IndexPageController {

	@Autowired
	private GetStructureDao getStructureDao;
	
	@Autowired
	private ShowStructureDetailDao showStructureDetailDao;
	
	@Autowired
	private StaffService staffService;
	
	@RequestMapping("/")
	public ModelAndView gotoIndexPage() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
	@RequestMapping("/gotoNewStaff")
	public ModelAndView gotoNewStaff() {
		int newStaffNo = staffService.getNewStaffNo();
		ModelAndView mav = new ModelAndView("admin/newStaff");
		mav.addObject("newStaffNo" , newStaffNo);
		return mav;
	}
	
	//鐧诲綍璺宠浆controller
	@RequestMapping("/gotoLogin")
	public ModelAndView gotoLogin(Administrator admin) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/login");
		return mav;
	}
	
	@RequestMapping(value = "/getFirstLevelDepartment" , method = RequestMethod.POST)
	@ResponseBody
	public List<StaffDetail> getFirstLevelDepartment(){
		List<StaffDetail> firstLevelDepartmentList = new ArrayList<>(); 
		firstLevelDepartmentList = showStructureDetailDao.getFirstLevelDepartment();
		return firstLevelDepartmentList;
	}
	
	@RequestMapping(value = "/viewChild" , method = RequestMethod.POST)
	@ResponseBody
	public List<Department> getChildrenDepartment(String deptID) {
		List<Department> childrenDepartmentList = new ArrayList<>(); 
		childrenDepartmentList = getStructureDao.getChildrenDepartment(deptID);
		return childrenDepartmentList;
	}
	
	@RequestMapping("/gotoAdminMain")
	public ModelAndView gotoAdminMain() {
		ModelAndView mav = new ModelAndView("admin/adminMain");
		return mav;
	}
	
	@RequestMapping("/gotoQueryStaff")
	public ModelAndView gotoQueryStaff() {
		ModelAndView mav = new ModelAndView("admin/queryStaff");
		return mav;
	}
	
	@RequestMapping("/gotoAddDepartment")
	public ModelAndView gotoAddDepartment() {
		ModelAndView mav = new ModelAndView("admin/addDepartment");
		return mav;
	}
	
	@RequestMapping("/gotoAddPosition")
	public ModelAndView gotoAddPosition() {
		ModelAndView mav = new ModelAndView("admin/addPosition");
		return mav;
	}
	
}
