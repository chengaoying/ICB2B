package cn.business.backend.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.business.backend.dao.IUserAddressDao;
import cn.business.backend.model.UserAddress;

/**
 * 服务类：会员地址信息
 * @author pridewu
 * @date 2016-05-15
 */
@Service("UserAddressService")
public class UserAddressService{

	@Resource  
    private IUserAddressDao userAddressDao; 
	
	public List<UserAddress> queryAll(Map<String,Object> params) {
		return userAddressDao.queryAll(params);
	}
	
	public int queryCount() {
		return userAddressDao.queryCount();
	}
	
	public UserAddress loadById(int id) {
		return userAddressDao.loadById(id);
	}
	
	public int update(UserAddress userAddress) {
		return userAddressDao.update(userAddress);
	}
	
	public int save(UserAddress userAddress) {
		return userAddressDao.save(userAddress);
	}
	
	public int delete(int id) {
		return userAddressDao.delete(id);
	}
}
