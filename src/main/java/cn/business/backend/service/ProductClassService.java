package cn.business.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.business.backend.dao.IProductClassDao;
import cn.business.backend.model.Category;

/**
 * 服务类:商品分类
 * @author TZ
 * @data 2016年5月20日
 */
@Service("productClassService")
public class ProductClassService {

	@Resource
	private IProductClassDao iProductClassDao;

	public Category loadById(int id) {
		return iProductClassDao.loadById(id);
	}

	public void update(Category category) {
		iProductClassDao.update(category);
	}

	public void save(Category category) {
		iProductClassDao.save(category);
	}

	public List<Category> queryAll(Map<String, Object> params) {
		return iProductClassDao.queryAll(params);
	}

	public List<Category> levelAscQueryAll(Map<String, Object> params) {
		List<Category> listDao = iProductClassDao.levelQueryAll(params);
		// 将一级分类先保存，然后保存二级分类
		List<Category> listOk = new ArrayList<Category>();
		for (Category ClassOne : listDao) {
			if (ClassOne.getpId() == 0) {
				listOk.add(ClassOne);
				for (Category ClassTwo : listDao) {
					if (ClassTwo.getpId() == ClassOne.getId()) {
						listOk.add(ClassTwo);
					}
				}
			}
		}
		return listOk;
	}

	public int queryCount() {
		return iProductClassDao.queryCount();
	}

	public void delete(int id) {
		iProductClassDao.delete(id);
	}
}
