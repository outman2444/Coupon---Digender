package com.couponDigender.comm.core.enmu;

import lombok.Getter;

/**
 * 接口响应码枚举
 */
public enum RespCode {
    SUCCESS(10000 , "请求成功"),
    FAIL(10001 , "请求失败"),
    EXCEPTION(10002 , "请求异常"),
    ;

    @Getter
    private int code;
    @Getter
    private String desc;

    RespCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
