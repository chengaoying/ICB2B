package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.GroupGoods;

/**
 * Dao:拼购商品
 * @author TZ
 * @data 2016年5月20日
 */
public interface IGroupGoodsDao {

	/**
	 * 根据拼购商品id获取拼购商品
	 * @param id 拼购商品id
	 * @return GroupGoods
	 */
	public GroupGoods loadById(int id);

	/**
	 * 更新拼购商品
	 * @param GroupGoods
	 */
	public void update(GroupGoods goGroupGoods);

	/**
	 * 保存拼购商品
	 * @param GroupGoods
	 */
	public void save(GroupGoods goGroupGoods);

	/**
	 * 分页查找所有资源
	 * @param param  查询参数集合
	 * @return  list
	 */
	public List<GroupGoods> queryAll(Map<String, Object> params);

	/**
	 * 查询总记录数
	 * @return
	 */
	public int queryCount();

	/**
	 * 删除拼购商品
	 * @param id
	 */
	public void delete(int id);
}