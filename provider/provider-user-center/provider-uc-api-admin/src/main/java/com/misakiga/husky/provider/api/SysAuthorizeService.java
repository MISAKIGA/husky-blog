package com.misakiga.husky.provider.api;


import com.misakiga.husky.uc.model.Authorize;

/**
 * 系统授权服务类
 * @author MISAKIGA
 */
public interface SysAuthorizeService {

    Authorize getAuthorize(Long userId);

/*    *//**
     * 根据授权对象和类型获取授权列表
     * @param target
     * @param type
     * @return
     *//*
    List<SysAuthorize> getAuthorizesByTargetAndType(Long target, String type);

    *//**
     * 批量添加角色授权
     * @param authorizeBatchTargetDTO
     *//*
    void batchAuthorize(SysAuthorizeBatchDTO authorizeBatchTargetDTO);

    *//**
     * 根据授权对象和授权类型获取角色列表
     * @param target
     * @param type
     * @return
     *//*
    List<SysRole> getRoleListByTargetAndType(Long target, String type);*/

    /**
     * 根据用户，角色删除权限
     * @param userId
     * @param roleId
     */
    void delete(Long userId, Long roleId);
}
