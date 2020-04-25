package com.misakiga.husky.comm.sms;

import java.util.Map;

/**
 *
 * 短信发送响应结果
 * @author MISAKIGA
 */
public class SmsSendResult {

    private Map<String,Object> data;

    public  Map<String,Object> getData() {
        return data;
    }

    public void setData(Map<String,Object> data) {
        this.data = data;
    }

    public boolean isSuccess(){
        if(data != null){
            return "OK".equals(data.get("Code"));
        }
        return false;
    }

    public String getCode(){
        if(data != null){
            return data.get("Code").toString();
        }
        return "";
    }

}
