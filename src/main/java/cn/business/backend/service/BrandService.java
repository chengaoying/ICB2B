package cn.business.backend.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.business.backend.dao.IBrandDao;
import cn.business.backend.model.Brand;

/**
 * 服务类:品牌管理
 * @author TZ
 * @data 2016年5月20日
 */
@Service("brandService")
public class BrandService {

	@Resource
	private IBrandDao iBrandDao;

	public Brand loadById(int id) {
		return iBrandDao.loadById(id);
	}

	public void update(Brand brand) {
		iBrandDao.update(brand);
	}

	public void save(Brand brand) {
		iBrandDao.save(brand);
	}

	public List<Brand> queryAll(Map<String, Object> params) {
		return iBrandDao.queryAll(params);
	}

	public int queryCount() {
		return iBrandDao.queryCount();
	}

	public void delete(int id) {
		iBrandDao.delete(id);
	}
}
