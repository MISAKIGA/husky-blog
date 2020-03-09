package com.misakiga.husky.business.integration.authenticator;

import com.misakiga.husky.business.integration.IntegrationAuthentication;
import com.misakiga.husky.uc.model.SysUserAuthentication;

/**
 * 集成认证（认证器）
 * @author MISAKIGA
 */
public interface IntegrationAuthenticator {

    /**
     * 处理集成认证
     * @param integrationAuthentication
     * @return 认证主体 {@link SysUserAuthentication}
     */
    SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication);

    /**
     * 进行预处理
     * @param integrationAuthentication
     */
    void prepare(IntegrationAuthentication integrationAuthentication);

    /**
     *
     * 判断是否支持集成认证类型
     * @param integrationAuthentication
     * @return
     */
    boolean support(IntegrationAuthentication integrationAuthentication);

    /** 认证结束后执行
     * @param integrationAuthentication
     */
    void complete(IntegrationAuthentication integrationAuthentication);
}
