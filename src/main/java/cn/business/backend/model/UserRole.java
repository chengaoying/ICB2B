package cn.business.backend.model;

import java.io.Serializable;

/**
 * model: 用户-角色
 * @author CGY
 * @date 2016-3-10
 */
public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7713618240475531894L;

	private int id;
	
	private int userId;
	
	private int roleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
