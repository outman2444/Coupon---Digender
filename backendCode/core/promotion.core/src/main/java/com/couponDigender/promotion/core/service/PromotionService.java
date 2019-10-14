package com.couponDigender.promotion.core.service;

import com.couponDigender.base.core.extEntity.PromotionExtModal;
import com.couponDigender.comm.core.resp.RespData;

public interface PromotionService {

    /**
     * 创建多多进宝推广位
     * @param methodDesc
     * @param promotionExtModal
     * @return
     */
    RespData createGenerate(String methodDesc, PromotionExtModal promotionExtModal);

    /**
     * 领取优惠券   创建推广链接
     * @param methodDesc
     * @param promotionExtModal
     * @return
     */
    RespData takeCoupons(String methodDesc, PromotionExtModal promotionExtModal);
}
