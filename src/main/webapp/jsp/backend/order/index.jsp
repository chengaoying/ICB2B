<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<div data-options="region:'center',split:true,border:false" style="overflow: hidden;">
	<div id="datagrid_toolbar" style="padding:5px;">
	   <div style="float: left;"  style="height:25px;">
	        <form method="post" id="search_form" style="padding: 0px;">
				<input type="text" name="buyerId" placeholder="请输入采购商id" style="width: 140px"></input>
				<input type="text" name="sellerId" placeholder="请输入销售商id" style="width: 140px"></input>
				<a href="javascript:search(datagrid,'#search_form');" class="easyui-linkbutton" iconCls="icon-search" plain="true" >查 询</a>
			</form>
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
		url: '${backend_uri}/order/loadData',
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
	        {field:'buyerId',title:'采购商id',width:120},    
	        {field:'sellerId',title:'销售商id',width:120},
	        {field:'code',title:'订单编号',width:120},
	        {field:'amount',title:'总金额',width:120},   
	        {field:'contacts',title:'联系人',width:120},   
	        {field:'tel',title:'联系电话',width:120},   
	        {field:'address',title:'收货地址',width:120},
	        {field:'status',title:'状态',width:120,
	        	formatter:function(value,row,index){
	        		if(value == 0) return "未付款";
	        		if(value == 1) return "已付款";
	        		if(value == 2) return "待发货";
	        		if(value == 3) return "已发货";
	        		if(value == 4) return "交易完成";
            	}
	        }, 
	        {field:'type',title:'类型',width:120},    
	        {field:'certificate1',title:'付款凭证',width:120}, 
	        {field:'certificate2',title:'发货凭证',width:120},    
	        {field:'addTime',title:'下单时间',width:150,
	        	formatter:function(value,row,index){
	        		return formatTime(value);
            	}
	        },
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
	addData("添加","#edit_form",datagrid,"${backend_uri}/order/add",edit_W,edit_H);
}
function edit(rowIndex,rowData){
	editData(rowIndex,rowData,"编辑",'#edit_form',datagrid,"${backend_uri}/order/edit",edit_W,edit_H);
}
function del(){
	delData(datagrid,"${backend_uri}/order/del");
}

</script>

<jsp:include page="../footer.jsp"/>