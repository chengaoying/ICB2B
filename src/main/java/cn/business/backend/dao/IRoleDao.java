package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.Role;

/**
 * DAO层：角色
 * @author JC
 * @date 2016-3-10
 */
public interface IRoleDao {
	
	/**
	 * 根据角色id获取角色
	 * @param id 角色id
	 * @return Role
	 */
    public Role loadById(int id);
    
    
    /**
     * 更新角色信息
     * @param Role
     */
    public void update(Role Role);
    
    /**
     * 保存角色信息
     * @param Role
     */
    public void save(Role Role);
    
    /**
     * 分页查找所有角色
     * @param param  查询参数集合
     * @return  list
     */
    public List<Role> queryAll(Map<String,Object> params);
    
    /**
     * 查询总记录数
     * @return
     */
    public int queryCount();

    /**
     * 删除角色
     * @param id
     */
    public void delete(int id);
}
