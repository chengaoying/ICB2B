package cn.business.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.Category;
import cn.business.backend.service.ProductClassService;
import cn.core.framework.common.Configuration;
import cn.core.framework.common.Constant;

/**
 * 控制器：商品分类
 * @author TZ
 * @data 2016年5月15日
 */
@Controller("productClassController")
public class ProductClassController extends CommonController {
	@Resource
	private ProductClassService productClassService;

	/**
	 * 商品分类主页
	 * @return
	 */
	@RequestMapping("classManager/index")
	public ModelAndView index() {
		Map<String, Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "classManager"));
		return getModelView("class/index", map);
	}

	/**
	* 获取查询数据
	* 
	* @return list
	*/
	@RequestMapping(value = "classManager/loadData")
	public @ResponseBody Map<String, Object> loadData(@RequestParam int rows, @RequestParam int page) {

		// 总记录数
		int total = productClassService.queryCount();

		// 分页查询起始记录数
		int offset = (page - 1) * rows;

		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();

		String id = request.getParameter("id");
		String pId = request.getParameter("pid");
		String nameZh = request.getParameter("nameZh");
		String nameEn = request.getParameter("nameEn");
		String level = request.getParameter("level");

		// 查询赋值
		params.put("id", id);
		params.put("pid", pId);
		params.put("nameZh", nameZh);
		params.put("nameEn", nameEn);
		params.put("level", level);

		params.put("orderBy", "id asc");// 排序字段
		params.put("offset", offset);// 起始记录
		params.put("limit", rows);// 每页记录数

		// 搜索时，不进行重组
		if (id != null || pId != null || nameZh != null || nameEn != null || level != null) {
			// 不对记录进行重组
			List<Category> classList = productClassService.queryAll(params);
			return this.getPageMap(total, classList);
		} else {
			// 有序排列
			List<Category> classList = productClassService.levelAscQueryAll(params);
			return this.getPageMap(total, classList);

		}
	}

	/**
	 * 产品分类添加/编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "classManager/add", "classManager/edit" }, method = RequestMethod.GET)
	public ModelAndView addOrEdit(@RequestParam(value = "id", required = false) Integer id) {
		Map<String, Object> map = new HashMap<>();

		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("orderBy", "sort asc");// 排序字段
		params.put("offset", 0);// 起始记录
		params.put("limit", 99);// 每页记录数
		params.put("level", 1);
		List<Category> classList = productClassService.queryAll(params);
		map.put("classList", classList);

		if (request.getServletPath().indexOf("/edit") != -1) {
			Category category = productClassService.loadById(id);
			map.put("category", category);
			return this.getModelView("class/edit", map);
		} else {
			return this.getModelView("class/add", map);
		}
	}

	/**
	 * 产品分类添加/编辑页面
	 * @param category
	 * @param imgFile
	 * @return
	 */
	@RequestMapping(value = { "classManager/add", "classManager/edit" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addOrEdit(@ModelAttribute("category") Category category,
			@RequestParam("docFile") CommonsMultipartFile docFile, @RequestParam("pid_level") String pid_level) {
		System.out.println("addd----------------");
		// 分类模版文件临时上传路径
		if (!docFile.isEmpty()) {
			// 判断文件格式
			String[] ss = docFile.getOriginalFilename().split("\\.");
			if (ss[1].toLowerCase().equals("xls") || ss[1].toLowerCase().equals("xlsx")) {
				String path = Configuration.getProperty("image_uri");
				Map<String, Object> map = this.uploadFile(path, docFile);
				String status = String.valueOf(map.get("status"));
				if (status.equals(Constant.CONST_ERROR)) {
					return map;
				} else {
					category.setTempleteUrl(map.get("data").toString());
				}
			} else {
				return this.getResultMap(1, "操作失败，图片只支持doc或excl", null);
			}
		}

		// 解析页面pid_level参数，格式:pId_level
		String[] str = pid_level.split("_");
		category.setpId(Integer.parseInt(str[0]));
		category.setLevel((Integer.parseInt(str[1]) + 1));

		// 保存或更新
		if (request.getServletPath().indexOf("/add") != -1) {
			productClassService.save(category);
		} else {
			productClassService.update(category);
		}
		return this.getResultMap(1, "操作成功", null);
	}

	/**
	 * 根据id删除分类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "classManager/del")
	public @ResponseBody Map<String, Object> del(@RequestParam int id) {
		productClassService.delete(id);
		return this.getResultMap(1, "操作成功", null);
	}
}
