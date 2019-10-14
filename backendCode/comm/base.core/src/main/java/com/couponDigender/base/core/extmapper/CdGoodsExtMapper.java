package com.couponDigender.base.core.extmapper;

import com.alibaba.fastjson.JSONObject;
import com.couponDigender.base.core.extEntity.GoodsExtModal;

import java.util.List;

public interface CdGoodsExtMapper {

    List<JSONObject> querySearchHistory(GoodsExtModal goodsExtModal);
}
