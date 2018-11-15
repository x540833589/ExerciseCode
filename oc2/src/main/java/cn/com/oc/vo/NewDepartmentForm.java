package cn.com.oc.vo;

/**
 * 填写的新的部门详细信息的表单
 * @author Administrator
 */
public class NewDepartmentForm {

	private String deptID;  		//部门编号

	private String upOneLevel;		//直属上级部门编号
	
	private String department_CN;	//部门中文名
	
	private String department_EN;	//部门英文名
	
	private int deptLevel;			//部门等级
	
	private String bu_Head;			//是否是顶级部门(除了'D1000 总经办'以外的一级部门)
	
	//--------------------------------

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getUpOneLevel() {
		return upOneLevel;
	}

	public void setUpOneLevel(String upOneLevel) {
		this.upOneLevel = upOneLevel;
	}

	public String getDepartment_CN() {
		return department_CN;
	}

	public void setDepartment_CN(String department_CN) {
		this.department_CN = department_CN;
	}

	public String getDepartment_EN() {
		return department_EN;
	}

	public void setDepartment_EN(String department_EN) {
		this.department_EN = department_EN;
	}

	public int getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getBu_Head() {
		return bu_Head;
	}

	public void setBu_Head(String bu_Head) {
		this.bu_Head = bu_Head;
	}
	
}
