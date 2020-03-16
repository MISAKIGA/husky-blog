package com.misakiga.husky.business.endpoint;

import com.misakiga.husky.business.BusinessStatus;
import com.misakiga.husky.commons.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登出接口
 * @author MISAKIGA@FrameworkEndpoint
 */

public class TokenRevokeEndpoint {

/*    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @DeleteMapping("/oauth/token")
    public ResponseResult<Void> deleteAccessToken(@RequestParam("access_token") String accessToken){
        System.out.println("acc++++++"+accessToken);
        consumerTokenServices.revokeToken(accessToken);
        return new ResponseResult<>(BusinessStatus.OK.getCode(),"用户注销",null);
    }*/

}
