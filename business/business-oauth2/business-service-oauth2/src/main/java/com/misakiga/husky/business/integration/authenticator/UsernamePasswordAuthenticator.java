package com.misakiga.husky.business.integration.authenticator;

import com.misakiga.husky.business.feign.ProfileFeign;
import com.misakiga.husky.business.integration.IntegrationAuthentication;
import com.misakiga.husky.commons.utils.MapperUtils;
import com.misakiga.husky.uc.model.SysUserAuthentication;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 默认登录
 * 账号密码登录
 * @author MISAKIGA
 */
@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {


    @Resource
    ProfileFeign profileFeign;

    @Override
    public SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {

        String jsonString = profileFeign.findUserByUsername(integrationAuthentication.getUsername());
        System.err.println("json:"+jsonString);
        try {
            SysUserAuthentication sysUserAuthentication = MapperUtils.json2pojoByTree(jsonString,"data",SysUserAuthentication.class);
            return sysUserAuthentication;
        }catch (Exception e){

        }

        return null;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return "password".equals(integrationAuthentication.getAuthType());
    }

}
