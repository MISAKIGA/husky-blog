package com.misakiga.husky.provider.service;

import com.misakiga.husky.provider.domain.UmsPermission;
import javax.annotation.Resource;
import com.misakiga.husky.provider.mapper.UmsPermissionMapper;
import com.misakiga.husky.provider.api.UmsPermissionService;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @author MISAKIGA
 */
@Service(version = "1.0.0")
public class UmsPermissionServiceImpl implements UmsPermissionService{

    @Resource
    private UmsPermissionMapper umsPermissionMapper;

    @Override
    public List<UmsPermission> selectPermissionByRoleId(Long id) {

        return umsPermissionMapper.selectPermissionByRoleId(id);
    }
}
