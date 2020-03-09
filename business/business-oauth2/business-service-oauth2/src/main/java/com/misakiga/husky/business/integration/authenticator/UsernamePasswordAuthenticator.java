package com.misakiga.husky.business.integration.authenticator;

import com.misakiga.husky.business.integration.IntegrationAuthentication;
import com.misakiga.husky.uc.model.SysUserAuthentication;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 默认登录
 * 账号密码登录
 * @author MISAKIGA
 */
@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    @Override
    public SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {



        return null;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return StringUtils.isEmpty(integrationAuthentication.getAuthType());
    }

}
