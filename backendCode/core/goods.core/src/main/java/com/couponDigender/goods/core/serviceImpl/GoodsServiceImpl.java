package com.couponDigender.goods.core.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.couponDigender.base.core.extEntity.GoodsExtModal;
import com.couponDigender.comm.core.contanst.PddContanst;
import com.couponDigender.comm.core.enmu.RespCode;
import com.couponDigender.comm.core.enmu.ValidateStrategy;
import com.couponDigender.comm.core.extModal.FieldModal;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.comm.core.utils.CommUtil;
import com.couponDigender.comm.core.utils.PddUtil;
import com.couponDigender.goods.core.service.GoodsService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Override
    public RespData search(String methodDesc, GoodsExtModal goodsExtModal) {
        JSONObject param = new JSONObject();
        return PddUtil.doRequest(methodDesc, PddContanst.searchGoodsUrlType, param);
    }

    @Override
    public RespData detail(String methodDesc, GoodsExtModal goodsExtModal) {
        // 参数校验
        RespData validationParamResp = CommUtil.validationParam(methodDesc, goodsExtModal, ValidateStrategy.Positive.getStrategy(),
                new FieldModal("vc2GoodId", "商品ID"));
        if(validationParamResp.getRespCode() != RespCode.SUCCESS.getCode()){
            return validationParamResp;
        }
        JSONObject param = new JSONObject();
        param.put("goods_id_list" , Arrays.asList(goodsExtModal.getVc2GoodId()));
        return PddUtil.doRequest(methodDesc, PddContanst.goodsDetailUrlType, param);
    }
}
