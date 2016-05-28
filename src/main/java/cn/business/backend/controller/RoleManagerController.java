package cn.business.backend.controller;

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

import cn.business.backend.model.Resources;
import cn.business.backend.model.Role;
import cn.business.backend.service.ResourcesService;
import cn.business.backend.service.RoleService;
import cn.core.framework.common.Configuration;

/**
 * 控制器：用户角色管理
 * @author JC
 * @date 2015-8-1
 */
@Controller
public class RoleManagerController extends CommonController {

	protected static Log log = LogFactory.getLog(RoleManagerController.class);
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private ResourcesService resourcesService;
	
	/**
	 * 角色列表页
	 * @return
	 */
	@RequestMapping(value="roleManager/index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "roleManager"));
		return getModelView("roleManager/index",map);
	}
	
	/**
	 * 获取查询数据
	 * @return list
	 */
	@RequestMapping(value="roleManager/loadData")
	public @ResponseBody Map<String,Object> loadData(@RequestParam int rows,@RequestParam int page){
		
		//分页查询起始记录数
		int offset = (page-1) * rows; 
		
		//总记录数
		int total = roleService.queryCount();
		
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", offset);
		params.put("limit", rows);
		
		//当前页的记录数
		List<Role> roleList = roleService.queryAll(params);
		return this.getPageMap(total, roleList);
	}
	
	/**
	 * 角色添加页面
	 * @return
	 */
	@RequestMapping(value="roleManager/add",method=RequestMethod.GET)
	public ModelAndView add(){
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("offset", 0);
		map.put("limit", 9999);
		List<Resources> resources = resourcesService.queryAll(map);
		map.clear();
		
		map.put("resources", resources);
		return this.getModelView("roleManager/add",map);
	}
	
	/**
	 * 角色编辑页面
	 * @return
	 */
	@RequestMapping(value="roleManager/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("offset", 0);
		map.put("limit", 9999);
		List<Resources> resources = resourcesService.queryAll(map);
		map.clear();
		
		map.put("resources", resources);
		
		Role role = roleService.loadById(id);
		map.put("role", role);
		
		return this.getModelView("roleManager/edit",map);
	}
	
	/**
	 * 角色添加/更新处理
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value={"roleManager/add","roleManager/edit"},method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrEdit(@ModelAttribute("role") Role role){
		
		//获取权限参数
		JSONObject json = (JSONObject) JSONObject.toJSON(this.getParams(new String[]{"nameZh","nameEn","id"}));
		role.setAuth(json.toJSONString());
		
		if(request.getServletPath().indexOf("/add") != -1){
			roleService.save(role);
		}else{
			roleService.update(role);
		}
		return this.getResultMap(1, "操作成功", null);
	}
	
	/**
	 * 删除角色
	 * @return
	 */
	@RequestMapping(value="roleManager/del")
	public @ResponseBody Map<String,Object> del(@RequestParam int id){
		roleService.delete(id);;
		return this.getResultMap(1, "操作成功", null);
	}
	
}
