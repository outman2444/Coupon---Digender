package com.couponDigender.manage.vc.controller;

import com.couponDigender.comm.core.resp.RespCode;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.manage.core.ExtModal.UserExtModal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/hello")
    public RespData hello(@RequestBody UserExtModal userExtModal) {
        String methodDesc = "微笑";
        return RespData.org(RespCode.SUCCESS.getCode(), methodDesc + "成功", "hello " + userExtModal.getName());
    }
}
