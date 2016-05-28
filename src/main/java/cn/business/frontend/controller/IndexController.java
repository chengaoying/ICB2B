package cn.business.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController extends CommonController{

	
	/**
	 * 网站首页
	 * @return
	 */
	@RequestMapping("/index.html")
	public ModelAndView test(){
		return getModelView("index");
	}
}
