package com.it.wechatorder.service.impl;

import com.it.wechatorder.domain.SellerInfo;
import com.it.wechatorder.repository.SellerInfoRepository;
import com.it.wechatorder.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }

    @Override
    public Page<SellerInfo> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public SellerInfo findOne(Integer id) {
        return repository.getOne(id);
    }

    @Override
    public SellerInfo saveOrUpdate(SellerInfo sellerInfo) {
        return repository.save(sellerInfo);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
