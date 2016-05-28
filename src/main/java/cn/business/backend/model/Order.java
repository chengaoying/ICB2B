package cn.business.backend.model;

import java.io.Serializable;
import java.util.Date;

/**
 * model: 订单表
 * @author pridewu
 * @date 2016-5-21
 */
public class Order implements Serializable{

	private static final long serialVersionUID = 4781431766328423660L;

	//id
	private int id;
	
	//采购商id
	private int buyerId;
	
	//销售商id
	private int sellerId;
	
	//订单编号
	private String code;
	
	//总金额
	private float amount;
	
	//联系人
	private String contacts;
	
	//联系电话
	private String tel;
	
	//收货地址
	private String address;
	
	//状态
	private int status;
	
	//类型
	private int type;
		
	//付款凭证
	private String certificate1;
	
	//发货凭证
	private String certificate2;
	
	//下单时间
	private Date addTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCertificate1() {
		return certificate1;
	}

	public void setCertificate1(String certificate1) {
		this.certificate1 = certificate1;
	}

	public String getCertificate2() {
		return certificate2;
	}

	public void setCertificate2(String certificate2) {
		this.certificate2 = certificate2;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
}
