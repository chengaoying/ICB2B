package cn.business.backend.model;

import java.io.Serializable;

public class UserAddress implements Serializable {

	private static final long serialVersionUID = -7502501025596243043L;

	//id
	private int id;
	
	//用户id
	private int userId;
	
	//联系人
	private String contacts;
	
	//联系电话
	private String tel;
	
	//手机号
	private String mobile;
	
	//国家
	private int country;
	
	//省份
	private int province;
	
	//城市
	private int city;
	
	//地区
	private int district;
	
	//地址
	private int address;
	
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

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getProvince() {
		return province;
	}

	public void setProvince(int province) {
		this.province = province;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getDistrict() {
		return district;
	}

	public void setDistrict(int district) {
		this.district = district;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

}
