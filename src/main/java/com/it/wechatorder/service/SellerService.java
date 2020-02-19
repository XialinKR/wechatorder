package com.it.wechatorder.service;

import com.it.wechatorder.domain.SellerInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);

    Page<SellerInfo> findAll(Pageable pageable);

    SellerInfo findOne(Integer id);

    SellerInfo saveOrUpdate(SellerInfo sellerInfo);

    void delete(Integer id);
}
