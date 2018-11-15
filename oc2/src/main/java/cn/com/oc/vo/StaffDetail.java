package cn.com.oc.vo;


public class StaffDetail {
	//员工表字段 
	private Integer no; //员工编号
	
	private String name_CN; //员工中文名
	
	private String name_EN; //员工英文名
	
	private int positionID; //员工职位ID
	
	private int isHead; //是否是相应层级领导（1 or 0）
	
	private String email; // 员工邮箱
	
	private int isActive; //员工是否在职
	
	private String photo; //员工照片
	
	private int structureDistribution = 0; //员工部门职位排序字段
	
	//部门字段
	private String deptID; // 员工相应部门编号
	
	private String childDeptID; //虚拟子部门编号(design for Hebert)
	
	private String department_CN; // 部门中文名
	
	private String department_EN; // 部门英文名
	
	private int childNum; //子部门数量
	
	private String bu_Head; //暂时无用
	
	private String upOneLevel; //上级部门编号
	
	private int deptLevel; //部门等级 
	
	//职位字段
	private String position_CN; //职位中文名
	
	private String position_EN; // 职位英文名

	
	//------------------------------------------------------
	
	public int getStructureDistribution() {
		return structureDistribution;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public void setStructureDistribution(int structureDistribution) {
		this.structureDistribution = structureDistribution;
	}
	
	public String getChildDeptID() {
		return childDeptID;
	}

	public void setChildDeptID(String childDeptID) {
		this.childDeptID = childDeptID;
	}

	public String getName_CN() {
		return name_CN;
	}

	public String getName_EN() {
		return name_EN;
	}

	public int getPositionID() {
		return positionID;
	}

	public int getIsHead() {
		return isHead;
	}

	public String getEmail() {
		return email;
	}

	public int getIsActive() {
		return isActive;
	}

	public String getPhoto() {
		return photo;
	}

	public String getDeptID() {
		return deptID;
	}

	public String getDepartment_CN() {
		return department_CN;
	}

	public String getDepartment_EN() {
		return department_EN;
	}

	public int getChildNum() {
		return childNum;
	}

	public String getBu_Head() {
		return bu_Head;
	}

	public String getUpOneLevel() {
		return upOneLevel;
	}

	public int getDeptLevel() {
		return deptLevel;
	}

	public String getPosition_CN() {
		return position_CN;
	}

	public String getPosition_EN() {
		return position_EN;
	}

	public void setName_CN(String name_CN) {
		this.name_CN = name_CN;
	}

	public void setName_EN(String name_EN) {
		this.name_EN = name_EN;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	public void setIsHead(int isHead) {
		this.isHead = isHead;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}

	public void setDepartment_CN(String department_CN) {
		this.department_CN = department_CN;
	}

	public void setDepartment_EN(String department_EN) {
		this.department_EN = department_EN;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public void setBu_Head(String bu_Head) {
		this.bu_Head = bu_Head;
	}

	public void setUpOneLevel(String upOneLevel) {
		this.upOneLevel = upOneLevel;
	}

	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}

	public void setPosition_CN(String position_CN) {
		this.position_CN = position_CN;
	}

	public void setPosition_EN(String position_EN) {
		this.position_EN = position_EN;
	}
	
	
}
