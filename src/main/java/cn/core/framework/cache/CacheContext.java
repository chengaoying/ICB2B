package cn.core.framework.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存操作管理上下文
 * @author CGY
 * @date 2016-1-27
 */
public class CacheContext {
	
	private static Map<String, CacheManager> cacheMap = new HashMap<String, CacheManager>();

	static{
		//register(RedisCacheManager.CACHE_KEY, new RedisCacheManager());
		//register(LocalCacheManager.CACHE_KEY, new LocalCacheManager());
	}
	
	/**
	 * 获取具体的缓存
	 * 
	 * @param key
	 * @return
	 */
	public static CacheManager getCache(String key) {
		return cacheMap.get(key);
	}

	/**
	 * 注册新的缓存
	 * 
	 * @param key
	 * @param cache
	 */
	public static void register(String key, CacheManager cache) {
		cacheMap.put(key, cache);
	}

}
