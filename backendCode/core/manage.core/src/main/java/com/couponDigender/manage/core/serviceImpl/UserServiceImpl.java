package com.couponDigender.manage.core.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.couponDigender.base.core.entity.CdUser;
import com.couponDigender.base.core.mapper.CdUserMapper;
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

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CdUserMapper cdUserMapper;

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
        JSONObject jsonBody = JSONObject.parseObject(httpResp.getBody().toString().replace("\"" , "'"));
        String openid = jsonBody.getString("openid");

        // 如果数据库没有该用户openid 则新加一条记录
        QueryWrapper<CdUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("VC2_OPEN_ID" , openid);
        Integer count = cdUserMapper.selectCount(queryWrapper);
        if(count == 0){
            CdUser cdUser = new CdUser();
            cdUser.setVc2OpenId(openid);
            cdUser.setDatCreateTime(LocalDateTime.now());
            cdUser.setDatUpdateTime(LocalDateTime.now());
            cdUser.setNumDelFlag(0);
            cdUserMapper.insert(cdUser);
        }
        return httpResp;
    }

    @Override
    public RespData updateUserInfo(String methodDesc, UserExtModal userExtModal) {

        //参数校验
        RespData validationParamResp = CommUtil.validationParam(methodDesc, userExtModal, ValidateStrategy.Positive.getStrategy(),
                new FieldModal("openId", "openId")
        );
        if (validationParamResp.getRespCode() != RespCode.SUCCESS.getCode()) {
            return validationParamResp;
        }

        // 执行更新
        CdUser cdUser = new CdUser();
        cdUser.setVc2AvatarUrl(userExtModal.getAvatarUrl());
        cdUser.setVc2City(userExtModal.getCity());
        cdUser.setVc2Country(userExtModal.getCountry());
        cdUser.setNumGender(userExtModal.getGender());
        cdUser.setVc2NickName(userExtModal.getNickName());
        cdUser.setVc2Province(userExtModal.getProvince());
        cdUser.setDatUpdateTime(LocalDateTime.now());

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("VC2_OPEN_ID" , userExtModal.getOpenId());
        cdUserMapper.update(cdUser , updateWrapper);
        return RespData.org(RespCode.SUCCESS.getCode() , methodDesc+"成功");
    }
}
