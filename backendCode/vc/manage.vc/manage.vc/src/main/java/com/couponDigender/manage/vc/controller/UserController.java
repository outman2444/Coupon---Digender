package com.couponDigender.manage.vc.controller;

import com.couponDigender.comm.core.exception.ExceptionHandle;
import com.couponDigender.comm.core.enmu.RespCode;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.manage.core.extModal.UserExtModal;
import com.couponDigender.manage.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关
 */

@RestController
@RequestMapping("/user")
public class UserController extends ExceptionHandle{

    @Autowired
    private UserService userServiceImpl;

    @RequestMapping("/hello")
    public RespData hello(@RequestBody UserExtModal userExtModal) {
        String methodDesc = "微笑";
        return RespData.org(RespCode.SUCCESS.getCode(), methodDesc + "成功", "hello " + userExtModal.getName());
    }

    /**
     * 通过 wxCode 登陆
     * @param userExtModal
     * @return
     */
    @RequestMapping("/loginByCode")
    public RespData loginByCode(@RequestBody UserExtModal userExtModal) {
        String methodDesc = "通过 wxCode 登陆";
        return userServiceImpl.loginByCode(methodDesc ,userExtModal);
    }
}
