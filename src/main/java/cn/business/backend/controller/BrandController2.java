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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.Brand;
import cn.business.backend.service.BrandService;
import cn.core.framework.common.Configuration;
import cn.core.framework.common.Constant;

/**
 * 控制器：品牌管理
 * @author TZ
 * @data 2016年5月14日
 */
@Controller  
public class BrandController2 extends CommonController {

	protected static Log log = LogFactory.getLog(BrandController2.class);

	@Resource
	private BrandService brandService;

	/**
	 * 品牌管理主页
	 * @return
	 */
	@RequestMapping("brandManager/index")
	public ModelAndView index() {
		Map<String, Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "brandManager"));
		return getModelView("brand/index", map);
	}

	/**
	* 获取查询数据
	* 
	* @return list
	*/
	@RequestMapping(value = "brandManager/loadData")
	public @ResponseBody Map<String, Object> loadData(@RequestParam int rows, @RequestParam int page) {

		// 总记录数
		int total = brandService.queryCount();

		// 分页查询起始记录数
		int offset = (page - 1) * rows;

		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", request.getParameter("id"));
		params.put("nameZh", request.getParameter("nameZh"));
		params.put("nameEn", request.getParameter("nameEn"));
		
		params.put("orderBy", "id asc");// 排序字段
		params.put("offset", offset);// 起始记录
		params.put("limit", rows);// 每页记录数
		// 当前页的记录数
		List<Brand> brandList = brandService.queryAll(params);
		return this.getPageMap(total, brandList);
	}

	/**
	 * 品牌添加页面
	 * @return
	 */
	@RequestMapping(value = "brandManager/add", method = RequestMethod.GET)
	public ModelAndView add() {
		return this.getModelView("brand/add");
	}

	/**
	 * 品牌编辑页面
	 * @return
	 */
	@RequestMapping(value = "brandManager/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Brand brand = brandService.loadById(id);
		map.put("brand", brand);
		return this.getModelView("brand/edit", map);
	}

	/**
	 * 产品添加/更新处理
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = { "brandManager/add", "brandManager/edit" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addOrEdit(@ModelAttribute("brand") Brand brand,
			@RequestParam("imgFile") CommonsMultipartFile imgFile) {
		// 产品图片文件临时上传路径
		if (!imgFile.isEmpty()) {
			// 判断图片格式
			String[] ss = imgFile.getOriginalFilename().split("\\.");
			if (ss[1].toLowerCase().equals("jpg") || ss[1].toLowerCase().equals("png")) {
				String path = Configuration.getProperty("image_uri");
				Map<String, Object> map = this.uploadFile(path, imgFile);
				String status = String.valueOf(map.get("status"));
				if (status.equals(Constant.CONST_ERROR)) {
					return map;
				} else {
					brand.setLogo(map.get("data").toString());
				}
			} else {
				return this.getResultMap(1, "操作失败，图片只支持jpg或png", null);
			}
		}
		// 保存或更新
		if (request.getServletPath().indexOf("/add") != -1) {
			brandService.save(brand);
		} else {
			brandService.update(brand);
		}
		return this.getResultMap(1, "操作成功", null);
	}

	/**
	 * 根据id删除品牌
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "brandManager/del")
	public @ResponseBody Map<String, Object> del(@RequestParam int id) {
		brandService.delete(id);
		return this.getResultMap(1, "操作成功", null);
	}
}
