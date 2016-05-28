package cn.bussiness.frontend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import base.test.BaseTest;
import cn.business.backend.model.Role;
import cn.business.backend.service.RoleService;

public class RoleServiceTest extends BaseTest {
	//private static Log log = LogFactory.getLog(RoleServiceTest.class);

	@Resource
	private RoleService RoleService;
	
	@Test
	public void loadByIdTest(){
		Role Role = RoleService.loadById(1);
		System.out.println(Role.getNameZh());
	}
	
	@Test
	public void updateRoleTest(){
		Role Role = RoleService.loadById(1);
		Role.setNameZh("系统管理员");
		RoleService.update(Role);
	}
	
	@Test
	public void saveRoleTest(){
		Role Role = new Role();
		Role.setNameZh("经销商");
		Role.setNameEn("seller");
		Role.setAuth("123456");
		RoleService.save(Role);
		System.out.println(Role.getId());
	}
	
	@Test
	public void queryAllTest(){
		//List list = sqlSession.selectList("queryAll", null, new RowBounds(0, 10));
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 9999);
		List<Role> list = RoleService.queryAll(params);
		System.out.println(list.size());
	}
	
	@Test
	public void deleteTest(){
		RoleService.delete(3);
	}
}
