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
    @ApiParam(required = true , name = "promotionExtModal"  , value = "order参数封装实体")
    public RespData crawlingPddOrder(OrderExtModal queryModal) {
        String methodDesc = "爬取订单";
        return orderServiceImpl.crawlingPddOrder(methodDesc , queryModal);
    }

}
