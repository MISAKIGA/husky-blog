package com.misakiga.husky.business.controller;

import com.misakiga.husky.provider.api.UmsMemberService;
import com.misakiga.husky.uc.model.SysUserAuthentication;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MISAKIGA
 */
@RestController
@RequestMapping("user")
public class UmsMemberController {


    @Reference(version = "1.0.0")
    private UmsMemberService umsMemberService;
    /**
     * 通过用户名查询用户及其角色信息
     *
     * @param phoneNumber 手机号
     * @return UseVo 对象
     */
    @GetMapping("/findUserByPhoneNumber/{phoneNumber}")
    public SysUserAuthentication findUserByPhoneNumber(@PathVariable String phoneNumber) {
        return umsMemberService.findUserByPhoneNumber(phoneNumber);
    }
}
