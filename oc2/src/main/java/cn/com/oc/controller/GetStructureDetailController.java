package cn.com.oc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.oc.service.ShowStructureDetailService;
import cn.com.oc.vo.StaffDetail;

@Controller
public class GetStructureDetailController {
	
	@Autowired
	private ShowStructureDetailService showStructureDetailService;
	
	@RequestMapping("/showDetail")
	public ModelAndView gotoStructureDetailPage(String deptID) {
		
		List<StaffDetail> leaderInfo = new ArrayList<>();
		List<StaffDetail> employeeInfo = new ArrayList<>();
		leaderInfo = showStructureDetailService.getStructureDistributionLeader(deptID);
		employeeInfo = showStructureDetailService.getStructureDistribution(deptID);
		ModelAndView mav = new ModelAndView("StructureDetail");
		mav.addObject("leaderInfo" , leaderInfo);
		mav.addObject("employeeInfo" , employeeInfo);
		return mav;
	}
	
	@RequestMapping("/showSpecial")
	public ModelAndView showSpecial() {
		List<StaffDetail> leaderInfo = new ArrayList<>();
		List<StaffDetail> employeeInfo = new ArrayList<>();
		leaderInfo = showStructureDetailService.getSpecialStructureLeader();
		employeeInfo = showStructureDetailService.getSpecialStructureMember();
		ModelAndView mav = new ModelAndView("StructureDetail");
		mav.addObject("leaderInfo" , leaderInfo);
		mav.addObject("employeeInfo" , employeeInfo);
		return mav;
	}

}
