package com.couponDigender.comm.core.exception;

import com.couponDigender.comm.core.enmu.RespCode;
import com.couponDigender.comm.core.resp.RespData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局统一异常处理
 */
@Component
public class ExceptionHandle {

    private Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler
    public RespData handle(Exception e){
        logger.error("程序出现异常--> {}" , e.getMessage());
        e.printStackTrace();
        return RespData.org(RespCode.EXCEPTION.getCode() , "【异常捕获】"+e.getMessage());
    }
}
