package cn.com.shoppingmall.domain;

import cn.com.shoppingmall.relate.PurchaseItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 中间数据 --> 购物车
 * 不存入数据库(可变数据)
 * */

public class Cart {

    private Integer cartId;                 					//购物车编号
    private Integer userId;                 					//用户编号
    private List<PurchaseItem> goodsList 	
    	 					 = new ArrayList<PurchaseItem>();   //购物清单
    private Double totalPrice = 0.0;              					//总价
    
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
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
	
	/**
	 *.获取购物车中所有商品的总价
	 * @return
	 */
	public Double getTotalPrice() {
		Double summary = 0.0;
		if(goodsList.size() > 0) {
			for(PurchaseItem item : goodsList) {
				summary = summary + item.getGoods().getPrice() * item.getAmount();
			}
		}
		this.totalPrice = summary;
		return totalPrice;
	}
	
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
