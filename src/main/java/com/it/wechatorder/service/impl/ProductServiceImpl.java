package com.it.wechatorder.service.impl;

import com.it.wechatorder.domain.ProductInfo;
import com.it.wechatorder.dto.CartDTO;
import com.it.wechatorder.enums.ProductStatusEnum;
import com.it.wechatorder.enums.ResultEnum;
import com.it.wechatorder.exception.SellException;
import com.it.wechatorder.repository.ProductRepository;
import com.it.wechatorder.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repository;

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo findOne(String id) {
        return repository.getOne(id);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo saveOrUpdate(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.getOne(cartDTO.getProductId());
            if (productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = repository.getOne(cartDTO.getProductId());
            if (productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock()-cartDTO.getProductQuantity();
            if (result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
}
