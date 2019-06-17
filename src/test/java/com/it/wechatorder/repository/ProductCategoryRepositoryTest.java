package com.it.wechatorder.repository;

import com.it.wechatorder.WechatorderApplicationTests;
import com.it.wechatorder.domain.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;


@Slf4j
public class ProductCategoryRepositoryTest extends WechatorderApplicationTests{

    @Autowired
    private CategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.getOne(1);
        log.info(productCategory.toString());
    }

    @Test
    public void saveOrUpdateTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("热销");
        productCategory.setCategoryType(1);
        repository.save(productCategory);
//        ProductCategory productCategory = repository.getOne(4);
//        productCategory.setCategoryName("莲蓉包");
//        productCategory.setCategoryType(4);
//        repository.save(productCategory);
    }

    @Test
    @Transactional
    public void deleteTest(){
//        ProductCategory productCategory = repository.getOne(4);
          repository.deleteById(4);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1,2);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0, result.size());
    }
}