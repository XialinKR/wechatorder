package com.it.wechatorder.service;

import com.it.wechatorder.dto.OrderDTO;

public interface BuyerService {

    OrderDTO findOrderOne(String openid,String orderId);

    OrderDTO cancelOrder(String openid,String orderId);
}
