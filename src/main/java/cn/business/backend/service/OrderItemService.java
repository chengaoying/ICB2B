package cn.business.backend.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.business.backend.dao.IOrderItemDao;
import cn.business.backend.model.OrderItem;

/**
 * 服务类：订单项信息
 * @author pridewu
 * @date 2016-05-26
 */
@Service("OrderItemService")
public class OrderItemService{

	@Resource  
    private IOrderItemDao orderItemDao; 
	
	public List<OrderItem> queryAll(Map<String,Object> params) {
		return orderItemDao.queryAll(params);
	}
	
	public int queryCount() {
		return orderItemDao.queryCount();
	}
	
	public OrderItem loadById(int id) {
		return orderItemDao.loadById(id);
	}
	
	public int update(OrderItem organization) {
		return orderItemDao.update(organization);
	}
	
	public int save(OrderItem organization) {
		return orderItemDao.save(organization);
	}
	
	public int delete(int id) {
		return orderItemDao.delete(id);
	}
}
