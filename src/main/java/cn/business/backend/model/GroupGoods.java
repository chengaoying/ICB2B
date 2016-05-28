package cn.business.backend.model;

import java.util.Date;

public class GroupGoods {
	/**
	 * 表：t_group_goods
	 * 字段：goods_id
	 * 注释：商品id
	 */
	private Integer goodsId;

	/**
	 * 表：t_group_goods
	 * 字段：promote_price
	 * 注释：促销价格
	 */
	private Float promotePrice;

	/**
	 * 表：t_group_goods
	 * 字段：max_count
	 * 注释：最多可购数量
	 */
	private Integer maxCount;

	/**
	 * 表：t_group_goods
	 * 字段：min_count
	 * 注释：最低起购数量
	 */
	private Integer minCount;

	/**
	 * 表：t_group_goods
	 * 字段：start_date
	 * 注释：拼购开始时间
	 */
	private Date startDate;

	/**
	 * 表：t_group_goods
	 * 字段：end_date
	 * 注释：拼购截至时间
	 */
	private Date endDate;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Float getPromotePrice() {
		return promotePrice;
	}

	public void setPromotePrice(Float promotePrice) {
		this.promotePrice = promotePrice;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getMinCount() {
		return minCount;
	}

	public void setMinCount(Integer minCount) {
		this.minCount = minCount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}