<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${resource_uri}/js/frontend/jquery.min.js" type="text/javascript"></script>
<script src="${resource_uri}/js/common.js" type="text/javascript"></script>
<title>注册</title>
</head>
<body>

<form id="register_form" action="" method="post">
	<input type="hidden" id="type" name="type" value="email">
	邮箱：<input type="text" id="email" name="email" value=""><br>
	密码：<input type="password" id="password" name="password"><br>
	确认密码：<input type="password" id="comfirmPassword" name="comfirmPassword"><br>
	验证码：<input type="text" id="checkCode" name="checkCode" value="${checkCode}"><br>
	<input type="submit" value="登录" id="submit">
</form>

<script type="text/javascript">

//表单校验
function check(){
	if(isEmpty($("#email").val())){
		alert("邮箱不能为空！")
	}
	else if(isEmpty($("#password").val())){
		alert("密码不能为空！")
	}
	else if(isEmpty($("#comfirmPassword").val())){
		alert("确认密码不能为空！")
	}
	else if($("#password").val() != $("#comfirmPassword").val()){
		alert("两次密码输入不一致！");
	}
	else if(isEmpty($("#checkCode").val())){
		alert("验证码不能为空！")
	}
}

$(document).ready(function () {
    $("#submit").click(function () {
    	check();
        var options = {
            url: '${frontend_uri}/register.html',
            type: 'post',
            dataType: 'text',
            data: $("#register_form").serialize(),
            success: function (data) {
            	var _data = eval('(' + data + ')');
                if (_data.status == 'success'){
                	location.href = "${frontend_uri}"+_data.data;
                }else if(_data.status == 'error'){
                	//登入失败
                	alert(_data.info);
                }
            }
        };
        $.ajax(options);
        return false;
    });
});

</script>

</body>
</html>