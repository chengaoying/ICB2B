<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<form id="edit_form" method="post" enctype="multipart/form-data" style="padding:10px">
	<table class="editTable">
		<tr>
			<th>企业名称</th>
			<td>
				<input type="text" id="name" name="name" value="" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>简介</th>
			<td>
				<input type="text" id="shortName" name="shortName" value="" class="easyui-validatebox" data-options="required:true" />
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
			<th>地址详情</th>
			<td>
				<input type="text" id="address" name="address" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>业务模式</th>
			<td>
				<input type="text" id="businessModel" name="businessModel" value="" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>联系人</th>
			<td>
				<input type="text" id="contacts" name="contacts" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>联系电话</th>
			<td>
				<input type="text" id="phone" name="phone" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>三证合一</th>
			<td>
				<input type="text" id="qualification0" name="qualification0" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>营业执照</th>
			<td>
				<input type="text" id="qualification1" name="qualification1" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>组织机构代码</th>
			<td>
				<input type="text" id="qualification2" name="qualification2" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>税务登记证</th>
			<td>
				<input type="text" id="qualification3" name="qualification3" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>开户许可证</th>
			<td>
				<input type="text" id="qualification4" name="qualification4" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>企业认证授权书</th>
			<td>
				<input type="text" id="qualification5" name="qualification5" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>代理资格证</th>
			<td>
				<input type="text" id="qualification6" name="qualification6" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>状态</th>
			<td>
				<input type="radio" value="1" name="status" id="status"  />
				<label for="status1">启用</label>　　
				<input type="radio" value="0" name="status" id="status"  />
				<label for="status0">禁用</label>
			</td>
		</tr>
	</table>
</form>


<script type="text/javascript">

/**
 * 表单验证
 */
function check(){
	var businessModel = document.getElementById("businessModel").value;
	var name = document.getElementById("name").value;
	
	var country = document.getElementById("country").value;
	var province = document.getElementById("province").value;
	var city = document.getElementById("city").value;
	var district = document.getElementById("district").value;
	
	if(name == null || name == ""){
     	$.messager.alert('提示', "名称不能为空！", 'info');
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
     
	if(businessModel == null || businessModel == ""){
     	$.messager.alert('提示', "商业模式不能为空！", 'info');
     	return false;
    }
 	
    return true;
}

</script>
<jsp:include page="../footer.jsp"/>