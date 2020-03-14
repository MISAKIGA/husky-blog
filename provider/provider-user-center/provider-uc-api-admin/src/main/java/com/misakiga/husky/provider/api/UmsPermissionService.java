package com.misakiga.husky.provider.api;

import com.misakiga.husky.provider.domain.UmsPermission;

import java.util.List;

public interface UmsPermissionService{

    List<UmsPermission> selectPermissionByRoleId(Long id);

    List<UmsPermission> selectAllPermissionByUserId(Long id);
}
