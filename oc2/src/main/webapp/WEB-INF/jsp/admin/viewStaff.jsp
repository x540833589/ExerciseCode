<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>员工信息</title>
		<link rel="stylesheet" href="${path}/css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="${path}/css/addmember.css" type="text/css"/>
	</head>
	<body>
		<h3 align="center">浏览信息</h3><br/>
		<div class="out">
			<div class="mydiv">
				    <div class="block">
				         <label>员工编号:</label>
				         <input type="text" id="no" name="no" class="inputtt input-large" readonly="readonly" value="${staffDetails.no}">
				    </div>
					<br/>
				    <div class="block">
						<label for="name_CN">中文名:</label>
						<input type="text" class="inputtt input-large" name="name_CN" id ="name_CN" readonly="readonly" value="${staffDetails.name_CN}"/>
					</div>
					<br/>
					<div class="block">
						<label for="name_EN">英文名:</label>
						<input type="text" name="name_EN" id="name_EN" class="inputtt input-large" readonly="readonly" value="${staffDetails.name_EN}"/>
					</div>
					<br/>
					<div class="block">
						<label>邮箱:</label>
						<input type="email" name="email" id="email" class="inputtt" readonly="readonly" value="${staffDetails.email}"/>
					</div>
					 <br/>
				    <div class="block">
						<label>部门:</label>
						<input type="text" name="department" id="department" class="inputtt" readonly="readonly" value="${department.deptID} ${department.department_EN} ${department.department_CN}"/>
					</div>
					<br/>
					<div class="block">
						<label>职位:</label>
						<input type="text" name="position" id="position" class="inputtt" readonly="readonly" value="${position.position_EN} ${position.position_CN}"/>
					</div>
				    <br/>
				    <div class="block">
						<label>在职:</label>
						<c:if test="${staffDetails.isActive eq 1}">是</c:if>
						<c:if test="${staffDetails.isActive eq 0}">否</c:if>
				    </div>
					<br/>
				    <div class="block">
					 	<label id="yulan">照片:</label>
					 	<img id="portrait" src="${staffDetails.photo}" style="height: 150px; width: 125px; margin-left: 155px;"/>
					 	<br/>
		            </div>
		            <br/>
		            <div class="block">
		                 <button type="button" class="btn btn-primary" style="margin-left: 167px;" onclick="self.parent.Open('(${staffDetails.name_CN})信息编辑','${path}/editStaffInfo?no=${staffDetails.no}')"><i class="glyphicon glyphicon-pencil"></i>&nbsp;&nbsp;编辑信息</button>&emsp;&emsp;<font color="red">${message}</font>
		            </div>
		   </div>
		</div>	
		<script type="text/javascript" src="${path}/open_tab/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="${path}/js/viewStaff.js"></script>
		<script type="text/javascript">
			var realPath = "${path}";
			//获得部门信息的ajax
			$(function() {
				$.post("${path}/getAllDepartment", null, function(data) {
					$.each(data, function(i, department) {
						var ID = department.deptID;
						var demo = '<option value="' + ID + '">' + ID + ' ' + department.department_EN + ' ' + department.department_CN + '</option>';
						$("#department").append(demo);
					});
				}, "json")
			})
			
			//照片实时显示
			function showPreview(source) {
				var file = source.files[0];
				if (window.FileReader) {
					var fr = new FileReader();
					console.log(fr);
					var portrait = document.getElementById('portrait');
					fr.onloadend = function(e) {
						portrait.src = e.target.result;
					};
					fr.readAsDataURL(file);
					portrait.style.display = 'block';
				}
			}
			//动态居中
			$(window).resize(function() {
				$(".mydiv").css({
					position : "absolute",
					left : ($(window).width() - $(".mydiv").outerWidth()) / 2,
	
				});
	
			});
			//页面加载时调用动态居中
			$(function() {
				$(window).resize();
			});
			
			
			//根据deptId联动职位
			 function getAllPosition() {
					//获取下拉框主键
					var deptID = $("#department").find('option:selected').val();
					console.log(deptID);
					$("#position").empty();  //清空二级目录 
					$("#position_EN").empty(); //清空文本框 
					var classNext = $("#position"); //获取已选择部门下相应的所有职位
					
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
			                 	      		
			                 				classNext.append("<option value='" + result[i].no + "'>" + result[i]["position_EN"] + ' ' + result[i]["position_CN"] + "</option>");
			                 	 		}
			            	     }else{
			            			classNext.append("<option value=" + "></option>");
			            	     }
			       		 }   
			       	 }); 
				}
			
			/* //把英文职位写入text的方法
			function changeValue() {
				val = $('#position').find("option:selected");
				if(val!=null){
					document.getElementById("position_EN").value = val;
				}
				else{
					document.getElementById("position_EN").value ="";
				}
			} */
			
		</script>
	</body>
</html>