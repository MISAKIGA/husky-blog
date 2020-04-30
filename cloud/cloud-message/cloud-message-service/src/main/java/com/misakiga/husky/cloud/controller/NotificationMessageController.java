package com.misakiga.husky.cloud.controller;

import com.misakiga.husky.cloud.notification.model.EmailNotification;
import com.misakiga.husky.cloud.notification.model.SmsNotification;
import com.misakiga.husky.cloud.producer.NotificationMessageProducer;
import com.misakiga.husky.comm.base.BaseController;
import com.misakiga.husky.comm.dto.ResponseResult;;
import com.misakiga.husky.commons.utils.IdUtils;
import com.misakiga.husky.commons.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用队列发送消息
 * @author MISAKIGA
 */
@RestController
@RequestMapping("message")
public class NotificationMessageController extends BaseController {

    @Resource
    private NotificationMessageProducer notificationMessageProducer;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/email/notification")
    public ResponseResult<String> sendEmailNotification(@RequestBody EmailNotification emailNotification){

        if(sendNotification(null,emailNotification)){
            return this.success("发送成功！");
        }
        return this.failure("发送失败！");
    }

    @PostMapping("/sms/notification")
    public ResponseResult<String> sendSmsNotification(@RequestBody SmsNotification smsNotification){

        System.out.println(smsNotification);
        if(sendNotification(smsNotification,null)){
            return this.success("发送成功！");
        }
        return this.failure("发送失败！");
    }

    private boolean sendNotification(SmsNotification smsNotification,EmailNotification emailNotification){

        String notificationJson = null;
        try {
            Map<String,Object> map = new HashMap<>(5);
            map.put("id", IdUtils.generate());
            if(smsNotification != null && emailNotification == null) {
                map.put("type","sms");
                map.put("data", smsNotification);
            } else if(emailNotification != null){
                map.put("type","email");
                map.put("data", emailNotification);
            }
            notificationJson = MapperUtils.mapToJson(map);
        }catch (Exception e) {
            logger.error("JSON转换异常:" + e.getMessage());
        }
        boolean result = false;
        if(notificationJson != null){
            result = notificationMessageProducer.notificationTask(notificationJson);
        }

        return result;
    }
}
