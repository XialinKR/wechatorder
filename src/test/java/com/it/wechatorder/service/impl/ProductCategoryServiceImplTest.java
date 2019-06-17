package com.it.wechatorder.service.impl;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.domain.ProductCategory;
import com.it.wechatorder.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ProductCategoryServiceImplTest extends WechatorderApplicationTests{

    @Autowired
    private CategoryService service;

    @Test
    public void findOne() {
        ProductCategory productCategory = service.findOne(1);
        log.info(productCategory.toString());
    }

    @Test
    public void findAll() {
        List<ProductCategory> list = service.findAll();
        log.info(list.toString());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> list = service.findByCategoryTypeIn(Arrays.asList(1,2));
        Assert.assertEquals(0,list.size());
    }

    @Test
    public void saveOrUpdate() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("熟梨膏");
        productCategory.setCategoryType(6);
        productCategory = service.saveOrUpdate(productCategory);
        Assert.assertNotEquals(null,productCategory);
        productCategory.setCategoryType(5);
        productCategory = service.saveOrUpdate(productCategory);
        Assert.assertNotEquals(null,productCategory);
    }
}