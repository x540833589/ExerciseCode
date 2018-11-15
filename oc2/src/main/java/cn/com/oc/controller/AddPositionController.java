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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.com.oc.domain.Department;
import cn.com.oc.dto.AddPositionForm;
import cn.com.oc.service.PositionService;

/**
 * .添加职位的控制器(对已存在的/新添加的部门添加非leader职位)
 * @author Administrator
 *
 */
@Controller
public class AddPositionController {

	@Autowired
	private PositionService positionService;
	
	@RequestMapping(value = "/addPositionAndRelations" , method = RequestMethod.POST)
	@ResponseBody
	public String addPositionAndRelations(HttpServletRequest request) throws IOException {
		//结果标志位
		boolean isOK = false;
		Gson gson = new GsonBuilder().create();
		//获取JSON
		String formData = request.getParameter("formData");
		//反序列化JSON,将其转换为添加职位表单对象
		AddPositionForm addPositionForm = gson.fromJson(formData , AddPositionForm.class);
		//将表单传递给Service层
		isOK = positionService.addPosition(addPositionForm);
		//System.out.println(addPositionForm.getDepartmentSelected()[0]);
		//System.out.println(addPositionForm.getPositionSelected()[0]);
		//System.out.println(addPositionForm.getNewPosition_EN());
		if(isOK == true)
			return "操作成功";
		else
			return "操作失败";
	}
	
	/**
	 * 获取所有已经存在的部门
	 * @return
	 */
	@RequestMapping(value = "/getAllExistedDepartment" , method = RequestMethod.POST)
	@ResponseBody
	public List<Department> getAllExistedDepartment() {
		List<Department> allExistedDepartmentList = new ArrayList<>();
		allExistedDepartmentList = positionService.getAllExistedDepartment();
		return allExistedDepartmentList;
	}
	
}
