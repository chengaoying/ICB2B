package cn.business.backend.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.business.backend.dao.IRoleDao;
import cn.business.backend.model.Role;

/**
 * 服务类：角色
 * @author JC
 * @date 2015-8-1
 */
@Service("roleService")
public class RoleService{

	@Resource  
    private IRoleDao roleDao; 
	
    public Role loadById(int id) {  
		return roleDao.loadById(id);
    }

	public void update(Role Role) {
		roleDao.update(Role);
	}

	public void save(Role Role) {
		roleDao.save(Role);
	}
	
	public List<Role> queryAll(Map<String,Object> params) {
		return roleDao.queryAll(params);
	}
	
	public int queryCount() {
		return roleDao.queryCount();
	}

	public void delete(int id){
		roleDao.delete(id);
	}
}
