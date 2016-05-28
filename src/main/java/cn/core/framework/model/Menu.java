package cn.core.framework.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * 系统菜单实体类
 * @author JC
 *
 */
public class Menu {
	
	//菜单id
	private int id;
	
	//父类id
	private int pId;
	
	//菜单名称
	private String name;
	
	//菜单对应的控制器
	private String action;
	
	//菜单操作属性
	private Map<String,String> property;
	
	//子菜单列表
	private ArrayList<Menu> subMenus;
	
	//菜单默认方法
	private String defaultMethod;

	//菜单权限
	private int auth;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Map<String, String> getProperty() {
		return property;
	}
	public void setProperty(Map<String, String> property) {
		this.property = property;
	}
	public ArrayList<Menu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(ArrayList<Menu> subMenus) {
		this.subMenus = subMenus;
	}
	public String getDefaultMethod() {
		return defaultMethod;
	}
	public void setDefaultMethod(String defaultMethod) {
		this.defaultMethod = defaultMethod;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	
}
