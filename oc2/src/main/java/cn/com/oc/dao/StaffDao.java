package cn.com.oc.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.com.oc.domain.Department;
import cn.com.oc.domain.Position;
import cn.com.oc.domain.Staff;
import cn.com.oc.dto.DepartmentPositionInfo;

@Repository
public interface StaffDao {
	
	public Integer getNewStffNo();
	
    public String addStaff(Staff staff);
    
    public Staff getStaffByNo(Integer no);
    
    public String updateStaff(Staff staff);
    
    public List<Department> getAllDetail();
    
    public List<Position> getAllPositionByDeptID(String deptID);
    
    public String checkHeadStatus(Integer positionID);
    
    public List<Position> getAllExistedPosition();
    
    public boolean insertDepartmentPosition(DepartmentPositionInfo departmentPositionInfo);
    
    public String getPhotoByNo(Integer no);
    
}
