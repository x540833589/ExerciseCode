<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="cn.com.shoppingmall.domain.User"%>
<%@ page import="cn.com.shoppingmall.domain.Goods"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>首页</title>
   		<link rel="stylesheet" href="css/IndexPage.css" type="text/css">
   		<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
   		<script type="text/javascript" src="js/jquery.ellipsis.js"></script>
   		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    </head>
    <body>
    <%
    	User user = new User();
    	if(session.getAttribute("user") != null)
    		user = (User)session.getAttribute("user");
    	@SuppressWarnings("unchecked")
    	List<Goods> goodsList = (List<Goods>)session.getAttribute("goodsList");
   		@SuppressWarnings("unchecked")
    	List<Integer> pageIndexList = (List<Integer>)session.getAttribute("pageIndex");
    	Integer currentPageNumber = (Integer)session.getAttribute("currentPageNumber");
    	Integer maxPageNumber = pageIndexList.size();
    	Integer previousPageNumber = currentPageNumber == 1 ? 1 : currentPageNumber - 1;
    	Integer nextPageNumber = currentPageNumber == maxPageNumber ? maxPageNumber : currentPageNumber + 1;
    %>
    <h2 class="title">Welcome Here.</h2>
    <%
        if(session.getAttribute("user") == null){
    %>
        <span class="leftt">您好，请<a href="gotoLoginPage">登录</a></span><br/>
    <%
        }else {
    %>
	    <span class="leftt">您好，<a href="gotoPersonalCenterPage">${sessionScope.user.nickname}</a></span>
	    <span class="rightt"><a href="userLogout">注销</a></span>
	    <span class="rightt"><a href="gotoPersonalCenterPage?userId=${sessionScope.user.userId}">个人中心</a></span>
	    <span class="rightt"><a href="gotoCartPage?previousPageInfo=index">我的购物车</a>(${sessionScope.cart.goodsList.size()}<c:if test="${empty sessionScope.cart}">0</c:if>件商品，${sessionScope.cart.totalPrice}<c:if test="${empty sessionScope.cart}">0.0</c:if>元)</span><br/>
    <%
        }
    %>
    	<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=1)" width="100%" color=black SIZE=3>
    	<%
    		if(goodsList.size() > 0) {
    	%>
	    	<c:forEach items="${sessionScope.goodsList}" var="goods">
		    	<div style="height: 200px; width: 100%;">
		    		<div class="lefttt">
		    			<img class="goodsImage" alt="${goods.name}" src="image/${goods.goodsPictureURL}">
		    		</div>
		    		<div class="righttt">
			    		<span class="goodsName"><a href="viewGoods?goodsId=${goods.goodsId}&userId=<%=user.getUserId()%>">${goods.name}</a></span><br/>
			    		<p class="goodsDescription">${goods.description}</p>
			    		<br/>
			    		<br/>
			    		<span class="goodsInfo">月售&nbsp;:&nbsp;${goods.numberOfSales}件</span>
			    		<span class="goodsInfo">成交&nbsp;:&nbsp;${goods.numberOfKnockdown}单</span>
			    		<span class="addToCart"><a href="viewGoods?goodsId=${goods.goodsId}&userId=<%=user.getUserId()%>"><button class="btn btn-primary"><i class="glyphicon glyphicon-shopping-cart"></i>&nbsp;&nbsp;加入购物车</button></a></span>
		    			<span class="unitPrice">单价&nbsp;:&nbsp;${goods.price}</span><br/><br/>
		    		</div>
		    		<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="100%" color=#987cb9 SIZE=1>
		    	</div>
	    	</c:forEach>
	    	<br/><br/>
	    	<span class="pageIndexes">
		    	<span class="pageIndex"><a href="index?currentPageNumber=<%=previousPageNumber%>">上一页</a></span>
		    	<c:forEach items="${sessionScope.pageIndex}" var="pageNumber">
		    		<span class="pageIndex"><a href="index?currentPageNumber=${pageNumber}">${pageNumber}</a></span>
		    	</c:forEach>
		    	<span class="pageIndex"><a href="index?currentPageNumber=<%=nextPageNumber%>">下一页</a></span>
    		</span>
    		<br/><br/><br/>
    	<%
    		}else {
    	%>
    		<br/><br/><h2>还没有商品发布哦。</h2>
    	<%
    		}
    	%>
    </body>
</html>