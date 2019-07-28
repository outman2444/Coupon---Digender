package com.couponDigender.manage.core.service;

import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.manage.core.extModal.UserExtModal;

public interface UserService {

    /**
     * 通过 wxCode 登陆
     * @param userExtModal
     * @return
     */
    RespData loginByCode(UserExtModal userExtModal);
}
