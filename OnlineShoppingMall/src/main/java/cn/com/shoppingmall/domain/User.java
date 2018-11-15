package cn.com.shoppingmall.domain;

import java.sql.Date;

/**
 * 用户类
 * */
public class User {

    private Integer userId = 0;         //用户编号
    private String username = "";       //用户名
    private String password;            //密码
    private String nickname;            //昵称
    private String trueName;            //真实姓名
    private String sex;                 //性别
    private Date dateOfBirth;           //出生日期
    private String address;             //住址
    private String email;               //邮箱
    private String phoneNumber;         //联系电话
    private String IDNumber;            //身份证号码
    private Double wealthIntegral = 0.0;//财富积分
    private Integer pointOfPraise = 0;  //用户被点赞数
    private String lockedStatus = "N";  //用户锁定状态
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getIDNumber() {
		return IDNumber;
	}
	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}
	public Double getWealthIntegral() {
		return wealthIntegral;
	}
	public void setWealthIntegral(Double wealthIntegral) {
		this.wealthIntegral = wealthIntegral;
	}
	public Integer getPointOfPraise() {
		return pointOfPraise;
	}
	public void setPointOfPraise(Integer pointOfPraise) {
		this.pointOfPraise = pointOfPraise;
	}
	public String getLockedStatus() {
		return lockedStatus;
	}
	public void setLockedStatus(String lockedStatus) {
		this.lockedStatus = lockedStatus;
	}

}
