package cn.business.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.UserSeller;
import cn.business.backend.service.UserSellerService;
import cn.core.framework.common.Configuration;

/**
 * 控制器：用户关注商家
 * @author pridewu
 * @date 2016-05-14
 */
@Controller
public class UserSellerController extends CommonController {

	protected static Log log = LogFactory.getLog(UserSellerController.class);
	
	@Resource
	private UserSellerService userSellerService = null;
	
	/**
	 * 用户关注商家页面
	 * @return
	 */
	@RequestMapping(value="userSeller/index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "userSeller"));
		return getModelView("userSeller/index",map);
	}
	
	/**
	 * 获取查询数据
	 * @return list
	 */
	@RequestMapping(value="userSeller/loadData")
	public @ResponseBody Map<String,Object> loadData(@RequestParam int rows,@RequestParam int page){
		
		//分页查询起始记录数
		int offset = (page-1) * rows; 
		
		//总记录数
		int total = userSellerService.queryCount();
		
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("user_id", request.getParameter("user_id"));
		params.put("seller_id", request.getParameter("seller_id"));
		params.put("offset", offset);
		params.put("limit", rows);
		params.put("id", request.getParameter("id"));
		
		//当前页的记录数
		List<UserSeller> userList = userSellerService.queryAll(params);
		return this.getPageMap(total, userList);
	}
	
}
