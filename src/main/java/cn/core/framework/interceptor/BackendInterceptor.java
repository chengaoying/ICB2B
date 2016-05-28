package cn.core.framework.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.controller.BaseAuthController;
import cn.business.backend.model.SysUser;
import cn.business.backend.service.SysUserService;
import cn.core.framework.common.Constant;
import cn.core.framework.util.UrlUtil;

/**
 * 后台管理系统拦截器：认证
 * @author JC
 * @date 2015-8-5
 */
public class BackendInterceptor extends BaseAuthController implements AsyncHandlerInterceptor {

	protected static Log log = LogFactory.getLog(BackendInterceptor.class);
	
	@Resource
	private SysUserService sysUserService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, 
			Object handler) throws Exception {
		
		String uri = request.getRequestURI();
		String account = (String) request.getSession().getAttribute(Constant.CONST_SESSION_BACKEND_USER);
		SysUser sysUser = sysUserService.loadByAccount(account);
		
		log.info("website backend request url ===> " + request.getRequestURL());
		log.info("website backend request param ===> " + request.getQueryString());
		log.info("website backend client url ===> " + UrlUtil.getIpAddr(request));
		
		//登入和退出操作返回true
		if(uri.indexOf("/login") != -1 || uri.indexOf("/logout") != -1) 
			return true;
		
		//用户信息过期验证：如果session中用户信息为空并且当前访问的不是登入页面，
		//则跳转至登入页面
		if(sysUser == null){
			response.sendRedirect(request.getContextPath() + "/" + Constant.CONST_BACKEND + "/login");
			return false;
		}else{
			//用户访问权限验证
			String servletPath = request.getServletPath();
			
			//后台首页返回直接true,不用拦截处理
			if(uri.indexOf(request.getContextPath() + "/" + Constant.CONST_BACKEND +"/index") != -1)	
				return true;
			
			String path[] = servletPath.split("/");
			boolean isActionAuth = this.isActionAuth(sysUser.getRole(), path[2], path[3]);
			if(!isActionAuth){
				throw new RuntimeException("没有操作权限！");
			}
			return true;
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
