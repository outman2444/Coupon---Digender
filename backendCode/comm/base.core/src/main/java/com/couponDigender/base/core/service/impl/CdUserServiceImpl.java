package com.couponDigender.base.core.service.impl;

import com.couponDigender.base.core.entity.CdUser;
import com.couponDigender.base.core.mapper.CdUserMapper;
import com.couponDigender.base.core.service.ICdUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author outman
 * @since 2019-08-06
 */
@Service
public class CdUserServiceImpl extends ServiceImpl<CdUserMapper, CdUser> implements ICdUserService {

}
