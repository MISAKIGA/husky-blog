package com.misakiga.husky.business.controller.fallback;

import com.misakiga.husky.business.BusinessStatus;
import com.misakiga.husky.business.dto.UmsAdminDTO;
import com.misakiga.husky.business.feign.fallback.ProfileFeignFallback;
import com.misakiga.husky.commons.dto.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfileControllerFallback {

    private static final Logger logger = LoggerFactory.getLogger(ProfileControllerFallback.class);

    public static ResponseResult<UmsAdminDTO> infoFallback(String username, Throwable ex){
        logger.warn("inboke infoFallback" + ex.getClass().getTypeName());

        return  new ResponseResult<UmsAdminDTO>(BusinessStatus.BREAKING.getCode(), ProfileFeignFallback.BREAKING_MESSAGE);
    }
}
