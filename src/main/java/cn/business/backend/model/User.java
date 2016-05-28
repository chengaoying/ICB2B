package cn.business.backend.model;

import java.io.Serializable;
import java.util.List;

/**
 * model: 用户
 * @author CGY
 * @date 2016-3-10
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7713618240475531894L;

	private int id;
	
	/**
	 * 用户名
	 */
	private String name;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 用户所属企业
	 */
	private Organization org;
	
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 第三方登入帐号
	 */
	private String otherAccount;
	
	/**
	 * 用户状态（-1-未激活，1-）
	 */
	private byte status;
	
	/**
	 * 用户等级
	 */
	private byte level;
	
	/**
	 * 用户积分
	 */
	private int point;
	
	/**
	 * 创建时间
	 */
	private String addTime;
	
	/**
	 * 用户密码
	 */
	private String password;
	
	/**
	 * 角色列表
	 */
	private List<Role> roles;
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public byte getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
