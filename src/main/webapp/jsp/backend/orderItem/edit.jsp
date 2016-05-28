<jsp:include page="../header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<form id="edit_form" method="post" enctype="multipart/form-data" style="padding:10px">
	<table class="editTable">
		<tr>
			<th>订单id</th>
			<td>
				<input type="hidden" id="id" name="id" value="${orderItem.id}" />
				<input type="text" id="orderId" name="orderId" value="${orderItem.orderId}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>商品型号</th>
			<td>
				<input type="text" id="goodsModel" name="goodsModel" value="${orderItem.goodsModel}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>分类中文名</th>
			<td>
			<input type="text" id="catNameZh" name="catNameZh" value="${orderItem.catNameZh}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>分类英文名</th>
			<td>
				<input type="text" id="catNameEn" name="catNameEn" value="${orderItem.catNameEn}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>商家名称</th>
			<td>
				<input type="text" id="sellerName" name="sellerName" value="${orderItem.sellerName}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>品牌中文名</th>
			<td>
				<input type="text" id="brandNameZh" name="brandNameZh" value="${orderItem.brandNameZh}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>品牌英文名</th>
			<td>
				<input type="text" id="brandNameEn" name="brandNameEn" value="${orderItem.brandNameEn}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>是否现货</th>
			<td>
				<input type="text" id="isInStock" name="isInStock" value="${orderItem.isInStock}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>数量</th>
			<td>
				<input type="text" id="num" name="num" value="${orderItem.num}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>原价</th>
			<td>
				<input type="text" id="normalPrice" name="normalPrice" value="${orderItem.normalPrice}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<tr>
			<th>促销价</th>
			<td>
				<input type="text" id="promotePrice" name="promotePrice" value="${orderItem.promotePrice}" class="easyui-validatebox" data-options="required:true" /><em>*</em>
			</td>
		</tr>
		<!-- <tr>
			<th>交货日期</th>
			<td>
				<input type="text" id="deliveryDate" name="deliveryDate" value="" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr> -->
		<tr>
			<th>物流类型</th>
			<td>
				<input type="text" id="deliveryType" name="deliveryType" value="${orderItem.deliveryType}" class="easyui-validatebox" data-options="required:true" />
			</td>
		</tr>
	</table>
</form>

<script type="text/javascript">

/**
 * 表单验证
 */
function check(){
	var orderId = document.getElementById("orderId").value;
	var goodsModel = document.getElementById("goodsModel").value;
	var catNameZh = document.getElementById("catNameZh").value;
	
	var catNameEn = document.getElementById("catNameEn").value;
	var sellerName = document.getElementById("sellerName").value;
	var brandNameZh = document.getElementById("brandNameZh").value;
	
	var brandNameEn = document.getElementById("brandNameEn").value;
	var isInStock = document.getElementById("isInStock").value;
	
	var num = document.getElementById("num").value;
	var normalPrice = document.getElementById("normalPrice").value;
	var promotePrice = document.getElementById("promotePrice").value;
	
	if(orderId == null || orderId == ""){
	  	$.messager.alert('提示', "订单id不能为空！", 'info');
     	return false;
  	}
   	if(goodsModel == null || goodsModel == ""){
     	$.messager.alert('提示', "商品型号不能为空！", 'info');
     	return false;
    }
    if(catNameZh == null || catNameZh == ""){
      	$.messager.alert('提示', "分类中文名不能为空！", 'info');
      	return false;
    }
    
	if(catNameEn == null || catNameEn == ""){
	  	$.messager.alert('提示', "分类英文名不能为空！", 'info');
     	return false;
  	}
   	if(sellerName == null || sellerName == ""){
     	$.messager.alert('提示', "商家名称不能为空！", 'info');
     	return false;
    }
    if(brandNameZh == null || brandNameZh == ""){
      	$.messager.alert('提示', "品牌中文名不能为空！", 'info');
      	return false;
    }
    
	if(brandNameEn == null || brandNameEn == ""){
	  	$.messager.alert('提示', "品牌英文名不能为空！", 'info');
     	return false;
  	}
   	if(isInStock == null || isInStock == ""){
     	$.messager.alert('提示', "是否现货不能为空！", 'info');
     	return false;
    }
   	
   	if(num == null || num == ""){
	  	$.messager.alert('提示', "数量不能为空！", 'info');
     	return false;
  	}
   	if(normalPrice == null || normalPrice == ""){
     	$.messager.alert('提示', "原价不能为空！", 'info');
     	return false;
    }
    if(promotePrice == null || promotePrice == ""){
      	$.messager.alert('提示', "促销价不能为空！", 'info');
      	return false;
    }
     
    return true;
}

</script>

<jsp:include page="../footer.jsp"/>