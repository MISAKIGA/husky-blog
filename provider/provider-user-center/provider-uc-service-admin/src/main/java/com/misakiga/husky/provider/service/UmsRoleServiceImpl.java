package com.misakiga.husky.provider.service;

import com.misakiga.husky.provider.domain.UmsRole;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.misakiga.husky.provider.mapper.UmsRoleMapper;
import com.misakiga.husky.provider.api.UmsRoleService;

import java.util.List;

/**
 * 角色管理
 * @author MISAKIGA
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService{

    @Resource
    private UmsRoleMapper umsRoleMapper;


    @Override
    public List<UmsRole> getRolesByUserId(Long id) {


        return umsRoleMapper.getRolesByUserId(id);
    }
}
