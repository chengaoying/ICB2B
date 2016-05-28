package cn.business.backend.controller;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONObject;

import cn.business.backend.model.SysRole;
import cn.business.backend.service.SysRoleService;
import cn.core.framework.common.Configuration;
import cn.core.framework.model.Menu;

/**
 * 控制器：系统角色
 * @author JC
 * @date 2015-8-1
 */
@Controller
public class SysRoleController extends CommonController {

	protected static Log log = LogFactory.getLog(SysRoleController.class);
	
	@Resource
	private SysRoleService sysRoleService = null;
	
	/**
	 * 角色列表页
	 * @return
	 */
	@RequestMapping(value="sysRole/index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "sysRole"));
		return getModelView("sysRole/index",map);
	}
	
	/**
	 * 获取查询数据
	 * @return list
	 */
	@RequestMapping(value="sysRole/loadData")
	public @ResponseBody Map<String,Object> loadData(@RequestParam int rows,@RequestParam int page){
		
		//分页查询起始记录数
		int offset = (page-1) * rows; 
		
		//总记录数
		int total = sysRoleService.queryCount();
		
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", offset);
		params.put("limit", rows);
		params.put("id", request.getParameter("id"));
		
		//当前页的记录数
		List<SysRole> roleList = sysRoleService.queryAll(params);
		return this.getPageMap(total, roleList);
	}
	
	/**
	 * 角色添加页面
	 * @return
	 */
	@RequestMapping(value="sysRole/add",method=RequestMethod.GET)
	public ModelAndView add(){
		Map<String,Object> map = new HashMap<String,Object>();
		
		//获取功能菜单
		ArrayList<Menu> menus = this.initMenusResource(this.getUserFromSession().getRole());
		map.put("menus", menus);
		return this.getModelView("sysRole/add",map);
	}
	
	/**
	 * 角色编辑页面
	 * @return
	 */
	@RequestMapping(value="sysRole/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		//角色
		SysRole sysRole = sysRoleService.loadById(id);
		map.put("sysRole", sysRole);
		
		//获取功能菜单
		ArrayList<Menu> menus = this.initMenusResource(sysRole);
		map.put("menus", menus);
		return this.getModelView("sysRole/edit",map);
	}
	
	/**
	 * 角色添加/更新处理
	 * @param sysRole
	 * @return
	 */
	@RequestMapping(value={"sysRole/add","sysRole/edit"},method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrEdit(@ModelAttribute("sysRole") SysRole sysRole){
		//获取权限参数
		JSONObject json = (JSONObject) JSONObject.toJSON(this.getParams(new String[]{"name","isSuper","id"}));
		sysRole.setAuth(json.toJSONString());
		
		if(request.getServletPath().indexOf("/add") != -1){
			sysRole.setAddTime(new Date());
			sysRoleService.save(sysRole);
		}else{
			sysRoleService.update(sysRole);
		}
		return this.getResultMap(1, "操作成功", null);
	}
	
	/**
	 * 角色删除
	 * @return
	 */
	@RequestMapping(value="sysRole/del")
	public @ResponseBody Map<String,Object> del(@RequestParam int id){
		sysRoleService.delete(id);
		return this.getResultMap(1, "操作成功", null);
	}
}
