package cn.business.backend.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.business.backend.dao.IUserSellerDao;
import cn.business.backend.model.UserSeller;

/**
 * 服务类：用户商家
 * @author pridewu
 * @date 2016-05-14
 */
@Service("UserSellerService")
public class UserSellerService{

	@Resource  
    private IUserSellerDao userSellerDao; 
	
	public List<UserSeller> queryAll(Map<String,Object> params) {
		return userSellerDao.queryAll(params);
	}
	
	public int queryCount() {
		return userSellerDao.queryCount();
	}
}
