package cn.business.backend.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.business.backend.dao.IGoodsDao;
import cn.business.backend.model.Goods;

/**
 * 服务类：现货期货管理
 * @author TZ
 * @data 2016年5月26日
 */
@Service("goodsService")
public class GoodsService {

	@Resource
	private IGoodsDao goodsDao;

	public Goods loadById(int id) {
		return goodsDao.loadById(id);
	}

	public void update(Goods goods) {
		goodsDao.update(goods);
	}

	public void save(Goods goods) {
		goodsDao.save(goods);
	}

	public List<Goods> queryAll(Map<String, Object> params) {
		return goodsDao.queryAll(params);
	}

	public int queryCount() {
		return goodsDao.queryCount();
	}

	public void delete(int id) {
		goodsDao.delete(id);
	}
}
