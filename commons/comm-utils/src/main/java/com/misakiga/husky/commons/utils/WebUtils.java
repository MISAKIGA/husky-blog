package com.misakiga.husky.commons.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author MISAKIGA
 */
public class WebUtils extends org.springframework.web.util.WebUtils{

    private final static String REAL_IP_HEADER = "X-Real-IP";

    /**
     * 获取客户端IP
     * @return
     */
    public static String getClientIp(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if(requestAttributes != null){
            request = ((ServletRequestAttributes)requestAttributes).getRequest();
        }

        if(!StringUtils.isEmpty(request.getHeader(REAL_IP_HEADER))){
            return request.getHeader(REAL_IP_HEADER);
        }

        if(request != null){
            return request.getRemoteAddr();
        }
        return null;
    }
}
