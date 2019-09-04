package com.couponDigender.base.core.extEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品相关接口参数封装
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Goods参数封装对象", description="")
public class GoodsExtModal implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "NUM_ID", type = IdType.AUTO)
    private Integer numId;

    @TableField("VC2_GOOD_NAME")
    private String vc2GoodName;

    @TableField("DAT_CLT_CPN_END_TIME")
    private LocalDateTime datCltCpnEndTime;

    @TableField("DAT_CPN_MIN_AMT")
    private String datCpnMinAmt;

    @TableField("NUM_COUPON_REMAIN_QUANTITY")
    private Integer numCouponRemainQuantity;

    @TableField("NUM_CLT_CPN_REMAIN_QUANTITY")
    private String numCltCpnRemainQuantity;

    @TableField("NUM_PROMOTION_RATE")
    private Integer numPromotionRate;

    @TableField("VC2_SERVICE_TAGS")
    private String vc2ServiceTags;

    @TableField("NUM_MALL_ID")
    private Integer numMallId;

    @TableField("VC2_MALL_NAME")
    private String vc2MallName;

    @TableField("NUM_MALL_COUPON_END_TIME")
    private LocalDateTime numMallCouponEndTime;

    @TableField("VC2_CPN_BATCH_SN")
    private String vc2CpnBatchSn;

    @TableField("VC2_LGST_TXT")
    private String vc2LgstTxt;

    @TableField("VC2_CATEHORY_NAME")
    private String vc2CatehoryName;

    @TableField("NUM_CLT_CPN_DISCOUNT")
    private Integer numCltCpnDiscount;

    @TableField("VC2_GOOD_ID")
    private String vc2GoodId;

    @TableField("VC2_GOODS_GALLERY_URLS")
    private String vc2GoodsGalleryUrls;

    @TableField("VC2_GOODS_DESC")
    private String vc2GoodsDesc;

    @TableField("VC2_OPT_NAME")
    private String vc2OptName;

    @TableField("VC2_OPT_IDS")
    private String vc2OptIds;

    @TableField("VC2_GOODS_IMAGE_URL")
    private String vc2GoodsImageUrl;

    @TableField("BLO_HAS_MALL_COUPON")
    private Boolean bloHasMallCoupon;

    @TableField("VC2_MIN_GROUP_PRICE")
    private String vc2MinGroupPrice;

    @TableField("DAT_COUPON_END_TIME")
    private LocalDateTime datCouponEndTime;

    @TableField("NUM_ZS_DUO_ID")
    private Integer numZsDuoId;

    @TableField("NUM_MALL_COUPON_REMAIN_QUANTITY")
    private Integer numMallCouponRemainQuantity;

    @TableField("NUM_PLAN_TYPE")
    private Integer numPlanType;

    @TableField("NUM_CLT_CPN_QUANTITY")
    private Integer numCltCpnQuantity;

    @TableField("VC2_CRT_RF_ORDER_RTOLM")
    private String vc2CrtRfOrderRtolm;

    @TableField("VC2_CPS_SIGN")
    private String vc2CpsSign;

    @TableField("VC2_CAT_IDS")
    private String vc2CatIds;

    @TableField("NUM_COUPON_MIN_ORDER_AMOUNT")
    private Integer numCouponMinOrderAmount;

    @TableField("NUM_CATRGORY_ID")
    private Integer numCatrgoryId;

    @TableField("NUM_MALL_COUPON_DISCOUNT_PCT")
    private Integer numMallCouponDiscountPct;

    @TableField("NUM_ACTIVITY_TYPE")
    private Integer numActivityType;

    @TableField("NUM_CAT_ID")
    private Integer numCatId;

    @TableField("NUM_COUPON_TOTAL_QUANTITY")
    private Integer numCouponTotalQuantity;

    @TableField("NUM_MALL_COUPON_MIN_ORDER_AMOUNT")
    private Integer numMallCouponMinOrderAmount;

    @TableField("NUM_MERCHANT_TYPE")
    private Integer numMerchantType;

    @TableField("DAT_CLT_CPN_START_TIME")
    private LocalDateTime datCltCpnStartTime;

    @TableField("VC2_SALES_TIP")
    private String vc2SalesTip;

    @TableField("NUM_MALL_COUPON_ID")
    private Integer numMallCouponId;

    @TableField("VC2_DESC_TXT")
    private String vc2DescTxt;

    @TableField("VC2_GOODS_THUMBNAIL_URL")
    private String vc2GoodsThumbnailUrl;

    @TableField("NUM_OPT_ID")
    private Integer numOptId;

    @TableField("VC2_MIN_NORMAL_PIRCE")
    private String vc2MinNormalPirce;

    @TableField("BLO_HAS_COUPON")
    private Integer bloHasCoupon;

    @TableField("DAT_MALL_COUPON_START_TIME")
    private LocalDateTime datMallCouponStartTime;

    @TableField("VC2_SERV_TXT")
    private String vc2ServTxt;

    @TableField("NUM_MALL_RATE")
    private Integer numMallRate;

    @TableField("NUM_MALL_COUPON_TOTAL_QUANTITY")
    private Integer numMallCouponTotalQuantity;

    @TableField("DAT_CREATE_AT")
    private LocalDateTime datCreateAt;

    @TableField("NUM_MALL_COUPON_MAX_DISCOUNT_AMOUNT")
    private Integer numMallCouponMaxDiscountAmount;

    @TableField("NUM_MALL_CPS")
    private Integer numMallCps;


}