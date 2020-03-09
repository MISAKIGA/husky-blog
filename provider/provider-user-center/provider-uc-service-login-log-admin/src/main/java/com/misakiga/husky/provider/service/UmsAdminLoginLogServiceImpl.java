package com.misakiga.husky.provider.service;

import com.misakiga.husky.provider.api.UmsAdminLoginLogService;
import com.misakiga.husky.provider.domain.UmsAdminLoginLog;
import javax.annotation.Resource;
import com.misakiga.husky.provider.mapper.UmsAdminLoginLogMapper;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author MISAKIGA
 */
@Service(version = "1.0.0")
public class UmsAdminLoginLogServiceImpl implements UmsAdminLoginLogService {

    @Resource
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Override
    public int insert(UmsAdminLoginLog umsAdminLoginLog) {
        return umsAdminLoginLogMapper.insert(umsAdminLoginLog);
    }
}
