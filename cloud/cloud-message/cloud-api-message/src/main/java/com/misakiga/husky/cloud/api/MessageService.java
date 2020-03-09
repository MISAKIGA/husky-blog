package com.misakiga.husky.cloud.api;

import com.misakiga.husky.cloud.dto.UmsAdminLoginLogDTO;

/**
 * 消息队列服务
 * @author MISAKIGA
 */
public interface MessageService {
    /**
     * 发送管理员登录日志
     * @param dto {@link UmsAdminLoginLogDTO}
     * @return
     */
    boolean sendAdminLoginLog(UmsAdminLoginLogDTO dto);
}
