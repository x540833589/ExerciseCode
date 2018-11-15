<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>编辑员工信息</title>
		<link rel="stylesheet" href="${path}/css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" type="text/css"/>
		<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-confirm/3.2.3/jquery-confirm.min.css" type="text/css"/>
		<link rel="stylesheet" href="${path}/css/addmember.css" type="text/css"/>
		<link rel="stylesheet" href="${path}/css/updateStaff.css" type="text/css"/>
	</head>
	<body>
		<h3 align="center">编辑员工信息</h3><br/>
		<div class="out">
			<div class="mydiv">
				<form id="updateForm" enctype="multipart/form-data">
				    <div class="block">
				         <label>员工编号:</label>
				         <input type="text" id="no" name="no" class="inputtt input-large" readonly="readonly" value="${staffDetails.no}">
				    </div>
					<br/>
				    <div class="block">
						<label for="name_CN">请输入中文名:</label>
						<input type="text" class="inputtt input-large" name="name_CN" id ="name_CN" value="${staffDetails.name_CN}"/>
						<span id="NameCnStatus"></span>
					</div>
					<br/>
					<div class="block">
						<label for="name_EN">请输入英文名:</label>
						<input type="text" name="name_EN" id="name_EN" class="inputtt input-large" value="${staffDetails.name_EN}"/>
						<span id="NameEnStatus"></span>
					</div>
					<br/>
					<div class="block">
						<label>请输入邮箱:</label>
						<input type="email" name="email" id="email" class="inputtt" value="${staffDetails.email}"/>
						<span id="EmailStatus"></span>
					</div>
					 <br/>
				    <div class="block">
						<label>请选择部门:</label>
						<select id="department" name="deptID" class="inputtt" style="width: 156px; height: 22.4px;" onchange="getAllPosition()">
						          <option value="${staffDetails.deptID}" selected="selected">${staffDetails.deptID} ${staffDetails.department_EN} ${staffDetails.department_CN}</option>
						</select> 
						<span id="deptIdStatus"></span>
					</div>
					<br/>
					<div class="block">
						<label>请选择职位:</label>
						<select id="position" class="inputtt" style="width: 156px; height: 22.4px;" name="positionID">
					        <option value="${staffDetails.positionID}" selected="selected">${staffDetails.position_EN} ${staffDetails.position_CN}</option>
						</select>
						<span id="positionStatus"></span><span id="isHeadStatus" style="color: transparent"></span>
					</div>
				    <br/>
				    <div class="block">
						<label>是否在职:</label>
						<select class="inputtt" style="width: 156px; height: 22.4px;" id="isActive" name="isActive">
							<c:if test="${staffDetails.isActive eq 1}">
								<option value="1" selected="selected">是</option>
								<option value="0">否</option>
							</c:if>
							<c:if test="${staffDetails.isActive eq 0}">
								<option value="1">是</option>
								<option value="0" selected="selected">否</option>
							</c:if>
		                </select>
				    </div>
					<br/>
				    <div class="block">
					 	<label>请选择要上传员工照片:</label>
					 	<input type="file" name="file" id="file" class="inputtt" style="width: 156px; height: 22.4px; display: inline; border: none; text-indent: 1px;" onchange="showPreview(this)">
					 	<span id="photoStatus" style="display: block; width: 116.66px; float: right;"></span>
					 	<br/><br/>
					 	<label id="yulan">照片:</label><img id="portrait" src="${staffDetails.photo}" style="height: 150px; width: 125px; margin-left: 155px;"/>
					 	<br/>
		            </div>
		            <br/>
		            <input type="hidden" name="isHead" id="isHead" value=""/>
		            <input type="hidden" name="structureDistribution" id="structureDistribution" value=""/>
		            <div class="block">
		                 <input type="button" class="btn btn-primary" onclick="submitAndClose('(${staffDetails.name_CN})信息编辑','(${staffDetails.name_CN})信息浏览')" style="margin-left: 190px;" value="保存">
		            </div>
				</form>
		   </div>
		</div>	
		<script type="text/javascript" src="${path}/js/jquery-3.3.1.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery-confirm/3.2.3/jquery-confirm.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.js"></script>
		<script type="text/javascript" src="${path}/js/updateStaff.js"></script>
		<script src="js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			var realPath = "${path}";
			//该方法用于添加员工或更新员工时，员工的待(更新/添加)职位上已经有人的时候，弹出提醒窗口，提示其应先修改已在职的员工的信息
			function positionExisted(text , url) {
				console.log("新开的标签页的标题为:" + text);
				console.log("新开的标签页的url为:" + url);
				 $.confirm({
			            theme: 'black', 
			            icon: 'fa fa-warning',
			            title: '提示',
			            content: '该职位已有在职员工，请先修改其职位后重试。',
			           	buttons: {
				            confirm: {
				            	text: '前往修改',
				            	action: function () {
					            	console.log("点击了修改按钮");
					               	self.parent.Open(text , url);
				            	}
					 		},
				            cancel: {
				            	text: '关闭',
			            		action: function () {
									console.log("点击了关闭按钮");
				            	}
				            }
			           	}
			     });
			}
		
			//获得部门信息的ajax
			$(function() {
				$.post("${path}/getAllDepartment", null, function(data) {
					$.each(data, function(i, department) {
						var ID = department.deptID;
						var demo = '<option value="' + ID + '">' + ID + ' ' + department.department_EN + ' ' + department.department_CN + '</option>';
						$("#department").append(demo);
					});
				}, "json")
			});
			
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
			
			//该方法用于提交修改页面数据,并在提交完毕后关闭相应页面(查看页面和编辑页面)
			var submitAndClose = function(title1 , title2) {
				
				var okAmount = $('.tserror').length;
				if(okAmount != 0) {
					window.wxc.xcConfirm('输入信息有误，请修正' , window.wxc.xcConfirm.typeEnum.info);
					//alert('输入信息有误，请修正');
				}else if($('#department').val() == '请选择' || $('#position').val() == '请选择') {
					window.wxc.xcConfirm('部门或职位信息填写有误，请修正' , window.wxc.xcConfirm.typeEnum.info);
					//alert('部门或职位信息填写有误，请修正');
				}else {
					var newForm = new FormData();
					newForm.append("no" , $('#no').val());
					newForm.append("name_CN" , $('#name_CN').val());
					newForm.append("name_EN" , $('#name_EN').val());
					newForm.append("email" , $('#email').val());
					newForm.append("deptID" , $('#department').val());
					newForm.append("positionID" , $('#position').val());
					newForm.append("isActive" , $('#isActive').val());
					newForm.append("isHead" , $('#isHead').val());
					//如果没有出现职位变更，即structureDistribution字段值为空字符串，其无法转换为数字，故需先判断后赋值
					if($('#structureDistribution').val() != "")
						newForm.append("structureDistribution" , $('#structureDistribution').val());
					else
						newForm.append("structureDistribution" , 0);
					newForm.append("file" , $("#file")[0].files[0]);
					
					$.ajax({
						url : '${path}/checkPositionActiveStatus',
						type : 'POST',
						data : {
							'deptID' : $('#department').val(),
							'positionID' : $('#position').val()
						},
						success : function(result) {
							//console.log("即将提交的职位的查询结果:" + result.name_CN);
							if(result != null && result != ""){
								/* console.log("result不为空");
								console.log(typeof(result));
								console.log(typeof($('#isActive').val()) +''+ $('#isActive').val()); */
								console.log("已存在的员工编号为:" + result.no);
								console.log("当前正在修改的员工编号为:" + $('#no').val());
								//查看员工进入编辑页面后，什么也不修改直接保存，为了防止刚添加的员工与当前数据库中已有的记录冲突，增加判断条件为已存在记录和当前浏览的员工是否为同一人
								//若为同一人，则说明只是单纯的浏览其信息
								//若不为同一人，则说明修改后的职位与现有职位冲突
								if($('#isActive').val() == '1' && result.no != parseInt($('#no').val())){
									if(result.position_CN.indexOf('总监') != -1
									|| result.position_CN.indexOf('经理') != -1
									|| result.position_CN.indexOf('主管') != -1
									|| result.position_CN.indexOf('组长') != -1) {
										//console.log(result.no);
										positionExisted('(' + result.name_CN + ')信息编辑' , '${path}/editStaffInfo?no=' + result.no);
									}else {
										console.log("该职位允许多人在职");
										var name_CN = $('#name_CN').val();
									    $.ajax({
											 url : "${path}/updateStaffInfo",
											 xhrFields : {
											   withCredentials:true
											 },
											 type : "POST",
											 cache : false,
											 data : newForm,
											 processData : false,
											 contentType : false,
											 async : false,
											 success : function (result) {
												 if(result == "修改成功"){
													//调用关闭标签页的方法
													self.parent.closeTab(title1 , title2);
												}
												else
													console.log("修改失败");
											 }
										});
									}
								}else {
									var name_CN = $('#name_CN').val();
								    $.ajax({
										 url : "${path}/updateStaffInfo",
										 xhrFields : {
										   withCredentials:true
										 },
										 type : "POST",
										 cache : false,
										 data : newForm,
										 processData : false,
										 contentType : false,
										 async : false,
										 success : function (result) {
											 if(result == "修改成功"){
												//console.log("异步修改成功");
												//调用关闭标签页的方法
												self.parent.closeTab(title1 , title2);
											}
											else
												console.log("修改失败");
										 }
									});
								}
							}else {
								console.log("进入异步提交");
								var name_CN = $('#name_CN').val();
							    $.ajax({
									 url : "${path}/updateStaffInfo",
									 xhrFields : {
									   withCredentials:true
									 },
									 type : "POST",
									 cache : false,
									 data : newForm,
									 processData : false,
									 contentType : false,
									 async : false,
									 success : function (result) {
										 if(result == "修改成功"){
											console.log("异步修改成功");
											//调用关闭标签页的方法
											self.parent.closeTab(title1 , title2);
										}
										else
											console.log("修改失败");
									 }
								});
							}
						}
					});
				}
			}
		</script>
	</body>
</html>