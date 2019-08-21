package com.couponDigender.comm.core.extModal;

import lombok.Data;

/**
 * 拼多多请求基本参数封装
 */
@Data
public class PddRequestBeseModal {

    // 接口名称
    private String type;

    // 应用ID
    private String clientId;

    // accessToken
    private String accessToken;

    // 时间戳
    private String timestamp;

    // 响应数据类型  json xml
    private String dataType;

    // api协议版本号
    private String version;

    // 签名
    private String sign;

    public PddRequestBeseModal(String type, String clientId, String accessToken, String timestamp, String dataType, String version, String sign) {
        this.type = type;
        this.clientId = clientId;
        this.accessToken = accessToken;
        this.timestamp = timestamp;
        this.dataType = dataType;
        this.version = version;
        this.sign = sign;
    }
}
