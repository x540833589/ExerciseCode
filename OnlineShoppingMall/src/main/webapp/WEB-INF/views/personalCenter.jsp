<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="cn.com.shoppingmall.domain.User"%>
<%@ page import="cn.com.shoppingmall.domain.Order"%>
<%@ page import="cn.com.shoppingmall.domain.Remark"%>
<%@ page import="cn.com.shoppingmall.relate.OrderItem"%>
<%@ page import="cn.com.shoppingmall.domain.Cart"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
<link type="text/css" rel="stylesheet" href="css/personalCenter.css">
</head>
<body>
	<%
		@SuppressWarnings("unchecked")
		List<Remark> remarkList = (List<Remark>) session.getAttribute("personalRemarkList");
		@SuppressWarnings("unchecked")
		List<Integer> remarkPageIndex = (List<Integer>) session.getAttribute("remarkPageIndex");
		@SuppressWarnings("unchecked")
		List<OrderItem> orderItemList = (List<OrderItem>) session.getAttribute("personalOrderItemList");
		@SuppressWarnings("unchecked")
		List<Integer> orderPageIndex = (List<Integer>) session.getAttribute("orderPageIndex");
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		Integer goodsId = (Integer) session.getAttribute("currentGoodsId");
		Integer userId = user.getUserId();
		Integer currentOrderPageNumber = (Integer) session.getAttribute("centerOrderPageNumber");
		Integer currentRemarkPageNumber = (Integer) session.getAttribute("centerRemarkPageNumber");
		Integer maxOrderPageNumber = orderPageIndex.size();
		Integer maxRemarkPageNumber = remarkPageIndex.size();
		Integer previousOrderPageNumber = currentOrderPageNumber == 1 ? 1 : currentOrderPageNumber - 1;
		Integer nextOrderPageNumber = currentOrderPageNumber == maxOrderPageNumber ? maxOrderPageNumber
				: currentOrderPageNumber + 1;
		Integer previousRemarkPageNumber = currentRemarkPageNumber == 1 ? 1 : currentRemarkPageNumber - 1;
		Integer nextRemarkPageNumber = currentRemarkPageNumber == maxRemarkPageNumber ? maxRemarkPageNumber
				: currentRemarkPageNumber + 1;
		Integer orderStartIndex = (currentOrderPageNumber - 1) * 5;
		Integer orderEndIndex = currentOrderPageNumber * 5 - 1;
		Integer remarkStartIndex = (currentRemarkPageNumber - 1) * 5;
		Integer remarkEndIndex = currentRemarkPageNumber * 5 - 1;
	%>
	<div>
		<br /> <span class="prePageInfo"><a href="index"
			style="margin-left: 15px;">首页</a></span>
		<c:if test="${not empty sessionScope.currentGoodsId}">
			<span class="prePageInfo">&nbsp;&nbsp;//&nbsp;&nbsp;<a
				href="viewGoods?goodsId=<%=goodsId%>&userId=<%=userId%>">继续购物</a></span>
		</c:if>
		<span class="rightt"><a href="userLogout">注销</a></span> <span
			class="rightt"><a
			href="gotoCartPage?previousPageInfo=personalCenter">我的购物车</a>(<c:if
				test="${empty sessionScope.cart.goodsList.size()}">0</c:if>${sessionScope.cart.goodsList.size()}件商品,<c:if
				test="${empty sessionScope.cart.totalPrice}">0.0</c:if>${sessionScope.cart.totalPrice}元)</span>
		<span class="rightt"><a
			href="editPersonalAccount?username=<%=user.getUsername()%>">修改资料</a></span>
		<br />
	</div>
	<HR
		style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9, strength=10)"
		width="100%" color=#987cb9 SIZE=1>
	<br />
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#order">
						<font style="color: red; font-size: medium; font-weight: bold;">我的订单</font>
					</a>
				</h4>
			</div>
			<div id="order" class="panel-collapse collapse in">
				<div class="panel-body">
					<div class="myOrder">
						<c:forEach items="<%=orderItemList%>" var="item"
							begin="<%=orderStartIndex%>" end="<%=orderEndIndex%>">
							<div>
								<div class="lefttt">
									<p class="Id">订单编号&nbsp;:&nbsp;${item.orderId}</p>
								</div>
								<div class="righttt">
									<span id="goodsId${item.goodsId}" style="color: transparent;">${item.goodsId}</span>
									<br /> <span class="name"><a
										href="viewGoods?goodsId=${item.goodsId}&userId=${item.userId}">${item.goodsName}</a></span><br />
									<br />
									<span class="unitPrice">单价&nbsp;:&nbsp;${item.unitPrice}元</span>
									<span class="amount">数量&nbsp;:&nbsp;${item.amount}</span> <span
										class="itemTotalPrice">共计&nbsp;:&nbsp;${item.totalPrice}元
									</span>
									<c:if test="${item.deliverStatus == 'N'}">
										<span class="deliverStatus"><i
											class="glyphicon glyphicon-remove-sign"></i>&nbsp;&nbsp;未发货</span>
									</c:if>
									<c:if test="${item.deliverStatus == 'Y'}">
										<span class="deliverStatus"><i
											class="glyphicon glyphicon-ok-sign"></i>&nbsp;&nbsp;已发货</span>
									</c:if>
								</div>
								<HR
									style="margin-bottom: 0px; FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9, strength=10)"
									width="100%" color=#987cb9 SIZE=1>
							</div>
						</c:forEach>
						<br/>
						<c:if test="${sessionScope.personalOrderItemList.size() ne 0}">
							<span class="pageIndexes"> <span class="pageIndex"><a
									href="gotoPersonalCenterPage?centerOrderPageNumber=<%=previousOrderPageNumber%>&userId=<%=userId%>">上一页</a></span>
								<c:forEach items="<%=orderPageIndex%>" var="pageNumber">
									<span class="pageIndex"><a
										href="gotoPersonalCenterPage?centerOrderPageNumber=${pageNumber}&userId=<%=userId%>">${pageNumber}</a></span>
								</c:forEach> <span class="pageIndex"><a
									href="gotoPersonalCenterPage?centerOrderPageNumber=<%=nextOrderPageNumber%>&userId=<%=userId%>">下一页</a></span>
							</span>
						</c:if>
						<c:if test="${sessionScope.personalOrderItemList.size() eq 0}">
							<br/><br/><h2 style="text-align: center; color: black; font-weight: bold;">还没有任何下单记录哦。</h2>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-group" id="accordion">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h4 class="panel-title">
					<a data-toggle="collapse" data-parent="#accordion" href="#remark">
						<font style="color: red; font-size: medium; font-weight: bold;">我的评论</font>
					</a>
				</h4>
			</div>
			<div id="remark" class="panel-collapse collapse in">
				<div class="panel-body">
					<div class="myOrder">
						<c:forEach items="<%=remarkList%>" var="remark"
							begin="<%=remarkStartIndex%>" end="<%=remarkEndIndex%>">
							<div>
								<span class="nickname">评论&nbsp;:&nbsp;<a
									href="viewGoods?goodsId=${remark.goodsId}&userId=${remark.userId}">${remark.goodsName}</a></span><br />
								<br /> <span class="remarkContent">${remark.content}</span><br />
								<br /> <span class="createTime">发表于&nbsp;:&nbsp;<fmt:formatDate
										value="${remark.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span><br />
								<HR
									style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9, strength=10)"
									width="100%" color=#987cb9 SIZE=1>
							</div>
						</c:forEach>
						<br/>
						<c:if test="${sessionScope.personalRemarkList.size() ne 0}">
							<span class="pageIndexes"> <span class="pageIndex"><a
									href="gotoPersonalCenterPage?centerRemarkPageNumber=<%=previousRemarkPageNumber%>&userId=<%=userId%>">上一页</a></span>
								<c:forEach items="<%=remarkPageIndex%>" var="pageNumber">
									<span class="pageIndex"><a
										href="gotoPersonalCenterPage?centerRemarkPageNumber=${pageNumber}&userId=<%=userId%>">${pageNumber}</a></span>
								</c:forEach> <span class="pageIndex"><a
									href="gotoPersonalCenterPage?centerRemarkPageNumber=<%=nextRemarkPageNumber%>&userId=<%=userId%>">下一页</a></span>
							</span>
						</c:if>
						<c:if test="${sessionScope.personalRemarkList.size() eq 0}">
							<br/><br/><h2 style="text-align: center; color: black; font-weight: bold;">还没有发表任何看法哦。</h2>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>