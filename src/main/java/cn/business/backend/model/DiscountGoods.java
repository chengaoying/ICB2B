package cn.business.backend.model;

import java.util.Date;

public class DiscountGoods {
	/**
	 * 表：t_discount_goods
	 * 字段：goods_id
	 * 注释：商品id
	 */
	private Integer goodsId;

	/**
	 * 表：t_discount_goods
	 * 字段：discount_price
	 * 注释：促销价格
	 */
	private Float discountPrice;

	/**
	 * 表：t_discount_goods
	 * 字段：discount_start_date
	 * 注释：特卖开始时间
	 */
	private Date discountStartDate;

	/**
	 * 表：t_discount_goods
	 * 字段：discount_end_date
	 * 注释：特卖结束时间
	 */
	private Date discountEndDate;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Date getDiscountStartDate() {
		return discountStartDate;
	}

	public void setDiscountStartDate(Date discountStartDate) {
		this.discountStartDate = discountStartDate;
	}

	public Date getDiscountEndDate() {
		return discountEndDate;
	}

	public void setDiscountEndDate(Date discountEndDate) {
		this.discountEndDate = discountEndDate;
	}
}