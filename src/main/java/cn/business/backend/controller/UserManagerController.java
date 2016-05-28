package cn.business.backend.controller;

import java.util.ArrayList;
import java.util.Collection;
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

import cn.business.backend.model.Role;
import cn.business.backend.model.User;
import cn.business.backend.service.RoleService;
import cn.business.backend.service.UserService;
import cn.core.framework.common.Configuration;

/**
 * 控制器：用户管理
 * @author JC
 * @date 2015-8-1
 */
@Controller
public class UserManagerController extends CommonController {

	protected static Log log = LogFactory.getLog(UserManagerController.class);
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private UserService userService;
	
	/**
	 * 用户列表页
	 * @return
	 */
	@RequestMapping(value="userManager/index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "userManager"));
		return getModelView("userManager/index",map);
	}
	
	/**
	 * 获取查询数据
	 * @return list
	 */
	@RequestMapping(value="userManager/loadData")
	public @ResponseBody Map<String,Object> loadData(@RequestParam int rows,@RequestParam int page){
		
		//分页查询起始记录数
		int offset = (page-1) * rows; 
		
		//总记录数
		int total = userService.queryCount();
		
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", offset);
		params.put("limit", rows);
		params.put("id", request.getParameter("id"));
		
		//当前页的记录数
		List<User> userList = userService.queryAll(params);
		return this.getPageMap(total, userList);
	}
	
	/**
	 * 用户编辑页面
	 * @return
	 */
	@RequestMapping(value="userManager/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("offset", 0);
		map.put("limit", 999);
		List<Role> roleList = roleService.queryAll(map);
		map.clear();
		
		User user = userService.loadById(id);
		
		map.put("user", user);
		map.put("roleList", roleList);
		return this.getModelView("userManager/edit",map);
	}
	
	/**
	 * 用户添加/更新处理
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value={"userManager/edit"},method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrEdit(@ModelAttribute("user") User user){
		
		//获取角色id参数集合
		Map<String,String> params = this.getParams(new String[]{"name","id"});
		Collection<String> values = params.values();
		List<String> roleIds = new ArrayList<String>(values);
		
		userService.updateUserRole(roleIds, userService.loadById(user.getId()));
		
		userService.update(user);
		return this.getResultMap(1, "操作成功", null);
	}
	
}
