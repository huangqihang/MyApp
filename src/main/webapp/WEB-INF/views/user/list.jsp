<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<link rel="stylesheet" type="text/css" href="/pet/public/css/main.css"/>
</head>
<body>
	<!-- ${loginUser.name} 从哪里取出来的？？？ -->
	<h1>users-当前登录用户：${loginUser.name}</h1>
	<a href="add">add user</a><br/>
	<hr/>
	<c:forEach items="${users}" var="user">
		 ${user.value.name}---${user.value.age} &nbsp;&nbsp;
		 <a href="${user.key}">查看</a> 
		 <a href="${user.key}/update">更新</a> 
		 <a href="${user.key}/delete">删除</a> 
		<br/>
	</c:forEach>
</body>
</html>