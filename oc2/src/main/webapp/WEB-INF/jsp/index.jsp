<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset=utf-8 />
		<title>Organizational Structure</title>
		<script class="jsbin" src="${path}/open_tab/js/jquery-1.7.2.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${path}/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${path}/open_tab/css/easyui.css">
		<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="${path}/css/index.css">
		<script type="text/javascript" src="${path}/open_tab/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${path}/open_tab/js/main.js"></script>		
		<script type="text/javascript">
			
			var rootPath = "${path}";
		
			function getLeftStructure() {
				
				var closeStatus = "glyphicon glyphicon-chevron-right";
				
				var openStatus = "glyphicon glyphicon-chevron-down";
				
				var that = null;
				
				var deptID = null;
				
				$.ajax({
					url : '${path}/getFirstLevelDepartment',
					type : 'POST',
					success : function(result) {
						console.log(result);
						for(var i = 0 ; i < result.length ; i++){
							var childNum = result[i].childNum;
							if(i == 0) {
								var demo = '<span><i class="' + closeStatus + '" id="' + result[i].deptID + '"></i>&nbsp;&nbsp;<a href="#" style="padding-left: 1.5px;">' + result[i]["department_EN"] + '</a></span>';
								var demo2 = '<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="'+ result[i].deptID +'">' + result[i]["department_EN"] + '</a></span>';
							}else {
								var demo = '<br/><span><i class="' + closeStatus + '" id="' + result[i].deptID + '"></i>&nbsp;&nbsp;<a href="#" style="padding-left: 1.5px;">' + result[i]["department_EN"] + '</a></span>';
								var demo2 = '<br/><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="'+ result[i].deptID +'">' + result[i]["department_EN"] + '</a></span>';
							}
							console.log(childNum)
							
							if(childNum > 0){
							
								$('#structure').append(demo);
							}
							else{
								
								$('#structure').append(demo2);
							}
						}
						
						$("div>span>a").click(function() {
							var deptID = $(this).prev().attr("id");
							if(typeof(deptID) == "undefined"){
								deptID = $(this).attr("id");
							}
							var text = $(this).html();
							var title = text.replace(/&amp;/g , '&');
							var url = "${path}/showDetail?deptID=" + deptID;
							console.log(title);
							console.log(url);
							Open(title, url);
						});
						
						$("div>span>i").click(function() {
							console.log("第一级部门点击");
							that = $(this);
							deptID = that.attr("id");
							var currentStatus = that.attr("class");
								if(currentStatus == closeStatus){
									that.attr("class" , openStatus);
									$.ajax({
										url : '${path}/viewChild',
										type : 'POST',
										data : {
											'deptID' : deptID
										},
										success : function(result) {
											var deptLevel = result[0].deptLevel;
											for(var i = result.length - 1 ; i >= 0 ; i--){
												var childNum = result[i].childNum;
												var demo = '<br/><span><i class="' + closeStatus + '" id="' + result[i].deptID + '"></i>&nbsp;&nbsp;<a href="#">' + result[i]["department_EN"] + '</a></span>';
												var demo2 = '<br/><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="'+ result[i].deptID +'">' + result[i]["department_EN"] + '</a></span>';
												if(childNum > 0)
													that.next().after(demo);
												else
													that.next().after(demo2);
											}
											that.nextAll().filter("span").css('padding-left', 20 + 'px');
											clickIcon();
										}
									});
								}else {
									clearChildren($(this));
								}
							});
					}
				});
				
				var clickIcon = function(){
					
					$("span>span>a").click(function() {
						var deptID = $(this).prev().attr("id");
						if(typeof(deptID) == "undefined"){
							deptID = $(this).attr("id");
						}
						var text = $(this).html();
						var title = text.replace(/&amp;/g , '&');
						var url = "${path}/showDetail?deptID=" + deptID;
						console.log(title);
						console.log(url);
						Open(title, url);
					});
					
					$("div>span>span>i").unbind("click").click(function() {
						console.log("第er级部门点击");
						that = $(this);
						deptID = that.attr("id");
						var currentStatus2 = that.attr("class");
						if(currentStatus2 == closeStatus){
							console.log(deptID);
								that.attr("class" , openStatus);
								
							$.ajax({
								url : '${path}/viewChild',
								type : 'POST',
								data : {
									'deptID' : deptID
								},
							
								success : function(result) {
									var deptLevel = result[0].deptLevel;
									console.log(result.length);
									for(var i = result.length-1 ; i >= 0; i--){
										var childNum = result[i].childNum;
										console.log(childNum);
								
										var demo = '<br/><span><i class="' + closeStatus + '" id="' + result[i].deptID + '"></i>&nbsp;&nbsp;<a href="#">' + result[i]["department_EN"] + '</a></span>';
										var demo2 = '<br/><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" id="'+ result[i].deptID +'">' + result[i]["department_EN"] + '</a></span>';
										if(childNum > 0)
											that.next().after(demo);
										else
											that.next().after(demo2);
									}
									that.nextAll().filter("span").css('padding-left', 38 + 'px');
									
									$("span>span>a").click(function() {
										var deptID = $(this).prev().attr("id");
										if(typeof(deptID) == "undefined"){
											deptID = $(this).attr("id");
										}
										var text = $(this).html();
										var title = text.replace(/&amp;/g , '&');
										var url = "${path}/showDetail?deptID=" + deptID;
										console.log(title);
										console.log(url);
										Open(title, url);
									});
								
								}
							});
						}else {
							clearChildren($(this))
						}
					});
				}
				
				var clearChildren = function(nowClick){
					nowClick.attr("class" , closeStatus);
					$("#"+nowClick.attr("id")).nextAll().filter("span").remove();
					$("#"+nowClick.attr("id")).nextAll().filter("br").remove();
				}
				
			}
			//调用方法获取左侧组织结构图
			getLeftStructure();
			
			//在右边center区域打开菜单，新增tab
		    function Open(text, url) {
				
				console.log("新打开的页面的title为:" + text);
				console.log("新打开的页面的url为:" + url);
		    	
		        var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	
		        if ($("#tabs").tabs('exists', text)) {
		            $('#tabs').tabs('select', text);
		        } else {
		            $('#tabs').tabs('add', {
		                title : text,
		                closable : true,
		                content : content
		            });
		        }
		    }
	
		    //绑定tabs的右键菜单
		    $("#tabs").tabs({
		        onContextMenu : function (e, title) {
		            e.preventDefault();
		            $('#tabsMenu').menu('show', {
		                left : e.pageX,
		                top : e.pageY
		            }).data("tabTitle", title);
		        }
		    });
	
		    //实例化menu的onClick事件
		    $("#tabsMenu").menu({
		        onClick : function (item) {
		            CloseTab(this, item.name);
		        }
		    });
	
		    //几个关闭事件的实现
		    function CloseTab(menu, type) {
		        var curTabTitle = $(menu).data("tabTitle");
		        var tabs = $("#tabs");
	
		        if (type === "close") {
		            tabs.tabs("close", curTabTitle);
		            return;
		        }
	
		        var allTabs = tabs.tabs("tabs");
		        var closeTabsTitle = [];
	
		        $.each(allTabs, function () {
		            var opt = $(this).panel("options");
		            if (opt.closable && opt.title != curTabTitle && type === "Other") {
		                closeTabsTitle.push(opt.title);
		            } else if (opt.closable && type === "All") {
		                closeTabsTitle.push(opt.title);
		            }
		        });
	
		        for (var i = 0; i < closeTabsTitle.length; i++) {
		            tabs.tabs("close", closeTabsTitle[i]);
		        }
		    }
		    
		    var closeTab = function(title1 , title2) {  
		    	
		    	/* var updateForm = currentPage.getElementById('updateForm');
		    	
		    	console.log(typeof(updateForm));
		    	
		    	console.log("进入标签关闭方法"); */
		    	//先关闭除自己以外的另一个标签页
	            if ($('#tabs').tabs('exists', title2)) { 
	                $('#tabs').tabs('close', title2);  
	                console.log("关闭编辑页面");
	            }
		    	//然后关闭自己
	            if ($('#tabs').tabs('exists', title1)) {  
	                $('#tabs').tabs('close', title1);  
	                console.log("关闭浏览页面");
	            } 
	        } 
		    
			//关闭当前选中的标签页
		    var closeCurrentPage = function(){
		    	var current = $('#tabs').tabs('getSelected');
		    	var index = $('#tabs').tabs('getTabIndex',current);
		    	$('#tabs').tabs('close',index);
		    }
			
			//清空左侧组织结构图
			var clearLeft = function() {
				$('#structure').empty();
			}
		</script>
		<style>
			article, aside, figure, footer, header, hgroup, menu, nav, section {
				display: block;
			}
			
			.west {
				width: 200px;
				padding: 10px;
			}
			
			.north {
				height: 30px;
			}
			.panel-body .panel-body-noheader .panel-body-noborder {
				overflow: hidden !important;
			}
		</style>
	</head>
	<body class="easyui-layout">
		<div region="north" split="true" border="false" style="position: relative; overflow: hidden; height: 45px; background: #7f99be repeat-x center 50%; line-height: 20px; color: #fff; font-family: Verdana, 微软雅黑, 黑体">
			<span style="padding-left: 6px; font-size: 16px; position: absolute; margin-top: 7px; width: 1510px;">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<img src="images/sottop.png" style="margin-top: 0px; float: right;" width="100" height="20"/>
				<span><i class="fa fa-users"></i></span>&nbsp;
				Organizational Structure
			</span>
		</div>
		<div region="center" title="">
			<div class="easyui-tabs" fit="true" border="false" id="tabs">
				<div title="首页">
					<br/>
					<h1 style="text-align: center;">Welcome.</h1>
					<button class="btn btn-primary" style="float: right; margin-right: 25px; height: 35px; width: 130px;" onclick="Open('管理员页面','${path}/gotoAdminMainPage')"><i class="fa fa-user-circle fa-spin"></i>&nbsp;&nbsp;<font color="white">管理员戳这里</font></button>
				</div>
			</div>
		</div>
		<div region="west" class="west" id="westPanel" split="true" border="true" style="width: 240px" title="&nbsp;&nbsp;&nbsp;<i class='fa fa-sitemap'></i>&nbsp;&nbsp;部门一览">
			<div id="structure" style="padding-top: 0px;"></div>
		</div>
	
		<div id="tabsMenu" class="easyui-menu" style="width: 120px;">
			<!-- <div name="close">关闭</div> -->
			<div name="Other">关闭其他页面 </div>
			<div name="All">关闭所有页面</div>
		</div>
	</body>
</html>