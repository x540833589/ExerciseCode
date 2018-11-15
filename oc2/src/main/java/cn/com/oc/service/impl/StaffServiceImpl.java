package cn.com.oc.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.oc.dao.StaffDao;
import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.domain.Staff;
import cn.com.oc.dto.DepartmentPositionInfo;
import cn.com.oc.service.StaffService;
@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private StaffDao staffDao;
	
	@Override
	public Integer getNewStaffNo() {
		return staffDao.getNewStffNo();
	}
	
	@Override
	public String getPhoto(MultipartFile file,HttpServletRequest request) {  //保存照片并重命名，并获得路径和文件名
	       	String path = request.getServletContext().getRealPath("/");
			//System.out.println("绝对路径:" + path);
			String file_path =path + "photo\\";
			
			String originalFileName = file.getOriginalFilename();
			String newFileName = UUID.randomUUID().toString().replace("-", "") + originalFileName.substring(originalFileName.lastIndexOf("."));
			File newFile = new File(file_path + newFileName);
		    try {
				file.transferTo(newFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		    String photo = file_path+newFileName;
		    //String pattern = "photo.+";
//		    Pattern.
		    photo = (photo.substring(photo.indexOf("photo"))).replace("\\", "/");   //截取相对路径并替换
		    System.out.println(photo);
		    return photo;
		}
	//插入新员工
	@Override
	public String addStaff(Staff staff) {
		System.out.println("-----------addStaff-----------");
		return staffDao.addStaff(staff);
	}

	@Override
	public Staff getStaffByNo(Integer no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateStaff(Staff staff) {
		String result = staffDao.updateStaff(staff);
		return result;
	}

	@Override
	public Map<String, String> dataValidate(Staff staff) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 获得添加员工页面三级联动
	 * @return
	 */
	@Override
	public List<Department> getAllDetail() {
		List<Department> details = new ArrayList<>();
		details = staffDao.getAllDetail();
		return details;
	}
	
	/**
	 * 根据已选择的部门获取相应的职位
	 */
	@Override
	public List<Position> getAllPositionByDeptID(String deptID) {
		List<Position> allPosition = new ArrayList<>();
		deptID = deptID.substring(0, 2);
		allPosition = staffDao.getAllPositionByDeptID(deptID);
		return allPosition;
	}

	@Override
	public String checkHeadStatus(Integer positionID) {
		String status = "";
		status = staffDao.checkHeadStatus(positionID);
		return status;
	}

	@Override
	public List<Position> getAllExistedPosition() {
		List<Position> allHeadPositionList = new ArrayList<>();
		allHeadPositionList = staffDao.getAllExistedPosition();
		return allHeadPositionList;
	}

	@Override
	public boolean insertDepartmentPosition(DepartmentPositionInfo departmentPositionInfo) {
		boolean isExisted = false;
		isExisted = staffDao.insertDepartmentPosition(departmentPositionInfo);
		return isExisted;
	}

	@Override
	public boolean deleteExistedPhoto(String realPath) throws IOException{
		File f=new File(realPath);
		boolean result = false;
		if(f.isDirectory()==false){   //是否是目录
			result =f.delete();
		}
        System.out.println("文件是否存在：" + result);
        return result;
	}
	
	//通过编号获取员工照片路径
	@Override
	public String getPhotoByNo(Integer no) {
		String photo = "";
		photo = staffDao.getPhotoByNo(no);
		return photo;
	}

}