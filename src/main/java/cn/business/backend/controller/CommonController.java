package cn.business.backend.controller;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

import cn.business.backend.model.SysUser;
import cn.business.backend.service.SysUserService;
import cn.core.framework.common.Configuration;
import cn.core.framework.common.Constant;

/**
 * 控制器：后台公共控制器
 * @author JC
 * @date 2015-7-26
 */
@RequestMapping("/backend")
public class CommonController extends BaseAuthController{
	
	protected static Log log = LogFactory.getLog(CommonController.class);
	
	@Autowired(required=false)
	protected HttpSession session;
	
	@Autowired(required=false)
	protected HttpServletRequest request;
	
	@Autowired(required=false)
	protected HttpServletResponse response;
	
	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 获取请求的参数，并将参数保存在map中
	 * @param strings 过滤的参数列表
	 * @return map
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String,String> getParams(String...strings){
		Map<String,String[]> params = request.getParameterMap();
		Map<String,String> map = new HashMap<String,String>();
		for(Iterator iter = params.entrySet().iterator();iter.hasNext();){  
			Map.Entry element = (Map.Entry)iter.next(); 
			String strKey = (String) element.getKey();
			//过滤不是权限操作的参数
			if(!Arrays.asList(strings).contains(strKey)){
				String[] strObj = (String[])element.getValue();
				map.put(strKey,strObj[0]);
			}
		}
		return map;
	}
	
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
		view = Constant.CONST_BACKEND + "/" + view;
		ModelAndView mv = new ModelAndView(view);
		if(map == null)	map = new HashMap<String,Object>();
		
		//管理后台相对地址：/xxx/platform
		map.put("backend_uri", request.getContextPath() + "/" + Constant.CONST_BACKEND);
		//静态资源地址
		map.put("resource_uri", Configuration.getProperty("resource_uri"));
		mv.addAllObjects(map);
		
		return mv;
	}
	
	/**
	 * 获取分页数据的map
	 * 注：参数名total和rows是前端分页插件固定使用的名称，修改会导致无法读取参数。
	 * @param total 总记录数
	 * @param rows	记录列表
	 * @return map
	 */
	protected Map<String,Object> getPageMap(int total, List<?> rows){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", rows);
		return map;
	}
	
	/**
	 * 文件上传
	 * @param path	文件相对路径
	 * @param file	待上传的文件
	 * @return map 
	 */
	protected Map<String,Object> uploadFile(String path,CommonsMultipartFile file){
		return this.uploadFile(this.getUserFromSession().getAccount(), path, file);
	}
	
	/**
	 * 文件下载
	 * @param filePath 文件相对路径
	 */
	protected void downloadFile(String filePath){
		this.downloadFile(response, filePath);
	}
	
	/**
	 * 保存系统用户至session中
	 * @param sysUser
	 */
	protected void saveUserToSession(SysUser sysUser){
		session.setAttribute(Constant.CONST_SESSION_BACKEND_USER, sysUser.getAccount());
	}
	
	/**
	 * 从session中获取用户
	 * @return
	 */
	protected SysUser getUserFromSession(){
		String account = (String)session.getAttribute(Constant.CONST_SESSION_BACKEND_USER);
		return sysUserService.loadByAccount(account);
	}
	
	/**
	 * 从session中移除系统用户（退出登入）
	 * @param sysUser
	 */
	protected void removeUserFromSession(){
		session.removeAttribute(Constant.CONST_SESSION_BACKEND_USER);
	}
	
}
