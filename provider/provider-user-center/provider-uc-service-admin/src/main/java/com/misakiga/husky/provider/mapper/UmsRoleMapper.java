package com.misakiga.husky.provider.mapper;

import com.misakiga.husky.provider.domain.UmsRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.MyMapper;

import java.util.List;

/**
 * 角色
 * @author MISAKIGA
 */
public interface UmsRoleMapper extends MyMapper<UmsRole> {

    List<UmsRole> getRolesByUserId(@Param("id") Long id);
}