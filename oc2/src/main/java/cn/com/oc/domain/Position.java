package cn.com.oc.domain;

public class Position {
	
	private int no;
	
	private String position_CN;
	
	private String position_EN;
	
	private Integer isHeadStatus;
	
	private Integer structureDistribution = 0;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getPosition_CN() {
		return position_CN;
	}

	public void setPosition_CN(String position_CN) {
		this.position_CN = position_CN;
	}

	public String getPosition_EN() {
		return position_EN;
	}

	public void setPosition_EN(String position_EN) {
		this.position_EN = position_EN;
	}

	public Integer getIsHeadStatus() {
		return isHeadStatus;
	}

	public void setIsHeadStatus(Integer isHeadStatus) {
		this.isHeadStatus = isHeadStatus;
	}

	public Integer getStructureDistribution() {
		return structureDistribution;
	}

	public void setStructureDistribution(Integer structureDistribution) {
		this.structureDistribution = structureDistribution;
	}
	
}
