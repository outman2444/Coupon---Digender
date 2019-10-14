package com.couponDigender.comm.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.couponDigender.comm.core.properties.PddProperty;
import com.couponDigender.comm.core.resp.RespData;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;

import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 拼多多公用工具
 */
@Data
@Component
public class PddUtil {


    private static PddProperty pddProperty;

    @Autowired
    public void setPddProperty(PddProperty pddProperty) {
        this.pddProperty = pddProperty;
    }

    /**
     * 获取授权码
     *
     * @return
     */
    public static RespData getCode() {
        String methodDesc = "获取code";

        List<String> reqBody = new ArrayList<>();
        reqBody.add(pddProperty.getGetCodeUrl());
        reqBody.add("client_id=" + pddProperty.getClientId());
        reqBody.add("redirect_uri=" + pddProperty.getRedirectUri());

        String url = StringUtils.join(reqBody.toArray(), "&");

        RespData respData = HttpUtil.get(methodDesc, url);
        return respData;
    }

    public static RespData doRequest(String methodDesc, String type, JSONObject param) {
        String url = pddProperty.getHost();

        // 将参数中list参数转换
        param
                .entrySet()
                .stream()
                .filter(entry ->
                        entry.getValue().getClass().getSuperclass() == AbstractList.class)
                .forEach(entry -> {
                    String valueStr = "";
                    List<Object> valueList = (List<Object>) entry.getValue();
                    List<String> newvalueList = valueList
                            .stream()
                            .filter(vItem -> vItem != null)
                            .map(vitem -> {
                                String vItemStr = String.valueOf(vitem);
                                if(vItemStr.matches("\\d*")){
                                    return vItemStr;
                                }
                                return "'" + String.valueOf(vitem) + "'";
                            })
                            .collect(Collectors.toList());
                    valueStr = newvalueList.toString();
                    param.put(entry.getKey(), valueStr);
                });

        param.put("type", type);
        param.put("data_type", "JSON");
        param.put("timestamp", System.currentTimeMillis() + "");
        param.put("client_id", pddProperty.getClientId());
        param.put("sign", getSign(param));

        List<String> paramList = param
                .entrySet()
                .stream()
                .map(item -> item.getKey() + "=" + item.getValue())
                .collect(Collectors.toList());
        url += "?" + StringUtils.join(paramList.toArray(), "&");

        return HttpUtil.get(methodDesc, url);
    }

    public static String getSign(JSONObject param) {
        TreeMap<String, Object> treeMap = new TreeMap<>(param);

        List<String> paramList = treeMap
                .entrySet()
                .stream()
                .map(item -> item.getKey() + item.getValue())
                .collect(Collectors.toList());
        String join = pddProperty.getClientSecret();
        join += StringUtils.join(paramList.toArray(), "");
        join += pddProperty.getClientSecret();
        String md5 = DigestUtils.md5DigestAsHex(join.getBytes());
        md5 = StringUtils.upperCase(md5);
        return md5;
    }

}
