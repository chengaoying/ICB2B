/*package cn.core.framework.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.dashuju.redis.client.RedisHashTypeCommand;
import com.dashuju.redis.client.ShardedJedisSentinelPool;
import com.dashuju.redis.client.util.SerializeUtil;
import cn.core.framework.common.SpringContextUtil;

*//**
 * Redis缓存实现
 * @author CGY
 * @date 2015-1-20
 *//*
public class RedisCacheManager implements CacheManager {

	protected static Log log = LogFactory.getLog(RedisCacheManager.class);
	
	//定义缓存类型
	public static final String CACHE_KEY = "redis";
	
	protected RedisHashTypeCommand command;
	
	private void initRedisClientCommand(){
		command = new RedisHashTypeCommand((ShardedJedisSentinelPool)SpringContextUtil.getBean("shardedJedisPool"));
	}
	
	@Override
	public void saveCache(String key, String field, Object value, int expire) {
		initRedisClientCommand();
		command.hsetnx(key, field, value);
		log.debug("save cache, key:"+key+", field:"+field+", value:"+value);
		
		command.expire(SerializeUtil.serialize(key), expire);
		log.debug("set cache expire time, key:"+key+", expire:"+expire);
	}

	@Override
	public void updateCache(String key, String field, Object value, int expire) {
		log.debug("update cache, key:"+key+", field:"+field+", value:"+value);
		this.saveCache(key,field,value,expire);
	}

	@Override
	public void delCache(String key) {
		initRedisClientCommand();
		command.del(SerializeUtil.serialize(key));
		log.debug("del cache, key:"+key);
	}
	
	@Override
	public Object getCache(String key, String field) {
		initRedisClientCommand();
		Object object = command.hget(key, field);
		log.debug("get cache, key:"+key+", field:"+field+", value:"+object);
		return object;
		
	}

}
*/