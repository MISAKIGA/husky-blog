package com.misakiga.husky.provider.service;

import com.misakiga.husky.comm.core.CommonConstant;
import com.misakiga.husky.commons.utils.MapperUtils;
import com.misakiga.husky.provider.api.SysAuthorizeService;
import com.misakiga.husky.provider.api.UmsPermissionService;
import com.misakiga.husky.provider.api.UmsRoleService;
import com.misakiga.husky.provider.domain.UmsPermission;
import com.misakiga.husky.provider.domain.UmsRole;
import com.misakiga.husky.provider.mapper.UmsAdminMapper;
import com.misakiga.husky.uc.model.Authorize;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 授权服务类
 * @author MISAKIGA
 */
@Service(version = "1.0.0")
public class SysAuthorizeServiceImpl implements SysAuthorizeService {


    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Reference(version = "1.0.0")
    private UmsRoleService umsRoleService;

    @Reference(version = "1.0.0")
    private UmsPermissionService umsPermissionService;

    @Override
    public Authorize getAuthorize(Long userId) {
        Authorize authorize = new Authorize();

        try {
            List<UmsRole> umsRoles = umsRoleService.getRolesByUserId(userId);
            List<UmsPermission> umsPermissions = umsPermissionService.selectAllPermissionByUserId(userId);
            Collection<String> roles = new ArrayList<>();
            Collection<String> resources = new ArrayList<>();
            umsRoles.forEach(umsRole -> {
                if (umsRole.getStatus().equals(CommonConstant.STATUS_ENABLED)){
                    roles.add(umsRole.getName());
                }
            });

            umsPermissions.forEach(umsPermission -> {
                if (umsPermission.getStatus().equals(CommonConstant.STATUS_ENABLED)){
                    resources.add(umsPermission.getUri());
                }
            });

            authorize.setRoles(roles);
            authorize.setResources(resources);

            return authorize;
        }catch (Exception ignored){}

        return null;
    }

    @Override
    public void delete(Long userId, Long roleId) {

    }
}
