package cn.business.backend.model;

import java.io.Serializable;

public class UserSeller implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7340424957942607168L;
	
	
	//id
	private int id;
	
	//用户id
	private int userId;
	
	//商家id
	private int sellerId;
	
	//商家名字
	private String sellerName;
	
	//业务模式
	private String model;
	
	//地区
	private String region;

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

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	
}
