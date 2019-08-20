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
 * 用户信息表
 * </p>
 *
 * @author outman
 * @since 2019-08-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CdUser对象", description="用户信息表")
public class CdUser implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "NUM_ID", type = IdType.AUTO)
    private Long numId;

    @ApiModelProperty(value = "创建时间")
    @TableField("DAT_CREATE_TIME")
    private LocalDateTime datCreateTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("DAT_UPDATE_TIME")
    private LocalDateTime datUpdateTime;

    @ApiModelProperty(value = "删除标志位  0=可用  1=已删除")
    @TableField("NUM_DEL_FLAG")
    private Integer numDelFlag;

    @ApiModelProperty(value = "性别 0=未知 1=男 2=女")
    @TableField("NUM_GENDER")
    private Integer numGender;

    @ApiModelProperty(value = "accessToken")
    @TableField("VC2_ACCESS_TOKEN")
    private String vc2AccessToken;

    @ApiModelProperty(value = "头像")
    @TableField("VC2_AVATAR_URL")
    private String vc2AvatarUrl;

    @ApiModelProperty(value = "城市")
    @TableField("VC2_CITY")
    private String vc2City;

    @ApiModelProperty(value = "国家")
    @TableField("VC2_COUNTRY")
    private String vc2Country;

    @ApiModelProperty(value = "昵称")
    @TableField("VC2_NICK_NAME")
    private String vc2NickName;

    @ApiModelProperty(value = "openId")
    @TableField("VC2_OPEN_ID")
    private String vc2OpenId;

    @ApiModelProperty(value = "省份")
    @TableField("VC2_PROVINCE")
    private String vc2Province;

    @ApiModelProperty(value = "uinonId")
    @TableField("VC2_UNION_ID")
    private String vc2UnionId;


}
