<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page isELIgnored="false"%>
<c:set value="${pageContext.request.contextPath}" var="path"/>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Admin Login</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/xcConfirm.css"/>
		<link rel="stylesheet" href="${path}/css/login.css" type="text/css">
		<script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
	</head>
	<body>
		<div class="login">
			<h1>Please Login</h1><br/>
			<form action="${path}/login" method="POST" id="loginForm">
				<input type="text" name="name" id="name" placeholder="Username" required="required"/>
				<input type="password" name="password" id="password" placeholder="Password" required="required"/>
				<button type="submit" id="loginButton" class="btn btn-primary btn-block btn-large">login</button>
			</form>
		</div>
		<script type="text/javascript" src="${path}/js/jquery-3.3.1.min.js"></script>
		<script src="js/xcConfirm.js" type="text/javascript" charset="utf-8"></script>
		<c:if test="${sessionScope.firstGoInLoginPage eq null && sessionScope.usernameOrPasswordError ne null}">
			<script type="text/javascript">
				var txt= "用户名或密码错误。";
				window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
			</script>
		</c:if>
	</body>
</html>
