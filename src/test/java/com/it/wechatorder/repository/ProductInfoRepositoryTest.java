package com.it.wechatorder.repository;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.domain.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class ProductInfoRepositoryTest extends WechatorderApplicationTests{

    @Autowired
    private ProductRepository repository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> list = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }

    @Test
    public void saveOrUpdateTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setProductName("绝味鸭脖");
        productInfo.setProductPrice(new BigDecimal(15));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("jwyb.png");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        ProductInfo result = repository.save(productInfo);
        log.info(result.toString());
//        ProductInfo productInfo = repository.getOne("123457");
//        productInfo.setProductPrice(new BigDecimal(16));
//        ProductInfo result = repository.save(productInfo);
//        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void deleteTest(){
        repository.deleteById("123457");
    }
}