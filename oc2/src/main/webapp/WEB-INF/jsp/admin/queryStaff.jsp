<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>员工查询</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/queryStaff.css">
		<script type="text/javascript" src="${path}/open_tab/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="js/queryStaff.js"></script>
	</head>
	<body>
		<form action="${path}/staffQuery" method="POST" id="queryForm">
			<div>
				<font style="margin-left: 30px;">中文名(英文名)</font>&nbsp;:&nbsp;
				<input type="text" class="inputtt" style="width: 156px; height: 25px; text-indent: 4px;" placeholder="输入中文名或英文名" id="queryName"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				所属部门&nbsp;:&nbsp;
				<select name="deptID" id="queryDepartment" class="inputttt input-large" style="width: 156px; height: 25px; margin-top: 25px;" onchange="getAllPosition()">
					<option value="请选择" selected="selected">请选择</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				职位&nbsp;:&nbsp;
				<select name="position_CN" id="queryPosition" class="inputttt input-large" style="width: 156px; height: 25px; margin-top: 25px;">
					<option value="请选择" selected="selected">请选择</option>
				</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-primary" style="height: 30px; width: 80px;"><i class="glyphicon glyphicon-search"></i>&nbsp;&nbsp;查询</button>
			</div>
			<input type="hidden" name="name_CN" id="name_CN" value=""/>
			<input type="hidden" name="name_EN" id="name_EN" value=""/>
		</form>
		<br/>
		<table>
			<tr height="35px;">
				<th style="width: 350px;"><font style="padding-left: 190px; font-size: x-large; font-weight: bolder;">Name</font></th>
				<th style="width: 350px;"><font style="padding-left: 110px; font-size: x-large; font-weight: bolder;">Department_CN</font></th>
				<th style="width: 240px;"><font style="padding-left: 87px; font-size: x-large; font-weight: bolder;">Position_CN</font></th>
				<th style="width: 350px;"><font style="padding-left: 43px; font-size: x-large; font-weight: bolder;">Details</font></th>
			</tr>
			<c:forEach items="${allStaffInfoList}" var="staffInfo">
				<tr height="25px;">
					<td style="width: 350px; vertical-align: middle; text-align: center;"><font style="padding-left: 105px;">${staffInfo.name_CN}&nbsp;(${staffInfo.name_EN})</font></td>
					<td style="width: 350px; vertical-align: middle; text-align: center;"><font style="padding-left: 85px;">${staffInfo.department_CN}</font></td>
					<td style="width: 240px; vertical-align: middle; text-align: center;"><font style="padding-left: 95px;">${staffInfo.position_CN}</font></td>
					<td style="width: 350px;"><font style="padding-left: 45px;"><button class="btn btn-link" onclick="self.parent.Open('(${staffInfo.name_CN})信息浏览'  , '${path}/staffInfoDetails?no=${staffInfo.no}')">查看详情</button></font></td>
				</tr>
				<tr class="trline">
					<td colspan="4" height="1">
						<img src="images/line.png">
					</td>
				</tr>
			</c:forEach>
		</table>
		<script type="text/javascript">
			var allPosition;
			//加载页面时往下拉框中写入当前已存在的所有部门
			$.post("${path}/getAllDepartment" , null , function(data) {
				$.each(data, function(i, department) {
					var ID = department.deptID;
					var demo = '<option value="' + ID + '">' + department.department_CN + '</option>';
					$("#queryDepartment").append(demo);
				});
			}, "json");
			
			//加载页面时往下拉框中写入当前已存在的所有职位
			$.post("${path}/getAllExistedPosition" , null , function(data) {
				allPosition = data;
				$.each(data, function(i, position) {
					var position_CN = position.position_CN;
					var demo = '<option value="' + position_CN + '">' + position.position_CN + '</option>';
					$("#queryPosition").append(demo);
				});
			}, "json");
			
			//根据deptId联动职位
			 function getAllPosition() {
					//获取下拉框主键
					var deptID = $("#queryDepartment").find('option:selected').val();
					if(deptID == '请选择') {
						$.each(allPosition, function(i, position) {
							var position_CN = position.position_CN;
							var demo = '<option value="' + position_CN + '">' + position.position_CN + '</option>';
							$("#queryPosition").append(demo);
						});
					}else {
						$("#queryPosition").empty();  //清空所有职位，写入已选择部门下的所有职位
						var classNext = $("#queryPosition"); //获取已选择部门下相应的所有职位
						classNext.empty();
						$.ajax({
							 url : '${path}/getAllPosition',
				             type: 'POST',
				             data : {
				            	'deptID' : deptID
				             },
				             success: function (result) {
				            	    if(result.length != "" && result.length != null){
				            	    		classNext.append("<option>请选择 </option>");
				                 	      	for ( var i = 0; i < result.length; i++) {
				                 	      		console.log(result[i]);
				                 	      		
				                 				classNext.append("<option value='" + result[i]["position_CN"] + "'>" + result[i]["position_CN"] + "</option>");
				                 	 		}
				            	     }else{
				            			classNext.append("<option value=" + "></option>");
				            	     }
				       		 }   
				       	 }); 
					}
				}
			
			$('#queryForm').submit(function(){
				//英文正则
				var name_EN = /^\w+$/;
				//中文正则
				var name_CN = /^[\u4e00-\u9fa5]+$/;
				var checkStatus = false;
				var queryName = $('#queryName').val();
				var queryDepartment = $('#queryDepartment').val();
				if(name_EN.test(queryName)){
					$('#name_EN').val(queryName);
					checkStatus = true;
				}else if(name_CN.test(queryName)){
					$('#name_CN').val(queryName);
					checkStatus = true;
				}else if(queryName == "" && queryDepartment != "请选择"){
					checkStatus = true;
				}else if(queryName == "" && queryDepartment == "请选择"){
					checkStatus = true;
				}else{
					alert("查询条件输入有误，请您修改。");
				}
				return checkStatus;
			});
		</script>
	</body>
</html>