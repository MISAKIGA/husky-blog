package com.misakiga.husky.business.service;

import com.google.common.collect.Lists;
import com.misakiga.husky.business.dto.User;
import com.misakiga.husky.business.feign.SysAuthorizeClient;
import com.misakiga.husky.business.integration.IntegrationAuthentication;
import com.misakiga.husky.business.integration.IntegrationAuthenticationContext;
import com.misakiga.husky.business.integration.authenticator.IntegrationAuthenticator;
import com.misakiga.husky.provider.api.UmsAdminService;
import com.misakiga.husky.provider.domain.UmsAdmin;
import com.misakiga.husky.uc.model.Authorize;
import com.misakiga.husky.uc.model.SysUserAuthentication;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author MISAKIGA
 */
@Component
public class IntegrationUserDetailsService implements UserDetailsService {

    private List<IntegrationAuthenticator> authenticators;

    @Resource
    private SysAuthorizeClient sysAuthorizeClient;

    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> integrationAuthenticators){
        this.authenticators = integrationAuthenticators;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();

        //判断是否是集成登陆
        if(integrationAuthentication == null){
            integrationAuthentication = new IntegrationAuthentication();
        }
        integrationAuthentication.setUsername(username);

        SysUserAuthentication sysUserAuthentication = this.authenticate(integrationAuthentication);

        User user = new User();

        assert sysUserAuthentication != null;
        BeanUtils.copyProperties(sysUserAuthentication,user);

        this.setAuthorize(user);

        return user;
    }

    /**
     * 设置授权信息
     * @param
     */
    public void setAuthorize(User user){

        Authorize authorize = this.sysAuthorizeClient.get(user.getId());
        Collection<String> roles = authorize.getRoles();
        roles.add("USER");
        user.setRoles(roles);
        user.setResources(authorize.getResources());
        user.getAuthorities();
    }

    private SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {

        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    return authenticator.authenticate(integrationAuthentication);
                }
            }
        }
        throw new OAuth2Exception("无效的auth_type");
    }
}
