package cn.business.backend.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.business.backend.dao.IGoodsPropertyDao;
import cn.business.backend.model.GoodsProperty;

/**
 * 服务类：商品属性管理
 * @author TZ
 * @data 2016年5月26日
 */
@Service("goodsPropertyService")
public class GoodsPropertyService {
	@Resource
	private IGoodsPropertyDao goodsPropertyDao;

	public GoodsProperty loadById(int id) {
		return goodsPropertyDao.loadById(id);
	}

	public void update(GoodsProperty goodsProperty) {
		goodsPropertyDao.update(goodsProperty);
	}

	public void save(GoodsProperty goodsProperty) {
		goodsPropertyDao.save(goodsProperty);
	}

	public List<GoodsProperty> queryAll(Map<String, Object> params) {
		return goodsPropertyDao.queryAll(params);
	}

	public int queryCount() {
		return goodsPropertyDao.queryCount();
	}

	public void delete(int id) {
		goodsPropertyDao.delete(id);
	}
}
