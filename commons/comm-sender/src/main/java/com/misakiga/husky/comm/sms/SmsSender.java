package com.misakiga.husky.comm.sms;

/**
 * @author MISAKIGA
 */
public interface SmsSender {

    /**
     * 发送短信
     * @param parameter
     * @return
     */
    SmsSendResult send(SmsParameter parameter);
}
