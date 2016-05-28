<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ taglib uri="/WEB-INF/myFunctions.tld" prefix="myFun"%>

<form id="edit_form" method="post" style="padding:10px">
	<table class="editTable">
		<tr>
			<th>角色中文名称</th>
			<td>
				<input type="hidden" id="id" name="id" value="${role.id}" />
				<input type="text" id="nameZh" name="nameZh" value="${role.nameZh}"/><em>*</em>
			</td>
		</tr>
		<tr>
			<th>角色英文名称</th>
			<td>
				<input type="text" id="nameEn" name="nameEn" value="${role.nameEn}"/><em>*</em>
			</td>
		</tr>
		<tr>
			<th>访问权限</th>
			<td>
				<c:forEach items="${resources}" var="res">
					<c:set var="hasResourceAuth" value="${myFun:hasResourceAuth(role.auth,res.id)}"/>
					<div style="margin-left:40px;">
						<h5>${res.nameZh}：</h5>
						<input <c:if test="${hasResourceAuth eq 'true'}"> checked="checked" </c:if> 
							type="radio" id="${res.id}" name="${res.id}" value="1"/>允许
						<input <c:if test="${hasResourceAuth eq 'false'}"> checked="checked" </c:if> 
							type="radio" id="${res.id}" name="${res.id}" value="0"/>不允许
					</div>
				</c:forEach>
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">

/**
 * 表单验证
 */
function check(){
	var nameZh = document.getElementById("nameZh").value;
	var nameEn = document.getElementById("nameEn").value;
	if(nameZh == null || nameZh == ""){
     	$.messager.alert('提示', "角色中文名称不能为空！", 'info');
     	return false;
    }
	if(nameEn == null || nameEn == ""){
     	$.messager.alert('提示', "角色英文名称不能为空！", 'info');
     	return false;
    }
    return true;
}

</script>

<jsp:include page="../footer.jsp"/>

