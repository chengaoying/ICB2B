package cn.business.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.business.backend.model.DiscountGoods;
import cn.business.backend.service.DiscountGoodsService;
import cn.core.framework.common.Configuration;

/**
 * 控制器：特卖商品
 * @author TZ
 * @data 2016年5月20日
 */
@Controller("DiscountGoodsController")
public class DiscountGoodsController extends CommonController {

	@Resource
	private DiscountGoodsService discountGoodsService;

	/**
	 * 特卖商品主页
	 * @return
	 */
	@RequestMapping("discountManager/index")
	public ModelAndView index() {
		Map<String, Object> map = new HashMap<>();
		map.put("pageSize", Configuration.getProperty("backend_pagesize"));
		map.putAll(this.buttonOptationAuthMap(this.getUserFromSession().getRole(), "brandManager"));
		return getModelView("discount/index", map);
	}

	/**
	* 获取查询数据
	* 
	* @return list
	*/
	@RequestMapping(value = "discountManager/loadData")
	public @ResponseBody Map<String, Object> loadData(@RequestParam int rows, @RequestParam int page) {
		// 总记录数
		int total = discountGoodsService.queryCount();

		// 分页查询起始记录数
		int offset = (page - 1) * rows;

		// 查询参数
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", request.getParameter("id"));

		params.put("orderBy", "goods_id asc");// 排序字段
		params.put("offset", offset);// 起始记录
		params.put("limit", rows);// 每页记录数
		// 当前页的记录数
		List<DiscountGoods> discountGoodsList = discountGoodsService.queryAll(params);
		return this.getPageMap(total, discountGoodsList);
	}
}
