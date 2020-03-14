package com.misakiga.husky.business.feign;

import com.misakiga.husky.configuration.FeignRequestConfiguration;
import com.misakiga.husky.uc.model.Authorize;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author MISAKIGA
 */
@FeignClient(value = "business-profile", path = "authorize", configuration = FeignRequestConfiguration.class)
public interface SysAuthorizeClient {

    @GetMapping("/get")
    public Authorize get(Long userId);
}
