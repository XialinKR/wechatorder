package com.it.wechatorder.service.impl;

import com.it.wechatorder.domain.ProductCategory;
import com.it.wechatorder.repository.CategoryRepository;
import com.it.wechatorder.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Override
    public ProductCategory findOne(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory saveOrUpdate(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
