package cn.bussiness.frontend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import base.test.BaseTest;
import cn.business.backend.model.Resources;
import cn.business.backend.service.ResourcesService;

public class ResourcesServiceTest extends BaseTest {
	//private static Log log = LogFactory.getLog(ResourcesServiceTest.class);

	@Resource
	private ResourcesService ResourcesService;
	
	@Test
	public void loadByIdTest(){
		Resources resources = ResourcesService.loadById(1);
		System.out.println(resources.getNameZh());
	}
	
	@Test
	public void updateResourcesTest(){
		Resources Resources = ResourcesService.loadById(1);
		Resources.setNameZh("测试");
		Resources.setNameEn("test");
		ResourcesService.update(Resources);
	}
	
	@Test
	public void saveResourcesTest(){
		Resources Resources = new Resources();
		Resources.setNameZh("测试");
		Resources.setNameEn("test");
		Resources.setUrl("url");
		Resources.setType((byte)1);
		ResourcesService.save(Resources);
		System.out.println(Resources.getId());
	}
	
	@Test
	public void queryAllTest(){
		//List list = sqlSession.selectList("queryAll", null, new RowBounds(0, 10));
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("offset", 0);
		params.put("limit", 9999);
		List<Resources> list = ResourcesService.queryAll(params);
		System.out.println(list.size());
	}
	
	@Test
	public void deleteTest(){
		ResourcesService.delete(1);
	}
}
