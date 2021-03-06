package com.misakiga.husky.provider.test;

import com.misakiga.husky.provider.api.UmsAdminLoginLogService;
import com.misakiga.husky.provider.domain.UmsAdminLoginLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class AdminLoginLogTests {

    //线程安全
    @Resource
    private UmsAdminLoginLogService umsAdminLoginLogService;

    @Test
    public void testInsert(){
        UmsAdminLoginLog umsAdminLoginLog = new UmsAdminLoginLog();
        umsAdminLoginLog.setAdminId(1L);
        umsAdminLoginLog.setCreateTime(new Date());
        umsAdminLoginLog.setIp("0.0.0.0");
        umsAdminLoginLog.setAddress("0.0.0.0");
        umsAdminLoginLog.setUserAgent("0.0.0.0");



        int insert = umsAdminLoginLogService.insert(umsAdminLoginLog);
    }
}