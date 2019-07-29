package com.couponDigender.comm.core.resp;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定义全局接口响应形式
 */
@Data
public class RespData {

    private static Logger logger = LoggerFactory.getLogger(RespData.class);

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
        logger.info("respCode：{}\nmessage:{}\nbody:{}" , respCode , message , body);
        return new RespData(respCode , message , body);
    }

    /**
     * 组织响应体
     * @return
     */
    public static RespData org(int respCode,String message){
        logger.info("respCode：{}\nmessage:{}" , respCode , message );
        return new RespData(respCode , message , null);
    }
}
