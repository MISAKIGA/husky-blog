package com.misakiga.husky.business.integration.authenticator.sms.event;

import org.springframework.context.ApplicationEvent;

/**
 * 短信认证成功事件
 * @author MISAKIGA
 */
public class SmsAuthenticateSuccessEvent extends ApplicationEvent {

    public SmsAuthenticateSuccessEvent(Object source) {
        super(source);
    }
}
