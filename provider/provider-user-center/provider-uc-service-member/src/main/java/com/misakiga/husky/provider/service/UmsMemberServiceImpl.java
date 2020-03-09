package com.misakiga.husky.provider.service;

import com.misakiga.husky.provider.api.UmsMemberService;;
import com.misakiga.husky.provider.domain.UmsMember;
import com.misakiga.husky.uc.model.SysUserAuthentication;
import com.misakiga.husky.uc.model.SysUserDTO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.misakiga.husky.provider.mapper.UmsMemberMapper;

import java.util.List;

/**
 * @author MISAKIGA
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Resource
    private UmsMemberMapper umsMemberMapper;

    @Override
    public void update(SysUserDTO sysUserDTO) {

    }

    @Override
    public List<UmsMember> list() {
        return null;
    }

    @Override
    public void doResetPassword(Long id) {

    }

    @Override
    public UmsMember doDisable(Long id) {
        return null;
    }

    @Override
    public UmsMember doEnable(Long id) {
        return null;
    }

    @Override
    public UmsMember doLock(Long id) {
        return null;
    }

    @Override
    public UmsMember doUnlock(Long id) {
        return null;
    }

    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {

    }

    @Override
    public SysUserAuthentication findUserByUsername(String username) {
        return null;
    }

    @Override
    public SysUserAuthentication findUserById(Long id) {
        return null;
    }

    @Override
    public SysUserAuthentication findUserByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public boolean deleteSysUser(String username) {
        return false;
    }

    @Override
    public void insert(SysUserDTO sysUserDTO) {

    }

}
