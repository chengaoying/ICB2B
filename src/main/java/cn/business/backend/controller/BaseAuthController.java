package cn.business.backend.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import cn.business.backend.model.SysRole;
import cn.core.framework.common.Constant;
import cn.core.framework.common.SystemMenuXmlParser;
import cn.core.framework.controller.BaseController;
import cn.core.framework.model.Menu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 系统权限处理控制器
 * @author JC
 * @date 2015-8-1
 */
public class BaseAuthController extends BaseController{

	/**
	 * 判断是否有操作权限
	 * @param sysRole	角色
	 * @param action	当前访问的控制器
	 * @param method	访问的方法
	 * @return
	 */
	protected boolean isActionAuth(SysRole sysRole,String action,String method){
		Map<String,String> auths = this.buttonOptationAuthMap(sysRole, action);
		
		//访问的方法没有在权限集合中，有可能是异步加载数据操作，不做权限处理
		if(auths.get(method) == null)	
			return true;
		
		//权限判断：反问操作是否可访问
		int val = Integer.parseInt(auths.get(method));
		if(val == Constant.CONST_RES_ACCESS_AUTH_YES) 
			return true;
		
		return false;
	}
	
	/**
	 * 当前访问控制器中的操作权限集合
	 * @param sysRole	角色
	 * @param action	当前控制器
	 * @return
	 */
	protected Map<String,String> buttonOptationAuthMap(SysRole sysRole,String action){
		Map<String,String> map = new HashMap<>();
		Menu menu = this.getSubMenusByAction(sysRole, action);
		if(menu == null) return null;
		
		Map<String,String> properties = menu.getProperty();
		for(Map.Entry<String,String> entry : properties.entrySet()){
			String s[] = entry.getValue().split("_");
			map.put(s[0], s[1]);
		}
		return map;
	}
	
	/**
	 * 获取对应控制器对应的菜单
	 * @param sysUser	当前用户
	 * @param action	当前控制器
	 * @return
	 */
	private Menu getSubMenusByAction(SysRole sysRole,String action){
		ArrayList<Menu> menus = this.initMenusResource(sysRole);
		for(int i=0;i<menus.size();i++){
			ArrayList<Menu> subMenus= menus.get(i).getSubMenus();
			for(Menu subMenu:subMenus){
				if(subMenu.getAction().equals(action))
					return subMenu;
			}
		}
		return null;
	}
	
	/**
	 * 加载菜单资源，并设置角色的访问权限
	 * @param sysRole
	 * @return menus  菜单资源集合
	 */
	protected ArrayList<Menu> initMenusResource(SysRole sysRole){
		ArrayList<Menu> menus = SystemMenuXmlParser.menus;
		
		boolean isSuper = false;
		if(sysRole.getIsSuper() == Constant.CONST_SYS_SUPER_USER)
			isSuper = true;
		
		//用户菜单权限
		try {
			ObjectMapper om = new ObjectMapper();
			JsonNode node = null;
			if(!isSuper) node = om.readValue(sysRole.getAuth(), JsonNode.class);
			
			//标记操作(没有权限的菜单和操作属性进行标记)
			for(int i=0;i<menus.size();i++){
				Menu menu = menus.get(i); //一级菜单
				ArrayList<Menu> subMenus = menu.getSubMenus();//子菜单
				int num = 0;//没有访问权限的子菜单个数（用于控制一级菜单权限，如果一级菜单下的所有子菜单都不能访问，则把一级菜单权限设为不可访问）
				
				for(int j=0;j<subMenus.size();j++){
					
					//1.检查是否有子菜单的默认方法(查看)权限，对没有权限的二级菜单进行标记
					Menu subMenu = subMenus.get(j);
					String defaultMethod = subMenu.getAction()+"_"+subMenu.getDefaultMethod();
					if(isSuper || node.has(defaultMethod)){
						subMenu.setAuth(Constant.CONST_RES_ACCESS_AUTH_YES);
					}else{
						subMenu.setAuth(Constant.CONST_RES_ACCESS_AUTH_NO);
						num ++;
					}
					
					//2.检查该子菜单的其他操作权限,对没有权限的操作进行标记
					Map<String,String> property = subMenu.getProperty();
					for (Map.Entry<String,String> entry : property.entrySet()) {  
						String[] s = entry.getValue().split("_");
						String prop = subMenu.getAction()+"_"+s[0];
						if(isSuper || node.has(prop))
							entry.setValue(s[0]+"_"+Constant.CONST_RES_ACCESS_AUTH_YES);
						else
							entry.setValue(s[0]+"_"+Constant.CONST_RES_ACCESS_AUTH_NO);
					}
				}
				
				//3.设置一级菜单的访问权限
				if(num < subMenus.size())
					menu.setAuth(Constant.CONST_RES_ACCESS_AUTH_YES);
				else
					menu.setAuth(Constant.CONST_RES_ACCESS_AUTH_NO);
			}
		} catch (IOException e) {
			//TODO
			e.printStackTrace();
		} 
		return menus;
	}
}
