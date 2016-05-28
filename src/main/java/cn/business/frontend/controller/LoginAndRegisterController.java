package cn.business.frontend.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.service.UserService;
import cn.core.framework.common.Constant;

/**
 * 登入和注册控制器
 * @author JC
 * @date 2016-4-5
 * @since v1.0
 */
@Controller
public class LoginAndRegisterController extends CommonController{

	@Resource
	private UserService userService;
	
	/**
	 * 用户登入页面
	 * @return
	 */
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView login() 
	{
		return this.getModelView("login");
	}

	/**
	 * 用户登入处理
	 * @return
	 */
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public @ResponseBody Map<String, String> login(@RequestParam String name, @RequestParam String password) 
	{
		Map<String, String> map = userService.login(name, password);
		String status = map.get("status");
		if (Constant.CONST_SUCCESS.equals(status)) {
			// 保存用户至session中
			this.saveUserToSession(userService.loadByName(name));
			// 跳转至用户中心首页
			map.put("data", "/userCenter/index.html");
		}
		return map;
	}
	
	/**
	 * 用户注册页面
	 * @return
	 */
	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public ModelAndView register() 
	{
		return this.getModelView("register");
	}
	
}
