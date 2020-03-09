package com.misakiga.husky.provider.service;

import com.misakiga.husky.provider.api.SysAuthorizeService;
import com.misakiga.husky.provider.mapper.UmsAdminMapper;
import com.misakiga.husky.uc.model.Authorize;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * 授权服务类
 * @author MISAKIGA
 */
public class SysAuthorizeServiceImpl implements SysAuthorizeService {


    @Resource
    private UmsAdminMapper umsAdminMapper;


    @Override
    public Authorize getAuthorize(Long userId) {
        Authorize authorize = new Authorize();


        return null;
    }

    @Override
    public void delete(Long userId, Long roleId) {

    }
}
