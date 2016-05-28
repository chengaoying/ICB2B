package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.GoodsProperty;

/**
 * Dao:商品属性
 * @author TZ
 * @data 2016年5月26日
 */
public interface IGoodsPropertyDao {
	/**
	 * 根据商品属性id获取商品属性
	 * @param id 商品属性id
	 * @return GoodsProperty
	 */
	public GoodsProperty loadById(int id);

	/**
	 * 更新商品属性
	 * @param GoodsProperty
	 */
	public void update(GoodsProperty goodsProperty);

	/**
	 * 保存商品属性
	 * @param GoodsProperty
	 */
	public void save(GoodsProperty goodsProperty);

	/**
	 * 分页查找所有资源
	 * @param param  查询参数集合
	 * @return  list
	 */
	public List<GoodsProperty> queryAll(Map<String, Object> params);

	/**
	 * 查询总记录数
	 * @return
	 */
	public int queryCount();

	/**
	 * 删除商品属性
	 * @param id
	 */
	public void delete(int id);
}