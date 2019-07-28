package com.couponDigender.comm.core.extModal;

import lombok.Data;

/**
 * 被校验参数对象封装
 */
@Data
public class FieldModal {
    private String name;
    private String desc;

    public FieldModal(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
