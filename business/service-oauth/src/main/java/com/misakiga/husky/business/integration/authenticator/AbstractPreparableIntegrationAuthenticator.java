package com.misakiga.husky.business.integration.authenticator;

import com.misakiga.husky.business.dto.SysUserAuthentication;
import com.misakiga.husky.business.integration.IntegrationAuthentication;

/**
 * 集成认证抽象类
 * @author MISAKIGA
 */
public abstract class AbstractPreparableIntegrationAuthenticator implements IntegrationAuthenticator {

    @Override
    public abstract SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract void prepare(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract boolean support(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract void complete(IntegrationAuthentication integrationAuthentication);
}
