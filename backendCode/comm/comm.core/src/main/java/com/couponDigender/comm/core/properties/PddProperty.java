package com.couponDigender.comm.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class PddProperty {
    @Value("${pdd.url.host}")
    private String router;

    @Value("${pdd.clientId}")
    private String clientId;

    @Value("${pdd.clientSecret}")
    private String clientSecret ;

    @Value("${pdd.code}")
    private String code ;

    @Value("${pdd.refreshToken}")
    private String refreshToken ;

    @Value("${pdd.url.getCode}")
    private String getCodeUrl;

    @Value("${pdd.url.redirectUri}")
    private String redirectUri;
}
