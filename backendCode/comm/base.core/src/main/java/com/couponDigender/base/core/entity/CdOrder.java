package com.couponDigender.base.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author outman
 * @since 2019-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CdOrder对象", description="")
public class CdOrder implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "NUM_ID", type = IdType.AUTO)
    private Integer numId;

    @ApiModelProperty(value = "推广订单编号")
    @TableField("VC2_ORDER_SN")
    private String vc2OrderSn;

    @TableField("VC2_GOODS_ID")
    private String vc2GoodsId;

    @TableField("VC2_GOODS_NAME")
    private String vc2GoodsName;

    @ApiModelProperty(value = "商品缩略图")
    @TableField("VC2_GOODS_THIMBNAIL_URL")
    private String vc2GoodsThimbnailUrl;

    @ApiModelProperty(value = "购买数量")
    @TableField("NUM_GOODS_QUANTITY")
    private Integer numGoodsQuantity;

    @TableField("VC2_GOODS_PRICE")
    private String vc2GoodsPrice;

    @TableField("VC2_ORDER_AMOUNT")
    private String vc2OrderAmount;

    @ApiModelProperty(value = "推广位ID")
    @TableField("VC2_P_ID")
    private String vc2PId;

    @ApiModelProperty(value = "佣金比例")
    @TableField("VC2_PROMOTION_RATE")
    private String vc2PromotionRate;

    @ApiModelProperty(value = "佣金金额")
    @TableField("VC2_PROMOTION_AMOUNT")
    private String vc2PromotionAmount;

    @ApiModelProperty(value = "订单状态-1 未支付; 0-已支付；1-已成团；2-确认收货；3-审核成功；4-审核失败（不可提现）；5-已经结算；8-非多多进宝商品（无佣金订单）")
    @TableField("NUM_ORDER_STATUS")
    private Integer numOrderStatus;

    @TableField("VC2_ORDER_STATUS_DESC")
    private String vc2OrderStatusDesc;

    @TableField("DAT_ORDER_CREATE_TIME")
    private LocalDateTime datOrderCreateTime;

    @TableField("DAT_ORDER_PAY_TIME")
    private LocalDateTime datOrderPayTime;

    @TableField("DAT_ORDER_GROUP_SUCCESS_TIME")
    private LocalDateTime datOrderGroupSuccessTime;

    @TableField("DAT_ORDER_VERTIFY_TIME")
    private LocalDateTime datOrderVertifyTime;

    @TableField("DAT_ORDER_MODIFY_AT")
    private LocalDateTime datOrderModifyAt;

    @TableField("VC2_CUSTOM_PARAMETERS")
    private String vc2CustomParameters;

    @ApiModelProperty(value = "是否是 cpa 新用户，1表示是，0表示否")
    @TableField("NUM_CAP_NEW")
    private Integer numCapNew;

    @ApiModelProperty(value = "推广类型")
    @TableField("NUM_TYPE")
    private Integer numType;

    @TableField("DAT_ORDER_SETTLT_TIME")
    private LocalDateTime datOrderSettltTime;

    @ApiModelProperty(value = "多多客工具ID")
    @TableField("VC2_AUTH_DUO_ID")
    private String vc2AuthDuoId;

    @ApiModelProperty(value = "结算批次号")
    @TableField("VC2_BATCH_NO")
    private String vc2BatchNo;

    @ApiModelProperty(value = "确认收货时间")
    @TableField("DAT_ORDER_RECEIVE_TIME")
    private LocalDateTime datOrderReceiveTime;

    @TableField("VC2_GROUP_ID")
    private String vc2GroupId;

    @ApiModelProperty(value = "审核失败原因")
    @TableField("VC2_FAIL_REASON")
    private String vc2FailReason;

    @TableField("VC2_ORDER_ID")
    private String vc2OrderId;

    @ApiModelProperty(value = "招商多多客ID")
    @TableField("VC2_ZS_DUO_ID")
    private String vc2ZsDuoId;

    @ApiModelProperty(value = "创建时间")
    @TableField("DAT_CREATE_TIME")
    private LocalDateTime datCreateTime;

    @TableField("VC2_DUO_COUPON_AMOUNT")
    private String vc2DuoCouponAmount;


}
