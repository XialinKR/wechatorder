package com.it.wechatorder.service.impl;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.domain.ProductInfo;
import com.it.wechatorder.enums.PayStatusEnum;
import com.it.wechatorder.enums.ProductStatusEnum;
import com.it.wechatorder.enums.ResultEnum;
import com.it.wechatorder.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class ProductServiceImplTest extends WechatorderApplicationTests{

    @Autowired
    private ProductService service;

    @Test
    public void onSale() {
        ProductInfo productInfo = service.onSale("1234567");
        Assert.assertEquals(ProductStatusEnum.UP,productInfo.getProductStatusEnum());
    }

    @Test
    public void offSale() {
        ProductInfo productInfo = service.offSale("1234567");
        Assert.assertEquals(ProductStatusEnum.DOWN,productInfo.getProductStatusEnum());
    }
}