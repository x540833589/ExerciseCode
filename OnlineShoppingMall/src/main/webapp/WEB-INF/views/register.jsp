<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户注册</title>
        <link rel="stylesheet" type="text/css" href="css/register.css">
    </head>
    <body>
    	<span style="margin-left: 10px; font-weight: bold;"><a href="index?currentPageNumber=1"><font color="red">首页</font></a></span>
        <h2>注册一个账号</h2>
        <h3><c:if test="${not empty sessionScope.UsernameOrPasswordFormatError}">${sessionScope.UsernameOrPasswordFormatError}</c:if></h3>
        <h3><c:if test="${not empty sessionScope.UsernameOrPasswordError}">${sessionScope.UsernameOrPasswordError}</c:if></h3>
        <br/>
        <form action="doRegister" method="GET" onsubmit="return Validate()">
            <label>用户名:</label><input type="text" name="username" value="${sessionScope.newRegisterForm.username}"/><span style="font-size: small;"> * 3-10位字母、数字、字符</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.username}<c:if test="${not empty sessionScope.usernameExistError}">${sessionScope.usernameExistError}</c:if></span><br/><br/>
            <label>密码:</label><input type="password" id="password_1" name="password" value="${sessionScope.newRegisterForm.password}" onkeyup="validatePasswordLength(this.value)"/><span style="font-size: small;"> * 3-10位字母、数字、字符</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.password}</span><br/>
            <span id="passwordStrength" style="color: red; margin-left: 45.4%; font-size: x-small;"><font style="color: transparent;"></font></span><br/>
            <label>确认密码:</label><input type="password" id="password_2" value="${sessionScope.newRegisterForm.password}"/><span style="font-size: small;"> * 3-10位字母、数字、字符</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.password}</span><br/><br/>
            <label>昵称:</label><input type="text" name="nickname" value="${sessionScope.newRegisterForm.nickname}"/><span style="font-size: small;"> * 3-10位字母、数字、字符</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.nickname}</span><br/><br/>
            <label>性别:</label><input type="radio" name="sex" value="男" checked="checked"/>男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女"/>女&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.sex}</span><br/><br/>
            <label>生日:</label><input type="date" style="width: 156px;" name="dateOfBirthStringValue" value="${sessionScope.newRegisterForm.dateOfBirthStringValue}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.dateOfBirthStringValue}</span><br/><br/>
            <label>住址:</label><input type="text" name="address" value="${sessionScope.newRegisterForm.address}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.address}</span><br/><br/>
            <label>邮箱:</label><input type="text" name="email" value="${sessionScope.newRegisterForm.email}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.email}</span><br/><br/>
            <label>联系电话:</label><input type="text" name="phoneNumber" value="${sessionScope.newRegisterForm.phoneNumber}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.phoneNumber}</span><br/><br/>
            <label>真实姓名:</label><input type="text" name="trueName" value="${sessionScope.newRegisterForm.trueName}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.trueName}</span><br/><br/>
            <label>身份证号码:</label><input type="text" name="IDNumber" value="${sessionScope.newRegisterForm.IDNumber}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newRegisterForm.errorMessages.IDNumber}</span><br/><br/>
            <button type="submit" style="margin-left: 51.6em">注册</button>
            <input style="margin-left: 4.7%" type="reset" value="重置"/>
        </form>
        <%
        	session.setAttribute("newRegisterForm" , null);
	        session.setAttribute("UsernameOrPasswordFormatError" , null);
	        session.setAttribute("UsernameOrPasswordError" , null);
	        session.setAttribute("usernameExistError" , null);
        %>
        <script type="text/javascript" src="js/register.js"></script>
    </body>
</html>