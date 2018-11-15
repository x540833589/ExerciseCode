package cn.com.shoppingmall.domain;

import cn.com.shoppingmall.relate.PurchaseItem;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单类(确定的购物清单)
 * */

public class Order {

    private Integer orderId;                //订单编号
    private Integer userId;                 //用户编号
    private List<PurchaseItem> goodsList;   //购物清单
    private Double totalPrice;              //总价
    private Timestamp createTime;           //订单创建时间
    private String trueName;                //收货人真实姓名
    private String phoneNumber;             //联系电话
    private String destination;             //收货地址
    private String deliverStatus = "N";		//发货状态(由管理员修改)
    private String note;                    //备注
    
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
	public List<PurchaseItem> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<PurchaseItem> goodsList) {
		this.goodsList = goodsList;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDeliverStatus() {
		return deliverStatus;
	}
	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
