package com.misakiga.husky.business.configure;

import com.misakiga.husky.business.integration.IntegrationAuthenticationFilter;
import com.misakiga.husky.business.service.IntegrationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 认证服务器
 * @author MISAKIGA
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 注入用于支持 password 模式
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    //集成登陆
    @Autowired
    private IntegrationAuthenticationFilter integrationAuthenticationFilter;

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        //配置该datasource为主要数据源
        return DataSourceBuilder.create().build();
    }


    @Bean
    public TokenStore tokenStore() {
        // 基于 JDBC 实现，令牌保存到数据库
        // return new JdbcTokenStore(dataSource());
        //使用Redis保存
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        // 基于 JDBC 实现，需要事先在数据库配置客户端信息
        return new JdbcClientDetailsService(dataSource());
    }

    //modify
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 用于支持密码模式
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore());
                //集成登陆
                //.reuseRefreshTokens(false);
    }

    //modify
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                // 允许客户端访问 /oauth/check_token 检查 token
               // .checkTokenAccess("isAuthenticated()")
                //.tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()")
                //
                .allowFormAuthenticationForClients()
                //过滤器
                .addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);
    }

    /**
     * 配置客户端
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 客户端配置
        clients.withClientDetails(jdbcClientDetailsService());
    }

}
