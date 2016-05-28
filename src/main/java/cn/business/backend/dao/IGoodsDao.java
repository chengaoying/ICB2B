package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.Goods;

/**
 * Dao:现货期货管理
 * @author TZ
 * @data 2016年5月26日
 */
public interface IGoodsDao {
	/**
	 * 根据现货期货id获取现货期货
	 * @param id 现货期货id
	 * @return Goods
	 */
	public Goods loadById(int id);

	/**
	 * 更新现货期货
	 * @param Goods
	 */
	public void update(Goods goods);

	/**
	 * 保存现货期货
	 * @param Goods
	 */
	public void save(Goods goods);

	/**
	 * 分页查找所有资源
	 * @param param  查询参数集合
	 * @return  list
	 */
	public List<Goods> queryAll(Map<String, Object> params);

	/**
	 * 查询总记录数
	 * @return
	 */
	public int queryCount();

	/**
	 * 删除现货期货
	 * @param id
	 */
	public void delete(int id);
}