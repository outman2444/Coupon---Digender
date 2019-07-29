package com.couponDigender.manage.core.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 微信配置管理
 */
@Component
@Data
@PropertySource("classpath:application-wx.properties")
public class WXProperty {

    @Value("${wx.appId}")
    private String APPID;

    @Value("${wx.secret}")
    private String SECRET;

    @Autowired
    private WXUrlProperty URL;

}
