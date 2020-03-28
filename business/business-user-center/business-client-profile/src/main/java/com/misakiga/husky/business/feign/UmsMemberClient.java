package com.misakiga.husky.business.feign;

import com.misakiga.husky.configuration.FeignRequestConfiguration;
import com.misakiga.husky.uc.model.SysUserAuthentication;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author MISAKIGA
 */
@FeignClient(value = "business-profile",path = "member",configuration = FeignRequestConfiguration.class)
public interface UmsMemberClient {

    /**
     * 通过用户名查询用户及其角色信息
     *
     * @param phoneNumber 手机号
     * @return UseVo 对象
     */
    @GetMapping("/findUserByPhoneNumber/{phoneNumber}")
    SysUserAuthentication findUserByPhoneNumber(@PathVariable String phoneNumber);
}
