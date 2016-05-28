<jsp:include page="../header.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div data-options="region:'center',split:true,border:false"
	style="overflow: hidden;">
	<div id="datagrid_toolbar" style="padding: 5px;">
		<div style="float: left;" style="height:25px;">
		<form method="post" id="search_form" style="padding: 0px;">
			品牌ID：
			<input type="text" name="id" placeholder="ID" style="width: 40px"></input>&nbsp;&nbsp;
			品牌中文名：
			<input type="text" name="nameZh" placeholder="品牌中文名" style="width: 140px"></input>&nbsp;&nbsp;
			品牌英文名：
			<input type="text" name="nameEn" placeholder="品牌英文名" style="width: 140px"></input>&nbsp;&nbsp; 
			
			<a href="javascript:search(datagrid,'#search_form');" class="easyui-linkbutton" 
			iconCls="icon-search" plain="true">查询</a>
		</form>
		</div>
		<div align="right" style="height: 25px;">
			<c:if test="${add eq '1'}">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"
					plain="true" onclick="add()">新增</a>
			</c:if>
			<c:if test="${edit eq '1'}">
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"
					plain="true" onclick="edit()">编辑</a>
			</c:if>
			<c:if test="${delete eq '1'}">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"
					plain="true" onclick="del()">删除</a>
			</c:if>
		</div>
	</div>

	<table id="datagrid" toolbar="#datagrid_toolbar" border="false"></table>

</div>

<script type="text/javascript">
	var edit_W = 800;
	var edit_H = 600;
	var datagrid;
	$(function() {
		//数据列表
		datagrid = $("#datagrid").datagrid({
			url : '${ctx}/backend/brandManager/loadData',
			fit : true,
			autoRowHeight : false, //自动行高
			border : false,
			pagination : true, //分页栏
			pagePosition : 'bottom', //分页栏位置
			rownumbers : true,//显示行数
			pageSize : '${pageSize}',//每页记录数
			singleSelect : true,//只允许选择单行
			columns : [ [ {
				field : 'id',
				title : '品牌ID',
				width : 40
			}, {
				field : 'nameZh',
				title : '品牌中文名',
				width : 110
			}, {
				field : 'nameEn',
				title : '品牌英文名',
				width : 110
			}, {
				field : 'logo',
				title : '品牌Logo',
				width : 200
			}, {
				field : 'descZh',
				title : '品牌中文描述',
				width : 300
			}, {
				field : 'descEn',
				title : '品牌英文描述',
				width : 300
			}, ] ],
			onLoadSuccess : function() {
				$(this).datagrid('clearSelections');//取消所有的已选择项
				$(this).datagrid('unselectAll');//取消全选按钮为全选状态
			},
			onDblClickRow : function(rowIndex, rowData) {
				edit(rowIndex, rowData);
			},
		});
	});

	function add() {
		addData("添加", "#add_form", datagrid,
				"${ctx}/backend/brandManager/add", edit_W, edit_H, true);
	}
	function edit(rowIndex, rowData) {
		editData(rowIndex, rowData, "编辑", '#edit_form', datagrid,
				"${ctx}/backend/brandManager/edit", edit_W, edit_H, "", true);
	}
	function del() {
		delData(datagrid, "${ctx}/backend/brandManager/del");
	}
</script>

<jsp:include page="../footer.jsp" />