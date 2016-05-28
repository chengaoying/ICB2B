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

import cn.business.backend.model.Order;
import cn.business.backend.service.OrderService;
import cn.core.framework.common.Configuration;

/**
 * 控制器：订单信息管理
 * @author pridewu
 * @date 2016-05-16
 */
@Controller
public class OrderController extends CommonController {
	
	protected static Log log = LogFactory.getLog(OrderController.class);
	
	@Resource
	private OrderService orderService = null;
	
	/**
	 * 订单地址页
	 * @return
	 */
	@RequestMapping(value="order/index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "userAddress"));
		return getModelView("order/index",map);
	}
	
	/**
	 * 获取查询数据
	 * @return list
	 */
	@RequestMapping(value="order/loadData")
	public @ResponseBody Map<String,Object> loadData(@RequestParam int rows,@RequestParam int page){
		
		//分页查询起始记录数
		int offset = (page-1) * rows; 
		
		//总记录数
		int total = orderService.queryCount();
		
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("buyerId", request.getParameter("buyerId"));
		params.put("sellerId", request.getParameter("sellerId"));
		params.put("offset", offset);
		params.put("limit", rows);
		
		//当前页的记录数
		List<Order> userList = orderService.queryAll(params);
		return this.getPageMap(total, userList);
	}
	
	/**
	 * 添加/编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = { "order/add", "order/edit" }, method = RequestMethod.GET)
	public ModelAndView addOrEdit(@RequestParam(value = "id", required = false) Integer id) {
		Map<String, Object> map = new HashMap<>();
		
		if (request.getServletPath().indexOf("/edit") != -1) {
			Order order = orderService.loadById(id);
			map.put("order", order);
			return this.getModelView("order/edit", map);
		} else {
			return this.getModelView("order/add", map);
		}
	}

	
	/**
	 * 添加/更新处理
	 * @param order
	 * @return
	 */
	@RequestMapping(value={"order/add","order/edit"},method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrEdit(@ModelAttribute("order") Order order)
	{
		//保存或更新
		if(request.getServletPath().indexOf("/edit") != -1)
		{
			orderService.update(order);
		}
		else
		{
			order.setAddTime(new Date());
			orderService.save(order);
		}
		
		return this.getResultMap(1, "操作成功", null);
	}
	
	/**
	 * 删除地址信息
	 * @return
	 */
	@RequestMapping(value="order/del")
	public @ResponseBody Map<String,Object> del(@RequestParam int id){
		orderService.delete(id);
		return this.getResultMap(1, "操作成功", null);
	}
}
