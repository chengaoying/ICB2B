package cn.business.backend.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 模型：系统角色
 * @author JC
 * @date 2015-8-1
 */
public class SysRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3097966963596975256L;

	//角色id
	private int id;
	
	//角色名称
	private String name;
	
	//角色权限
	private String auth;
	
	//是否是超级管理员：0-不是，1-是
	private short isSuper;
	
	//创建时间
	private Date addTime;
	
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
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public short getIsSuper() {
		return isSuper;
	}
	public void setIsSuper(short isSuper) {
		this.isSuper = isSuper;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	@Override
	public String toString() {
		String str = "SysRole: [id:"+this.id+",name:"+this.name+",auth:"+this.auth+",isSuper:"+this.isSuper+",addTime:"+this.addTime+"]";
		return str;
	}
	
}
