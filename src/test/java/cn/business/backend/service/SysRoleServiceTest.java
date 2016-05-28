package cn.business.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import base.test.BaseTest;
import cn.business.backend.model.SysRole;
import cn.business.backend.service.SysRoleService;

public class SysRoleServiceTest extends BaseTest{
	//private static Log log = LogFactory.getLog(SysUserServiceTest.class);

	@Resource
	private SysRoleService syRoleService = null;
	
	@Test
	public void loadByIdTest(){
		SysRole sysRole = syRoleService.loadById(1);
		System.out.println(sysRole);
	}
	
	@Test
	public void updateRoleTest(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 9999);
		List<SysRole> list = syRoleService.queryAll(params);
		SysRole sysRole = list.get(list.size()-1);
		sysRole.setName("超级管理组2");
		syRoleService.update(sysRole);
	}
	
	@Test
	public void saveRoleTest(){
		SysRole sysRole = new SysRole();
		sysRole.setName("jc");
		sysRole.setAuth("test");
		sysRole.setIsSuper((short)1);
		sysRole.setAddTime(new Date());
		syRoleService.save(sysRole);
	}
	
	@Test
	public void deleteUserTest(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 9999);
		List<SysRole> list = syRoleService.queryAll(params);
		SysRole sysRole = list.get(list.size()-1);
		syRoleService.delete(sysRole.getId());
	}
	
	@Test
	public void queryAllTest(){
		//List list = sqlSession.selectList("queryAll", null, new RowBounds(0, 10));
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 9999);
		List<SysRole> list = syRoleService.queryAll(params);
		System.out.println(list.size());
	}
	
	@Test
	public void queryCountTest(){
		int count = syRoleService.queryCount();
		System.out.println(count);
	}
	
}
