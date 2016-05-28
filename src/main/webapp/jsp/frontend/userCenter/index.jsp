<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="/WEB-INF/myFunctions.tld" prefix="myFun"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
用户首页

<c:forEach items="${resList}" var="res">
	${myFun:convertLang(res.nameZh,res.nameEn,site_lang)}<br>
</c:forEach>

</body>
</html>