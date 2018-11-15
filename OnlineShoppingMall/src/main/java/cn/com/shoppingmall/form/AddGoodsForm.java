package cn.com.shoppingmall.form;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Map;

public class AddGoodsForm implements ActionForm {

	private Integer goodsId;                        //商品编号
    private String name;                            //商品名称
    private String category;                        //商品类别
    private String description;						//商品描述(详细信息)
    private String goodsPictureURL = "";            //商品描述图片
    private Double price = 0.0;                     //单价
    private String manufacturer;                    //制造商
    private String dateOfProductionStringValue = "";//出场日期(页面提交的字符串，中间值)
    private Date dateOfProduction;                  //出厂日期（日期类型，真实值）
    private Integer numberOfRemaining = 0;          //剩余库存
    private Integer numberOfRemarked = 0;           //被评论数
    private Integer dayCountOfGoodQuality = 0;		//保质天数(仅食品可用)
    private Integer pointOfPraise = 0;              //好评数
    private Integer numberOfKnockdown = 0;          //成交数(该商品每提交一次订单，成交数+1)
    private Integer numberOfSales = 0;				//销售量(被购买的数量)
	
    private Map<String, String> errorMessages = new Hashtable<>();//错误信息提示列表
    
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
	@Override
	public Map<String, String> validate() {
		if(name.length() > 50 || name.length() < 1)
			errorMessages.put("name", "商品名称输入非法.");
		if(category.length() > 50 || category.length() < 1)
			errorMessages.put("category", "商品种类输入非法.");
		if(goodsPictureURL.length() > 200 || goodsPictureURL.length() < 1)
			errorMessages.put("goodsPictureURL", "图片路径输入非法.");
		if(price <= 0)
			errorMessages.put("price", "单价不能为负数.");
		if(manufacturer.length() > 100 || manufacturer.length() < 1)
			errorMessages.put("manufacturer", "制造商名称输入非法.");
		try {
			if(dateOfProductionStringValue.length() == 0)
				errorMessages.put("dateOfProduction", "出厂日期不能为空.");
			else if(simpleDateFormat.parse(dateOfProductionStringValue).after(new java.util.Date()))
				errorMessages.put("dateOfProduction", "出厂日期输入非法.");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(dayCountOfGoodQuality <= 0)
			errorMessages.put("dayCountOfGoodQuality", "保质期输入非法.");
		if(description.length() <= 0)
			errorMessages.put("description", "商品简介不能为空.");
		
		return errorMessages;
	}

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

	public String getDateOfProductionStringValue() {
		return dateOfProductionStringValue;
	}

	public void setDateOfProductionStringValue(String dateOfProductionStringValue) {
		this.dateOfProductionStringValue = dateOfProductionStringValue;
	}

	public Date getDateOfProduction() {
		if(dateOfProductionStringValue.length() > 0) {
			try {
				dateOfProduction = new Date(simpleDateFormat.parse(dateOfProductionStringValue).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
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

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
}
