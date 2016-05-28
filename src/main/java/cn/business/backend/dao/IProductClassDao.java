package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.Category;

/**
 * Dao层：商品分类
 * @author TZ
 * @data 2016年5月15日
 */
public interface IProductClassDao {
	/**
	 * 根据商品分类id获取分类
	 * @param id 品牌id
	 * @return Category
	 */
	public Category loadById(int id);

	/**
	 * 更新商品分类
	 * @param Category
	 */
	public void update(Category category);

	/**
	 * 保存商品分类
	 * @param Category
	 */
	public void save(Category category);

	/**
	 * 分页查找所有商品分类
	 * @param param  查询参数集合
	 * @return  list
	 */
	public List<Category> queryAll(Map<String, Object> params);

	/**
	 * 分类等级递增查找所有商品分类
	 * @param params
	 * @return
	 */
	public List<Category> levelQueryAll(Map<String, Object> params);

	/**
	 * 查询总记录数
	 * @return
	 */
	public int queryCount();

	/**
	 * 删除商品分类
	 * @param id
	 */
	public void delete(int id);

}
