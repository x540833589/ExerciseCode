//package cn.com.oc.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import cn.com.oc.dto.QueryInfo;
//import cn.com.oc.service.StaffQueryService;
//import cn.com.oc.vo.StaffDetail;
//
//@Controller
//public class StaffQueryController {
//
//	@Autowired
//	private StaffQueryService queryService;
//	
//	@RequestMapping("/staffQuery")
//	public ModelAndView getAllStaffInfo(QueryInfo queryInfo){
//		ModelAndView mav = new ModelAndView("queryStaff");
//		List<StaffDetail> allStaffInfoList = new ArrayList<>();
//		allStaffInfoList = queryService.getAllStaffInfo(queryInfo);
//		mav.addObject("allStaffInfoList" , allStaffInfoList);
//		return mav;
//	}
//}
package cn.com.oc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.domain.Staff;
import cn.com.oc.dto.QueryInfo;
import cn.com.oc.service.StaffQueryService;
import cn.com.oc.service.StaffService;
import cn.com.oc.vo.StaffDetail;

@Controller
public class StaffQueryController {

	@Autowired
	private StaffQueryService queryService;
	
	@Autowired
	private StaffService staffService;
	
	@RequestMapping("/staffQuery")
	public ModelAndView getAllStaffInfo(QueryInfo queryInfo){
		ModelAndView mav = new ModelAndView("admin/queryStaff");
		List<StaffDetail> allStaffInfoList = new ArrayList<>();
		allStaffInfoList = queryService.getAllStaffInfo(queryInfo);
		mav.addObject("allStaffInfoList" , allStaffInfoList);
		return mav;
	}
	
	/**
	 * .查看一个员工的详细信息
	 * @param no 员工编号
	 * @return 视图
	 */
	@RequestMapping("/staffInfoDetails")
	public ModelAndView staffInfoDetails(int no) {
		ModelAndView mav = new ModelAndView();
		Position position = new Position();
		Department department = new Department();
		StaffDetail staffDetails = new StaffDetail();
		staffDetails = queryService.getStaffInfoDetails(no);
		position = queryService.getPositionByPositionID(staffDetails.getPositionID());
		department = queryService.getDepartmentByDeptID(staffDetails.getDeptID());
		mav.addObject("position" , position);
		mav.addObject("department" , department);
		mav.addObject("staffDetails" , staffDetails);
		mav.setViewName("admin/viewStaff");
		return mav;
	}
	
	/**
	 *.更新员工信息的方法
	 * @param file 员工照片
	 * @param request 异步请求
	 * @param staff 员工对象
	 * @return 更新是否成功
	 * @throws IOException 
	 */
	@RequestMapping(value = "/updateStaffInfo" , method=RequestMethod.POST)
	@ResponseBody
	public String updateStaffInfo(MultipartFile file , HttpServletRequest request , Staff staff) throws IOException {
		boolean isOK = false;
		String realPath = request.getServletContext().getRealPath("/");
		
		System.out.println("进入编辑方法");
		System.out.println("员工旧照片路径:" + staff.getPhoto());
		Integer currentID = staff.getNo();
		
		
		if(file!=null) {
			String previousPhotoPath = staffService.getPhotoByNo(currentID);
			isOK = staffService.deleteExistedPhoto(realPath.replace("\\", "/") + previousPhotoPath);
       		if(isOK == true) {
				String photo = staffService.getPhoto(file, request);
	       		staff.setPhoto(photo);  //将图片路径存入staff对象
       		}
       	}
       	String result = staffService.updateStaff(staff);
       	return result;
	}
	
	/**
	 * 查看最近新添加的员工(一人)的详细信息
	 * @return
	 */
	@RequestMapping("/viewCurrentNewStaff")
	public ModelAndView viewCurrentNewStaff() {
		ModelAndView mav = new ModelAndView();
		Position position = new Position();
		Department department = new Department();
		StaffDetail staffDetails = new StaffDetail();
		staffDetails = queryService.getCurrentNewStaffInfo();
		position = queryService.getPositionByPositionID(staffDetails.getPositionID());
		department = queryService.getDepartmentByDeptID(staffDetails.getDeptID());
		mav.addObject("staffDetails" , staffDetails);
		mav.addObject("position" , position);
		mav.addObject("department" , department);
		mav.setViewName("admin/viewStaff");
		return mav;
	}
	
	@RequestMapping("/editStaffInfo")
	public ModelAndView editStaffInfo(int no) {
		System.out.println("已在职员工编号xxxxxxxxxxxxxxxxxxxxxxxxxxx:" + no);
		ModelAndView mav = new ModelAndView();
		StaffDetail staffDetails = new StaffDetail();
		staffDetails = queryService.getStaffInfoDetails(no);
		System.out.println(staffDetails.getNo());
		mav.setViewName("admin/updateStaff");
		mav.addObject("staffDetails" , staffDetails);
		return mav;
	}
	
}
