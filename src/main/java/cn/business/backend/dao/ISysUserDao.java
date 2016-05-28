package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.SysUser;

/**
 * DAO层：系统用户
 * @author JC
 * @date 2015-8-1
 */
public interface ISysUserDao {
	
	/**
	 * 根据用户id获取系统用户
	 * @param id 用户id
	 * @return SysUser
	 */
    public SysUser loadById(int id);
    
    /**
     * 根据用户账号获取系统用户
     * @param account 用户账户
     * @return SysUser
     */
    public SysUser loadByAccount(String account);
    
    /**
     * 更新系统用户信息
     * @param sysUser
     */
    public void update(SysUser sysUser);
    
    /**
     * 保存系统用户信息
     * @param sysUser
     */
    public void save(SysUser sysUser);
    
    /**
     * 删除系统用户
     * @param id
     */
    public void delete(int id);
    
    /**
     * 分页查找所有用户
     * @param param  查询参数集合
     * @return  list
     */
    public List<SysUser> queryAll(Map<String,Object> params);
    
    /**
     * 查询总记录数
     * @return
     */
    public int queryCount();

}
