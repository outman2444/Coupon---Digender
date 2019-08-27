package com.couponDigender.comm.core.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("classpath:application-pdd.properties")
public class PddProperty {


    @Value("${pdd.clientId}")
    private String clientId;

    @Value("${pdd.clientSecret}")
    private String clientSecret ;

    @Value("${pdd.url.host}")
    private String host;

    @Value("${pdd.url.getCode}")
    private String getCodeUrl;

    @Value("${pdd.url.redirectUri}")
    private String redirectUri;
}
