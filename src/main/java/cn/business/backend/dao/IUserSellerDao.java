package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.UserSeller;

/**
 * DAO层：用户商家
 * @author pridewu
 * @date 2016-05-14
 */
public interface IUserSellerDao {
	
    
    /**
     * 分页查找所有用户商家表
     * @param param  查询参数集合
     * @return  list
     */
    public List<UserSeller> queryAll(Map<String,Object> params);
    
    /**
     * 查询总记录数
     * @return
     */
    public int queryCount();
    

}
