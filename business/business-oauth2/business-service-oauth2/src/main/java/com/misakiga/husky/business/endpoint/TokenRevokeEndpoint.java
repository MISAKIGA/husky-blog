package com.misakiga.husky.business.endpoint;

/**
 * 登出接口
 * @author MISAKIGA@FrameworkEndpoint
 */
public class TokenRevokeEndpoint {

/*    @Resource
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;

    @DeleteMapping("/oauth/token")
    public ResponseResult<Void> deleteAccessToken(@RequestParam("access_token") String accessToken){
        System.out.println("acc++++++"+accessToken);
        consumerTokenServices.revokeToken(accessToken);
        return new ResponseResult<>(BusinessStatus.OK.getCode(),"用户注销",null);
    }*/
}
