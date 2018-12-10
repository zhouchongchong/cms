package com.cloudminds.cms.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class RedisUtil {
    private final static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private final static String NAMESPACE = "cms:";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> valueOps;
    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;
    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOps;
    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zsetOps;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOps;

    public void setValue(String key, Object value) {
        valueOps.set(NAMESPACE + key, value);
    }

    public void setValue(String key, Object value, long time) {
        valueOps.set(NAMESPACE + key, value, time, TimeUnit.SECONDS);
    }

    public void multiSet(Map<String, String> map) {
        valueOps.multiSet(map);
        List<Object> results = redisTemplate.executePipelined(
                (RedisCallback<Object>) connection -> {
                    StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                    stringRedisConn.mSetString(map);
                    return null;
                });
    }

    public void multiSet2(Map<String, String> map) {
        List<Object> results = redisTemplate.executePipelined(
                (RedisCallback<Object>) connection -> {
                    StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
                    stringRedisConn.mSetString(map);
                    return null;
                });
    }

    public void expire(String key, long time) {
        redisTemplate.expire(NAMESPACE + key, time, TimeUnit.SECONDS);
    }

    public Object getValue(String key) {
        return valueOps.get(NAMESPACE + key);
    }

    public String getStrValue(String key) {
        return (String) valueOps.get(NAMESPACE + key);
    }

    public Long setSet(String key, String... value) {
        return setOps.add(NAMESPACE + key, value);
    }

    public Set<String> getSetMembers(String key) {
        return setOps.members(NAMESPACE + key);
    }

    public Boolean setZSet(String key, String value, double score) {
        return zsetOps.add(NAMESPACE + key, value, score);
    }

    public Double getZSetScore(String key, String value) {
        return zsetOps.score(NAMESPACE + key, value);
    }

    public void deleteKey(String key) {
        redisTemplate.delete(NAMESPACE + key);
    }

    private Collection<String> prefixKeys(Collection<String> keys) {
        return keys.stream().map(key -> NAMESPACE + key).collect(Collectors.toList());
    }

    public void deleteKeys(Collection<String> keys) {
        redisTemplate.delete(prefixKeys(keys));
    }

    public List<Object> multiGet(Collection<String> keys) {
        List<Object> ret = valueOps.multiGet(prefixKeys(keys));
        if (ret == null) {
            ret = new ArrayList();
        }
        return ret;
    }

    public void increment(String key, long delta) {
        valueOps.increment(NAMESPACE + key, delta);
    }

    public void setBit(String key, long offset, boolean value) {
        valueOps.setBit(NAMESPACE + key, offset, value);
    }

    public boolean getBit(String key, long offset) {
        return valueOps.getBit(NAMESPACE + key, offset);
    }


}
