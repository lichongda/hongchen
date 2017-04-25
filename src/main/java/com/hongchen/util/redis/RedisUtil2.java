package com.hongchen.util.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author lu.jianhao 
 * rediscache工具类
 */
@SuppressWarnings("unchecked")
@Configuration
@Service
public class RedisUtil2 {
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	public RedisTemplate<?,?> getRedisTemplate(){
		return redisTemplate;
	}
	
	@SuppressWarnings("rawtypes")
	public RedisTemplate getNormalRedisTemplate(){
		return redisTemplate;
	}
	
	/**
	 * 批量删除对应的value
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}
	
	/**
	 * 获取库的大小
	 * @return
	 */
	public long dbSize() {
	        return (Long) redisTemplate.execute(new RedisCallback() {
	            public Long doInRedis(RedisConnection connection) throws DataAccessException {
	                return connection.dbSize();
	            }
	        });
	}
	
	/**
	 * 清空所有的redis的数据
	 * @return
	 */
	public String flushDB() {
        return (String) redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }
	
	/**
	 * 批量删除key
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 批量获取keys
	 * @param string
	 * @return
	 */
	public Set keys(String string) {
		Set keys = redisTemplate.keys(string);
		return keys;
	}
	
	public Set<String> getKeys(final String pattern){
		Set<String> keys = redisTemplate.keys(pattern);
		return keys;
	}
	
	/**
	 * 删除对应的value
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}
    
	/**
	 * 删除对应的value
	 * @param byteKey
	 */
	public void remove(final byte[] byteKey) {
		  redisTemplate.execute(new RedisCallback() {
	            public Long doInRedis(RedisConnection connection) throws DataAccessException {
	                 return connection.del(byteKey);
	            }
	        });
	}
	
	
	/**
	 * 判断缓存中是否有对应的value
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}
    
	public boolean exists(final byte[] byteKey){
		return redisTemplate.hasKey(byteKey);
	}
	
	/**
	 * 读取缓存
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}
	
	public byte[] getValue(final String key) {
		byte[] result = (byte[]) redisTemplate.opsForValue().get(key);
		return result;
	}
	
//	/**
//	 * 获取二进制的对象
//	 * @param byteKey
//	 * @return
//	 */
//	public byte[] get(byte[] byteKey) {
//		Object result = null;
//		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
//		result = operations.get(byteKey);
//		return (byte[]) result;
//	}

	
	 public byte[] get(final byte[] key) {
	        return (byte[]) redisTemplate.execute(new RedisCallback() {
	            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
	                 return connection.get(key);
	            }
	        });
	    }
	
	/**
	 * 写入缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean set(final String key, byte[] value, Long expireTime) {
		boolean result = false;
		try {
			redisTemplate.opsForValue().set(key, value);;
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
     
	
	/**
     * @param keys
     */
    public long del(final String... keys) {
        return (Long) redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long result = 0;
                for (int i = 0; i < keys.length; i++) {
                    result = connection.del(keys[i].getBytes());
                }
                return result;
            }
        });
    }
 
        /**
         * @param key
         * @param value
         * @param liveTime(单位为：s)
         */
        public void set(final byte[] key, final byte[] value, final long liveTime) {
            redisTemplate.execute(new RedisCallback() {
                public Long doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.set(key, value);
                    if (liveTime > 0) {
                        connection.expire(key, liveTime);
                    }
                    return 1L;
                }
            });
        }
 
        /**
         * @param key
         * @param value
         * @param liveTime
         */
        public void set(String key, String value, long liveTime) {
            this.set(key.getBytes(), value.getBytes(), liveTime);
        }
 
        /**
         * @param key
         * @param value
         */
        public void set(String key, String value) {
            this.set(key, value, 0L);
        }
 
        public Object getAttribute(String name) {
        	Object value = null;
    		final byte[] key = this.getRedisTemplate().getStringSerializer().serialize(name);
    		byte[] byteValue = (byte[]) this.getRedisTemplate().execute(new RedisCallback<Object>() {
    			public Object doInRedis(RedisConnection connection) throws DataAccessException {
    				return connection.get(key);
    			}
    		});
    		if(byteValue!=null&&byteValue.length>0){
    			 value = SerializeUtils.deserialize(byteValue);
    		}
    		return value;
    	}
        
        /**
         * 判断当前的值是否已经存在
         * @param sessionId
         * @return
         */
		public boolean judgeExistSession(String sessionId) {
			final byte[] key = this.getRedisTemplate().getStringSerializer().serialize(sessionId);
    		byte[] byteValue = (byte[]) this.getRedisTemplate().execute(new RedisCallback<Object>() {
    			public Object doInRedis(RedisConnection connection) throws DataAccessException {
    				return connection.get(key);
    			}
    		});
    		if(byteValue!=null && byteValue.length>0){
    			return true;
    		}
			return false;
		}
}
