package com.hongchen.util.redis;

import com.hongchen.util.spring.Springs;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 缓存工具类
 * Created by lichongda
 */
@SuppressWarnings("unchecked")
public class RedisUtil {
    public static final StringRedisTemplate stringRedisTemplate = Springs.getBean(StringRedisTemplate.class);

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    public static void set(String key, String value, int timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.HOURS);
    }

    public static void set(String key, String value) {
        set(key,value,24*7);
    }


    public static void setMinutes(String key, String value, int timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MINUTES);
    }

    public static void setHours(String key, String value, int timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.HOURS);
    }

    public static void setDays(String key, String value, int timeout) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.DAYS);
    }

    public static String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public static Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    public static void del(String key) {
        stringRedisTemplate.delete(key);
    }

    public static void clearCache(){
        Set<String> keys = stringRedisTemplate.keys("ChargerlinkCache:*");
        stringRedisTemplate.delete(keys);
    }

    public static void clearCache(String keyPrefix){
        if(StringUtils.isEmpty(keyPrefix)){
            clearCache();
            return;
        }
        Set<String> keys = stringRedisTemplate.keys("ChargerlinkCache:" + keyPrefix + ":*");
        stringRedisTemplate.delete(keys);
    }

    /**
     * 入队
     *
     * @param key
     * @param value
     * @return
     */
    public static Long rightPush(String key, String value) {
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }


    /**
     * 第一个元素
     *
     * @param key
     * @param value
     * @return
     */
    public static Long leftPush(String key, String value) {
        return stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 出队
     *
     * @param key
     * @return
     */
    public static String leftPop(String key) {
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    /**
     * 根据key删除vaue
     *
     * @param key
     * @return
     */
    public static Long remove(String key,String value) {
        return stringRedisTemplate.opsForSet().remove(key, value);
    }
    /**
     * 栈/队列长
     *
     * @param key
     * @return
     */
    public static Long length(String key) {
        return stringRedisTemplate.opsForList().size(key);
    }

    /**
     * 范围检索
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static List<String> range(String key, int start, int end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
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
    public Object getCache(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    public byte[] getValue(final String key) {
        byte[] result = (byte[]) redisTemplate.opsForValue().get(key);
        return result;

    }

}
