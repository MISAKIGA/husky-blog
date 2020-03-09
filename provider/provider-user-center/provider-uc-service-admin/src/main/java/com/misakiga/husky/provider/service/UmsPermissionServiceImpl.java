package com.misakiga.husky.provider.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.misakiga.husky.provider.mapper.UmsPermissionMapper;
import com.misakiga.husky.provider.service.UmsPermissionService;
@Service
public class UmsPermissionServiceImpl implements UmsPermissionService{

    @Resource
    private UmsPermissionMapper umsPermissionMapper;

}
