package com.couponDigender.manage.core.RrmoteService;

import com.couponDigender.base.core.extEntity.PromotionExtModal;
import com.couponDigender.comm.core.resp.RespData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "promotion")
public interface PromotionRemoteService {

    @RequestMapping(value = "/promotion/createGenerate" , method = RequestMethod.POST)
    RespData createGenerate(@RequestBody PromotionExtModal promotionExtModal) ;

}
