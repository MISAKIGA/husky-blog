package com.misakiga.husky.provider.tests;
import java.util.Date;

import com.misakiga.husky.provider.ProviderAdminBootstrap;
import com.misakiga.husky.provider.api.UmsAdminService;
import com.misakiga.husky.provider.api.UmsPermissionService;
import com.misakiga.husky.provider.api.UmsRoleService;
import com.misakiga.husky.provider.domain.UmsAdmin;
import com.misakiga.husky.provider.domain.UmsPermission;
import com.misakiga.husky.provider.domain.UmsRole;
import com.misakiga.husky.provider.mapper.UmsAdminMapper;
import com.misakiga.husky.provider.mapper.UmsRoleMapper;
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
@SpringBootTest(classes = ProviderAdminBootstrap.class)
public class UmsTests {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Resource
    private UmsAdminService umsAdminService;

    @Resource
    private UmsPermissionService permissionService;

    @Resource
    private UmsRoleService umsRoleService;
    @Test
    public void testSelectAllPermission(){
        List<UmsPermission> umsPermissions = permissionService.selectPermissionByRoleId(1L);
        umsPermissions.forEach(u ->{
            System.out.println(u);
        });
    }


    @Test
    public void testSelectAll(){

        List<UmsAdmin> umsAdmins = umsAdminMapper.selectAll();
        umsAdmins.forEach(umsAdmin -> {
            System.out.println(umsAdmin);
        });
    }

    @Test
    public void tesSelectRole(){
        List<UmsRole> umsRoles = umsRoleService.getRolesByUserId(1L);
        umsRoles.forEach(umsRole -> {
            System.out.println(umsRole);
        });
    }

    @Test
    public void testQueryAllPermission(){
        List<UmsPermission> umsPermissions = permissionService.selectAllPermissionByUserId(3L);
        umsPermissions.forEach(umsPermission -> {
            System.out.println(umsPermission);
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
