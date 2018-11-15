<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>提交成功</title>
        <link type="text/css" rel="stylesheet" href="css/orderSuccess.css">
        <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body>
    	<h2 class="success">下单成功!(<span id="sp">5</span>秒后返回首页)</h2><br/><br/>
    	<div class="userOperation">
    		<a class="index" href="index"><button class="btn btn-info">返回首页</button></a>
    		<a class="viewGoods" href="viewGoods"><button class="btn btn-info">继续购物</button></a>
    	</div>
    	<script type="text/javascript">
	   			onload = function() {
						setInterval(go, 1000);
					};
				var x = 5; //利用了全局变量来执行
				function go() {
					x--;
					if (x > 0) {
						document.getElementById("sp").innerHTML = x; //每次设置的x的值都不一样了。
					} else {
						location.href = 'http://localhost:8080/index?currentPageNumber=1';
					}
				}
		</script>
    </body>
</html>