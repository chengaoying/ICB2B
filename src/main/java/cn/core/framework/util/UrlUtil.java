package cn.core.framework.util;

import javax.servlet.http.HttpServletRequest;

/**
 * url处理类
 * @author JC
 * @date 2016-4-9
 */
public class UrlUtil {

	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {     
	      String ip = request.getHeader("x-forwarded-for");     
	      if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	         ip = request.getHeader("Proxy-Client-IP");     
	     }     
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	        ip = request.getHeader("WL-Proxy-Client-IP");     
	     }
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
		        ip = request.getHeader("HTTP_CLIENT_IP");     
		 }
		 if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	        ip = request.getHeader("HTTP_X_FORWARDED_FOR");     
	     }
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
	          ip = request.getRemoteAddr();     
	     }     
	     return ip;     
	}    
}
