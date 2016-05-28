package cn.core.framework.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.business.backend.model.Role;
import cn.core.framework.common.Constant;

/**
 * jstl自定义函数处理类
 * 
 * @author JC
 * @date 2015-3-26
 */
public class FuntionsUtil {

	/**
	 * 判断是否有资源访问权限
	 * 
	 * @param json
	 *            权限集合
	 * @param resId
	 *            资源id
	 * @return boolean
	 */
	public static boolean hasResourceAuth(String json, int resId) {
		if (json == null)
			return false;

		JSONObject obj = JSON.parseObject(json);
		String key = String.valueOf(resId);
		if (obj.containsKey(key) 
				&& obj.get(key).equals(String.valueOf(Constant.CONST_RES_ACCESS_AUTH_YES))) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否有角色roleId
	 * @param roles 角色集合
	 * @param roleId 角色id
	 * @return boolean
	 */
	public static boolean hasRole(List<Role> roles, int roleId){
		for (Role role : roles) {
			if(role.getId() == roleId)
				return true;
		}
		return false;
	}
	
	/**
	 * 根据网站语言类型，显示对应语言的字符，主要用于数据库中的多语言支持，
	 * 页面中静态的文字由Spring国际化支持
	 * @param nameZh
	 * @param nameEn
	 * @param siteLang
	 * @return string
	 */
	public static String convertLang(String nameZh, String nameEn, String siteLang){
		if(siteLang != null && siteLang.equals(Constant.CONST_LANG_EN)){
			return nameEn;
		}
		return nameZh;
	}
}
