package com.couponDigender.comm.core.utils;

import org.springframework.core.convert.converter.Converter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 转换日期
 *
 * @author xia17
 * @date 2019/5/25 14:52
 */
@Slf4j
public class DateConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String s) {
        if (s == null || "".equals(s)) {
            return null;
        }
        try {
            LocalDate parse = LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return parse.atStartOfDay();
        } catch (RuntimeException e) {
            log.error("参数转换Date异常：" + e.getMessage());
        }
        try {
            return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (RuntimeException e) {
            log.error("参数转换DateTime异常" + e.getMessage());
            throw new RuntimeException("在Controller转换日期格式时发生错误");
        }
    }
}


