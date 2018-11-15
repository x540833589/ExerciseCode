$(document).ready(function() {
//	//该方法用于添加员工或更新员工时，员工的待(更新/添加)职位上已经有人的时候，弹出提醒窗口，提示其应先修改已在职的员工的信息
//	function positionExisted(text , url) {
//		console.log("新开的标签页的标题为:" + text);
//		console.log("新开的标签页的url为:" + url);
//		 $.confirm({
//	            theme: 'black', 
//	            icon: 'fa fa-warning',
//	            title: '提示',
//	            content: '该职位已有在职员工，请先修改其职位后重试。',
//	           	buttons: {
//		            confirm: {
//		            	text: '前往修改',
//		            	action: function () {
//			            	console.log("点击了修改按钮");
//			               	self.parent.Open(text , url);
//		            	}
//			 		},
//		            cancel: {
//		            	text: '关闭',
//	            		action: function () {
//							console.log("点击了关闭按钮");
//		            	}
//		            }
//	           	}
//	     });
//		 console.log("finish");
//	}
	//邮箱结尾正则
	var endtemp = /^\w+\s*\_*\-*@(sottop\.cn|Sottop\.cn)$/;
	//表单信息校验标志位
	var checkStatus = true;
	
	$('#name_CN').keyup(function(){
		//中文名
		var cnName = $('#name_CN').val();
		if (cnName.length == 0) {
			$('#NameCnStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/buok.png" class="tserror"><font style="color: red;">&nbsp;&nbsp;请输入员工姓名</font>');
		} else {
			if(cnName.length < 2 || cnName.length > 5) {
				$('#NameCnStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/buok.png" class="tserror"><font style="color: red;">&nbsp;&nbsp;姓名格式有误</font>');
			}else {
				$('#NameCnStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/ok8.png" class="ts">&nbsp;&nbsp;');
			}
		}
	});
	
	$('#name_EN').keyup(function(){
		//英文名
		var enName = $('#name_EN').val();
		if (enName.length == 0) {
			$('#NameEnStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/buok.png" class="tserror"><font style="color: red;">&nbsp;&nbsp;请输入英文名</font>');
		} else {
			$('#NameEnStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/ok8.png" class="ts">');
		}
	});
	
	$('#email').keyup(function(){
		//邮箱
		var email = $('#email').val();
		if (email.length == 0) {
			$('#EmailStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/buok.png" class="tserror"><font style="color: red;">&nbsp;&nbsp;请输入邮箱</font>');
		} else if (!endtemp.test(email)) {
			$('#EmailStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/buok.png" class="tserror"><font style="color: red;">&nbsp;&nbsp;邮箱无效</font>');
		} else {
			$('#EmailStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/ok8.png" class="ts">');
		}
	});
	
	$('#department').change(function(){
		console.log("已选择部门");
		//部门
		var department = $('#department').val();
		console.log("当前部门" + department);
		if (department == "请选择") {
			$('#deptIdStatus').html('&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/buok.png" class="tserror"><font style="color: red;">&nbsp;&nbsp;请选择部门号</font>');
			$('#positionStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/buok.png" class="tserror"><font style="color: red;">&nbsp;&nbsp;请选择职位</font>');
		} else {
			$('#deptIdStatus').html('&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/ok8.png" class="ts">');
		}
	});
	
	$('#position').change(function(){
		//职位
		var position = $('#position').val();
		if (position == "请选择" || position == "") {
			$('#positionStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/buok.png" class="tserror"><font style="color: red;">&nbsp;&nbsp;请选择职位</font>');
		} else {
			$('#positionStatus').html('&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/ok8.png" class="ts">');
		}
	});
	
	$('#file').change(function(){
		//图片名字
		var file = $('#file').val();
		if (file.length == 0) {
			$('#photoStatus').html('&nbsp;<img src="images/buok.png" class="tserror"><font style="color: red;">&nbsp;&nbsp;请插入照片</font>');
		} else {
			$('#portrait').css("width" , "125px");
			$('#photoStatus').html('<img src="images/ok8.png" class="ts">');
		}
	});
	
//	$('#userData').submit(function(){
//		var newForm = new FormData();
//		newForm.append("no" , $('#no').val());
//		newForm.append("name_CN" , $('#name_CN').val());
//		newForm.append("name_EN" , $('#name_EN').val());
//		newForm.append("email" , $('#email').val());
//		newForm.append("deptID" , $('#department').val());
//		newForm.append("positionID" , $('#position').val());
//		newForm.append("isActive" , $('#isActive').val());
//		newForm.append("isHead" , $('#isHead').val());
//		newForm.append("structureDistribution" , $('#structureDistribution').val());
//		newForm.append("file" , $("#file")[0].files[0]);
//		
//		console.log("进入异步提交");
//		
//		var name_CN = $('#name_CN').val();
//		
//		$.ajax({
//			url : '/checkPositionActiveStatus',
//			type : 'POST',
//			data : {
//				'deptID' : $('#department').val(),
//				'positionID' : $('#position').val()
//			},
//			success : function(result) {
//				//console.log("即将提交的职位的查询结果:" + result.name_CN);
//				if(result != null && result != ""){
//					console.log("result不为空");
//					/*console.log(typeof(result));
//					console.log(typeof($('#isActive').val()) +''+ $('#isActive').val()); */
//					if(result.position_CN.indexOf('总监') != -1
//					|| result.position_CN.indexOf('经理') != -1
//					|| result.position_CN.indexOf('主管') != -1
//					|| result.position_CN.indexOf('组长') != -1) {
//						console.log(result.no);
//						positionExisted('编辑(' + result.name_CN + ')' , '/editStaffInfo?no=' + result.no);
//						return false;
//					}else {
//						//console.log("进入异步提交");
//						$.ajax({
//							 url : "/newStaff",
//							 xhrFields : {
//							   withCredentials:true
//							 },
//							 type : "POST",
//							 cache : false,
//							 data : newForm,
//							 processData : false,
//							 contentType : false,
//							 async : false,
//							 success : function (result) {
//								 if(result == "插入成功"){
//										console.log("异步提交成功");
//										self.parent.Open('(' + name_CN + ')信息浏览','/viewCurrentNewStaff');
//									}
//									else
//										console.log("插入失败");
//							 }
//						});
//					}
//				}else {
//					$.ajax({
//						 url : "/newStaff",
//						 xhrFields : {
//						   withCredentials:true
//						 },
//						 type : "POST",
//						 cache : false,
//						 data : newForm,
//						 processData : false,
//						 contentType : false,
//						 async : false,
//						 success : function (result) {
//							 if(result == "插入成功"){
//									console.log("异步提交成功");
//									self.parent.Open('(' + name_CN + ')信息浏览','/viewCurrentNewStaff');
//								}
//								else
//									console.log("插入失败");
//						 }
//					});
//				}
//			}
//		});
//	});
	
	/**
	 * 隐藏域字段"isHead"设值,员工structureDistribution字段设值
	 */
	$('#position').change(function(){
		var currentDepartment = $('#department').find("option:selected").text();
		var stringPart = currentDepartment.substring(1,5);  //多人职位截取四位数字位来决定该员工属于哪一个具体部门
		var positionID = $('#position').val();
		var ID = parseInt(positionID);  //9
		var isHead = []; //	所有leader职位的编号集合
		$.ajax({
			url : realPath + '/getAllExistedPosition',
			type : 'POST',
			success : function(result){
				console.log("获得所有leader职位成功");
				//过滤出所有leader职位的编号
				for(var i = 0 ; i < result.length ; i++){
					if(result[i].isHeadStatus == 1)
						isHead.push(result[i].no);
				}
				//拼接isHead字段
				if(isHead.indexOf(ID) > -1)
					$('#isHead').val("1");
				else
					$('#isHead').val("0");
				var demo = "";
				//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
				//遍历职位表动态拼接structureDistribution字段 ，(需满足一定的命名规则)
				//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
				for(var j = 0 ; j < result.length ; j++){
					if(result[j].no == ID){  //9
						//单一部门特有职位
						if(result[j].structureDistribution != 0) {
							demo = result[j].structureDistribution;
						//多个部门共有职位
						}else{
							console.log('当前职位中文名:' + result[j].position_CN);
							//开发工程师,实习生(一级)
							if(result[j].no == 7 || result[j].position_CN.indexOf('师') > -1 || result[j].position_CN.indexOf('实习生') > -1){
								stringPart += '1';
								demo = parseInt(stringPart);
							//开发组长(二级)
							}else if(result[j].no == 8 || result[j].position_CN.indexOf('组长') > -1){
								stringPart += '2';
								demo = parseInt(stringPart);
							//开发经理、人事专员(三级)
							}else if(result[j].no == 12 || result[j].position_CN.indexOf('经理') > -1 || result[j].position_CN.indexOf('人事专员') > -1){
								stringPart += '3';
								demo = parseInt(stringPart);
							}
						}
					}
				}
				console.log(demo);
				$('#structureDistribution').val(demo);
			}
		});
	});
	
	$('form').submit(function(){
		var okAmount = $('.ts').length;
		if(okAmount != 6) {
			alert('输入信息有误，请修正');
			return false;
		}else {
			return true;
		}
	});
	
});


//window.alert = function(str)
//{
//	 var shield = document.createElement("DIV");
//	 shield.id = "shield";
//	 shield.style.position = "absolute";
//	 shield.style.left = "0px";
//	 shield.style.top = "0px";
//	 shield.style.width = "100%";
//	 shield.style.height = document.body.scrollHeight+"px";
//	 //弹出对话框时的背景颜色
//	 shield.style.background = "#fff";
//	 shield.style.textAlign = "center";
//	 shield.style.zIndex = "25";
//	 //背景透明 IE有效
//	 //shield.style.filter = "alpha(opacity=0)";
//	 var alertFram = document.createElement("DIV");
//	 alertFram.id="alertFram";
//	 alertFram.style.position = "absolute";
//	 alertFram.style.left = "50%";
//	 alertFram.style.top = "50%";
//	 alertFram.style.marginLeft = "-225px";
//	 alertFram.style.marginTop = "-75px";
//	 alertFram.style.width = "450px";
//	 alertFram.style.height = "180px";
//	 alertFram.style.background = "#E0ECFF";
//	 alertFram.style.textAlign = "center";
//	 alertFram.style.lineHeight = "150px";
//	 alertFram.style.zIndex = "300";
//	 strHtml = "<ul style=\"list-style:none;margin:0px;padding:0px;width:100%\">\n";
//	 strHtml += " <li style=\"background:#E0ECFF;text-align:left;padding-left:20px;font-size:14px;font-weight:bold;height:25px;line-height:25px;border:1px solid #E0ECFF;\">[信息提示]</li>\n";
//	 strHtml += " <li style=\"background:#fff;text-align:center;font-size:12px;height:120px;line-height:120px;border-left:1px solid #F9CADE;border-right:1px solid #F9CADE;\">"+str+"</li>\n";
//	 strHtml += " <li style=\"background:#E0ECFF;text-align:center;font-weight:bold;height:25px;line-height:25px; border:1px solid #E0ECFF;\"><input type=\"button\" value=\"确 定\" onclick=\"doOk()\" /></li>\n";
//	 strHtml += "</ul>\n";
//	 alertFram.innerHTML = strHtml;
//	 document.body.appendChild(alertFram);
//	 document.body.appendChild(shield);
//	 var ad = setInterval("doAlpha()",5);
//	 this.doOk = function(){
//	     alertFram.style.display = "none";
//	     shield.style.display = "none";
//	 }
//	 alertFram.focus();
//	 document.body.onselectstart = function(){return false;};
//}