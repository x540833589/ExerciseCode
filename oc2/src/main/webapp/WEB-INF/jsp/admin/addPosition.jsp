<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>添加职位</title>
	<link rel="stylesheet" href="${path}/css/bootstrap.min.css" type="text/css"/>
	<link rel="stylesheet" href="${path}/css/bootstrap-multiselect.css" type="text/css"/>
	<link rel="stylesheet" href="${path}/css/addPosition.css" type="text/css"/>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/xcConfirm.js" charset="utf-8"></script>
</head>
<body>
	<h3 style="margin-left: 51.4%;">添加职位</h3><br/>
		<div class="out">
			<div class="mydiv">
				<form id="addPositionForm" method="POST">
					<!-- <img src="images/selectDepartment.png" height="40px;" width="570px;"/> -->
					<img src="images/1.png" height="30px;" width="35px;" style="margin-left: 90.5%; margin-bottom: 3px;"/>
				    <div class="block">
						<label for="department_selected" class="multipleSelectx"><font class="useLead multipleSelectx">请选择待添加职位的部门:</font></label>
						<select id="departmentExisted" name="departmentExisted" multiple="multiple">
						</select>
					</div>
					<br/>
					<!-- <img src="images/addNewPosition.png" height="40px;" width="570px;"/> -->
					<img src="images/2.png" height="30px;" width="35px;" style="margin-left: 90%; margin-bottom: 3px;"/>
					<div class="block">
						<label for="position_CN" class="multipleSelecty"><font class="useLead multipleSelecty">请输入新职位中文名:</font></label>
						<input type="text" name="position_CN" id="position_CN" class="inputtt input-large" style="width: 380px; height: 25px;"/>
						<span id="DepartmentEnStatus" class="checkInputStatus"></span>
					</div>
					<br/>
				    <div class="block">
						<label for="position_EN" class="multipleSelecty"><font class="useLead multipleSelecty">请输入新职位英文名:</font></label>
						<input type="text" name="position_EN" id="position_EN" class="inputtt input-large" style="width: 380px; height: 25px;"/>
					</div>
					<br/>
					<div class="blockx">
						<label for="isSharedPosition" class="labelForIsSharedPosition"><font style="color: red;">该职位为多人职位(如开发工程师、项目经理、部门经理等)</font></label>
						<input type="checkbox" name="sharedPosition" id="isSharedPosition" style="margin-left: 183px;" value="yes"/>
					</div>
					<br/>
					<!-- <img src="images/addExistedPosition.png" height="40px;" width="570px;"/> -->
					<img src="images/3.png" height="30px;" width="35px;" style="margin-left: 90%; margin-bottom: 3px;"/>
					<div class="block">
						<label for="department_selected" class="multipleSelectx"><font class="useLead multipleSelectx">添加多人职位:</font></label>
						<select id="positionExisted" name="positionExisted" multiple="multiple">
						</select>
					</div>
					<br/><br/>
					<input type="hidden" id="newPositionStructureDistribution" name="structureDistribution" value=""/>
					<input type="hidden" id="newPositionIsHeadStatus" name="isHeadStatus" value="0"/>
					<div class="block">
		                 <button type="button" id="submitButton" class="btn btn-primary" style="margin-left: 51.4%;"><i class="glyphicon glyphicon-ok"></i><font style="font-size: 15px;">&nbsp;&nbsp;确认添加</font></button>
		            </div>
					<br/>
				</form>
		   </div>
		</div>	
		<script type="text/javascript">
			//获得部门信息的ajax
			$(function() {
				//获取当前存在的所有部门
				$.post("${path}/getAllExistedDepartment" , null , function(data) {
					$.each(data, function(i, department) {
						var ID = department.deptID;
						var demo = '<option value="' + ID + '">' + ID + ' ' + department.department_EN + ' ' + department.department_CN + '</option>';
						$("#departmentExisted").append(demo);
					});
					//启用多选下拉框插件
					$('#departmentExisted').multiselect({
						buttonWidth: 380,
						enableFiltering: true,
				        filterPlaceholder: '输入关键字搜索部门...'
					});
				}, "json");
				
				//获取当前存在的所有共享职位
				$.post("${path}/getAllSharedPosition" , null , function(data) {
					$.each(data, function(i, position) {
						var no = position.no;
						var demo2 = '<option value="' + no + '">' + no + ' ' + position.position_EN + ' ' + position.position_CN + '</option>';
						$("#positionExisted").append(demo2);
					});
					//启用多选下拉框插件
					$('#positionExisted').multiselect({
						buttonWidth: 380,
						enableFiltering: true,
				        filterPlaceholder: '输入关键字搜索职位...'
					});
				}, "json");
				
			});
			
			$('#position_CN').keyup(function(){
				console.log('进入职位中文输入方法');
				var currentPositionCN = $('#position_CN').val();
				//记录当前选中的部门的数量
				var departmentNum = 0;
				$("#departmentExisted option:selected").each(function(){
					departmentNum++;
				});
				console.log("已选中的部门数量:" + departmentNum);
				//如果添加新职位时，已选中多个部门，则其为共享职位(选中复选框)
				if(departmentNum > 1) {
					$('#isSharedPosition').prop('checked' , true);
					$('#newPositionStructureDistribution').val(0);
				}else if(departmentNum == 1) {
					//如果当前只选中一个部门，默认取消多人职位选中状态
					$('#isSharedPosition').prop('checked' , false);
					var currentDeptID = $("#departmentExisted option:selected").val();
					if(currentDeptID.indexOf('000') != -1) {
						if(currentDeptID == 'D1000') {
							var numberPart = currentDeptID.substring(1);
							numberPart += '4';
						}else {
							var numberPart = currentDeptID.substring(1);
							numberPart += '3';
						}
						$('#newPositionStructureDistribution').val(parseInt(numberPart));
					}else if(currentDeptID.indexOf('00') != -1) {
						var numberPart = currentDeptID.substring(1);
						numberPart += '2';
						$('#newPositionStructureDistribution').val(parseInt(numberPart));
					}else if(currentDeptID.indexOf('0') != -1) {
						var numberPart = currentDeptID.substring(1);
						numberPart += '1';
						$('#newPositionStructureDistribution').val(parseInt(numberPart));
					}
				}
				console.log('多人职位选中状态:' + $('#isSharedPosition').prop('checked'));
				console.log("方法结束");
			});
			
			
			//点击按钮时异步提交表单
			$('#submitButton').click(function(){
				var submitAddPositionForm = function(currentSelectedDepartment , currentSelectedPosition) {
					console.log()
					//创建表单对象
					var formData = {
						departmentSelected : currentSelectedDepartment,
						positionSelected : currentSelectedPosition,
						newPosition_CN : $('#position_CN').val(),
						newPosition_EN : $('#position_EN').val(),
						structureDistribution : $('#newPositionStructureDistribution').val(),
						isHeadStatus : $('#newPositionIsHeadStatus').val()
					};
						
					$.ajax({
						url : '${path}/addPositionAndRelations',
						type : 'POST',
						data: {
							'formData' : JSON.stringify(formData)
						},
						success : function(result) {
							//添加成功，关闭当前页面
							if(result == '操作成功')
								self.parent.closeCurrentPage();
								console.log("添加职位成功");
						}
					});
					//console.log("已选择部门数量:" + departmentNum);
				}
				//表单提交时若多人职位被选中，则新职位的structureDistribution字段值设为0
				if($('#isSharedPosition').val() == 'yes') {
					$('#newPositionStructureDistribution').val('0');
				}
				var departmentNum = 0;
				//保存当前选中的部门
				var currentSelectedDepartment = [];
				//保存当前选中的共享职位
				var currentSelectedPosition = [];
				//获取用户输入的新职位的中文名
				var newPosition_CN = $('#position_CN').val();
				//获取用户输入的新职位的英文名
				var newPosition_EN = $('#position_EN').val();
				$("#departmentExisted option:selected").each(function () {
					currentSelectedDepartment.push($(this).val());
					departmentNum++;
				});
				$("#positionExisted option:selected").each(function () {
					currentSelectedPosition.push($(this).val());
				});
				
				if(currentSelectedDepartment.length == 0){
					window.wxc.xcConfirm("部门信息填写有误，请修正。" , window.wxc.xcConfirm.typeEnum.info);
					//alert("部门信息填写有误，请修正。");
				}else {
					if(newPosition_CN.length * newPosition_EN.length != 0) {
						submitAddPositionForm(currentSelectedDepartment , currentSelectedPosition);
					}
					else if(newPosition_CN.length == 0 && newPosition_EN.length == 0 && currentSelectedPosition.length != 0) {
						submitAddPositionForm(currentSelectedDepartment , currentSelectedPosition);
					}
					else if(newPosition_CN.length * newPosition_EN.length != 0 && currentSelectedPosition.length != 0) {
						submitAddPositionForm(currentSelectedDepartment , currentSelectedPosition);
					}else {
						window.wxc.xcConfirm("职位信息填写有误，请修正。" , window.wxc.xcConfirm.typeEnum.info);
						//alert("职位信息填写有误，请修正。");
					}
				}
			});
			
			
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
			
		</script>
</body>
</html>