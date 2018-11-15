<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户登陆</title>
        <link type="text/css" rel="stylesheet" href="css/login.css">
    </head>
    <body>
    	<span style="margin-left: 10px; font-weight: bold;"><a href="index?currentPageNumber=1"><font color="red">首页</font></a></span>
        <br/>
        <br/>
        <h3><c:if test="${not empty sessionScope.UsernameOrPasswordFormatError}"></c:if>${sessionScope.UsernameOrPasswordFormatError}</h3>
        <h3><c:if test="${not empty sessionScope.lockedStatusError}"></c:if>${sessionScope.lockedStatusError}</h3>
        <h3><c:if test="${not empty sessionScope.UsernameOrPasswordError}"></c:if>${sessionScope.UsernameOrPasswordError}</h3>
        <h3><c:if test="${not empty sessionScope.purchaseError}"></c:if>${sessionScope.purchaseError}</h3>
        <h3><c:if test="${not empty sessionScope.gotoCartError}"></c:if>${sessionScope.gotoCartError}</h3>
        <h3><c:if test="${not empty sessionScope.praiseError}"></c:if>${sessionScope.praiseError}</h3>
        <h3><c:if test="${not empty sessionScope.remarkError}"></c:if>${sessionScope.remarkError}</h3>
        <h2>使用账号登录</h2>
        <br/>
        <form action="doLogin" method="get">
            <label>用户名:</label><input type="text" name="username"/><br/><br/>
            <label>密码:</label><input type="password" name="password"/><br/><br/>
            <input class="sub" type="submit" value="登录"/>
            <input style="margin-left: 4.7%" type="reset" value="重置"/>
        </form><br/>
        <span class="gotoRes">还没有账号?去<a class="reg" href="/gotoRegisterPage">注册</a></span>
        <%
        	session.setAttribute("UsernameOrPasswordFormatError" , null);
	        session.setAttribute("lockedStatusError" , null);
	        session.setAttribute("UsernameOrPasswordError" , null);
	        session.setAttribute("purchaseError" , null);
	        session.setAttribute("gotoCartError" , null);
	        session.setAttribute("praiseError" , null);
	        session.setAttribute("remarkError" , null);
        %>
    </body>
</html>