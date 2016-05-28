<jsp:include page="header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
    
<div data-options="region:'center',split:true" style="overflow: hidden;">
	<noscript>您的浏览器暂不支持脚本功能，请开启脚本支持</noscript>
	<div style="font-size:30px;letter-spacing:20px; padding:80px; text-align:center">屏芯网平台管理系统</div>
	<div id="win" class="easyui-window" title="管理员登录" style="width:320px;height:240px"  
	       data-options="collapsible:false,minimizable:false,maximizable:false,closable:false,resizable:false">  
	    <form id="login_form" method="post" style="padding:20px 35px;">  
		    <div>  
		        <label for="account">用户名：</label>  
		        <input id="account" type="text" name="account" style="width:150px;" />  
		    </div> 
		    <br/>
		    <div>  
		        <label for="password">密　码：</label>  
		        <input id="password" type="password" name="password" style="width:150px;" />  
		    </div>		   
		    <div id="info" style="color:red;text-align:center; padding:20px 0px;"></div>
		    <div>
		    	<label>　　　　</label>
		    	<input id="btnLogin" type="submit" value="登录" style="padding:5px 15px"/>		    
		    </div>
		</form>   
	</div>
</div>

<script>
$('#login_form').form({
	url:"${backend_uri}/loginValidate",
    onSubmit: function(){   
       	return check();
    },
    error:function(data){  
        $.messager.alert('登入异常', data, 'info');
    },
    success:function(data){
     	var _d = eval('(' + data + ')');
    	if(_d.status){
    		top.location = _d.data;
    	}else{
    		$.messager.alert('登录失败', _d.info, 'info');
    	}
    }   
});

//检验用户名和密码是否为空
function check(){
	 var u = document.getElementById("account").value;
     var p = document.getElementById("password").value;
     if(u == null || u == ""){
     	$.messager.alert('登录失败', "用户名不能为空！", 'info');
     	//$("#info").html("用户名不能为空！");
     	return false;
     }
     if(p == null || p == ""){
     	$.messager.alert('登录失败', "密码不能为空！", 'info');
     	return false;
     }
}
</script>

<jsp:include page="footer.jsp"/>
