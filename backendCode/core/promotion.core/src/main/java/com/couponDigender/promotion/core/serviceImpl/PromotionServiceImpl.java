package com.couponDigender.promotion.core.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.couponDigender.base.core.entity.CdPromotionPosition;
import com.couponDigender.base.core.extEntity.PromotionExtModal;
import com.couponDigender.base.core.mapper.CdPromotionPositionMapper;
import com.couponDigender.comm.core.contanst.PddContanst;
import com.couponDigender.comm.core.enmu.RespCode;
import com.couponDigender.comm.core.enmu.ValidateStrategy;
import com.couponDigender.comm.core.extModal.FieldModal;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.comm.core.utils.CommUtil;
import com.couponDigender.comm.core.utils.PddUtil;
import com.couponDigender.promotion.core.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    private CdPromotionPositionMapper cdPromotionPositionMapper;

    @Override
    public RespData createGenerate(String methodDesc, PromotionExtModal promotionExtModal) {

        // 参数验证
        RespData valiDateResp = CommUtil.validationParam(methodDesc, promotionExtModal, ValidateStrategy.Positive.getStrategy(),
                new FieldModal("vc2OpenId", "用户OpenId")
        );
        if (valiDateResp.getRespCode() != RespCode.SUCCESS.getCode()) {
            return valiDateResp;
        }

        // 如果用户已经有推广位则直接返回 ， 如果过没有则创建之后返回
        QueryWrapper<CdPromotionPosition> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .eq(CdPromotionPosition::getVc2OpenId, promotionExtModal.getVc2OpenId())
                .eq(CdPromotionPosition::getNumDelFlag, 0);
        CdPromotionPosition cdPromotionPositions = cdPromotionPositionMapper.selectOne(queryWrapper);

        if (cdPromotionPositions != null) {
            return RespData.org(RespCode.SUCCESS.getCode(), methodDesc + "成功", cdPromotionPositions);
        }

        JSONArray nameList = new JSONArray();
        nameList.add(promotionExtModal.getVc2OpenId());

        List<String> pIdNameList = new ArrayList<>();
        pIdNameList.add(promotionExtModal.getVc2OpenId());

        JSONObject param = new JSONObject();
        param.put("number", 1);
        param.put("p_id_name_list", pIdNameList);
        RespData createGenerateResp = PddUtil.doRequest(methodDesc, PddContanst.createPromotionPosition, param);
        if (createGenerateResp.getRespCode() != RespCode.SUCCESS.getCode()) {
            return createGenerateResp;
        }

        // 创建推广位
        // 如果过创建推广位失败 ，  识别是否已经存在
        // 如果已经存在   查询推广位信息 ， 如果不存则  返回错误信息
        JSONObject pIdGenerateResponse;

        JSONObject createGenerateRespBody = JSONObject.parseObject(String.valueOf(createGenerateResp.getBody()));
        if (createGenerateRespBody.get("error_response") != null) {
            JSONObject error_response = createGenerateRespBody.getJSONObject("error_response");
            if (!error_response.getString("sub_msg").contains("已经存在")) {
                return RespData.org(RespCode.FAIL.getCode(), methodDesc + "失败", createGenerateRespBody.get("error_response"));
            } else {
                // 查询已经生成的推广位信息
                RespData queryGenerateResp = queryGenerate(methodDesc, promotionExtModal);
                if (queryGenerateResp.getRespCode() != RespCode.SUCCESS.getCode()) {
                    return queryGenerateResp;
                }
                pIdGenerateResponse = (JSONObject) queryGenerateResp.getBody();
            }
        } else {
            pIdGenerateResponse = createGenerateRespBody.getJSONObject("p_id_generate_response");
        }

        JSONArray pIdList = pIdGenerateResponse.getJSONArray("p_id_list");
        JSONObject pIdGenerateBody = (JSONObject) pIdList.get(0);


        cdPromotionPositions = new CdPromotionPosition();
        cdPromotionPositions.setVc2OpenId(promotionExtModal.getVc2OpenId());
        cdPromotionPositions.setVc2PPromotionPosition(pIdGenerateBody.getString("p_id"));
        cdPromotionPositions.setVc2PromotionPosition(pIdGenerateBody.getString("pid_name"));
        cdPromotionPositions.setDatCreate(LocalDateTime.now());
        cdPromotionPositions.setDatUpdate(LocalDateTime.now());
        cdPromotionPositions.setNumDelFlag(0);

        cdPromotionPositionMapper.insert(cdPromotionPositions);

        return RespData.org(RespCode.SUCCESS.getCode(), methodDesc + "成功", cdPromotionPositions);

    }

    /**
     * 查询已经创建的推广位信息
     * @param methodDesc
     * @param promotionExtModal
     * @return
     */
    private RespData queryGenerate(String methodDesc, PromotionExtModal promotionExtModal) {

        // 分页信息
        int page = 1;
        int pageSize = 100;
        int pageTotal = 99999999;

        // 参数信息
        List<String> pIdNameList = new ArrayList<>();
        pIdNameList.add(promotionExtModal.getVc2OpenId());

        JSONObject queryPromotionPositionParam = new JSONObject();
        queryPromotionPositionParam.put("page" , page);
        queryPromotionPositionParam.put("page_size" , pageSize);

        while (page <= pageTotal){
            RespData queryPromotionPositionResp = PddUtil.doRequest(methodDesc, PddContanst.queryPromotionPosition, queryPromotionPositionParam);
            if(queryPromotionPositionResp.getRespCode() != RespCode.SUCCESS.getCode()){
                return queryPromotionPositionResp;
            }
            JSONObject queryPromotionPositionRespBody =  JSONObject.parseObject(String.valueOf(queryPromotionPositionResp.getBody()));
            if(queryPromotionPositionRespBody.get("error_response") !=null){
                return RespData.org(RespCode.FAIL.getCode() , methodDesc+"失败",queryPromotionPositionRespBody.get("error_response"));
            }

            // 请求成功
            JSONObject pIdQueryResponse = queryPromotionPositionRespBody.getJSONObject("p_id_query_response");
            JSONArray pIdList = pIdQueryResponse.getJSONArray("p_id_list");
            List<JSONObject> pidInfo = pIdList
                    .stream()
                    .map(item -> JSONObject.parseObject(String.valueOf(item)))
                    .filter(item -> item.getString("pid_name").equals(promotionExtModal.getVc2OpenId()))
                    .collect(Collectors.toList());
            if(pidInfo.size() > 0){
                JSONObject pidQueryList = new JSONObject();
                pidQueryList.put("p_id_list" , pidInfo);
                return RespData.org(RespCode.SUCCESS.getCode() , methodDesc + "成功", pidQueryList);
            }

            // 更新分页信息
            int totalCount = pIdQueryResponse.getIntValue("total_count");
            pageTotal = totalCount/pageSize;
            pageTotal += totalCount%pageSize >0 ? 1:0;
            page++;
        }
        return RespData.org(RespCode.FAIL.getCode() , methodDesc+"失败,"+promotionExtModal.getVc2OpenId()+"推广位已经生成，但是查询不到信息");
    }

    @Override
    public RespData takeCoupons(String methodDesc, PromotionExtModal promotionExtModal) {

        // 参数验证
        RespData valiDateResp = CommUtil.validationParam(methodDesc,promotionExtModal, ValidateStrategy.Positive.getStrategy(),
                new FieldModal("vc2OpenId" , "用户OpenId"),
                new FieldModal("vc2GoodId" , "商品ID")
        );
        if(valiDateResp.getRespCode() != RespCode.SUCCESS.getCode()){
            return valiDateResp;
        }

        //查询用户推广位
        QueryWrapper<CdPromotionPosition> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .lambda()
                .eq(CdPromotionPosition::getVc2OpenId , promotionExtModal.getVc2OpenId())
                .eq(CdPromotionPosition::getNumDelFlag , 0);
        CdPromotionPosition cdPromotionPosition = cdPromotionPositionMapper.selectOne(queryWrapper);

        // 请求生成推广链接
        JSONObject param = new JSONObject();
        param.put("p_id" , cdPromotionPosition.getVc2PPromotionPosition());
        param.put("goods_id_list" , Arrays.asList(promotionExtModal.getVc2GoodId()));
        param.put("generate_we_app" , true);
        RespData createPromotionUrlResp = PddUtil.doRequest(methodDesc, PddContanst.createPromotionUrl, param);
        if(createPromotionUrlResp.getRespCode() != RespCode.SUCCESS.getCode()){
            return createPromotionUrlResp;
        }

        JSONObject createPromotionUrlRespBody = JSONObject.parseObject(String.valueOf(createPromotionUrlResp.getBody()));
        if(createPromotionUrlRespBody.containsKey("error_response")){
            return RespData.org(RespCode.FAIL.getCode() , methodDesc+"失败",createPromotionUrlRespBody.get("error_response"));
        }

        JSONObject goodsPromotionUrlGenerateRespons = createPromotionUrlRespBody.getJSONObject("goods_promotion_url_generate_response");
        JSONArray goodsPromotionUrlList = goodsPromotionUrlGenerateRespons.getJSONArray("goods_promotion_url_list");
        JSONObject goodsPromotionUrlList_0 = goodsPromotionUrlList.getJSONObject(0);
        JSONObject weAppInfo = goodsPromotionUrlList_0.getJSONObject("we_app_info");
        return RespData.org(RespCode.SUCCESS.getCode() , methodDesc+"成功" , weAppInfo);
    }
}
