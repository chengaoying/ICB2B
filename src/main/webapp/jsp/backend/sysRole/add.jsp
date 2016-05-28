<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<form id="edit_form" method="post" style="padding:10px">
	<table class="editTable">
		<tr>
			<th>角色名</th>
			<td><input type="text" id="name" name="name" /><em>*</em></td>
		</tr>
		<tr>
			<th>超级管理</th>
			<td><input type="radio" value="1" name="isSuper" id="isSuper1" ><label for="isSuper1">是</label>　　
				<input type="radio" value="0" name="isSuper" id="isSuper0" checked="checked"><label for="isSuper0">否</label>
				<span>(超级组将拥有全部的权限，不论下方小项是否设置，请谨慎！)</span>				
			</td>
		</tr>
		<tr>
			<th>操作权限</th>
			<td>
				<c:forEach items="${menus}" var="menu">
					<div style="line-height:30px; font-weight:bold;">${menu.name}：</div>
					<c:forEach items="${menu.subMenus}" var="item">
						<div style="margin-left:40px;">
						<h5>${item.name}：</h5>
						<c:forEach items="${item.property}" var="prop">
							<c:set var="propValue" value="${prop.value.split(\"_\")[0]}"></c:set>
							<input type="checkbox" name="${item.action}_${propValue}" value="1" id="${item.action}_${propValue}"/>
							<label for="${item.action}_${propValue}">${prop.key}</label>
						</c:forEach>
						</div>
					</c:forEach>
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
	var name = document.getElementById("name").value;
	if(name == null || name == ""){
     	$.messager.alert('提示', "角色名称不能为空！", 'info');
     	return false;
     }
     return true;
}

</script>

<jsp:include page="../footer.jsp"/>