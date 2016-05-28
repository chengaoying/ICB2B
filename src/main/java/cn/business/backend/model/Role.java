package cn.business.backend.model;

import java.io.Serializable;

/**
 * model: 角色
 * @author CGY
 * @date 2016-3-10
 */
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7713618240475531894L;

	private int id;
	
	/**
	 * 角色名（中文）
	 */
	private String nameZh;
	
	/**
	 * 角色名（英文）
	 */
	private String nameEn;
	
	/**
	 * 权限集合
	 */
	private String auth;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameZh() {
		return nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	
}
