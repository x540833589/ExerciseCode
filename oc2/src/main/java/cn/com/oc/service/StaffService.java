package cn.com.oc.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.domain.Staff;
import cn.com.oc.dto.DepartmentPositionInfo;

@Service
public interface StaffService {
	
	public Integer getNewStaffNo();
	
	public String getPhoto(MultipartFile file,HttpServletRequest request); //保存图片并获得路径
	
	public String addStaff(Staff staff);
	
    public Staff getStaffByNo(Integer no);
    
    public String updateStaff(Staff staff);
    
    public Map<String, String> dataValidate(Staff staff);
    
    public List<Department> getAllDetail();
	
    public List<Position> getAllPositionByDeptID(String deptID);
    
    public String checkHeadStatus(Integer positionID);
    
    public List<Position> getAllExistedPosition();
    
    public boolean insertDepartmentPosition(DepartmentPositionInfo departmentPositionInfo);
    
    public boolean deleteExistedPhoto(String realPath) throws IOException;
    
    public String getPhotoByNo(Integer no);
    
}
