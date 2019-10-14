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

    /**
     * 商品详情
     * @param methodDesc
     * @param goodsExtModal
     * @return
     */
    RespData detail(String methodDesc, GoodsExtModal goodsExtModal);

    /**
     * 收藏商品
     * @param methodDesc
     * @param goodsExtModal
     * @return
     */
    RespData collection(String methodDesc, GoodsExtModal goodsExtModal);

    /**
     * 查询商品搜索历史
     * @param methodDesc
     * @param goodsExtModal
     * @return
     */
    RespData querySearchHistory(String methodDesc, GoodsExtModal goodsExtModal);
}
