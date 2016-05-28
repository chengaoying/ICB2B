package cn.business.backend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.SysUser;
import cn.core.framework.model.Menu;

/**
 * 控制器：后台主页
 * @author JC
 * @date 2015-8-1
 */
@Controller("backendIndexController")
public class IndexController extends CommonController {

	protected static Log log = LogFactory.getLog(IndexController.class);
	
	/**
	 * 后台主页
	 * @return
	 */
	@RequestMapping("index")
	public ModelAndView index(){
		Map<String,Object> param = new HashMap<String,Object>();
		SysUser user = this.getUserFromSession();
		
		//加载导航菜单
		ArrayList<Menu> menus = this.initMenusResource(user.getRole());
		
		param.put("menus", menus);
		param.put("userName", user.getName());
		param.put("roleName", user.getRole().getName());
		
		return this.getModelView("index",param);
	}
}
