package cn.business.backend.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.business.backend.dao.IDiscountGoodsDao;
import cn.business.backend.model.DiscountGoods;

/**
 * 服务类：特卖商品
 * @author TZ
 * @data 2016年5月20日
 */
@Service("discountGoodsService")
public class DiscountGoodsService {

	@Resource
	private IDiscountGoodsDao discountGoodsDao;

	public DiscountGoods loadById(int id) {
		return discountGoodsDao.loadById(id);
	}

	public void update(DiscountGoods discountGoods) {
		discountGoodsDao.update(discountGoods);
	}

	public void save(DiscountGoods discountGoods) {
		discountGoodsDao.save(discountGoods);
	}

	public List<DiscountGoods> queryAll(Map<String, Object> params) {
		return discountGoodsDao.queryAll(params);
	}

	public int queryCount() {
		return discountGoodsDao.queryCount();
	}

	public void delete(int id) {
		discountGoodsDao.delete(id);
	}
}
