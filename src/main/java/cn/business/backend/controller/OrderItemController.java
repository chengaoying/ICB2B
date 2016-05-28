package cn.business.backend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.OrderItem;
import cn.business.backend.service.OrderItemService;
import cn.core.framework.common.Configuration;

/**
 * 控制器：订单项信息管理
 * @author pridewu
 * @date 2016-05-26
 */
@Controller
public class OrderItemController extends CommonController {
	
	protected static Log log = LogFactory.getLog(OrderItemController.class);
	
	@Resource
	private OrderItemService orderItemService = null;
	
	/**
	 * 订单项地址页
	 * @return
	 */
	@RequestMapping(value="orderItem/index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "userAddress"));
		return getModelView("orderItem/index",map);
	}
	
	/**
	 * 获取查询数据
	 * @return list
	 */
	@RequestMapping(value="orderItem/loadData")
	public @ResponseBody Map<String,Object> loadData(@RequestParam int rows,@RequestParam int page){
		
		//分页查询起始记录数
		int offset = (page-1) * rows; 
		
		//总记录数
		int total = orderItemService.queryCount();
		
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("orderId", request.getParameter("orderId"));
		params.put("goodsModel", request.getParameter("goodsModel"));
		params.put("offset", offset);
		params.put("limit", rows);
		
		//当前页的记录数
		List<OrderItem> orderItem = orderItemService.queryAll(params);
		return this.getPageMap(total, orderItem);
	}
	
	/**
	 * 添加/编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = { "orderItem/add", "orderItem/edit" }, method = RequestMethod.GET)
	public ModelAndView addOrEdit(@RequestParam(value = "id", required = false) Integer id) {
		Map<String, Object> map = new HashMap<>();
		
		if (request.getServletPath().indexOf("/edit") != -1) {
			OrderItem orderItem = orderItemService.loadById(id);
			map.put("orderItem", orderItem);
			return this.getModelView("orderItem/edit", map);
		} else {
			return this.getModelView("orderItem/add", map);
		}
	}

	
	/**
	 * 添加/更新处理
	 * @param orderItem
	 * @return
	 */
	@RequestMapping(value={"orderItem/add","orderItem/edit"},method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrEdit(@ModelAttribute("orderItem") OrderItem orderItem)
	{
		//保存或更新
		if(request.getServletPath().indexOf("/edit") != -1)
		{
			orderItemService.update(orderItem);
		}
		else
		{
			orderItem.setDeliveryDate(new Date());
			orderItemService.save(orderItem);
		}
		
		return this.getResultMap(1, "操作成功", null);
	}
	
	/**
	 * 删除地址信息
	 * @return
	 */
	@RequestMapping(value="orderItem/del")
	public @ResponseBody Map<String,Object> del(@RequestParam int id){
		orderItemService.delete(id);
		return this.getResultMap(1, "操作成功", null);
	}
}
