package com.ruoyi.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNoGenerator {
    public static String generateOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        Random random = new Random();
        int randNum = random.nextInt(899) + 100; // 生成三位随机数
        return timestamp + randNum;
    }
}
