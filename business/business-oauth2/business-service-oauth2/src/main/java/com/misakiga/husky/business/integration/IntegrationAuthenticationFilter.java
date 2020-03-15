package com.misakiga.husky.business.integration;

import com.misakiga.husky.business.integration.authenticator.IntegrationAuthenticator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 *  自定义登录拦截器
 * @author MISAKIGA
 * @date 2020-3-2
 */
@Component
public class IntegrationAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {
    //ApplicationContextAware 实现该接口后 ，这个类就获得了ApplicationContext中的所有Bean
    //GenericFilter 是一个过滤器 详情：https://my.oschina.net/u/2819035/blog/736658

    private static final String AUTH_TYPE_PARM_NAME = "auth_type";

    private static final String OAUTH_TOKEN_URL = "/oauth/token";

    private Collection<IntegrationAuthenticator> authenticators;

    private ApplicationContext applicationContext;

    private RequestMatcher requestMatcher;

    public IntegrationAuthenticationFilter(){
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "GET"),
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "POST")
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(requestMatcher.matches(request)){
            //设置集成登录信息
            IntegrationAuthentication integrationAuthentication = new IntegrationAuthentication();
            integrationAuthentication.setAuthType(request.getParameter(AUTH_TYPE_PARM_NAME));
            integrationAuthentication.setAuthParameters(request.getParameterMap());
            IntegrationAuthenticationContext.set(integrationAuthentication);

            try{
                //预处理
                this.prepare(integrationAuthentication);

                filterChain.doFilter(request,response);

                //后置处理
                this.complete(integrationAuthentication);
            }finally {
                IntegrationAuthenticationContext.clear();
            }
        }

        else{
            filterChain.doFilter(request,response);
        }
    }

    /**
     * 进行预处理
     * @param integrationAuthentication
     */
    private void prepare(IntegrationAuthentication integrationAuthentication) {

        //延迟加载认证器
        if(this.authenticators == null){
            synchronized (this){
                //获取认证器，UsernamePasswordAuthenticator等认证器
                Map<String,IntegrationAuthenticator> integrationAuthenticatorMap = applicationContext.getBeansOfType(IntegrationAuthenticator.class);
                if(integrationAuthenticatorMap != null){
                    this.authenticators = integrationAuthenticatorMap.values();
                }
            }
        }

        if(this.authenticators == null){
            this.authenticators = new ArrayList<>();
        }

        //判断是否支持认证器，如果支持，则使用该认证器进行验证
        for (IntegrationAuthenticator authenticator: authenticators) {
            if(authenticator.support(integrationAuthentication)){
                authenticator.prepare(integrationAuthentication);
            }
        }
    }

    /**
     * 后置处理
     * @param integrationAuthentication
     */
    private void complete(IntegrationAuthentication integrationAuthentication){
        for (IntegrationAuthenticator authenticator: authenticators) {
            if(authenticator.support(integrationAuthentication)){
                authenticator.complete(integrationAuthentication);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
 }
