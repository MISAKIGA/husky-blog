package com.misakiga.husky.business.controller;

import com.misakiga.husky.provider.api.SysAuthorizeService;
import com.misakiga.husky.uc.model.Authorize;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  授权控制类，管理用户角色授权信息
 * @author MISAKIGA
 * @date 2020-3-14
 */
@RestController
@RequestMapping("/authorize")
public class SysAuthorizeController {

    @Reference(version = "1.0.0")
    private SysAuthorizeService authorizeService;

    @GetMapping("/get")
    public Authorize get(Long userId){
        return this.authorizeService.getAuthorize(userId);
    }
}
