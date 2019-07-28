package com.couponDigender.manage.core.utils;

import com.couponDigender.manage.core.constant.WXConstant;
import com.couponDigender.manage.core.property.WXProperty;

/**
 * 微信工具类
 */
public class WXUtil {

    /**
     * 获取 code2Session Url
     *
     * @param code
     * @return
     */
    public String getCode2SessionUrl(String code) {
        String url = WXProperty.URL.CODE_2_SESSION;
        url = url.replace(WXConstant.APPID, WXProperty.APPID);
        url = url.replace(WXConstant.SECRET, WXProperty.SECRET);
        url = url.replace(WXConstant.COSE, code);
        return url;
    }
}
