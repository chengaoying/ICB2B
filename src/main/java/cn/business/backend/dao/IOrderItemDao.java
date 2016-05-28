package cn.business.backend.dao;

import java.util.List;
import java.util.Map;

import cn.business.backend.model.OrderItem;

/**
 * DAO层：订单项信息
 * @author pridewu
 * @date 2016-05-26
 */
public interface IOrderItemDao {
    
    /**
     * 订单项信息
     * @param param  查询参数集合
     * @return  list
     */
    public List<OrderItem> queryAll(Map<String,Object> params);
    
    /**
     * 查询总记录数
     * @return int
     */
    public int queryCount();
    
    /**
	 * 根据订单项id获取订单项信息
	 * @param id 订单项id
	 * @return OrderItem
	 */
    public OrderItem loadById(int id);
    
    /**
	 * 更新订单项信息
	 * @param id 订单项id
	 * @return int
	 */
    public int update(OrderItem orderItem);
    
    /**
	 * 添加订单项信息
	 * @param id 订单项id
	 * @return int
	 */
    public int save(OrderItem orderItem);
    
    /**
	 * 删除订单项信息
	 * @param id 订单项id
	 * @return int
	 */
    public int delete(int id);
    
}
