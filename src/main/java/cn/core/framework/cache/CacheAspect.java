package cn.core.framework.cache;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * 缓存aop接口
 * @author CGY
 * @date 2016-1-27
 */
public class CacheAspect {
	
	/**
	 * 缓存类型，redis-分布式缓存，local-本地缓存
	 */
	private String cacheType = "redis";
	
	/**
	 * 是否开启缓存 true:开启，false:禁用
	 */
	private boolean openCache = true;
	
	/**
	 * 缓存失效时间，单位：秒（s），-1表示永久有效
	 */
	private int expireTime = -1;
	
	/**
	 * 缓存处理
	 * @param pjpParam
	 * @return
	 * @throws Throwable
	 */
	public Object doCache(ProceedingJoinPoint pjpParam) throws Throwable {
		Signature sig = pjpParam.getSignature();
		if (sig instanceof MethodSignature && openCache) {
			Method method = ((MethodSignature) sig).getMethod();
			Object[] args = pjpParam.getArgs();
			String cacheKey = method.getDeclaringClass().getName(); 
			String field =  method.getName();
			for (Object object : args) {
				if (object != null) {
					field += object.toString();
				} else {
					field += "NULL";
				}
			}

			CacheManager cache = CacheContext.getCache(cacheType);
			if(isQuery(method.getName())){
	            Object value = cache.getCache(cacheKey,field);
	            if (value == null) {
	                value = pjpParam.proceed();
	                cache.saveCache(cacheKey, field, value, expireTime);
	            }
	            return value;
			}else{
				if(isUpdateOrSave(method.getName()) || isDelete(method.getName())){
					cache.delCache(cacheKey);
				}
				return pjpParam.proceed();
			}
		} else {
			return pjpParam.proceed();
		}
	}

	/**
	 * 判断方法是不是属于查询类型的方法
	 * @param method 待查询的方法
	 * @return boolean
	 */
	protected boolean isQuery(String method){
		if(method.indexOf("load") != -1 || method.indexOf("query") != -1
				|| method.indexOf("select") != -1 || method.indexOf("get") != -1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断方法是不是属于修改或保存类型的方法
	 * @param method 待查询的方法
	 * @return boolean
	 */
	protected boolean isUpdateOrSave(String method){
		if(method.indexOf("update") != -1 || method.indexOf("save") != -1
				|| method.indexOf("set") != -1 || method.indexOf("put") != -1){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断方法是不是属于删除类型的方法
	 * @param method 待查询的方法
	 * @return boolean
	 */
	protected boolean isDelete(String method){
		if(method.indexOf("delete") != -1 || method.indexOf("del") != -1){
			return true;
		}else{
			return false;
		}
	}

	public String getCacheType() {
		return cacheType;
	}

	public void setCacheType(String cacheType) {
		this.cacheType = cacheType;
	}

	public boolean isOpenCache() {
		return openCache;
	}

	public void setOpenCache(boolean openCache) {
		this.openCache = openCache;
	}

	public int getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(int expireTime) {
		this.expireTime = expireTime;
	}
}