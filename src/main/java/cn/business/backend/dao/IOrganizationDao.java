package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.Organization;

/**
 * DAO层：企业信息
 * @author pridewu
 * @date 2016-05-15
 */
public interface IOrganizationDao {
    
    /**
     * 企业信息
     * @param param  查询参数集合
     * @return  list
     */
    public List<Organization> queryAll(Map<String,Object> params);
    
    /**
     * 查询总记录数
     * @return int
     */
    public int queryCount();
    
    /**
	 * 根据用户id获取企业信息
	 * @param id 用户id
	 * @return Organization
	 */
    public Organization loadById(int id);
    
    /**
	 * 更新企业信息
	 * @param id 用户id
	 * @return int
	 */
    public int update(Organization organization);
    
    /**
	 * 添加企业信息
	 * @param id 用户id
	 * @return int
	 */
    public int save(Organization organization);
    
    /**
	 * 删除企业信息
	 * @param id 用户id
	 * @return int
	 */
    public int delete(int id);
    
}
