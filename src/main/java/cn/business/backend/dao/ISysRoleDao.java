package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.SysRole;

/**
 * DAO层：系统角色
 * @author JC
 * @date 2015-8-1
 */
public interface ISysRoleDao {
	
	/**
	 * 根据角色id获取系统角色
	 * @param id 角色id
	 * @return SysRole
	 */
    public SysRole loadById(int id);
    
    
    /**
     * 更新系统角色信息
     * @param sysRole
     */
    public void update(SysRole sysRole);
    
    /**
     * 保存系统角色信息
     * @param sysRole
     */
    public void save(SysRole sysRole);
    
    /**
     * 删除系统角色
     * @param id
     */
    public void delete(int id);
    
    /**
     * 分页查找所有角色
     * @param param  查询参数集合
     * @return  list
     */
    public List<SysRole> queryAll(Map<String,Object> params);
    
    /**
     * 查询总记录数
     * @return
     */
    public int queryCount();

}
