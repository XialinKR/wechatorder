package com.it.wechatorder.service;

import com.it.wechatorder.domain.ProductInfo;

public interface SeckillService {

    void seckillByProductId(String productId,Integer stock);
}
