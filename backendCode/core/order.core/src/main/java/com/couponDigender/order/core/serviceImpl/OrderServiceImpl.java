package com.couponDigender.order.core.serviceImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.couponDigender.base.core.entity.CdOrder;
import com.couponDigender.base.core.extEntity.OrderExtModal;
import com.couponDigender.base.core.mapper.CdOrderMapper;
import com.couponDigender.comm.core.contanst.PddContanst;
import com.couponDigender.comm.core.enmu.RespCode;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.comm.core.utils.LocalDateTimeUtils;
import com.couponDigender.comm.core.utils.PddUtil;
import com.couponDigender.order.core.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private CdOrderMapper cdOrderMapper;

    @Override
    public RespData crawlingPddOrder(String methodDesc, OrderExtModal queryModal) {

        // 查询历史信息
        List<CdOrder> cdOrderList = cdOrderMapper.selectList(new QueryWrapper<CdOrder>());
        List<String> orderIdList = cdOrderList.stream().map(item -> item.getVc2OrderId()).collect(Collectors.toList());

        // 时间信息
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (queryModal.getStartTime() != null && queryModal.getEndTime() != null) {
            startTime = queryModal.getStartTime();
            endTime = queryModal.getEndTime();
        } else {
            endTime = LocalDateTime.now();
            startTime = endTime.minusHours(1);
        }

        // 计算时间相差
        Duration between = Duration.between(startTime, endTime);
        long days = between.toDays();

        if (days >= 1) {
            endTime = startTime.plusDays(1);
        }

        for (int day = 0; day <= days; day++) {
            doCrawlingPddOrder(methodDesc, startTime, endTime , orderIdList);
            startTime = startTime.plusDays(1);
            endTime = endTime.plusDays(1);
        }


        return RespData.org(RespCode.SUCCESS.getCode(), methodDesc + "成功");
    }

    /**
     * 执行爬取订单信息
     *  @param methodDesc
     * @param startTime
     * @param endTime
     * @param orderIdList
     */
    private void doCrawlingPddOrder(String methodDesc, LocalDateTime startTime, LocalDateTime endTime, List<String> orderIdList) {

        // 分页信息
        int page = 1;
        int pageSize = 50;
        int pageTotal = 999999;

        while (page < pageTotal) {

            JSONObject param = new JSONObject();
            param.put("start_update_time", startTime.toEpochSecond(ZoneOffset.of("+8")));
            param.put("end_update_time", endTime.toEpochSecond(ZoneOffset.of("+8")));
            param.put("page", page);
            param.put("page_size", pageSize);
            RespData doRequestResp = PddUtil.doRequest(methodDesc, PddContanst.queryOrderList, param);
            logger.info("{}定时任务,时间【{}~{}】,共【{}】页，第【{}】页，执行结果 -->{}"
                    , methodDesc
                    , startTime
                    , endTime
                    , pageTotal
                    , page,
                    doRequestResp);

            // 解析爬取结果
            JSONObject jsonObject = JSONObject.parseObject(doRequestResp.getBody().toString());
            JSONObject orderListGetResponse = jsonObject.getJSONObject("order_list_get_response");
            // 设置总页数
            int totalCount = orderListGetResponse.getIntValue("total_count");
            pageTotal = totalCount / pageSize;
            if (totalCount % pageSize > 0) pageTotal++;

            // 获取结果内容
            JSONArray orderList = orderListGetResponse.getJSONArray("order_list");
            orderList
                    .stream()
                    .map(orderInfo -> JSONObject.parseObject(JSONObject.toJSON(orderInfo).toString()))
                    .filter(orderInfo -> !orderIdList.contains(orderInfo.getString("order_id")))
                    .forEach(orderInfo -> {
                        CdOrder cdOrder = new CdOrder();
                        cdOrder.setDatCreateTime(LocalDateTime.now());
                        cdOrder.setVc2GoodsPrice(orderInfo.getString("goods_price"));
                        cdOrder.setVc2PromotionRate(orderInfo.getString("promotion_rate"));
                        cdOrder.setNumType(orderInfo.getIntValue("type"));
                        cdOrder.setNumOrderStatus(orderInfo.getIntValue("order_status"));
                        cdOrder.setDatOrderCreateTime(LocalDateTimeUtils.seconsLongToLocalDateTime(orderInfo.getLong("order_create_time")));
                        cdOrder.setDatOrderSettltTime(LocalDateTimeUtils.seconsLongToLocalDateTime(orderInfo.getLong("order_settle_time")));
                        cdOrder.setDatOrderVertifyTime(LocalDateTimeUtils.seconsLongToLocalDateTime(orderInfo.getLong("order_verify_time")));
                        cdOrder.setDatOrderGroupSuccessTime(LocalDateTimeUtils.seconsLongToLocalDateTime(orderInfo.getLong("order_group_success_time")));
                        cdOrder.setVc2OrderAmount(orderInfo.getString("order_amount"));
                        cdOrder.setDatOrderModifyAt(LocalDateTimeUtils.seconsLongToLocalDateTime(orderInfo.getLong("order_modify_at")));
                        cdOrder.setVc2AuthDuoId(orderInfo.getString("auth_duo_id"));
                        cdOrder.setNumCapNew(orderInfo.getIntValue("cpa_new"));
                        cdOrder.setVc2GoodsName(orderInfo.getString("goods_name"));
                        cdOrder.setVc2BatchNo(orderInfo.getString("batch_no"));
                        cdOrder.setNumGoodsQuantity(orderInfo.getIntValue("goods_quantity"));
                        cdOrder.setVc2GoodsId(orderInfo.getString("goods_id"));
                        cdOrder.setVc2GoodsThimbnailUrl(orderInfo.getString("goods_thumbnail_url"));
                        cdOrder.setDatOrderReceiveTime(LocalDateTimeUtils.seconsLongToLocalDateTime(orderInfo.getLong("order_receive_time")));
                        cdOrder.setVc2CustomParameters(orderInfo.getString("custom_parameters"));
                        cdOrder.setVc2PromotionAmount(orderInfo.getString("promotion_amount"));
                        cdOrder.setDatOrderPayTime(LocalDateTimeUtils.seconsLongToLocalDateTime(orderInfo.getLong("order_pay_time")));
                        cdOrder.setVc2GroupId(orderInfo.getString("group_id"));
                        cdOrder.setVc2OrderStatusDesc(orderInfo.getString("order_status_desc"));
                        cdOrder.setVc2FailReason(orderInfo.getString("fail_reason"));
                        cdOrder.setVc2OrderId(orderInfo.getString("order_id"));
                        cdOrder.setVc2OrderSn(orderInfo.getString("order_sn"));
                        cdOrder.setVc2PId(orderInfo.getString("p_id"));
                        cdOrder.setVc2ZsDuoId(orderInfo.getString("zs_duo_id"));
                        cdOrder.setVc2DuoCouponAmount(orderInfo.getString("duo_coupon_amount"));
                        cdOrderMapper.insert(cdOrder);
                    });

            break;
        }


    }
}
