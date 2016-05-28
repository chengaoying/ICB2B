package cn.business.backend.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.business.backend.dao.IResourcesDao;
import cn.business.backend.model.Resources;

/**
 * 服务类：资源
 * @author JC
 * @date 2015-8-1
 */
@Service("resourcesService")
public class ResourcesService{

	@Resource  
    private IResourcesDao resourcesDao; 
	
    public Resources loadById(int id) {  
		return resourcesDao.loadById(id);
    }

	public void update(Resources Resource) {
		resourcesDao.update(Resource);
	}

	public void save(Resources Resource) {
		resourcesDao.save(Resource);
	}
	
	public List<Resources> queryAll(Map<String,Object> params) {
		return resourcesDao.queryAll(params);
	}
	
	public int queryCount() {
		return resourcesDao.queryCount();
	}

	public void delete(int id){
		resourcesDao.delete(id);
	}
}
