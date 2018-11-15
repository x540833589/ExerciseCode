<%@page import="cn.com.shoppingmall.domain.Cart"%>
<%@page import="cn.com.shoppingmall.domain.Goods"%>
<%@page import="cn.com.shoppingmall.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>看法</title>
        <link rel="stylesheet" type="text/css" href="css/remark.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
    	<%
    		User user = (User)session.getAttribute("user");
    		Goods goods = (Goods)session.getAttribute("goods");
    		Cart cart = (Cart)session.getAttribute("cart");
    		Integer userId = user.getUserId();
    		Integer goodsId = goods.getGoodsId();
    		String goodsName = goods.getName();
    		String username = user.getUsername();
    		String nickname = user.getNickname();
    	%>
    	<div>
    		<br/>
	        <span class="prePageInfo"><a href="index" style="margin-left: 15px;">首页</a></span>&nbsp;>>>&nbsp;
	        <span class="prePageInfo"><a href="viewCategory?category=<%=goods.getCategory()%>"><%=goods.getCategory()%></a></span>&nbsp;>>>&nbsp;
	        <span class="prePageInfo"><a href="viewGoods?goodsId=<%=goods.getGoodsId()%>&userId=<%=user.getUserId()%>"><%=goods.getName()%></a></span>&nbsp;>>>&nbsp;看法
	    	<span class="rightt"><a href="userLogout">注销</a></span>
	    	<span class="rightt"><a href="gotoPersonalCenterPage?userId=${sessionScope.user.userId}">个人中心</a></span>
	    	<span class="rightt"><span id="sl"><%=cart.getGoodsList().size()%></span>件商品，<span id="zj"><%=cart.getTotalPrice()%></span>元</span><br/>
        </div>
        <HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="100%" color=#987cb9 SIZE=1><br/>
    	<form action="deliverRemark" method="GET">
				<span class="title titleFont"><i class="glyphicon glyphicon-comment"></i>&nbsp;&nbsp;“看法”</span><br/><br/>
				<textarea class="title" id="content" name="content" rows="15" cols="150"></textarea><br/><br/>
				<input type="hidden" name="username" value="<%=username%>"/>
				<input type="hidden" name="nickname" value="<%=nickname%>"/>
				<input type="hidden" name="userId" value="<%=userId%>"/>
				<input type="hidden" name="goodsId" value="<%=goodsId%>"/>
				<input type="hidden" name="goodsName" value="<%=goodsName%>"/>
			<button class="title btn btn-primary" type="submit"><i class="glyphicon glyphicon-share-alt"></i>&nbsp;&nbsp;发表</button>
		</form>
    </body>
</html>