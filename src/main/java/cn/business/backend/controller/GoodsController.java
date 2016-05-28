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
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.Goods;
import cn.business.backend.service.GoodsPropertyService;
import cn.business.backend.service.GoodsService;
import cn.core.framework.common.Configuration;

/**
 * 控制器：现货期货管理
 * @author TZ
 * @data 2016年5月20日
 */
@Controller("GoodsController")
public class GoodsController extends CommonController {

	@Resource
	private GoodsService goodsService;
	
	@Resource
	private GoodsPropertyService goodsPropertyService;

	/**
	 * 现货期货主页
	 * @return
	 */
	@RequestMapping("goodsManager/index")
	public ModelAndView index() {
		Map<String, Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "brandManager"));
		return getModelView("goods/index", map);
	}

	/**
	* 获取查询数据
	* 
	* @return list
	*/
	@RequestMapping(value = "goodsManager/loadData")
	public @ResponseBody Map<String, Object> loadData(@RequestParam int rows, @RequestParam int page) {
		// 总记录数
		int total = goodsService.queryCount();

		// 分页查询起始记录数
		int offset = (page - 1) * rows;

		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", request.getParameter("id"));

		params.put("orderBy", "goods_id asc");// 排序字段
		params.put("offset", offset);// 起始记录
		params.put("limit", rows);// 每页记录数
		// 当前页的记录数
		List<Goods> goodsList = goodsService.queryAll(params);
		return this.getPageMap(total, goodsList);
	}

	/**
	 * 产品分类添加/编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "goodsManager/add", "goodsManager/edit" }, method = RequestMethod.GET)
	public ModelAndView addOrEdit(@RequestParam(value = "id", required = false) Integer id) {
		Map<String, Object> map = new HashMap<>();

		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("orderBy", "start_date desc");// 排序字段
		params.put("offset", 0);// 起始记录
		params.put("limit", 99);// 每页记录数
		params.put("level", 1);
		// 查询商品
		List<Goods> goodsList = goodsService.queryAll(params);
		map.put("classList", goodsList);

		if (request.getServletPath().indexOf("/edit") != -1) {
			Goods goods = goodsService.loadById(id);
			map.put("groupGoods", goods);
			return this.getModelView("goods/edit", map);
		} else {
			return this.getModelView("goods/add", map);
		}
	}

	/**
	 * 产品分类添加/编辑页面
	 * @param category
	 * @param imgFile
	 * @return
	 */
	@RequestMapping(value = { "goodsManager/add", "goodsManager/edit" }, method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addOrEdit(@ModelAttribute("groupGoods") Goods goods,
			@RequestParam("pid_level") String pid_level) {

		// 保存或更新
		if (request.getServletPath().indexOf("/add") != -1) {
			goodsService.save(goods);
		} else {
			goodsService.update(goods);
		}
		return this.getResultMap(1, "操作成功", null);
	}

	/**
	 * 根据id删除分类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "goodsManager/del")
	public @ResponseBody Map<String, Object> del(@RequestParam int id) {
		goodsService.delete(id);
		return this.getResultMap(1, "操作成功", null);
	}
}
