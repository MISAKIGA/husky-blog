package com.misakiga.husky.provider.tests;
import java.util.Date;

import com.misakiga.husky.provider.api.UmsAdminService;
import com.misakiga.husky.provider.domain.UmsAdmin;
import com.misakiga.husky.provider.mapper.UmsAdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UmsTests {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private UmsAdminService umsAdminService;

    @Test
    public void testSelectAll(){
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectAll();
        umsAdmins.forEach(umsAdmin -> {
            System.out.println(umsAdmin);
        });
    }

    @Test
    public void testInsert(){

        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setUsername("test");
        umsAdmin.setPassword("test");
        umsAdmin.setIcon("xxxicon");
        umsAdmin.setEmail("sdf@qq,ciomn");
        umsAdmin.setNickName("admins");
        umsAdmin.setNote("nop");
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());
        umsAdmin.setStatus(0);

        umsAdminService.insert(umsAdmin);
    }

}
