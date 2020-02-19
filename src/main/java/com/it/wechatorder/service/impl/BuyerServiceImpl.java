package com.it.wechatorder.service.impl;

import com.it.wechatorder.domain.OrderDetail;
import com.it.wechatorder.dto.OrderDTO;
import com.it.wechatorder.enums.ResultEnum;
import com.it.wechatorder.exception.SellException;
import com.it.wechatorder.repository.OrderDetailRepository;
import com.it.wechatorder.service.BuyerService;
import com.it.wechatorder.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService{

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner("oTgZpwag4mHY1vnWTIUf8PTTI-aM",orderId);
        if (orderDTO==null){
            log.error("【取消订单】查不到该订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    public OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO==null){
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase("oTgZpwag4mHY1vnWTIUf8PTTI-aM")){
            log.error("【查询订单】订单的openid不一致.openid={},orderDTO={}",openid,orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
