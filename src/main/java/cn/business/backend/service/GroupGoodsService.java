package cn.business.backend.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.business.backend.dao.IGroupGoodsDao;
import cn.business.backend.model.GroupGoods;

/**
 * 服务类：拼购商品
 * @author TZ
 * @data 2016年5月20日
 */
@Service("groupGoodsService")
public class GroupGoodsService {
	@Resource
	private IGroupGoodsDao groupGoodsDao;

	public GroupGoods loadById(int id) {
		return groupGoodsDao.loadById(id);
	}

	public void update(GroupGoods GroupGoods) {
		groupGoodsDao.update(GroupGoods);
	}

	public void save(GroupGoods GroupGoods) {
		groupGoodsDao.save(GroupGoods);
	}

	public List<GroupGoods> queryAll(Map<String, Object> params) {
		return groupGoodsDao.queryAll(params);
	}

	public int queryCount() {
		return groupGoodsDao.queryCount();
	}

	public void delete(int id) {
		groupGoodsDao.delete(id);
	}
}
