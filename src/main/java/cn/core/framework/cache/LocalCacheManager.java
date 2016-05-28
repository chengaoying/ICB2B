/*package cn.core.framework.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dashuju.redis.client.util.SerializeUtil;
import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;

*//**
 * 本地缓存实现
 * @author CGY
 * @date 2015-1-20
 *//*
public class LocalCacheManager implements CacheManager {

	protected static Log log = LogFactory.getLog(LocalCacheManager.class);
	
	//定义缓存类型
	public static final String CACHE_KEY = "local";
	//设置初始化容量
	private static final int CAPACITY = 512;
	//设置最大缓存个数
	private static final int WEIGHT = 10000;
	//存放byte[]类型主要是为了防止缓存数据遭到污染
	private static Map<String, Map<String,byte[]>> localCache = new ConcurrentLinkedHashMap.Builder<String, Map<String,byte[]>>()
			.initialCapacity(CAPACITY).maximumWeightedCapacity(WEIGHT).build();
	
	@Override
	public synchronized void saveCache(String key, String field, Object value, int expire) {
		Map<String,byte[]> cacheMap = localCache.get(key);
		if(cacheMap == null){
			cacheMap = new HashMap<>();
		}
		cacheMap.put(field, assembleValue(value,expire));
		localCache.put(key, cacheMap);
		log.debug("save cache, key:"+key+", field:"+field+", value:"+value+", expire:"+expire);
	}

	@Override
	public synchronized void updateCache(String key, String field, Object value, int expire) {
		log.debug("update cache, key:"+key+", field:"+field+", value:"+value+", expire:"+expire);
		this.saveCache(key,field,value,expire);
	}

	@Override
	public synchronized void delCache(String key) {
		localCache.remove(key);
		log.debug("del cache, key:"+key);
	}
	
	@Override
	public Object getCache(String key, String field) {
		Map<String,byte[]> cacheMap = localCache.get(key);
		if(cacheMap == null) return null;
		
		byte[] bytes = cacheMap.get(field);
		if(bytes == null) return null;
		
		Object obj = disassembleValue(key,bytes);
		log.debug("get cache, key:"+key+", field:"+field+", value:"+obj);
		return obj;
		
	}
	
	*//**
	 * 组装缓存对象
	 * @param object  被序列化对象
	 * @param expire  系列化对象过期时间
	 * @return byte[] 字节数组
	 *//*
	protected byte[] assembleValue(Object object, long expire) {
    	byte[] value = SerializeUtil.serialize(object);
    	if(value == null) return null;
    	
        byte[] cacheValue = new byte[value.length + 8];
        long expireTime = Long.MAX_VALUE;
        if (expire >= 0) {
            expireTime = System.currentTimeMillis()/1000 + expire;
        }
        cacheValue[0] = (byte) ((expireTime >> 56) & 0xFF);
        cacheValue[1] = (byte) ((expireTime >> 48) & 0xFF);
        cacheValue[2] = (byte) ((expireTime >> 40) & 0xFF);
        cacheValue[3] = (byte) ((expireTime >> 32) & 0xFF);
        cacheValue[4] = (byte) ((expireTime >> 24) & 0xFF);
        cacheValue[5] = (byte) ((expireTime >> 16) & 0xFF);
        cacheValue[6] = (byte) ((expireTime >> 8) & 0xFF);
        cacheValue[7] = (byte) ((expireTime) & 0xFF);
        System.arraycopy(value, 0, cacheValue, 8, value.length);
        return cacheValue;
    }

    *//**
     * 拆解缓存对象
     * @param key
     * @param cacheValue
     * @return
     *//*
    protected Object disassembleValue(String key, byte[] cacheValue) {
        if (cacheValue == null) {
            return null;
        }
        long expireTime = ((long) (cacheValue[0] & 0xFF) << 56)
                  + ((long) (cacheValue[1] & 0xFF) << 48)
                  + ((long) (cacheValue[2] & 0xFF) << 40)
                  + ((long) (cacheValue[3] & 0xFF) << 32)
                  + ((long) (cacheValue[4] & 0xFF) << 24)
                  + ((long) (cacheValue[5] & 0xFF) << 16)
                  + ((long) (cacheValue[6] & 0xFF) << 8)
                  + ((long) (cacheValue[7] & 0xFF));
        if (expireTime >= System.currentTimeMillis()/1000) {
            byte[] value = new byte[cacheValue.length - 8];
            System.arraycopy(cacheValue, 8, value, 0, value.length);
            return SerializeUtil.unserialize(value);
        } else {
        	delCache(key);
            return null;
        }
    }

}
*/