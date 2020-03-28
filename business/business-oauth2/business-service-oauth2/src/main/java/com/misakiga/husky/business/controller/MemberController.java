package com.misakiga.husky.business.controller;

import com.misakiga.husky.business.BusinessStatus;
import com.misakiga.husky.business.dto.LoginParam;
import com.misakiga.husky.comm.base.BaseController;
import com.misakiga.husky.comm.dto.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 成员登录
 * @author MISAKIGA
 */
@RestController()
@RequestMapping("member")
public class MemberController extends BaseController {

    @PostMapping("/login")
    public ResponseResult<Void> memberLogin(@RequestBody LoginParam loginParam, HttpServletRequest request){

        return  this.success("登录成功");
    }

}
