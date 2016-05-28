package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.Resources;

/**
 * DAO层：资源
 * @author JC
 * @date 2016-3-10
 */
public interface IResourcesDao {
	
	/**
	 * 根据资源id获取资源
	 * @param id 资源id
	 * @return Resources
	 */
    public Resources loadById(int id);
    
    /**
     * 更新资源信息
     * @param Resources
     */
    public void update(Resources Resource);
    
    /**
     * 保存资源信息
     * @param Resources
     */
    public void save(Resources Resource);
    
    /**
     * 分页查找所有资源
     * @param param  查询参数集合
     * @return  list
     */
    public List<Resources> queryAll(Map<String,Object> params);
    
    /**
     * 查询总记录数
     * @return
     */
    public int queryCount();

    /**
     * 删除资源
     * @param id
     */
    public void delete(int id);
}
