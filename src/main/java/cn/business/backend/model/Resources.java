package cn.business.backend.model;

import java.io.Serializable;

/**
 * model: 资源
 * @author CGY
 * @date 2016-3-10
 */
public class Resources implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7713618240475531894L;

	private int id;
	
	/**
	 * 资源中文名
	 */
	private String nameZh;
	
	/**
	 * 资源英文名
	 */
	private String nameEn;
	
	/**
	 * 访问地址
	 */
	private String url;
	
	/**
	 * 资源类型，1-菜单
	 */
	private byte type;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

}
