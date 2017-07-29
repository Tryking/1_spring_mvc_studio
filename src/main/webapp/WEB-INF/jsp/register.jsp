<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>咪咕论坛注册</title>
</head>
<body>
<form action="<c:url value="registerCheck.html"/>" method="post">
    <br><font color="#4b0082" size="12">欢迎注册咪咕论坛</font><br><br>
    &#12288;用户名：
    <input type="text" placeholder="请输入用户名" name="userName">
    <br>
    <br>
    创建密码：
    <input type="password" placeholder="请输入密码" name="password">
    <br>
    <br>
    确认密码：
    <input type="password" placeholder="请再次输入密码" name="rePassword">
    <br><br>
    <c:if test="${!empty error}">
        <font color="red" size="5"><c:out value="${error}"/></font>
    </c:if>
    <br><br>
    &#12288;&#12288;&#12288;&#12288;
    <input type="submit" value="注册"/>
</form>
<br>
</body>
</html>
