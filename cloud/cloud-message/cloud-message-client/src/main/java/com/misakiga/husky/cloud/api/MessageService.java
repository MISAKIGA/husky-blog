package com.misakiga.husky.cloud.api;

import com.misakiga.husky.cloud.dto.UmsAdminLoginLogDTO;
import com.misakiga.husky.cloud.notification.model.Notification;

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

    /**
     * 发送通知
     * @param notification
     * @return
     */
    boolean notificationTask(Notification notification);
}
