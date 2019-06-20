package com.it.wechatorder.repository;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.domain.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@Slf4j
public class SellerInfoRepositoryTest extends WechatorderApplicationTests{

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void save() {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setSellerId("1");
        sellerInfo.setUsername("枉想");
        sellerInfo.setPassword("123");
        sellerInfo.setOpenid("oa-El5hwBMeUETZzQaY9Rfd5m45U");
        SellerInfo result = repository.save(sellerInfo);
        Assert.assertNotEquals(null,result);
    }

    @Test
    public void findByOpenid() {
        SellerInfo result = repository.findByOpenid("oa-El5hwBMeUETZzQaY9Rfd5m45U");
        Assert.assertEquals("枉想",result.getUsername());
    }
}