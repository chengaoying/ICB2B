
//查询
function search(datagrid,formId){
	datagrid.datagrid('load',$.serializeObject($(formId).form()));
}

//删除数据
function delData(datagrid,actUrl){
	postData(actUrl,true,'数据删除后无法恢复，确定要删除数据吗？',datagrid,"one");
}

//新增数据
function addData(title,formId,datagrid,actUrl,width,height,maximized){
	return editDialog(title,formId,datagrid,actUrl,width,height,maximized);
}
//编辑数据
function editData(rowIndex,rowData,title,formId,datagrid,actUrl,width,height,field,maximized){
	if(field==undefined || field=="") field="id";
    if(rowIndex != undefined) {
    	fieldValue = eval("rowData."+field);
    	if(actUrl.indexOf("?")>=0){
    		var url = actUrl + "&" +field + "=" + fieldValue;
    	}else{
    		var url = actUrl + "?" +field + "=" + fieldValue;
    	}
    	return editDialog(title,formId,datagrid,url,width,height,maximized);        
    }
    //选中的所有行
	var rows = datagrid.datagrid('getSelections');
	//选中的行（第一次选择的行）
	var row = datagrid.datagrid('getSelected');
	if (row){
		if(rows.length>1){
			row = rows[rows.length-1];
			$.messager.alert("提示信息","您选择了多个操作对象，默认操作最后一次被选中的记录！");
		}
		fieldValue = eval("row."+field);
		if(actUrl.indexOf("?")>=0){
			var url = actUrl + "&" +field + "=" + fieldValue;
    	}else{
    		var url = actUrl + "?" +field + "=" + fieldValue;
    	}
		return editDialog(title,formId,datagrid,url,width,height,maximized);
	}else{
		$.messager.alert("提示信息","请选择要操作的对象！");
	}
}


//提交数据
function postData(actUrl,isShowMsg,confirmText,datagrid,isGetRow,field){	
	if(field==undefined || field=="") field="id";	
	var rows = 1;
	var params = '';
	if(datagrid!=undefined ){
		rows = datagrid.datagrid('getSelections');
		row = datagrid.datagrid('getSelected');
		if(row){
			if(rows.length>1){
				row = rows[rows.length-1];
				$.messager.alert("提示信息","您选择了多个操作对象，默认操作最后一次被选中的记录！");
			}	
			var ids = eval("row."+field);
			params = {id:ids};		
			if(confirmText == undefined || confirmText==''){
				post(actUrl,isShowMsg,datagrid,params);
			}else{
				$.messager.confirm('确认提示！',confirmText,function(r){
					if(r){
						post(actUrl,isShowMsg,datagrid,params);
					}
				});
			}
		}else{
			$.messager.alert('提示信息',"请选择要操作的对象！");
		}
	}
}

function post(actUrl,isShowMsg,datagrid,params){
	if(params==undefined) params = '';
	$.post(actUrl,params,function(data){
		if (data.status==1){			
			if(isShowMsg==undefined || isShowMsg) $.messager.alert('提示信息',data.info);
			if(datagrid!=undefined) datagrid.datagrid('load');	// reload the user data
		} else {
			$.messager.alert('错误信息',data.info);
		}
	},'json');	
}

//编辑对话窗口
function editDialog(title,formId,datagrid,actUrl,width,height,maximized){
	if(datagrid==undefined || datagrid=='') datagrid==null;
	if(width==undefined) width=400;
	if(height==undefined) width=300;
	if(formId==undefined || formId==null) formId="";
	var the_form = null;
	var the_dialog = null;	
	the_dialog = $("<div/>").dialog({
		title: title,
		width: width,
		height: height,
		href: actUrl,
		modal: true,
		maximizable: true,
		maximized: (maximized != undefined ? maximized : false),
		buttons : [{
			text : '确定',
			iconCls : 'icon-save',
			handler : function() {
				if(the_form==null){
					the_dialog.dialog('destroy');
				}else{
					if(typeof(check)=='undefined' || check()){//表单验证
						the_form.submit();
					}
				}
			}
		},{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				the_dialog.dialog('destroy');
			}
		}],		
		onClose : function() {
			$(this).dialog('destroy');
		},
		onLoad : function(data){
			if(formId=="") return;
			$(formId).find("#submit").hide();
			//初始化表单
			the_form = $(formId).form({
				url: actUrl,
				onSubmit: function(param){ 
					$.messager.progress({ title : '提示信息', text : '数据处理中，请稍候....'	});
			    },
				success: function(data){
					$.messager.progress('close');
					var json = $.parseJSON(data);
					if (json.status == 1){	
						$.messager.alert('提示信息',json.info);//操作结果提示
						if(datagrid!=null) datagrid.datagrid('reload');	// reload the user data
						the_dialog.dialog('destroy');//销毁对话框 
					}else {
						$.messager.alert('错误信息',json.info);//操作结果提示
					}					
				},
			});
		},
	}).dialog('open');
	return the_dialog;
}

//弹出查看表头列选择对话窗
function selColDialog(datagridId){
	var columns = $(datagridId).datagrid('getColumnFields');	
	var content = '';
	for(var i=0; i<columns.length; i++){		
		opt = $(datagridId).datagrid('getColumnOption',columns[i]);		
		content = content + '<div><input type="checkbox" id="ck' + opt.field + '" onclick="selCol(this,\'' + datagrid + '\',\'' + opt.field + '\')"' + (opt.hidden ? '' : 'checked="checked"') + '><label for="ck' + opt.field + '">' + opt.title + '</label></div>';
	}
	content = '<div id="selColDialog" style="padding:10px 40px 20px 10px;">' + content + '</div>';
	the_dialog = $('<div/>').dialog({  
	    content: content,
	    title: '查看列',
	    minimizable: false,
	    maximizable: false,
		buttons : [{
			text : '关闭',
			iconCls : 'icon-cancel',
			handler : function() {
				the_dialog.dialog('destroy');
			}
		}],		
		onClose : function() {
			the_dialog.dialog('destroy');
		},
	}).dialog('open');
}
//显示/隐藏表头列
function selCol(obj,datagridId,id){
	if($(obj).attr("checked")=="checked"){
		$(datagridId).datagrid('showColumn',id); 
	}else{
		$(datagridId).datagrid('hideColumn',id); 
	}
}

/**
 * jQuery方法扩展：将form表单元素的值序列化成对象
 * @author 尔演@Eryan eryanwcp@gmail.com
 * @version 2013-05-26
 * @returns object
 */
$.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

