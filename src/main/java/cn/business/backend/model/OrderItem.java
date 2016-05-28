package cn.business.backend.model;

import java.io.Serializable;
import java.util.Date;

/**
 * model: 订单项表
 * @author pridewu
 * @date 2016-5-26
 */
public class OrderItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9158151531206759318L;

	//id
	private int id;
	
	//订单id
	private int orderId;
	
	//商品型号
	private String goodsModel;
	
	//分类中文名
	private String catNameZh;
	
	//分类英文名
	private String catNameEn;
	
	//商家名称
	private String sellerName;
	
	//品牌中文名
	private String brandNameZh;
	
	//品牌英文名
	private String brandNameEn;
	
	//是否现货(1-现货，-1-期货)
	private int isInStock;
	
	//数量
	private int num;
	
	//原价
	private float normalPrice;
	
	//促销价
	private float promotePrice;
	
	//交货日期
	private Date deliveryDate;
	
	//物流类型
	private int deliveryType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getGoodsModel() {
		return goodsModel;
	}

	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}

	public String getCatNameZh() {
		return catNameZh;
	}

	public void setCatNameZh(String catNameZh) {
		this.catNameZh = catNameZh;
	}

	public String getCatNameEn() {
		return catNameEn;
	}

	public void setCatNameEn(String catNameEn) {
		this.catNameEn = catNameEn;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getBrandNameZh() {
		return brandNameZh;
	}

	public void setBrandNameZh(String brandNameZh) {
		this.brandNameZh = brandNameZh;
	}

	public String getBrandNameEn() {
		return brandNameEn;
	}

	public void setBrandNameEn(String brandNameEn) {
		this.brandNameEn = brandNameEn;
	}

	public int getIsInStock() {
		return isInStock;
	}

	public void setIsInStock(int isInStock) {
		this.isInStock = isInStock;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public float getNormalPrice() {
		return normalPrice;
	}

	public void setNormalPrice(float normalPrice) {
		this.normalPrice = normalPrice;
	}

	public float getPromotePrice() {
		return promotePrice;
	}

	public void setPromotePrice(float promotePrice) {
		this.promotePrice = promotePrice;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(int deliveryType) {
		this.deliveryType = deliveryType;
	}
	
}
