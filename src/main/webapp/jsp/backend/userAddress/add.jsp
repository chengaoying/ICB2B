<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<form id="edit_form" method="post" enctype="multipart/form-data" style="padding:10px">
	<table class="editTable">
		<tr>
			<th>用户帐号</th>
			<td>
				<select class="easyui-combobox" id="user_id" name="user_id" style="width:150px;">
					<c:forEach items="${userList}" var="user">
						<option value="${user.id}">${user.email}</option>
					</c:forEach>
				</select><em>*</em>
			</td>
		</tr>
		<tr>
			<th>联系人</th>
			<td>
			<input type="text" id="contacts" name="contacts" value="" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>联系电话</th>
			<td>
				<input type="text" id="tel" name="tel" value="" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>手机号</th>
			<td>
				<input type="text" id="mobile" name="mobile" value="" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>国家</th>
			<td>
			<input type="text" id="country" name="country" value="" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>省份</th>
			<td>
				<input type="text" id="province" name="province" value="" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>城市</th>
			<td>
				<input type="text" id="city" name="city" value="" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>地区</th>
			<td>
				<input type="text" id="district" name="district" value="" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>联系地址</th>
			<td>
				<input type="text" id="address" name="address" value="" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
	</table>
</form>


<script type="text/javascript">

/**
 * 表单验证
 */
function check(){
	var user_id = document.getElementById("user_id").value;
	
	var contacts = document.getElementById("contacts").value;
	var tel = document.getElementById("tel").value;
	var mobile = document.getElementById("mobile").value;
	var address = document.getElementById("address").value;
	
	var country = document.getElementById("country").value;
	var province = document.getElementById("province").value;
	var city = document.getElementById("city").value;
	var district = document.getElementById("district").value;
	
	if(user_id == null || user_id == ""){
	  	$.messager.alert('提示', "用户账号不能为空！", 'info');
     	return false;
  	}
	
   	if(contacts == null || contacts == ""){
     	$.messager.alert('提示', "联系人不能为空！", 'info');
     	return false;
    }
    if(tel == null || tel == ""){
      	$.messager.alert('提示', "联系电话不能为空！", 'info');
      	return false;
    }
    if(mobile == null || mobile == ""){
      	$.messager.alert('提示', "手机号不能为空！", 'info');
      	return false;
    }
	
   	if(country == null || country == ""){
     	$.messager.alert('提示', "国家不能为空！", 'info');
     	return false;
    }
    if(province == null || province == ""){
      	$.messager.alert('提示', "省份不能为空！", 'info');
      	return false;
    }
    if(city == null || city == ""){
      	$.messager.alert('提示', "城市不能为空！", 'info');
      	return false;
    }
	if(district == null || district == ""){
	  	$.messager.alert('提示', "地区不能为空！", 'info');
     	return false;
  	}
	
	if(address == null || address == ""){
	  	$.messager.alert('提示', "收货地址不能为空！", 'info');
     	return false;
  	}
     
    return true;
}

</script>

<jsp:include page="../footer.jsp"/>