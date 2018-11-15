package cn.com.oc.dto;
/**
 * .保存用户填写的添加职位的表单类
 * @author Administrator
 *
 */
public class AddPositionForm {

	private String[] departmentSelected;	//已选择的部门
	
	private Integer[] positionSelected;		//已选择的职位
	
	private String newPosition_CN;			//新职位的中文名

	private String newPosition_EN;			//新职位的英文名
	
	private String structureDistribution;	//职位部门等级号
	
	private String isHeadStatus;			//leader职位状态

	public String[] getDepartmentSelected() {
		return departmentSelected;
	}

	public void setDepartmentSelected(String[] departmentSelected) {
		this.departmentSelected = departmentSelected;
	}

	public Integer[] getPositionSelected() {
		return positionSelected;
	}

	public void setPositionSelected(Integer[] positionSelected) {
		this.positionSelected = positionSelected;
	}

	public String getNewPosition_CN() {
		return newPosition_CN;
	}

	public void setNewPosition_CN(String newPosition_CN) {
		this.newPosition_CN = newPosition_CN;
	}

	public String getNewPosition_EN() {
		return newPosition_EN;
	}

	public void setNewPosition_EN(String newPosition_EN) {
		this.newPosition_EN = newPosition_EN;
	}

	public String getStructureDistribution() {
		return structureDistribution;
	}

	public void setStructureDistribution(String structureDistribution) {
		this.structureDistribution = structureDistribution;
	}

	public String getIsHeadStatus() {
		return isHeadStatus;
	}

	public void setIsHeadStatus(String isHeadStatus) {
		this.isHeadStatus = isHeadStatus;
	}
	
}
