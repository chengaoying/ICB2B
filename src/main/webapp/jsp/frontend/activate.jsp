<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose> 
	<c:when test="${status == 0 }">   
		<a href="${frontend_uri}/sendActiveEmail.html">重新发送验证邮件</a>
	</c:when>
	<c:when test="${status == -1 }">   
		${info}
	</c:when>  
	<c:when test="${status == 1 }">   
		${info}
	</c:when> 
</c:choose>

</body>
</html>