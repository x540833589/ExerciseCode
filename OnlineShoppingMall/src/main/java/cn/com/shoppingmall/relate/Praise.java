package cn.com.shoppingmall.relate;

/**
 * 点赞类
 * @author Administrator
 */
public class Praise {

	private Integer userId = 0;	//用户编号
	private Integer goodsId = 0;//商品编号
	
	public Praise() {}
	
	public Praise(Integer userId , Integer goodsId) {
		this.userId = userId;
		this.goodsId = goodsId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
}
