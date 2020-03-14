package com.misakiga.husky.provider.api;

import com.misakiga.husky.provider.domain.UmsRole;

import java.util.List;

public interface UmsRoleService{

    List<UmsRole> getRolesByUserId(Long id);

}
