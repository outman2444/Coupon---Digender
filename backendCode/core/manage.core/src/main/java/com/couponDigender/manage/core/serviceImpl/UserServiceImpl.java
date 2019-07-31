package com.couponDigender.manage.core.serviceImpl;

import com.couponDigender.base.core.dbDao.CDUserDao;
import com.couponDigender.comm.core.enmu.RespCode;
import com.couponDigender.comm.core.enmu.ValidateStrategy;
import com.couponDigender.comm.core.extModal.FieldModal;
import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.comm.core.utils.CommUtil;
import com.couponDigender.comm.core.utils.HttpUtil;
import com.couponDigender.manage.core.extModal.UserExtModal;
import com.couponDigender.manage.core.service.UserService;
import com.couponDigender.manage.core.utils.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CDUserDao cdUserDao;

    @Override
    public RespData loginByCode(String methodDesc, UserExtModal userExtModal) {

        //参数校验
        RespData validationParamResp = CommUtil.validationParam(methodDesc, userExtModal, ValidateStrategy.Positive.getStrategy(),
                new FieldModal("wxCode", "微信Code")
        );
        if (validationParamResp.getRespCode() != RespCode.SUCCESS.getCode()) {
            return validationParamResp;
        }

        // 执行请求
        RespData httpResp = HttpUtil.get(methodDesc, WXUtil.getCode2SessionUrl(userExtModal.getWxCode()));
        if (httpResp.getRespCode() != RespCode.SUCCESS.getCode()) {
            return httpResp;
        }

        // 如果数据库没有该用户openid 则新加一条记录
//        cdUserDao.

        return httpResp;
    }
}
