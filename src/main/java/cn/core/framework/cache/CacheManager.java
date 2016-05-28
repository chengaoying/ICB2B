package cn.core.framework.cache;

/**
 * 缓存接口，缓存数据使用哈希类型数据结构存储。
 * @author CGY
 * @date 2015-1-20
 */
public interface CacheManager {

	/**
	 * 获取缓存
	 * @param key  缓存key
	 * @param field  缓存map中的键
	 * @return object
	 */
	public Object getCache(String key, String field);
	
	/**
	 * 添加缓存
	 * @param cache
	 */
	public void saveCache(String key, String field, Object value, int expire);
	
	/**
	 * 更新缓存
	 * @param cache
	 */
	public void updateCache(String key, String field, Object value, int expire);
	
	/**
	 * 删除缓存
	 * @param key
	 */
	public void delCache(String key);
}
