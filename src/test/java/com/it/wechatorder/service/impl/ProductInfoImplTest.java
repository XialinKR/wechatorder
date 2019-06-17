package com.it.wechatorder.service.impl;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.domain.ProductInfo;
import com.it.wechatorder.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class ProductInfoImplTest extends WechatorderApplicationTests{

    @Autowired
    private ProductService service;

    @Test
    public void findUpAll() {
        List<ProductInfo> list = service.findUpAll();
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void findOne() {
        ProductInfo productInfo = service.findOne("123456");
        Assert.assertEquals("123456",productInfo.getProductId());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,1);
        Page<ProductInfo> list = service.findAll(pageRequest);
        log.info(list.getTotalElements()+"");
    }

    @Test
    public void saveOrUpdate() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123458");
        productInfo.setProductName("泡椒风爪");
        productInfo.setProductPrice(new BigDecimal(15));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("pjfz.png");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(3);
        ProductInfo result = service.saveOrUpdate(productInfo);
        log.info(result.toString());
    }
}