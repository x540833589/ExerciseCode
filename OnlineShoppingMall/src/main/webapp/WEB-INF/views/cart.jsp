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
        <title>${sessionScope.user.nickname}的购物车</title>
        <link rel="stylesheet" type="text/css" href="css/cart.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    </head>
    <body>
    <%
     	Goods goods = new Goods();
    	User user = (User)session.getAttribute("user");
    	if(session.getAttribute("goods") != null)
    		goods = (Goods)session.getAttribute("goods");
    	Cart cart = (Cart)session.getAttribute("cart");
    	List<PurchaseItem> itemList = cart.getGoodsList();
    %>
    	<div>
    		<br/>
	        <c:if test="${sessionScope.previousPageInfo eq 'index'}">
	        	<span class="prePageInfo" style="margin-left: 15px;"><a href="index">首页</a></span>&nbsp;>>>&nbsp;
	        	购物车
	        </c:if>
	        <c:if test="${sessionScope.previousPageInfo eq 'viewGoods'}">
	        	<span class="prePageInfo"><a href="index" style="margin-left: 15px;">首页</a></span>&nbsp;>>>&nbsp;
	        	<span class="prePageInfo"><a href="viewCategory?category=<%=goods.getCategory()%>"><%=goods.getCategory()%></a></span>&nbsp;>>>&nbsp;
	        	<span class="prePageInfo"><a href="viewGoods?goodsId=<%=goods.getGoodsId()%>&userId=<%=user.getUserId()%>"><%=goods.getName()%></a></span>&nbsp;>>>&nbsp;
	        	购物车
	        </c:if>
	        <c:if test="${sessionScope.previousPageInfo eq 'personalCenter'}">
	        	<span class="prePageInfo"><a href="index" style="margin-left: 15px;">首页</a></span>&nbsp;>>>&nbsp;
	        	<span class="prePageInfo"><a href="gotoPersonalCenterPage?userId=<%=user.getUserId()%>">个人中心</a></span>&nbsp;>>>&nbsp;
	        	购物车
	        </c:if>
	    	<span class="rightt"><a href="userLogout">注销</a></span>
	    	<span class="rightt"><a href="gotoPersonalCenterPage?userId=${sessionScope.user.userId}">个人中心</a></span>
	    	<span class="rightt"><span id="sl"><%=cart.getGoodsList().size()%></span>件商品，<span id="zj"><%=cart.getTotalPrice()%></span>元</span><br/>
        </div>
        <div>
        	<c:forEach items="<%=itemList%>" var="item">
        		<div>
        			<HR style="margin-bottom: 20px; margin-top: 20px; FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="100%" color=#987cb9 SIZE=1>
        			<div class="lefttt">
        				<img class="image" alt="${item.goods.name}" src="image/${item.goods.goodsPictureURL}">
        			</div>
        			<div class="righttt">
        				<span id="goodsId${item.goods.goodsId}" style="color: transparent;">${item.goods.goodsId}</span>
        				<br/>
	        			<span class="name">${item.goods.name}</span><br/><br/>
	        			<span class="deleteGoods"><a href="deleteGoods?goodsId=${item.goods.goodsId}"><button class="btn"><i class="glyphicon glyphicon-trash"></i>&nbsp;&nbsp;删除该商品</button></a></span>
						<span class="itemTotalPrice">共计&nbsp;:&nbsp;<input id="totalPrice${item.goods.goodsId}" name="totalPrice" style="border: 0px;outline:none;cursor: pointer;" type="text" readonly="readonly" value="${item.currentPrice}元"></span>	        			
						<span class="amount">数量&nbsp;:&nbsp;
							<button class="numberButton btn" id="plus" onclick="handlePlus(${item.goods.goodsId})"><i class="glyphicon glyphicon-plus"></i></button>
							<input type="text" class="goodsAmount" name="amount" id="${item.goods.goodsId}" value="${item.amount}" onkeyup="total(1 , this.value)" oninput="handleInput(${item.goods.goodsId} , this.value)">
							<button class="numberButton btn" id="minus" onclick="handleMinus(${item.goods.goodsId})"><i class="glyphicon glyphicon-minus"></i></button>
						</span>	        			
						<span class="unitPrice">单价&nbsp;:&nbsp;<span id="price${item.goods.goodsId}">${item.goods.price}</span>元</span>
        			</div>
        		</div>
        	</c:forEach>
        </div>
        <br/><br/>
        <c:if test="${sessionScope.cart.goodsList.size() > 0}">
	        <div class="orderInfo">
	        	<br/>
		        <span class="totalPrice"><input id="summary" style="direction: rtl; border: 0px;outline:none;cursor: pointer;" type="text" readonly="readonly" value="总价 : <%=cart.getTotalPrice()%>元"></span><br/><br/>
		        <span class="submitt"><a href="gotoOrderPage"><button class="btn btn-info sutt"><i class="glyphicon glyphicon-ok"></i>&nbsp;&nbsp;提交订单</button></a></span>
	    	</div>
        </c:if>
        <c:if test="${sessionScope.cart.goodsList.size() eq 0}">
        	<br/><br/><h2 style="color: red; font-weight: bold;" align="center">您还没有选购任何商品哦。</h2>
        </c:if>
        <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="js/cart.js"></script>
    </body>
</html>