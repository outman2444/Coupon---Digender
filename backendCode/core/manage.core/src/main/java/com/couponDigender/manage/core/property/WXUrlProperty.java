package com.couponDigender.manage.core.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource("classpath:application-wx.properties")
public class WXUrlProperty {
    @Value("${wx.auth.code2Session}")
    private String CODE_2_SESSION;
}
