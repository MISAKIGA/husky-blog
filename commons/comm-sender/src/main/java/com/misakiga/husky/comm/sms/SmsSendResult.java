package com.misakiga.husky.comm.sms;

/**
 *
 * 短信发送响应结果
 * @author MISAKIGA
 */
public class SmsSendResult {

    private boolean success;

    private String code;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
