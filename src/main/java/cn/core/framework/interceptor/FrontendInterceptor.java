package cn.core.framework.interceptor;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.Resources;
import cn.business.backend.model.User;
import cn.business.backend.service.UserCenterService;
import cn.business.backend.service.UserService;
import cn.core.framework.common.Constant;
import cn.core.framework.util.UrlUtil;

/**
 * 后台管理系统拦截器：认证
 * @author JC
 * @date 2015-8-5
 */
public class FrontendInterceptor implements AsyncHandlerInterceptor {

	protected static Log log = LogFactory.getLog(FrontendInterceptor.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private UserCenterService userCenterService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, 
			Object handler) throws Exception {
		
		log.info("website frontend request url ===> " + request.getRequestURL());
		log.info("website frontend request param ===> " + request.getQueryString());
		log.info("website frontend client url ===> " + UrlUtil.getIpAddr(request));
		
		User user = null;
		boolean isLogin = false;
		Object obj = request.getSession().getAttribute(Constant.CONST_SESSION_FRONTEND_USER);
		if(obj != null){
			 user = userService.loadById(Integer.parseInt(String.valueOf(obj)));
			if(user != null)
				isLogin = true;
		}
		
		//没有登入则进入登入页
		if(!isLogin){
			response.sendRedirect(request.getContextPath() + "/" + Constant.CONST_FRONTEND + "/login.html");
			return false;
		}else{
			String uri = request.getRequestURI();
			
			//用户中心首页返回直接true,不用拦截处理
			if(uri.indexOf("/userCenter/index") != -1)	
				return true;
			
			/**
			 * 获取用户可访问的资源列表
			 */
			List<Resources> ress = userCenterService.getUserResources(user, request.getSession());
			for (Resources resources : ress) {
				if(uri.indexOf(resources.getUrl()) != -1)
					return true;
			}
			
			throw new RuntimeException("没有操作权限！");
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	}
	
}
