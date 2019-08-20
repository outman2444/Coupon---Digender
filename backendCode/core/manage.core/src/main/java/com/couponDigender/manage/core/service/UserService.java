package com.couponDigender.manage.core.service;

import com.couponDigender.comm.core.resp.RespData;
import com.couponDigender.manage.core.extModal.UserExtModal;

public interface UserService {

    /**
     * 通过 wxCode 登陆
     * @param userExtModal
     * @return
     */
    RespData loginByCode(String methodDesc ,UserExtModal userExtModal);

    /**
     * 更新用户信息
     * @param methodDesc
     * @param userExtModal
     * @return
     */
    RespData updateUserInfo(String methodDesc, UserExtModal userExtModal);
}
