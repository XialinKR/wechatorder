package com.it.wechatorder.service;

import com.it.wechatorder.domain.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    ProductCategory findOne(Integer id);

    Page<ProductCategory> findAll(Pageable pageable);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory saveOrUpdate(ProductCategory productCategory);
}
