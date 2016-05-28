<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<form id="edit_form" method="post" style="padding:10px">
	<table class="editTable">
		<tr>
			<th>资源中文名称</th>
			<td><input type="text" id="nameZh" name="nameZh" /><em>*</em></td>
		</tr>
		<tr>
			<th>资源英文名称</th>
			<td><input type="text" id="nameEn" name="nameEn" /><em>*</em></td>
		</tr>
		<tr>
			<th>访问地址</th>
			<td><input type="text" id="url" name="url" /></td>
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
     	$.messager.alert('提示', "资源中文名称不能为空！", 'info');
     	return false;
    }
	if(nameEn == null || nameEn == ""){
     	$.messager.alert('提示', "资源英文名称不能为空！", 'info');
     	return false;
    }
    return true;
}

</script>

<jsp:include page="../footer.jsp"/>