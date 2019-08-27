package com.couponDigender.goods.core.service;

import com.couponDigender.base.core.extEntity.GoodsExtModal;
import com.couponDigender.comm.core.resp.RespData;

public interface GoodsService {

    /**
     * 搜索商品
     * @param methodDesc
     * @param goodsExtModal
     * @return
     */
    RespData search(String methodDesc, GoodsExtModal goodsExtModal);
}
