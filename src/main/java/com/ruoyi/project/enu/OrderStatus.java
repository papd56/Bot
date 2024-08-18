package com.ruoyi.project.enu;

import java.util.HashMap;

public enum OrderStatus {

    CONFIRMED("待确认", 1),
    CANCELED("订单取消", 2),
    COMPLETED("订单完成", 3);

    /**
     * 描述
     */
    private String desc;

    /**
     * 编码
     */
    private int code;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    OrderStatus(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    private static HashMap<Integer, String> map = new HashMap<Integer, String>();

    static {
        for (OrderStatus d : OrderStatus.values()) {
            map.put(d.code, d.desc);
        }
    }

    public static String getDescByCode(int code) {
        if (map.containsKey(code)) {
            return map.get(code);
        }
        return null;
    }

}
