package com.misakiga.husky.cloud.producer;

import com.misakiga.husky.cloud.api.MessageService;
import com.misakiga.husky.cloud.dto.UmsAdminLoginLogDTO;
import com.misakiga.husky.cloud.message.MessageSource;
import com.misakiga.husky.cloud.notification.model.Notification;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Service(version = "1.0.0")
public class MessageProducer implements MessageService {

    @Resource
    private MessageSource source;

    /**
     * 管理登录日志
     * @param dto
     * @return
     */
    @Override
    public boolean sendAdminLoginLog(UmsAdminLoginLogDTO dto){
        return source.adminLoginLog().send(MessageBuilder.withPayload(dto).build());
    }

    /**
     * 发送通知
     * @param notification {@link Notification}
     * @return 发送是否成功
     */
    @Override
    public boolean notificationTask(Notification notification) {
        return source.notificationTask().send(MessageBuilder.withPayload(notification).build());
    }
}
