package com.couponDigender.promotion.vc.controller;

import com.couponDigender.base.core.extEntity.PromotionExtModal;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.promotion.core.service.PromotionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionServiceImpl;

    @RequestMapping("/createGenerate")
    @ApiOperation(value = "创建多多进宝推广位" , notes = "创建多多进宝推广位" )
    @ApiParam(required = true , name = "userExtModal"  , value = "用户参数封装实体")
    public RespData createGenerate(@RequestBody PromotionExtModal promotionExtModal) {
        String methodDesc = "创建多多进宝推广位";
        return promotionServiceImpl.createGenerate(methodDesc , promotionExtModal);
    }
}
