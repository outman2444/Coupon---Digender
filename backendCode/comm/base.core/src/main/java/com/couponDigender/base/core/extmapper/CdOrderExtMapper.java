package com.couponDigender.base.core.extmapper;

import com.couponDigender.base.core.entity.CdOrder;
import com.couponDigender.base.core.extEntity.OrderExtModal;

import java.util.List;

public interface CdOrderExtMapper {

    /**
     * 查询用户统计信息
     * @param queryModal
     * @return
     */
    List<CdOrder> queryStatisticsInfo(OrderExtModal queryModal);
}
