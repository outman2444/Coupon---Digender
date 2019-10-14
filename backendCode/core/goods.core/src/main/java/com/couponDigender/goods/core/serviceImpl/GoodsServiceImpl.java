package com.couponDigender.goods.core.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.couponDigender.base.core.entity.CdUserGoodsCollect;
import com.couponDigender.base.core.entity.CdUserSearchHistory;
import com.couponDigender.base.core.extEntity.GoodsExtModal;
import com.couponDigender.base.core.mapper.CdUserGoodsCollectMapper;
import com.couponDigender.base.core.mapper.CdUserSearchHistoryMapper;
import com.couponDigender.comm.core.contanst.PddContanst;
import com.couponDigender.comm.core.enmu.RespCode;
import com.couponDigender.comm.core.enmu.ValidateStrategy;
import com.couponDigender.comm.core.extModal.FieldModal;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.comm.core.utils.CommUtil;
import com.couponDigender.comm.core.utils.PddUtil;
import com.couponDigender.base.core.extmapper.CdGoodsExtMapper;
import com.couponDigender.goods.core.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private CdUserGoodsCollectMapper cdUserGoodsCollectMapper;

    @Autowired
    private CdUserSearchHistoryMapper cdUserSearchHistoryMapper;

    @Autowired
    private CdGoodsExtMapper cdGoodsExtMapper;

    @Override
    public RespData search(String methodDesc, GoodsExtModal goodsExtModal) {

        // 保存用户搜索 关键词
        if(!StringUtils.isEmpty(goodsExtModal.getVc2GoodName())){
            CdUserSearchHistory cdUserSearchHistory = new CdUserSearchHistory();
            cdUserSearchHistory.setVc2OpenId(goodsExtModal.getVc2OpenId());
            cdUserSearchHistory.setVc2KeyWord(goodsExtModal.getVc2GoodName());
            cdUserSearchHistory.setDatCreateTime(LocalDateTime.now());
            cdUserSearchHistory.setDatUpdateTime(LocalDateTime.now());
            cdUserSearchHistory.setNumDelFlag(0);
            cdUserSearchHistoryMapper.insert(cdUserSearchHistory);
        }

        // 执行搜索
        JSONObject param = new JSONObject();
        param.put("keyword" , goodsExtModal.getVc2GoodName());
        param.put("page" , goodsExtModal.getPage());
        param.put("sort_type" , goodsExtModal.getSortType());

        return PddUtil.doRequest(methodDesc, PddContanst.searchGoodsUrlType, param);
    }

    @Override
    public RespData detail(String methodDesc, GoodsExtModal goodsExtModal) {
        // 参数校验
        RespData validationParamResp = CommUtil.validationParam(methodDesc, goodsExtModal, ValidateStrategy.Positive.getStrategy(),
                new FieldModal("vc2GoodId", "商品ID"),
                new FieldModal("vc2OpenId", "openId"));

        if (validationParamResp.getRespCode() != RespCode.SUCCESS.getCode()) {
            return validationParamResp;
        }
        JSONObject param = new JSONObject();
        param.put("goods_id_list", Arrays.asList(goodsExtModal.getVc2GoodId()));
        RespData doRequestResp = PddUtil.doRequest(methodDesc, PddContanst.goodsDetailUrlType, param);
        if (doRequestResp.getRespCode() != RespCode.SUCCESS.getCode()) {
            return doRequestResp;
        }
        JSONObject jsonObject = JSONObject.parseObject(doRequestResp.getBody().toString());
        JSONObject goodsDetailResponse = jsonObject.getJSONObject("goods_detail_response");
        JSONObject goodsDetailInfo = goodsDetailResponse.getJSONArray("goods_details").getJSONObject(0);
        String goodsId = goodsDetailInfo.getString("goods_id");
        QueryWrapper<CdUserGoodsCollect> cdUserGoodsCollectQueryWrapper = new QueryWrapper<>();
        cdUserGoodsCollectQueryWrapper
                .lambda()
                .eq(CdUserGoodsCollect::getVc2OpenId,goodsExtModal.getVc2OpenId())
                .eq(CdUserGoodsCollect::getVc2GoodsId,goodsId);
        CdUserGoodsCollect cdUserGoodsCollect = cdUserGoodsCollectMapper.selectOne(cdUserGoodsCollectQueryWrapper);
        if(cdUserGoodsCollect !=null && cdUserGoodsCollect.getNumDelFlag()!= 1){
            goodsDetailResponse.put("isCollect" , true);
        }else{
            goodsDetailResponse.put("isCollect" , false);
        }
        return RespData.org(RespCode.SUCCESS.getCode() , methodDesc+"成功" , jsonObject);
    }

    @Override
    public RespData collection(String methodDesc, GoodsExtModal goodsExtModal) {

        QueryWrapper<CdUserGoodsCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(CdUserGoodsCollect::getVc2GoodsId , goodsExtModal.getVc2GoodId())
                .eq(CdUserGoodsCollect::getVc2OpenId , goodsExtModal.getVc2OpenId());
        CdUserGoodsCollect goodsCollect = cdUserGoodsCollectMapper.selectOne(queryWrapper);
        if(goodsCollect == null){
            goodsCollect = new CdUserGoodsCollect();
            goodsCollect.setVc2OpenId(goodsExtModal.getVc2OpenId());
            goodsCollect.setVc2GoodsId(goodsExtModal.getVc2GoodId());
            goodsCollect.setNumDelFlag(0);
            goodsCollect.setDatCreateTime(LocalDateTime.now());
            goodsCollect.setDatUpdateTime(LocalDateTime.now());
            cdUserGoodsCollectMapper.insert(goodsCollect);
        }else{
            goodsCollect.setNumDelFlag((goodsCollect.getNumDelFlag()+1)%2);
            goodsCollect.setDatUpdateTime(LocalDateTime.now());
            cdUserGoodsCollectMapper.updateById(goodsCollect);
        }

        return RespData.org(RespCode.SUCCESS.getCode() , methodDesc+"成功" , goodsCollect);
    }

    @Override
    public RespData querySearchHistory(String methodDesc, GoodsExtModal goodsExtModal) {

        List<JSONObject> historyList = cdGoodsExtMapper.querySearchHistory(goodsExtModal);

        return RespData.org(RespCode.SUCCESS.getCode() , methodDesc+"成功" , historyList);
    }
}
