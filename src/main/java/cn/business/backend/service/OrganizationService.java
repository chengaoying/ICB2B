package cn.business.backend.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.business.backend.dao.IOrganizationDao;
import cn.business.backend.model.Organization;

/**
 * 服务类：企业信息
 * @author pridewu
 * @date 2016-05-15
 */
@Service("OrganizationService")
public class OrganizationService{

	@Resource  
    private IOrganizationDao organizationDao; 
	
	public List<Organization> queryAll(Map<String,Object> params) {
		return organizationDao.queryAll(params);
	}
	
	public int queryCount() {
		return organizationDao.queryCount();
	}
	
	public Organization loadById(int id) {
		return organizationDao.loadById(id);
	}
	
	public int update(Organization organization) {
		return organizationDao.update(organization);
	}
	
	public int save(Organization organization) {
		return organizationDao.save(organization);
	}
	
	public int delete(int id) {
		return organizationDao.delete(id);
	}
}
