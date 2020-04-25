package com.misakiga.husky.cloud.api;

import com.misakiga.husky.cloud.dto.UmsAdminLoginLogDTO;
import com.misakiga.husky.cloud.notification.model.Notification;

/**
 * @author MISAKIGA
 */
public interface NotificationMessageService {

    /**
     * 发送通知
     * @param notification
     * @return
     */
    boolean notificationTask(Notification notification);
}
