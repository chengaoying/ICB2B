package cn.business.backend.controller;

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

import cn.business.backend.model.User;
import cn.business.backend.model.UserAddress;
import cn.business.backend.service.UserAddressService;
import cn.business.backend.service.UserService;
import cn.core.framework.common.Configuration;

/**
 * 控制器：会员地址信息管理
 * @author pridewu
 * @date 2016-05-15
 */
@Controller
public class UserAddressController extends CommonController {
	
	protected static Log log = LogFactory.getLog(UserSellerController.class);
	
	@Resource
	private UserAddressService userAddressService = null;
	
	@Resource
	private UserService userService = null;
	
	/**
	 * 用户地址页
	 * @return
	 */
	@RequestMapping(value="userAddress/index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "userAddress"));
		return getModelView("userAddress/index",map);
	}
	
	/**
	 * 获取查询数据
	 * @return list
	 */
	@RequestMapping(value="userAddress/loadData")
	public @ResponseBody Map<String,Object> loadData(@RequestParam int rows,@RequestParam int page){
		
		//分页查询起始记录数
		int offset = (page-1) * rows; 
		
		//总记录数
		int total = userAddressService.queryCount();
		
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("user_id", request.getParameter("user_id"));
		params.put("contacts", request.getParameter("contacts"));
		params.put("offset", offset);
		params.put("limit", rows);
		params.put("id", request.getParameter("id"));
		
		//当前页的记录数
		List<UserAddress> userList = userAddressService.queryAll(params);
		return this.getPageMap(total, userList);
	}
	
	/**
	 * 添加/编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = { "userAddress/add", "userAddress/edit" }, method = RequestMethod.GET)
	public ModelAndView addOrEdit(@RequestParam(value = "id", required = false) Integer id) {
		Map<String, Object> map = new HashMap<>();
		Map<String,Object> params = new HashMap<String,Object>();
		//总记录数
		int total = userService.queryCount();
		params.put("offset", 0);//起始记录
		params.put("limit", total);//每页记录数
		List<User> userList = userService.queryAll(params);
		map.put("userList", userList);
		
		if (request.getServletPath().indexOf("/edit") != -1) {
			UserAddress userAddress = userAddressService.loadById(id);
			map.put("userAddress", userAddress);
			return this.getModelView("userAddress/edit", map);
		} else {
			return this.getModelView("userAddress/add", map);
		}
	}

	
	/**
	 * 添加/更新处理
	 * @param userAddress
	 * @return
	 */
	@RequestMapping(value={"userAddress/add","userAddress/edit"},method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrEdit(@ModelAttribute("userAddress") UserAddress userAddress)
	{
		//保存或更新
		if(request.getServletPath().indexOf("/edit") != -1)
		{
			userAddressService.update(userAddress);
		}
		else
		{
			userAddressService.save(userAddress);
		}
		
		return this.getResultMap(1, "操作成功", null);
	}
	
	/**
	 * 删除地址信息
	 * @return
	 */
	@RequestMapping(value="userAddress/del")
	public @ResponseBody Map<String,Object> del(@RequestParam int id){
		userAddressService.delete(id);
		return this.getResultMap(1, "操作成功", null);
	}
}
