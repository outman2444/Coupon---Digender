package com.couponDigender.comm.core.utils;

import com.couponDigender.comm.core.properties.PddProperty;
import com.couponDigender.comm.core.resp.RespData;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 拼多多公用工具
 */
@Data
public class PddUtil {


    private static PddProperty pddProperty;
    @Autowired
    public void setPddProperty(PddProperty pddProperty) {
        this.pddProperty = pddProperty;
    }

    /**
     * 获取授权码
     * @return
     */
    public static RespData getCode(){
        String methodDesc = "获取code";

        List<String> reqBody = new ArrayList<>();
        reqBody.add(pddProperty.getGetCodeUrl());
        reqBody.add("client_id="+pddProperty.getClientId());
        reqBody.add("redirect_uri="+pddProperty.getRedirectUri());

        String url = StringUtils.join(reqBody.toArray() , "&");

        RespData respData = HttpUtil.get(methodDesc, url);
        return respData;
    }

}
