<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cn.com.shoppingmall.domain.Cart"%>
<%@ page import="cn.com.shoppingmall.domain.Goods"%>
<%@ page import="cn.com.shoppingmall.relate.PurchaseItem"%>
<%@ page import="cn.com.shoppingmall.domain.User"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>确认订单</title>
        <link rel="stylesheet" type="text/css" href="css/order.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"> 
    </head>
    <body>
    <%
    	User user = (User)session.getAttribute("user");
    	Goods goods = (Goods)session.getAttribute("goods");
    	Cart cart = (Cart)session.getAttribute("cart");
    	List<PurchaseItem> itemList = cart.getGoodsList();
    %>
    	<div>
    		<br/>
	        <span class="prePageInfo"><a href="index" style="margin-left: 15px;">首页</a></span>&nbsp;>>>&nbsp;
	        <span class="prePageInfo"><a href="viewCategory?category=<%=goods.getCategory()%>"><%=goods.getCategory()%></a></span>&nbsp;>>>&nbsp;
	        <span class="prePageInfo"><a href="viewGoods?goodsId=<%=goods.getGoodsId()%>&userId=<%=user.getUserId()%>"><%=goods.getName()%></a></span>&nbsp;>>>&nbsp;
	        <span class="prePageInfo"><a href="gotoCartPage">我的购物车</a></span>&nbsp;>>>&nbsp;确认订单
	    	<span class="rightt"><a href="userLogout">注销</a></span>
	    	<span class="rightt"><a href="gotoPersonalCenterPage?userId=${sessionScope.user.userId}">个人中心</a></span>
	    	<span class="rightt"><span id="sl"><%=cart.getGoodsList().size()%></span>件商品，<span id="zj"><%=cart.getTotalPrice()%></span>元</span>
        </div>
        <div>
        	<c:forEach items="<%=itemList%>" var="item">
        		<div>
        			<HR style="margin-bottom: 20px; margin-top: 20px; FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="100%" color=#987cb9 SIZE=1>
        			<div class="lefttt">
        				<p class="Id">商品编号&nbsp;:&nbsp;${item.goods.goodsId}</p>
        				<img class="image" alt="${item.goods.name}" src="image/${item.goods.goodsPictureURL}">
        			</div>
        			<div class="righttt">
        				<span id="goodsId${item.goods.goodsId}" style="color: transparent;">${item.goods.goodsId}</span>
        				<br/>
	        			<span class="name">${item.goods.name}</span><br/><br/>
	        			<span class="itemTotalPrice">共计&nbsp;:&nbsp;${item.currentPrice}元 </span>
	        			<span class="amount">数量&nbsp;:&nbsp;${item.amount}</span>
	        			<span class="unitPrice">单价&nbsp;:&nbsp;${item.goods.price}元</span>
        			</div>
        		</div>
        	</c:forEach>
        	<span class="totalPrice">总计&nbsp;:&nbsp;<%=cart.getTotalPrice()%>元</span>
        </div>
        <br/><br/><br/>
        <span class="ensureOrderInfo">确认订单信息&nbsp;&nbsp;<i class="glyphicon glyphicon-file"></i></span><br/><br/><br/>
        <div class="userInfo">
        	<form action="createOrder" method="GET">
	        	<label>真实姓名&nbsp;:</label><input type="text" name="trueName"/>&nbsp;*&nbsp;必填<br/><br/>
	        	<label>电话号码&nbsp;:</label><input type="text" name="phoneNumber"/>&nbsp;*&nbsp;必填<br/><br/>
	        	<label>配送至&nbsp;:</label><input type="text" name="destination" style="width: 1097px;"/>&nbsp;*&nbsp;必填<br/><br/>
	        	<label>添加备注&nbsp;:</label><textarea id="note" name="note" cols="150" rows="10"></textarea><br/><br/>
        		<input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
        		<input type="hidden" name="totalPrice" value="<%=cart.getTotalPrice()%>"/>
        		<button type="submit" class="OK btn btn-primary"><i class="glyphicon glyphicon-ok"></i>&nbsp;&nbsp;确认下单</button>
        	</form>
        </div>
        <br/><br/><br/>
        <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="js/cart.js"></script>
    </body>
</html>