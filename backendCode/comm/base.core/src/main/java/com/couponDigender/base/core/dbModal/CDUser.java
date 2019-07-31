package com.couponDigender.base.core.dbModal;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表实体类
 */

@Entity
@Table(name = "CD_USER")
@org.hibernate.annotations.Table(appliesTo = "CD_USER", comment = "用户信息表")
@Data
public class CDUser {

    // 主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUM_ID")
    private Long numId;

    @Column(name = "VC2_NICK_NAME" , columnDefinition = "varchar(200) COMMENT  '昵称'")
    private String vc2NickName;

    @Column(name = "VC2_AVATAR_URL" , columnDefinition = "varchar(200) COMMENT  '头像'")
    private String vc2AvatarUrl;

    @Column(name = "NUM_GENDER", columnDefinition = "int(1) COMMENT  '性别 0=未知 1=男 2=女'")
    private int numGender;

    @Column(name = "VC2_PROVINCE" , columnDefinition = "varchar(200) COMMENT  '省份'")
    private String vc2Province;

    @Column(name = "VC2_CITY" , columnDefinition = "varchar(200) COMMENT  '城市'")
    private String vc2City;

    @Column(name = "VC2_COUNTRY" , columnDefinition = "varchar(200) COMMENT  '国家'")
    private String vc2Country;

    @Column(name = "VC2_OPEN_ID" , columnDefinition = "varchar(200) COMMENT  'openId'")
    private String vc2OpenId;

    @Column(name = "VC2_UNION_ID" , columnDefinition = "varchar(200) COMMENT  'uinonId'")
    private String vc2UnionId;

    @Column(name = "VC2_ACCESS_TOKEN" , columnDefinition = "varchar(200) COMMENT  'accessToken'")
    private String vc2AccessToken;

    @Column(name = "DAT_CREATE_TIME" , columnDefinition = "datetime COMMENT  '创建时间'")
    private Date datCreateTime;

    @Column(name = "DAT_UPDATE_TIME" , columnDefinition = "datetime COMMENT  '修改时间'")
    private Date datUpdateTime;

    @Column(name = "NUM_DEL_FLAG" , columnDefinition = "varchar(200) COMMENT  '删除标志位  0=可用  1=已删除'")
    private int numDelFlag;
}
