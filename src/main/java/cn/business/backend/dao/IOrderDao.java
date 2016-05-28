package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.Order;

/**
 * DAO层：订单信息
 * @author pridewu
 * @date 2016-05-21
 */
public interface IOrderDao {
    
    /**
     * 订单信息
     * @param param  查询参数集合
     * @return  list
     */
    public List<Order> queryAll(Map<String,Object> params);
    
    /**
     * 查询总记录数
     * @return int
     */
    public int queryCount();
    
    /**
	 * 根据订单id获取订单信息
	 * @param id 订单id
	 * @return Order
	 */
    public Order loadById(int id);
    
    /**
	 * 更新订单信息
	 * @param id 订单id
	 * @return int
	 */
    public int update(Order order);
    
    /**
	 * 添加订单信息
	 * @param id 订单id
	 * @return int
	 */
    public int save(Order order);
    
    /**
	 * 删除订单信息
	 * @param id 订单id
	 * @return int
	 */
    public int delete(int id);
    
}
