<jsp:include page="../header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form id="add_form" method="post" enctype="multipart/form-data" style="padding: 10px">
	<table class="editTable">
		<tr>
			<th>商品名</th>
			<td><select class="easyui-combobox" id="pid_level" name="pid_level" style="width: 150px;">
					<c:forEach items="${classList}" var="cls">
						<option value="${cls.id}_${cls.level}"><c:if test="${cls.level == 2}">····</c:if>${cls.nameZh}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th>促销价格</th>
			<td><input type="text" id="promotePrice" name="promotePrice" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th>最多可购数量</th>
			<td><input type="text" id="maxCount" name="maxCount" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th>最低起购数量</th>
			<td><input type="text" id="minCount" name="minCount" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th>拼购开始时间</th>
			<td><input id="startDate" name="startDate" class="easyui-datebox"></input></td>
		</tr>
		<tr>
			<th>拼购结束时间</th>
			<td><input id="endDate" name="endDate" class="easyui-datebox"></input></td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	/**
	 * 表单验证
	 */
	function check() {
		var nameZh = document.getElementById("nameZh").value;
		var nameEn = document.getElementById("nameEn").value;
		var descZh = document.getElementById("descZh").value;
		var descEn = document.getElementById("descEn").value;
		
		if (nameZh == null || nameZh == "") {
			$.messager.alert('提示', "请填写品牌中文名！", 'info');
			return false;
		}
		else if (nameEn == null || nameEn == "") {
			$.messager.alert('提示', "请填写品牌英文名！", 'info');
			return false;
		}
		else if (descZh == null || descZh == "") {
			$.messager.alert('提示', "请填写品牌中文描述！", 'info');
			return false;
		}else if (descEn == null || descEn == "") {
			$.messager.alert('提示', "请填写品牌英文描述！", 'info');
			return false;
		}
		return true;
	}
</script>

<jsp:include page="../footer.jsp" />