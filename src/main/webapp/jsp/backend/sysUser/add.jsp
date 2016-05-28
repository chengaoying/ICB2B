<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<form id="edit_form" method="post" style="padding:10px">
	<table class="editTable">
		<tr>
			<th>登入帐号</th>
			<td><input type="text" id="account" name="account" /><em>*</em></td>
		</tr>
		<tr>
			<th>登入密码</th>
			<td><input type="text" id="password" name="password" /><em>*</em></td>
		</tr>
		<tr>
			<th>名称</th>
			<td><input type="text" id="name" name="name" /><em>*</em></td>
		</tr>
		<tr>
			<th>所属角色</th>
			<td>
				<select class="easyui-combobox" id="roleId" name="role.id" style="width:150px;">
				<c:forEach items="${roleList}" var="role">
					<option value="${role.id}">${role.name}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>状态</th>
			<td>
				<input type="radio" value="1" name="status" id="status1"/>
				<label for="status1">启用</label>　　
				<input type="radio" value="0" name="status" id="status0" checked="checked"/>
				<label for="status0">禁用</label>
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">

/**
 * 表单验证
 */
function check(){
	var account = document.getElementById("account").value;
	var name = document.getElementById("name").value;
	var password = document.getElementById("password").value;
	if(name == null || name == ""){
     	$.messager.alert('提示', "名称不能为空！", 'info');
     	return false;
     }
     if(account == null || account == ""){
     	$.messager.alert('提示', "帐号不能为空！", 'info');
     	return false;
     }
     if(password == null || password == ""){
      	$.messager.alert('提示', "密码不能为空！", 'info');
      	return false;
      }
     return true;
}

</script>

<jsp:include page="../footer.jsp"/>