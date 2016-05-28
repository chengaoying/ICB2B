package cn.core.framework.interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.business.backend.service.UserService;
import cn.core.framework.common.Configuration;
import cn.core.framework.util.MD5Util;

/**
 * Api拦截器
 * @author JC
 * @date 2015-8-5
 */
public class ApiInterceptor implements AsyncHandlerInterceptor {

	protected static Log log = LogFactory.getLog(ApiInterceptor.class);
	
	@Resource
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, 
			Object handler) throws Exception {
		
		String sign = request.getParameter("sign");
		if(sign == null || "".equals(sign)){
			JSONObject json = new JSONObject();
			json.put("status", "-1");
			json.put("message", "接口访问错误");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
		    out.print(json);
		    out.flush();
		    out.close();
		    return false;
		}
		
		String apiKey = Configuration.getProperty("api_key");
		String _sign = MD5Util.encodeByMD5(apiKey);
		//System.out.println(_sign);
		if(!sign.equals(_sign)){
			JSONObject json = new JSONObject();
			json.put("status", "-1");
			json.put("message", "接口访问错误");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
		    out.print(json);
		    out.flush();
		    out.close();
		    return false;
		}
		return true;
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
