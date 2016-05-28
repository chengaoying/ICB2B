<jsp:include page="../header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form id="add_form" method="post" enctype="multipart/form-data" style="padding: 10px">
	<table class="editTable">
		<tr>
			<th>品牌中文名</th>
			<td><input type="text" id="nameZh" name="nameZh" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th>品牌英文名</th>
			<td><input type="text" id="nameEn" name="nameEn" class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<tr>
			<th>产品图片</th>
			<td><input type="file" id="imgFile" name="imgFile" data-options="required:true" />
			<p style="color: red; font-size: 16px;">图片只支持jpg或png</p></td>
		</tr>
		<tr>
			<th>品牌中文描述</th>
			<td><textarea id="descZh" name="descZh" style="width: 100%; height: 200px;"></textarea></td>
		</tr>
		<tr>
			<th>品牌英文描述</th>
			<td><textarea id="descEn" name="descEn" style="width: 100%; height: 200px;"></textarea></td>
		</tr>
	</table>
</form>

<script type="text/javascript">
	var editor;
	$(function() {
		window.editor = KindEditor.create(
				'textarea[id="descZh","descEn"]', {
					resizeType : 1,
					newlineTag : "p",
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					afterBlur : function() {
						this.sync();
					},
					items : [ 'source', 'undo', 'redo', '|', 'print', 'cut',
							'copy', 'paste', '|', 'justifyleft',
							'justifycenter', 'justifyright', 'justifyfull',
							'insertorderedlist', 'insertunorderedlist',
							'indent', 'outdent', 'selectall', '|', 'preview',
							'fullscreen', '/', 'formatblock', 'fontname',
							'fontsize', '|', 'forecolor', 'hilitecolor',
							'bold', 'italic', 'underline', 'strikethrough',
							'hr' ]
				});
	});

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