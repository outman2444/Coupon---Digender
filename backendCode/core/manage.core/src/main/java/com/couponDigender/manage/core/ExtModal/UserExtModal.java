package com.couponDigender.manage.core.extModal;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户相关请求参数封装
 */
@Data
@Api(value = "操作用户接口参数封装")
public class UserExtModal {

    @ApiModelProperty(value = "姓名")
    String name;

    @ApiModelProperty(value = "微信code")
    String wxCode;

    @ApiModelProperty(value = "openId")
    String openId;

    @ApiModelProperty(value = "昵称")
    String nickName;

    @ApiModelProperty(value = "性别")
    Integer gender;

    @ApiModelProperty(value = "头像")
    String avatarUrl;

    @ApiModelProperty(value = "城市")
    String city;

    @ApiModelProperty(value = "国家")
    String country;

    @ApiModelProperty(value = "语言")
    String language;

    @ApiModelProperty(value = "省份")
    String province;

}
