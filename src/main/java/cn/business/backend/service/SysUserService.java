package cn.business.backend.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import cn.business.backend.dao.ISysUserDao;
import cn.business.backend.model.SysUser;
import cn.core.framework.common.Constant;
import cn.core.framework.util.MD5Util;

/**
 * 服务类：系统用户
 * @author JC
 * @date 2015-8-1
 */
@Service("sysUserService")
public class SysUserService{

	@Resource  
    private ISysUserDao sysUserDao; 
	
    public SysUser loadById(int id) {  
		return sysUserDao.loadById(id);
    }

	public SysUser loadByAccount(String account) {
		return sysUserDao.loadByAccount(account);
	}

	public void update(SysUser sysUser) {
		sysUserDao.update(sysUser);
	}

	public void save(SysUser sysUser) {
		sysUserDao.save(sysUser);
	}

	public void delete(int id) {
		sysUserDao.delete(id);
	}

	public List<SysUser> queryAll(Map<String,Object> params) {
		return sysUserDao.queryAll(params);
	}
	
	public int queryCount() {
		return sysUserDao.queryCount();
	}

	public String loginValidate(String account, String password) {
		SysUser sysUser = this.loadByAccount(account);
		return this.loginValidate(sysUser, password);
	}

	public String loginValidate(SysUser sysUser, String password) {
		if(sysUser == null || password.equals("")) return Constant.CONST_ERROR;
		if(sysUser.getPassword().equals(MD5Util.encodeByMD5(password)))
			return Constant.CONST_SUCCESS;
		else
			return Constant.CONST_ERROR;
	}
}
