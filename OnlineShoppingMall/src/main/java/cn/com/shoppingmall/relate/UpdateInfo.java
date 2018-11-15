package cn.com.shoppingmall.relate;

/**
 * 下单成功，更新用户信息
 * @author Administrator
 *
 */
public class UpdateInfo {

	private Integer userId = 0;				//用户编号
	private Integer goodsId = 0;			//商品编号
	private Integer amount = 0;				//购买数量
	private Double wealthIntegral = 0.0;	//财富积分
	
	public UpdateInfo() {}
	
	public UpdateInfo(Integer userId , Integer goodsId , Integer amount , Double wealthIntegral) {
		this.userId = userId;
		this.goodsId = goodsId;
		this.amount = amount;
		this.wealthIntegral = wealthIntegral;
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
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getWealthIntegral() {
		return wealthIntegral;
	}

	public void setWealthIntegral(Double wealthIntegral) {
		this.wealthIntegral = wealthIntegral;
	}
	
}
