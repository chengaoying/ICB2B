package cn.business.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import base.test.BaseTest;
import cn.business.backend.model.SysRole;
import cn.business.backend.model.SysUser;
import cn.business.backend.service.SysUserService;
import cn.core.framework.util.MD5Util;

public class SysUserServiceTest extends BaseTest{
	//private static Log log = LogFactory.getLog(SysUserServiceTest.class);

	@Resource
	private SysUserService sysUserService = null;
	
	@Test
	public void loadByIdTest(){
		SysUser sysUser = sysUserService.loadById(1);
		System.out.println(sysUser.getRole().getName());
	}
	
	@Test
	public void loadByAccountTest(){
		SysUser sysUser = sysUserService.loadByAccount("admin");
		System.out.println(sysUser.getRole().getName());
	}
	
	
	@Test
	public void updateUserTest(){
		SysUser sysUser = sysUserService.loadById(1);
		sysUser.setName("系统管理员");
		sysUserService.update(sysUser);
	}
	
	@Test
	public void saveUserTest(){
		SysUser sysUser = new SysUser();
		SysRole sysRole = new SysRole();
		sysRole.setId(1);
		sysUser.setAccount("test");
		sysUser.setName("jc");
		sysUser.setPassword(MD5Util.encodeByMD5("123"));
		sysUser.setRole(sysRole);
		sysUser.setStatus(1);
		sysUser.setLastIp("192.168.1.1");
		sysUser.setLastTime(new Date());
		sysUser.setAddTime(new Date());;
		sysUserService.save(sysUser);
	}
	
	@Test
	public void deleteUserTest(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 9999);
		List<SysUser> list = sysUserService.queryAll(params);
		SysUser sysUser = list.get(list.size()-1);
		sysUserService.delete(sysUser.getId());
	}
	
	@Test
	public void queryAllTest(){
		//List list = sqlSession.selectList("queryAll", null, new RowBounds(0, 10));
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 9999);
		List<SysUser> list = sysUserService.queryAll(params);
		System.out.println(list.size());
	}
}
