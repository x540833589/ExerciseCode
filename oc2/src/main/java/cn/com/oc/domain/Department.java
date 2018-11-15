package cn.com.oc.domain;

public class Department {
	private String deptID;
	
	private String upOneLevel;
	
	private String department_CN;
	
	private String department_EN;
	
	private int childNum = 0;
	
	private String bu_Head;
	
	private String comment = "";
	
	private int deptLevel;

	public int getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}

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

	public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public String getBu_Head() {
		return bu_Head;
	}

	public void setBu_Head(String bu_Head) {
		this.bu_Head = bu_Head;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
