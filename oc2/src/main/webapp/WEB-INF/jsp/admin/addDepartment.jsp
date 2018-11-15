<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>添加部门</title>
	<link rel="stylesheet" href="${path}/css/bootstrap.min.css" type="text/css"/>
	<link rel="stylesheet" href="${path}/css/addDepartment.css" type="text/css"/>
	<link rel="stylesheet" href="${path}/css/xcConfirm.css" type="text/css"/>
	<link rel="stylesheet" href="css/glyphicons.css" type="text/css"/>
	<style type="text/css">
		.xcConfirm .popBox .txtBox p {
		    height: 84px;
		    margin-top: 35px;
		    line-height: 26px;
		    overflow-x: hidden;
		    overflow-y: auto;
		    font-size: 16px !important;
		}
		.xcConfirm .popBox .sgBtn.ok {
		    background-color: #0095d9;
		    color: #FFFFFF;
		    margin-top: 0px !important;
		    margin-right: 25px !important;
		    width: 80px;
		    font-size: 16px !important;
		    text-decoration: none !important;
		}
	</style>
	<script type="text/javascript" src="${path}/js/jquery-3.3.1.min.js"></script>
	<script src="js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript" src="open_tab/js/jquery.easyui.min.js"></script>
</head>
<body>
	<h3 style="margin-left: 45.5%;">添加一个新部门</h3><br/>
		<div class="out">
			<div class="mydiv">
				<form id="newDepartmentForm" method="POST">
				    <div class="block">
						<label for="name_CN">请输入部门中文名:</label>
						<input type="text" name="department_CN" id ="department_CN" class="inputtt input-large" style="width: 156px; height: 22.4px;"/>
						<span id="DepartmentCnStatus" class="checkInputStatus"></span>
					</div>
					<br/>
					<div class="block">
						<label for="name_EN">请输入部门英文名:</label>
						<input type="text" name="department_EN" id="department_EN" class="inputtt input-large" style="width: 156px; height: 22.4px;"/>
						<span id="DepartmentEnStatus" class="checkInputStatus"></span>
					</div>
					<br/>
				    <div class="block">
						<label>直属上级部门:</label>
						<select id="upOneLevel" name="upOneLevel" class="inputtt" style="width: 156px; height: 22.4px;" onchange="upOneLevelSelected()">
							<option value="请选择">请选择</option>
						</select> 
						<span id="deptIdStatus" class="checkInputStatus"></span>
					</div>
					<br/>
					<input type="hidden" name="deptID" id="deptID" value=""/>
		            <input type="hidden" name="deptLevel" id="deptLevel" value=""/>
		            <input type="hidden" name="bu_Head" id="bu_Head" value=""/>
		            <div class="block">
		                 <button type="button" id="submitButton" class="btn btn-primary" style="margin-left: 210px;"><i class="glyphicon glyphicon-ok"></i>&nbsp;&nbsp;提交</button>
		            </div>
				</form>
				<form id="newDepartmentPositionForm" method="POST">
					<div class="block">
						<label for="position_CN">请输入管理者职位中文名:</label>
						<input type="text" name="position_CN" id="position_CN" class="inputtt input-large" style="width: 156px; height: 22.4px;"/>
						<span id="DepartmentCnStatus" class="checkInputStatus"></span>
					</div>
					<br/>
					<div class="block">
						<label for="position_EN">请输入管理者职位英文名:</label>
						<input type="text" name="position_EN" id ="position_EN" class="inputtt input-large" style="width: 156px; height: 22.4px;"/>
					</div>
					<br/>
					<div class="blockx">
						<label for="isSharedPosition" class="labelForIsSharedPosition"><font style="color: red;">该职位为多人职位(如开发工程师、项目经理、部门经理等)</font></label>
						<input type="checkbox" name="sharedPosition" id="isSharedPosition" style="margin-left: 170px;" value="yes"/>
					</div>
					<br/>
					<div class="block">
						<label>选择已有职位:</label>
						<select id="sharedPosition" name="positionID" class="inputtt" style="width: 156px; height: 22.4px;" onchange="sharedPositionSelected()">
							<option value="请选择">请选择</option>
						</select> 
						<span id="deptIdStatus" class="checkInputStatus"></span>
					</div>
					<br/>
					<input type="hidden" id="sharedDeptID" name="deptID" value=""/>
					<input type="hidden" id="newPositionStructureDistribution" name="structureDistribution" value=""/>
					<input type="hidden" id="newPositionIsHeadStatus" name="isHeadStatus" value=""/>
					<div class="block">
		                 <button type="button" id="submitButton2" class="btn btn-primary" style="margin-left: 210px;"><i class="glyphicon glyphicon-ok"></i>&nbsp;&nbsp;提交</button>
		            </div>
					<br/>
				</form>
		   </div>
		</div>	
		<script type="text/javascript" src="${path}/open_tab/js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="${path}/js/addmember.js"></script>
		<script type="text/javascript">
		
			$('#newDepartmentPositionForm').hide();  //隐藏二级表单
		
			var currentNewDepartmentDeptID = '';	//当前新部门的部门编号
		
			//获得部门信息的ajax
			$(function() {
				$.post("${path}/getAllSuperiorDepartment" , null , function(data) {
					$.each(data, function(i, department) {
						var ID = department.deptID;
						var demo = '<option value="' + ID + '">' + ID + ' ' + department.department_EN + ' ' + department.department_CN + '</option>';
						$("#upOneLevel").append(demo);
					});
				}, "json");
				
				$.post("${path}/getAllSharedPosition" , null , function(data) {
					$.each(data, function(i, position) {
						var no = position.no;
						var demo2 = '<option value="' + no + '">' + no + ' ' + position.position_EN + ' ' + position.position_CN + '</option>';
						$("#sharedPosition").append(demo2);
					});
				}, "json");
				
			});
			
			//选中上级部门的时候，动态拼接隐藏域字段
			function upOneLevelSelected() {
				
				var deptID = $("#upOneLevel").find('option:selected').val();
				
				$.ajax({
					url : '${path}/getAllChildrenDepartmentByDeptID',
					type : 'POST',
					data : {
						'deptID' : deptID
					},
					success : function(result){
						//当前待添加部门有同级部门
						if(result.length != 0) {
							var currentDepartment = result[result.length - 1]; 	//当前待添加部门最近的一个同级部门
							var currentDeptID = currentDepartment.deptID;		//同级部门的编号
							var currentDeptLevel = currentDepartment.deptLevel;	//同级部门的部门等级
							//最近的一个同级部门的编号的数字位，例如最近的一个同级部门为'D8130',其数字位为'813'
							var previousNewID = currentDeptID.substring(1 , 1 + currentDeptLevel);
							//带添加部门生成的数字位,此时为'814'
							var currentNewID = parseInt(previousNewID) + 1;
							//完整部门编号前需要拼接'D'字符
							var currentNewDeptID = 'D' + currentNewID;
							//根据当前部门等级动态拼接末尾字符'0',其个数由(4 - 部门等级)决定
							for(var i = 0 ; i < 4 - currentDeptLevel ; i++){
								currentNewDeptID += '0';
							}
							//根据当前添加的部门等级动态拼接'bu_Head'字段
							if(currentDeptLevel == 1){
								$('#bu_Head').val('是');
							}else{
								$('#bu_Head').val('否');
							}
							$('#deptID').val(currentNewDeptID);		//向隐藏域的部门编号设值
							$('#deptLevel').val(currentDeptLevel);	//向隐藏域的部门等级设值
						//当前带添加部门没有同级部门(即带添加部门是其上级的第一个子部门)
						}else {
							var currentNewDeptID = '';
							var currentDeptLevel = 0;
							if(deptID.indexOf('000') != -1) {
								for(var i = 0 ; i < deptID.length ; i++){
									if(i == 2)
										currentNewDeptID += '1';
									else
										currentNewDeptID += deptID.charAt(i);
								}
								currentDeptLevel = 2;
							}else if(deptID.indexOf('00') != -1) {
								for(var j = 0 ; j < deptID.length ; j++) {
									if(j == 3)
										currentNewDeptID += '1';
									else
										currentNewDeptID += deptID.charAt(j);
								}
								currentDeptLevel = 3;
							}
							$('#bu_Head').val('否');
							$('#deptID').val(currentNewDeptID);		//向隐藏域的部门编号设值
							$('#deptLevel').val(currentDeptLevel);	//向隐藏域的部门等级设值
						}
					}
				});
			}
			
			//异步提交添加部门表单，添加成功显示添加职位表单
			$('#submitButton').click(function(){
				var department_CN = $('#department_CN').val();
				var department_EN = $('#department_EN').val();
				var upOneLevel = $('#upOneLevel').val();
				if(department_CN == '' || department_EN == '' || upOneLevel == '') {
					window.wxc.xcConfirm('输入信息有误，请修正' , window.wxc.xcConfirm.typeEnum.info);
				}else {
					//记录当前新部门的部门编号
					currentNewDepartmentDeptID = $('#deptID').val();
					//异步提交表单
					$.ajax({
						url : '${path}/addDepartment',
						type : 'POST',
						data : $('#newDepartmentForm').serialize(),
						success : function(result){
							//添加部门成功，关闭当前页面
							if(result == '添加成功') {
								$('#submitButton').hide();  //隐藏添加部门提交按钮
								$('#newDepartmentPositionForm').show();  //二级表单展开
								//self.parent.closeCurrentPage();
							}
						}
					});
				}
			});
			
			function sharedPositionSelected(){
				if($('#sharedPosition').val() != "请选择") {
					$('#position_CN').val("");
					$('#position_EN').val("");
				}
			}
			
			//异步提交管理者职位表单
			$('#submitButton2').click(function(){
				var position_CN = $('#position_CN').val();
				var position_EN = $('#position_EN').val();
				var sharedPosition = $('#sharedPosition').val();
				//该leader职位选择了已有的多人职位(如开发组长、开发经理等)
				if(position_CN == '' && position_EN == '' && sharedPosition != '请选择'){
					$('#position_CN').removeAttr("name");  //移除name属性，不将其提交到后台
					$('#position_EN').removeAttr("name");  //移除name属性，不将其提交到后台
					$('#newPositionStructureDistribution').removeAttr("name");
					$('#newPositionIsHeadStatus').removeAttr("name"); 
					$('#sharedDeptID').val(currentNewDepartmentDeptID);
					$.ajax({
						url : '${path}/addSharedPositionByAddDepartment',
						type : 'POST',
						data : $('#newDepartmentPositionForm').serialize(),
						success : function(result){
							if(result == '添加成功') {
								//添加成功关闭当前页面
								self.parent.closeCurrentPage();
							}
						}
					});
				//该leader职位添加了一个新的职位
				}else if(position_CN != '' && position_EN != '' && sharedPosition == '请选择'){
					var structureDistribution = '';
					$('#sharedPosition').removeAttr("name");  //移除name
					$('#sharedDeptID').val(currentNewDepartmentDeptID);  //将新部门的编号传递到后台，更新关联表
					//$('#sharedDeptID').removeAttr("name");  //移除name
					var sharedButtonStatus = $('input:checkbox[name="sharedPosition"]:checked').val();
					//如果选中了职位共享单选框,则新添加的职位为共享职位,structureDistribution字段值为0(共享职位该字段值均为0)
					if(sharedButtonStatus == 'yes') {
						$('#newPositionStructureDistribution').val(0);
						$('#newPositionIsHeadStatus').val(1);
					}else if(currentNewDepartmentDeptID.indexOf('000') != -1){
						structureDistribution += currentNewDepartmentDeptID.substring(1);
						structureDistribution += '4';
						$('#newPositionStructureDistribution').val(structureDistribution);
						$('#newPositionIsHeadStatus').val(1);
					}else if(currentNewDepartmentDeptID.indexOf('00') != -1){
						structureDistribution += currentNewDepartmentDeptID.substring(1);
						structureDistribution += '3';
						$('#newPositionStructureDistribution').val(structureDistribution);
						$('#newPositionIsHeadStatus').val(1);
					}else if(currentNewDepartmentDeptID.indexOf('0') != -1){
						structureDistribution += currentNewDepartmentDeptID.substring(1);
						structureDistribution += '2';
						$('#newPositionStructureDistribution').val(structureDistribution);
						$('#newPositionIsHeadStatus').val(1);
					}
					
					$.ajax({
						url : '${path}/addNewPositionByAddDepartment',
						type : 'POST',
						data : $('#newDepartmentPositionForm').serialize(),
						success : function(result){
							if(result == "添加成功") {
								//添加成功关闭当前页面
								self.parent.closeCurrentPage();
							}
						}
					});
				//两种类型的职位全部填写了
				}else if(position_CN != '' && position_CN != '' && sharedPosition !='请选择') {
					window.wxc.xcConfirm('多人职位和新职位不能同时存在，请修正' , window.wxc.xcConfirm.typeEnum.info);
				}else {
					window.wxc.xcConfirm('输入信息有误，请修正' , window.wxc.xcConfirm.typeEnum.info);
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