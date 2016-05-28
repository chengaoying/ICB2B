<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<div data-options="region:'center',split:true,border:false" style="overflow: hidden;">
	<div id="datagrid_toolbar" style="padding:5px;">
	   <div style="float: left;"  style="height:25px;">
	        <form method="post" id="search_form" style="padding: 0px;">
				<input type="text" name="name" placeholder="请输入企业名称" style="width: 140px"></input>
				<input type="text" name="contacts" placeholder="请输入联系人" style="width: 140px"></input>
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

//会员状态
var statusArr = new Array("待激活","待完善信息","待审核","审核未通过","审核通过");

//会员类型
var typeArr = new Array("普通会员","采购商","供应商","即是采购商也是供应商");

var edit_W = 600;
var edit_H = 500;
var datagrid;
$(function(){
	//数据列表
	datagrid = $("#datagrid").datagrid({
		url: '${backend_uri}/organization/loadData',
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
	        {field:'name',title:'企业名称',width:120},    
	        {field:'shortName',title:'企业简介',width:120},
	        {field:'country',title:'国家',width:120},
	        {field:'province',title:'省份',width:120},   
	        {field:'city',title:'城市',width:120},   
	        {field:'district',title:'地区',width:120},   
	        {field:'address',title:'地址',width:120},
	        {field:'businessModel',title:'业务模式',width:120}, 
	        {field:'contacts',title:'联系人',width:120},    
	        {field:'phone',title:'联系电话',width:120},
	        {field:'qualification0',title:'三证合一',width:120}, 
	        {field:'qualification1',title:'营业执照',width:120},    
	        {field:'qualification2',title:'组织机构代码',width:120},
	        {field:'qualification3',title:'税务登记证',width:120},
	        {field:'qualification4',title:'开户许可证',width:120},    
	        {field:'qualification5',title:'企业认证授权书',width:120},
	        {field:'qualification6',title:'代理资格证',width:120},
	        {field:'status',title:'状态',width:120,
	        	formatter:function(value,row,index){
	        		return statusArr[value];
            	}
	        },
	        {field:'addTime',title:'添加时间',width:150,
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
	addData("添加","#edit_form",datagrid,"${backend_uri}/organization/add",edit_W,edit_H);
}
function edit(rowIndex,rowData){
	editData(rowIndex,rowData,"编辑",'#edit_form',datagrid,"${backend_uri}/organization/edit",edit_W,edit_H);
}
function del(){
	delData(datagrid,"${backend_uri}/organization/del");
}

</script>

<jsp:include page="../footer.jsp"/>