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
        <h3><c:if test="${not empty sessionScope.adminUsernameOrPasswordError}"></c:if>${sessionScope.adminUsernameOrPasswordError}</h3>
        <h2>管理员登录</h2>
        <br/>
        <form action="adminLogin" method="get">
            <label>用户名:</label><input type="text" name="username"/><br/><br/>
            <label>密码:</label><input type="password" name="password"/><br/><br/>
            <input class="sub" type="submit" value="登录"/>
            <input style="margin-left: 4.7%" type="reset" value="重置"/>
        </form><br/>
        <%
        	session.setAttribute("adminUsernameOrPasswordError" , null);
        %>
    </body>
</html>