package com.couponDigender.comm.core.enmu;

import lombok.Getter;

/**
 * 参数校验策略枚举
 */

public enum  ValidateStrategy {
    DEFAULT(1 , "正向"),
    Positive(1 , "正向"),
    Reverse(2 , "反向");


    @Getter
    private int strategy;
    @Getter
    private String desc;

    ValidateStrategy(int strategy, String desc) {
        this.strategy = strategy;
        this.desc = desc;
    }
}
