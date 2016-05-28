<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="/WEB-INF/myFunctions.tld" prefix="myFun"%>

<form id="edit_form" method="post" style="padding:10px">
	<table class="editTable">
		<tr>
			<th>用户名</th>
			<td>
				<input type="hidden" id="id" name="id" value="${user.id}" />
				<input type="hidden" id="name" name="name" value="${user.name}" />
				${user.name}
			</td>
		</tr>
		<tr>
			<th>角色列表</th>
			<td>
				<c:forEach items="${roleList}" var="role">
					<c:set var="hasRole" value="${myFun:hasRole(user.roles,role.id)}"/>
					<div style="margin-left:40px;">
						<input <c:if test="${hasRole eq 'true'}">checked="checked"</c:if> type="checkbox" id="${role.id}" name="${role.id}" value="${role.id}"/>
						<label for="${role.id}">${role.nameZh }</label>
					</div>
				</c:forEach>
			</td>
		</tr>
	</table>
</form>

<jsp:include page="../footer.jsp"/>