package com.couponDigender.order.core.task;

import com.couponDigender.base.core.extEntity.OrderExtModal;
import com.couponDigender.order.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 订单定时任务
 */

@Component
public class OrderTask {
    @Autowired
    private OrderService orderServiceImpl;

//    @Scheduled(cron="*/6 * * * * ?")
    private void process(){

        // 执行爬取订单信息
        OrderExtModal orderExtModal = new OrderExtModal();
        String crawlingPddOrderDesc = "爬取拼多多订单信息";
        orderServiceImpl.crawlingPddOrder(crawlingPddOrderDesc , orderExtModal);
    }
}
