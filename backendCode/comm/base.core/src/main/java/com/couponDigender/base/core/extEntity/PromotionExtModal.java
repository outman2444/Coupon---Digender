package com.couponDigender.base.core.extEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "PromotionExtModal对象", description = "")
public class PromotionExtModal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer numId;

    @ApiModelProperty(value = "用户ID")
    private Integer numUserId;

    @ApiModelProperty(value = "用户OPENID")
    private String vc2OpenId;

    @ApiModelProperty(value = "推广位ID")
    private String vc2PromotionPosition;

    @ApiModelProperty(value = "父级推广位ID")
    private String vc2PPromotionPosition;

    @ApiModelProperty(value = "推广位创建时间")
    private LocalDateTime datCreate;

    @ApiModelProperty(value = "剩余创建推广位数量")
    private Integer numRemainCount;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime datUpdate;

    @ApiModelProperty(value = "逻辑删除")
    private Integer numDelFlag;


}

