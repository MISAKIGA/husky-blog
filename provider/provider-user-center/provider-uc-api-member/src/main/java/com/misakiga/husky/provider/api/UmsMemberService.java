package com.misakiga.husky.provider.api;

import com.misakiga.husky.provider.domain.UmsMember;
import com.misakiga.husky.uc.model.SysUserAuthentication;
import com.misakiga.husky.uc.model.SysUserDTO;

import java.util.List;

/**
 * @author MISAKIGA
 */
public interface UmsMemberService {


    /**
     * 修改用户信息
     * @param sysUserDTO
     */
    void update(SysUserDTO sysUserDTO);

    /**
     * 查询系统用户
     *
     * @return
     */
    List<UmsMember> list();

    /**
     * 重置用户密码
     *
     * @param id
     */
    void doResetPassword(Long id);

    /**
     * 禁用用户
     *
     * @param id
     * @return
     */
    UmsMember doDisable(Long id);

    /**
     * 启用用户
     *
     * @param id
     * @return
     */
    UmsMember doEnable(Long id);


    /**
     * 锁定用户
     *
     * @param id
     * @return
     */
    UmsMember doLock(Long id);

    /**
     * 解锁账号
     *
     * @param id
     * @return
     */
    UmsMember doUnlock(Long id);

    /**
     * 修改密码
     * @param id
     * @param oldPassword
     * @param newPassword
     */
    void updatePassword(Long id, String oldPassword, String newPassword);

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    SysUserAuthentication findUserByUsername(String username);

    /**
     * 通过用户ID获取用户
     * @param id
     * @return
     */
    SysUserAuthentication findUserById(Long id);

    /**
     * 通过手机号获取用户
     * @param phoneNumber
     * @return
     */
    SysUserAuthentication findUserByPhoneNumber(String phoneNumber);

    boolean deleteSysUser(String username);

    /**
     * 创建用户
     * @param sysUserDTO
     */
    void insert(SysUserDTO sysUserDTO);

}
