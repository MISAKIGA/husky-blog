package com.misakiga.husky.provider.service.fallback;

import com.misakiga.husky.provider.domain.UmsAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 用户服务提供者熔断器
 * @author MISAKIGA
 */
public class UmsAdminServiceFallback {

    private static final Logger logger = LoggerFactory.getLogger(UmsAdminServiceFallback.class);

    public static UmsAdmin getByUsernameFallback(String username, Throwable ex){

        logger.warn("invoke getByUsernameFallback" + ex.getClass().getTypeName());
        return null;
    }
}
