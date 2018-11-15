package cn.com.shoppingmall.relate;

/**
 * .订单商品项目类
 * @author Administrator
 */
public class OrderItem {

	private Integer orderId;				//订单编号
	private Integer userId;					//用户编号
	private Integer goodsId;				//商品编号
	private String goodsName;				//商品名称
	private Double unitPrice;				//商品单价
	private Integer amount;					//购买数量
	private Double totalPrice;				//单项总价
	private String deliverStatus = "N";		//发货状态
	
	public OrderItem() {}
	
	public OrderItem (Integer orderId , Integer userId , Integer goodsId , String goodsName , Double unitPrice , Integer amount , Double totalPrice) {
		this.orderId = orderId;
		this.userId = userId;
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.totalPrice = totalPrice;
	}
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getDeliverStatus() {
		return deliverStatus;
	}
	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}
	
}
