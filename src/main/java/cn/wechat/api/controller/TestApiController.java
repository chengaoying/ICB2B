package cn.wechat.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 测试Api控制器
 * @author JC
 * @date 2016-5-28
 * @since v1.0
 */
@Controller
public class TestApiController extends BaseApiController{

	/**
	 * 返回json数据
	 * @return JSONObject
	 */
	@RequestMapping("/test1.html")
	public @ResponseBody JSONObject test1(){
		JSONObject json = new JSONObject();
		json.put("name", "jc你好");
		json.put("age", 18);
		
		return json;
	}
	
	/**
	 * 返回json数组
	 * @return JSONObject
	 */
	@RequestMapping("/test2.html")
	public @ResponseBody JSONArray test2(){
		JSONArray arr = new JSONArray();
		JSONObject json1 = new JSONObject();
		json1.put("name", "jc");
		json1.put("age", 18);
		
		JSONObject json2 = new JSONObject();
		json2.put("name", "jc2");
		json2.put("age", 19);
		
		arr.add(json1);
		arr.add(json2);
		return arr;
	}
}
