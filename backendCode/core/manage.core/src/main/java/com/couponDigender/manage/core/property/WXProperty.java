package com.couponDigender.manage.core.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

/**
 * 微信配置管理
 */
@PropertySource("classpath:application-wx.properties")
public class WXProperty {

    @Value("${wx.appId}")
    public static final String APPID = null;

    @Value("${wx.secret}")
    public static final String SECRET = null;

    public interface URL {

        @Value("${wx.auth.code2Session}")
        String CODE_2_SESSION = null;
    }
}
