package com.couponDigender.order.core.service;

import com.couponDigender.base.core.extEntity.OrderExtModal;
import com.couponDigender.comm.core.resp.RespData;

public interface OrderService {

    /**
     * 爬取拼多多订单
     * @param methodDesc
     * @param queryModal
     * @return
     */
    RespData crawlingPddOrder(String methodDesc, OrderExtModal queryModal);
}
