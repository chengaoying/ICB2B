package cn.business.frontend.controller;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import cn.business.backend.model.User;
import cn.business.backend.service.UserService;
import cn.core.framework.common.Configuration;
import cn.core.framework.common.Constant;
import cn.core.framework.controller.BaseController;

/**
 * 控制器：后台公共控制器
 * @author JC
 * @date 2015-7-26
 */
@RequestMapping("/frontend")
public class CommonController extends BaseController{
	
	protected static Log log = LogFactory.getLog(CommonController.class);
	
	@Autowired(required=false)
	protected HttpSession session;
	
	@Autowired(required=false)
	protected HttpServletRequest request;
	
	@Autowired(required=false)
	protected HttpServletResponse response;
	
	@Resource
	private UserService userService;
	
	/**
	 * jsp view
	 * @param view  视图
	 * @return
	 */
	protected ModelAndView getModelView(String view){
		return this.getModelView(view, null);
	}
	
	/**
	 * jsp view
	 * @param view  视图
	 * @param map 参数集合 
	 * @return
	 */
	protected ModelAndView getModelView(String view, Map<String,Object> map){
		view = Constant.CONST_FRONTEND + "/" + view;
		ModelAndView mv = new ModelAndView(view);
		if(map == null)	map = new HashMap<String,Object>();
		
		//前端相对地址：/xxx/frontend
		map.put("frontend_uri", request.getContextPath() + "/" + Constant.CONST_FRONTEND);
		//静态资源地址
		map.put("resource_uri", Configuration.getProperty("resource_uri"));
		//语言种类
		map.put("site_lang", session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
		mv.addAllObjects(map);
		
		return mv;
	}
	
	/**
	 * 获取网站全路径
	 * @return
	 */
	public String getFrontendUrl(){
		String url = "http://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath() + "/"+Constant.CONST_FRONTEND;
		return url;
	}
	
	/**
	 * 文件上传
	 * @param path	文件相对路径
	 * @param file	待上传的文件
	 * @return map 
	 */
	protected Map<String,Object> uploadFile(String path,CommonsMultipartFile file){
		return this.uploadFile(this.getUserFromSession().getName(), path, file);
	}
	
	/**
	 * 文件下载
	 * @param filePath 文件相对路径
	 */
	protected void downloadFile(String filePath){
		this.downloadFile(response, filePath);
	}
	
	/**
	 * 保存用户至session中
	 * @param user
	 */
	protected void saveUserToSession(User user){
		session.setAttribute(Constant.CONST_SESSION_FRONTEND_USER, user.getId());
	}
	
	/**
	 * 从session中获取用户
	 * @return
	 */
	protected User getUserFromSession(){
		Object userId = session.getAttribute(Constant.CONST_SESSION_FRONTEND_USER);
		return userService.loadById(Integer.parseInt(String.valueOf(userId)));
	}
	
	/**
	 * 更新session中的当前用户
	 * @param account
	 */
	protected void updateUserInSession(User user){
		this.removeUserFromSession();
		this.saveUserToSession(user);
	}
	
	/**
	 * 从session中移除系统用户（退出登入）
	 * @param account
	 */
	protected void removeUserFromSession(){
		session.removeAttribute(Constant.CONST_SESSION_FRONTEND_USER);
	}
}
