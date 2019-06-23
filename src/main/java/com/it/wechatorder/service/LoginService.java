package com.it.wechatorder.service;

import com.it.wechatorder.domain.SellerInfo;

public interface LoginService {

    String findByPhoneAndPassword(String phone,String password,String code);

    void saveSeller(String username, String phone, String password);

    SellerInfo findByPhone(String phone);
    void save(SellerInfo sellerInfo);
}
