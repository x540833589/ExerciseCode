package cn.com.shoppingmall.domain;

import java.sql.Timestamp;

/**
 * 评论类
 * */

public class Remark {

    private Integer remarkId;           //评论编号
    private Integer userId;             //用户编号(发表者)
    private String username;			//用户名
    private String nickname;			//昵称
    private Integer goodsId;            //商品编号
    private String goodsName;			//商品名称
    private String content;             //评论内容
    private Timestamp createTime;       //发表时间
    
	public Integer getRemarkId() {
		return remarkId;
	}
	public void setRemarkId(Integer remarkId) {
		this.remarkId = remarkId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
    
}
