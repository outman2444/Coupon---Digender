package com.couponDigender.manage.core.utils;

import com.couponDigender.manage.core.constant.WXConstant;
import com.couponDigender.manage.core.property.WXProperty;
import com.couponDigender.manage.core.property.WXUrlProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 微信工具类
 */
@Component
public class WXUtil {

    private static WXProperty wxProperty;
    @Autowired
    public void setWxProperty(WXProperty wxProperty) {
        this.wxProperty = wxProperty;
    }

    /**
     * 获取 code2Session Url
     *
     * @param code
     * @return
     */
    public static String getCode2SessionUrl(String code) {
        String url = wxProperty.getURL().getCODE_2_SESSION();
        url = url.replace(WXConstant.APPID, wxProperty.getAPPID());
        url = url.replace(WXConstant.SECRET, wxProperty.getSECRET());
        url = url.replace(WXConstant.COSE, code);
        return url;
    }
}
