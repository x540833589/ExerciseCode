<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="open_tab/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/structureDetail.css">
	<style type="text/css">
		.personInfoLeader {
			position: relative;
			margin-left: 455px !important;
			width: 300px !important;
			height: 200px;
			float: left;
		}
		.leader {
			padding-left: 0% !important;
			padding-top: 25px;
			height: 175px;
		}
	</style>
	<script class="jsbin" src="open_tab/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="open_tab/js/jquery.easyui.min.js"></script>
</head>
<body>
	<div>
		<c:forEach items="${leaderInfo}" var="info">
			<div class="personInfoLeader">
				<div class="leader">
					<img src="${path}/${info.photo}" class="photo"/>
					<div style="height: 20px;"></div>
					<span>&nbsp;&nbsp;&nbsp;&nbsp;${info.name_EN}(${info.name_CN})</span><br/><br/>
					<span>&nbsp;&nbsp;&nbsp;&nbsp;${info.department_EN}</span><br/><br/>
					<span>&nbsp;&nbsp;&nbsp;&nbsp;${info.position_EN}</span>
				</div>
			</div>
		</c:forEach>
	</div>
	<HR style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#000000,strength=10)" width="95%" color=#987cb9 SIZE=1>
	<div>
		<c:forEach items="${employeeInfo}" var="info2">
			<div class="personInfoMember">
				<div class="member">
					<c:if test="${info2.name_CN == '熊碧恒'}">
						<img src="${path}/${info2.photo}" class="photo" onclick="self.parent.Open('Automation','${path}/showSpecial')"/>
					</c:if>
					<c:if test="${info2.name_CN != '熊碧恒'}">
						<img src="${path}/${info2.photo}" class="photo" onclick="self.parent.Open('${info2.department_EN}','${path}/showDetail?deptID=${info2.deptID}')"/>
					</c:if>
					<div style="height: 25px;"></div>
					<span>&nbsp;&nbsp;&nbsp;&nbsp;${info2.name_EN}(${info2.name_CN})</span><br/><br/>
					<span>&nbsp;&nbsp;&nbsp;&nbsp;${info2.department_EN}</span><br/><br/>
					<span>&nbsp;&nbsp;&nbsp;&nbsp;${info2.position_EN}</span>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>