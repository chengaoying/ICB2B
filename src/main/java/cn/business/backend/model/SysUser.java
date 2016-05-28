package cn.business.backend.model;

import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5100979053764565680L;

	//用户id
	private int id;
	
	//用户账户
	private String account;
	
	//用户名
	private String name;
	
	//用户
	private String password;
	
	//用户所属角色
	private SysRole role;
	
	//状态：0-禁用，1-启用
	private int status;
	
	//最近登入时间
	private Date lastTime;
	
	//访问IP
	private String lastIp;
	
	//创建时间
	private Date addTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	@Override
	public String toString() {
		String str = "SysUser [id:"+this.id+",account:"+this.account+",name:"+this.name+",password:"+this.password+"]";
		return str;
	}
	
	
	
}
