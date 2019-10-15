package com.couponDigender.comm.core.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocalDateTimeUtils {

    public static LocalDateTime seconsLongToLocalDateTime(Long seconsLong) {
        if (seconsLong == null) return null;
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconsLong), ZoneId.systemDefault());
    }
}
