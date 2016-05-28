package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.DiscountGoods;

/**
 * Dao:特卖商品
 * @author TZ
 * @data 2016年5月20日
 */
public interface IDiscountGoodsDao {

	/**
	 * 根据特卖商品id获取特卖商品
	 * @param id 特卖商品id
	 * @return DiscountGoods
	 */
	public DiscountGoods loadById(int id);

	/**
	 * 更新特卖商品
	 * @param DiscountGoods
	 */
	public void update(DiscountGoods discountGoods);

	/**
	 * 保存特卖商品
	 * @param DiscountGoods
	 */
	public void save(DiscountGoods discountGoods);

	/**
	 * 分页查找所有特卖商品
	 * @param param  查询参数集合
	 * @return  list
	 */
	public List<DiscountGoods> queryAll(Map<String, Object> params);

	/**
	 * 查询总记录数
	 * @return
	 */
	public int queryCount();

	/**
	 * 删除特卖商品
	 * @param id
	 */
	public void delete(int id);
}