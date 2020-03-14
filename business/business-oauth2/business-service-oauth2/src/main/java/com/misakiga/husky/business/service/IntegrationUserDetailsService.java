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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MISAKIGA
 */
@Component
public class IntegrationUserDetailsService implements UserDetailsService {

    @Reference(version = "1.0.0")
    private UmsAdminService umsAdminService;

    private List<IntegrationAuthenticator> authenticators;

    @Resource
    private SysAuthorizeClient sysAuthorizeClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*//给每位用户授予权限
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        grantedAuthorities.add(grantedAuthority);

        UmsAdmin umsAdmin = umsAdminService.get(username);

        //账号存在
        if(umsAdmin != null){
            return new User(umsAdmin.getUsername(),umsAdmin.getPassword(),grantedAuthorities);
        }

        else {
            return null;
        }*/

        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();

        //判断是否是集成登陆
        if(integrationAuthentication == null){
            integrationAuthentication = new IntegrationAuthentication();
        }
        integrationAuthentication.setUsername(username);
        SysUserAuthentication sysUserAuthentication = this.authenticate(integrationAuthentication);

        if(sysUserAuthentication == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        User user = new User();
        BeanUtils.copyProperties(sysUserAuthentication, user);
        this.setAuthorize(user);
        return user;

    }

    /**
     * 设置授权信息
     * @param user
     */
    public void setAuthorize(User user){
        Authorize authorize = this.sysAuthorizeClient.get(user.getId());
        user.setRoles(authorize.getRoles());
        user.setResources(authorize.getResources());
    }

    private SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    return authenticator.authenticate(integrationAuthentication);
                }
            }
        }
        return null;
    }
}
