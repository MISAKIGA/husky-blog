package com.misakiga.husky.cloud.feign;

import com.misakiga.husky.cloud.feign.fallback.MessageFeignFallback;
import com.misakiga.husky.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author MISAKIGA
 */
@FeignClient(value = "cloud-message", path = "message", configuration = FeignRequestConfiguration.class, fallback = MessageFeignFallback.class)
public interface MessageFeign {

}
