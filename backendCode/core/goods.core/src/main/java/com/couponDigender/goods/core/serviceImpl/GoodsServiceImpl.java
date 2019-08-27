package com.couponDigender.goods.core.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.couponDigender.base.core.extEntity.GoodsExtModal;
import com.couponDigender.comm.core.contanst.PddContanst;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.comm.core.utils.PddUtil;
import com.couponDigender.goods.core.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Override
    public RespData search(String methodDesc, GoodsExtModal goodsExtModal) {
        JSONObject param = new JSONObject();
        return PddUtil.doRequest(methodDesc, PddContanst.searchGoodsUrlType, param);
    }
}
