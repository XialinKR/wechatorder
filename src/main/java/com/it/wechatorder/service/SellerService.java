package com.it.wechatorder.service;

import com.it.wechatorder.domain.SellerInfo;

public interface SellerService {

    SellerInfo findSellerInfoByOpenid(String openid);
}
