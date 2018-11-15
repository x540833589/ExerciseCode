<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="cn.com.shoppingmall.domain.User"%>
<%@ page import="cn.com.shoppingmall.domain.Goods"%>
<%@ page import="cn.com.shoppingmall.domain.Remark"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
	<%
	    User user = new User();
		if(session.getAttribute("user") != null)
			user = (User)session.getAttribute("user");
		Goods goods = (Goods)session.getAttribute("goods");
		Integer currentPageNumber = (Integer)session.getAttribute("currentRemarkPageNumber");
		Integer currentPageRemarkAmount = (Integer)session.getAttribute("currentPageRemarkAmount");
		@SuppressWarnings("unchecked")
		List<Integer> pageIndex = (List<Integer>)session.getAttribute("remarkPageIndex");
		Integer maxPageNumber = pageIndex.size();
		Integer previousPageNumber = currentPageNumber == 1 ? 1 : currentPageNumber - 1;
		Integer nextPageNumber = currentPageNumber == maxPageNumber ? maxPageNumber : currentPageNumber + 1;
		Integer startIndex = (currentPageNumber - 1) * currentPageRemarkAmount;
		Integer endIndex = currentPageNumber * currentPageRemarkAmount - 1;
	%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=goods.getName()%></title>
        <link rel="stylesheet" type="text/css" href="css/viewGoods.css">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
        <script type="text/javascript" src="js/viewGoods.js"></script>
		<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
    </head>
    <body>
    	<br/>
    	<div>
    		<span class="Index1"><a href="index">首页</a></span>&nbsp;>>>&nbsp;
    		<span class="Index2"><a href="viewCategory?category=<%=goods.getCategory()%>"><%=goods.getCategory()%></a></span>&nbsp;>>>&nbsp;
    		<span class="Index2"><%=goods.getName()%></span>
    	<%
    		if(session.getAttribute("user") != null) {
    	%>
    		<span class="rightt"><a href="userLogout">注销</a></span>
	    	<span class="rightt"><a href="gotoPersonalCenterPage?userId=${sessionScope.user.userId}">个人中心</a></span>
	    	<span class="rightt"><a href="gotoCartPage?previousPageInfo=viewGoods">我的购物车(<c:if test="${empty sessionScope.cart.goodsList.size()}">0</c:if>${sessionScope.cart.goodsList.size()}件商品,<c:if test="${empty sessionScope.cart.totalPrice}">0.0</c:if>${sessionScope.cart.totalPrice}元)</a></span><br/>
    	<%
	    	}
    	%>
    	</div>
    	<br/><br/>
    	<div class="goodsInfo">
    		<div class="lefttt">
    			<img class="goodsImage" alt="<%=goods.getName()%>" src="image/<%=goods.getGoodsPictureURL()%>">
		    </div>
		    <div class="righttt">
			    <span class="goodsName"><a href="viewGoods?goodsId=<%=goods.getGoodsId()%>&userId=<%=user.getUserId()%>"><%=goods.getName()%></a></span><br/><br/>
			    <span class="goodsPrice">单价&nbsp;:&nbsp;<%=goods.getPrice()%>元</span>
			    <span class="goodsCredits">赠送<%=goods.getPrice()/10%>财富积分</span>
			    <br/><br/>
			    <span class="goodsManufacturer">制造商&nbsp;:&nbsp;<%=goods.getManufacturer()%></span>
			<%
				if(goods.getCategory().equals("foods") || goods.getCategory().equals("Foods") || goods.getCategory().equals("美食")) {
			%>
			    <span class="dayCountOfGoodQuality">保质期&nbsp;:&nbsp;<%=goods.getDayCountOfGoodQuality()%>天</span>
			<%
				}else {
			%>
				<span class="zw"></span>
			<%
				}
			%>
			    <span class="goodsProductDate">生产日期&nbsp;:&nbsp;<%=goods.getDateOfProduction()%></span>
				<br/><br/>
				<span class="goodsInfo1">成交&nbsp;:&nbsp;<%=goods.getNumberOfKnockdown()%>单</span>
			    <span class="goodsInfo2">月售&nbsp;:&nbsp;<%=goods.getNumberOfSales()%></span>
			    <br/><br/>
			    <span class="goodsInfo1">累计评价&nbsp;:&nbsp;<%=goods.getNumberOfRemarked()%>条</span>
			    <span class="goodsRemark"><a href="gotoRemarkPage?goodsId=<%=goods.getGoodsId()%>&userId=<%=user.getUserId()%>"><button class="btn"><i class="glyphicon glyphicon-comment"></i>&nbsp;&nbsp;添加评论</button></a></span>
			    <span class="goodsInfo3"><%=goods.getPointOfPraise()%>人赞过该商品</span>
			    <c:if test="${sessionScope.checkPraiseStatus == false or sessionScope.checkPraiseStatus == null}">
			    	<span class="addPraise"><a href="addPraise?goodsId=<%=goods.getGoodsId()%>&userId=<%=user.getUserId()%>"><button class="btn"><i class="glyphicon glyphicon-thumbs-up"></i>&nbsp;&nbsp;赞</button></a></span>
			    </c:if>
			    <c:if test="${sessionScope.checkPraiseStatus == true}">
			    	<span class="cancelPraise"><a href="cancelPraise?goodsId=<%=goods.getGoodsId()%>&userId=<%=user.getUserId()%>"><button class="btn"><i class="glyphicon glyphicon-thumbs-down"></i>&nbsp;&nbsp;取消赞</button></a></span>
			    </c:if>
			    <br/><br/>
			    <form action="addGoodsToCart" method="GET">
				  	<span style="padding-left: 10px;">数量</span>&nbsp;:&nbsp;
				    <button class="numberButton btn" onclick="return minus()"><i class="glyphicon-minus"></i></button>
				    <input id="amount" class="goodsAmount" type="text" style="width: 50px;" name="amount" value="1">
				    <button class="numberButton btn" onclick="return plus()"><i class="glyphicon-plus"></i></button>
				    <span class="goodsRemaining">库存剩余&nbsp;:&nbsp;<%=goods.getNumberOfRemaining()%>件</span>
				    <button type="submit" class="addToCart btn btn-primary"><i class="glyphicon glyphicon-shopping-cart"></i>&nbsp;&nbsp;加入购物车</button>
				    <input type="hidden" name="goodsId" value="<%=goods.getGoodsId()%>"/>
				    <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
				    <input type="hidden" name="previousPageInfo" value="viewGoods"/>
			    </form>
		    </div>
    	</div>
    	<br/>
    	<div>
			<ul id="myTab" class="nav nav-tabs">
				<li class="active"><a href="#remark" data-toggle="tab">累计评价(${sessionScope.remarkList.size()}条)</a></li>
				<li><a href="#description" data-toggle="tab">商品详情</a></li>
			</ul>
		</div>
		<br/>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="remark">
			<%
				@SuppressWarnings("unchecked")
				List<Remark> remarkList = (List<Remark>)session.getAttribute("remarkList");
				if(remarkList.size() > 0) {
			%>
				<c:forEach items="${sessionScope.remarkList}" var="remark" begin="<%=startIndex%>" end="<%=endIndex%>">
					<span class="nickname">用户&nbsp;:&nbsp;${remark.nickname}</span><br/><br/>
					<span class="remarkContent">${remark.content}</span><br/><br/>
					<span class="createTime">发表于&nbsp;:&nbsp;<fmt:formatDate value="${remark.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span><br/>
					<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10)" width="100%" color=#987cb9 SIZE=1>
				</c:forEach>
				<span class="pageIndexes">
					<a class="remarkPageIndex" href="viewGoods?currentRemarkPageNumber=<%=previousPageNumber%>&userId=<%=user.getUserId()%>">上一页</a>
					<c:forEach items="${sessionScope.remarkPageIndex}" var="pageIndex">
						<a class="remarkPageIndex" href="viewGoods?currentRemarkPageNumber=${pageIndex}&userId=<%=user.getUserId()%>">${pageIndex}</a>
					</c:forEach>
					<a class="remarkPageIndex" href="viewGoods?currentRemarkPageNumber=<%=nextPageNumber%>&userId=<%=user.getUserId()%>">下一页</a>
				</span>
			<%
				}else {
			%>
				<br/><br/><h2 style="color: red; font-weight: bold;" align="center">还没有人发表评论哦。</h2>
			<%
				}
			%>
			</div>
			<div class="tab-pane fade" id="description">
				<p class="des"><%=goods.getDescription()%></p>
			</div>
		</div>
</body>
</html>