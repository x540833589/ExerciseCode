package cn.com.oc.dto;

public class QueryInfo {
	
	private String name_CN; //中文名
	
	private String name_EN; //英文名
	
	private String deptID;
	
	private String position_CN;
	
	public String getPosition_CN() {
		return position_CN;
	}
	public void setPosition_CN(String position_CN) {
		this.position_CN = position_CN;
	}
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	public String getName_CN() {
		return name_CN;
	}
	public void setName_CN(String name_CN) {
		this.name_CN = name_CN;
	}
	public String getName_EN() {
		return name_EN;
	}
	public void setName_EN(String name_EN) {
		this.name_EN = name_EN;
	}
	
}
