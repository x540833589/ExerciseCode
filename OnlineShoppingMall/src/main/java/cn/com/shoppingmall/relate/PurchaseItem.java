package cn.com.shoppingmall.relate;

import cn.com.shoppingmall.domain.Goods;

/**
 * 购物车中的一项商品
 * */
public class PurchaseItem {
	
	private Goods goods = new Goods();      					//购买的商品
    private Integer amount = 0; 								//购买数量
    private Double currentPrice = goods.getPrice() * amount;    //当前商品总价
    
    public PurchaseItem() {}
    
    public PurchaseItem(Goods goods , Integer amount , Double currentPrice) {
    	this.goods = goods;
    	this.amount = amount;
    	this.currentPrice = currentPrice;
    }
    
    public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
}
