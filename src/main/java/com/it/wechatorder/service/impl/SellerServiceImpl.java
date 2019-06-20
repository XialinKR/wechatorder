package com.it.wechatorder.service.impl;

import com.it.wechatorder.domain.SellerInfo;
import com.it.wechatorder.repository.SellerInfoRepository;
import com.it.wechatorder.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
