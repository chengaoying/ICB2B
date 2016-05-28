package cn.business.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.business.backend.model.UserSeller;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserSellerServiceTest {
	//private static Log log = LogFactory.getLog(SysUserServiceTest.class);

	@Resource
	private UserSellerService userSellerService = null;
	
	@Test
	public void queryAllTest(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 99);
		List<UserSeller> list = userSellerService.queryAll(params);
		System.out.println(list.size());
	}
}
