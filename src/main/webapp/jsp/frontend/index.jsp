<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="${frontend_uri}/index.html?locale=zh">中文</a>|<a href="${frontend_uri}/index.html?locale=en">英文</a> <br>
<fmt:message key="label.message.name" /> <br>
${site_lang }<br>

<a href="${frontend_uri}/login.html">登入</a> <br>
<a href="${frontend_uri}/register.html">注册</a>
</body>
</html>