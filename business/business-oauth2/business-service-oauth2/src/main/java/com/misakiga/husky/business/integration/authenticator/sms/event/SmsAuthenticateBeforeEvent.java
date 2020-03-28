package com.misakiga.husky.business.integration.authenticator.sms.event;

import org.springframework.context.ApplicationEvent;

/**
 * 短信验证之前的事件，可以监听事件进行用户手机号自动注册
 *
 * @author MISAKIGA
 */
public class SmsAuthenticateBeforeEvent extends ApplicationEvent {

    public SmsAuthenticateBeforeEvent(Object source) {
        super(source);
    }
}
