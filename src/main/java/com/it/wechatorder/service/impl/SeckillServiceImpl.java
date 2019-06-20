package com.it.wechatorder.service.impl;

import com.it.wechatorder.domain.ProductInfo;
import com.it.wechatorder.enums.ResultEnum;
import com.it.wechatorder.exception.SellException;
import com.it.wechatorder.service.ProductService;
import com.it.wechatorder.service.RedisLock;
import com.it.wechatorder.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillServiceImpl implements SeckillService{

    //超时时间
    private static final Long TIMEOUT= 3*1000L;

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private ProductService productService;

    @Override
    public void seckillByProductId(String productId,Integer stock) {
        ProductInfo productInfo = productService.findOne(productId);
        if (productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        Long time = System.currentTimeMillis()+TIMEOUT;
        redisLock.lock(productId, String.valueOf(time));
        if (productInfo.getProductStock()==0){
            throw new SellException(100,"活动结束");
        }
        productInfo.setProductStock(productInfo.getProductStock()-1);
        productService.saveOrUpdate(productInfo);
        redisLock.unlock(productId, String.valueOf(time));
    }
}
