<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<div data-options="region:'center',split:true,border:false" style="overflow: hidden;">
	<div id="datagrid_toolbar" style="padding:5px;">
	   <div style="float: left;"  style="height:25px;">
	        <form method="post" id="search_form" style="padding: 0px;">
				<input type="text" name="id" placeholder="请输入用户名称" style="width: 140px"></input>
				<a href="javascript:search(datagrid,'#search_form');" class="easyui-linkbutton" iconCls="icon-search" plain="true" >查 询</a>
			</form>
		</div>
		<div align="right" style="height:25px;">
			<c:if test="${edit eq '1'}">
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="edit()">编辑</a>
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
		url: '${backend_uri}/userManager/loadData',
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
	        {field:'name',title:'用户名',width:120},    
	        {field:'realName',title:'真实姓名',width:120},    
	        {field:'email',title:'邮箱',width:120},    
	        {field:'mobile',title:'手机号',width:120},    
	        {field:'otherAccount',title:'第三方登入帐号',width:180},    
	        {field:'level',title:'用户等级',width:80},    
	        {field:'point',title:'积分',width:80},    
	        {field:'status',title:'状态',width:60},    
	        {field:'addTime',title:'创建时间',width:180},    
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


function edit(rowIndex,rowData){
	editData(rowIndex,rowData,"编辑",'#edit_form',datagrid,"${backend_uri}/userManager/edit",edit_W,edit_H);
}

</script>

<jsp:include page="../footer.jsp"/>