package com.misakiga.husky.provider.tests;

import com.misakiga.husky.provider.ProviderMemberBootstrap;
import com.misakiga.husky.provider.api.UmsMemberService;
import com.misakiga.husky.provider.domain.UmsMember;
import com.misakiga.husky.provider.mapper.UmsMemberMapper;
import com.misakiga.husky.uc.model.SysUserAuthentication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderMemberBootstrap.class)
public class UmsAllTests {

    @Resource
    private UmsMemberService umsMemberService;

    @Test
    public void selectUserByPhone(){
        SysUserAuthentication userByPhoneNumber = umsMemberService.findUserByPhoneNumber("18061581849");

        System.out.println(userByPhoneNumber);
    }
}
