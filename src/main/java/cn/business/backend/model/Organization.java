package cn.business.backend.model;

import java.io.Serializable;
import java.util.Date;

/**
 * model: 用户所属企业
 * @author pridewu
 * @date 2016-5-16
 */
public class Organization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7713618240475531894L;
	
	//id
	private int id;
	
	//企业名字
	private String name;
	
	//简介
	private String shortName;
	
	//国家
	private String country;
	
	//省份
	private String province;
	
	//城市
	private String city;
	
	//区域
	private String district;
	
	//详细地址
	private String address;
	
	//业务模型
	private String businessModel;
	
	//联系人
	private String contacts;
	
	//联系电话
	private String phone;
	
	//三证合一
	private String qualification0;

	//营业执照
	private String qualification1;
	
	//组织机构代码
	private String qualification2;
	
	//税务登记证
	private String qualification3;

	//开户许可证
	private String qualification4;
	
	//企业认证授权书
	private String qualification5;
	
	//代理资格证
	private String qualification6;
	
	//状态：0-禁用，1-启用
	private int status;
	
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQualification0() {
		return qualification0;
	}

	public void setQualification0(String qualification0) {
		this.qualification0 = qualification0;
	}

	public String getQualification1() {
		return qualification1;
	}

	public void setQualification1(String qualification1) {
		this.qualification1 = qualification1;
	}

	public String getQualification2() {
		return qualification2;
	}

	public void setQualification2(String qualification2) {
		this.qualification2 = qualification2;
	}

	public String getQualification3() {
		return qualification3;
	}

	public void setQualification3(String qualification3) {
		this.qualification3 = qualification3;
	}

	public String getQualification4() {
		return qualification4;
	}

	public void setQualification4(String qualification4) {
		this.qualification4 = qualification4;
	}

	public String getQualification5() {
		return qualification5;
	}

	public void setQualification5(String qualification5) {
		this.qualification5 = qualification5;
	}

	public String getQualification6() {
		return qualification6;
	}

	public void setQualification6(String qualification6) {
		this.qualification6 = qualification6;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}
