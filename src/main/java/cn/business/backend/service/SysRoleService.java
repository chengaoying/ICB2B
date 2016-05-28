package cn.business.backend.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.business.backend.dao.ISysRoleDao;
import cn.business.backend.model.SysRole;

/**
 * 服务类：系统角色
 * @author JC
 * @date 2015-8-1
 */
@Service("sysRoleService")
public class SysRoleService{

	@Resource
	private ISysRoleDao sysRoleDao;
	
	public SysRole loadById(int id) {
		return sysRoleDao.loadById(id);
	}

	public void update(SysRole sysRole) {
		sysRoleDao.update(sysRole);
	}

	public void save(SysRole sysRole) {
		sysRoleDao.save(sysRole);
	}

	public void delete(int id) {
		sysRoleDao.delete(id);
	}

	public List<SysRole> queryAll(Map<String,Object> params) {
		return sysRoleDao.queryAll(params);
	}

	public int queryCount() {
		return sysRoleDao.queryCount();
	}
	
	
}
