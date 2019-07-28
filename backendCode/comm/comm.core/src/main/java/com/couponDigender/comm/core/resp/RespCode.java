package com.couponDigender.comm.core.resp;

/**
 * 接口响应码枚举
 */
public enum RespCode {
    SUCCESS(10000 , "请求成功"),
    FAIL(10001 , "请求失败"),
    EXCEPTION(10002 , "请求异常"),
    ;


    private int code;
    private String desc;

    RespCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
