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
 * @since 2019-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CdUserGoodsCollect对象", description="")
public class CdUserGoodsCollect implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "NUM_ID", type = IdType.AUTO)
    private Integer numId;

    @ApiModelProperty(value = "关注者openId")
    @TableField("VC2_OPEN_ID")
    private String vc2OpenId;

    @ApiModelProperty(value = "商品ID")
    @TableField("VC2_GOODS_ID")
    private String vc2GoodsId;

    @ApiModelProperty(value = "创建时间")
    @TableField("DAT_CREATE_TIME")
    private LocalDateTime datCreateTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("DAT_UPDATE_TIME")
    private LocalDateTime datUpdateTime;

    @ApiModelProperty(value = "删除标志")
    @TableField("NUM_DEL_FLAG")
    private Integer numDelFlag;


}
