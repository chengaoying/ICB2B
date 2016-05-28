package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.Brand;

/**
 * Dao:商品品牌
 * @author TZ
 * @data 2016年5月15日
 */
public interface IBrandDao {

	/**
	 * 根据资源id获取品牌
	 * @param id 品牌id
	 * @return Brand
	 */
	public Brand loadById(int id);

	/**
	 * 更新资源信息
	 * @param Brand
	 */
	public void update(Brand brand);

	/**
	 * 保存资源信息
	 * @param Brand
	 */
	public void save(Brand brand);

	/**
	 * 分页查找所有资源
	 * @param param  查询参数集合
	 * @return  list
	 */
	public List<Brand> queryAll(Map<String, Object> params);

	/**
	 * 查询总记录数
	 * @return
	 */
	public int queryCount();

	/**
	 * 删除品牌
	 * @param id
	 */
	public void delete(int id);
}