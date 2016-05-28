<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<form id="edit_form" method="post" enctype="multipart/form-data" style="padding:10px">
	<table class="editTable">
		<tr>
			<th>采购商id</th>
			<td>
				<input type="hidden" id="id" name="id" value="${order.id}" />
				<input type="text" id="buyerId" name="buyerId" value="${order.buyerId}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>销售商id</th>
			<td>
				<input type="text" id="sellerId" name="sellerId" value="${order.sellerId}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>订单编号</th>
			<td>
			<input type="text" id="code" name="code" value="${order.code}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>总金额</th>
			<td>
				<input type="text" id="amount" name="amount" value="${order.amount}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>联系人</th>
			<td>
				<input type="text" id="contacts" name="contacts" value="${order.contacts}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>联系电话</th>
			<td>
				<input type="text" id="tel" name="tel" value="${order.tel}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>收货地址</th>
			<td>
				<input type="text" id="address" name="address" value="${order.address}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>状态</th>
			<td>
				<select class="easyui-combobox" id="status" name="status" style="width:140px;">
					<option value="0" <c:if test="${0 == order.status}">selected="selected"</c:if> >未付款</option>
					<option value="1" <c:if test="${1 == order.status}">selected="selected"</c:if> >已付款</option>
					<option value="2" <c:if test="${2 == order.status}">selected="selected"</c:if> >待发货</option>
					<option value="3" <c:if test="${3 == order.status}">selected="selected"</c:if> >已发货</option>
					<option value="4" <c:if test="${4 == order.status}">selected="selected"</c:if> >交易完成</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>类型</th>
			<td>
				<input type="text" id="type" name="type" value="${order.type}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>付款凭证</th>
			<td>
				<input type="text" id="certificate1" name="certificate1" value="${order.certificate1}" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>发货凭证</th>
			<td>
				<input type="text" id="certificate2" name="certificate2" value="${order.certificate2}" class="easyui-validatebox" data-options="required:true" />
			</td> 
		</tr>
	</table>
</form>

<script type="text/javascript">

/**
 * 表单验证
 */
function check(){
	var buyerId = document.getElementById("buyerId").value;
	var sellerId = document.getElementById("sellerId").value;
	var code = document.getElementById("code").value;
	
	var amount = document.getElementById("amount").value;
	var contacts = document.getElementById("contacts").value;
	var tel = document.getElementById("tel").value;
	
	var address = document.getElementById("address").value;
	var type = document.getElementById("type").value;
	
	if(buyerId == null || buyerId == ""){
	  	$.messager.alert('提示', "采购商id不能为空！", 'info');
     	return false;
  	}
   	if(sellerId == null || sellerId == ""){
     	$.messager.alert('提示', "销售商id不能为空！", 'info');
     	return false;
    }
    if(code == null || code == ""){
      	$.messager.alert('提示', "订单编号不能为空！", 'info');
      	return false;
    }
    
	if(amount == null || amount == ""){
	  	$.messager.alert('提示', "总金额不能为空！", 'info');
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
    
	if(address == null || address == ""){
	  	$.messager.alert('提示', "收货地址不能为空！", 'info');
     	return false;
  	}
   	if(type == null || type == ""){
     	$.messager.alert('提示', "类型不能为空！", 'info');
     	return false;
    }
     
    return true;
}

</script>

<jsp:include page="../footer.jsp"/>