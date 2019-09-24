package com.couponDigender.manage.vc.controller;

import com.couponDigender.base.core.extEntity.GoodsExtModal;
import com.couponDigender.comm.core.exception.ExceptionHandle;
import com.couponDigender.comm.core.enmu.RespCode;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.base.core.extEntity.UserExtModal;
import com.couponDigender.manage.core.RrmoteService.GoodsRemoteService;
import com.couponDigender.manage.core.RrmoteService.PromotionRemoteService;
import com.couponDigender.manage.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户相关
 */

@RestController
@RequestMapping("/user")
@Api(value = "用户Controller" , description = "用户操作接口")
public class UserController extends ExceptionHandle{

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private GoodsRemoteService goodsRemoteService;

    @RequestMapping("/hello")
    @ApiOperation(value = "测试swagger" , notes = "测试接口 ， 测试swagger" )
    @ApiParam(required = true , name = "userExtModal"  , value = "用户参数封装实体")
    public RespData hello(@RequestBody UserExtModal userExtModal) {
        RespData search = goodsRemoteService.search(new GoodsExtModal());
        System.out.println(search);
        String methodDesc = "微笑";
        return RespData.org(RespCode.SUCCESS.getCode(), methodDesc + "成功", "hello " + userExtModal.getName());
    }

    /**
     * 通过 wxCode 登陆
     * @param userExtModal
     * @return
     */
    @PostMapping("/loginByCode")
    @ApiOperation(value = "通过code 登录" , notes = "登录接口" )
    @ApiParam(required = true , name = "userExtModal"  , value = "用户参数封装实体")
    public RespData loginByCode(@RequestBody UserExtModal userExtModal) {
        String methodDesc = "通过 wxCode 登陆";
        return userServiceImpl.loginByCode(methodDesc ,userExtModal);
    }

    /**
     * 更新用户信息
     * @param userExtModal
     * @return
     */
    @PostMapping("/updateUserInfo")
    @ApiOperation(value = "更新用户信息" , notes = "更新用户信息" )
    @ApiParam(required = true , name = "userExtModal"  , value = "用户参数封装实体")
    public RespData updateUserInfo(@RequestBody UserExtModal userExtModal) {
        String methodDesc = "更新用户信息";
        return userServiceImpl.updateUserInfo(methodDesc ,userExtModal);
    }

}
