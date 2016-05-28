<jsp:include page="../header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<form id="edit_form" method="post" enctype="multipart/form-data" style="padding: 10px">
	<table class="editTable">
		<tr>
			<th>父类</th>
			<td><select class="easyui-combobox" id="pid_level" name="pid_level" style="width: 150px;">
					<option value="0_0">顶级分类</option>
					<c:forEach items="${classList}" var="cls">
						<!-- 父类中不显示自身节点（父节点不能是自己） -->
						<c:if test="${category.id != cls.id}">
							<option value="${cls.id}_${cls.level}" <c:if test="${category.pId == cls.id}">selected="selected"</c:if>><c:if
									test="${cls.level == 2}">····</c:if>${cls.nameZh}</option>
						</c:if>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th>分类中文名</th>
			<td><input type="hidden" id="id" name="id" value="${category.id}" /> <input type="text" id="nameZh"
				name="nameZh" value="${category.nameZh}" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th>分类英文名</th>
			<td><input type="text" id="nameEn" name="nameEn" value="${category.nameEn}" class="easyui-validatebox"
				data-options="required:true" /></td>
		</tr>
		<tr>
			<th>分类模版文件</th>
			<td>
			<input type="text" id="tempFile" name="tempFile" value="${category.templeteUrl}" readonly/>
			<input type="file" id="docFile" name="docFile" />
		</tr>
		<tr>
			<th>分类层级</th>
			<td><input type="text" id="level" name="level" value="${category.level}" /><em>*</em></td>
		</tr>
		<tr>
			<th>排序</th>
			<td><input type="text" id="sort" name="sort" value="${category.sort}" class="easyui-validatebox"
				data-options="required:true" /></td>
		</tr>
		<tr>
			<th>是否显示</th>
			<td><select class="easyui-combobox" id="isShow" name="isShow" style="width: 100px;">
					<option value="1">显示</option>
					<option value="-1">不显示</option>
			</select></td>
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
		var sort = document.getElementById("sort").value;

		if (nameZh == null || nameZh == "") {
			$.messager.alert('提示', "请填写分类中文名！", 'info');
			return false;
		}
		if (nameEn == null || nameEn == "") {
			$.messager.alert('提示', "请填写分类英文名！", 'info');
			return false;
		}
		if (sort == null || sort == "") {
			$.messager.alert('提示', "请填写产品分类排序！", 'info');
			return false;
		}
		return true;
	}
</script>

<jsp:include page="../footer.jsp" />