package cn.bussiness.frontend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import base.test.BaseTest;
import cn.business.backend.model.Role;
import cn.business.backend.model.User;
import cn.business.backend.service.UserService;
import cn.core.framework.util.DateUtil;

public class UserServiceTest extends BaseTest{
	//private static Log log = LogFactory.getLog(userServiceTest.class);

	@Resource
	private UserService userService;
	
	@Test
	public void loadByIdTest(){
		User user = userService.loadById(1);
		for (Role r : user.getRoles()) {
			System.out.println(r.getNameZh());
		}
		System.out.println(user.getName());
	}
	
	@Test
	public void loadByNameTest(){
		User user = userService.loadByName("222");
		for (Role r : user.getRoles()) {
			System.out.println(r.getNameZh());
		}
		System.out.println(user.getName());
	}
	
	
	@Test
	public void updateUserTest(){
		User User = userService.loadById(1);
		User.setName("采购商");
		userService.update(User);
	}
	
	@Test
	public void saveUserTest(){
		User User = new User();
		User.setName("222");
		User.setPassword("123456");
		User.setEmail("test@qq.com");
		User.setAddTime(DateUtil.formatDate(new Date()));
		User.setLevel((byte)0);
		User.setMobile("123");
		User.setOtherAccount("t");
		User.setPoint(123);
		User.setStatus((byte)1);
		User.setRealName("jc");
		userService.save(User);
		System.out.println(User.getId());
	}
	
	@Test
	public void queryAllTest(){
		//List list = sqlSession.selectList("queryAll", null, new RowBounds(0, 10));
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 9999);
		List<User> list = userService.queryAll(params);
		System.out.println(list.size());
	}
	
	@Test
	public void delUserRoleTest(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("userId", 2);
		params.put("roleId", 3);
		userService.delUserRole(params);
	}
}
