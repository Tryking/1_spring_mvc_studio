<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>咪咕论坛登录</title>
</head>
<body>
<form action="<c:url value="loginCheck.html"/>" method="post">
    <br><font color="#4b0082" size="12">欢迎使用咪咕论坛</font><br><br>
    用户名：
    <input type="text" name="userName">
    <br>
    <br>
    密&#12288;码：
    <input type="password" name="password">
    <br>
    <br>
    <input type="submit" value="登录"/>
    <input type="reset" value="重置"/>
</form>
<br>
<c:if test="${!empty error}">
    <font color="red" size="5"><c:out value="${error}"/></font>
</c:if>
</body>
</html>
