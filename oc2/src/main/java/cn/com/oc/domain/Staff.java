package cn.com.oc.domain;

public class Staff {
	
	private int no;
	
	private String name_CN;
	
	private String name_EN;
	
	private String deptID;
	
	private String childDeptID;
	
	private int positionID;
	
	private String isHead;
	
	private String email;
	
	private String isActive;
	
	private String photo;

	private Integer structureDistribution;
	
	public int getStructureDistribution() {
		return structureDistribution;
	}

	public void setStructureDistribution(int structureDistribution) {
		this.structureDistribution = structureDistribution;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	public String getDeptID() {
		return deptID;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public String getChildDeptID() {
		return childDeptID;
	}

	public void setChildDeptID(String childDeptID) {
		this.childDeptID = childDeptID;
	}

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	public String getIsHead() {
		return isHead;
	}

	public void setIsHead(String isHead) {
		this.isHead = isHead;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
