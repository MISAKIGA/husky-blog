package com.misakiga.husky.business.feign.fallback;

import com.misakiga.husky.business.dto.UmsAdminDTO;
import com.misakiga.husky.business.feign.SysAuthorizeClient;
import com.misakiga.husky.uc.model.Authorize;
import org.springframework.stereotype.Component;

/**
 * @author MISAKIGA
 */
@Component
public class SysAuthorizeClientFallback implements SysAuthorizeClient {

    public static final String BREAKING_MESSAGE = "您的网络有问题，请检查";

    @Override
    public Authorize get(Long userId) {
        UmsAdminDTO dto = new UmsAdminDTO();
        dto.setEmail("service@funtl.com");

        return null;
    }
}
