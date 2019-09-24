package com.couponDigender.goods.vc.controller;

import com.couponDigender.base.core.extEntity.GoodsExtModal;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.goods.core.service.GoodsService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsServiceImpl;

     /**
      * 搜索商品
      * @param goodsExtModal
      * @return
      */
    @RequestMapping("search")
    public RespData search(@RequestBody GoodsExtModal goodsExtModal){
         String methodDesc = "搜索商品";
        return goodsServiceImpl.search(methodDesc , goodsExtModal);
    }

    /**
     * 商品详情
     * @param goodsExtModal
     * @return
     */
    @RequestMapping("detail")
    public RespData detail(@RequestBody GoodsExtModal goodsExtModal){
        String methodDesc = "商品详情";
        return goodsServiceImpl.detail(methodDesc , goodsExtModal);
    }

}
