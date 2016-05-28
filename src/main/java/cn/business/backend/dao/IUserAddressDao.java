package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.UserAddress;

/**
 * DAO层：会员地址信息
 * @author pridewu
 * @date 2016-05-15
 */
public interface IUserAddressDao {
	
    
    /**
     * 分页查找所有用户商家表
     * @param param  查询参数集合
     * @return  list
     */
    public List<UserAddress> queryAll(Map<String,Object> params);
    
    /**
     * 查询总记录数
     * @return int
     */
    public int queryCount();
    
    /**
	 * 根据收货人id获取单个收货人信息
	 * @param id 收货人id
	 * @return UserAddress
	 */
    public UserAddress loadById(int id);
    
    /**
	 * 更新收货人地址信息
	 * @param id 收货人id
	 * @return int
	 */
    public int update(UserAddress userAddress);
    
    /**
	 * 添加收货人地址信息
	 * @param id 收货人id
	 * @return int
	 */
    public int save(UserAddress userAddress);
    
    /**
	 * 删除收货人地址信息
	 * @param id 收货人id
	 * @return int
	 */
    public int delete(int id);
    
}
