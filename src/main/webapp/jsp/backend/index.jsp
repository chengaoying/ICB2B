<jsp:include page="header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
   
<!-- 头部 -->    
<div data-options="region:'north',border:false" style="height:60px;background:#eee;padding:6px">
	<div style="float:left; font-size:32px;">屏芯网平台管理系统</div>
	<div style="float:right;  margin-right:10px;">
		<div style="text-align:right;margin-bottom:5px;">您好，${userName} [${roleName}] 欢迎您！</div>
		<!-- <a href="javascript:;" class="easyui-menubutton" data-options="menu:'#menu_skin'">更换皮肤</a> -->    
		<!-- <a href="javascript:;" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="editPwd()">修改密码</a> -->
		<a href="${backend_uri}/logout" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-back'">注销退出</a>
	</div>
</div>

<!-- 导航菜单 -->
<div data-options="region:'west',split:true,title:'导航菜单'" style="width:200px;">
	<div class="easyui-accordion" data-options="fit:true,border:false" style="overflow-y:scroll">	
	<ul id="nav" class="easyui-tree" style="padding:10px;">	
		<c:forEach items="${menus}" var="menu">
			<c:if test="${menu.auth == 1}">
				<li>
					<span>${menu.name}</span>
					<ul>
					<c:forEach items="${menu.subMenus}" var="item">
						<c:if test="${item.auth == 1}">
							<li id="${backend_uri}/${item.action}/${item.defaultMethod}">
								${item.name}
							</li>			
						</c:if>	
					</c:forEach>
					</ul>
				</li>
			</c:if>
		</c:forEach>
	</ul>
	</div>
</div>

<!-- 版权信息 -->	
<div data-options="region:'south',border:false" style="height:30px;background:#eee;padding:5px; text-align:center;">版权所有 (copyright)屏芯网 <a href="http://www.miitbeian.gov.cn/">皖ICP备15019342号-2</a></div>
<!-- 主区域 -->
<div data-options="region:'center'">
	<div id="mt" class="easyui-tabs" data-options="fit:true,border:false">  
   		<div title="首页" style="padding:20px;font-size:18px">欢迎登入</div>
	</div>
</div>

<script type="text/javascript">
$(function(){
	//处理导航菜单
	$("#nav").tree({
		lines: true,
		onClick: function(node){
			if($("#nav").tree('getChildren',node.target)!=''){
				if(node.state=='open'){
					$("#nav").tree('collapse',node.target);
				}else{
					$("#nav").tree('expand',node.target);					
				}
				return;
			}
			if($("#mt").tabs('exists',node.text)){
				$("#mt").tabs('select',node.text);
			}else{			
				$('#mt').tabs('add',{
				    title: node.text,
				    content:'<iframe src="'+ node.id +'" frameborder="no" width="100%" height="100%" />',
				    closable:true,
				    style: 'padding:20px;',
				});
			}
		}
	});
});

</script>

<jsp:include page="footer.jsp"/>