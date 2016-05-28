package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.User;
import cn.business.backend.model.UserRole;

/**
 * DAO层：用户
 * @author JC
 * @date 2016-3-10
 */
public interface IUserDao {
	
	/**
	 * 根据用户id获取用户
	 * @param id 用户id
	 * @return User
	 */
    public User loadById(int id);
    
    /**
     * 根据用户名获取用户
     * @param name 用户名
     * @return User
     */
    public User loadByName(String name);
    
    /**
     * 更新用户信息
     * @param User
     */
    public void update(User User);
    
    /**
     * 保存用户信息
     * @param User
     */
    public void save(User User);
    
    /**
     * 保存用户-角色关联信息
     * @param userRole
     */
    public void saveUserRole(UserRole userRole);
    
    /**
     * 分页查找所有用户
     * @param param  查询参数集合
     * @return  list
     */
    public List<User> queryAll(Map<String,Object> params);
    
    /**
     * 查询总记录数
     * @return
     */
    public int queryCount();

    /**
     * 删除用户和角色之间的关联
     * @param params
     */
    public void delUserRole(Map<String,Object> params);
}
