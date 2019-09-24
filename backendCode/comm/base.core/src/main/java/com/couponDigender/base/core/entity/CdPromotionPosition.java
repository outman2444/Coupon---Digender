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
 * @since 2019-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CdPromotionPosition对象", description="")
public class CdPromotionPosition implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "NUM_ID", type = IdType.AUTO)
    private Integer numId;

    @ApiModelProperty(value = "用户openID")
    @TableField("VC2_OPEN_ID")
    private String vc2OpenId;

    @ApiModelProperty(value = "推广位ID")
    @TableField("VC2__PROMOTION_POSITION")
    private String vc2PromotionPosition;

    @ApiModelProperty(value = "父级推广位ID")
    @TableField("VC2__P_PROMOTION_POSITION")
    private String vc2PPromotionPosition;

    @ApiModelProperty(value = "推广位创建时间")
    @TableField("DAT_CREATE")
    private LocalDateTime datCreate;

    @ApiModelProperty(value = "剩余创建推广位数量")
    @TableField("NUM_REMAIN_COUNT")
    private Integer numRemainCount;

    @ApiModelProperty(value = "更新时间")
    @TableField("DAT_UPDATE")
    private LocalDateTime datUpdate;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("NUM_DEL_FLAG")
    private Integer numDelFlag;


}
