<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>员工管理</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/glyphicons.css"/>
		<link rel="stylesheet" type="text/css" href="css/adminMain.css"/>
		<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	</head>
	<body>
		<br/>
		<h1 style="text-align: center; font-weight: bold; text-shadow: 1px 1px 1px black;">welcome to admin page</h1>
		<a href="#" id="adminLogout"><button class="btn btn-primary" style="float: right; margin-right: 25px;"><i class="fa fa-power-off"></i><font style="margin-bottom: 2px;">&nbsp;&nbsp;注销</font></button></a>
		<div class="OperationButtonGroup">
	 		<span class="adminOperation"><a id="addStaffButton" class="myButton"><i class="icon-user-add" style="font-size: 100px;"></i><br/><font style="font-size: xx-large;">添加员工</font></a></span>
	 		<span class="adminOperation"><a id="queryStaffButton" class="myButton">&nbsp;&nbsp;&nbsp;<i class="icon-search" style="font-size: 100px;"></i><br/><font style="font-size: xx-large;">查询员工</font></a></span>
	 		<span class="adminOperation"><a id="addPositionButton" class="myButton">&nbsp;&nbsp;&nbsp;<i class="icon-adress-book" style="font-size: 100px;"></i><br/><font style="font-size: xx-large;">添加职位</font></a></span>
	 		<span class="adminOperation"><a id="addDepartmentButton" class="myButton">&nbsp;&nbsp;&nbsp;<i class="icon-group" style="font-size: 100px;"></i><br/><font style="font-size: xx-large;">添加部门</font></a></span>
		</div>
	</body>
	<script type="text/javascript" src="open_tab/js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
		$('#addStaffButton').click(function(){
			self.parent.Open('新人入职','${path}/gotoNewStaff');
		});
		$('#queryStaffButton').click(function(){
			self.parent.Open('查询员工','${path}/gotoQueryStaff');
		});
		$('#addPositionButton').click(function(){
			self.parent.Open('添加职位','${path}/gotoAddPosition');
		});
		$('#addDepartmentButton').click(function(){
			self.parent.Open('添加部门','${path}/gotoAddDepartment');
		});
		$('#adminLogout').click(function(){
			$.ajax({
				url : '${path}/logout',
				type : 'POST',
				success : function(result){
					if(result == "注销成功");
						self.parent.closeCurrentPage();
				}
			});
		});
	</script>
</html>