package com.it.wechatorder.service.impl;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.domain.SellerInfo;
import com.it.wechatorder.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class SellerServiceImplTest extends WechatorderApplicationTests{

    private static final String OPENID = "oa-El5hwBMeUETZzQaY9Rfd5m45U";

    @Autowired
    private SellerService service;

    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo sellerInfo = service.findSellerInfoByOpenid(OPENID);
        Assert.assertEquals("枉想",sellerInfo.getUsername());
    }
}