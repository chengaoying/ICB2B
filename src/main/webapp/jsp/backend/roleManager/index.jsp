<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<div data-options="region:'center',split:true,border:false" style="overflow: hidden;">
	<div id="datagrid_toolbar" style="padding:5px;">
	   <div style="float: left;"  style="height:25px;">
		</div>
		<div align="right" style="height:25px;">
			<c:if test="${add eq '1'}">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add()">新增</a>
			</c:if>
			<c:if test="${edit eq '1'}">
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
			</c:if>
			<c:if test="${delete eq '1'}">
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="del()">删除</a>
			</c:if>	
		</div>
	</div>
	
	<table id="datagrid" toolbar="#datagrid_toolbar" border="false"></table>
	
</div>

<script type="text/javascript">
var edit_W = 600;
var edit_H = 500;
var datagrid;
$(function(){
	//数据列表
	datagrid = $("#datagrid").datagrid({
		url: '${backend_uri}/roleManager/loadData',
		fit: true,
		autoRowHeight: false, //自动行高
		border:false,
		pagination:true, //分页栏
		pagePosition:'bottom', //分页栏位置
		rownumbers:true,//显示行数
	    pageSize:'${pageSize}',//每页记录数
        singleSelect:true,//只允许选择单行
        columns:[[    
	        {field:'id',title:'ID',width:60},    
	        {field:'nameZh',title:'角色中文名称',width:120},    
	        {field:'nameEn',title:'角色英文名称',width:120},    
	        {field:'auth',title:'权限',width:200},    
        ]],  
        onLoadSuccess:function(){
	    	$(this).datagrid('clearSelections');//取消所有的已选择项
	    	$(this).datagrid('unselectAll');//取消全选按钮为全选状态
		},
        onDblClickRow:function(rowIndex, rowData){
            edit(rowIndex, rowData);
        },       
	});
});


function add(){
	addData("添加","#edit_form",datagrid,"${backend_uri}/roleManager/add",edit_W,edit_H);
}
function edit(rowIndex,rowData){
	editData(rowIndex,rowData,"编辑",'#edit_form',datagrid,"${backend_uri}/roleManager/edit",edit_W,edit_H);
}
function del(){
	delData(datagrid,"${backend_uri}/roleManager/del");
}

</script>

<jsp:include page="../footer.jsp"/>