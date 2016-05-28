package cn.business.backend.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.business.backend.model.Resources;
import cn.business.backend.model.Role;
import cn.business.backend.model.User;
import cn.core.framework.common.Constant;

/**
 * 服务类：用户中心
 * @author JC
 * @date 2015-8-1
 */
@Service("userCenterService")
public class UserCenterService{
	
	@Resource
	private ResourcesService resourcesService;
	
	/**
	 * 获取用户有访问权限的资源，并保存到session
	 * @param user 访问用户
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<Resources> getUserResources(User user, HttpSession session){
		List<Resources> resList = null;
		Object ress = session.getAttribute(user.getId()+"_resList");
		if(ress != null){
			resList = (List<Resources>) ress;
		}
		 
		StringBuffer ids = new StringBuffer("(");;
		StringBuffer jsonStr = new StringBuffer("[");
		
		for (Role role : user.getRoles()) {
			jsonStr.append(role.getAuth()).append(",");
		}
		String _jsonStr = jsonStr.substring(0, jsonStr.length()-1) + "]";
		
		JSONArray jsonArr = JSONArray.parseArray(_jsonStr);
		Iterator<?> it = jsonArr.iterator();
		while(it.hasNext()){
			JSONObject obj = (JSONObject) it.next();
			Iterator<?> it2 = obj.entrySet().iterator();
			while (it2.hasNext()) {
				String str = it2.next().toString();
				String key = str.split("=")[0];
				String value = str.split("=")[1];
				if(value.equals(String.valueOf(Constant.CONST_RES_ACCESS_AUTH_YES)))
					ids.append(key).append(",");
			}
		}
		String _ids = ids.substring(0, ids.length()-1) + ")";
		if(_ids.length() <= 1) 
			return null;
		
		HashMap<String,Object> params = new HashMap<>();
		params.put("ids", _ids);
		resList = resourcesService.queryAll(params);
		session.setAttribute(user.getId()+"_resList", resList);
		return resList;
	}
}
