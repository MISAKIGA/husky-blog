package com.misakiga.husky.business.feign;

import com.misakiga.husky.configuration.FeignRequestConfiguration;
import com.misakiga.husky.uc.model.Authorize;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author MISAKIGA
 */
@FeignClient(value = "business-oauth2", path = "oauth2",configuration = FeignRequestConfiguration.class)
public interface SysAuthorizeClient {

    /**
     * 获取授权用户
     * @param userId
     * @return
     */
    @GetMapping(value = "/oauth2/authorize/get")
    Authorize getAuthorize(@RequestParam("userId") Long userId);
}
