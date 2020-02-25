package com.misakiga.husky.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.misakiga.husky.business.BusinessStatus;
import com.misakiga.husky.business.controller.fallback.ProfileControllerFallback;
import com.misakiga.husky.business.dto.UmsAdminDTO;
import com.misakiga.husky.business.dto.params.IconParam;
import com.misakiga.husky.business.dto.params.PasswordParam;
import com.misakiga.husky.business.dto.params.ProfileParam;
import com.misakiga.husky.commons.dto.ResponseResult;
import com.misakiga.husky.provider.api.UmsAdminService;
import com.misakiga.husky.provider.domain.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 个人信息管理s
 */
@RestController
@RequestMapping(value = "profile")
public class ProfileController {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping(value = "/info/{username}")
    @SentinelResource(value = "info", fallback = "infoFallback", fallbackClass = ProfileControllerFallback.class)
    public ResponseResult<UmsAdminDTO> info(@PathVariable String username){
        UmsAdmin umsAdmin = umsAdminService.get(username);
        UmsAdminDTO dto = new UmsAdminDTO();
        BeanUtils.copyProperties(umsAdmin, dto);

        return  new ResponseResult<UmsAdminDTO>(BusinessStatus.OK.getCode(),"查询用户信息",dto);
    }

    @PostMapping(value = "update")
    public ResponseResult<Void> update(@RequestBody ProfileParam profileParam){
        UmsAdmin umsAdmin  = new UmsAdmin();
        //将ProfileParam里的值复制到umsAdmin里
        BeanUtils.copyProperties(profileParam,umsAdmin);
        int result = umsAdminService.udate(umsAdmin);

        //成功
        if(result > 0){
            return  new ResponseResult<Void>(BusinessStatus.OK.getCode(),"更新个人信息成功 ");
        }

        //失败
        else{
            return  new ResponseResult<Void>(BusinessStatus.FAIL.getCode(),"更新个人信息失败 ");
        }
    }

    /**
     * 修改密码
     * @param passwordParam
     * @return
     */
    @PostMapping(value = "modify/password")
    public ResponseResult<Void> modifyPassword(@RequestBody PasswordParam passwordParam){
        UmsAdmin umsAdmin = umsAdminService.get(passwordParam.getUsername());

        //旧密码正确
        if(passwordEncoder.matches(passwordParam.getOldPassword(), umsAdmin.getPassword())){
            int result = umsAdminService.modifyPassword(umsAdmin.getUsername(),passwordParam.getNewPassword());
            if(result > 0){
                return new ResponseResult<Void>(BusinessStatus.OK.getCode(),"修改密码成功");
            }
        }

        //旧密码错误
        else{
            return new ResponseResult<Void>(BusinessStatus.FAIL.getCode(),"旧密码不匹配,请重试");
        }
        return new ResponseResult<Void>(BusinessStatus.FAIL.getCode(),"修改密码失败");
    }

    @PostMapping(value = "modify/icon")
    public ResponseResult<Void> modifyIcon(@RequestBody IconParam iconParam){
        int result = umsAdminService.modifyIcon(iconParam.getUsername(), iconParam.getPath());

        //成功
        if(result > 0){
            return  new ResponseResult<Void>(BusinessStatus.OK.getCode(),"修改头像成功 ");
        }

        //失败
        else{
            return  new ResponseResult<Void>(BusinessStatus.OK.getCode(),"修改头像失败 ");
        }
    }
}
