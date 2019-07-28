package com.couponDigender.comm.core.resp;

/**
 * 定义全局接口响应形式
 */
public class RespData {

    // 响应码
    private int respCode;
    // 信息
    private String message;
    // 响应体
    private Object body;

    public RespData(int respCode, String message, Object body) {
        this.respCode = respCode;
        this.message = message;
        this.body = body;
    }

    /**
     * 组织响应体
     * @return
     */
    public static RespData org(int respCode,String message ,Object body){
        return new RespData(respCode , message , body);
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return body;
    }

    public void setObj(Object body) {
        this.body = body;
    }
}
