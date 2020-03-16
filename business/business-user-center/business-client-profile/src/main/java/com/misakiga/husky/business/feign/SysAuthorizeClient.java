package com.misakiga.husky.business.feign;

import com.misakiga.husky.configuration.FeignRequestConfiguration;
import com.misakiga.husky.uc.model.Authorize;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author MISAKIGA
 */
@FeignClient(value = "business-profile", path = "authorize", configuration = FeignRequestConfiguration.class)
public interface SysAuthorizeClient {

    @GetMapping("/get/{userid}")
    Authorize get(@PathVariable("userid") Long userId);
}
