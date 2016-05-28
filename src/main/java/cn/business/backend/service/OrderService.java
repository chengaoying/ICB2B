package cn.business.backend.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.business.backend.dao.IOrderDao;
import cn.business.backend.model.Order;

/**
 * 服务类：订单信息
 * @author pridewu
 * @date 2016-05-21
 */
@Service("OrderService")
public class OrderService{

	@Resource  
    private IOrderDao orderDao; 
	
	public List<Order> queryAll(Map<String,Object> params) {
		return orderDao.queryAll(params);
	}
	
	public int queryCount() {
		return orderDao.queryCount();
	}
	
	public Order loadById(int id) {
		return orderDao.loadById(id);
	}
	
	public int update(Order organization) {
		return orderDao.update(organization);
	}
	
	public int save(Order organization) {
		return orderDao.save(organization);
	}
	
	public int delete(int id) {
		return orderDao.delete(id);
	}
}
