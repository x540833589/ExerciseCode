package cn.com.oc.dto;

/**
 * 部门职位关联表(position_department)对象
 * @author Administrator
 *
 */
public class DepartmentPositionInfo {

	private String deptID;
	
	private Integer positionID;

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public Integer getPositionID() {
		return positionID;
	}

	public void setPositionID(Integer positionID) {
		this.positionID = positionID;
	}
	
}
