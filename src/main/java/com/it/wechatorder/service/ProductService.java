package com.it.wechatorder.service;

import com.it.wechatorder.domain.ProductInfo;
import com.it.wechatorder.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    //查询在架所有商品
    List<ProductInfo> findUpAll();

    ProductInfo findOne(String id);

    //管理端分页
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo saveOrUpdate(ProductInfo productInfo);

    //加减库存

    void increaseStock(List<CartDTO> cartDTOList);

    void decreaseStock(List<CartDTO> cartDTOList);
}
