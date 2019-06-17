package com.it.wechatorder.enums;

import lombok.Getter;

@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;
    private String msg;

//    ProductStatusEnum() {
//    }

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}