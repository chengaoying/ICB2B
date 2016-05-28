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

import cn.business.backend.model.Organization;
import cn.business.backend.service.OrganizationService;
import cn.core.framework.common.Configuration;

/**
 * 控制器：企业信息管理
 * @author pridewu
 * @date 2016-05-16
 */
@Controller
public class OrganizationController extends CommonController {
	
	protected static Log log = LogFactory.getLog(OrganizationController.class);
	
	@Resource
	private OrganizationService organizationService = null;
	
	/**
	 * 用户地址页
	 * @return
	 */
	@RequestMapping(value="organization/index")
	public ModelAndView index(){
		Map<String,Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "organization"));
		return getModelView("organization/index",map);
	}
	
	/**
	 * 获取查询数据
	 * @return list
	 */
	@RequestMapping(value="organization/loadData")
	public @ResponseBody Map<String,Object> loadData(@RequestParam int rows,@RequestParam int page){
		
		//分页查询起始记录数
		int offset = (page-1) * rows; 
		
		//总记录数
		int total = organizationService.queryCount();
		
		//查询参数
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("name", request.getParameter("name"));
		params.put("contacts", request.getParameter("contacts"));
		params.put("offset", offset);
		params.put("limit", rows);
		
		//当前页的记录数
		List<Organization> userList = organizationService.queryAll(params);
		return this.getPageMap(total, userList);
	}
	
	/**
	 * 添加/编辑页面
	 * 
	 * @return
	 */
	@RequestMapping(value = { "organization/add", "organization/edit" }, method = RequestMethod.GET)
	public ModelAndView addOrEdit(@RequestParam(value = "id", required = false) Integer id) {
		Map<String, Object> map = new HashMap<>();
		
		if (request.getServletPath().indexOf("/edit") != -1) {
			Organization organization = organizationService.loadById(id);
			map.put("organization", organization);
			return this.getModelView("organization/edit", map);
		} else {
			return this.getModelView("organization/add", map);
		}
	}

	
	/**
	 * 添加/更新处理
	 * @param organization
	 * @return
	 */
	@RequestMapping(value={"organization/add","organization/edit"},method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrEdit(@ModelAttribute("organization") Organization organization)
	{
		//保存或更新
		if(request.getServletPath().indexOf("/edit") != -1)
		{
			organizationService.update(organization);
		}
		else
		{
			organization.setAddTime(new Date());
			organizationService.save(organization);
		}
		
		return this.getResultMap(1, "操作成功", null);
	}
	
	/**
	 * 删除地址信息
	 * @return
	 */
	@RequestMapping(value="organization/del")
	public @ResponseBody Map<String,Object> del(@RequestParam int id){
		organizationService.delete(id);
		return this.getResultMap(1, "操作成功", null);
	}
}
