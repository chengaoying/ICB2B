package cn.business.frontend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.Resources;
import cn.business.backend.model.User;
import cn.business.backend.service.UserCenterService;
import cn.business.backend.service.UserService;

/**
 * 控制器：用户中心
 * @author JC
 * @date 2016-3-16
 */
@Controller
public class UserCenterController extends CommonController{

	@Resource
	private UserService userService;
	
	@Resource
	private UserCenterService userCenterService;
	
	/**
	 * 用户中心首页
	 * @return
	 */
	@RequestMapping(value = "/userCenter/index.html", method = RequestMethod.GET)
	public ModelAndView index() 
	{
		Map<String,Object> map = new HashMap<String,Object>();
		
		User u = this.getUserFromSession();
		List<Resources> resList = userCenterService.getUserResources(u,session);
		
		map.put("resList", resList);
		return this.getModelView("userCenter/index",map);
	}
}
