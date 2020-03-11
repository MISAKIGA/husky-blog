package com.misakiga.husky.provider.mapper;

import com.misakiga.husky.provider.domain.UmsPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.MyMapper;

import java.util.List;

/**
 * @author MISAKIGA
 */
public interface UmsPermissionMapper extends MyMapper<UmsPermission> {

    /**
     * 根据角色返回拥有得权限
     * @param id
     * @return UmsPermission
     */
    List<UmsPermission> selectPermissionByRoleId(@Param("id") Long id);
}