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

import cn.business.backend.model.Resources;
import cn.business.backend.service.ResourcesService;
import cn.core.framework.common.Configuration;

/**
 * 控制器：资源（资源中心导航菜单）
 * @author JC
 * @date 2015-8-1
 */
@Controller
public class ResourcesController extends CommonController {

	protected static Log log = LogFactory.getLog(ResourcesController.class);
	
	@Resource
	private ResourcesService resourcesService;
	
	/**
	 * 资源列表页
	 * @return
	 */
	@RequestMapping(value="resources/index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "resources"));
		return getModelView("resources/index",map);
	}
	
	/**
	 * 获取查询数据
	 * @return list
	 */
	@RequestMapping(value="resources/loadData")
	public @ResponseBody Map<String,Object> loadData(@RequestParam int rows,@RequestParam int page){
		
		//分页查询起始记录数
		int offset = (page-1) * rows; 
		
		//总记录数
		int total = resourcesService.queryCount();
		
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", offset);
		params.put("limit", rows);
		//params.put("id", request.getParameter("id"));
		
		//当前页的记录数
		List<Resources> resources = resourcesService.queryAll(params);
		return this.getPageMap(total, resources);
	}
	
	/**
	 * 资源添加页面
	 * @return
	 */
	@RequestMapping(value="resources/add",method=RequestMethod.GET)
	public ModelAndView add(){
		return this.getModelView("resources/add");
	}
	
	/**
	 * 资源编辑页面
	 * @return
	 */
	@RequestMapping(value="resources/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id){
		Map<String,Object> map = new HashMap<String,Object>();
		
		Resources resource = resourcesService.loadById(id);
		map.put("resource", resource);
		
		return this.getModelView("resources/edit",map);
	}
	
	/**
	 * 资源添加/更新处理
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value={"resources/add","resources/edit"},method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrEdit(@ModelAttribute("resource") Resources resource){
		
		if(request.getServletPath().indexOf("/add") != -1){
			resource.setType((byte)1);
			resourcesService.save(resource);
		}else{
			resourcesService.update(resource);
		}
		return this.getResultMap(1, "操作成功", null);
	}
	
	/**
	 * 删除资源
	 * @return
	 */
	@RequestMapping(value="resources/del")
	public @ResponseBody Map<String,Object> del(@RequestParam int id){
		resourcesService.delete(id);;
		return this.getResultMap(1, "操作成功", null);
	}
	
}
