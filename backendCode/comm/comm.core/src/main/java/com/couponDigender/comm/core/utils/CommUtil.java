package com.couponDigender.comm.core.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.couponDigender.comm.core.enmu.ValidateStrategy;
import com.couponDigender.comm.core.extModal.FieldModal;
import com.couponDigender.comm.core.enmu.RespCode;
import com.couponDigender.comm.core.resp.RespData;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 公共工具
 */
public class CommUtil {

    /**
     * 参数校验工具方法
     *
     * @param methodDesc
     * @param param
     * @param strategy
     * @param fieldModals
     * @return
     */
    public RespData validationParam(String methodDesc, Object param, int strategy, FieldModal... fieldModals) {
        try {
            if (methodDesc == null) {
                throw new RuntimeException("校验参数方法描述为空");
            }

            if (param == null) {
                throw new RuntimeException(methodDesc + "校验参数：参数为空");
            }

            if (fieldModals.length == 0) {
                throw new RuntimeException(methodDesc + "校验参数:被校验参数为空");
            }

            // 获取参数对象所有属性列表
            List<Field> paramFieldList = new ArrayList<>();
            for (Class tempClazz = param.getClass(); tempClazz != Object.class; tempClazz = tempClazz.getSuperclass()) {
                Field[] paramFields = tempClazz.getDeclaredFields();
                paramFieldList.addAll(Arrays.asList(paramFields));
            }
            List<FieldModal> paramFieldModalList = paramFieldList
                    .stream()
                    .map(field -> new FieldModal(field.getName(), field.getName()))
                    .collect(Collectors.toList());

            // 校验非法参数
            List<String> paramFieldModalNameList = paramFieldModalList.stream().map(fieldModal -> fieldModal.getName()).collect(Collectors.toList());
            List<FieldModal> noUserdFieldList = Arrays.asList(fieldModals).stream()
                    .filter(fieldModal -> !paramFieldModalNameList.contains(fieldModal.getName()))
                    .collect(Collectors.toList());
            if (noUserdFieldList.size() > 0) {
                return RespData.org(RespCode.FAIL.getCode(), methodDesc + "失败，非法参数 -->" + StringUtils.join(noUserdFieldList.toArray(), ","));
            }

            // 使用map  防止取值时重复遍历list
            JSONObject paramFieldModalJson = new JSONObject();
            paramFieldModalList.forEach(fieldModal -> {
                paramFieldModalJson.put(fieldModal.getName(), fieldModal.getDesc());
            });

            // 组织校验内容
            JSONObject fieldJsonArr = new JSONObject();
            List<FieldModal> fieldModalList = null;
            if (strategy == ValidateStrategy.DEFAULT.getStrategy() || strategy == ValidateStrategy.Positive.getStrategy()) {
                fieldModalList = Arrays.asList(fieldModals);
            } else if (strategy == ValidateStrategy.Reverse.getStrategy()) {
                List<String> fieldModalNameList = Arrays.asList(fieldModals).stream().map(fieldModal -> fieldModal.getName()).collect(Collectors.toList());
                fieldModalList = paramFieldModalList.stream().filter(fieldModal -> !fieldModalNameList.contains(fieldModal.getName())).collect(Collectors.toList());
            } else {
                throw new RuntimeException("未知的参数校验策略");
            }

            for (FieldModal fieldModal : fieldModalList) {

                String getter = "get" + fieldModal.getName().substring(0, 1).toUpperCase() + fieldModal.getName().substring(1, fieldModal.getName().length());
                Method method = param.getClass().getMethod(getter);
                Object vo = method.invoke(param);

                JSONObject fieldBody = new JSONObject();
                fieldBody.put("desc", fieldModal.getDesc());
                fieldBody.put("value", vo);
                fieldJsonArr.put(fieldModal.getName(), fieldBody);
            }


            // 校验内容
            for (Map.Entry<String, Object> fieldJson : fieldJsonArr.entrySet()) {

                JSONObject body = (JSONObject) fieldJson.getValue();
                Object value = body.get("value");

                if (value == null) {
                    return RespData.org(RespCode.FAIL.getCode(), methodDesc + "失败：参数【" + body.getString("desc") + "】不能为空");
                }

                if (value.getClass().getSuperclass() == AbstractList.class) {
                    List list = (List) value;
                    if (list == null || list.size() == 0) {
                        return RespData.org(RespCode.FAIL.getCode(), methodDesc + "失败：参数【" + body.getString("desc") + "】不能为空");
                    }
                }

                String valueStr = String.valueOf(value);
                if (StringUtils.isEmpty(valueStr) || "null".equals(valueStr)) {
                    return RespData.org(RespCode.FAIL.getCode(), methodDesc + "失败：参数【" + body.getString("desc") + "】不能为空");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespData.org(RespCode.EXCEPTION.getCode(), methodDesc + "失败：" + e.getMessage());
        }

        return RespData.org(RespCode.SUCCESS.getCode(), methodDesc + "成功");
    }

}
