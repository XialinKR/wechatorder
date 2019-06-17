package com.it.wechatorder.service;

import com.it.wechatorder.domain.ProductCategory;

import java.util.List;

public interface CategoryService {

    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory saveOrUpdate(ProductCategory productCategory);
}
