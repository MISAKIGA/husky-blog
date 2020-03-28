package com.misakiga.husky.cloud.configure;

import com.misakiga.husky.cloud.cache.GeneralVerificationCodeCache;
import com.misakiga.husky.cloud.cache.RedisVerificationCodeCache;
import com.misakiga.husky.cloud.cache.VerificationCodeCache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author MISAKIGA
 */
@Configuration
public class VerificationCacheConfiguration {

    @Bean
    @ConditionalOnBean(RedisOperations.class)
    public RedisVerificationCodeCache redisVerificationCodeCache(StringRedisTemplate stringRedisTemplate){
        return new RedisVerificationCodeCache(stringRedisTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(VerificationCodeCache.class)
    public GeneralVerificationCodeCache generalVerificationCodeCache(CacheManager cacheManager){
        return new GeneralVerificationCodeCache(cacheManager);
    }
}
