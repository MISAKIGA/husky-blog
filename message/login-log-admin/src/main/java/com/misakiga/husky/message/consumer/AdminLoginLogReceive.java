package com.misakiga.husky.message.consumer;

import com.misakiga.husky.commons.utils.MapperUtils;
import com.misakiga.husky.provider.api.UmsAdminLoginLogService;
import com.misakiga.husky.provider.domain.UmsAdminLoginLog;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * 处理已订阅订阅消息队列
 * @author MISAKIGA
 */
@Service
public class AdminLoginLogReceive {


    @Reference(version = "1.0.0")
    private UmsAdminLoginLogService umsAdminLoginLogService;

    @StreamListener("admin-login-log-topic")
    public void receiveAdminLoginLog(String umsAdminLoginLogJson) throws Exception {

        UmsAdminLoginLog umsAdminLoginLog = MapperUtils.json2pojo(umsAdminLoginLogJson, UmsAdminLoginLog.class);
        umsAdminLoginLogService.insert(umsAdminLoginLog);
    }
}
