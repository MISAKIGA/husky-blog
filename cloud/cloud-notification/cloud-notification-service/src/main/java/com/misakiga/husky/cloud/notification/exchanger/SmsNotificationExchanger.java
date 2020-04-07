package com.misakiga.husky.cloud.notification.exchanger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.misakiga.husky.cloud.notification.model.Notification;
import com.misakiga.husky.cloud.notification.model.SmsNotification;
import com.misakiga.husky.comm.sms.SmsParameter;
import com.misakiga.husky.comm.sms.SmsSendResult;
import com.misakiga.husky.comm.sms.SmsSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * 短信通知转发器
 * @author MISAKIGA
 */
public class SmsNotificationExchanger implements NotificationExchanger {

    private Logger logger = LoggerFactory.getLogger(SmsNotificationExchanger.class);

    private SmsSender smsSender;

    private final static String STATUS_OK = "OK";

    @Value("${husky.notification.sms.sign-name}")
    private String signName;

    public SmsNotificationExchanger(SmsSender smsSender) {
        if (smsSender != null) {
            logger.info("初始化短信通知组件");
        }
        this.smsSender = smsSender;
    }

    @Override
    public boolean support(Object notification) {
        return notification.getClass().equals(SmsNotification.class);
    }

    @Override
    public boolean exchange(Notification notification) {
        Assert.notNull(smsSender, "短信接口没有初始化");

        SmsNotification smsNotification = (SmsNotification) notification;
        SmsParameter parameter = new SmsParameter();
        parameter.setPhoneNumbers(Arrays.asList(smsNotification.getPhoneNumber()));
        parameter.setTemplateCode(smsNotification.getTemplateCode());

        if(StringUtils.isEmpty(smsNotification.getSignName())){
            smsNotification.setSignName(this.signName);
        }

        Assert.notNull(smsNotification.getSignName(),"短信签名不能为空");

        parameter.setSignName(smsNotification.getSignName());
        if (smsNotification.getParams() != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                parameter.setParams(mapper.writeValueAsString(smsNotification.getParams()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("格式化短信参数失败");
            }
        }
        SmsSendResult smsSendResult = smsSender.send(parameter);
        return STATUS_OK.equals(smsSendResult.getCode());
    }
}
