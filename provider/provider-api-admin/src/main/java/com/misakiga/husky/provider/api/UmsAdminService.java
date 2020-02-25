package com.misakiga.husky.provider.api;

import com.misakiga.husky.provider.domain.UmsAdmin;

/**
 * 用户管理服务
 * @author MISAKIGA
 */
public interface UmsAdminService {

    /**
     * 新增用户
     * @param umsAdmin {@link UmsAdmin}
     * @return
     */
    int insert(UmsAdmin umsAdmin);

    /**
     * 获取用户
     * @param username 用户名
     * @return {@link UmsAdmin}
     */
    UmsAdmin get(String username);

    /**
     * 获取用户
     * @param umsAdmin {@link UmsAdmin}
     * @return {@link UmsAdmin}
     */
    UmsAdmin get(UmsAdmin umsAdmin);

    /**
     * 更新用户
     * @param umsAdmin {@link UmsAdmin}
     * @return
     */
    int udate(UmsAdmin umsAdmin);

    /**
     * 修改图标
     * @param username {@code String} 用户名
     * @param password {@code String} 路径
     * @return {@code int} 大于0 则表示更新成功
     */
    int modifyPassword(String username,String password);

    /**
     * 修改头像
     * @param username {@code String} 用户名
     * @param path {@code String} 路径
     * @return
     */
    int modifyIcon(String username,String path);
}

