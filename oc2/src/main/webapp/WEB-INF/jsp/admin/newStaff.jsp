<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>添加新员工</title>
		<link rel="stylesheet" href="${path}/css/bootstrap.min.css" type="text/css"/>
		<link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" type="text/css"/>
		<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-confirm/3.2.3/jquery-confirm.min.css" type="text/css"/>
		<link rel="stylesheet" href="${path}/css/addmember.css" type="text/css"/>
		<link rel="stylesheet" href="${path}/css/glyphicons.css" type="text/css"/>
	</head>
	<body>
		<h3 align="center">填写新员工信息</h3><br/>
		<div class="out">
			<div class="mydiv">
				<form id="userData" enctype="multipart/form-data">
				    <div class="block">
				         <label>员工编号:</label>
				         <input type="text" id="no" name="no" class="inputtt input-large" style="width: 156px; height: 22.4px;" readonly="readonly" value="${newStaffNo}">
				    </div>
					<br/>
				    <div class="block">
						<label for="name_CN">请输入中文名:</label>
						<input type="text" class="inputtt input-large" name="name_CN" id ="name_CN" style="width: 156px; height: 22.4px;"/>
						<span id="NameCnStatus" class="checkInputStatus"></span>
					</div>
					<br/>
					<div class="block">
						<label for="name_EN">请输入英文名:</label>
						<input type="text" name="name_EN" id="name_EN" class="inputtt input-large" style="width: 156px; height: 22.4px;"/>
						<span id="NameEnStatus" class="checkInputStatus"></span>
					</div>
					<br/>
					<div class="block">
						<label>请输入邮箱:</label>
						<input type="email" name="email" id="email" class="inputtt" style="width: 156px; height: 22.4px;"/>
						<span id="EmailStatus" class="checkInputStatus"></span>
					</div>
					 <br/>
				    <div class="block">
						<label>请选择部门:</label>
						<select id="department" name="deptID" class="inputtt" style="width: 156px; height: 22.4px;" onchange="getAllPosition()">
						          <option value="请选择">请选择</option>
						</select> 
						<span id="deptIdStatus" class="checkInputStatus"></span>
					</div>
					<br/>
					<div class="block">
						<label>请选择职位:</label>
						<select id="position" class="inputtt" style="width: 156px; height: 22.4px;" name="positionID">
					        <option value="请选择" selected="selected">请选择</option>
						</select>
						<span id="positionStatus" class="checkInputStatus"></span><span id="isHeadStatus" style="color: transparent"></span>
					</div>
				    <br/>
				    <div class="block">
						<label>是否在职:</label>
						<select class="inputtt" style="width: 156px; height: 22.4px;" id="isActive" name="isActive">
							<option value="1" selected="selected">是</option>
							<option value="0">否</option>
		                </select>
				    </div>
					<br/>
				    <div class="block">
					 	<label>请选择要上传员工照片:</label>
					 	<input type="file" name="file" id="file" class="inputtt" style="width: 156px; height: 22.4px; display: inline; border: none; text-indent: 1px;" onchange="showPreview(this)">
					 	<span id="photoStatus" style="display: block; width: 116.66px; float: right;"></span>
					 	<br/><br/>
					 	<label id="yulan">照片预览:</label><img id="portrait" src="${path}/images/yulan2.png" style="height: 150px; width: 125px; margin-left: 155px;"/>
					 	<br/>
		            </div>
		            <br/>
		            <input type="hidden" name="isHead" id="isHead" value=""/>
		            <input type="hidden" name="structureDistribution" id="structureDistribution" value=""/>
		            <div class="block">
		                 <button type="button" class="btn btn-primary" style="margin-left: 180px;" onclick="submitAndOpen()"><i class="glyphicon glyphicon-ok"></i>&nbsp;&nbsp;提交</button>&emsp;&emsp;<font color="red">${message}</font>
		            </div>
				</form>
		   </div>
		</div>	
		<script type="text/javascript" src="${path}/js/jquery-3.3.1.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery-confirm/3.2.3/jquery-confirm.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.js"></script>
		<script type="text/javascript" src="${path}/js/addmember.js"></script>
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
			
			var refreshLeftStructure = function() {
				//清空左侧菜单
				self.parent.clearLeft();
				//刷新左侧菜单
				self.parent.getLeftStructure();
			}
			
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
			
			function submitAndOpen() {
				var okAmount = $('.ts').length;
				if(okAmount != 6) {
					alert('输入信息有误，请修正');
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
					newForm.append("structureDistribution" , $('#structureDistribution').val());
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
								if($('#isActive').val() == '1'){
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
											 url : "${path}/newStaff",
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
												 if(result == "插入成功"){
														console.log("异步提交成功");
														self.parent.Open('(' + name_CN + ')信息浏览','${path}/viewCurrentNewStaff');
													}
													else
														console.log("插入失败");
											 }
										});
										//刷新左侧组织结构图
										refreshLeftStructure();
									}
								}else{
									alert("添加员工的在职状态填写有误，请修正。");
								}
							}else {
								console.log("该职位暂无员工在职");
								var name_CN = $('#name_CN').val();
								$.ajax({
									 url : "${path}/newStaff",
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
										 if(result == "插入成功"){
												console.log("异步提交成功");
												self.parent.Open('(' + name_CN + ')信息浏览','${path}/viewCurrentNewStaff');
											}
											else
												console.log("插入失败");
									 }
								});
								//刷新左侧组织结构图
								refreshLeftStructure();
							}
						}
					});
				}
			}
		</script>
	</body>
</html>