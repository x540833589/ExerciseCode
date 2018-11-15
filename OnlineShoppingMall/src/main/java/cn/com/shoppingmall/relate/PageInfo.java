package cn.com.shoppingmall.relate;

/**
 * 页码和页面记录数的包装类
 * @author Administrator
 *
 */
public class PageInfo {
	
	private Integer recordStartIndex;	//当前页码记录开始索引
	private Integer pageRecordAmount;	//每页显示的记录数量
	
	public PageInfo() {}
	
	public PageInfo(Integer recordStartIndex , Integer pageRecordAmount) {
		this.recordStartIndex = recordStartIndex;
		this.pageRecordAmount = pageRecordAmount;
	}
	
	public Integer getRecordStartIndex() {
		return recordStartIndex;
	}

	public void setRecordStartIndex(Integer recordStartIndex) {
		this.recordStartIndex = recordStartIndex;
	}

	public Integer getPageRecordAmount() {
		return pageRecordAmount;
	}
	public void setPageRecordAmount(Integer pageRecordAmount) {
		this.pageRecordAmount = pageRecordAmount;
	}
	
}
