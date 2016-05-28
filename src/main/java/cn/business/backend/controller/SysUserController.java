package cn.business.backend.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.SysRole;
import cn.business.backend.model.SysUser;
import cn.business.backend.service.SysRoleService;
import cn.business.backend.service.SysUserService;
import cn.core.framework.common.Configuration;
import cn.core.framework.util.MD5Util;

/**
 * 控制器：系统角色
 * @author JC
 * @date 2015-8-1
 */
@Controller
public class SysUserController extends CommonController {

	protected static Log log = LogFactory.getLog(SysUserController.class);
	
	@Resource
	private SysUserService sysUserService = null;
	@Resource
	private SysRoleService sysRoleService = null;
	
	/**
	 * 用户列表页
	 * @return
	 */
	@RequestMapping(value="sysUser/index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "sysUser"));
		return getModelView("sysUser/index",map);
	}
	
	/**
	 * 获取查询数据
	 * @return list
	 */
	@RequestMapping(value="sysUser/loadData")
	public @ResponseBody Map<String,Object> loadData(@RequestParam int rows,@RequestParam int page){
		
		//分页查询起始记录数
		int offset = (page-1) * rows; 
		
		//总记录数
		int total = sysUserService.queryCount();
		
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", offset);
		params.put("limit", rows);
		params.put("id", request.getParameter("id"));
		
		//当前页的记录数
		List<SysUser> userList = sysUserService.queryAll(params);
		return this.getPageMap(total, userList);
	}
	
	/**
	 * 用户添加页面
	 * @return
	 */
	@RequestMapping(value="sysUser/add",method=RequestMethod.GET)
	public ModelAndView add(){
		Map<String,Object> map = new HashMap<>();
		
		//角色列表
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 9999);
		List<SysRole> roleList = sysRoleService.queryAll(params);
		
		map.put("roleList", roleList);
		return this.getModelView("sysUser/add",map);
	}
	
	/**
	 * 用户编辑页面
	 * @return
	 */
	@RequestMapping(value="sysUser/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		SysUser sysUser = sysUserService.loadById(id);
		map.put("sysUser", sysUser);
		
		//角色列表
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 9999);
		List<SysRole> roleList = sysRoleService.queryAll(params);
		
		map.put("roleList", roleList);
		return this.getModelView("sysUser/edit",map);
	}
	
	/**
	 * 用户添加/更新处理
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value={"sysUser/add","sysUser/edit"},method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrEdit(@ModelAttribute("sysUser") SysUser sysUser){
		
		if(!sysUser.getPassword().equals(""))
			sysUser.setPassword(MD5Util.encodeByMD5(sysUser.getPassword()));
		
		if(request.getServletPath().indexOf("/add") != -1){
			sysUser.setAddTime(new Date());
			sysUserService.save(sysUser);
		}else{
			sysUserService.update(sysUser);
			
		}
		return this.getResultMap(1, "操作成功", null);
	}
	
	/**
	 * 删除用户
	 * @return
	 */
	@RequestMapping(value="sysUser/del")
	public @ResponseBody Map<String,Object> del(@RequestParam int id){
		sysUserService.delete(id);;
		return this.getResultMap(1, "操作成功", null);
	}
	
}
