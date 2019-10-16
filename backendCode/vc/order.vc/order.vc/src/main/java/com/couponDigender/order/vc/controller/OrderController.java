package com.couponDigender.order.vc.controller;

import com.couponDigender.base.core.extEntity.OrderExtModal;
import com.couponDigender.base.core.extEntity.PromotionExtModal;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.order.core.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderServiceImpl;

    @RequestMapping("crawlingPddOrder")
    @ApiOperation(value = "爬取订单" , notes = "爬取订单" )
    @ApiParam(required = true , name = "crawlingPddOrder"  , value = "order参数封装实体")
    public RespData crawlingPddOrder(OrderExtModal queryModal) {
        String methodDesc = "爬取订单";
        return orderServiceImpl.crawlingPddOrder(methodDesc , queryModal);
    }

    @RequestMapping("queryStatisticsInfo")
    @ApiOperation(value = "查询统计信息" , notes = "查询统计信息" )
    @ApiParam(required = true , name = "queryStatisticsInfo"  , value = "order参数封装实体")
    public RespData queryStatisticsInfo(@RequestBody OrderExtModal queryModal) {
        String methodDesc = "查询统计信息";
        return orderServiceImpl.queryStatisticsInfo(methodDesc , queryModal);
    }

}
