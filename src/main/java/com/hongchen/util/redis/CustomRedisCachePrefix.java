package com.hongchen.util.redis;

import org.springframework.data.redis.cache.RedisCachePrefix;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by lichongda on 2016/8/11.
 */
public class CustomRedisCachePrefix implements RedisCachePrefix {

    private final StringRedisSerializer serializer = new StringRedisSerializer();
    private final String delimiter;

    public CustomRedisCachePrefix() {
        this(":");
    }

    public CustomRedisCachePrefix(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public byte[] prefix(String cacheName) {
        return serializer.serialize((delimiter != null ? delimiter.concat(cacheName).concat(":") : cacheName.concat(":")));
    }
}
