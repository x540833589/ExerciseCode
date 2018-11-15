<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>编辑资料</title>
        <link rel="stylesheet" type="text/css" href="css/register.css">
    </head>
    <body>
        <br/>
        <h2>修改个人资料</h2>
        <h3><c:if test="${not empty sessionScope.UsernameOrPasswordFormatError}">${sessionScope.UsernameOrPasswordFormatError}</c:if></h3>
        <h3><c:if test="${not empty sessionScope.UsernameOrPasswordError}">${sessionScope.UsernameOrPasswordError}</c:if></h3>
        <br/>
        <form action="editSave" method="GET" onsubmit="return Validate()">
            <label>用户名:</label><input type="text" name="username" value="${sessionScope.user.username}" readonly="readonly"><span style="font-size: small; color: red;"> * 用户名不可修改</span><br/><br/>
            <label>密码:</label><input type="text" id="password_1" name="password" value="${sessionScope.previousInfo.password}${sessionScope.newForm.password}" placeholder="${sessionScope.user.password}" onkeyup="validatePasswordLength(this.value)"/><span style="font-size: small;"> * 3-10位字母、数字、字符</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newForm.errorMessages.password}</span><br/>
            <span id="passwordStrength" style="color: red; margin-left: 45.4%; font-size: x-small;"><font style="color: transparent;"></font></span><br/>
            <label>确认密码:</label><input type="text" id="password_2" value="${sessionScope.previousInfo.password}${sessionScope.newForm.password}" placeholder="${sessionScope.user.password}"/><span style="font-size: small;"> * 3-10位字母、数字、字符</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newForm.errorMessages.password}</span><br/><br/>
            <label>昵称:</label><input type="text" name="nickname" value="${sessionScope.previousInfo.nickname}${sessionScope.newForm.nickname}" placeholder="${sessionScope.user.nickname}"/><span style="font-size: small;"> * 3-10位字母、数字、字符</span>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newForm.errorMessages.nickname}</span><br/><br/>
            <label>性别:</label><input type="radio" name="sex" value="男" checked="checked"/>男&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女"/>女&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newForm.errorMessages.sex}</span><br/><br/>
            <label>生日:</label><input type="date" style="width: 156px;" name="dateOfBirthStringValue" value="${sessionScope.previousInfo.dateOfBirth}${sessionScope.newForm.dateOfBirth}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newForm.errorMessages.dateOfBirthStringValue}</span><br/><br/>
            <label>住址:</label><input type="text" name="address" value="${sessionScope.previousInfo.address}${sessionScope.newForm.address}" placeholder="${sessionScope.user.address}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newForm.errorMessages.address}</span><br/><br/>
            <label>邮箱:</label><input type="text" name="email" value="${sessionScope.previousInfo.email}${sessionScope.newForm.email}" placeholder="${sessionScope.user.email}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newForm.errorMessages.email}</span><br/><br/>
            <label>联系电话:</label><input type="text" name="phoneNumber" value="${sessionScope.previousInfo.phoneNumber}${sessionScope.newForm.phoneNumber}" placeholder="${sessionScope.user.phoneNumber}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newForm.errorMessages.phoneNumber}</span><br/><br/>
            <label>真实姓名:</label><input type="text" name="trueName" value="${sessionScope.previousInfo.trueName}${sessionScope.newForm.trueName}" placeholder="${sessionScope.user.trueName}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newForm.errorMessages.trueName}</span><br/><br/>
            <label>身份证号码:</label><input type="text" name="IDNumber" value="${sessionScope.previousInfo.IDNumber}${sessionScope.newForm.IDNumber}" placeholder="${sessionScope.user.IDNumber}"/>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">${sessionScope.newForm.errorMessages.IDNumber}</span><br/><br/>
            <input type="hidden" name="userId" value="${sessionScope.user.userId}"/>
            <button type="submit" style="margin-left: 51.6em">保存</button>
            <button style="margin-left: 4.7%" onclick="return resett()">重置</button>
        </form>
        <script type="text/javascript" src="js/register.js"></script>
    </body>
</html>