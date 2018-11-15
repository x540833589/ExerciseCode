package cn.com.shoppingmall.domain;

import java.sql.Date;

/**
 * 商品类
 * */
public class Goods {

    private Integer goodsId;                        //商品编号
    private String name;                            //商品名称
    private String category;                        //商品类别
    private String description;						//商品描述(详细信息)
    private String goodsPictureURL = "";            //商品描述图片
    private Double price = 0.0;                     //单价
    private String manufacturer;                    //制造商
    private Date dateOfProduction;                  //出厂日期（日期类型，真实值）
    private Integer numberOfRemaining = 0;          //剩余库存
    private Integer numberOfRemarked = 0;           //被评论数
    private Integer dayCountOfGoodQuality = 0;		//保质天数(仅食品可用)
    private Integer pointOfPraise = 0;              //好评数
    private Integer numberOfKnockdown = 0;          //成交数(该商品每提交一次订单，成交数+1)
    private Integer numberOfSales = 0;				//销售量(被购买的数量)
    
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGoodsPictureURL() {
		return goodsPictureURL;
	}
	public void setGoodsPictureURL(String goodsPictureURL) {
		this.goodsPictureURL = goodsPictureURL;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public Date getDateOfProduction() {
		return dateOfProduction;
	}
	public void setDateOfProduction(Date dateOfProduction) {
		this.dateOfProduction = dateOfProduction;
	}
	public Integer getNumberOfRemaining() {
		return numberOfRemaining;
	}
	public void setNumberOfRemaining(Integer numberOfRemaining) {
		this.numberOfRemaining = numberOfRemaining;
	}
	public Integer getNumberOfRemarked() {
		return numberOfRemarked;
	}
	public void setNumberOfRemarked(Integer numberOfRemarked) {
		this.numberOfRemarked = numberOfRemarked;
	}
	public Integer getDayCountOfGoodQuality() {
		return dayCountOfGoodQuality;
	}
	public void setDayCountOfGoodQuality(Integer dayCountOfGoodQuality) {
		this.dayCountOfGoodQuality = dayCountOfGoodQuality;
	}
	public Integer getPointOfPraise() {
		return pointOfPraise;
	}
	public void setPointOfPraise(Integer pointOfPraise) {
		this.pointOfPraise = pointOfPraise;
	}
	public Integer getNumberOfKnockdown() {
		return numberOfKnockdown;
	}
	public void setNumberOfKnockdown(Integer numberOfKnockdown) {
		this.numberOfKnockdown = numberOfKnockdown;
	}
	public Integer getNumberOfSales() {
		return numberOfSales;
	}
	public void setNumberOfSales(Integer numberOfSales) {
		this.numberOfSales = numberOfSales;
	}
    
}
