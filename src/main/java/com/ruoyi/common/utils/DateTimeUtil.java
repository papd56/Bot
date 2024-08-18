package com.ruoyi.common.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
    // 将时间戳转换为格式化日期字符串，使用Java 7及之前的SimpleDateFormat
    public static String formatTimestamp(long timestamp, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = new Date(timestamp);
        return sdf.format(date);
    }

    // 将时间戳转换为格式化日期字符串，使用Java 8的DateTimeFormatter
    public static String formatTimestampJava8(long timestamp, String pattern) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    // 使用默认的日期格式 ("yyyy-MM-dd HH:mm:ss") 将时间戳转换为字符串
    public static String formatTimestamp(long timestamp) {
        return formatTimestamp(timestamp, "yyyy-MM-dd HH:mm:ss");
    }

    // 使用默认的日期格式 ("yyyy-MM-dd HH:mm:ss") 将时间戳转换为字符串 (Java 8)
    public static String formatTimestampJava8(long timestamp) {
        return formatTimestampJava8(timestamp, "yyyy-MM-dd HH:mm:ss");
    }
}
