package com.springboot.common.utils;

import java.util.UUID;


/**
 * 获取唯一标识符
 */
public class UUIDUtil {
    private static UUIDUtil uuidUtil;

    public static UUIDUtil getInstance() {

        if (uuidUtil == null) {
            synchronized (UUIDUtil.class) {
                if (uuidUtil == null) {
                    uuidUtil = new UUIDUtil();
                }
            }
        }
        return uuidUtil;
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


}
