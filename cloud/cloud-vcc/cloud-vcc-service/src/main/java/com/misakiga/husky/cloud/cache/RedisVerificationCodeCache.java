package com.misakiga.husky.cloud.cache;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


/**
 * Redis缓存，会自动过期
 */
@Component
public class RedisVerificationCodeCache implements VerificationCodeCache{

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public RedisVerificationCodeCache(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String cacheName, String key, String value, long expire){
        stringRedisTemplate.opsForValue().set(cacheName + ":" + key,value);
        stringRedisTemplate.expire(cacheName + ":" + key,expire, TimeUnit.MILLISECONDS);
    }

    @Override
    public void remove(String cacheName, String key) {
        stringRedisTemplate.delete(cacheName + ":" + key);
    }

    @Override
    public String get(String cacheName, String key){
        return stringRedisTemplate.opsForValue().get(cacheName + ":" + key);
    }

    @Override
    public boolean validate(String cacheName, String key, String value){
        String originValue = this.get(cacheName,key);
        if(!StringUtils.isEmpty(originValue)){
            return originValue.equals(value);
        }
        return  false;
    }

    @Override
    public boolean isExpire(String cacheName, String key) {
        return stringRedisTemplate.opsForValue().get(cacheName + ":" + key) == null;
    }
}
