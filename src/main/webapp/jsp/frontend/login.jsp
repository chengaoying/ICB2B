<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${resource_uri}/js/frontend/jquery.min.js" type="text/javascript"></script>
<title>login</title>
</head>
<body>

<form id="login_form" action="" method="post">
<input type="text" id="name" name="name">
<input type="password" id="password" name="password">
<input type="submit" value="登录" id="submit">
</form>

<script type="text/javascript">

$(document).ready(function () {
    $("#submit").click(function () {
        var options = {
            url: '${frontend_uri}/login.html',
            type: 'post',
            dataType: 'text',
            data: $("#login_form").serialize(),
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