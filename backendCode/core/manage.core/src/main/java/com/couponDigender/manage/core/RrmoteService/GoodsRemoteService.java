package com.couponDigender.manage.core.RrmoteService;

import com.couponDigender.base.core.extEntity.GoodsExtModal;
import com.couponDigender.comm.core.resp.RespData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "goods")
public interface GoodsRemoteService {

    @RequestMapping(value = "/goods/search" , method = RequestMethod.POST)
    RespData search(@RequestBody GoodsExtModal goodsExtModal);
}
