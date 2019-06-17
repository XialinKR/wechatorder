package com.it.wechatorder.uitls;

import java.util.UUID;

public class KeyUtil {

    public static String genUniqueKey(){
        return System.currentTimeMillis()+String.valueOf(UUID.randomUUID()).substring(0,6);
    }
}
